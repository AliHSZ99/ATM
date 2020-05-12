package atm;

import java.util.Scanner;

/**
 * THIS IS THE CHALLENGE PROBLEM WHICH WE IMPLEMENTED IN OUR MAIN ATM SIMULATOR
 * CLASS! Program to give the least amount of bills from a withdrawal. It lets
 * the user chose if he wants to receive $100 bills and/or $50 bills depending
 * on the withdrawal amount.
 *
 * @author Ali Zoubeidi
 */
public class MoneyChallenge {

    /**
     * main method
     *
     * @param args
     */
    public static void main(String[] args) {

        // Instantiate Scanner class
        Scanner scan = new Scanner(System.in);

        // Initialize variables
        String garbage;
        int count100 = 0;
        int count50 = 0;
        int count20 = 0;
        int count10 = 0;
        String answer100;
        String answer50;
        boolean want100 = false;
        boolean want50 = false;

        // Output if user wants to receive $100 bills
        System.out.print("Enter (yes) if you want to receive $100 bills depending on your amount > ");
        answer100 = scan.next().trim();
        if (answer100.equalsIgnoreCase("yes")) {
            want100 = true;
        }

        // Output if user wants to receive $50 bills
        System.out.print("Enter (yes) if you want to receive $50 bills depending on your amount > ");
        answer50 = scan.next().trim();
        if (answer50.equalsIgnoreCase("yes")) {
            want50 = true;
        }

        // User enters amount that is wanted to withdraw with type-safe input.
        System.out.print("Enter the amount that you want to withdraw > ");
        while (!scan.hasNextInt()) {
            System.out.print("Enter a valid amount > ");
            garbage = scan.next();
        }
        int amount = scan.nextInt();

        // Checks if user enters a value that is less than 10 because the minimum bill available is $10.
        while (amount < 10) {
            System.out.print("Valid entry please, we do not accept less than $10 bills > ");
            while (!scan.hasNextInt()) {
                System.out.print("Enter valid please > ");     // Type-Safe input
                garbage = scan.next();
            }
            amount = scan.nextInt();
        }

        // Converting integer amount to a String to check if it ends with 0 or not. Type-Safe input is included.
        String strAmount = String.valueOf(amount);
        while (!strAmount.endsWith("0")) {
            System.out.print("The amount you want to withdraw is not possible with the bills available, enter a different amount > ");
            while (!scan.hasNextInt()) {
                System.out.print("Enter a valid amount > ");  // Type-Safe input
                garbage = scan.next();
            }
            amount = scan.nextInt();
            strAmount = String.valueOf(amount);
        }

        // If statement for a user that want to get $100 and $50 bills.
        if (want100 && want50) {
            while (amount >= 100) {
                amount = amount - 100;
                count100++;
            }
            while (amount >= 50 && amount < 100) {
                amount = amount - 50;
                count50++;
            }
            while (amount >= 20 && amount < 50) {
                amount = amount - 20;
                count20++;
            }
            while (amount >= 10 && amount < 20) {
                amount = amount - 10;
                count10++;
            }
        }

        // If statement for user who wants $100 bills but no $50 bills.
        if (!want50 && want100) {
            while (amount >= 100) {
                amount = amount - 100;
                count100++;
            }
            while (amount >= 20 && amount < 100) {
                amount = amount - 20;
                count20++;
            }
            while (amount >= 10 && amount < 20) {
                amount = amount - 10;
                count10++;
            }
        }

        // If statement for user that wants to receive $50 bills but no $100 bills.
        if (!want100 && want50) {
            while (amount >= 50) {
                amount = amount - 50;
                count50++;
            }

            while (amount >= 20 && amount < 50) {
                amount = amount - 20;
                count20++;
            }

            while (amount >= 10 && amount < 20) {
                amount = amount - 10;
                count10++;
            }
        }

        // If statement for user that does not want both $100 and $50 
        if (!want50 && !want100) {
            while (amount >= 20) {
                amount = amount - 20;
                count20++;
            }
            while (amount >= 10 && amount < 20) {
                amount = amount - 10;
                count10++;
            }
        }

        // Outputs what the user will receive. Conditional operator is used to output bill or bills
        // depending on how many bills the user will receive.
        System.out.println("\nYou will receive the following:");
        if (count100 >= 1) {
            System.out.println("  " + count100 + (count100 >= 2 ? " $100 bills" : " $100 bill"));
        }
        if (count50 >= 1) {
            System.out.println("  " + count50 + (count50 >= 2 ? " $50 bills" : " $50 bill"));
        }
        if (count20 >= 1) {
            System.out.println("  " + count20 + (count20 >= 2 ? " $20 bills" : " $20 bill"));
        }
        if (count10 >= 1) {
            System.out.println("  " + count10 + (count10 >= 2 ? " $10 bills" : " $10 bill"));
        }
    }
}
