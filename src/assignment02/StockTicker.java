/*
 * 	Name: Peter Arsenault
 * 	File: StockTicker.java
 * 	Description: This program accepts user input about a company's stock. 
 * 				 If the user owns the stock, the program tells how much money gained/lost,
 * 				 and prints information about annual dividend.
 * 
 * 				 If the user doesn't own the stock, the program tells how many 
 * 				 dividends could be expected if they purchase the stock.
 */

package assignment02;

import java.util.Scanner;

public class StockTicker {

	public static void main(String[] args) {
		/* Initialize variables needed later */
		double stockValueAtPurchase = 0.0; 
		double currentMarketPrice = 0.0; 
		double stockValueAtCurrent = 0.0; 
		double gainOrLoss = 0.0; 
		double annualPayout = 0.0;
		int wantedShares = 0;
		
		/* This section accepts and validates general user input 
			regarding stock name, symbol, and whether you own shares. */
		Scanner kbd = new Scanner(System.in);
		
		System.out.print("Enter the name of the company: ");
		String company = kbd.nextLine().toUpperCase();
		
		System.out.print("Enter the stock ticker symbol: ");
		String stockTicker = kbd.nextLine().toUpperCase();
		while (stockTicker.length() < 2 || stockTicker.length() > 4) {
			System.out.println("Stock ticker symbol must be 2 to 4 characters in length. You entered "+ stockTicker +". Please re-enter...");
			System.out.print("Enter the stock ticker symbol: ");
			stockTicker = kbd.nextLine().toUpperCase();
		}
		
		System.out.print("Enter annual dividends paid out per share of " + company + " stock: ");
		double dividends = kbd.nextDouble();
		
		System.out.print("Enter the number of " + company + " shares you currently own: ");
		int shares = kbd.nextInt();
		while (shares < 0 || shares > 5000 ) {
			System.out.println("Shares Owned can be 0 - 5000, You entered " + shares + ". Please re-enter...");
			System.out.print("Enter the number of " + company + " shares you currently own: ");
			shares = kbd.nextInt();
		}
		
		/* The behavior of this program depends upon 
		 * whether you own shares or not. 
		 * This section gathers required information for both cases. */
		if (shares > 0) {
			System.out.print("Enter the price you paid per share for the " + company + " stock: ");
			double pricePerShare = kbd.nextDouble();	
			while (pricePerShare < 0) {
				System.out.println("The purchase price must be greater than 0.");
				System.out.print("Enter the price you paid per share for the " + company + " stock: ");
				pricePerShare = kbd.nextDouble();
			}

			System.out.print("Enter the current market price for the " + company + " stock: ");
			currentMarketPrice = kbd.nextDouble();
			while (currentMarketPrice < 0) {
				System.out.println("The current price must be greater than 0.");
				System.out.print("Enter the current market price for the " + company + " stock: ");
				currentMarketPrice = kbd.nextDouble();
			}

			stockValueAtPurchase = shares * pricePerShare;
			stockValueAtCurrent = shares * currentMarketPrice;
			gainOrLoss = stockValueAtCurrent - stockValueAtPurchase ;
			annualPayout = dividends * shares;
		} 
		else {
			System.out.print("Enter the number of " + company + " shares do you want to purchase: ");
			wantedShares = kbd.nextInt();
			while (wantedShares  < 0 || wantedShares > 5000 ) {
				System.out.println("Shares Owned can be 0 - 5000, You entered "+ wantedShares +". Please re-enter...");
				System.out.print("Enter the number of " + company + " shares you currently own: ");
				wantedShares = kbd.nextInt();
			}
			
			System.out.print("Enter the current market price for the " + company + " stock: ");
			currentMarketPrice = kbd.nextDouble();
			while (currentMarketPrice < 0) {
				System.out.println("The current price must be greater than 0.");
				System.out.print("Enter the current market price for the " + company + " stock: ");
				currentMarketPrice = kbd.nextDouble();
			}
			
			stockValueAtCurrent = wantedShares * currentMarketPrice;
			annualPayout = dividends * wantedShares;
		}
		double dividendYield = (dividends / currentMarketPrice)*100;

		kbd.close();
		
		/* This section prints output to console. 
		 * Output depends on whether you own stocks or not,
			and includes sub-logic for whether the company pays a dividend. */
		System.out.println("\nStock Value Output");
		for (int i=0;i<65;i++){
			System.out.print("-");
		}
		if (shares > 0) {
			System.out.printf("\nPurchased value of " + shares + " share(s) of "+ company +" ("+ stockTicker +") stock: $%,.2f", stockValueAtPurchase);
			System.out.printf("\nCurrent value of " + shares + " share(s) of "+ company +" ("+ stockTicker +") stock: $%,.2f", stockValueAtCurrent);
			
			if (gainOrLoss > 0.0) {
				System.out.printf("\nGain or loss of " + shares + " share(s) of "+ company +" ("+ stockTicker +") stock: $%,.2f", gainOrLoss);
			} else {
				System.out.printf("\nGain or loss of " + shares + " share(s) of "+ company +" ("+ stockTicker +") stock: -$%,.2f", Math.abs(gainOrLoss));
			}
			
			if (dividends > 0.0) {
				System.out.printf("\nAnnual dividend paid of per share for "+ company +" ("+ stockTicker +") stock: $%.02f", dividends);
				System.out.printf("\nAnnual dividend yield for "+ company +" ("+ stockTicker +") stock: %.03f%%", dividendYield);
				System.out.printf("\nAnnual dividend pay out of " + shares + " share(s) of " + company +" ("+ stockTicker +") stock: $%,.2f", annualPayout);
			} else {
				System.out.println("\nAnnual dividend yield for "+ company +" ("+ stockTicker +") stock: N\\A");
			}
		} else {
			System.out.printf("\nTo purchase " + wantedShares + " shares(s) of "+ company +" ("+ stockTicker +") stock: $%,.2f", stockValueAtCurrent);
			if (dividends > 0.0) {
				System.out.printf("\nAnnual dividend paid of per share for "+ company +" ("+ stockTicker +") stock: $%.02f", dividends);
				System.out.printf("\nAnnual dividend yield for "+ company +" ("+ stockTicker +") stock: %.03f%%", dividendYield);
				System.out.printf("\nAnnual dividend pay out of " + wantedShares + " share(s) of " + company +" ("+ stockTicker +") stock: $%,.2f", annualPayout);
			} else {
				System.out.println("\nAnnual dividend yield for "+ company +" ("+ stockTicker +") stock: N\\A");
			}
		}
	}
}
