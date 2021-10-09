package com.skilldistillery.app;

import java.util.Scanner;

public class MakeChange {

	static Scanner kb = new Scanner(System.in);

	public static void main(String[] args) {

		// Overview
		// In the cash register we will calculate the amount of change returned to a
		// customer based on the purchase price and the amount tendered. We will also
		// notify the attendant how many of each piece of currency ($20 ,$10 ,$5 ,$1,
		// .25c, .10c, .05c, .01c) is needed to make the change for the customer. Change
		// should be provided using the largest bill and coin denominations as possible.
		// Denominations that are not used should not be displayed.

		// Hint: Mod operator

		System.out.println("Welcome to DVS, the Digital Void Store!\n");

		double itemCost = 0.0;
		double moneyGiven = 0.0;
		String again;
		boolean repeat = true;

		// REPEATS Q'S AND STUFF
		while (repeat == true) {

			// User Story #1
			// The user is prompted asking for the price of the item.

			itemCost = askCost(itemCost);

			// User Story #2
			// The user is then prompted asking how much money was tendered by the customer.

			moneyGiven = askGiven(moneyGiven);

			// User Story #3
			// Display an appropriate message if the customer provided too little money or
			// the exact amount.

			do {
				if (moneyGiven < itemCost) {
					System.out.println(
							"I'm sorry that's not enough. Please enter an amount that's higher than or equal to "
									+ itemCost);
					moneyGiven = askGiven(moneyGiven);
				}
			} while (moneyGiven < itemCost);

//			MAYBE MAKE BOOLEAN ASKING FOR USER TO QUIT IN IF STMT

//			Can use nested while loop but changing while (moneyGiven > itemCost); to
//			while (moneyGiven < itemCost); fixed the exit when someone entered a lower number
//			a second time.
//			
//			while (moneyGiven < itemCost) {
//				System.out.println("I'm sorry that's not enough. Please enter an amount that's higher than or equal to "
//						+ itemCost);
//				moneyGiven = askGiven(moneyGiven);
//			} 


			// Gets enough money at this point
			// need to calculate change

			// User Story #4
			// If the amount tendered is more than the cost of the item, display the number
			// of bills and coins that should be given to the customer.

			int bill20;
			int bill10;
			int bill5;
			int bill1;
			int coin25;
			int coin10;
			int coin5;
			int coin1;
			double remainder = 0.0;
			String moneyReturned;

			if (moneyGiven == itemCost) {
				System.out.println("\nThank you for giving exact change.");
			} else if (moneyGiven > itemCost) {
				remainder = (moneyGiven - itemCost) % 20;

				if ((moneyGiven - itemCost) / 20 != 0 && ((moneyGiven - itemCost) / 20) >= 1) {
					bill20 = (int) ((moneyGiven - itemCost) / 20);
					System.out.println("Num $20: " + bill20);

					remainder = (moneyGiven - itemCost) % 20;
				}

				if ((moneyGiven - itemCost) / 10 != 0 && (remainder / 10) >= 1) {
					bill10 = (int) (remainder / 10);
					System.out.println("Num $10: " + bill10);

					remainder = (remainder % 10);
				}

				if ((moneyGiven - itemCost) / 5 != 0 && (remainder / 5) >= 1) {
					bill5 = (int) (remainder / 5);
					System.out.println("Num $5: " + bill5);

					remainder = (remainder % 5);
				}

				if ((moneyGiven - itemCost) / 1 != 0 && (remainder / 1) >= 1) {
					bill1 = (int) (remainder / 1);
					System.out.println("Num $1: " + bill1);

					remainder = (remainder) % 1;
				}

				if ((moneyGiven - itemCost) / 1 != 0 && (remainder / 0.25) >= 1) {
					coin25 = (int) (remainder / 0.25);
					System.out.println("Num 0.25: " + coin25);

					remainder = (remainder) % 0.25;
				}

				if ((moneyGiven - itemCost) / 1 != 0 && (remainder / 0.10) >= 1) {
					coin10 = (int) (remainder / 0.10);
					System.out.println("Num 0.10: " + coin10);

					remainder = (remainder) % 0.10;
				}

				if ((moneyGiven - itemCost) / 1 != 0 && (remainder / 0.05) >= 1) {
					coin5 = (int) (remainder / 0.05);
					System.out.println("Num 0.05: " + coin5);

					remainder = (remainder) % 0.05;
				}

				double coin1Round;
				
				// (remainder / 0.01) > 1) putting 1 less penny
//				if ((moneyGiven - itemCost) / 1 != 0 && (remainder / 0.01) > 1) {
//					coin1 = (int) (remainder / 0.01);
//
//					System.out.println("Remainder pre add: " + remainder);
//					coin1Round = round ((remainder / 0.01), 2);
//					// TEST IF WILL CAPTURE MISSED PENNY
//					if (remainder > 0.00001) {
//						coin1 += 1;
//					}
//					System.out.println("Num 0.01: " + coin1);
//					System.out.println("Coin1Round: " + coin1Round);
//
//					remainder = (remainder) % 0.01;
//				}
				
				if ((moneyGiven - itemCost) / 1 != 0 && (remainder / 0.01) > 1) {
					
					// COINS PRE ROUNDING MAYBE DELETE LATER
					coin1 = (int) (remainder / 0.01);
					System.out.println("Remainder pre round: " + remainder);
					
					// ROUNDING TEST SECTION
					
					// Cast as int and * 1000 to get last 3 decimals of original remainder
					int remainderRound;
					remainderRound = (int) (remainder * 1000);
					System.out.println("remRoundInt * 1000: " + remainderRound);

					// Divides by 1000 to turn back in to decimal form & rounds to nearest hundredth.
//					remainder = (double) remainderRound / 1000.0;
					remainder =  remainderRound / 1000.0;
					System.out.println("remainder = remRound / 1000: " + remainder);
					remainder = Math.round(remainder *100.0)/100.0;
					System.out.println("remainder post Math.round: " + remainder);
					
					// 
					coin1Round = (int) (remainder / 0.01);
					System.out.println("Num 0.01: " + coin1);
					System.out.println("Post round 0.01 coins: " + coin1Round);
					
					// NOT WORKING remainder = Math.round(remainder, 2);
					//coin1Round = (int) (remainder / 0.01);
					


					remainder = (remainder) % 0.01;
				}
				

			}

			System.out.println("\n post round Reminder: " + remainder);
			System.out.println("\nmoneyGiven " + moneyGiven);
			System.out.println("itemCost: " + itemCost);

			System.out.print("Would you like to purchase something else? (Y or N) ");
			again = kb.nextLine();

			switch (again) {
			case "N":
			case "n":
				repeat = false;
				break;
			default:
				System.out.println("\nThat wasn't an option and it looks like you have something else to purchase!\n");
			}

		}
		while (repeat == true);

		System.out.println("\nThank you for shopping at DVS! Come again soon!");
		System.out.println("Program exited.");
		kb.close();		// Close Scanner.
	}

	public static double askCost(double itemCost) {
		System.out.print("How much does the item you wish to purchase cost? ");
		itemCost = kb.nextDouble();
		kb.nextLine(); // Flush return.

		return itemCost;
	}

	public static double askGiven(double moneyGiven) {
		System.out.print("How much are you going to give so I can calculate the change? ");
		moneyGiven = kb.nextDouble();
		kb.nextLine(); // Flush return.

		return moneyGiven;
	}

}
