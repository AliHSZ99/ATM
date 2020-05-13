package atm;

import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

/**
 * This is a program about an ATM machine that contains 3 accounts. It is an ATM
 * machine simulator.We created an Account class with all the attributes that
 * characterizes a bank account, with all the methods needed, and other service
 * classes such as MoneyConversion class and AdminAccount class. We provided
 * operations such as deposit, withdraw, transfer between accounts, print
 * balance and more with nice outputs for better user experience.
 *
 * @author Ali Zoubeidi, Vincent Benesen and Jeremie Guerchon
 */
public class ATM {

    /**
     * The main method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        // Instantiating objects from the Account class and AdminAccount class.
        Accounts vincent = new Accounts("Vincent", "Benesen", 5000);
        Accounts ali = new Accounts("Ali", "Zoubeidi", 8000);
        Accounts jeremie = new Accounts("Jeremie", "Guerchon", 4000);
        AdminAccount admin = new AdminAccount("admin", "password", 9999);

        // Instantiating File class and Scanner class.
        // Scanner reads from files.
        File ft = new File("users.txt");
        Scanner list = new Scanner(ft);
        File file = new File("machine.txt");
        Scanner hardware = new Scanner(file);

        //Here are the sets of accounts
        String userOne = list.nextLine();
        String userTwo = list.nextLine();
        String userThree = list.nextLine();

        //Here are the sets of hardware info
        String h1 = hardware.nextLine();
        String h2 = hardware.nextLine();
        String h3 = hardware.nextLine();
        String h4 = hardware.nextLine();

        // Initializing variables
        String newPass = " ";
        String garbage;
        String answer100;
        String answer50;
        String password = null;
        int count100 = 0;
        int count50 = 0;
        int count20 = 0;
        int count10 = 0;
        int amount;
        boolean want100 = false;
        boolean want50 = false;

        // Instantiating DecimalFormat class for money formatting.
        // Instantiating Scanner class for user input.
        DecimalFormat currency = new DecimalFormat("0.00$");
        Scanner sc = new Scanner(System.in);

        // Outputs nice welcome message for the simulator.
        System.out.println("\n*******************************************");
        System.out.println("*------->>>WELCOME TO J.A.V BANK<<<-------*");
        System.out.println("*******************************************");
        System.out.print("\nPlease enter your username > ");
        String username = sc.next();

        // Checking for login info for Vincent's account.
        while (!username.equals(vincent.getUserName()) && !username.equals(admin.getUser()) && !username.equals(ali.getUserName()) && !username.equals(jeremie.getUserName())) {
            System.out.print("INVALID USERNAME, Please enter your username > ");
            username = sc.next();
        }

        if (username.equals(vincent.getUserName())) {
            System.out.print("Please enter your password > ");
            password = sc.next();
            while (!password.equals(vincent.getPassWord())) {
                System.out.print("WRONG PASSWORD! Please enter your password again >");
                password = sc.next();
            }
            if (username.equals(vincent.getUserName()) && password.equals(vincent.getPassWord())) {
                System.out.println("\n" + vincent);
                String response = "yes";
                // This is where login is finished and prints out user choices

                while (response.equalsIgnoreCase("Yes")) {

                    System.out.print("Please enter your choice > ");    // Asks for user choice with
                    while (!sc.hasNextInt()) {                          // Type-Safe input.
                        sc.next();
                        System.out.println("\n" + vincent);
                        System.out.print("Please enter your choice > ");
                    }
                    int number = sc.nextInt();

                    // Switch case depending on user choice 
                    switch (number) {
                        // case 1 is for withdrawals and here is where our challenge (MoneyChallenge.java) is used.
                        // Note: Most info of this block/challenge can be found in MoneyChallenge.java!
                        case 1:
                            System.out.print("\nEnter (yes) if you want to receive $100 bills depending on your amount > ");
                            answer100 = sc.next().trim();
                            if (answer100.equalsIgnoreCase("yes")) {
                                want100 = true;
                            }

                            System.out.print("Enter (yes) if you want to receive $50 bills depending on your amount > ");
                            answer50 = sc.next().trim();
                            if (answer50.equalsIgnoreCase("yes")) {
                                want50 = true;
                            }

                            System.out.print("\nPlease enter the amount of money you want to withdraw > ");
                            while (!sc.hasNextInt()) {
                                System.out.print("Enter a valid amount > ");
                                garbage = sc.next();
                            }
                            int amounts = sc.nextInt();

                            // Here we get the balance of Vincent when subtracting from the amount wanted to withdraw.
                            int balance = vincent.getMoney() - amounts;
                            int withdraw = amounts;
                            vincent.setMoney(balance);  // This line is important for keeping up with user's balance                           

                            // Here we check if the amount wanted is higher than the user's balnce.
                            while (amounts > vincent.getMoney()) {  // HUGE ERRROR NEED TO FIX ASAP!!!!!!!!!
                                System.out.println("Sorry! insufficient balance");
                                System.out.print("Please Enter a new amount > ");
                                while (!sc.hasNextInt()) {
                                    System.out.print("Your entry is invalid, try again > ");
                                    sc.next();
                                }
                                amounts = sc.nextInt();

                                withdraw = amounts;
                                balance = vincent.getMoney() - amounts;
                            }

                            while (amounts < 10) {
                                System.out.print("Valid entry please, we do not accept less than $10 bills > ");
                                while (!sc.hasNextInt()) {
                                    System.out.print("Enter valid please > ");
                                    garbage = sc.next();
                                }
                                amounts = sc.nextInt();
                            }

                            String strAmount = String.valueOf(amounts);
                            while (!strAmount.endsWith("0")) {
                                System.out.print("The amount you want to withdraw is not possible with the bills available, enter a different amount > ");
                                while (!sc.hasNextInt()) {
                                    System.out.print("Enter a valid amount > ");
                                    garbage = sc.next();
                                }
                                amounts = sc.nextInt();
                                strAmount = String.valueOf(amounts);
                            }

                            if (want100 && want50) {
                                while (amounts >= 100) {
                                    amounts = amounts - 100;
                                    count100++;
                                }
                                while (amounts >= 50 && amounts < 100) {
                                    amounts = amounts - 50;
                                    count50++;
                                }
                                while (amounts >= 20 && amounts < 50) {
                                    amounts = amounts - 20;
                                    count20++;
                                }
                                while (amounts >= 10 && amounts < 20) {
                                    amounts = amounts - 10;
                                    count10++;
                                }
                            }

                            if (!want50 && want100) {
                                while (amounts >= 100) {
                                    amounts = amounts - 100;
                                    count100++;
                                }
                                while (amounts >= 20 && amounts < 100) {
                                    amounts = amounts - 20;
                                    count20++;
                                }
                                while (amounts >= 10 && amounts < 20) {
                                    amounts = amounts - 10;
                                    count10++;
                                }
                            }

                            if (!want100 && want50) {
                                while (amounts >= 50) {
                                    amounts = amounts - 50;
                                    count50++;
                                }

                                while (amounts >= 20 && amounts < 50) {
                                    amounts = amounts - 20;
                                    count20++;
                                }

                                while (amounts >= 10 && amounts < 20) {
                                    amounts = amounts - 10;
                                    count10++;
                                }
                            }

                            if (!want50 && !want100) {
                                while (amounts >= 20) {
                                    amounts = amounts - 20;
                                    count20++;
                                }
                                while (amounts >= 10 && amounts < 20) {
                                    amounts = amounts - 10;
                                    count10++;
                                }
                            }

                            System.out.println("Here is your: " + currency.format(withdraw));  // Prints withdraw amount

                            System.out.println("\nIs there anything we can help you with? (YES) (NO)");
                            response = sc.next();

                            // Checks if user needs other utility and prints a receipt no matter the response.
                            // Prints options if user enters YES.
                            if (response.equalsIgnoreCase("Yes")) {

                                response = "yes";

                                System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                        + "\n3..." + "\n2.." + "\n1.");
                                System.out.println(vincent.receipt() + "\nWITHDRAWAL FROM ACCOUNT:              " + currency.format(withdraw));
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
                                System.out.println("\n" + "\n" + "Available Balance:                   " + currency.format(balance));
                                System.out.println(vincent.bankStatement());  // bankStatement method from Accounts class

                                // User options printed once again.
                                System.out.println(vincent.options());

                            } else {

                                // USELESS???????           //response = "no";
                                System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                        + "\n3..." + "\n2.." + "\n1.");
                                System.out.println(vincent.receipt() + "\nWITHDRAWAL FROM ACCOUNT:              " + currency.format(withdraw));
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
                                System.out.println("\n" + "\n" + "Available Balance:                   " + currency.format(balance));
                                System.out.println(vincent.bankStatement());
                            }
                            break;
                        // IS IT POSSIBLE TO MAKE DEPOSITS A DOUBLE OR NOT BRO????
                        // Case 2 is if the user wants to make a deposit
                        case 2:
                            // Get user's deposit amount
                            System.out.print("\nPlease enter the amount of money you want to deposit (Integer Only) > ");
                            while (!sc.hasNextInt()) {
                                sc.next();
                                System.out.print("Please enter a valid amount > ");
                            }
                            int deposit = sc.nextInt();

                            vincent.setMoney((deposit + vincent.getMoney()));

                            System.out.println("Deposit to Account: " + currency.format(deposit));
                            System.out.println("\nIs there anything we can help you with? (YES) (NO)");
                            response = sc.next();

                            // Checking if user wants to do something else.
                            if (response.equalsIgnoreCase("Yes")) {
                                response = "yes";
                                System.out.println(vincent.options());

                            } else {   // I REMOVED THIS!! -> (response.equalsIgnoreCase("No"))

                                // USELESS????? //response = "no";
                                System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                        + "\n3..." + "\n2.." + "\n1.");
                                System.out.println(vincent.receipt() + "\nDEPOSIT TO ACCOUNT:              " + currency.format(deposit));
                                System.out.println("\n" + "\n" + "Total Balance:                   " + currency.format(vincent.getMoney()));
                                System.out.println(vincent.bankStatement());
                            }
                            break;
                        // Case 3 is used by the user to check his/her balance (Prints Balance)
                        case 3:
                            // Here we print the user's current balance and ask if there is anything else that the user wants to do.
                            System.out.println("\nYour Current Balance is: " + currency.format(vincent.getMoney()));
                            System.out.println("Is there anything we can help you with? (YES) (NO)");
                            response = sc.next();

                            // User response
                            if (response.equalsIgnoreCase("Yes")) {
                                response = "yes";
                                System.out.println(vincent.options());
                            } else { // REMOVED THIS (response.equalsIgnoreCase("no"))
                                System.out.println("Thank You!");
                            }
                            break;
                        // case 4 is for transfering money between accounts for Vincent's Account.
                        case 4:
                            // Asks user for account to transfer to with Type-Safe input.
                            System.out.println("\nPlease choose an account " + "\n(1) Ali" + "\n(2) Jeremie");
                            System.out.print("Enter choice > ");
                            while (!sc.hasNextInt()) {
                                System.out.print("Valid entry please > ");
                                sc.next();
                            }
                            int accountToTransfer = sc.nextInt();

                            // Checks if user entered an integer that is not 1 or 2
                            while (accountToTransfer != 1 && accountToTransfer != 2) {
                                System.out.println("\nINVALID ENTRY!");
                                System.out.println("Please choose an account " + "\n(1) Ali" + "\n(2) Jeremie");
                                System.out.print("Enter choice > ");
                                while (!sc.hasNextInt()) {
                                    System.out.println("\nINVALID AGAIN!");
                                    System.out.println("Please choose an account " + "\n(1) Ali" + "\n(2) Jeremie");
                                    System.out.print("Enter choice > ");
                                    sc.next();
                                }
                                accountToTransfer = sc.nextInt();
                            }
                            // Goes to account option 1 and asks for how much money to transfer
                            if (accountToTransfer == 1) {
                                System.out.print("\nPlease Enter the amount of money you want to transfer > ");
                                while (!sc.hasNextInt()) {
                                    sc.next();
                                    System.out.print("Please Enter a valid amount > ");
                                }
                                int transferedMoney = sc.nextInt();

                                //while (transferedMoney > vincent.getMoney) { // WANT TO ADD CODE HERE BUT WOULD BE ERROR LIKE THE WHILE ABOVE!!!!
                                //     
                                // }
                                ali.setMoney(ali.getMoney() + transferedMoney);
                                System.out.println("You've transferred " + currency.format(transferedMoney) + " to Ali");
                                vincent.setMoney(vincent.getMoney() - transferedMoney);

                                System.out.println("Is there anything we can help you with? (YES) (NO)");
                                response = sc.next();

                                if (response.equalsIgnoreCase("Yes")) {
                                    response = "yes";
                                    System.out.println(vincent.options());
                                } else if (response.equalsIgnoreCase("No")) {
                                    response = "no";
                                    System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                            + "\n3..." + "\n2.." + "\n1.");
                                    System.out.println(vincent.receipt() + "\nTRANSFERED MONEY:                 " + currency.format(transferedMoney));
                                    System.out.println("\n" + "\n" + "Total Balance:                   " + currency.format(vincent.getMoney()));
                                    System.out.println(vincent.bankStatement());
                                }

                                // This code is executed if Vincent account want to transfer to account option 2
                                // Note: Same Concept as option 1
                            } else if (accountToTransfer == 2) {

                                System.out.print("\nPlease Enter the amount of money you want to transfer > ");
                                while (!sc.hasNextInt()) {
                                    sc.next();
                                    System.out.print("Please Enter a valid amount > ");
                                }
                                int transferedMoney = sc.nextInt();

                                //while (transferedMoney > vincent.getMoney) { // WANT TO ADD CODE HERE BUT WOULD BE ERROR LIKE THE WHILE ABOVE!!!!
                                //     
                                // }
                                jeremie.setMoney(vincent.getMoney() + transferedMoney);
                                System.out.println("You've transferred " + currency.format(transferedMoney) + " to Jeremie");
                                vincent.setMoney(vincent.getMoney() - transferedMoney);
                                System.out.println("Is there anything we can help you with? (YES) (NO)");
                                response = sc.next();
                                if (response.equalsIgnoreCase("Yes")) {
                                    response = "yes";
                                    System.out.println(vincent.options());
                                } else if (response.equalsIgnoreCase("No")) {
                                    response = "no";
                                    System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                            + "\n3..." + "\n2.." + "\n1.");
                                    System.out.println(vincent.receipt() + "\nTRANSFERED MONEY:                 " + currency.format(transferedMoney));
                                    System.out.println("\n" + "\n" + "Total Balance:                   " + currency.format(vincent.getMoney()));
                                    System.out.println(vincent.bankStatement());
                                }
                            }
                            break;
                        // case 5 is for converting currencies from CAN to USD or CAN to EUR.
                        case 5:
                            // Printing user options and prompting for input
                            System.out.println("\n----Money Conversion----");
                            System.out.println("PLease choose for the following options: ");
                            System.out.println("(1) Convert money to US Dollars");
                            System.out.println("(2) Convert money to Euros");
                            System.out.print("\nPlease enter your choice > ");
                            while (!sc.hasNextInt()) {
                                sc.next();
                                System.out.println("PLease choose for the following options: ");
                                System.out.println("(1) Convert money to US Dollars");
                                System.out.println("(2) Convert money to Euros");
                                System.out.print("\nPlease enter your choice > ");
                            }
                            int choice = sc.nextInt();

                            // Checks if user did not input 1 or 2 put input is still valid
                            while (choice != 1 && choice != 2) {
                                System.out.println("PLease choose for the following options: ");
                                System.out.println("(1) Convert money to US Dollars");
                                System.out.println("(2) Convert money to Euros");
                                System.out.print("\nPlease enter your choice > ");
                                while (!sc.hasNextInt()) {
                                    System.out.print("Valid entry please > ");
                                    sc.next();
                                }
                                choice = sc.nextInt();
                            }
                            // This switch case performs whatever choice was chosen
                            switch (choice) {
                                // Here we convert from CAN to USD
                                case 1:
                                    System.out.println("\n****Converting From Canadian To US Dollars****");
                                    System.out.print("Please enter the amount of money you want to convert > ");
                                    while (!sc.hasNextInt()) {
                                        sc.next();
                                        System.out.print("Please enter a valid amount > ");
                                    }
                                    amount = sc.nextInt();

                                    // Instantiating MoneyConversion class
                                    MoneyConversion usd = new MoneyConversion("cad", amount, "usd");

                                    // Conversion with output
                                    System.out.println("\n----Money Conversion----");
                                    System.out.println("AMOUNT:                                                 " + currency.format(amount));
                                    System.out.println("EXCHANGE RATE:    1 Canadian Dollar = 0.71 United States Dollar");
                                    System.out.println("CONVERSION:                                             " + currency.format(usd.toUsd()));

                                    System.out.println("\nIs there anything we can help you with? (YES) (NO)");
                                    response = sc.next();

                                    // User response
                                    if (response.equalsIgnoreCase("Yes")) {
                                        response = "yes";
                                        System.out.println(vincent.options());
                                    } else if (response.equalsIgnoreCase("No")) {
                                        response = "no";
                                        System.out.println("Thank you!" + "\nGoodBye!");
                                    }
                                    break;
                                // Here we convert from CAN to EUR
                                // Note: It has the exact same concept as CAN to USD above
                                case 2:
                                    System.out.println("\n****Converting From Canadian To Euros****");
                                    System.out.print("Please enter the amount of money you want to convert > ");
                                    while (!sc.hasNextInt()) {
                                        sc.next();
                                        System.out.print("Please enter a valid amount > ");
                                    }
                                    amount = sc.nextInt();

                                    // Instantiating MoneyConversion class
                                    MoneyConversion eur = new MoneyConversion("cad", amount, "usd");

                                    System.out.println("\n----Money Conversion----");
                                    System.out.println("AMOUNT:                                                 " + currency.format(amount));
                                    System.out.println("EXCHANGE RATE:                    1 Canadian Dollar = 0.65 Euro");
                                    System.out.println("CONVERSION:                                             " + currency.format(eur.toEur()));
                                    System.out.println("\nIs there anything we can help you with? (YES) (NO)");
                                    response = sc.next();
                                    if (response.equalsIgnoreCase("Yes")) {
                                        response = "yes";
                                        System.out.println(vincent.options());
                                    } else if (response.equalsIgnoreCase("No")) {
                                        response = "no";
                                        System.out.println("Thank you!" + "\nGoodBye!");
                                    }
                                    break;
                            }
                            break;
                        default:
                            System.out.println(vincent.options());
                    }
                }
            }
            // Here is where Ali's account starts
            // Getting login info
        } else if (username.equals(ali.getUserName())) {
            System.out.print("Please enter your password > ");
            password = sc.next();
            while (!password.equals(ali.getPassWord())) {
                System.out.print("WRONG PASSWORD! Please enter your password >");
                password = sc.next();
            }
            if (username.equals(ali.getUserName()) && password.equals(ali.getPassWord())) {
                System.out.println("\n" + ali);
                String response = "yes";
                // Here is where the login verification ends for this account

                //IMPORTANT NOTE: Most of the code from here are very similar to Vincent's account until we reach the Admin account.
                //                 There will be less comments because everything was explained directly above.
                while (response.equalsIgnoreCase("Yes")) {

                    System.out.print("Please enter your choice > ");
                    while (!sc.hasNextInt()) {
                        sc.next();
                        System.out.println("\n" + ali);
                        System.out.print("Please enter your choice > ");
                    }
                    int number = sc.nextInt();

                    // switch for options choice from user
                    switch (number) {
                        // Withdrawals for this account + Challenge problem again
                        case 1:
                            System.out.print("\nEnter (yes) if you want to receive $100 bills depending on your amount > ");
                            answer100 = sc.next().trim();
                            if (answer100.equalsIgnoreCase("yes")) {
                                want100 = true;
                            }

                            System.out.print("Enter (yes) if you want to receive $50 bills depending on your amount > ");
                            answer50 = sc.next().trim();
                            if (answer50.equalsIgnoreCase("yes")) {
                                want50 = true;
                            }

                            System.out.print("\nPlease enter the amount of money you want to withdraw > ");
                            while (!sc.hasNextInt()) {
                                System.out.print("Enter a valid amount > ");
                                garbage = sc.next();
                            }
                            int amounts = sc.nextInt();
                            int withdraw = amounts;
                            int balance = ali.getMoney() - amounts;
                            ali.setMoney(balance);

                            while (amounts > ali.getMoney()) {
                                System.out.println("Sorry! insufficient balance");
                                System.out.print("Please Enter a new amount >");
                                while (!sc.hasNextInt()) {
                                    System.out.print("Integer please > ");
                                    sc.next();
                                }
                                amounts = sc.nextInt();

                                withdraw = amounts;
                                balance = ali.getMoney() - amounts;
                            }

                            while (amounts < 10) {
                                System.out.print("Valid entry please, we do not accept less than $10 bills > ");
                                while (!sc.hasNextInt()) {
                                    System.out.print("Enter valid please > ");
                                    garbage = sc.next();
                                }
                                amounts = sc.nextInt();
                            }

                            String strAmount = String.valueOf(amounts);
                            while (!strAmount.endsWith("0")) {
                                System.out.print("The amount you want to withdraw is not possible with the bills available, enter a different amount > ");
                                while (!sc.hasNextInt()) {
                                    System.out.print("Enter a valid amount > ");
                                    garbage = sc.next();
                                }
                                amounts = sc.nextInt();
                                strAmount = String.valueOf(amounts);
                            }

                            if (want100 && want50) {
                                while (amounts >= 100) {
                                    amounts = amounts - 100;
                                    count100++;
                                }
                                while (amounts >= 50 && amounts < 100) {
                                    amounts = amounts - 50;
                                    count50++;
                                }
                                while (amounts >= 20 && amounts < 50) {
                                    amounts = amounts - 20;
                                    count20++;
                                }
                                while (amounts >= 10 && amounts < 20) {
                                    amounts = amounts - 10;
                                    count10++;
                                }
                            }

                            if (!want50 && want100) {
                                while (amounts >= 100) {
                                    amounts = amounts - 100;
                                    count100++;
                                }
                                while (amounts >= 20 && amounts < 100) {
                                    amounts = amounts - 20;
                                    count20++;
                                }
                                while (amounts >= 10 && amounts < 20) {
                                    amounts = amounts - 10;
                                    count10++;
                                }
                            }

                            if (!want100 && want50) {
                                while (amounts >= 50) {
                                    amounts = amounts - 50;
                                    count50++;
                                }

                                while (amounts >= 20 && amounts < 50) {
                                    amounts = amounts - 20;
                                    count20++;
                                }

                                while (amounts >= 10 && amounts < 20) {
                                    amounts = amounts - 10;
                                    count10++;
                                }
                            }

                            if (!want50 && !want100) {
                                while (amounts >= 20) {
                                    amounts = amounts - 20;
                                    count20++;
                                }
                                while (amounts >= 10 && amounts < 20) {
                                    amounts = amounts - 10;
                                    count10++;
                                }
                            }
                            System.out.println("Here is your: " + currency.format(withdraw));

                            System.out.println("\nIs there anything we can help you with? (YES) (NO)");
                            response = sc.next();

                            // Prompting if user need anyhting else
                            if (response.equalsIgnoreCase("Yes")) {
                                response = "yes";
                                System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                        + "\n3..." + "\n2.." + "\n1.");
                                System.out.println(ali.receipt() + "\nWITHDRAWAL FROM ACCOUNT:              " + currency.format(withdraw));
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
                                System.out.println("\n" + "\n" + "Available Balance:                   " + currency.format(balance));
                                System.out.println(ali.bankStatement());

                                System.out.println(ali.options());

                            } else {  // REMOVED THIS!!! (response.equalsIgnoreCase("No"))

                                // USELESS????  ->    //response = "no";
                                System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                        + "\n3..." + "\n2.." + "\n1.");
                                System.out.println(ali.receipt() + "\nWITHDRAWAL FROM ACCOUNT:              " + currency.format(withdraw));
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
                                System.out.println("\n" + "\n" + "Available Balance:                   " + currency.format(balance));
                                System.out.println(ali.bankStatement());
                            }
                            break;
                        // Deposits for this account
                        case 2:
                            System.out.print("\nPlease enter the amount of money you want to deposit > ");
                            while (!sc.hasNextInt()) {
                                sc.next();
                                System.out.print("Please enter a valid amount > ");
                            }
                            int deposit = sc.nextInt();

                            ali.setMoney((deposit + ali.getMoney()));

                            System.out.println("Deposit to Account: " + currency.format(deposit));

                            System.out.println("\nIs there anything we can help you with? (YES) (NO)");
                            response = sc.next();

                            // user response
                            if (response.equalsIgnoreCase("Yes")) {
                                response = "yes";
                                System.out.println(ali.options());
                            } else {    // REMOVED THIS!!!! -> (response.equalsIgnoreCase("No"))
                                // USELESS???? ->  // response = "no";
                                System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                        + "\n3..." + "\n2.." + "\n1.");
                                System.out.println(ali.receipt() + "\nDEPOSIT TO ACCOUNT:              " + currency.format(deposit));
                                System.out.println("\n" + "\n" + "Total Balance:                   " + currency.format(ali.getMoney()));
                                System.out.println(ali.bankStatement());
                            }
                            break;
                        // Check Balance for this account
                        case 3:
                            System.out.println("\nYour Current Balance is: " + currency.format(ali.getMoney()));

                            System.out.println("Is there anything we can help you with? (YES) (NO)");
                            response = sc.next();

                            if (response.equalsIgnoreCase("Yes")) {
                                response = "yes";
                                System.out.println(ali.options());
                            } else {     // REMOVED THIS -> (response.equalsIgnoreCase("no"))
                                System.out.println("Thank You!");
                            }
                            break;
                        // Transfering money to other accounts
                        case 4:
                            System.out.println("\nPlease choose an account " + "\n(1) Vincent" + "\n(2) Jeremie");
                            System.out.print("Enter choice > ");
                            while (!sc.hasNextInt()) {
                                System.out.print("valid entry please > ");
                                sc.next();
                            }
                            int accountToTransfer = sc.nextInt();

                            while (accountToTransfer != 1 && accountToTransfer != 2) {
                                System.out.println("\nINVALID ENTRY!");
                                System.out.println("\nPlease choose an account " + "\n(1) Vincent" + "\n(2) Jeremie");
                                System.out.print("Enter choice > ");
                                while (!sc.hasNextInt()) {
                                    System.out.println("\nINVALID AGAIN!");
                                    System.out.println("(1) Vincent" + "\n(2) Jeremie");
                                    System.out.print("Enter choice > ");
                                    sc.next();
                                }
                                accountToTransfer = sc.nextInt();
                            }

                            // If option 1 is chosen
                            if (accountToTransfer == 1) {
                                System.out.print("\nPlease Enter the amount of money you want to transfer > ");
                                while (!sc.hasNextInt()) {
                                    sc.next();
                                    System.out.print("Please Enter a valid amount > ");
                                }
                                int transferedMoney = sc.nextInt();

                                vincent.setMoney(vincent.getMoney() + transferedMoney);

                                System.out.println("You've transferred " + currency.format(transferedMoney) + " to Ali");
                                ali.setMoney(ali.getMoney() - transferedMoney);

                                System.out.println("Is there anything we can help you with? (YES) (NO)");
                                response = sc.next();

                                // user response
                                if (response.equalsIgnoreCase("Yes")) {
                                    response = "yes";
                                    System.out.println(ali.options());
                                } else {  // REMOVED THIS!!! -> (response.equalsIgnoreCase("No"))
                                    // USELESS ???? //response = "no";
                                    System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                            + "\n3..." + "\n2.." + "\n1.");
                                    System.out.println(ali.receipt() + "\nTRANSFERED MONEY:                 " + currency.format(transferedMoney));
                                    System.out.println("\n" + "\n" + "Total Balance:                   " + currency.format(ali.getMoney()));
                                    System.out.println(ali.bankStatement());
                                }
                                // If option 2 is chosen
                            } else if (accountToTransfer == 2) {
                                System.out.print("\nPlease Enter the amount of money you want to transfer > ");
                                while (!sc.hasNextInt()) {
                                    sc.next();
                                    System.out.print("Please Enter a valid amount > ");
                                }
                                int transferedMoney = sc.nextInt();

                                jeremie.setMoney(ali.getMoney() + transferedMoney);

                                System.out.println("You've transferred " + currency.format(transferedMoney) + " to Jeremie");
                                ali.setMoney(ali.getMoney() - transferedMoney);

                                System.out.println("Is there anything we can help you with? (YES) (NO)");
                                response = sc.next();

                                // user response
                                if (response.equalsIgnoreCase("Yes")) {
                                    response = "yes";
                                    System.out.println(ali.options());
                                } else {  // REMOVED THIS!!! -> (response.equalsIgnoreCase("No"))
                                    // USELESS??? -> //response = "no";
                                    System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                            + "\n3..." + "\n2.." + "\n1.");
                                    System.out.println(ali.receipt() + "\nTRANSFERED MONEY:                 " + currency.format(transferedMoney));
                                    System.out.println("\n" + "\n" + "Total Balance:                   " + currency.format(vincent.getMoney()));
                                    System.out.println(ali.bankStatement());
                                }
                            }
                            break;
                        // Currency conversion for this account
                        case 5:
                            System.out.println("\n----Money Conversion----");
                            System.out.println("PLease choose for the following options: ");
                            System.out.println("(1) Convert money to US Dollars");
                            System.out.println("(2) Convert money to Euros");
                            System.out.print("\nPlease enter your choice > ");
                            while (!sc.hasNextInt()) {
                                sc.next();
                                System.out.println("\nPlease choose for the following options: ");
                                System.out.println("(1) Convert money to US Dollars");
                                System.out.println("(2) Convert money to Euros");
                                System.out.print("\nPlease enter your choice > ");
                            }
                            int choice = sc.nextInt();

                            while (choice != 1 && choice != 2) {
                                System.out.println("Please choose for the following options: ");
                                System.out.println("(1) Convert money to US Dollars");
                                System.out.println("(2) Convert money to Euros");
                                System.out.print("\nPlease enter your choice > ");
                                while (!sc.hasNextInt()) {
                                    System.out.print("Valid choice please > ");
                                    sc.next();
                                }
                                choice = sc.nextInt();
                            }
                            // switch statement for user's choice
                            switch (choice) {
                                // Converting from CAN to USD
                                case 1:
                                    System.out.println("\n****Converting From Canadian To US Dollars****");
                                    System.out.print("Please enter the amount of money you want to convert > ");
                                    while (!sc.hasNextInt()) {
                                        sc.next();
                                        System.out.print("Please enter a valid amount > ");
                                    }
                                    amount = sc.nextInt();

                                    // Instantiating MoneyConversion class
                                    MoneyConversion usd = new MoneyConversion("cad", amount, "usd");

                                    System.out.println("\n----Money Conversion----");
                                    System.out.println("AMOUNT:                                                 " + currency.format(amount));
                                    System.out.println("EXCHANGE RATE:    1 Canadian Dollar = 0.71 United States Dollar");
                                    System.out.println("CONVERSION:                                             " + currency.format(usd.toUsd()));

                                    System.out.println("\nIs there anything we can help you with? (YES) (NO)");
                                    response = sc.next();

                                    if (response.equalsIgnoreCase("Yes")) {
                                        response = "yes";
                                        System.out.println(ali.options());
                                    } else { // I REMOVED THIS!!! -> (response.equalsIgnoreCase("No"))
                                        // USELESS??? -> //response = "no";
                                        for (int i = 0; i < 2; i++) {
                                            if (i == 0) {
                                                System.out.println("Thank you!");
                                            }
                                            if (i == 1) {
                                                System.out.println("GoodBye!");
                                            }
                                        }
                                    }
                                    break;
                                // Converting CAD to EUR for this account
                                case 2:
                                    System.out.println("\n****Converting From Canadian To Euros****");
                                    System.out.print("Please enter the amount of money you want to convert > ");
                                    while (!sc.hasNextInt()) {
                                        sc.next();
                                        System.out.print("Please enter a valid amount > ");
                                    }
                                    amount = sc.nextInt();

                                    // Instantiating MoneyConversion class
                                    MoneyConversion eur = new MoneyConversion("cad", amount, "usd");

                                    System.out.println("\n----Money Conversion----");
                                    System.out.println("AMOUNT:                                                 " + currency.format(amount));
                                    System.out.println("EXCHANGE RATE:                    1 Canadian Dollar = 0.65 Euro");
                                    System.out.println("CONVERSION:                                             " + currency.format(eur.toEur()));

                                    System.out.println("\nIs there anything we can help you with? (YES) (NO)");
                                    response = sc.next();

                                    // user response
                                    if (response.equalsIgnoreCase("Yes")) {
                                        response = "yes";
                                        System.out.println(ali.options());
                                    } else {  // I REMOVED THIS!!! -> (response.equalsIgnoreCase("No"))
                                        // USELESSS???? -> //response = "no";
                                        System.out.println("Thank you!" + "\nGoodBye!");
                                    }
                                    break;
                                default:
//       USELESS???                             System.out.println("Please choose for the following options: ");
//       USELESS???                             System.out.println("(1) Convert money to US Dollars");
//       USELESS???                             System.out.println("(2) Convert money to Euros");
//       USELESS???                             System.out.print("\nPlease enter your choice > ");
//       USELESS???                             choice = sc.nextInt();
                            }
                            break;
                        default:
                            System.out.println(ali.options());
                    }
                }
            }
            // Here is where Jeremie's account starts
            // Getting login info
        } else if (username.equals(jeremie.getUserName())) {
            System.out.print("Please enter your password > ");
            password = sc.next();
            while (!password.equals(jeremie.getPassWord())) {
                System.out.print("Please enter your password >");
                password = sc.next();
            }
            if (username.equals(jeremie.getUserName()) && password.equals(jeremie.getPassWord())) {
                System.out.println("\n" + jeremie);
                String response = "yes";
                while (response.equalsIgnoreCase("Yes")) {

                    System.out.print("Please enter your choice > ");
                    while (!sc.hasNextInt()) {
                        sc.next();
                        System.out.println("\n" + jeremie);
                        System.out.print("Please enter your choice > ");
                    }
                    int number = sc.nextInt();

                    // Switch case for choice of the user
                    switch (number) {
                        // Withdrawal for this account + Challenge Problem
                        case 1:
                            System.out.print("\nEnter (yes) if you want to receive $100 bills depending on your amount > ");
                            answer100 = sc.next().trim();
                            if (answer100.equalsIgnoreCase("yes")) {
                                want100 = true;
                            }

                            System.out.print("Enter (yes) if you want to receive $50 bills depending on your amount > ");
                            answer50 = sc.next().trim();
                            if (answer50.equalsIgnoreCase("yes")) {
                                want50 = true;
                            }

                            System.out.print("\nPlease enter the amount of money you want to withdraw > ");
                            while (!sc.hasNextInt()) {
                                System.out.print("Enter a valid amount > ");
                                garbage = sc.next();
                            }
                            int amounts = sc.nextInt();
                            int withdraw = amounts;
                            int balance = jeremie.getMoney() - amounts;
                            jeremie.setMoney(balance);

                            while (amounts > jeremie.getMoney()) {     // THIS DOESNT WORK IF WE WANT TO WITHDRAW AGAIN!!!!!!!
                                System.out.println("Sorry! insufficient balance");
                                System.out.print("Please Enter a new amount >");
                                while (!sc.hasNextInt()) {
                                    System.out.print("Integer please > ");
                                    sc.next();
                                }
                                amounts = sc.nextInt();

                                withdraw = amounts;
                                balance = jeremie.getMoney() - amounts;
                            }

                            while (amounts < 10) {
                                System.out.print("Valid entry please, we do not accept less than $10 bills > ");
                                while (!sc.hasNextInt()) {
                                    System.out.print("Enter valid please > ");
                                    garbage = sc.next();
                                }
                                amounts = sc.nextInt();
                            }

                            String strAmount = String.valueOf(amounts);
                            while (!strAmount.endsWith("0")) {
                                System.out.print("The amount you want to withdraw is not possible with the bills available, enter a different amount > ");
                                while (!sc.hasNextInt()) {
                                    System.out.print("Enter a valid amount > ");
                                    garbage = sc.next();
                                }
                                amounts = sc.nextInt();
                                strAmount = String.valueOf(amounts);
                            }

                            if (want100 && want50) {
                                while (amounts >= 100) {
                                    amounts = amounts - 100;
                                    count100++;
                                }
                                while (amounts >= 50 && amounts < 100) {
                                    amounts = amounts - 50;
                                    count50++;
                                }
                                while (amounts >= 20 && amounts < 50) {
                                    amounts = amounts - 20;
                                    count20++;
                                }
                                while (amounts >= 10 && amounts < 20) {
                                    amounts = amounts - 10;
                                    count10++;
                                }
                            }

                            if (!want50 && want100) {
                                while (amounts >= 100) {
                                    amounts = amounts - 100;
                                    count100++;
                                }
                                while (amounts >= 20 && amounts < 100) {
                                    amounts = amounts - 20;
                                    count20++;
                                }
                                while (amounts >= 10 && amounts < 20) {
                                    amounts = amounts - 10;
                                    count10++;
                                }
                            }

                            if (!want100 && want50) {
                                while (amounts >= 50) {
                                    amounts = amounts - 50;
                                    count50++;
                                }

                                while (amounts >= 20 && amounts < 50) {
                                    amounts = amounts - 20;
                                    count20++;
                                }

                                while (amounts >= 10 && amounts < 20) {
                                    amounts = amounts - 10;
                                    count10++;
                                }
                            }

                            if (!want50 && !want100) {
                                while (amounts >= 20) {
                                    amounts = amounts - 20;
                                    count20++;
                                }
                                while (amounts >= 10 && amounts < 20) {
                                    amounts = amounts - 10;
                                    count10++;
                                }
                            }
                            System.out.println("Here is your: " + currency.format(withdraw));

                            System.out.println("\nIs there anything we can help you with? (YES) (NO)");
                            response = sc.next();

                            if (response.equalsIgnoreCase("Yes")) {
                                response = "yes";
                                System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                        + "\n3..." + "\n2.." + "\n1.");
                                System.out.println(jeremie.receipt() + "\nWITHDRAWAL FROM ACCOUNT:              " + currency.format(withdraw));
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
                                System.out.println("\n" + "\n" + "Available Balance:                   " + currency.format(balance));
                                System.out.println(jeremie.bankStatement());

                                System.out.println(jeremie.options());

                            } else if (response.equalsIgnoreCase("No")) {

                                response = "no";
                                System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                        + "\n3..." + "\n2.." + "\n1.");
                                System.out.println(ali.receipt() + "\nWITHDRAWAL FROM ACCOUNT:              " + currency.format(withdraw));
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
                                System.out.println("\n" + "\n" + "Available Balance:                   " + currency.format(balance));
                                System.out.println(jeremie.bankStatement());
                            }
                            break;
                        // Deposits for this account
                        case 2:
                            System.out.print("\nPlease enter the amount of money you want to deposit > ");
                            while (!sc.hasNextInt()) {
                                sc.next();
                                System.out.print("Please enter a valid amount > ");
                            }
                            int deposit = sc.nextInt();
                            
                            jeremie.setMoney((deposit + jeremie.getMoney()));
                            System.out.println("Deposit to Account: " + currency.format(deposit));
                            
                            System.out.println("\nIs there anything we can help you with? (YES) (NO)");
                            response = sc.next();
                            
                            // user response
                            if (response.equalsIgnoreCase("Yes")) {
                                response = "yes";
                                System.out.println(jeremie.options());
                            } else {  // REMOVED THIS!!! -> (response.equalsIgnoreCase("No"))
                                // USELESS ??? -> response = "no";
                                System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                        + "\n3..." + "\n2.." + "\n1.");
                                System.out.println(jeremie.receipt() + "\nDEPOSIT TO ACCOUNT:              " + currency.format(deposit));
                                System.out.println("\n" + "\n" + "Total Balance:                   " + currency.format(jeremie.getMoney()));
                                System.out.println(jeremie.bankStatement());
                            }
                            break;
                        // Check Balance for this account
                        case 3:
                            System.out.println("\nYour Current Balance is: " + currency.format(jeremie.getMoney()));
                            
                            System.out.println("Is there anything we can help you with? (YES) (NO)");
                            response = sc.next();
                            
                            // user response
                            if (response.equalsIgnoreCase("Yes")) {
                                // USLESSS ??? -> response = "yes";
                                System.out.println(jeremie.options());
                            } else { // I REMOVED THIS -> (response.equalsIgnoreCase("no"))
                                System.out.println("Thank You!");
                            }
                            break;
                        // Transfering money to other accounts for this account
                        case 4:
                            System.out.println("\nPlease choose an account " + "\n(1) Vincent" + "\n(2) Ali");
                            int accountToTransfer = sc.nextInt();
                            while (accountToTransfer != 1 && accountToTransfer != 2) {
                                System.out.println("Please choose from the following choices");
                                System.out.println("(1) Vincent" + "\n(2) Ali");
                                accountToTransfer = sc.nextInt();
                            }
                            if (accountToTransfer == 1) {
                                System.out.print("\nPlease Enter the amount of money you want to transfer > ");
                                while (!sc.hasNextInt()) {
                                    sc.next();
                                    System.out.print("Please Enter a valid amount > ");
                                }
                                int transferedMoney = sc.nextInt();
                                vincent.setMoney(vincent.getMoney() + transferedMoney);
                                System.out.println("You've transferred " + currency.format(transferedMoney) + " to Ali");
                                jeremie.setMoney(jeremie.getMoney() - transferedMoney);
                                System.out.println("Is there anything we can help you with? (YES) (NO)");
                                response = sc.next();
                                if (response.equalsIgnoreCase("Yes")) {
                                    response = "yes";
                                    System.out.println(jeremie.options());
                                } else if (response.equalsIgnoreCase("No")) {
                                    response = "no";
                                    System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                            + "\n3..." + "\n2.." + "\n1.");
                                    System.out.println(jeremie.receipt() + "\nTRANSFERED MONEY:                 " + currency.format(transferedMoney));
                                    System.out.println("\n" + "\n" + "Total Balance:                   " + currency.format(jeremie.getMoney()));
                                    System.out.println(jeremie.bankStatement());
                                }
                            } else if (accountToTransfer == 2) {
                                System.out.print("\nPlease Enter the amount of money you want to transfer > ");
                                while (!sc.hasNextInt()) {
                                    sc.next();
                                    System.out.print("Please Enter a valid amount > ");
                                }
                                int transferedMoney = sc.nextInt();
                                ali.setMoney(ali.getMoney() + transferedMoney);
                                System.out.println("You've transferred " + currency.format(transferedMoney) + " to Jeremie");
                                jeremie.setMoney(jeremie.getMoney() - transferedMoney);
                                System.out.println("Is there anything we can help you with? (YES) (NO)");
                                response = sc.next();
                                if (response.equalsIgnoreCase("Yes")) {
                                    response = "yes";
                                    System.out.println(jeremie.options());
                                } else if (response.equalsIgnoreCase("No")) {
                                    response = "no";
                                    System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                            + "\n3..." + "\n2.." + "\n1.");
                                    System.out.println(jeremie.receipt() + "\nTRANSFERED MONEY:                 " + currency.format(transferedMoney));
                                    System.out.println("\n" + "\n" + "Total Balance:                   " + currency.format(jeremie.getMoney()));
                                    System.out.println(jeremie.bankStatement());
                                }
                            }
                            break;
                        case 5:
                            System.out.println("\n----Money Conversion----");
                            System.out.println("PLease choose for the following options: ");
                            System.out.println("(1) Convert money to US Dollars");
                            System.out.println("(2) Convert money to Euros");
                            System.out.print("\nPlease enter your choice > ");
                            while (!sc.hasNextInt()) {
                                sc.next();
                                System.out.println("PLease choose for the following options: ");
                                System.out.println("(1) Convert money to US Dollars");
                                System.out.println("(2) Convert money to Euros");
                                System.out.print("\nPlease enter your choice > ");
                            }
                            int choice = sc.nextInt();
                            while (choice != 1 && choice != 2) {
                                System.out.println("PLease choose for the following options: ");
                                System.out.println("(1) Convert money to US Dollars");
                                System.out.println("(2) Convert money to Euros");
                                System.out.print("\nPlease enter your choice > ");
                                choice = sc.nextInt();
                            }
                            switch (choice) {
                                case 1:
                                    System.out.println("\n****Converting From Canadian To US Dollars****");
                                    System.out.println("Please enter the amount of money you want to convert > ");
                                    while (!sc.hasNextInt()) {
                                        sc.next();
                                        System.out.print("Please enter a valid amount > ");
                                    }
                                    amount = sc.nextInt();
                                    MoneyConversion USD = new MoneyConversion("cad", amount, "usd");
                                    System.out.println("\n----Money Conversion----");
                                    System.out.println("AMOUNT:                                                 " + currency.format(amount));
                                    System.out.println("EXCHANGE RATE:    1 Canadian Dollar = 0.71 United States Dollar");
                                    System.out.println("CONVERSION:                                             " + currency.format(USD.toUsd()));
                                    System.out.println("\nIs there anything we can help you with? (YES) (NO)");
                                    response = sc.next();
                                    if (response.equalsIgnoreCase("Yes")) {
                                        response = "yes";
                                        System.out.println(jeremie.options());
                                    } else if (response.equalsIgnoreCase("No")) {
                                        response = "no";
                                        System.out.println("Thank you!" + "\nGoodBye!");
                                    }
                                    break;
                                case 2:
                                    System.out.println("\n****Converting From Canadian To Euros****");
                                    System.out.print("Please enter the amount of money you want to convert > ");
                                    while (!sc.hasNextInt()) {
                                        sc.next();
                                        System.out.print("Please enter a valid amount > ");
                                    }
                                    amount = sc.nextInt();
                                    MoneyConversion EUR = new MoneyConversion("cad", amount, "usd");
                                    System.out.println("\n----Money Conversion----");
                                    System.out.println("AMOUNT:                                                 " + currency.format(amount));
                                    System.out.println("EXCHANGE RATE:                    1 Canadian Dollar = 0.65 Euro");
                                    System.out.println("CONVERSION:                                             " + currency.format(EUR.toEur()));
                                    System.out.println("\nIs there anything we can help you with? (YES) (NO)");
                                    response = sc.next();
                                    if (response.equalsIgnoreCase("Yes")) {
                                        response = "yes";
                                        System.out.println(jeremie.options());
                                    } else if (response.equalsIgnoreCase("No")) {
                                        response = "no";
                                        System.out.println("Thank you!" + "\nGoodBye!");
                                    }
                                    break;
                                default:
                                    System.out.println("PLease choose for the following options: ");
                                    System.out.println("(1) Convert money to US Dollars");
                                    System.out.println("(2) Convert money to Euros");
                                    System.out.println("\nPlease enter your choice");
                                    choice = sc.nextInt();
                            }
                            break;
                        default:
                            System.out.println(jeremie.options());
                    }
                }
            }
        } else if (username.equals(AdminAccount.getUser())) {
            System.out.println("\n[_[-=ADMINISTRATIVE ACCOUNT .JAV 2.0491=-]_]");
            System.out.println("Welcome back Jeremie~ please enter your password");
            newPass = sc.next();
            while (!newPass.equals(AdminAccount.getPass())) {
                System.out.println("WRONG PASSWORD ENTER AGAIN");
                newPass = sc.next();
            }
            if (username.equals(AdminAccount.getUser()) && newPass.equals(AdminAccount.getPass())) {
                while (!newPass.equals(AdminAccount.getPass())) {
                    System.out.println("WRONG PASSWORD ENTER AGAIN");
                    newPass = sc.next();
                }
                if (username.equals(AdminAccount.getUser()) && newPass.equals(AdminAccount.getPass())) {
                    String answer = "Yes";
                    while (answer.equalsIgnoreCase("yes")) {
                        System.out.println(admin);
                        System.out.println("Those are your account's selection permissions, select >>");
                        int number = sc.nextInt();
                        switch (number) {
                            case 1:
                                System.out.println("\n.::Here are the following accounts::.");
                                System.out.println(userOne);
                                System.out.println(userTwo);
                                System.out.println(userThree);
                                System.out.println("\n.::Enter yes or no respectively to either go back or terminate this session::.");
                                answer = sc.next();
                                break;
                            case 2:
                                System.out.println(h1);
                                System.out.println(h2);
                                System.out.println(h3);
                                System.out.println(h4);
                                System.out.println("\n.::Enter yes or no respectively to either go back or terminate this session::.");
                                answer = sc.next();
                                break;
                            case 3:
                                System.out.println("Select an account to verify their loyality");
                                System.out.println("Press 1 to enter Ali's account");
                                System.out.println("press 2 to enter Jeremie's account");
                                System.out.println("Press 3 to enter Vincent's account");
                                System.out.println("Press 4 to deposit/remove from all accounts");
                                while (!sc.hasNextInt()) {
                                    System.out.println("Please enter a number and not a string");
                                    sc.next();
                                }
                                int accounts = sc.nextInt();
                                switch (accounts) {
                                    case 1:
                                        System.out.println(".::You've selected Ali's account::.");
                                        System.out.println("The standing of the account's loyality is: GOOD");
                                        System.out.println("\nWhat do you wish to do: DEPOSIT | REMOVE");
                                        String word = sc.next();
                                        if (word.equalsIgnoreCase("deposit")) {
                                            System.out.println("Please enter the amount you wish to deposit to this account");
                                            while (!sc.hasNextInt()) {
                                                System.out.println("Please enter an INTEGER and not a string.");
                                                sc.next();
                                            }
                                            int pick1 = sc.nextInt();
                                            System.out.println("You've added " + pick1 + "$ " + "to Ali's account");
                                            System.out.println("New balance is: " + (pick1 + ali.getMoney()) + "$");
                                            System.out.println("\n.::Enter yes or no respectively to either go back or terminate this session::.");
                                            answer = sc.next();
                                            while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
                                                System.out.println("You've entered something else than yes or no, please enter either one.");
                                                answer = sc.next();
                                            }
                                        }

                                        if (word.equalsIgnoreCase("remove")) {
                                            System.out.println("Please enter the amount you wish to remove from the account");
                                            while (!sc.hasNextInt()) {
                                                System.out.println("Please enter an INTEGER and not a string.");
                                                sc.next();
                                            }
                                            int pick2 = sc.nextInt();
                                            System.out.println("You've removed " + pick2 + "$ " + "from Ali's account");
                                            System.out.println("New balance is: " + (ali.getMoney() - pick2) + "$");
                                            System.out.println("\n.::Enter yes or no respectively to either go back or terminate this session::.");
                                            answer = sc.next();
                                            while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
                                                System.out.println("You've entered something else than yes or no, please enter either one.");
                                                answer = sc.next();
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println(".::You've selected Jeremie's account::.");
                                        System.out.println("The standing of the account's loyality is: BAD");
                                        System.out.println("\nWhat do you wish to do: DEPOSIT | REMOVE");
                                        word = sc.next();
                                        if (word.equalsIgnoreCase("deposit")) {
                                            System.out.println("Please enter the amount you wish to deposit to this account");
                                            while (!sc.hasNextInt()) {
                                                System.out.println("Please enter an INTEGER and not a string.");
                                                sc.next();
                                            }
                                            int pick1 = sc.nextInt();
                                            System.out.println("You've added " + pick1 + "$ " + "to Jeremie's account");
                                            System.out.println("New balance is: " + (pick1 + jeremie.getMoney()) + "$");
                                            System.out.println("\n.::Enter yes or no respectively to either go back or terminate this session::.");
                                            answer = sc.next();
                                            while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
                                                System.out.println("You've entered something else than yes or no, please enter either one.");
                                                answer = sc.next();
                                            }
                                        }

                                        if (word.equalsIgnoreCase("remove")) {
                                            System.out.println("Please enter the amount you wish to remove from the account");
                                            while (!sc.hasNextInt()) {
                                                System.out.println("Please enter an INTEGER and not a string.");
                                                sc.next();
                                            }
                                            int pick2 = sc.nextInt();
                                            System.out.println("You've removed " + pick2 + "$ " + "from Jeremie's account");
                                            System.out.println("New balance is: " + (jeremie.getMoney() - pick2) + "$");
                                            System.out.println("\n.::Enter yes or no respectively to either go back or terminate this session::.");
                                            answer = sc.next();
                                            while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
                                                System.out.println("You've entered something else than yes or no, please enter either one.");
                                                answer = sc.next();
                                            }
                                        }
                                        break;
                                    case 3:
                                        System.out.println(".::You've selected Vincent's account::.");
                                        System.out.println("The standing of the account's loyality is: EXTREMELY GOOD");
                                        System.out.println("\nWhat do you wish to do: DEPOSIT | REMOVE");
                                        word = sc.next();
                                        if (word.equalsIgnoreCase("deposit")) {
                                            System.out.println("Please enter the amount you wish to deposit to this account");
                                            while (!sc.hasNextInt()) {
                                                System.out.println("Please enter an INTEGER and not a string.");
                                                sc.next();
                                            }
                                            int pick1 = sc.nextInt();
                                            System.out.println("You've added " + pick1 + "$ " + "to Vincent's account");
                                            System.out.println("New balance is: " + (pick1 + vincent.getMoney()) + "$");
                                            System.out.println("\n.::Enter yes or no respectively to either go back or terminate this session::.");
                                            answer = sc.next();
                                            while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
                                                System.out.println("You've entered something else than yes or no, please enter either one.");
                                                answer = sc.next();
                                            }
                                        }

                                        if (word.equalsIgnoreCase("remove")) {
                                            System.out.println("Please enter the amount you wish to remove from the account");
                                            while (!sc.hasNextInt()) {
                                                System.out.println("Please enter an INTEGER and not a string.");
                                                sc.next();
                                            }
                                            int pick2 = sc.nextInt();
                                            System.out.println("You've removed " + pick2 + "$ " + "from Vincent's account");
                                            System.out.println("New balance is: " + (vincent.getMoney() - pick2) + "$");
                                            System.out.println("\n.::Enter yes or no respectively to either go back or terminate this session::.");
                                            answer = sc.next();
                                            while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
                                                System.out.println("You've entered something else than yes or no, please enter either one.");
                                                answer = sc.next();
                                            }
                                        }
                                        break;
                                    case 4:
                                        System.out.println(".::You've selected all the accounts::.");
                                        System.out.println("The average standing of all account's loyality is: NORMAL");
                                        System.out.println("\nWhat do you wish to do: DEPOSIT | REMOVE");
                                        word = sc.next();
                                        if (word.equalsIgnoreCase("deposit")) {
                                            System.out.println("Please enter the amount you wish to deposit to all the accounts");
                                            while (!sc.hasNextInt()) {
                                                System.out.println("Please enter an INTEGER and not a string.");
                                                sc.next();
                                            }
                                            int pick1 = sc.nextInt();
                                            System.out.println("You've added " + pick1 + "$ " + "to all the accounts");
                                            System.out.println("New balance for Jeremie is: " + (pick1 + jeremie.getMoney()) + "$");
                                            System.out.println("New balance for Ali is: " + (pick1 + ali.getMoney()) + "$");
                                            System.out.println("New balance for Vincent is  " + (pick1 + vincent.getMoney()) + "$");
                                            System.out.println("\n.::Enter yes or no respectively to either go back or terminate this session::.");
                                            answer = sc.next();
                                            while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
                                                System.out.println("You've entered something else than yes or no, please enter either one.");
                                                answer = sc.next();
                                            }
                                        }

                                        if (word.equalsIgnoreCase("remove")) {
                                            System.out.println("Please enter the amount you wish to remove from all the accounts");
                                            while (!sc.hasNextInt()) {
                                                System.out.println("Please enter an INTEGER and not a string.");
                                                sc.next();
                                            }
                                            int pick2 = sc.nextInt();
                                            System.out.println("You've removed " + pick2 + "$ " + "from all accounts");
                                            System.out.println("New balance for Jeremie is: " + (jeremie.getMoney() - pick2) + "$");
                                            System.out.println("New balance for Ali is: " + (ali.getMoney() - pick2) + "$");
                                            System.out.println("New balance for Vincent is: " + (vincent.getMoney() - pick2) + "$");

                                            System.out.println("\n.::Enter yes or no respectively to either go back or terminate this session::.");
                                            answer = sc.next();
                                            while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
                                                System.out.println("You've entered something else than yes or no, please enter either one.");
                                                answer = sc.next();
                                            }
                                        }
                                        break;

                                    default:
                                }
                            default:
                        }
                    }
                    for (int i = 0; i < 2; i++) {
                        if (i == 0) {
                            System.out.println("Goodbye.");  // GOODBYE output for admin account. 
                        }
                        if (i == 1) {
                            System.out.println("Come back soon Admin Jeremie!");
                        }
                    }
                }
            }
        }
    }
}
