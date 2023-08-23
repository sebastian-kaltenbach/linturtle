package de.dsheng.linturtle.domain.model;


import java.util.Collection;

public record BpmnViolationSource(

        String processName,
        int testCount,
        long time,
        Collection<CheckerViolationSource> checkerViolationSourceCollection) {

}
