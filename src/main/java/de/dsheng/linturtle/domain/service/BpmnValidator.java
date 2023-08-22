package de.dsheng.linturtle.domain.service;

import de.dsheng.linturtle.domain.model.BpmnViolationSource;
import de.dsheng.linturtle.domain.model.CheckerViolationSource;
import de.dsheng.linturtle.domain.model.Violation;
import de.dsheng.linturtle.domain.model.ViolationSet;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.checker.BaseChecker;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import de.dsheng.linturtle.domain.service.port.BpmnValidating;
import org.apache.maven.plugin.logging.Log;

import java.time.Duration;
import java.time.Instant;
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
    public Collection<BpmnViolationSource> validate(Collection<TProcess> bpmnModelCollection, Collection<BaseChecker> checkerCollection) {
        final Collection<BpmnViolationSource> bpmnViolationSourceCollection = new ArrayList<>();
        bpmnModelCollection.forEach(bpmnModel -> {
            log.info(String.format("Check model [%s] for issues", bpmnModel.getName()));

            Instant bpmnViolationSourceStartTime = Instant.now();
            Collection<CheckerViolationSource> checkerViolationSourceCollection = new ArrayList<>();

            checkerCollection.forEach(checker -> {
                Instant checkerViolationSourceStartTime = Instant.now();
                var violations = checker.check(bpmnModel);
                Instant checkerViolationSourceEndTime = Instant.now();
                var checkerDiffTime = Duration.between(checkerViolationSourceStartTime, checkerViolationSourceEndTime).toMillis();
                if(!violations.isEmpty()) {
                    checkerViolationSourceCollection.add(new CheckerViolationSource(checkerDiffTime, violations));
                }
            });
            Instant bpmnViolationSourceEndTime = Instant.now();
            var bpmnDiffTime = Duration.between(bpmnViolationSourceStartTime, bpmnViolationSourceEndTime).toMillis();
            if(!checkerViolationSourceCollection.isEmpty()) {
                bpmnViolationSourceCollection.add(new BpmnViolationSource(bpmnModel.getName(), bpmnDiffTime, checkerViolationSourceCollection));
            }
        });
        return bpmnViolationSourceCollection;
    }
}
