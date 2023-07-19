package de.dsheng.linturtle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.omg.spec.bpmn._20100524.model.TProcess;

import com.typesafe.config.Config;

import de.dsheng.linturtle.model.ViolationSet;
import de.dsheng.linturtle.model.entity.Severity;
import lombok.Getter;

public class ValidationController {

    private Log log;
    private BPMNController bpmnController;
    private RuleController ruleController;
    private ReportController reportController;

    @Getter
    private Map<TProcess, ViolationSet> violationSets;

    public ValidationController(MavenProject project, Config config, String source, Set<String> skipBPMNs, Set<String> skipRules, String customRulePackage, Log log) {
        this.log = log;
        this.bpmnController = new BPMNController(source, skipBPMNs, log).prepare();
        this.ruleController = new RuleController(log).prepare(project, skipRules, customRulePackage);
        this.reportController = new ReportController(config, log);
        this.violationSets = new HashMap<>();
        log.debug("ValidationController initialized.");
    }

    public void execute() {
        log.debug("ValidationController executed.");
        bpmnController.getBpmnModelInstances().forEach(model -> {
            RuleSetController controller = new RuleSetController(ruleController.getActiveRuleSet(), model, log).execute();
            this.violationSets.put(model, controller.getViolationSet());
        });
        reportController.printResultToConsole(ruleController.getActiveRuleSet(), ruleController.getCustomRuleSet(), ruleController.getSkippedRuleSet(), this.violationSets);
    }

    public void executeReport() {
        reportController.execute(this.violationSets);
    }

    public boolean checkViolationsForSeverity(List<Severity> failOn) {
        for(var entry : this.violationSets.entrySet()) {   
            if(!entry.getValue().getViolationsBySeverity(failOn).isEmpty()) return true;
        }
        return false;
    }
}
