package de.dsheng.linturtle.domain.service;

import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.annotation.RuleSet;
import de.dsheng.linturtle.domain.model.checker.BaseChecker;
import de.dsheng.linturtle.domain.service.port.CheckerInitializing;
import org.apache.maven.plugin.logging.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * .
 */
public class CheckerSetup implements CheckerInitializing {

    private Log log;
    private final Collection<String> checkers = List.of(
            "NamingConventionChecker",
            "OutgoingGatewayHasNamedFlowsChecker",
            "TaskOnlyHasSingleFlowsChecker",
            "ElementConnectedChecker",
            "OverlapChecker",
            "EventBasedGatewaysLeadIntoEventsChecker"
    );

    public CheckerSetup(Log log) {
        this.log = log;
    }

    @Override
    public Collection<BaseChecker> mapping(RuleSet rulSet) {
        final Collection<BaseChecker> checkerCollection = new ArrayList<>();
        rulSet.rules().forEach(rule -> {
            if(checkers.contains(rule.name())) {
                try {
                    Class<BaseChecker> checkerClazz = (Class<BaseChecker>) Class.forName(String.format("de.dsheng.linturtle.domain.model.checker.%s", rule.name()));
                    checkerCollection.add(checkerClazz.getDeclaredConstructor(Rule.class, Log.class).newInstance(rule, log));
                    log.info(String.format("Class %s was created.", checkerClazz.getSimpleName()));
                } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                         IllegalAccessException | InvocationTargetException e) {
                    log.error(e.getMessage(), e);
                    throw new RuntimeException(e);
                }
                log.info(String.format("Checker [%s] was initialized.", rule.name()));
            }
            else {
                var errorMsg = String.format("Checker of config file in rule [%s] is not recognized.", rule.name());
                log.error(errorMsg);
                throw new IllegalArgumentException(errorMsg);
            }
        });
        return checkerCollection;
    }
}
