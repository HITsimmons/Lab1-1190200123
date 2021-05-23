/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        for(int i=1;i<=4;i++) 
        {  
        	turtle.forward(sideLength);  
        	turtle.turn(90);
        }
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) //int型sides返回double需要强转
    {
    	double insideAngles;
    	if(sides<=0) 
    	{
    		return 0;
    	}
    	else 
    	{
    		insideAngles=((double)(sides-2)*(double)(180))/(double)(sides);
    	    return insideAngles;
    	}
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) 
    {
    	double yujiao=180-angle;
    	double a=360/yujiao;
    	int side=(int)Math.round(a); 
    	return side;
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        double insideangels=180-TurtleSoup.calculateRegularPolygonAngle(sides);
        for(int i=0;i<sides;i++) {  
     	   turtle.turn(insideangels);
     	   turtle.forward(sideLength);
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	double x=(double)(targetX-currentX);
    	double y=(double)(targetY-currentY);
    	double p=Math.toDegrees(Math.atan2(y,x));
    	double q=90-p;
    	double angle=q-currentBearing;
    	if(angle<0) {
    		angle+=360;
    		}
    	return angle;
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
    	ArrayList<Double> angles=new ArrayList<Double>();
    	double angle;
    	if(xCoords.size()==2) 
    	{ 
    		angle=calculateBearingToPoint(0,xCoords.get(0),yCoords.get(0),xCoords.get(1),yCoords.get(1));
        	angles.add(angle);
    	}
    	if(xCoords.size()>2)
    	{    
    		angle=calculateBearingToPoint(0,xCoords.get(0),yCoords.get(0),xCoords.get(1),yCoords.get(1));
        	angles.add(angle);
    		for(int i=2;i<xCoords.size();i++) 
    		{
    			angle=calculateBearingToPoint(angles.get(i-2),xCoords.get(i-1),yCoords.get(i-1),xCoords.get(i),yCoords.get(i));
    			angles.add(angle);
    		}
    	}
    	return angles;
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
    	int length;
    	ArrayList<Point> sum=new ArrayList<Point>();
    	ArrayList<Point> point=new ArrayList<Point>();
    	point.addAll(points);  
    	length=point.size();
    	if(length<=3) 
    	{  
    		return points;
    	}
    	else 
    	{
    		Point a=point.get(0);  
    		for(Point t:points) 
    		{
    			if(t.x()<a.x()) 
    			{
    				a=t;
    			}
    			else if(t.x()==a.x()&&t.y()<a.y()) 
    			{
    				a=t;
    			}
    		}
    		sum.add(a); 
    		int i=0;
    		point.remove(a);  
    		Point chudu=a;
    		do {
    			if(i==1) {
    				point.add(a);  
    			}
    			double min2=360,z=0;
    			Point x=null;
    			for(Point t:point) {
    				double min1=calculateBearingToPoint(0,(int)chudu.x(),(int)chudu.y(),(int)t.x(),(int)t.y());//偏转角
    				double y=Math.pow(chudu.x() - t.x(), 2) + Math.pow(chudu.y() - t.y(), 2);
    				if(min1<min2) { 
    					min2=min1;
    					x=t;
    					z=y;
    				}
    				else if(min1==min2&&y>z) { 
    					x=t;
    					z=y;
    				}	
    			}
    			sum.add(x);
    			point.remove(x);
    			chudu=x;
    			i++;
    		}while(sum.get(i)!=a);
    		Set<Point> finalresult = new HashSet<Point>();
    		finalresult.addAll(sum);
    		return finalresult;
    	}
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	PenColor[] color = new PenColor[5];
    	color[0] = PenColor.GREEN;
		color[1] = PenColor.RED;
		color[2] = PenColor.YELLOW;
		color[3] = PenColor.BLUE;
		color[4] = PenColor.BLACK;
		for(int i =0;i<5;i++)
		{
			turtle.color(color[i]);
			turtle.forward(200);
			turtle.turn(144);
		}
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
    	DrawableTurtle turtle = new DrawableTurtle();

        drawSquare(turtle, 40);

        // draw the window
        turtle.draw();
    }

}
