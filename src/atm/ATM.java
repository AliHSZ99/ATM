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
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Accounts vincent = new Accounts("Vincent", "Benesen", 5000);
        Accounts ali = new Accounts("Ali", "Zoubeidi", 8000);
        Accounts jeremie = new Accounts("Jeremie", "Guerchon", 4000);
        AdminAccount admin = new AdminAccount("admin", "password", 9999);

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

        String newPass = " ";

        String garbage;
        int count100 = 0;
        int count50 = 0;
        int count20 = 0;
        int count10 = 0;
        String answer100;
        String answer50;
        boolean want100 = false;
        boolean want50 = false;

        DecimalFormat currency = new DecimalFormat("0.00$");
        String password = null;
        int amount;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n*******************************************");
        System.out.println("*------->>>WELCOME TO J.A.V BANK<<<-------*");
        System.out.println("*******************************************");
        System.out.println("\nPlease enter your username > ");
        String username = sc.next();

        while (!username.equals(vincent.getUserName()) && !username.equals(admin.getUser()) && !username.equals(ali.getUserName()) && !username.equals(jeremie.getUserName())) {
            System.out.println("INVALID USERNAME, Please enter your username");
            username = sc.next();
        }

        if (username.equals(vincent.getUserName())) {
            System.out.println("Please enter your password > ");
            password = sc.next();
            while (!password.equals(vincent.getPassWord())) {
                System.out.println("Please enter your password >");
                password = sc.next();
            }
            if (username.equals(vincent.getUserName()) && password.equals(vincent.getPassWord())) {
                System.out.println("\n" + vincent);
                String response = "yes";
                while (response.equalsIgnoreCase("Yes")) {

                    System.out.print("Please enter your choice > ");
                    int number = sc.nextInt();
                    switch (number) {
                        case 1:
                            System.out.print("Enter (yes) if you want to receive $100 bills depending on your amount > ");
                            answer100 = sc.next().trim();
                            if (answer100.equalsIgnoreCase("yes")) {
                                want100 = true;
                            }

                            System.out.print("Enter (yes) if you want to receive $50 bills depending on your amount > ");
                            answer50 = sc.next().trim();
                            if (answer50.equalsIgnoreCase("yes")) {
                                want50 = true;
                            }

                            System.out.print("Please enter the amount of money you want to withdraw > ");
                            while (!sc.hasNextInt()) {
                                System.out.print("Enter a valid amount > ");
                                garbage = sc.next();
                            }
                            int amounts = sc.nextInt();
                            int withdraw = amounts;
                            int balance = vincent.getMoney() - amounts;

                            while (amounts > vincent.getMoney()) {
                                System.out.println("Sorry! insufficient balance");
                                System.out.print("Please Enter a new amount >");
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

                            System.out.println("Here is your: " + currency.format(withdraw));
                            System.out.println("\nIs there anything we can help you with? (YES) (NO)");
                            response = sc.next();
                            if (response.equalsIgnoreCase("Yes")) {
                                response = "yes";
                                System.out.println(vincent.options());
                            } else if (response.equalsIgnoreCase("No")) {
                                response = "no";
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
                        case 2:
                            System.out.print("Please enter the amount of money you want to deposit > ");
                            while (!sc.hasNextInt()) {
                                sc.next();
                                System.out.print("Please enter a valid amount > ");
                            }
                            int deposit = sc.nextInt();
                            vincent.setMoney((deposit + vincent.getMoney()));
                            System.out.println("Deposit to Account: " + currency.format(deposit));
                            System.out.println("\nIs there anything we can help you with? (YES) (NO)");
                            response = sc.next();
                            if (response.equalsIgnoreCase("Yes")) {
                                response = "yes";
                                System.out.println(vincent.options());
                            } else if (response.equalsIgnoreCase("No")) {
                                response = "no";
                                System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                        + "\n3..." + "\n2.." + "\n1.");
                                System.out.println(vincent.receipt() + "\nDEPOSIT TO ACCOUNT:              " + currency.format(deposit));
                                System.out.println("\n" + "\n" + "Total Balance:                   " + currency.format(vincent.getMoney()));
                                System.out.println(vincent.bankStatement());
                            }
                            break;
                        case 3:
                            System.out.println("Your Current Balance is: " + currency.format(vincent.getMoney()));
                            System.out.println("Is there anything we can help you with? (YES) (NO)");
                            response = sc.next();
                            if (response.equalsIgnoreCase("Yes")) {
                                response = "yes";
                                System.out.println(vincent.options());
                            } else if (response.equalsIgnoreCase("no")) {
                                System.out.println("Thank You!");
                            }
                            break;
                        case 4:
                            System.out.println("Please choose an account " + "\n(1) Ali" + "\n(2) Jeremie");
                            int accountToTransfer = sc.nextInt();
                            while (accountToTransfer != 1 && accountToTransfer != 2) {
                                System.out.println("Please choose from the following choices");
                                System.out.println("(1) Ali" + "\n(2) Jeremie");
                                accountToTransfer = sc.nextInt();
                            }
                            if (accountToTransfer == 1) {
                                System.out.println("Please Enter the amount of money you want to transfer > ");
                                while (!sc.hasNextInt()) {
                                    sc.next();
                                    System.out.print("Please Enter a valid amount > ");
                                }
                                int transferedMoney = sc.nextInt();
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
                            } else if (accountToTransfer == 2) {
                                System.out.println("Please Enter the amount of money you want to transfer > ");
                                while (!sc.hasNextInt()) {
                                    sc.next();
                                    System.out.print("Please Enter a valid amount > ");
                                }
                                int transferedMoney = sc.nextInt();
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
                        case 5:
                            System.out.println("----Money Conversion----");
                            System.out.println("PLease choose for the following options: ");
                            System.out.println("(1) Convert money to US Dollars");
                            System.out.println("(2) Convert money to Euros");
                            System.out.println("\nPlease enter your choice");
                            int choice = sc.nextInt();
                            switch (choice) {
                                case 1:
                                    System.out.println("****Converting From Canadian To US Dollars****");
                                    System.out.println("Please enter the amount of money you want to convert > ");
                                    amount = sc.nextInt();
                                    MoneyConversion USD = new MoneyConversion("cad", amount, "usd");
                                    System.out.println("----Money Conversion----");
                                    System.out.println("AMOUNT:                                                 " + currency.format(amount));
                                    System.out.println("EXCHANGE RATE:    1 Canadian Dollar = 0.71 United States Dollar");
                                    System.out.println("CONVERSION:                                             " + currency.format(USD.toUsd()));
                                    System.out.println("Is there anything we can help you with? (YES) (NO)");
                                    response = sc.next();
                                    if (response.equalsIgnoreCase("Yes")) {
                                        response = "yes";
                                        System.out.println(vincent.options());
                                    } else if (response.equalsIgnoreCase("No")) {
                                        response = "no";
                                    }
                                    break;
                                case 2:
                                    System.out.println("****Converting From Canadian To Euros****");
                                    System.out.println("Please enter the amount of money you want to convert > ");
                                    amount = sc.nextInt();
                                    MoneyConversion EUR = new MoneyConversion("cad", amount, "usd");
                                    System.out.println("----Money Conversion----");
                                    System.out.println("AMOUNT:                                                 " + currency.format(amount));
                                    System.out.println("EXCHANGE RATE:                    1 Canadian Dollar = 0.65 Euro");
                                    System.out.println("CONVERSION:                                             " + currency.format(EUR.toEur()));
                                    System.out.println("Is there anything we can help you with? (YES) (NO)");
                                    response = sc.next();
                                    if (response.equalsIgnoreCase("Yes")) {
                                        response = "yes";
                                        System.out.println(vincent.options());
                                    } else if (response.equalsIgnoreCase("No")) {
                                        response = "no";
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
                            System.out.println(vincent.options());
                    }
                }
            }
        } else if (username.equals(ali.getUserName())) {
            System.out.println("Please enter your password > ");
            password = sc.next();
            while (!password.equals(ali.getPassWord())) {
                System.out.println("Please enter your password >");
                password = sc.next();
            }
            if (username.equals(ali.getUserName()) && password.equals(ali.getPassWord())) {
                System.out.println("\n" + ali);
                String response = "yes";
                while (response.equalsIgnoreCase("Yes")) {

                    System.out.print("Please enter your choice > ");
                    int number = sc.nextInt();
                    switch (number) {
                        case 1:
                            System.out.print("Enter (yes) if you want to receive $100 bills depending on your amount > ");
                            answer100 = sc.next().trim();
                            if (answer100.equalsIgnoreCase("yes")) {
                                want100 = true;
                            }

                            System.out.print("Enter (yes) if you want to receive $50 bills depending on your amount > ");
                            answer50 = sc.next().trim();
                            if (answer50.equalsIgnoreCase("yes")) {
                                want50 = true;
                            }

                            System.out.print("Please enter the amount of money you want to withdraw > ");
                            while (!sc.hasNextInt()) {
                                System.out.print("Enter a valid amount > ");
                                garbage = sc.next();
                            }
                            int amounts = sc.nextInt();
                            int withdraw = amounts;
                            int balance = ali.getMoney() - amounts;

                            while (amounts > ali.getMoney()) {
                                System.out.println("Sorry! insufficient balance");
                                System.out.print("Please Enter a new amount >");
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
                            if (response.equalsIgnoreCase("Yes")) {
                                response = "yes";
                                System.out.println(ali.options());
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
                                System.out.println(ali.bankStatement());
                            }
                            break;
                        case 2:
                            System.out.println("Please enter the amount of money you want to deposit");
                            while (!sc.hasNextInt()) {
                                sc.next();
                                System.out.print("Please enter a valid amount > ");
                            }
                            int deposit = sc.nextInt();
                            ali.setMoney((deposit + ali.getMoney()));
                            System.out.println("Deposit to Account: " + currency.format(deposit));
                            System.out.println("\nIs there anything we can help you with? (YES) (NO)");
                            response = sc.next();
                            if (response.equalsIgnoreCase("Yes")) {
                                response = "yes";
                                System.out.println(ali.options());
                            } else if (response.equalsIgnoreCase("No")) {
                                response = "no";
                                System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                        + "\n3..." + "\n2.." + "\n1.");
                                System.out.println(ali.receipt() + "\nDEPOSIT TO ACCOUNT:              " + currency.format(deposit));
                                System.out.println("\n" + "\n" + "Total Balance:                   " + currency.format(ali.getMoney()));
                                System.out.println(ali.bankStatement());
                            }
                            break;
                        case 3:
                            System.out.println("Your Current Balance is: " + currency.format(ali.getMoney()));
                            System.out.println("Is there anything we can help you with? (YES) (NO)");
                            response = sc.next();
                            if (response.equalsIgnoreCase("Yes")) {
                                response = "yes";
                                System.out.println(ali.options());
                            } else if (response.equalsIgnoreCase("no")) {
                                System.out.println("Thank You!");
                            }
                            break;
                        case 4:
                            System.out.println("Please choose an account " + "\n(1) Vincent" + "\n(2) Jeremie");
                            int accountToTransfer = sc.nextInt();
                            while (accountToTransfer != 1 && accountToTransfer != 2) {
                                System.out.println("Please choose from the following choices");
                                System.out.println("(1) Vincent" + "\n(2) Jeremie");
                                accountToTransfer = sc.nextInt();
                            }

                            if (accountToTransfer == 1) {
                                System.out.println("Please Enter the amount of money you want to transfer > ");
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
                                if (response.equalsIgnoreCase("Yes")) {
                                    response = "yes";
                                    System.out.println(ali.options());
                                } else if (response.equalsIgnoreCase("No")) {
                                    response = "no";
                                    System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                            + "\n3..." + "\n2.." + "\n1.");
                                    System.out.println(ali.receipt() + "\nTRANSFERED MONEY:                 " + currency.format(transferedMoney));
                                    System.out.println("\n" + "\n" + "Total Balance:                   " + currency.format(ali.getMoney()));
                                    System.out.println(ali.bankStatement());
                                }
                            } else if (accountToTransfer == 2) {
                                System.out.println("Please Enter the amount of money you want to transfer > ");
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
                                if (response.equalsIgnoreCase("Yes")) {
                                    response = "yes";
                                    System.out.println(ali.options());
                                } else if (response.equalsIgnoreCase("No")) {
                                    response = "no";
                                    System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                            + "\n3..." + "\n2.." + "\n1.");
                                    System.out.println(ali.receipt() + "\nTRANSFERED MONEY:                 " + currency.format(transferedMoney));
                                    System.out.println("\n" + "\n" + "Total Balance:                   " + currency.format(vincent.getMoney()));
                                    System.out.println(ali.bankStatement());
                                }
                            }
                            break;
                        case 5:
                            System.out.println("----Money Conversion----");
                            System.out.println("PLease choose for the following options: ");
                            System.out.println("(1) Convert money to US Dollars");
                            System.out.println("(2) Convert money to Euros");
                            System.out.println("\nPlease enter your choice");
                            int choice = sc.nextInt();
                            switch (choice) {
                                case 1:
                                    System.out.println("****Converting From Canadian To US Dollars****");
                                    System.out.println("Please enter the amount of money you want to convert > ");
                                    amount = sc.nextInt();
                                    MoneyConversion USD = new MoneyConversion("cad", amount, "usd");
                                    System.out.println("----Money Conversion----");
                                    System.out.println("AMOUNT:                                                 " + currency.format(amount));
                                    System.out.println("EXCHANGE RATE:    1 Canadian Dollar = 0.71 United States Dollar");
                                    System.out.println("CONVERSION:                                             " + currency.format(USD.toUsd()));
                                    System.out.println("Is there anything we can help you with? (YES) (NO)");
                                    response = sc.next();
                                    if (response.equalsIgnoreCase("Yes")) {
                                        response = "yes";
                                        System.out.println(ali.options());
                                    } else if (response.equalsIgnoreCase("No")) {
                                        response = "no";
                                    }
                                    break;
                                case 2:
                                    System.out.println("****Converting From Canadian To Euros****");
                                    System.out.println("Please enter the amount of money you want to convert > ");
                                    amount = sc.nextInt();
                                    MoneyConversion EUR = new MoneyConversion("cad", amount, "usd");
                                    System.out.println("----Money Conversion----");
                                    System.out.println("AMOUNT:                                                 " + currency.format(amount));
                                    System.out.println("EXCHANGE RATE:                    1 Canadian Dollar = 0.65 Euro");
                                    System.out.println("CONVERSION:                                             " + currency.format(EUR.toEur()));
                                    System.out.println("Is there anything we can help you with? (YES) (NO)");
                                    response = sc.next();
                                    if (response.equalsIgnoreCase("Yes")) {
                                        response = "yes";
                                        System.out.println(ali.options());
                                    } else if (response.equalsIgnoreCase("No")) {
                                        response = "no";
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
                            System.out.println(ali.options());
                    }
                }
            }
        } else if (username.equals(jeremie.getUserName())) {
            System.out.println("Please enter your password > ");
            password = sc.next();
            while (!password.equals(jeremie.getPassWord())) {
                System.out.println("Please enter your password >");
                password = sc.next();
            }
            if (username.equals(jeremie.getUserName()) && password.equals(jeremie.getPassWord())) {
                System.out.println("\n" + jeremie);
                String response = "yes";
                while (response.equalsIgnoreCase("Yes")) {

                    System.out.print("Please enter your choice > ");
                    int number = sc.nextInt();
                    switch (number) {
                        case 1:
                            System.out.print("Enter (yes) if you want to receive $100 bills depending on your amount > ");
                            answer100 = sc.next().trim();
                            if (answer100.equalsIgnoreCase("yes")) {
                                want100 = true;
                            }

                            System.out.print("Enter (yes) if you want to receive $50 bills depending on your amount > ");
                            answer50 = sc.next().trim();
                            if (answer50.equalsIgnoreCase("yes")) {
                                want50 = true;
                            }

                            System.out.print("Please enter the amount of money you want to withdraw > ");
                            while (!sc.hasNextInt()) {
                                System.out.print("Enter a valid amount > ");
                                garbage = sc.next();
                            }
                            int amounts = sc.nextInt();
                            int withdraw = amounts;
                            int balance = jeremie.getMoney() - amounts;

                            while (amounts > jeremie.getMoney()) {
                                System.out.println("Sorry! insufficient balance");
                                System.out.print("Please Enter a new amount >");
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
                        case 2:
                            System.out.println("Please enter the amount of money you want to deposit");
                            while (!sc.hasNextInt()) {
                                sc.next();
                                System.out.print("Please enter a valid amount > ");
                            }

                            int deposit = sc.nextInt();
                            jeremie.setMoney((deposit + jeremie.getMoney()));
                            System.out.println("Deposit to Account: " + currency.format(deposit));
                            System.out.println("\nIs there anything we can help you with? (YES) (NO)");
                            response = sc.next();
                            if (response.equalsIgnoreCase("Yes")) {
                                response = "yes";
                                System.out.println(jeremie.options());
                            } else if (response.equalsIgnoreCase("No")) {
                                response = "no";
                                System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                        + "\n3..." + "\n2.." + "\n1.");
                                System.out.println(jeremie.receipt() + "\nDEPOSIT TO ACCOUNT:              " + currency.format(deposit));
                                System.out.println("\n" + "\n" + "Total Balance:                   " + currency.format(jeremie.getMoney()));
                                System.out.println(jeremie.bankStatement());
                            }
                            break;
                        case 3:
                            System.out.println("Your Current Balance is: " + currency.format(jeremie.getMoney()));
                            System.out.println("Is there anything we can help you with? (YES) (NO)");
                            response = sc.next();
                            if (response.equalsIgnoreCase("Yes")) {
                                response = "yes";
                                System.out.println(jeremie.options());
                            } else if (response.equalsIgnoreCase("no")) {
                                System.out.println("Thank You!");
                            }
                            break;
                        case 4:
                            System.out.println("Please choose an account " + "\n(1) Vincent" + "\n(2) Ali");
                            int accountToTransfer = sc.nextInt();
                            while (accountToTransfer != 1 && accountToTransfer != 2) {
                                System.out.println("Please choose from the following choices");
                                System.out.println("(1) Vincent" + "\n(2) Ali");
                                accountToTransfer = sc.nextInt();
                            }
                            if (accountToTransfer == 1) {
                                System.out.println("Please Enter the amount of money you want to transfer > ");
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
                                System.out.println("Please Enter the amount of money you want to transfer > ");
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
                            System.out.println("----Money Conversion----");
                            System.out.println("PLease choose for the following options: ");
                            System.out.println("(1) Convert money to US Dollars");
                            System.out.println("(2) Convert money to Euros");
                            System.out.println("\nPlease enter your choice");
                            int choice = sc.nextInt();
                            switch (choice) {
                                case 1:
                                    System.out.println("****Converting From Canadian To US Dollars****");
                                    System.out.println("Please enter the amount of money you want to convert > ");
                                    amount = sc.nextInt();
                                    MoneyConversion USD = new MoneyConversion("cad", amount, "usd");
                                    System.out.println("----Money Conversion----");
                                    System.out.println("AMOUNT:                                                 " + currency.format(amount));
                                    System.out.println("EXCHANGE RATE:    1 Canadian Dollar = 0.71 United States Dollar");
                                    System.out.println("CONVERSION:                                             " + currency.format(USD.toUsd()));
                                    System.out.println("Is there anything we can help you with? (YES) (NO)");
                                    response = sc.next();
                                    if (response.equalsIgnoreCase("Yes")) {
                                        response = "yes";
                                        System.out.println(jeremie.options());
                                    } else if (response.equalsIgnoreCase("No")) {
                                        response = "no";
                                    }
                                    break;
                                case 2:
                                    System.out.println("****Converting From Canadian To Euros****");
                                    System.out.println("Please enter the amount of money you want to convert > ");
                                    amount = sc.nextInt();
                                    MoneyConversion EUR = new MoneyConversion("cad", amount, "usd");
                                    System.out.println("----Money Conversion----");
                                    System.out.println("AMOUNT:                                                 " + currency.format(amount));
                                    System.out.println("EXCHANGE RATE:                    1 Canadian Dollar = 0.65 Euro");
                                    System.out.println("CONVERSION:                                             " + currency.format(EUR.toEur()));
                                    System.out.println("Is there anything we can help you with? (YES) (NO)");
                                    response = sc.next();
                                    if (response.equalsIgnoreCase("Yes")) {
                                        response = "yes";
                                        System.out.println(jeremie.options());
                                    } else if (response.equalsIgnoreCase("No")) {
                                        response = "no";
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
                                            while(!sc.hasNextInt()) {
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
                                    case 2: System.out.println(".::You've selected Jeremie's account::.");
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
                                            while(!sc.hasNextInt()) {
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
                                    case 3: System.out.println(".::You've selected Vincent's account::.");
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
                                            while(!sc.hasNextInt()) {
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
                                    case 4: System.out.println(".::You've selected all the accounts::.");
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
                                            while(!sc.hasNextInt()) {
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
                    System.out.println("Goodbye.");
                }
            }
        }
    }
}