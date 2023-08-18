package de.dsheng.linturtle.domain.service.port;

import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.annotation.RuleSet;

import java.util.Collection;
import java.util.Map;

/**
 * .
 */
@FunctionalInterface
public interface CheckerInitializing {

    /**
     * .
     * @param rulSet
     * @return
     */
    public abstract Map<String, Collection<Rule>> mapping(RuleSet rulSet);
}
