package de.dsheng.linturtle;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import de.dsheng.linturtle.adapter.BPMNCollector;
import de.dsheng.linturtle.domain.service.BpmnValidator;
import de.dsheng.linturtle.domain.service.CheckerSetup;
import de.dsheng.linturtle.adapter.ViolationExtractor;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;

import de.dsheng.linturtle.adapter.XmlConfigReader;
//import de.dsheng.linturtle.controller.ValidationController;


@Mojo(threadSafe = true, name = "validate")
public class LinturtlePojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(property = "linturtle.configFile", defaultValue = "target/classes/linturtle/example.xml")
    private String configFile;

    @Parameter(property = "linturtle.sourceFolder", defaultValue = "src/main/resources")
    private String sourceFolder;
    
    @Parameter(property = "linturtle.skip", defaultValue = "false")
    private boolean skip;

    @Parameter(property = "linturtle.report", defaultValue = "true")
    private boolean report;

    @Parameter(property = "linturtle.failOnViolation", defaultValue = "true")
    private boolean failOnViolation;

    @Parameter(property = "linturtle.skipBPMNs")
    private Set<String> skipBPMNs;

    @Parameter(property = "linturtle.export")
    private Map<String, String> export;

    @Override
    public void execute() throws MojoFailureException {

        printHeader();
        printConfigurationSetup();

        if (skip)
        {
            getLog().info("Skipping execution as requested");
            return;
        }

        //  Step 1 | Gather Bpmn Models of project
        var bpmnCollector = new BPMNCollector(getLog());
        var bpmnModelCollection = bpmnCollector.collect(this.sourceFolder);

        // Step 2 | Gather config file of project
        var configReader = new XmlConfigReader(getLog());
        var ruleSet = configReader.collect(this.configFile);

        // Step 3 | Match Rules with available Checkers
        var checkerSetup = new CheckerSetup(getLog());
        var checkerCollection = checkerSetup.mapping(ruleSet);

        // Step 4 | Validating Rules per Checker
        var bpmnValidator = new BpmnValidator(getLog());
        var violationSetCollection = bpmnValidator.validate(bpmnModelCollection, checkerCollection);

        // Step 5 | Reporting result to Console
        var violationExtractor = new ViolationExtractor(getLog());
        violationExtractor.log(violationSetCollection);

        // Step 6 | Export report if required
        if (report) {
            Config config = ConfigValueFactory.fromMap(export).toConfig();
            try {
                violationExtractor.export(config, violationSetCollection);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Step 7 | Fail build if config is set
        if(failOnViolation) {
            var totalViolations = violationSetCollection.stream().map(violationSet -> violationSet.violations().size())
                    .reduce(0, Integer::sum);
            if(totalViolations > 0) throw new MojoFailureException(String.format("Build failed because of a total of %d violation(s).", totalViolations));
        }
    }
    
    private void printHeader() {
        getLog().info("");
        getLog().info("  _     _       _              _   _      ");
        getLog().info(" | |   (_)_ __ | |_ _   _ _ __| |_| | ___ ");
        getLog().info(" | |   | | '_ \\| __| | | | '__| __| |/ _ \\");
        getLog().info(" | |___| | | | | |_| |_| | |  | |_| |  __/");
        getLog().info(" |_____|_|_| |_|\\__|\\__,_|_|   \\__|_|\\___|");
        getLog().info("                                          ");
        getLog().info("");
    }

    private void printConfigurationSetup() {
        getLog().info("");
        getLog().info("Linturte is configured with the following parameters:");
        getLog().info("\t- Skip Plugin execution: " + skip);
        getLog().info("\t- Report Plugin validation result: " + report);
        getLog().info("");
    }
}
