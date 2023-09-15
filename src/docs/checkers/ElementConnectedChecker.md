# NamingConventionChecker
___
Checker for the control of used element naming conventions.

## Configuration
___
The rule can be configured as follows:
```xml
<rule>
    <!-- Name of used Checker -->
    <name>NamingConventionChecker</name>
    <elementConventions>
        <!-- Element convention for specific rule execution -->
        <elementConvention>
            <!-- Target element -->
            <name>ServiceTask</name>
            <!-- Rule description -->
            <description>Check if ServiceTask name starts with 'DRV'.</description>
            <operations>
                <!-- Name must start with pattern -->
                <operation type = "startsWith">DRV</operation>
                <!-- Name must end with pattern -->
                <operation type = "endsWith">.</operation>
                <!-- Name must contain pattern -->
                <operation type = "contains">Content</operation>
                <!-- Name must correspond to pattern -->
                <operation type = "pattern">([A-Z])\w+</operation>
            </operations>
        </elementConvention>
    </elementConventions>
</rule>
```
An element convention consists of:

- a **name**
- a **description** to describe the convention
- an **operation** to specify the rule behavior, which can consist of:
  - a string representing the naming convention (start)
  - a string representing the naming convention (end)
  - a string representing the  naming convention (partly)
  - a regular expression for the naming convention (pattern)

## Error messages
___
