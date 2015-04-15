package edu.moravian.View;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import edu.moravian.Entity.Agent;
import edu.moravian.Entity.Entity;
import edu.moravian.Entity.Player;
import edu.moravian.Entity.Prize;
import edu.moravian.Game.Game;
import edu.moravian.Game.GameMap;
import edu.moravian.Math.CoordinateTranslator;


public class SpriteRenderer {
	
	private Image gold = null;
	private Image spriteSheetImage, spriteSheetImage2 = null;
	private SpriteSheet spriteSheet, spriteSheet2;
	private Animation spearUp, spearDown, spearLeft, spearRight, spearStill, naziUp, naziDown, naziLeft, naziRight, naziStill;
	private int spriteWidth;
	private int spriteHeight;
	private float spriteSheetWidth;
	private float spriteSheetHeight;
	private int spritesPerRow = 11;
	private int spritesPerColumn = 4;
	private int duration = 200; // Time to display each sprite
	private int spriteX;
	private int spriteY;
	private final CoordinateTranslator coordTran;
	
	public SpriteRenderer() throws SlickException {
		gold = new Image("res2/gold.png");
		spriteSheetImage = new Image("res2/spearDude.png");
		spriteSheetImage2 = new Image("res2/nazi_sprite.png");
		spriteSheetWidth = spriteSheetImage.getWidth();
	    spriteSheetHeight = spriteSheetImage.getHeight();
	    // Get width of sprite based on width of the sheet and how many sprites are in it
	    spriteWidth = (int)(spriteSheetWidth/spritesPerRow);
	    // Get height similarly
	    spriteHeight = (int)(spriteSheetHeight/spritesPerColumn);
	    // Now create a SpriteSheet objecoordTran with the new SpriteSheet
	    spriteSheet = new SpriteSheet(spriteSheetImage, spriteWidth, spriteHeight);
	    spriteSheet2 = new SpriteSheet(spriteSheetImage2, spriteWidth, spriteHeight);
	    // SpriteSheet, Start Column, Start Row, End Column, End Row, Scan Horizontally, How long per Image, Continually Cycle 
	    spearUp      = new Animation(spriteSheet, 0, 1, 6, 1, true, duration, true);
	    spearRight   = new Animation(spriteSheet, 0, 3, 6, 3, true, duration, true);
	    spearDown    = new Animation(spriteSheet, 0, 0, 6, 0, true, duration, true);
	    spearLeft    = new Animation(spriteSheet, 0, 2, 6, 2, true, duration, true);
	    spearStill	= new Animation(spriteSheet, 0, 0, 0, 0, true, duration, true);
	    naziUp    = new Animation(spriteSheet2, 0, 1, 6, 1, true, duration, true);
	    naziRight   = new Animation(spriteSheet2, 0, 3, 6, 3, true, duration, true);
	    naziDown    = new Animation(spriteSheet2, 0, 0, 6, 0, true, duration, true);
	    naziLeft    = new Animation(spriteSheet2, 0, 2, 6, 2, true, duration, true);
	    naziStill	= new Animation(spriteSheet2, 0, 0, 0, 0, true, duration, true); 
	    
	    coordTran = Game.getInstance().getCT();
	}
	
    public void update(GameMap gameMap, Entity spriteEntity) {
        if(gameMap.getSx() > coordTran.getWorldWidth()*32 - coordTran.getScreenWidth()) {
            spriteX = (int) (coordTran.getWorldWidth()*32 - gameMap.getSx() + spriteEntity.getEntitySx());
            if(spriteX < 0)
                spriteX = (int) (spriteX + coordTran.getWorldWidth()*32);
            if(spriteX > coordTran.getWorldWidth()*32)
                spriteX = (int) (spriteX%(coordTran.getWorldWidth()*32));
        }
        else if (gameMap.getSx() < 0) {
            spriteX = (int) (-coordTran.getWorldWidth()*32 - gameMap.getSx() + spriteEntity.getEntitySx());
            if(spriteX < 0)
                spriteX = (int) (spriteX + coordTran.getWorldWidth()*32);
            if(spriteX > coordTran.getWorldWidth()*32)
                spriteX = (int) (spriteX%(coordTran.getWorldWidth()*32));
        }
        else {
            spriteX = spriteEntity.getEntitySx() - gameMap.getSx();
        }
        
        if(gameMap.getSy() < 0) {
            spriteY = (int) (-coordTran.getWorldHeight()*32 - gameMap.getSy() + spriteEntity.getEntitySy());
            if (spriteY < 0)
                spriteY = (int) (spriteY + coordTran.getWorldHeight()*32);
            if (spriteY > coordTran.getWorldHeight()*32)
                spriteY = (int) (spriteY%(coordTran.getWorldHeight()*32));
        }
        else if(gameMap.getSy() > coordTran.getWorldHeight()*32 - coordTran.getScreenHeight()) {
            spriteY = (int) (coordTran.getWorldHeight()*32 - gameMap.getSy() + spriteEntity.getEntitySy());
            if (spriteY < 0)
                spriteY = (int) (spriteY + coordTran.getWorldHeight()*32);
            if(spriteY > coordTran.getWorldHeight()*32)
                spriteY = (int) (spriteY%(coordTran.getWorldHeight()*32));
        }
        else {
            spriteY = spriteEntity.getEntitySy() - gameMap.getSy();
        }
    }


	
	public void render(Entity e) {
		if (e instanceof Player) {
			renderPlayer((Player)e);
		}
		else if (e instanceof Agent) {
			renderAgent((Agent)e);
		}
		else if (e instanceof Prize) {
			renderPrize((Prize)e);
		}
		else {
			throw new RuntimeException("Could not render");
		}
		
	}
	
	private void renderPlayer(Player player) {
		
		if (player.getState() == "up") {
			spearUp.draw(spriteX, spriteY);
		}
		if (player.getState() == "down") {
			spearDown.draw(spriteX, spriteY);
		}
		if (player.getState() == "left") {
			spearLeft.draw(spriteX, spriteY);
		}
		if (player.getState() == "right") {
			spearRight.draw(spriteX, spriteY);
		}
		if (player.getState() == "still") {
			spearStill.draw(spriteX, spriteY);
		}
	}
	
	private void renderAgent(Agent agent) {
		
		if (agent.getState() == "up") {
			naziUp.draw(spriteX, spriteY);
		}
		if (agent.getState() == "down") {
			naziDown.draw(spriteX, spriteY);
		}
		if (agent.getState() == "left") {
			naziLeft.draw(spriteX, spriteY);
		}
		if (agent.getState() == "right") {
			naziRight.draw(spriteX, spriteY);
		}
		if (agent.getState() == "still") {
			naziStill.draw(spriteX, spriteY);
		}
	}
	
	private void renderPrize(Prize prize) {
		
		gold.draw(spriteX, spriteY);
		if (prize.getState() == "init") {
			gold.draw(spriteX, spriteY);
		}
		if (prize.getState() == "found") {
			gold.draw(spriteX, spriteY);
			prize.setState("missing");
		}
	}
}
