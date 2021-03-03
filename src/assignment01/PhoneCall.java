/*
 * 	Name: Peter Arsenault
 * 	File: PhoneCall.java
 * 	Description: Program that computes the length and cost
 * 				 of a phone call. 
 * 
 */
package assignment01;
import java.util.Scanner;

public class PhoneCall {

	public static void main(String[] args) {
		String welcomeMessage = "This program computes the length and cost of a phone call."; 
		System.out.println(welcomeMessage + "\n");
		
		// Define constants.
		final double COST_PER_MINUTE = 0.33;
		final int MINUTES_PER_HOUR = 60;
		
		// Begin accepting user input with Scanner object. 
		Scanner kbd = new Scanner(System.in);
		
		System.out.print("Enter starting hour: ");
		int startingHour = kbd.nextInt();
		System.out.print("Enter starting minute: ");
		int startingMinute = kbd.nextInt();
		
		System.out.print("Enter ending hour: ");
		int endingHour = kbd.nextInt();
		System.out.print("Enter ending minute: ");
		int endingMinute = kbd.nextInt();
		
		kbd.close();
		
		// Perform calculations on user input. 
		int calcHours = endingHour - startingHour;
		int calcMinutes = endingMinute - startingMinute;
		int lengthOfCallInMinutes = calcHours * MINUTES_PER_HOUR + calcMinutes;
		int totalHours = lengthOfCallInMinutes  / MINUTES_PER_HOUR;
		int remainingMinutes = lengthOfCallInMinutes % MINUTES_PER_HOUR;
				
		// Print length of call to console. 
		if (totalHours == 1) {
			System.out.println("\nThe length of the phone call is " + totalHours + " hour and " + remainingMinutes + " minutes");
		} else {
			System.out.println("\nThe length of the phone call is " + totalHours + " hours and " + remainingMinutes + " minutes");
		}
		
		// Calculate cost of call. 
		double costOfCall = lengthOfCallInMinutes * COST_PER_MINUTE;
		System.out.printf("The total cost of the phone call is: $%,.2f", costOfCall);
	}

}
