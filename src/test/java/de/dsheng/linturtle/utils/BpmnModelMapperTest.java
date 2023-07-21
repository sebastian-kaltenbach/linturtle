package de.dsheng.linturtle.utils;

import java.io.File;
import java.util.NoSuchElementException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.omg.spec.bpmn._20100524.model.TDefinitions;
import org.omg.spec.bpmn._20100524.model.TProcess;

import jakarta.xml.bind.DataBindingException;

public class BpmnModelMapperTest {
    
    @Test
    public void nullFileThrowsException() {
        File fut = null;
        
        //  act & assert
        Assertions.assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> BpmnModelMapper.transformModelToObject(fut));
    }

    @Test
    public void emptyProcessThrowsException() {
        File fut = new File("src/test/java/resources/emptyProcess.bpmn");
        
       //  act & assert
        Assertions.assertThatExceptionOfType(DataBindingException.class).isThrownBy(() -> BpmnModelMapper.transformModelToObject(fut));
    }

    @Test
    public void testProcessIsValid() {
        File fut = new File("src/test/java/resources/testProcess.bpmn");
        
        //  act
        var result = BpmnModelMapper.transformModelToObject(fut);

        //  assert
        Assertions.assertThatObject(result.getClass()).isEqualTo(TDefinitions.class);
    }

    @Test
    public void nullDefinitionThrowsException() {
        TDefinitions definition = null;

       //  act & assert
        Assertions.assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> BpmnModelMapper.transformDefinitionToProcess(definition));
    }

    @Test
    public void definitionDoesNotHaveProcessThrowsException() {
        TDefinitions definition = new TDefinitions();

       //  act & assert
        Assertions.assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> BpmnModelMapper.transformDefinitionToProcess(definition));
    }

    @Test
    public void extractProcessFromDefinitionIsValid() {
        File fut = new File("src/test/java/resources/testProcess.bpmn");
        
        //  act
        var definition = BpmnModelMapper.transformModelToObject(fut);
        var result = BpmnModelMapper.transformDefinitionToProcess(definition);

        //  assert
        Assertions.assertThatObject(result.getClass()).isEqualTo(TProcess.class);
    }
}
