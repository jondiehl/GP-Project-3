package edu.moravian.PathFinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Comparator;

//import org.newdawn.slick.util.pathfinding.AStarPathFinder;

import edu.moravian.Game.GameMap;
import edu.moravian.Math.Point2D;
import edu.moravian.tests.GameMapInterface;

public class PathFinder {

	private GameMapInterface map;
	private LinkedList<Point2D> path;
	private Set<Edge> consideredEdges;
	
	public PathFinder(GameMapInterface map) {
		this.map = map;
		consideredEdges = new HashSet<Edge>();
	}
	
	//A* here
	public void generatePath(Point2D start, Point2D end) {
		
		//PQ, here's 8 points, whats the closest point? have to implement comparable that uses distance and vector approx.
		
		Point2D u = new Point2D();
		Point2D current = new Point2D();
		
//		for each vertex u within V:
//		d[u] = infinity
//		pred[u] = NULL
		
		//need a map for distance and predecessors
		Map<Point2D, Double> d = new HashMap<>();
//		ArrayList<Integer> d = new ArrayList<Integer>();
		Map<Point2D, Point2D> pred = new HashMap<>();
		//ArrayList<Integer> pred = new ArrayList<Integer>();
		
//		d[s] = 0
//		//# Put all the vertexes of G into the priority queue
//		Q = V
		
		//set first node's dist. to 0
		d.put(start, 0.0);
		//PQ with comparator that will figure out ordering for the minimum distance to end
		PriorityQueue<Point2D> PQ = new PriorityQueue<Point2D>(new Comparator<Point2D>(){
			@Override
			public int compare(Point2D p1, Point2D p2) {
				double w1 = w(end, p1);
				double w2 = w(end, p2);
				return (int)(w1-w2);
			}
		});
		
		//add start node.point to Q
		PQ.add(start);
		
		System.out.println("PQ: " + PQ);
		
//		while not Q.empty()
//			//# Take the Vertex in Q with the minimum distance
//			u = ExtractMin(Q)

		while (!PQ.isEmpty()) {
			//Take the vertex in PQ with the minimum distance
			u = PQ.poll();
			
//			for each v adjacent to u
//				if d[v] > d[u] + w(u, v)
//					d[v] = d[u] + w(u, v)
//					pred[v] = u
//					//add edge(u,v) to consideredEdges
			
			//for weight, either going to be 1 or root 2.
			//if ( x==x+-1 && y==y ) || ( x==x && y==y+-1 ) weight = 1
			//else weight = root 2		..(can you check nodes farther away than neighbors?)
			
			//w(u,v) is just the distance on the graph from u to v, use distance function
			
			Iterable<Point2D> adjacent = map.getNeighbors(u);
			
//			System.out.println("outside of for loop");
			for (Point2D v : adjacent) {
//				System.out.println("insdie for loop");
//				System.out.println("neighbor: " + v);
//				System.out.println(": " + !PQ.contains(v));
				
//				boolean flag = false;
//				Iterator<Point2D> it = PQ.iterator();
//				System.out.println("iterator: " + it.next());
//				for (Point2D e : PQ) {
//					System.out.println("x: " + current.getX());
//					System.out.println("x: " + v.getX());
//					if (Math.abs(e.getX() - v.getX()) > 0.00001){
//						flag=true;
//					}
//					if (Math.abs(e.getY() - v.getY()) > 0.00001){
//						flag=true;
//					}
//				}
				
				if (!PQ.contains(v)) {
//					System.out.println("added v: " + v);
					d.put(v, d.get(u) + w(u,v));
					pred.put(v, u);
					PQ.add(v);
					consideredEdges.add(new Edge(u, v));
//					System.out.println("here");
				}
			}
		}		
	}

	//Returns distance from point a to point b; equivalent to weight
	public double w(Point2D a, Point2D b) {
		return Math.sqrt( (a.getX()-b.getX())*(a.getX()-b.getX()) + 
							(a.getY()-b.getY())*(a.getY()-b.getY()) );
	}
	
	public LinkedList<Point2D> getPath() {
		return path;
	}
	
	public Set<Edge> getConsideredEdges() {
		return consideredEdges;
	}
}
