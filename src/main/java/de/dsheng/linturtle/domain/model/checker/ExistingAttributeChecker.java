package de.dsheng.linturtle.domain.model.checker;

import de.dsheng.linturtle.application.utils.BpmnExtractor;
import de.dsheng.linturtle.domain.model.Violation;
import de.dsheng.linturtle.domain.model.ViolationSource;
import de.dsheng.linturtle.domain.model.annotation.Operation;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.entity.Element;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.*;
import org.apache.maven.plugin.logging.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Checker for existing attributes within the bpmn model (xml)
 */
public class ExistingAttributeChecker extends BaseChecker {

    private Log log;

    public ExistingAttributeChecker(Rule rule, Log log) {
        super(rule);
        this.log = log;
    }

    @Override
    public ViolationSource check(TProcess process) {
        final Collection<Violation> violationCollection = new ArrayList<>();
        AtomicInteger testCount = new AtomicInteger();
        rule.elementConventions().forEach(elementConvention -> {
            log.info(String.format("Check convention with name %s.", elementConvention.name()));
        });
        return new ViolationSource(testCount.get(),violationCollection);
    }
}
