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
		// Denominations that are not used are not displayed.

		System.out.println("Welcome to DVS, the Digital Void Store!\n");

		double itemCost = 0.0;
		double moneyGiven = 0.0;
		double overPaid = 0.0;
		String again;
		boolean repeat = true;

		// Repeats while user wants to "buy" more stuff.
		while (repeat == true) {

			String moneyReturned = "You are receiving back:";

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
							"\nI'm sorry that's not enough. Please enter an amount that's higher than or equal to "
									+ itemCost + ".");
					moneyGiven = askGiven(moneyGiven);
				}
			} while (moneyGiven < itemCost);

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

			if (moneyGiven == itemCost) {
				System.out.println("\nThank you for giving exact change.");
				moneyReturned += " $0.00";
			} else if (moneyGiven > itemCost) {

				remainder = (moneyGiven - itemCost) % 20;

				// Assign actual over paid value
				overPaid = moneyGiven - itemCost;

				// Rounds remainder to 2 decimals.
				remainder = remainderRound(remainder);

				// When if statement meets condition, calculate bills owed per denomination.
				if (overPaid / 20 >= 1) {

					// Calculate number of $20 bills to return.
					bill20 = (int) ((moneyGiven - itemCost) / 20);

					// Captures punctuation
					if (bill20 > 0) {
						moneyReturned += (" " + bill20 + " twenty dollar bill,");
					}
				}

				if (remainder / 10 >= 1) {
					bill10 = (int) (remainder / 10);

					// Calculate number of $10 bills to return.
					remainder = (remainder % 10);

					// Captures punctuation
					if (bill10 > 0) {
						moneyReturned += (" " + bill10 + " ten dollar bill,");
					}
				}

				if (remainder / 5 >= 1) {

					// Calculate number of $5 bills to return.
					bill5 = (int) (remainder / 5);

					remainder = (remainder % 5);

					// Captures punctuation
					if (bill5 > 0) {
						moneyReturned += (" " + bill5 + " five dollar bill,");
					}
				}

				if (remainder / 1 >= 1) {

					// Calculate number of $1 bills to return.
					bill1 = (int) (remainder / 1);

					remainder = (remainder) % 1;

					// Rounds remainder to 2 decimals.
					remainder = remainderRound(remainder);

					// Captures punctuation
					if (bill1 > 0) {
						moneyReturned += (" " + bill1 + " one dollar bill,");
					}
				}

				if (remainder / 0.25 >= 1) {

					// Calculate number of quarters to return.
					coin25 = (int) (remainder / 0.25);

					remainder = (remainder) % 0.25;

					// Rounds remainder to 2 decimals.
					remainder = remainderRound(remainder);

					// Captures punctuation
					if (coin25 > 0) {
						moneyReturned += (" " + coin25 + " quarter,");
					}
				}

				if (remainder / 0.10 >= 1) {

					// Calculate number of dimes to return.
					coin10 = (int) (remainder / 0.10);

					remainder = (remainder) % 0.10;

					// Rounds remainder to 2 decimals.
					remainder = remainderRound(remainder);

					// Captures punctuation
					if (coin10 > 0) {
						moneyReturned += (" " + coin10 + " dime,");
					}
				}

				if (remainder / 0.05 >= 1) {

					// Calculate number of nickels to return.
					coin5 = (int) (remainder / 0.05);

					remainder = (remainder) % 0.05;

					// Rounds remainder to 2 decimals.
					remainder = remainderRound(remainder);

					// Captures punctuation
					if (coin5 > 0) {
						moneyReturned += (" " + coin5 + " nickel,");
					}
				}

				if (remainder / 0.01 >= 1) {

					// Calculate number of pennies to return.
					coin1 = (int) (remainder / 0.01);

					// Captures punctuation
					if (coin1 > 1) {
						moneyReturned += (" " + coin1 + " pennies.");
					} else {
						moneyReturned += (" " + coin1 + " penny.");
					}
				}
			}

			System.out.println("\n" + moneyReturned);

			System.out.print("\nWould you like to purchase something else? (Y or N) ");
			again = kb.nextLine();

			switch (again) {
			case "N":
			case "n":
				repeat = false;
				break;
			case "Y":
			case "y":
				System.out.println();
				break;
			default:
				System.out.println("\nThat wasn't an option and it looks like you have something else to purchase!\n");
			}

		}
		while (repeat == true)
			;

		System.out.println("\nThank you for shopping at DVS! Come again soon!");
		kb.close(); // Close Scanner.
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

	public static double remainderRound(double remainder) {
		int remainderRound;

		// Cast as int and * 1000 to get last 3 decimals of original remainder
		remainderRound = (int) (remainder * 1000);

		// Divides by 1000 to turn back in to decimal form 0.xxx & rounds to nearest
		// hundredth.
		remainder = remainderRound / 1000.0;
		remainder = Math.round(remainder * 100.0) / 100.0;

		return remainder;
	}

}
