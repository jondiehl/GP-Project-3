package edu.moravian.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import edu.moravian.Game.GameMap;
import edu.moravian.Math.Point2D;

public class GameMapTest {

	@Test
	public void test() throws SlickException {
		TiledMap tMap = new TiledMap("res2/100x100map.tmx");
		GameMap map = new GameMap(tMap);
		ArrayList<Point2D> it = new ArrayList<Point2D>();
		assertEquals( it ,map.getNeighbors(new Point2D(0,0)) );
	}

}
