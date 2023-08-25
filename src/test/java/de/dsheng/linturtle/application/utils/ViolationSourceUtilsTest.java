package de.dsheng.linturtle.application.utils;

import de.dsheng.linturtle.domain.model.BpmnViolationSource;
import de.dsheng.linturtle.domain.model.CheckerViolationSource;
import de.dsheng.linturtle.domain.model.Violation;
import de.dsheng.linturtle.domain.model.annotation.ElementConvention;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.ArrayList;
import java.util.Collection;

public class ViolationSourceUtilsTest {

    private final Collection<CheckerViolationSource> violationSourceCollection = new ArrayList<>() {{
        add(new CheckerViolationSource(1, new ArrayList<>(){
            {
                add(new Violation("TestA", new ElementConvention("A", "", null), null, ""));
                add(new Violation("TestB", new ElementConvention("B", "", null), null, ""));
            }
        }));
        add(new CheckerViolationSource(1, new ArrayList<>(){
            {
                add(new Violation("TestC", new ElementConvention("C", "", null), null, ""));
            }
        }));
    }};

    @Test
    public void extractAllViolationsForBpmnModelTest() {
        BpmnViolationSource bpmnViolationSource = new BpmnViolationSource("", 0, 0, this.violationSourceCollection);
        Collection<Violation> violationCollection = new ArrayList<>() {{
            add(new Violation("TestA", new ElementConvention("A", "", null), null, ""));
            add(new Violation("TestB", new ElementConvention("B", "", null), null, ""));
            add(new Violation("TestC", new ElementConvention("C", "", null), null, ""));
        }};

        assertThat(ViolationSourceUtils.extractAllViolationsForBpmnModel(bpmnViolationSource)).isEqualTo(violationCollection);
    }

    @Test
    public void extractTotalViolationCountTest() {
        BpmnViolationSource bpmnViolationSource = new BpmnViolationSource("", 0, 0, this.violationSourceCollection);
        Collection<BpmnViolationSource> bpmnViolationSourceCollection = new ArrayList<>() {{
            add(bpmnViolationSource);
            add(bpmnViolationSource);
        }};
        assertThat(ViolationSourceUtils.extractTotalViolationCount(bpmnViolationSourceCollection)).isEqualTo(6);
    }

    @Test
    public void extractViolationCountByBpmnViolationSourceTest() {
        BpmnViolationSource bpmnViolationSource = new BpmnViolationSource("", 0, 0, this.violationSourceCollection);

        assertThat(ViolationSourceUtils.extractViolationCountByBpmnViolationSource(bpmnViolationSource)).isEqualTo(3);
    }

    @Test
    public void extractTotalTestCountTest() {
        BpmnViolationSource bpmnViolationSource3 = new BpmnViolationSource("", 3, 0, this.violationSourceCollection);
        BpmnViolationSource bpmnViolationSource6 = new BpmnViolationSource("", 6, 0, this.violationSourceCollection);

        Collection<BpmnViolationSource> bpmnViolationSourceCollection = new ArrayList<>() {{
            add(bpmnViolationSource3);
            add(bpmnViolationSource6);
        }};

        assertThat(ViolationSourceUtils.extractTotalTestCount(bpmnViolationSourceCollection)).isEqualTo(9);
    }

    @Test
    public void representTotalTestTimeInMsTest() {
        BpmnViolationSource bpmnViolationSource10 = new BpmnViolationSource("", 0, 10, this.violationSourceCollection);
        BpmnViolationSource bpmnViolationSource25 = new BpmnViolationSource("", 0, 25, this.violationSourceCollection);

        Collection<BpmnViolationSource> bpmnViolationSourceCollection = new ArrayList<>() {{
            add(bpmnViolationSource10);
            add(bpmnViolationSource25);
        }};

        assertThat(ViolationSourceUtils.representTotalTestTimeInMs(bpmnViolationSourceCollection)).isEqualTo("0.035");
    }

    @Test
    public void representTestTimeInMsTest() {
        assertThat(ViolationSourceUtils.representTestTimeInMs(10)).isEqualTo("0.01");
    }
}
