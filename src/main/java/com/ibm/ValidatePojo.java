package com.ibm;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.ibm.controller.ReportController;
import com.ibm.controller.ValidationController;
import com.ibm.model.entity.Severity;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;

@Mojo(name = "validate")
public class ValidatePojo extends AbstractMojo {

    @Parameter(property = "plugin.sourceFolder", defaultValue = "src/main/resources")
    private String sourceFolder;

    @Parameter(property = "plugin.failOn")
    private List<Severity> failOn;
    
    @Parameter(property = "plugin.skip", defaultValue = "false")
    private boolean skip;

    @Parameter(property = "plugin.report", defaultValue = "true")
    private boolean report;

    @Parameter(property = "plugin.output")
    private Map<String, String> output;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        if (skip)
        {
            getLog().info("Skipping execution as requested");
            return;
        }

        Config config = ConfigValueFactory.fromMap(output).toConfig();
        ValidationController validationController = new ValidationController(config, sourceFolder, getLog());

        if (!failOn.isEmpty())
        {
            getLog().info("Will fail build on errors of severity: " + failOn
                    .stream()
                    .map(Enum::name)
                    .collect(Collectors.joining(", ")));
        }
        else
        {
            getLog().warn("No errors will fail the build, reporting only. Adjust 'failOn' " +
                    "property to fail on requested severities:" + Arrays.toString(Severity.values()));
        }

        validationController.execute();

        if (report) { 
            validationController.executeReportController();
        }

        if(validationController.checkViolationsForSeverity(failOn)){
            throw new RuntimeException();
        }
    }
}
