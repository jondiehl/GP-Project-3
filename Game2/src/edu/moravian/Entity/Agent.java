package edu.moravian.Entity;
import java.awt.Point;

import edu.moravian.Math.Point2D;
import edu.moravian.Math.Vector2D;
import edu.moravian.StateMachine.AgentState;
import edu.moravian.StateMachine.Chase;


public class Agent extends Entity {
	
	private AgentState currentState;
	private String currentMovementState;
    private final int maxEnergy;
    private int energy;
    private final int maxHealth;
    private int health;

    public Agent(int maxHealth, int maxEnergy) 
    {
    	this.type = "agent";
        this.currentState = Chase.getInstance();
        this.maxEnergy = maxEnergy;
        this.energy = maxEnergy;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.entityWx = (int) (Math.random()*((CT.getWorldWidth()*32)*CT.getWorldWidth()/CT.getScreenWidth()));
        this.entityWy = (int) (Math.random()*((CT.getWorldHeight()*32)*CT.getWorldHeight()/CT.getScreenHeight()));
    }
    
    @Override
    public void update() 
    {
        Point entityCT = CT.worldToScreen(entityWx, entityWy);
        entitySx = (int) entityCT.getX();
        entitySy = (int)(entityCT.getY() - CT.getScreenHeight())*-1;
        currentState.Execute(this);
    }
    
    @Override
    public void reset()
    {
        this.entityWx = (int) (Math.random()*((CT.getWorldWidth()*32)*CT.getWorldWidth()/CT.getScreenWidth()));
        this.entityWy = (int) (Math.random()*((CT.getWorldHeight()*32)*CT.getWorldHeight()/CT.getScreenHeight()));
    }
    
    public void changeState(AgentState newState)
    {
        currentState = newState;
    }
    
    public void setEntityWX(double entityWX)
    {
        this.entityWx = entityWX;
    }
    
    public void setEntityWY(double entityWY)
    {
        this.entityWy = entityWY;
    }
    
    public int getEnergy() {
        return energy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }
    
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

	@Override
	public void setState(String state) {
		currentMovementState = state;
	}

	@Override
	public String getState() {
		return currentMovementState;
	}
}
