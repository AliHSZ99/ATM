/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author Ali Zoubeidi, Vincent Benesen and Jeremie Guerchon
 */
public class ATM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Accounts vincent = new Accounts("Vincent", "Benesen", 5000);
        Accounts ali = new Accounts("Ali", "Zoubeidi", 8000);
        Accounts jeremiee = new Accounts("Jeremie", "Guerchon", 4000);
        AdminAccount jeremie = new AdminAccount("Jeremie", "Guerchon", 4000);

        DecimalFormat currency = new DecimalFormat("0.00$");
        String password;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n*******************************************");
        System.out.println("*------->>>WELCOME TO J.A.V BANK<<<-------*");
        System.out.println("*******************************************");
        System.out.println("\nPlease enter your username > ");
        String username = sc.next();

        while ((!username.equals(jeremie.getUser()) && !username.equals(vincent.getUserName()))) {
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
                                System.out.println("\nPRINTING YOUR RECEIPT ..." +"\n5....." + "\n4...." 
                                                   +"\n3..." + "\n2.." + "\n1.");
                                System.out.println(vincent.receipt()+ "\nWITHDRAWAL FROM ACCOUNT:              " +currency.format(withdraw));
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
                                System.out.println("\nPRINTING YOUR RECEIPT ..." +"\n5....." + "\n4...." 
                                                   +"\n3..." + "\n2.." + "\n1.");
                                System.out.println(vincent.receipt()+ "\nDEPOSIT TO ACCOUNT:              " +currency.format(deposit));
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
                            } else if (response.equalsIgnoreCase("no")){
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
                                System.out.println("You've transferred " + currency.format(transferedMoney) + " to Ali" );
                                vincent.setMoney(vincent.getMoney() - transferedMoney);
                                System.out.println("Is there anything else we can help you? (YES) (NO)");
                                response = sc.next();
                                if (response.equalsIgnoreCase("Yes")) {
                                     response = "yes";
                                    System.out.println(vincent.options());
                                } else if (response.equalsIgnoreCase("No")) {
                                    response = "no";
                                    System.out.println("\nPRINTING YOUR RECEIPT ..." +"\n5....." + "\n4...." 
                                                       +"\n3..." + "\n2.." + "\n1.");
                                    System.out.println(vincent.receipt()+ "\nTRANSFERED MONEY:                 " +currency.format(transferedMoney));
                                    System.out.println("\n" + "\n" + "Total Balance:                   " + currency.format(vincent.getMoney()));
                                    System.out.println(vincent.bankStatement());
                                }
                            } else if (accountToTransfer == 2) {
                                System.out.println("Please Enter the amount of money you want to transfer > ");
                                int transferedMoney = sc.nextInt();
                                jeremiee.setMoney(ali.getMoney() + transferedMoney);
                                System.out.println("You've transferred " + currency.format(transferedMoney) + " to Jeremie" );
                                vincent.setMoney(vincent.getMoney() - transferedMoney);
                                System.out.println("Is there anything else we can help you? (YES) (NO)");
                                response = sc.next();
                                if (response.equalsIgnoreCase("Yes")) {
                                    response = "yes";
                                    System.out.println(vincent.options());
                                } else if (response.equalsIgnoreCase("No")) {
                                    response = "no";
                                    System.out.println("\nPRINTING YOUR RECEIPT ..." +"\n5....." + "\n4...." 
                                                       +"\n3..." + "\n2.." + "\n1.");
                                    System.out.println(vincent.receipt()+ "\nTRANSFERED MONEY:                 " +currency.format(transferedMoney));
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
    }
}
