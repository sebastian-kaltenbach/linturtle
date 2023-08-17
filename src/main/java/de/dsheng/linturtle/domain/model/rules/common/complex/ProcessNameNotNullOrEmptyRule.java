package de.dsheng.linturtle.domain.model.rules.common.complex;

import de.dsheng.linturtle.domain.model.entity.Element;
import de.dsheng.linturtle.domain.model.entity.Severity;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.domain.model.BaseRule;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Provide name for process", severity = Severity.MUST, targetType = Element.PROCESS)
public class ProcessNameNotNullOrEmptyRule extends BaseRule {

    @Override
    public boolean check(Object out) {
        TProcess process = (TProcess) out;
        return RuleCheckUtils.notNullOrEmpty(process.getName());     
    }
}
