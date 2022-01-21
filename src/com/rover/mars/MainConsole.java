package com.rover.mars;

import java.util.Scanner;

import org.junit.platform.commons.util.StringUtils;

import com.rover.mars.entities.Plateau;
import com.rover.mars.entities.Rover;
import com.rover.mars.enums.EnumDirection;
import com.rover.mars.enums.EnumRoverCommands;

public class MainConsole {

	public static void main(String[] args) {
		
		
		Scanner scanner = new Scanner(System.in);
		
		try
		{
			System.out.println("Enter x to exit\n\n");
			
			
			int plateaWidth;
			int plateaHeight;
			
			do
			{
				System.out.println("Enter Plateau size:");
				
				String[] inputs = scanner.nextLine().toUpperCase().split(" ");
				
				if(inputs.length < 2)
				{
					System.out.println("Too few values entered");
					continue;
				}
				
				if(inputs[0].toLowerCase().equals("x"))
				{
					return;
				}
				
				try
				{
					plateaWidth = Integer.parseInt(inputs[0]);
				}
				catch(Exception e)
				{
					System.out.println("Invalid Width value: " + inputs[0]);
					continue;
				}
				
				try
				{
					plateaHeight = Integer.parseInt(inputs[1]);
				}
				catch(Exception e)
				{
					System.out.println("Invalid Height value: " + inputs[1]);
					continue;
				}
				
				break;
				
			}
			while(true);
			
			Plateau plateau = new Plateau(plateaWidth, plateaHeight);
			plateau.printArea();
			
			do
			{
				int roverX;
				int roverY;
				EnumDirection roverDirection;
				
				try
				{
					
					System.out.print("Enter Rover Coordinate:");

					String[] inputs = scanner.nextLine().toUpperCase().split(" ");
					
					if(inputs.length < 3)
					{
						System.out.println("Too few values entered");
						continue;
					}

					if(inputs[0].toLowerCase().equals("x"))
					{
						return;
					}
					
					try
					{
						roverX = Integer.parseInt(inputs[0]);
					}
					catch(Exception e)
					{
						throw new Exception("Invalid Rover X value: " + inputs[0]);
					}
					
					try
					{
						roverY = Integer.parseInt(inputs[1]);
					}
					catch(Exception e)
					{
						throw new Exception("Invalid Rover Y value: " + inputs[1]);
					}
					
					
					roverDirection = EnumDirection.getDirection(inputs[2]);
					
					
					Rover rover = plateau.addRover(roverX, roverY, roverDirection);
					plateau.printArea();
					
					
					do
					{
						try
						{
							System.out.println("Enter Rover Commands:");
							
							String commands = scanner.nextLine().toUpperCase();
							
							if(commands.equals("x"))
								return;
							
							if(!EnumRoverCommands.isValid(commands))
							{
								System.out.println("Invalid directions: " + commands);
								continue;
							}
							
							rover.ExecutedCommands(commands);	
							plateau.printArea();
						
						}
						catch(Exception e)
						{
							System.out.println("Error:" + e.getMessage());
							continue;
						}
						
						//break;
					}
					while(true);
					
					
				}
				catch(Exception e)
				{
					System.out.println("Error: " + e.getMessage());
					continue;
				}
			}
			while(true);
			
		}
		finally
		{
			scanner.close();
			System.out.println("Done");
		}
		
		

	}

}
