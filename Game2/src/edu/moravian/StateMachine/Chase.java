package edu.moravian.StateMachine;

import edu.moravian.Game.Game;
import edu.moravian.Entity.Agent;
import edu.moravian.Entity.Entity;


public class Chase implements AgentState
{
    private static Chase instance;
    private final Entity playerEntity;
    private Agent agentEntity;
    private double agentWX, agentWY;
    
    private Chase()
    {
        playerEntity = Game.getInstance().getEntity(0);
    }
    
    public static Chase getInstance()
    {
        if(instance == null)
            instance = new Chase();
        return instance;
    }

    @Override
    public void Execute(Agent agentEntity) 
    {
        this.agentEntity = agentEntity;
        int energy = agentEntity.getEnergy();
        int health = agentEntity.getHealth();
        if(energy != 0 && health != 0)
        {
            this.performAction(agentEntity);
            energy -= .005;
            health -= .001;
            agentEntity.setEnergy(energy);
            agentEntity.setHealth(health);
        }
        else if (health == 0 && energy == 0 || energy == 0)
        {
            agentEntity.changeState(Rest.getInstance());
        }
        else
        {
            agentEntity.changeState(Eat.getInstance());
        }
    }

    @Override
    public void performAction(Agent agentEntity) 
    {
        int playerMX = playerEntity.getEntitySx();
        int playerMY = playerEntity.getEntitySy();
        int agentMX = agentEntity.getEntitySx();
        int agentMY = agentEntity.getEntitySy();
        int delta = Game.getInstance().getDelta();
        
        if(playerMX != agentMX) {
            if(playerMX > agentMX) {
                if((playerMX - agentMX) > Game.getInstance().getWorldWidth()*32/2) {
                    agentWX = (agentEntity.getEntityWx() -1*delta / 80.0);
                    if(agentWX < 0)
                        agentWX = Game.getInstance().getWorldWidth()*32*Game.getInstance().getWorldWidth()/Game.getInstance().getScreenWidth();
                }
                else {
                    agentWX =(agentEntity.getEntityWx() +1*delta / 80.0) % ((Game.getInstance().getWorldWidth()*32*Game.getInstance().getWorldWidth())/Game.getInstance().getScreenWidth());
                }
                agentEntity.setState("right");
            }
            else if(playerMX < agentMX) {
                if((agentMX - playerMX) > Game.getInstance().getWorldWidth()*32/2) {
                    agentWX =(agentEntity.getEntityWx() +1*delta / 80.0) % ((Game.getInstance().getWorldWidth()*32*Game.getInstance().getWorldWidth())/Game.getInstance().getScreenWidth());
                }
                else {
                    agentWX = (agentEntity.getEntityWx() -1*delta / 80.0);
                    if(agentWX < 0)
                        agentWX = Game.getInstance().getWorldWidth()*32*Game.getInstance().getWorldWidth()/Game.getInstance().getScreenWidth();
                }
                agentEntity.setState("left");

            }
            agentEntity.setEntityWX(agentWX);
        }
        if(playerMY != agentMY) {
            if(playerMY > agentMY) {
                if((playerMY - agentMY) > Game.getInstance().getWorldHeight()*32/2) {
                    agentWY = (agentEntity.getEntityWy() -1*delta / 80.0);
                    if(agentWY < 0)
                        agentWY = Game.getInstance().getWorldHeight()*32*Game.getInstance().getWorldHeight()/Game.getInstance().getScreenHeight();
                }
                else {
                    agentWY =(agentEntity.getEntityWy() +1*delta / 80.0) % ((Game.getInstance().getWorldHeight()*32*Game.getInstance().getWorldHeight())/Game.getInstance().getScreenHeight());
                }
                agentEntity.setState("down");
            }
            else if(playerMY < agentMY) {
                if((agentMY - playerMY) > Game.getInstance().getWorldHeight()*32/2) {
                    agentWY =(agentEntity.getEntityWy() +1*delta / 80.0) % ((Game.getInstance().getWorldHeight()*32*Game.getInstance().getWorldHeight())/Game.getInstance().getScreenHeight());
                }
                else {
                    agentWY = (agentEntity.getEntityWy() -1*delta / 80.0);
                    if(agentWY < 0)
                        agentWX = Game.getInstance().getWorldHeight()*32*Game.getInstance().getWorldHeight()/Game.getInstance().getScreenHeight();
                }
                agentEntity.setState("up");
            }
            agentEntity.setEntityWY(agentWY);
        }
    }
}
