package edu.moravian.Entity;

import java.awt.Point;


public class Prize extends Entity {
	
	public Prize()
    {
		this.type = "prize";
        entityWx = (int) (Math.random()*((CT.getWorldWidth()*32)*CT.getWorldWidth()/CT.getScreenWidth()));
        entityWy = (int) (Math.random()*((CT.getWorldHeight()*32)*CT.getWorldHeight()/CT.getScreenHeight()));
    }

    @Override
    public void update() 
    {
        Point entityCo = CT.worldToScreen(entityWx, entityWy);
        entitySx = (int) entityCo.getX();
        entitySy = (int)(entityCo.getY() - CT.getScreenHeight())*-1;
    }
    
    @Override
    public void reset()
    {
        entityWx = (int) (Math.random()*((CT.getWorldWidth()*32)*CT.getWorldWidth()/CT.getScreenWidth()));
        entityWy = (int) (Math.random()*((CT.getWorldHeight()*32)*CT.getWorldHeight()/CT.getScreenHeight()));
    }

	@Override
	public void setState(String state) {
		
	}

	@Override
	public String getState() {
		return null;
	}



}
