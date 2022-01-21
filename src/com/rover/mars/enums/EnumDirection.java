package com.rover.mars.enums;

public enum EnumDirection {
	NORTH("N"),
	EAST("E"),
	SOUTH("S"),
	WEST("W");

	String letter;
	
	EnumDirection(String letter) {
		this.letter = letter;
	}
	
	public String getLetter()
	{
		return letter;
	}
	
	public static EnumDirection getDirection(String direction) throws Exception
	{
		switch(direction.toLowerCase())
		{
			case "n":
			{
				return EnumDirection.NORTH;
			}
			case "e":
			{
				return EnumDirection.EAST;
			}
			case "s":
			{
				return EnumDirection.SOUTH;
			}
			case "W":
			{
				return EnumDirection.WEST;
			}
		}
		
		
		throw new Exception("Invalid direction " + direction);
	}

}
