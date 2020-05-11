package atm;

import java.util.Scanner;

/**
 * Program to give the least amount of bills from a withdrawal.
 *
 * @author Ali Zoubeidi
 */
public class MoneyChallenge {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String garbage;
        int count100 = 0;
        int count50 = 0;
        int count20 = 0;
        int count10 = 0;
        String answer100;
        String answer50;
        boolean want100 = false;
        boolean want50 = false;

        System.out.print("Enter (yes) if you want to receive $100 bills depending on your amount > ");
        answer100 = scan.next().trim();
        if (answer100.equalsIgnoreCase("yes")) {
            want100 = true;
        }

        System.out.print("Enter (yes) if you want to receive $50 bills depending on your amount > ");
        answer50 = scan.next().trim();
        if (answer50.equalsIgnoreCase("yes")) {
            want50 = true;
        }

        System.out.print("Enter the amount that you want to withdraw > ");
        while (!scan.hasNextInt()) {
            System.out.print("Enter a valid amount > ");
            garbage = scan.next();
        }
        int amount = scan.nextInt();

        while (amount < 10) {
            System.out.print("Valid entry please, we do not accept less than $10 bills > ");
            while (!scan.hasNextInt()) {
                System.out.print("Enter valid please > ");
                garbage = scan.next();
            }
            amount = scan.nextInt();
        }

        String strAmount = String.valueOf(amount);
        while (!strAmount.endsWith("0")) {
            System.out.print("The amount you want to withdraw is not possible with the bills available, enter a different amount > ");
            while (!scan.hasNextInt()) {
                System.out.print("Enter a valid amount > ");
                garbage = scan.next();
            }
            amount = scan.nextInt();
            strAmount = String.valueOf(amount);
        }

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
