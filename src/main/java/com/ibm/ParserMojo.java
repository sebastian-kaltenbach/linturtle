package com.ibm;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.ibm.model.ModelCollector;

@Mojo(name = "report")
public class ParserMojo extends AbstractMojo {

    @Parameter(property = "sourceFolder", defaultValue = "src/main/resources")
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