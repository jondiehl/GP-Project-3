
package edu.moravian.StateMachine;

import edu.moravian.Entity.Agent;

public class Rest implements AgentState
{
    private static Rest instance;
    
    private Rest()
    {
    }
    
    public static Rest getInstance()
    {
        if(instance == null)
            instance = new Rest();
        return instance;
    }

    @Override
    public void Execute(Agent agentEntity) 
    {
        int energy = agentEntity.getEnergy();
        if(energy!=agentEntity.getMaxEnergy())
        {
            energy+=5;
            agentEntity.setEnergy(energy);
        }
        else
        {
            agentEntity.changeState(Chase.getInstance());
        }
        agentEntity.setState("still");
    }

    @Override
    public void performAction(Agent agentEntity) 
    {
    }
}
