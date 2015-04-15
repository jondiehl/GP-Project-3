
package edu.moravian.StateMachine;
import edu.moravian.Game.Game;
import edu.moravian.Entity.Agent;

public class Eat implements AgentState
{
    private static Eat instance;
    private Eat()
    {}
    
    public static Eat getInstance()
    {
        if(instance == null)
            instance = new Eat();
        return instance;
    }

    @Override
    public void Execute(Agent agentEntity) 
    {
        int health = agentEntity.getHealth();
        if(agentEntity.getEnergy()!=0 && health!=agentEntity.getMaxHealth())
        {
            if(agentEntity.getEntitySx() != Game.getInstance().getWorldWidth()/2 || agentEntity.getEntitySy() != Game.getInstance().getWorldHeight()/2)
            {
                int energy = agentEntity.getEnergy();
                this.performAction(agentEntity);
                energy -= .01;
                agentEntity.setEnergy(energy);
            }
            else
            {
                health+=5;
                agentEntity.setHealth(health);
            }
        }
        else if (agentEntity.getEnergy()==0)
        {
            agentEntity.changeState(Rest.getInstance());
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
        int agentMX = agentEntity.getEntitySx();
        int agentMY = agentEntity.getEntitySy();
        double agentWX = 0;
        double agentWY = 0;
        int delta = Game.getInstance().getDelta();
        if(Game.getInstance().getWorldWidth()/2 != agentMX)
        {
            if(Game.getInstance().getWorldWidth()/2 > agentMX)
            {
                agentWX =(agentEntity.getEntityWx() +1*delta / 30.0) % ((Game.getInstance().getWorldWidth()*32*Game.getInstance().getWorldWidth())/Game.getInstance().getScreenWidth());
            }
            else if(Game.getInstance().getWorldWidth()/2 < agentMX)
            {
                agentWX = (agentEntity.getEntityWx() -1*delta / 30.0);
            }
            agentEntity.setEntityWX(agentWX);
        }
        if(Game.getInstance().getWorldHeight()/2 != agentMY)
        {
            if(Game.getInstance().getWorldHeight()/2 > agentMY)
            {
                agentWY =(agentEntity.getEntityWy() +1*delta / 30.0) % ((Game.getInstance().getWorldHeight()*32*Game.getInstance().getWorldHeight())/Game.getInstance().getScreenHeight());

            }
            else if(Game.getInstance().getWorldHeight()/2 < agentMY)
            {
                agentWY = (agentEntity.getEntityWy() -1*delta / 30.0);
            }
            agentEntity.setEntityWY(agentWY);
        }
    }
}
