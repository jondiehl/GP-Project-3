package edu.moravian.StateMachine;

import edu.moravian.Entity.Agent;

public interface AgentState 
{   
    public void Execute(Agent agentEntity);
    public void performAction(Agent agentEntity);
}
