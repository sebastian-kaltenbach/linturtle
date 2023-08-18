package de.dsheng.linturtle.domain.service.mapping;

import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.annotation.RuleSet;
import de.dsheng.linturtle.domain.model.config.TXmlRule;
import de.dsheng.linturtle.domain.model.config.TXmlRuleSet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConfigMapper {

    ConfigMapper INSTANCE = Mappers.getMapper( ConfigMapper.class );

    /**
     * .
     * @param source
     * @return
     */
    RuleSet dtoToDomain(TXmlRuleSet source);
}
