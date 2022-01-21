package com.rover.mars.entities;

import java.util.HashMap;
import java.util.HashSet;

import com.rover.mars.enums.EnumDirection;

public class Plateau {
	
	private Rover[][] grid;
	
	// It's easier to look up rovers in the HashSet than to navigate through the entire grid. 
	private HashSet<Rover> Rovers;
	
	private int sizeX;
	
	private int sizeY;
	
	// Good thing we're not removing rovers that might introduce a bit of complexity here
	private int nextRoverId = 0; 
	
	
	public Plateau(int xSize, int ySize) {
		grid = new Rover[xSize][ySize];
		Rovers = new HashSet<>();
		this.sizeX = xSize;
		this.sizeY = ySize;
	}
	
	
	public void printRovers()
	{

		// It's admittedly a bit ugly to have the prints in here since we're now 
		// tying it to a particular output... it would be better to just return a string value
		// but for this particular little App it should be fine.
		for(Rover rover : Rovers)
		{
			System.out.println(rover.toString());
		}
		
	}
	
	
	/**
	 * Print out the current state of the plateau
	 */
	public void printArea()
	{
		/* the 0,0 coordinate is the bottom left so it's using it's something like
		
		Y
		-------------------------------
		| 0,2 | 1,2 | 2,2 | 3,2 | 4,2 |
		-------------------------------
		| 0,1 | 1,1 | 2,1 | 3,1 | 4,1 |
		-------------------------------
		| 0,0 | 1,0 | 2,0 | 3,0 | 4,0 |
		------------------------------- X	
		
		*/ 
		
		int blockWidth = 5; // numChar for a block
		
		// ok so the initial block width is 5, when we hit 1000 that means
		// wqe've used up those spaces (including arrows and formatting spaces), so we need to now calculate a new width
		// that handle  the largest possible id / name we use.
		if(nextRoverId > 1000)
		{
			// a bit hacky but we turn the last roverid into a string, check the legnth and add 2
			blockWidth = ("" + nextRoverId).length() + 3;
		}
		
		// This reads a bit badly but we're just using this to multiple the number of - so we can create
		// the horizontal seperator lines.
		String seperatorLine = new String(new char[blockWidth * sizeX + sizeX + 1]).replace("\0", "-");
		
		// here we're just dynamically defining the number of blank spaces
		String blank = new String(new char[blockWidth]).replace("\0", " ") + "|";
		
		for(int y = sizeY -1; y >= 0; y--)
		{
			System.out.println(seperatorLine);
			System.out.print("|");
			for(int x = 0; x < sizeX; x++)
			{
				Rover rover = grid[x][y];
				
				if(rover == null)
				{
					System.out.print(blank);
				}
				else
				{
					String arrow = "X";
					
					// the IDE (Eclipse) console out doesn't like ← ↑ → ↡
					// so just using some standard characters to show direction
					switch(rover.getDirection())
					{
						case NORTH:
						{
							arrow = "^";
							break;
						}
						case EAST:
						{
							arrow = ">";
							break;
						}
						case SOUTH:
						{
							arrow = "\\/";
							break;
						}
						case WEST:
						{
							arrow = "<";
							break;
						}
					}
					
					String id = rover.getRoverID() + "";
					int frontPaddingWidth = (blockWidth - arrow.length() - id.length()) / 2;
					
					String frontPadding = new String(new char[frontPaddingWidth]).replace("\0", " ");
					String bakcPadding = new String(new char[blockWidth - frontPaddingWidth - arrow.length() - id.length() - 1]).replace("\0", " ");
					
					System.out.print(frontPadding + id + " " + arrow + bakcPadding + "|");
				}
			}
			System.out.println("");
			
		}
		
		System.out.println(seperatorLine);
		

		
	}
	
	public boolean isOccupied(int x, int y)
	{
		return grid[x][y] != null; 
	}
	
	
	public boolean isOffGrid(int x, int y)
	{
		// subtract one since we're using zero based indexes
		return x < 0 || y < 0 || sizeX - 1 < x || sizeY - 1 < y; 
	}
	
	public Rover GetRover(int id)
	{
		for(Rover rover : Rovers)
		{
			if(rover.getRoverID() == id)
				return rover;
		}
		
		return null;
	}
	
	public void roverMove(int xFrom, int yFrom, int xTo, int yTo) throws Exception
	{
		if(isOffGrid(xTo, yTo))
			throw new Exception("Destination is off of the Plateau");
		
		if(isOccupied(xTo, yTo))
			throw new Exception("Destination is already occupied");
		
		Rover rover = grid[xFrom][yFrom];
		
		if(rover == null)
			throw new Exception("No Rover found at " + xFrom + "/" + yFrom);
		
		
		grid[xFrom][yFrom] = null; // rover moved so it's old spot is empty
		grid[xTo][yTo] = rover; // this is it's new position		
	}
	
	
	private void addRover(Rover toAdd) throws Exception
	{
		
		if(isOffGrid(toAdd.getX(),toAdd.getY())) 
			throw new Exception("Rover Outside off of the Plateau");
		
		if(isOccupied(toAdd.getX(), toAdd.getY())) 
			throw new Exception("Rover Coordinate conflict");	
			
		grid[toAdd.getX()][toAdd.getY()] = toAdd;
		
		Rovers.add(toAdd);
		
		toAdd.setPlateau(this);
		
		System.out.println("Rover " + toAdd.getRoverID() + " has landed");
		
	}
	
	
	/**
	 * Create a new Rover at a given coordinate
	 * @param x
	 * X coordinate of the rover
	 * 
	 * @param y
	 * y coordinate of the rover
	 * 
	 * @param direction
	 * The direction the rover is pointed at
	 * 
	 * @return
	 * @throws Exception
	 * If the rover is in a invalid location
	 */
	public Rover addRover(int x, int y, EnumDirection direction) throws Exception
	{
		Rover toAdd = new Rover(nextRoverId, x, y, direction);
		addRover(toAdd);
		
		// since addRover can fail it's better to increment the totals only once we've 
		// successfully added a rover, else we'll be wasting IDs
		nextRoverId++; 
		return toAdd;
	}
	
	
	

}
