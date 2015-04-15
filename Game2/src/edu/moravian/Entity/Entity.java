package edu.moravian.Entity;

import edu.moravian.Game.Game;
import edu.moravian.Math.CoordinateTranslator;

public abstract class Entity {
	
	protected final CoordinateTranslator CT = Game.getInstance().getCT();
	protected int delta, entitySx, entitySy;
	protected double entityWx, entityWy;
	protected String type;
	
	public Entity() {
	}
	
	public abstract void update();
	
	public abstract void reset();
	
	public int getEntitySx() {
		return entitySx;
	}
	
	public int getEntitySy() {
		return entitySy;
	}
	
	public double getEntityWx() {
		return entityWx;
	}
	
	public double getEntityWy() {
		return entityWy;
	}
	
	public abstract void setState(String state);
	
	public abstract String getState();
	
	public String getType() {
		return type;
	}
	
}
