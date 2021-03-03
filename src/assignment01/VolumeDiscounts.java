/*
 * 	Name: Peter Arsenault
 * 	File: VolumeDiscounts.java
 * 	Description: Program that computes the total cost of a purchase based 
 * 				 on tiers of pricing, also known as getting volume discounts 
 * 				 on per-item pricing when you order a high enough quantity. 
 * 
 */
package assignment01;

import java.util.Scanner;
public class VolumeDiscounts {

	public static void main(String[] args) {
		String welcomeMessage = "This program prints the total cost of a purchase.";
		System.out.println(welcomeMessage);
		
		// Define price-tier constants. 
		final double PRICE_FIRST = 5.15;
		final double PRICE_SECOND = 4.75;
		final double PRICE_THIRD = 3.75; 
		
		// Define ceiling price constants between tiers. 
		final double MAX_FIRST_PRICE = 15 * PRICE_FIRST;
		final double MAX_SECOND_PRICE = 10 * PRICE_SECOND;
		
		// Scanner object to accept user input. 
		Scanner kbd = new Scanner(System.in);
		System.out.print("Enter the number of items purchased: ");
		int itemsPurchased = kbd.nextInt();
		kbd.close();
		
		// Calculate a first tier price:
		if (itemsPurchased <= 15 ) {
			double firstTierPrice = itemsPurchased * PRICE_FIRST;
			System.out.printf("\nThe total purchase price is: $%,.2f", firstTierPrice);
		} 
		// Calculate a second tier price:
		if (itemsPurchased > 15 && itemsPurchased <= 25) {
			int amountOver15 = itemsPurchased % 15; 
			double secondTierPrice = MAX_FIRST_PRICE + (amountOver15 * PRICE_SECOND);
			System.out.printf("\nThe total purchase price is: $%,.2f", secondTierPrice);
		} 
		// Calculate a third tier price:
		if (itemsPurchased > 25) {
			int amountOver25 = itemsPurchased - 25; 
			double thirdTierPrice = MAX_FIRST_PRICE + MAX_SECOND_PRICE + (amountOver25 * PRICE_THIRD);
			System.out.printf("\nThe total purchase price is: $%,.2f", thirdTierPrice );
		}
		
	}

}
