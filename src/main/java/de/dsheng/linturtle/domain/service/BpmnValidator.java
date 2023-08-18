package de.dsheng.linturtle.domain.service;

import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.checker.BaseChecker;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import de.dsheng.linturtle.domain.service.port.BpmnValidating;
import org.apache.maven.plugin.logging.Log;

import java.util.Collection;
import java.util.Map;

public class BpmnValidator implements BpmnValidating {

    private Log log;

    public  BpmnValidator(Log log) {
        this.log = log;
    }

    /**
     * .
     * @param bpmnModelCollection
     * @param checkerCollection
     * @return
     */
    @Override
    public Collection<?> validate(Collection<TProcess> bpmnModelCollection, Collection<BaseChecker> checkerCollection) {

        bpmnModelCollection.forEach(bpmnModel -> {
            log.info(String.format("Check model [%s] for issues", bpmnModel.getName()));
            checkerCollection.forEach(checker -> {
                checker.check(bpmnModel);
            });
        });

        return null;
    }
}
