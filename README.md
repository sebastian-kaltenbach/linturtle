# BPMN Linter Java

Unofficial maven plugin using Plugin for BPMN 2.0 specification validation

## Benefits

- A
- B
- C

## Usage

```xml
    <plugin>
        <groupId>de.dsheng</groupId>
        <artifactId>linturtle</artifactId> 
        <configuration>
            <failOn>MUST</failOn>
            <sourceFolder>src/main/resources</sourceFolder>
            <skipRules>
                <skipRule>GatewayNameNonNullRule</skipRule>
                <skipRule>EndEventNameNonNullRule</skipRule>
            </skipRules>
            <skipBPMNs>
                <skipBPMN>skip.bpmn</skipBPMN>
            </skipBPMNs>
            <customRulePackage>com.ibm.model.custom.rules</customRulePackage>
            <output>
                <path>target/generated-sources/linturtle</path>
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