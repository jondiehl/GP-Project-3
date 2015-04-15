package edu.moravian.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.SlickException;

import edu.moravian.Game.GameMap;
import edu.moravian.Math.Point2D;
import edu.moravian.PathFinding.PathFinder;

public class PathFinderTest {

	@Test
	public void test() {
		GameMapMock map = new GameMapMock();
		PathFinder pf = new PathFinder(map);
//		map.set(0, 0, 1);
//		map.set(0,1,1);
		map.set(9, 9, 1);
		
//		Iterable<Point2D> neighbors = map.getNeighbors(new Point2D(8,8));
//		
//		for (Point2D i : neighbors) {
//			System.out.println(i);
//		}
		
		pf.generatePath(new Point2D(4,4), new Point2D(5,5));
//		fail("not implemented");
		
//		assertEquals(0, new Point2D(1,0));
	}

}
