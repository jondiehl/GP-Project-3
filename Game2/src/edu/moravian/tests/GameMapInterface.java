package edu.moravian.tests;

import edu.moravian.Math.Point2D;

public interface GameMapInterface {
	
	public Iterable<Point2D> getNeighbors(Point2D p);
}
