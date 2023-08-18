package de.dsheng.linturtle.domain.service.port;

import de.dsheng.linturtle.domain.model.annotation.RuleSet;
import de.dsheng.linturtle.domain.model.checker.BaseChecker;

import java.util.Collection;

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
    public abstract Collection<BaseChecker>  mapping(RuleSet rulSet);
}
