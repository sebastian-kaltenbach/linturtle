package com.ibm;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.ibm.controller.ValidationController;
import com.ibm.model.annotation.Rule;
import com.ibm.model.entity.Severity;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;

@Mojo(threadSafe = true, name = "validate")
public class ValidatePojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(property = "plugin.sourceFolder", defaultValue = "src/main/resources")
    private String sourceFolder;

    @Parameter(property = "plugin.failOn")
    private List<Severity> failOn;
    
    @Parameter(property = "plugin.skip", defaultValue = "false")
    private boolean skip;

    @Parameter(property = "plugin.report", defaultValue = "true")
    private boolean report;

    @Parameter(property = "plugin.skipRules")
    private Set<String> skipRules;

    @Parameter(property = "plugin.output")
    private Map<String, String> output;

    @Parameter(property = "plugin.customRulePackage")
    private String customRulePackage;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        if (skip)
        {
            getLog().info("Skipping execution as requested");
            return;
        }

        Config config = ConfigValueFactory.fromMap(output).toConfig();
        ValidationController validationController = new ValidationController(project, config, sourceFolder, skipRules, customRulePackage, getLog());

        if (!failOn.isEmpty())
        {
            getLog().info("Will fail build on errors of severity: " + failOn
                    .stream()
                    .map(Enum::name)
                    .collect(Collectors.joining(", ")));
            if(failOn.contains(Severity.MAY)) {
                failOn.add(Severity.SHOULD);
                failOn.add(Severity.MUST);
            }
            if(failOn.contains(Severity.SHOULD)) failOn.add(Severity.MUST);

            
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
            throw new MojoFailureException("Failing build due to errors with severity " + failOn.stream().map(Enum::name).collect(Collectors.joining(", ")));
        }
    }
}
