package be.vanga.day10;

import java.awt.Point;

public class Coordinate extends	Point	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3393454917727998242L;
	private Point velocity;
	
	public Coordinate(int x, int y, Point v)	{
		super(x, y);
		velocity = v;
	}
	
	public void move()	{
		super.translate(velocity.x, velocity.y);
	}
	public void moveBack()	{
		super.translate(-velocity.x, -velocity.y);
	}
}
