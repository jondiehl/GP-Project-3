package edu.moravian.Game;

import edu.moravian.Entity.Agent;
import edu.moravian.Game.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class GameLog  {
    private final Game game;  
    
    public GameLog(Game game) {
        this.game = game;
    }
    
    public void display(Graphics g) {
        g.setColor(Color.yellow);
        g.drawString("Agent 1 -- Stamina: " + ((Agent) game.getEntity(1)).getEnergy() +"/" + ((Agent) game.getEntity(1)).getMaxEnergy() +" Hunger: " + ((Agent) game.getEntity(1)).getHealth() +"/" + ((Agent) game.getEntity(1)).getMaxHealth(), 10, 30);
        g.setColor(Color.blue);
        g.drawString("Agent 2 -- Stamina: " + ((Agent) game.getEntity(2)).getEnergy() +"/" + ((Agent) game.getEntity(2)).getMaxEnergy() +" Hunger: " + ((Agent) game.getEntity(2)).getHealth() +"/" + ((Agent) game.getEntity(2)).getMaxHealth(), 10, 50);
        g.setColor(Color.pink);
        g.drawString("Agent 3 -- Stamina: " + ((Agent) game.getEntity(3)).getEnergy() +"/" + ((Agent) game.getEntity(3)).getMaxEnergy() +" Hunger: " + ((Agent) game.getEntity(3)).getHealth() +"/" + ((Agent) game.getEntity(3)).getMaxHealth(), 10, 70);
        
        g.setColor(Color.red);
        g.drawString("Player's map location: (" + game.getEntity(0).getEntitySx() + "," + game.getEntity(0).getEntitySy() +")", 30, 110);
        g.drawString("Player's world location: (" + game.getEntity(0).getEntityWx() + "," + game.getEntity(0).getEntityWy() +")", 30, 130);
        g.setColor(Color.green);
        g.drawString("Prize's map location: (" + game.getEntity(4).getEntitySx() + "," + game.getEntity(4).getEntitySy() +")", 30, 170);
        
        g.setColor(Color.white);
        g.drawString("Prizes Found: " + game.getPrizesFound() +"," + " Deaths: " + game.getDeaths(), 30, 210);
    }
}