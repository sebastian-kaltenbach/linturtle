package de.dsheng.linturtle.domain.mapping;

import de.dsheng.linturtle.domain.model.annotation.ElementConvention;
import de.dsheng.linturtle.domain.model.annotation.Operation;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.annotation.RuleSet;
import de.dsheng.linturtle.domain.model.config.TXmlElementConvention;
import de.dsheng.linturtle.domain.model.config.TXmlOperation;
import de.dsheng.linturtle.domain.model.config.TXmlRule;
import de.dsheng.linturtle.domain.model.config.TXmlRuleSet;
import de.dsheng.linturtle.domain.service.mapping.ConfigMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigMapperTest {

    private final TXmlRuleSet tXmlRuleSet = new TXmlRuleSet(new ArrayList<TXmlRule>(){{
        add(new TXmlRule("1", "Test1", "Desc1", new ArrayList<TXmlElementConvention>() {{
            add(new TXmlElementConvention("Name1", "Desc1", new ArrayList<TXmlOperation>() {{
                add(new TXmlOperation("Type1", "Value1"));
                add(new TXmlOperation("Type2", "Value2"));
            }}));
            add(new TXmlElementConvention("Name2", "Desc2", new ArrayList<TXmlOperation>() {{
                add(new TXmlOperation("Type1", "Value1"));
                add(new TXmlOperation("Type2", "Value2"));
            }}));
        }}));
        add(new TXmlRule("2", "Test2", "Desc2", new ArrayList<TXmlElementConvention>() {{
            add(new TXmlElementConvention("Name1", "Desc1", new ArrayList<TXmlOperation>() {{
                add(new TXmlOperation("Type1", "Value1"));
                add(new TXmlOperation("Type2", "Value2"));
            }}));
        }}));
    }});

    @Test
    public void dtoToDomainTest() {
        RuleSet ruleSet = new RuleSet(new ArrayList<Rule>(){{
            add(new Rule("1", "Test1", "Desc1", new ArrayList<ElementConvention>() {{
                add(new ElementConvention("Name1", "Desc1", new ArrayList<Operation>() {{
                    add(new Operation("Type1", "Value1"));
                    add(new Operation("Type2", "Value2"));
                }}));
                add(new ElementConvention("Name2", "Desc2", new ArrayList<Operation>() {{
                    add(new Operation("Type1", "Value1"));
                    add(new Operation("Type2", "Value2"));
                }}));
            }}));
            add(new Rule("2", "Test2", "Desc2", new ArrayList<ElementConvention>() {{
                add(new ElementConvention("Name1", "Desc1", new ArrayList<Operation>() {{
                    add(new Operation("Type1", "Value1"));
                    add(new Operation("Type2", "Value2"));
                }}));
            }}));
        }});

        assertThat(ConfigMapper.INSTANCE.dtoToDomain(this.tXmlRuleSet)).isEqualTo(ruleSet);
    }
}
