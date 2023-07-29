# Overview of common (provided) rules

## Event
Rule | Description
--- | --- 
StartEventIsConnectedRule | k
StartEventNameNotNullOrEmptyRule | k
IntermediateCatchEventIsConnectedRule | k
IntermediateCatchEventNameNotNullOrEmpty | k
IntermediateThrowEventIsConnectedRule | k
IntermediateThrowEventNameNotNullOrEmpty | k
EndEventIsConnectedRule | k
EndEventNameNotNullOrEmptyRule | k

## Gateway
Rule | Description
--- | --- 
GatewayIsConnectedRule | k
IncomingGatewayNameNotNullOrEmptyRule | k

## Task
Rule | Description
--- | --- 
TaskIsConnectedRule | k
TaskNameNotNullOrEmptyRule | k
TaskOnlySingleIncomingAndOutgoingFlowRule | k

## Complex
Rule | Description
--- | --- 
ProcessNameNotNullOrEmptyRule | k
ExclusiveGatewayOutgoingFlowsNameNonNullOrEmptyRule | k
ParallelGatewayOutgoingFlowsNameNonNullOrEmptyRule | k