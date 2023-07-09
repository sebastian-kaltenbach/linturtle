# BPMN Linter Java

Unofficial maven plugin using Plugin for BPMN 2.0 specification validation

## Benefits

- A
- B
- C

## Usage

```xml
<plugin>
    <groupId>com.ibm</groupId>
    <artifactId>bpmn-linter-maven-plugin</artifactId>
    <version>1.0-SNAPSHOT</version>
    <configuration>
        <failOn>SHOULD</failOn>
        <sourceFolder>src/main/resources</sourceFolder>
        <skipRules>
            <skipRule>GatewayNameNonNullRule</skipRule>
            <skipRule>EndEventNameNonNullRule</skipRule>
        </skipRules>
        <output>
            <path>target/generated-sources/plugin</path>
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