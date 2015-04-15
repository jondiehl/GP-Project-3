package edu.moravian.Game;

import java.awt.Point;
import java.util.ArrayList;

import edu.moravian.Math.Point2D;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import edu.moravian.Math.CoordinateTranslator;
import edu.moravian.tests.GameMapInterface;

public class GameMap implements GameMapInterface {

	private double x, y;
	private int tx, ty; // Distance from the Upper Left Corner to the X and Y axis of the tile it is currently in
	private int plotX, plotY; // How far in the negative direcoordTranion (up and to the left) from the corner point to reach the tile's axes
	private int sx, sy; // What tile do we start rendering at
	private int dsx, dsy; // Number of Tiles to render from upper left corner
	private final int tileWidth = 32;
	private final int tileHeight = 32;
	private final CoordinateTranslator CT;
	private final TiledMap map;
	
	public GameMap(TiledMap map) throws SlickException {
		CT = Game.getInstance().getCT();
		this.map = map;
	}
	
	public Iterable<Point2D> getNeighbors(Point2D p) {
		//going to need a helper function to map all points in a cell to that cell
		
		ArrayList<Point2D> ret = new ArrayList<Point2D>();
		int x = (int)p.getX()/32;
		int y = (int)p.getY()/32;
		int objectLayer = map.getLayerIndex("Objects");
//		System.out.println("Object Layer: " + objectLayer);
		
		//search a specific (x,y) position to determine if there is an object present,
		//	if there is NOT then add it to the list of neighbors.
		//for each neighbor v of p
		System.out.println("used x:" + x);
		System.out.println("used y:" + y);
		
		int count = 0;
		for(int i=x-1; i < x+2; i++) {
			for(int j=y-1; j < y+2; j++) {
				//SURROUND BY IF THAT CHECKS FOR -1 & 100
				if (i != 101 && i != 100 && i != -1) {
//					System.out.println("i: " + i);
//					System.out.println("j: " + j);
					if (j != 101 && j != 100 && j != -1) {
						if (i!=x || j!=y) {
							if (map.getTileId(i, j, objectLayer) == 0) {
								System.out.println("\"no object here\"===================" + ++count);
								ret.add(new Point2D(i,j));
							}
						}
					}
				}
			}
		}
		
		return ret;
	}
	
	public void checkPlayerWrap(double x, double y) {
		this.x = x;
		this.y = y;
		Point screenUpperLeft = CT.worldToScreen(x, y);
		sx = screenUpperLeft.x;
		sy = screenUpperLeft.y - CT.getScreenHeight();
		tx = sx % tileWidth;
		ty = sy % tileHeight;
		plotX = -1 * tx;
		plotY = -1 * ty;
		dsx = sx/tileWidth;
		dsy = sy/tileHeight;
		
		if(sx < -CT.getScreenWidth()/2) {
            this.x = ((CT.getWorldWidth()*32 - CT.getScreenWidth()/2)*CT.getWorldWidth())/(double)CT.getScreenWidth();
        }
        if(sx > CT.getWorldWidth()*32 - CT.getScreenWidth()/2) {
            this.x = -(double)CT.getWorldWidth()/2;
        }
        if(sy < -CT.getScreenHeight()/2) {
            this.y = (CT.getWorldHeight()*32 - CT.getScreenHeight()/2)*CT.getWorldHeight()/-(double)CT.getScreenHeight();
        }
        if(sy > CT.getWorldHeight()*32 - CT.getScreenHeight()/2) {
            this.y = (CT.getScreenHeight()/2*CT.getWorldHeight())/CT.getScreenHeight();
        }
	}
	
	public void render() {
        map.render((int) plotX, (int) plotY, (int) dsx,(int) dsy, CT.getScreenWidth()/32 + 2, CT.getScreenHeight()/32 + 2);
        if(sx > map.getWidth()*32 - CT.getScreenWidth()) {
            map.render((int) plotX, (int) plotY, (int) (dsx - map.getWidth())%(map.getWidth()), (int) dsy, CT.getScreenWidth()/32 + 2, CT.getScreenHeight()/32 + 2);
        }
        if(sx < 0) {
            map.render((int) plotX - 32, (int) plotY, (int) (dsx + map.getWidth() - 1)%(map.getWidth()), (int) dsy, CT.getScreenWidth()/32 + 2, CT.getScreenHeight()/32 + 2);
        }
        if(sy < 0) {
            map.render((int) plotX, (int) plotY - 32, (int) dsx, (int) (dsy + map.getHeight() - 1)%(map.getHeight()), CT.getScreenWidth()/32 + 2, CT.getScreenHeight()/32 + 2);
        }
        if(sy > map.getHeight()*32 - CT.getScreenHeight()) {
            map.render((int) plotX, (int) plotY, (int) dsx, (int) (dsy - map.getHeight())%(map.getHeight()), CT.getScreenWidth()/32 + 2, CT.getScreenHeight()/32 + 2);
        }
        if(sx < 0 && sy < 0) {
            map.render((int) plotX - 32, (int) plotY - 32, (int) (dsx + map.getWidth() - 1)%(map.getWidth()), (int) (dsy + map.getHeight() - 1)%(map.getHeight()), CT.getScreenWidth()/32 + 2, CT.getScreenHeight()/32 + 2);
        }
        if(sx < 0 && sy > map.getHeight()*32 - CT.getScreenHeight()) {
            map.render((int) plotX - 32, (int) plotY, (int) (dsx + map.getWidth() - 1)%(map.getWidth()), (int) (dsy - map.getHeight())%(map.getHeight()), CT.getScreenWidth()/32 + 2, CT.getScreenHeight()/32 + 2);
        }
        if(sx > map.getWidth()*32 - CT.getScreenWidth() && sy < 0) {
            map.render((int) plotX, (int) plotY - 32, (int) (dsx - map.getWidth())%(map.getWidth()), (int) (dsy + map.getHeight() - 1)%(map.getHeight()), CT.getScreenWidth()/32 + 2, CT.getScreenHeight()/32 + 2);
        }
        if(sx > map.getWidth()*32 - CT.getScreenWidth() && sy > map.getHeight()*32 - CT.getScreenHeight()) {
            map.render((int) plotX, (int) plotY, (int) (dsx - map.getWidth())%(map.getWidth()), (int) (dsy - map.getHeight())%(map.getHeight()), CT.getScreenWidth()/32 + 2, CT.getScreenHeight()/32 + 2);
        }
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getSx() {
		return sx;
	}
	
	public int getSy() {
		return sy;
	}

}
