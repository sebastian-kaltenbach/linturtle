# Overview of common (provided) rules

## Event
Rule | Description
--- | --- 
StartEventIsConnectedRule | Provide connection for start event
StartEventNameNotNullOrEmptyRule | Provide name for start event
IntermediateCatchEventIsConnectedRule | Provide connection for intermediate catch event
IntermediateCatchEventNameNotNullOrEmpty | Provide name for intermediate catch event
IntermediateThrowEventIsConnectedRule | Provide connection for intermediate throw event
IntermediateThrowEventNameNotNullOrEmpty | Provide name for intermediate throw event
EndEventIsConnectedRule | Provide connection for end event
EndEventNameNotNullOrEmptyRule | Provide name for end event

## Gateway
Rule | Description
--- | --- 
GatewayIsConnectedRule | Provide connection for gateway
IncomingGatewayNameNotNullOrEmptyRule | Provide name for incoming gateway

## Task
Rule | Description
--- | --- 
TaskIsConnectedRule | Provide connection for task
TaskNameNotNullOrEmptyRule | Provide name for task
TaskOnlySingleIncomingAndOutgoingFlowRule | Provide only one input and output flow for task

## Complex
Rule | Description
--- | --- 
ProcessNameNotNullOrEmptyRule | Provide name for process
ExclusiveGatewayOutgoingFlowsNameNotNullOrEmptyRule | Provide name for exclusive gateway outgoing flows
ParallelGatewayOutgoingFlowsNameNotNullOrEmptyRule | Provide name for parallel gateway outgoing flows