
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

/**
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
        AdminAccount jeremie = new AdminAccount("Jeremie","Guerchon", 4000);


        DecimalFormat currency = new DecimalFormat("0.00$");
        String password = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n*******************************************");
        System.out.println("*------->>>WELCOME TO J.A.V BANK<<<-------*");
        System.out.println("*******************************************");
        System.out.println("\nPlease enter your username > ");
        String username = sc.next();

        while (!username.equals(vincent.getUserName()) && !username.equals(jeremie.getUser())) {
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

                    System.out.println("Please enter your choice > ");
                    int number = sc.nextInt();
                    switch (number) {
                        case 1:
                            System.out.println("Please enter the amount of money you want to withdraw > ");
                            int withdraw = sc.nextInt();
                            int balance = vincent.getMoney() - withdraw;
                            System.out.println("Here is your: " + currency.format(withdraw));
                            System.out.println("\nIs there anything else we can help you? (YES) (NO)");
                            response = sc.next();
                            if (response.equalsIgnoreCase("Yes")) {
                                response = "yes";
                                System.out.println(vincent.options());
                            } else if (response.equalsIgnoreCase("No")) {
                                response = "no";
                                System.out.println("\nPRINTING YOUR RECEIPT ..." + "\n5....." + "\n4...."
                                        + "\n3..." + "\n2.." + "\n1.");
                                System.out.println(vincent.receipt() + "\nWITHDRAWAL FROM ACCOUNT:              " + currency.format(withdraw));
                                System.out.println("\n" + "\n" + "Available Balance:                   " + currency.format(balance));
                                System.out.println(vincent.bankStatement());
                            }
                            break;
                        case 2:
                            System.out.println("Please enter the amount of money you want to deposit");
                            int deposit = sc.nextInt();
                            vincent.setMoney((deposit + vincent.getMoney()));
                            System.out.println("Deposit to Account: " + currency.format(deposit));
                            System.out.println("\nIs there anything else we can help you? (YES) (NO)");
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
                            System.out.println("Is there anything else we can help you? (YES) (NO)");
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
                            if (accountToTransfer == 1) {
                                System.out.println("Please Enter the amount of money you want to transfer > ");
                                int transferedMoney = sc.nextInt();
                                ali.setMoney(ali.getMoney() + transferedMoney);
                                System.out.println("You've transferred " + currency.format(transferedMoney) + " to Ali");
                                vincent.setMoney(vincent.getMoney() - transferedMoney);
                                System.out.println("Is there anything else we can help you? (YES) (NO)");
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
                                int transferedMoney = sc.nextInt();
                                jeremie.setMoney(ali.getMoney() + transferedMoney);
                                System.out.println("You've transferred " + currency.format(transferedMoney) + " to Jeremie");
                                vincent.setMoney(vincent.getMoney() - transferedMoney);
                                System.out.println("Is there anything else we can help you? (YES) (NO)");
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
                        default:
                            System.out.println(vincent.options());
                    }
                }
            }
        }
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



        String newPass = null;
        if (username.equals(jeremie.getUser())) {
            System.out.println("\n[_[-=ADMINISTRATIVE ACCOUNT .JAV 2.0491=-]_]");
            System.out.println("Welcome back Jeremie~ please enter your password");
            newPass = sc.next();
        }

        while (!newPass.equals(jeremie.getPass())) {
            System.out.println("WRONG PASSWORD ENTER AGAIN");
            newPass = sc.next();
        }

        if (username.equals(jeremie.getUser()) && newPass.equals(jeremie.getPass())) {
            String answer = "Yes";
            while (answer.equalsIgnoreCase("yes")) {
                System.out.println(jeremie);
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
                    default:

                }
                System.out.println("Goodbye.");
            }
        }
    }
}