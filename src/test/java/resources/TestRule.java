package resources;

import org.omg.spec.bpmn._20100524.model.TEndEvent;
import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;

@Rule(severity = Severity.MUST, targetType = Element.STARTEVENT, description = "Test Rule for Test purposes")
public class TestRule extends BaseRule {

    public TestRule() {}

    @Override
    public RuleResult check(Object OUT) {
        TEndEvent targetType = (TEndEvent) OUT;
        return new RuleResult(targetType.getName() == null || targetType.getName() == "" ?  false :  true, targetType.getId());
    }
}
