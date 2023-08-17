package resources;

import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TEndEvent;

import de.dsheng.linturtle.domain.model.BaseRule;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.entity.Element;
import de.dsheng.linturtle.domain.model.entity.Severity;

@Rule(severity = Severity.MUST, targetType = Element.STARTEVENT, description = "Test Rule for Test purposes")
public class TestRule extends BaseRule {

    public TestRule() {}

    @Override
    public boolean check(Object element) {
        TEndEvent targetType = (TEndEvent) element;
        return targetType.getName() == null || targetType.getName() == "";
    }
}
