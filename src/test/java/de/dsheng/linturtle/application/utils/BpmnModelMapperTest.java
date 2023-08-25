package de.dsheng.linturtle.application.utils;

import java.io.File;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatObject;

import org.junit.jupiter.api.Test;

import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TDefinitions;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;

import jakarta.xml.bind.DataBindingException;

public class BpmnModelMapperTest {
    
    @Test
    public void nullFileThrowsException() {
        File fut = null;
        
        //  act & assert
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> BpmnModelMapper.transformModelToObject(fut));
    }

    @Test
    public void emptyProcessThrowsException() {
        File fut = new File("src/test/java/resources/emptyProcess.bpmn");
        
       //  act & assert
        assertThatExceptionOfType(DataBindingException.class).isThrownBy(() -> BpmnModelMapper.transformModelToObject(fut));
    }

    @Test
    public void testProcessIsValid() {
        File fut = new File("src/test/java/resources/testProcess.bpmn");
        
        //  act
        var result = BpmnModelMapper.transformModelToObject(fut);

        //  assert
        assertThatObject(result.getClass()).isEqualTo(TDefinitions.class);
    }

    @Test
    public void nullDefinitionThrowsException() {
        TDefinitions definition = null;

       //  act & assert
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> BpmnModelMapper.transformDefinitionToProcess(definition));
    }

    @Test
    public void definitionDoesNotHaveProcessThrowsException() {
        TDefinitions definition = new TDefinitions();

       //  act & assert
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> BpmnModelMapper.transformDefinitionToProcess(definition));
    }

    @Test
    public void extractProcessFromDefinitionIsValid() {
        File fut = new File("src/test/java/resources/testProcess.bpmn");
        
        //  act
        var definition = BpmnModelMapper.transformModelToObject(fut);
        var result = BpmnModelMapper.transformDefinitionToProcess(definition);

        //  assert
        assertThatObject(result.getClass()).isEqualTo(TProcess.class);
    }
}
