package de.dsheng.linturtle.model;

import java.util.ArrayList;
import java.util.List;

import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Severity;
import lombok.Getter;

public class ViolationSet {

    @Getter
    private List<Violation> violations;

    public ViolationSet() {
        this.violations = new ArrayList<>();
    }

    public List<Violation> getViolationsBySeverity(List<Severity> failOn) {
        return this.violations.stream().filter(violation -> failOn.contains(violation.getRule().getClass()
            .getAnnotation(Rule.class).severity())).toList();
    }
}
