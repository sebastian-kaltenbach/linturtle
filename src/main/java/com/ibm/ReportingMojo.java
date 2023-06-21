package com.ibm;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.ibm.model.BpmnReporter;

@Mojo(threadSafe = true, name = "report", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class ReportingMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;

    @Parameter(required = true, defaultValue = "${project.basedir}/src/main/resources", property = "name.source")
    private String source;

    @Parameter(property = "resultFile", defaultValue = "target/generated-source/dummy/bpmn_validation.result.yaml")
    private String resultFile;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        BpmnReporter reporter = new BpmnReporter(source).execute();

        reporter.getModelCollection().forEach(e -> {
            getLog().info("Analyzing model '" + e.getModel().getModelName() + "'");
            getLog().info("");
            getLog().info("Extraction of model objects");

            getLog().info(reporter.prepareBpmnReport(e));

            getLog().info("");
        });

        getLog().info("Hello from Reporter Mojo");
    }
}