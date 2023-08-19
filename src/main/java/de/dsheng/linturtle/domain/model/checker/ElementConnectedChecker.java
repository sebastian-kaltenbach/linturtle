package de.dsheng.linturtle.domain.model.checker;

import de.dsheng.linturtle.application.utils.AttributeUtils;
import de.dsheng.linturtle.application.utils.BpmnExtractor;
import de.dsheng.linturtle.domain.model.Violation;
import de.dsheng.linturtle.domain.model.annotation.Operation;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.entity.Element;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.*;
import org.apache.maven.plugin.logging.Log;

import java.util.ArrayList;
import java.util.Collection;

public class ElementConnectedChecker extends BaseChecker {

    private Log log;

    public ElementConnectedChecker(Rule rule, Log log) {
        super(rule);
        this.log = log;
    }

    @Override
    public Collection<Violation> check(TProcess process) {
        final Collection<Violation> violations = new ArrayList<>();
        rule.elementConventions().forEach(elementConvention -> {
            log.info(String.format("Check convention with name %s.", elementConvention.name()));
            var targetElements = BpmnExtractor.extractBpmnElementByTargetElement(process, Element.valueOf(elementConvention.name().toUpperCase()));
            targetElements.forEach(targetElement -> {
                log.info(String.format("Target element found with id %s and name %s.", targetElement.getId(), targetElement.getName()));
                boolean result;
                if(targetElement instanceof TTask) {
                    result = taskIsConnected((TTask) targetElement);
                }
                else if(targetElement instanceof TGateway) {
                    result = gatewayIsConnected((TGateway) targetElement);
                }
                else if(targetElement instanceof TEvent) {
                    result = eventIsConnected((TEvent) targetElement);
                }
                else {
                    throw new IllegalArgumentException("Rule name %s is invalid.");
                }
                if(!result) {
                    var operation = new Operation("check", "-");
                    violations.add(new Violation(rule.name(), elementConvention, operation, targetElement.getId()));
                }
            });
        });
        return violations;
    }

    /**
     * .
     * @param task
     * @return
     */
    private boolean taskIsConnected(TTask task) {
        return !task.getIncoming().isEmpty() && !task.getOutgoing().isEmpty();
    }

    /**
     * .
     * @param gateway
     * @return
     */
    private boolean gatewayIsConnected(TGateway gateway) {
        return !gateway.getIncoming().isEmpty() && !gateway.getOutgoing().isEmpty();
    }

    /**
     * .
     * @param event
     * @return
     */
    private boolean eventIsConnected(TEvent event) {
        if(event instanceof TStartEvent) {
            return !event.getOutgoing().isEmpty();
        }
        else if(event instanceof TEndEvent) {
            return !event.getIncoming().isEmpty();
        }
        else {
            return !event.getIncoming().isEmpty() && !event.getOutgoing().isEmpty();
        }
    }
}
