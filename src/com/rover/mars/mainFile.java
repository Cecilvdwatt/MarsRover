package com.rover.mars;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.rover.mars.entities.Plateau;
import com.rover.mars.entities.Rover;
import com.rover.mars.enums.EnumDirection;
import com.rover.mars.enums.EnumRoverCommands;

public class mainFile {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(System.in);
		File inputFiles;
		 
		do
		{
		
			System.out.println("Please Enter File Location:");
			 
			String fileLoc = scanner.nextLine();
			//creating File instance to reference text file in Java
		    inputFiles = new File(fileLoc);
		    
		    if(inputFiles.exists())
		    {
		    	break;
		    }
		    
		    System.out.println("Could not find: " + fileLoc);
		    
		}
		while(true);
		scanner.close(); // closer intput scanner
		
		// now scan file		
		scanner = new Scanner(inputFiles);
		
		if(!scanner.hasNextLine())
		{
			System.out.println("No Plateau size line in file");
			return;
		}
		
		int plateaWidth;
		int plateaHeight;
		
		String[] inputs = scanner.nextLine().toUpperCase().split(" ");
		if(inputs.length < 2)
		{
			System.out.println("Too few rover values");
			return;
		}
		
		try
		{
			plateaWidth = Integer.parseInt(inputs[0]);
		}
		catch(Exception e)
		{
			System.out.println("Invalid Width value: " + inputs[0]);
			return;
		}
		
		try
		{
			plateaHeight = Integer.parseInt(inputs[1]);
		}
		catch(Exception e)
		{
			System.out.println("Invalid Height value: " + inputs[1]);
			return;
		}
		
		Plateau plateau = new Plateau(plateaWidth, plateaHeight);
		
		while(scanner.hasNextLine())
		{
		
			
			inputs = scanner.nextLine().toUpperCase().split(" "); // rover landing location
			
			int roverX, roverY;
			EnumDirection roverDirection;
			 
			try
		 	{
				roverX = Integer.parseInt(inputs[0]);
			}
			catch(Exception e)
			{
				System.out.println("Invalid Rover X value: " + inputs[0]);
				return;
			}
			
			try
			{
				roverY = Integer.parseInt(inputs[1]);
			}
			catch(Exception e)
			{
				System.out.println("Invalid Rover Y value: " + inputs[1]);
				return;
			}
				
			Rover rover;
			try 
			{
				roverDirection = EnumDirection.getDirection(inputs[2]);
				rover = plateau.addRover(roverX, roverY, roverDirection);
				plateau.printArea();
			} 
			catch (Exception e) 
			{
				System.out.println("Error:" + e.getMessage());
				return;
			}
			
			 String commands = scanner.nextLine().toUpperCase(); // rover commands
			 
			 if(!EnumRoverCommands.isValid(commands))
			 {
				 System.out.println("Invalid directions: " + commands);
				 return;
			 }
			 
			 try
			 {
				 rover.ExecutedCommands(commands);
			 }
			 catch(Exception e)
			 {
				 System.out.println("Error:" + e.getMessage());
				 return;
			 }
		}
		
		plateau.printRovers();	
	

	}

}
