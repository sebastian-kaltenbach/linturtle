# BPMN Linter Java

Unofficial maven plugin using Plugin for BPMN 2.0 specification validation

## Benefits

- A
- B
- C

## Usage

```xml
<!-- Plugin implementation in required pom.xml -->
<plugin>
    <groupId>de.dsheng</groupId>
    <artifactId>linturtle</artifactId> 
    <configuration>
        <!-- Fail on Severity: Selection between _ | MAY | SHOULD | MUST -->
        <failOn>MUST</failOn>
        <!-- Folder containing bpmn processes -->
        <sourceFolder>src/main/resources</sourceFolder>
        <!-- Rules, that should be skipped during the evaluation process -->
        <skipRules>
            <skipRule>GatewayNameNonNullRule</skipRule>
            <skipRule>EndEventNameNonNullRule</skipRule>
        </skipRules>
        <!-- BPMN processes, that should be skipped during the evaluation process -->
        <skipBPMNs>
            <skipBPMN>skip.bpmn</skipBPMN>
        </skipBPMNs>
        <!-- Package for custom rules -->
        <customRulePackage>com.ibm.model.custom.rules</customRulePackage>
        <!-- Report export configuration -->
        <output>
            <!-- Target path for exporting evaluation report -->
            <path>target/generated-sources/linturtle</path>
            <!-- Report format: Selection between JSON | XML -->
            <format>JSON</format>
        </output>
    </configuration>
    <executions>
        <execution>
            <phase>compile</phase>
            <goals>
                <goal>validate</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```