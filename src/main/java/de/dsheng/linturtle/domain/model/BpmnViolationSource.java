package de.dsheng.linturtle.domain.model;


import java.util.Collection;

public record BpmnViolationSource(

        String processName,
        long time,
        Collection<CheckerViolationSource> checkerViolationSourceCollection) {

}
