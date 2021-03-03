/* 
 * Author: Peter Arsenault
 * File: SimpleYahtzeeArsenault.java
 * Description: 
 * This program is a type of Yahtzee game with the following features: 
 * 		- Lets a user roll 5 dice, 
 *  	- decide if they want to change any of the individual dice,
 *   	- re-roll the dice up to two times (if desired),
 *   	- score the final roll based on the scoring rules of Yahtzee, 
		- displays the game score and roll type to user,
 *   	- Saves the final score of the game,
 *   	- Lets user play games continuously until they want to stop.
 *   	Then: 
 *   	- Prints the number of games played
 *   	- Prints the results of all games
 *   	- Prints the highest scored game (the first instance of the highest score)
 * 
 */

package assignment03;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;

public class SimpleYahtzeeArsenault {

	public static void main(String[] args) {
		
		int[] dice = new int[5];
		runProgram(dice);

		// debugProgram(firstDiceRoll(dice));
		// generateRandomScoresDebug(100);
		// testEveryScore();
	}
	
	public static void runProgram(int[] dice) {
		/* This method drives the program logic. 
		 * For example, allowing the program to be run multiple times, 
		 * and letting the dice be re-rolled and scored based on changes. */
		Scanner kbd = new Scanner(System.in);
		String playAgain = "1"; 
		
		ArrayList<String> scoreResults = new ArrayList<String>();
		
		/* Generate initial 5 random dice */
		firstDiceRoll(dice);
		
		while (playAgain.equals("1")) {
			displayDice(dice, "FIRST");
			System.out.print("\n\nDo you want to re-roll some of the dice? (Yy/Nn): ");
			char rerollChoice = kbd.next().charAt(0);
			rerollChoice = Character.toUpperCase(rerollChoice);
			while (!(rerollChoice == 'N' || rerollChoice == 'Y' )) {
				System.out.println("Invalid choice. Please re-enter choice as Y or N.");
				System.out.print("Do you want to re-roll some of the dice? (Yy/Nn): ");
				rerollChoice = kbd.next().charAt(0);
				rerollChoice = Character.toUpperCase(rerollChoice);
			}
			
			if (rerollChoice == 'N' | rerollChoice == 'n') {
				horizontalBorder();
				
				/* Print results */
				System.out.print("\nResults: ");
				for (int i = 0; i < 5; i++) {
					System.out.print(dice[i] + " ");
				}
				
				System.out.println(finalScore(dice));
				scoreResults.add(finalScore(dice));
				horizontalBorder();
			}
			if (rerollChoice == 'Y' | rerollChoice == 'y') {
				reRollDice(dice, kbd);
				displayDice(dice, "SECOND");
				
				System.out.print("\n\nDo you want to re-roll some of the dice? (Yy/Nn): ");
				char rerollChoice2 = kbd.next().charAt(0);
				rerollChoice2 = Character.toUpperCase(rerollChoice2);
				while (!(rerollChoice2 == 'N' || rerollChoice2 == 'Y')) {
					System.out.println("Invalid choice. Please re-enter choice as Y or N.");
					System.out.print("Do you want to re-roll some of the dice? (Yy/Nn): ");
					rerollChoice2 = kbd.next().charAt(0);
					rerollChoice2 = Character.toUpperCase(rerollChoice2);
				}
				
				if (rerollChoice2 == 'N') {
					System.out.print("\nResults: ");
					for (int i = 0; i < 5; i++) {
						System.out.print(dice[i] + " ");
					}

					System.out.println(finalScore(dice));
					scoreResults.add(finalScore(dice));
					horizontalBorder();
				}
				if (rerollChoice2 == 'Y') {
					reRollDice(dice, kbd);
					displayDice(dice, "THIRD");
					System.out.print("\nResults: ");
					for (int i = 0; i < 5; i++) {
						System.out.print(dice[i] + " ");
					}
					
					System.out.println(finalScore(dice));
					scoreResults.add(finalScore(dice));
					horizontalBorder();
				}
			}
			System.out.print("\nPlay again (1 = Yes, 0 = No): ");
			playAgain = kbd.next();
			while (!(playAgain.equals("0") || playAgain.equals("1"))) {
				System.out.println("Invalid choice.");
				System.out.print("Play again (1 = Yes, 0 = No): ");
				playAgain = kbd.next();
			}

			if (playAgain.equals("0")) {
				System.out.println("\n !! GAME OVER !! \n");
				if (scoreResults.size()> 1) {
					System.out.println("You played "+ scoreResults.size() + " games! ");
				} else { 
					System.out.println("You played "+ scoreResults.size() + " game. ");
				}
				
				System.out.println("Your score results: ");
				for (String i : scoreResults) {
					System.out.println("\t" + i);
				}
				
				ArrayList<Integer> scores = new ArrayList<Integer>();
				
				for (String i : scoreResults) {
					scores.add(Integer.parseInt(i.substring(i.length() - 4,i.length() - 2)));
					
				}
				
				int bestScore = Collections.max(scores); 
				int bestScoreIndex = scores.indexOf(bestScore);
				System.out.println("Your best game was Game #"+ (bestScoreIndex+1) + ": ");
				System.out.println("\t"+scoreResults.get(bestScoreIndex));
				
			}
		}
		kbd.close();
	}
	
	public static void horizontalBorder() {
		for (int a = 0; a < 54; a++) {
			System.out.print("=");
		}
	}

	public static int[] firstDiceRoll(int[] dice) {
		/* This method provides the 5 randomly generated starting numbers. */
		Random rand = new Random();
		for (int i = 0; i < dice.length; i++) {
			dice[i] = rand.nextInt(6) + 1;
		}
		return dice;
	}
	
	public static void displayDice(int[] dice, String rollNumber) {
		/* Provide a fresh roll if it's the first roll */
		if (rollNumber.equals("FIRST")) {
			firstDiceRoll(dice);
		}
		/* Print output of roll */
		horizontalBorder();
		System.out.println("\n		"+ rollNumber +" ROLL 		");
		System.out.println("		Dice 1, Dice 2, Dice 3, Dice 4, Dice 5");
		System.out.print("Dice Values: 	");
		for (int i = 0; i < dice.length; i++) {
			System.out.print(dice[i] + "\t");
		}
		System.out.println();
		horizontalBorder();
	}
	
	public static int[] reRollDice(int[] dice, Scanner kbd) {
		/* This method lets the user choose which die he or she wants to re-roll */
		char[] reRollChoiceArray = new char[5];
		Random rand = new Random();
		horizontalBorder();
		System.out.println();
		for (int i = 0; i < reRollChoiceArray.length; i++) {
			System.out.print("Re-Roll Dice "+ (i+1) +" (Yy/Nn): ");
			reRollChoiceArray[i] = kbd.next().charAt(0);
			reRollChoiceArray[i] = Character.toUpperCase(reRollChoiceArray[i]);
			
			while(!(reRollChoiceArray[i] == 'N' || reRollChoiceArray[i] == 'Y')) {
				System.out.print("Invalid choice. Please re-enter choice as Y or N.\n");
				System.out.print("Re-Roll Dice "+ (i+1) +" (Yy/Nn): ");
				reRollChoiceArray[i] = kbd.next().charAt(0);
				reRollChoiceArray[i] = Character.toUpperCase(reRollChoiceArray[i]);
			}
		}
		System.out.println();

		for (int i=0; i<reRollChoiceArray.length; i++) {
			if(reRollChoiceArray[i] == 'y' || reRollChoiceArray[i] == 'Y') {
				dice[i] = rand.nextInt(6) + 1;
			}
		}
		
		return dice;
	}
	
	public static String rolledResults(int[] dice) {
		/* This method calculates the score based on the rules of Yahtzee. */
		Arrays.sort(dice);
		String returnMessage = "";

		/* Calculate set of unique numbers for Straight */
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < dice.length; i++) {
			set.add(dice[i]);
		}
		Object[] hashArray = set.toArray();
		
		/* The dice may be a straight if there are more than 3 unique numbers in the set */
		if (hashArray.length > 3) {
			/* If there are 5 unique numbers in the rolled set,
			 * It's either a large straight or a small straight. */
			if (hashArray.length == 5) {
				/* If all 5 numbers are sequential, it's a large straight. */
				if (	((int) hashArray[0] + 1 == (int) hashArray[1]) && 
						((int) hashArray[1] + 1 == (int) hashArray[2]) && 
						((int) hashArray[2] + 1 == (int) hashArray[3]) && 
						((int) hashArray[3] + 1 == (int) hashArray[4])) {

					returnMessage = "Large Straight";
					
				} 
				else {
					/* If not, it could be a small straight.
					 * I would call this roll a "Stupid Small Straight" 
					 * Because it was very hard to catch this bug in my program! */
					if ( 	
							(
								((int) hashArray[0] + 1 == (int) hashArray[1]) && 
								((int) hashArray[1] + 1 == (int) hashArray[2]) && 
								((int) hashArray[2] + 1 == (int) hashArray[3]) &&
								((int) hashArray[3] + 1 != (int) hashArray[4])
							) 
								||
							(
								((int) hashArray[0] + 1 != (int) hashArray[1]) &&
								((int) hashArray[1] + 1 == (int) hashArray[2]) && 
								((int) hashArray[2] + 1 == (int) hashArray[3]) && 
								((int) hashArray[3] + 1 == (int) hashArray[4]) 
							)
							){
						
						returnMessage = "Small Straight";
					}
					/* or, simply a chance */
					else {
						returnMessage = "Chance";
					}
				}
			}
			/* If there are four unique numbers rolled, 
			 * they are a small straight if they are all sequential.
			 * Otherwise, nothing special about the 4 unique numbers.  */
			if (hashArray.length == 4) {
				if ( ((int) hashArray[0] + 1 == (int) hashArray[1]) &&
					 ((int) hashArray[1] + 1 == (int) hashArray[2]) && 
					 ((int) hashArray[2] + 1 == (int) hashArray[3]) ) {
					
							returnMessage = "Small Straight";
				} 
				else {
					returnMessage = "Chance";
				}
			}
		} 
		/* If there are less than three unique numbers rolled, 
		 * we can assign a default score to Chance, 
		 * and then begin analyzing points that you get
		 * for having non-unique rolls (i.e., multiples of digits) */
		else {
			returnMessage = "Chance";
		}

		/* This section calculates if 3-of-a-kind, 
		 * or if the 3-of-a-kind is actually a 4-of-a-kind,
		 * 5-of-a-kind, or a full house.
		 */

		/* Calculate if potential 3-of-a kind */
		if (dice[0] == dice[2] || dice[1] == dice[3] || dice[2] == dice[4]) {

			/* Calculate a special type of full house, called Yatzhee or 5-of-a-kind */
			if (dice[0] == dice[4]) {
				returnMessage = "Yahtzee";
			}

			/* This 3-of-a-kind is NOT a Yatzhee */
			else {
				
				/* Calculate if 4 of a kind */
				if (dice[0] == dice[3] || dice[1] == dice[4]) {
					returnMessage = "Four of a Kind";
				}

				/* This 3-of-a-kind is NOT a 4-of-a-kind */
				else {
					/* Calculate whether 3-of-a-kind is actually a full house */
					if (dice[0] == dice[2]) {
						if (dice[3] == dice[4]) {
							returnMessage = "Full House";
						}
						else {
							returnMessage = "Three of a Kind";
						}
					}
					if (dice[2] == dice[4]) {
						if (dice[0] == dice[1]) {
							returnMessage = "Full House";
						}
						else {
							returnMessage = "Three of a Kind";
						}
					}
					if (dice[1] == dice[3]) {
						returnMessage = "Three of a Kind";
					}
				}
			}
		}
		return returnMessage;
	}

	public static int scoreRoll(int[] dice, String scoreString) {
		int totalOfAllDiceScore = 0;
		for (int i = 0; i < dice.length; i++) {
			totalOfAllDiceScore = totalOfAllDiceScore + dice[i];
		}
		
		int[] score = {50, 25, totalOfAllDiceScore, totalOfAllDiceScore, 30, 40, totalOfAllDiceScore};
		switch(scoreString) {
		case "Yahtzee":
			return score[0];
		case "Full House":
			return score[1];
		case "Three of a Kind":
			return score[2];
		case "Four of a Kind":
			return score[3];
		case "Small Straight":
			return score[4];
		case "Large Straight":
			return score[5];
		case "Chance":
			return score[6];
		default:
			return 0;
		}
	}
	
	public static String finalScore(int[] dice) {
		int numericScore = scoreRoll(dice, rolledResults(dice));
		String stringScore = String.valueOf(scoreRoll(dice, rolledResults(dice)));
		/* There is one case where the score could be less than 10. 
		 * If so, you have to format the output to be '09' rather than '9' 
		 * due to the way the program parses the score. */
		if ( numericScore < 10) {
			stringScore = String.format("%02d" , numericScore);
			
		}
		return rolledResults(dice) + " ("+stringScore +") ";
	}
	
	public static void debugProgram(int[] dice) {
		Arrays.sort(dice);
		System.out.print("Array: ");
		for (int i = 0; i < 5; i++) {
			System.out.print(dice[i] + " ");
		}
		System.out.println(finalScore(dice));
	}
	
	public static void generateRandomScoresDebug(int numberOfScores) {
		for (int i = 0; i < numberOfScores; i++) {
			int[] dice = new int[5];
			firstDiceRoll(dice);
			debugProgram(dice);
		}
	}

	public static void testEveryScore() {
		int[] yahtzee = { 4, 4, 4, 4, 4 };
		debugProgram(yahtzee);

		int[] fourOne = { 4, 4, 4, 4, 1 };
		debugProgram(fourOne);
		int[] fourTwo = { 1, 4, 4, 4, 4 };
		debugProgram(fourTwo);

		int[] threeOne = { 4, 4, 4, 1, 2 };
		debugProgram(threeOne);
		int[] threeTwo = { 1, 4, 4, 4, 2 };
		debugProgram(threeTwo);
		int[] threeThree = { 1, 5, 4, 4, 4 };
		debugProgram(threeThree);

		int[] fullHouseOne = { 2, 2, 4, 4, 4 };
		debugProgram(fullHouseOne);
		int[] fullHouseTwo = { 4, 4, 4, 5, 5 };
		debugProgram(fullHouseTwo);

		int[] smallStraightOne = { 1, 2, 3, 4, 4 };
		debugProgram(smallStraightOne);
		int[] smallStraightTwo = { 2, 2, 3, 4, 5 };
		debugProgram(smallStraightTwo);
		int[] smallStraightThree = { 1, 3, 4, 5, 6 };
		debugProgram(smallStraightThree);

		int[] largeStraightOne = { 1, 2, 3, 4, 5 };
		debugProgram(largeStraightOne);
		int[] largeStraightTwo = { 2, 3, 4, 5, 6 };
		debugProgram(largeStraightTwo);

		int[] chanceOne = { 4, 4, 2, 1, 2 };
		debugProgram(chanceOne);
		int[] chanceTwo = { 1, 2, 3, 3, 2 };
		debugProgram(chanceTwo);
		int[] chanceThree = { 1, 5, 2, 4, 4 };
		debugProgram(chanceThree);
		int[] chanceFour = { 1, 2, 3, 5, 6 };
		debugProgram(chanceFour);
		int[] chanceFive = { 1, 1, 2, 2, 3 };
		debugProgram(chanceFive);
	}

}
