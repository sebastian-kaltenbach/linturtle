package de.dsheng.linturtle.domain.service;

import de.dsheng.linturtle.domain.model.Violation;
import de.dsheng.linturtle.domain.model.ViolationSet;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.checker.BaseChecker;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import de.dsheng.linturtle.domain.service.port.BpmnValidating;
import org.apache.maven.plugin.logging.Log;

import java.util.ArrayList;
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
    public Collection<ViolationSet> validate(Collection<TProcess> bpmnModelCollection, Collection<BaseChecker> checkerCollection) {
        final Collection<ViolationSet> violationSetCollection = new ArrayList<>();
        bpmnModelCollection.forEach(bpmnModel -> {
            Collection<Violation> violationCollection = new ArrayList<>();
            log.info(String.format("Check model [%s] for issues", bpmnModel.getName()));
            checkerCollection.forEach(checker -> {
                var violations = checker.check(bpmnModel);
                if(!violations.isEmpty()) {
                    violationCollection.addAll(violations);
                }
            });
            violationSetCollection.add(new ViolationSet(bpmnModel.getName(), violationCollection));
        });
        return violationSetCollection;
    }
}
