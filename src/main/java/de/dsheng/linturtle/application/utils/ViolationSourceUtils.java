package de.dsheng.linturtle.application.utils;

import de.dsheng.linturtle.domain.model.BpmnViolationSource;
import de.dsheng.linturtle.domain.model.CheckerViolationSource;
import de.dsheng.linturtle.domain.model.Violation;

import java.util.Collection;

public class ViolationSourceUtils {

    /**
     * .
     * @param bpmnViolationSource
     * @return
     */
    public static Collection<Violation> extractAllViolationsForBpmnModel(BpmnViolationSource bpmnViolationSource) {
        return bpmnViolationSource.checkerViolationSourceCollection().stream().flatMap(checkerViolationSource ->
                checkerViolationSource.violationCollection().stream()).toList();
    }

    /**
     * .
     * @param bpmnViolationSourceCollection
     * @return
     */
    public static int extractTotalViolationCount(Collection<BpmnViolationSource> bpmnViolationSourceCollection) {
        return bpmnViolationSourceCollection.stream().flatMap(bpmnViolationSource ->
                bpmnViolationSource.checkerViolationSourceCollection().stream().map(checkerViolationSource ->
                        checkerViolationSource.violationCollection().size())).reduce(0, Integer::sum);
    }

    /**
     * .
     * @param bpmnViolationSource
     * @return
     */
    public static int extractViolationCuntByBpmnViolationSource(BpmnViolationSource bpmnViolationSource)  {
        return bpmnViolationSource.checkerViolationSourceCollection().stream().map(checkerViolationSource ->
                checkerViolationSource.violationCollection().size()).reduce(0, Integer::sum);
    }
}
