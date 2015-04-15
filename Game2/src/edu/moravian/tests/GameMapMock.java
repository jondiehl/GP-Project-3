package edu.moravian.tests;

import java.lang.reflect.Array;
import java.util.ArrayList;
import edu.moravian.Math.Point2D;
import edu.moravian.tests.GameMapInterface;

public class GameMapMock implements GameMapInterface {
	
	int[][] map = new int[10][10];
	
	GameMapMock() {
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				map[i][j] = 0;
			}
		}
	}

	public Iterable<Point2D> getNeighbors(Point2D p){
		
		ArrayList<Point2D> neighbors = new ArrayList<Point2D>();
		
		int x = (int)p.getX();
		int y = (int)p.getY();
//		int objectLayer = map.getLayerIndex("Objects");
//		System.out.println("Object Layer: " + objectLayer);
		
		//search a specific (x,y) position to determine if there is an object present,
		//	if there is NOT then add it to the list of neighbors.
		//for each neighbor v of p
		System.out.println("used x:" + x);
		System.out.println("used y:" + y);
		
		int count=0;
		for(int i=x-1; i < x+2; i++) {
			for(int j=y-1; j < y+2; j++) {
				//SURROUND BY IF THAT CHECKS FOR -1 & 100
//				if (i != 101 && i != 100 && i != -1) {
//					System.out.println("i: " + i);
//					System.out.println("j: " + j);
//					if (j != 101 && j != 100 && j != -1) {
						if (map[i][j] == 0) { //causes to fail // NEED TO PASS p THROUGH CT TO GET THE CORRECT TILE COORDINATE
							if (i!=x || j!=y) {
								System.out.println("\"no object here\"================" + ++count);
								neighbors.add(new Point2D(i,j));
								System.out.println(i+ " , " + j);
							}
						}
//					}
//				}
			}
		}
		
		return neighbors;
	}
	
	public void set(int index1, int index2, int value) {
		map[index1][index2] = value;
	}
}