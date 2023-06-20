package com.ibm;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.ibm.model.Runner;

@Mojo(name = "validate")
public class ValidatePojo extends AbstractMojo {

    @Parameter(property = "sourceFolder", defaultValue = "src/main/resources")
    private String sourceFolder;

    @Parameter(property = "resultFile", defaultValue = "target/generated-source/dummy/")
    private String resultFile;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Hello from Validate Mojo");

        Runner runner = new Runner();
    }
}
