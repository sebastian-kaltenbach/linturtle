package de.dsheng.linturtle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

import com.typesafe.config.Config;

import de.dsheng.linturtle.controller.handler.BPMNHandler;
import de.dsheng.linturtle.controller.handler.ReportHandler;
import de.dsheng.linturtle.controller.handler.RuleHandler;
import de.dsheng.linturtle.controller.handler.RuleSetHandler;
import de.dsheng.linturtle.model.ViolationSet;
import de.dsheng.linturtle.model.entity.Severity;
import lombok.Getter;

public class ValidationController {

    private Log log;
    private BPMNHandler bpmnController;
    private RuleHandler ruleController;
    private ReportHandler reportController;

    @Getter
    private Map<String, ViolationSet> violationSets;

    public ValidationController(MavenProject project, Config config, String source, Set<String> skipBPMNs, Set<String> skipRules, String customRulePackage, Log log) {
        this.log = log;
        this.bpmnController = new BPMNHandler(source, skipBPMNs, log);
        this.ruleController = new RuleHandler(log).prepare(project, skipRules, customRulePackage);
        this.reportController = new ReportHandler(config, log);
        this.violationSets = new HashMap<>();
        log.debug("ValidationController initialized.");
    }

    public void execute() {
        log.debug("ValidationController executed.");
        bpmnController.getBpmnProviderCollection().forEach(provider -> {
            RuleSetHandler controller = new RuleSetHandler(ruleController.getActiveRuleSet(), provider, log).execute();
            this.violationSets.put(provider.getFileName(), controller.getViolationSet());
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
