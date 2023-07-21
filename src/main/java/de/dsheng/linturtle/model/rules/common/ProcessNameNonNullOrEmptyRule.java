package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;

@Rule(description = "Checks if process has a name", id = 1, severity = Severity.MUST, targetType = Element.PROCESS)
public class ProcessNameNonNullOrEmptyRule extends BaseRule {

    @Override
    public RuleResult check(Object OUT) {
        var process = (TProcess) OUT;
        return new RuleResult(process.getName() == null || process.getName() == "" ?  false :  true, process.getId());
    }
}
