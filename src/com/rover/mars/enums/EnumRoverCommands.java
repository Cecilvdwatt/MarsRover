package com.rover.mars.enums;

public enum EnumRoverCommands {
	
	TURN_LEFT('L'), 
	TURN_RIGHT('R'),
	MOVE('M');

	private char commandLetter;
	EnumRoverCommands(char command) {
		this.commandLetter = command;
	}
	
	public char getCommandLetter()
	{
		return commandLetter;
	}
	
	
	public static boolean isValid(char commandLetter)
	{
		
		for(EnumRoverCommands direction : EnumRoverCommands.values())
		{
			if(commandLetter == direction.getCommandLetter())
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isValid(String commandString)
	{
		for(char command : commandString.toUpperCase().toCharArray())
		{
			if(!isValid(command))
			{
				return false;
			}
		}
		
		return true;
	}

}
