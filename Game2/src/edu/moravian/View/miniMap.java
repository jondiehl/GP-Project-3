package edu.moravian.View;

import edu.moravian.Game.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class miniMap 
{
    private final int miniMapWidth = 200, miniMapHeight = 200;
    private int miniMapPlayerX, miniMapPlayerY, miniMapAgentX, miniMapAgentY, miniMapAgent1X, miniMapAgent1Y, miniMapAgent2X, miniMapAgent2Y, miniMapTreasureX, miniMapTreasureY;
    private final Image miniMap;
    private final Game game;
    public miniMap(Game game, String filePath) throws SlickException
    {
        this.game = game;
        this.miniMap = new Image(filePath);
    }
    
    public void update()
    {
        miniMapPlayerX = (int)game.getEntity(0).getEntityWx()*1600/(Game.getInstance().getWorldWidth()*32) + (Game.getInstance().getScreenWidth() - 200 - 10);
        miniMapPlayerY = -(int)game.getEntity(0).getEntityWy()*1200/(Game.getInstance().getWorldHeight()*32) + 206;
        miniMapAgentX = (int)game.getEntity(1).getEntityWx()*1600/(Game.getInstance().getWorldWidth()*32) + (Game.getInstance().getScreenWidth() - 200 - 10);
        miniMapAgentY = (int)game.getEntity(1).getEntityWy()*1200/(Game.getInstance().getWorldHeight()*32) + 10;
        miniMapAgent1X = (int)game.getEntity(2).getEntityWx()*1600/(Game.getInstance().getWorldWidth()*32) + (Game.getInstance().getScreenWidth() - 200 - 10);
        miniMapAgent1Y = (int)game.getEntity(2).getEntityWy()*1200/(Game.getInstance().getWorldHeight()*32) + 10;
        miniMapAgent2X = (int)game.getEntity(3).getEntityWx()*1600/(Game.getInstance().getWorldWidth()*32) + (Game.getInstance().getScreenWidth() - 200 - 10);
        miniMapAgent2Y = (int)game.getEntity(3).getEntityWy()*1200/(Game.getInstance().getWorldHeight()*32) + 10;
        miniMapTreasureX = (int)game.getEntity(4).getEntityWx()*1600/(Game.getInstance().getWorldWidth()*32) + (Game.getInstance().getScreenWidth() - 200 - 10);
        miniMapTreasureY = (int)game.getEntity(4).getEntityWy()*1200/(Game.getInstance().getWorldHeight()*32) + 10;
    }
    
    public void render(Graphics grphcs)
    {
        grphcs.setColor(Color.red);
        grphcs.drawRect(Game.getInstance().getScreenWidth() - miniMapWidth - 11, 9, miniMapWidth+1, miniMapHeight+1);
        grphcs.flush();
        miniMap.draw(Game.getInstance().getScreenWidth() - miniMapWidth - 10, 10, miniMapWidth, miniMapHeight);
        grphcs.fillRect(miniMapPlayerX, miniMapPlayerY, 5, 5);
        grphcs.setColor(Color.yellow);
        grphcs.fillRect(miniMapAgentX, miniMapAgentY, 5, 5);
        grphcs.setColor(Color.blue);
        grphcs.fillRect(miniMapAgent1X, miniMapAgent1Y, 5, 5);
        grphcs.setColor(Color.pink);
        grphcs.fillRect(miniMapAgent2X, miniMapAgent2Y, 5, 5);
        grphcs.setColor(Color.green);
        grphcs.fillRect(miniMapTreasureX, miniMapTreasureY, 5, 5);

    }
}
