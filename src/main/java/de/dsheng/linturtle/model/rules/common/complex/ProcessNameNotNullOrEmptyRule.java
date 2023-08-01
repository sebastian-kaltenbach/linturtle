package de.dsheng.linturtle.model.rules.common.complex;

import org.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.model.BaseRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Provide name for process", severity = Severity.MUST, targetType = Element.PROCESS)
public class ProcessNameNotNullOrEmptyRule extends BaseRule {

    @Override
    public boolean check(Object out) {
        TProcess process = (TProcess) out;
        return RuleCheckUtils.notNullOrEmpty(process.getName());     
    }
}
