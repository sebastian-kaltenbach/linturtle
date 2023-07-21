package de.dsheng.linturtle.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import resources.TestRule;

public class RuleMapperTest {
    
    @Test
    public void nullClassThrowsException() {
        Class<?> cut = null;

        //  act & assert
        Assertions.assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> RuleMapper.transformClassToRule(cut));
    }

    @Test
    public void cannotParseToBaseRuleThrowsException() {
        Class<?> cut = String.class;

        //  act & assert
        Assertions.assertThatExceptionOfType(ClassCastException.class).isThrownBy(() -> RuleMapper.transformClassToRule(cut));
    }

    @Test
    public void testRuleClassValid() {
        Class<?> cut = TestRule.class;
        
        //  act
        var result = RuleMapper.transformClassToRule(cut);

        //  assert
        Assertions.assertThat(result.getClass()).isEqualTo(new TestRule().getClass());
    }
}
