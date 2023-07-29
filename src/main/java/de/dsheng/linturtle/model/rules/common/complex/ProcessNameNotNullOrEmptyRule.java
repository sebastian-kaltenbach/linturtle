package de.dsheng.linturtle.model.rules.common.complex;

import java.util.HashMap;
import java.util.Map;

import org.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.model.ComplexRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Provide name for process", severity = Severity.MUST, targetType = Element.PROCESS)
public class ProcessNameNotNullOrEmptyRule extends ComplexRule {

    @Override
    public Map<String, Boolean> check(TProcess process) {
        Map<String, Boolean> resultSet = new HashMap<>();
        resultSet.put(process.getId(), RuleCheckUtils.notNullOrEmpty(process.getName()));
        return resultSet;
    }
}
