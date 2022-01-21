package com.rover.mars.entities;

import com.rover.mars.enums.EnumDirection;
import com.rover.mars.enums.EnumRoverCommands;

public class Rover {
	
	private int roverID; 
	private int xCoordinate;
	private int yCoordinate;
	private EnumDirection direction;
	private Plateau plateau;
	
	
	public Rover(int roverid, int xcoordinate, int ycoordinate, EnumDirection direction) {
			
		this.setRoverID(roverid);
		this.xCoordinate = xcoordinate;
		this.yCoordinate = ycoordinate;
		this.direction = direction;
	}
	
	
	
	public void ExecutedCommands(String directions) throws Exception
	{
		for(char command : directions.toUpperCase().toCharArray())
		{
			if(EnumRoverCommands.TURN_LEFT.getCommandLetter() == command)
			{
				System.out.println("Rover " + roverID + " is turning left/clockwise");
				turnLeft();
			}
			else if(EnumRoverCommands.TURN_RIGHT.getCommandLetter() == command)
			{
				System.out.println("Rover " + roverID + " is turning right/anti-clockwise");
				turnRight();
				
			}
			else if(EnumRoverCommands.MOVE.getCommandLetter() == command)
			{
				System.out.println("Rover " + roverID + " is moving forward");
				move();
			}
			else
			{
				throw new Exception("Invalid commnand: " + command);
			}
			
			plateau.printArea();
		}
	}
	
	
	public void move() throws Exception
	{
		int currX, currY, newX, newY;
		
		currX = newX = xCoordinate;
		currY = newY = yCoordinate;
		
		switch(direction)
		{
			case NORTH:
			{
				newY++;
				break;
			}
			case WEST:
			{
				newX--;
				break;
			}
			case SOUTH:
			{
				newY--;
				break;
			}
			case EAST:
			{
				newX++;
				break;
			}
		}
		
		plateau.roverMove(currX, currY, newX, newY);
		
		// above code will throw an error if the coordinates are bad
		// so if we reach this point its save to say we can update the coordinates
		this.xCoordinate = newX;
		this.yCoordinate = newY;
		
	}
		
	
	public void turnLeft()
	{
		switch(direction)
		{
			case NORTH:
			{
				direction = EnumDirection.WEST;
				break;
				
			}
			case WEST:
			{
				direction = EnumDirection.SOUTH;
				break;
			}
			case SOUTH:
			{
				direction = EnumDirection.EAST;
				break;
			}
			case EAST:
			{
				direction = EnumDirection.NORTH;
				break;
			}
		}
	}
	
	public void turnRight()
	{
		switch(direction)
		{
			case NORTH:
			{
				direction = EnumDirection.EAST;
				break;
				
			}
			case EAST:
			{
				direction = EnumDirection.SOUTH;
				break;
			}
			case SOUTH:
			{
				direction = EnumDirection.WEST;
				break;
			}
			case WEST:
			{
				direction = EnumDirection.NORTH;
				break;
			}
		}
	}
	
	
	
	public int getX() {
		return xCoordinate;
	}
	public void setXCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public int getY() {
		return yCoordinate;
	}
	public void setYCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	public EnumDirection getDirection() {
		return direction;
	}
	public void setDirection(EnumDirection direction) {
		this.direction = direction;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	
	public int getRoverID() {
		return roverID;
	}

	public void setRoverID(int roverID) {
		this.roverID = roverID;
	}
	
	@Override
	public String toString() {
		
		return xCoordinate + " " + yCoordinate + " " + direction.getLetter();
	}
	
	
	@Override
	public int hashCode() {
		// the spec said nothing about IDs... 
		// but I personally like the idea of having a way to uniquely identify a rover
		// in practice you'd probably like to know which rover is which... 
		return getRoverID(); 
	}
	
	@Override
	public boolean equals(Object obj) {

		if(obj instanceof Rover)
		{
			return this.getRoverID() == ((Rover)obj).getRoverID();
		}
		
		return false;
	}



	
	

}
