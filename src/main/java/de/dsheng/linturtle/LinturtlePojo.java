package de.dsheng.linturtle;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;

import de.dsheng.linturtle.controller.ValidationController;
import de.dsheng.linturtle.model.entity.Severity;

@Mojo(threadSafe = true, name = "validate")
public class LinturtlePojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(property = "linturtle.sourceFolder", defaultValue = "src/main/resources")
    private String sourceFolder;

    @Parameter(property = "linturtle.failOn")
    private List<Severity> failOn;
    
    @Parameter(property = "linturtle.skip", defaultValue = "false")
    private boolean skip;

    @Parameter(property = "linturtle.report", defaultValue = "true")
    private boolean report;

    @Parameter(property = "linturtle.skipRules")
    private Set<String> skipRules;

    @Parameter(property = "linturtle.skipBPMNs")
    private Set<String> skipBPMNs;

    @Parameter(property = "linturtle.output")
    private Map<String, String> output;

    @Parameter(property = "linturtle.customRulePackage")
    private String customRulePackage;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        printHeader();
        printConfigurationSetup();

        if (skip)
        {
            getLog().info("Skipping execution as requested");
            return;
        }

        if (!failOn.isEmpty())
        {
            if(failOn.contains(Severity.MAY)) failOn.add(Severity.SHOULD);
            if(failOn.contains(Severity.SHOULD)) failOn.add(Severity.MUST); 
        }

        Config config = ConfigValueFactory.fromMap(output).toConfig();
        ValidationController validationController = new ValidationController(project, config, sourceFolder, skipBPMNs, skipRules, customRulePackage, getLog());

        validationController.execute();

        if (report) {
            validationController.executeReport();
        }     

        if(validationController.checkViolationsForSeverity(failOn)){
            throw new MojoFailureException("Failing build due to errors with severity " + failOn.stream().map(Enum::name).collect(Collectors.joining(", ")));
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
        getLog().info("\t- Fail on severity: " + (!failOn.isEmpty() ? failOn.stream().map(Enum::name).collect(Collectors.joining(", ")) : 
            "No errors will fail the build, reporting only. Adjust 'failOn' property to fail on requested severities:" + Arrays.toString(Severity.values())));
        getLog().info("\t- Report Plugin validation result: " + report);
        getLog().info("");
    }
}
