package com.ibm;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.ibm.model.ModelCollector;

@Mojo(threadSafe = true, name = "report", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class ReportingMojo extends AbstractMojo {

    @Parameter(required = true, defaultValue = "${project.basedir}/src/main/resources", property = "name.source")
    private String sourceFolder;

    @Parameter(property = "resultFile", defaultValue = "target/generated-source/dummy/bpmn_validation.result.yaml")
    private String resultFile;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        ModelCollector collector = new ModelCollector(sourceFolder);
        collector.collectModelsInSourceFolder();
        getLog().info("Hello from Parser Mojo");
    }
}