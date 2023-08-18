package de.dsheng.linturtle.domain.service;

import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.annotation.RuleSet;
import de.dsheng.linturtle.domain.service.port.CheckerInitializing;
import org.apache.maven.plugin.logging.Log;

import java.util.*;

/**
 * .
 */
public class CheckerSetup implements CheckerInitializing {

    private Log log;
    private final Collection<String> checkers = List.of("TaskNamingConventionChecker");

    public CheckerSetup(Log log) {
        this.log = log;
    }

    @Override
    public Map<String, Collection<Rule>> mapping(RuleSet rulSet) {
        Map<String, Collection<Rule>> collectionMap = new HashMap<>();
        rulSet.rules().forEach(rule -> {
            if(checkers.contains(rule.name())) {
                Collection<Rule> tmpList;
                if(collectionMap.containsKey(rule.name())) {
                    tmpList = collectionMap.get(rule.name());
                }
                else {
                    tmpList = new ArrayList<>();
                }
                tmpList.add(rule);
                collectionMap.put(rule.name(), tmpList);
                log.info(String.format("Rule was added to checker [%s]", rule.name()));
            }
            else {
                var errorMsg = String.format("Checker of config file in rule [%s] is not recognized.", rule.name());
                log.error(errorMsg);
                throw new IllegalArgumentException(errorMsg);
            }
        });
        return collectionMap;
    }
}
