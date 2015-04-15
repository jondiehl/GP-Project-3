package edu.moravian.Entity;

import java.awt.Point;

import edu.moravian.Game.Game;


public class Player extends Entity {
	
	private int mapX, mapY;
	private String currentState;
	
	public Player() {
		this.type = "player";
	}

	@Override
	public void update() {
		Point entityCoordinate = CT.worldToScreen(Game.getInstance().getX(), Game.getInstance().getY());
		
		mapX = entityCoordinate.x;
		mapY = entityCoordinate.y - CT.getScreenHeight();
		
		entitySx = mapX + CT.getScreenWidth()/2;
		entitySy = mapY + CT.getScreenHeight()/2;
		
		entityWx = (entitySx * CT.getWorldWidth()/CT.getScreenWidth());
		entityWy = (CT.getWorldHeight() * 32 - entitySy) * CT.getWorldHeight()/CT.getScreenHeight();
	}
	
	@Override
	public void reset() {
	}
	
	@Override
	public void setState(String state) {
		currentState = state;
	}
	
	@Override
	public String getState() {
		return currentState;
	}
}
