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
        Accounts jeremie = new Accounts("Jeremie", "Guerchon", 7000);
        Accounts ali = new Accounts("Ali", "Zoubeidi", 8000);

        DecimalFormat currency = new DecimalFormat("0.00$");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your username > ");
        String username = sc.next();

        while ((!username.equals(jeremie.getUserName()) && !username.equals(vincent.getUserName()))) {
            System.out.println("INVALID USERNAME, Please enter your username");
            username = sc.next();
        }

        System.out.println("Please enter your password > ");
        String password = sc.next();

        while (!password.equals(vincent.getPassWord()) && !password.equals(jeremie.getPassWord())) {
            System.out.println("INVALID PASSWORD, Please enter your password");
            password = sc.next();
        }

        if (username.equals(vincent.getUserName()) && password.equals(vincent.getPassWord())) {
            System.out.println(vincent);
            System.out.println("Please enter your choice > ");
            int number = sc.nextInt();
            switch (number) {
                case 1:
                    System.out.println("Please enter the amount of money you want to withdraw > ");
                    int withdraw = sc.nextInt();
                    int balance = vincent.getMoney() - withdraw;
                    System.out.println("Here is your: " + currency.format(withdraw));
                    System.out.println("Your Current Balance is : " + currency.format(balance));
                    break;
                case 2:
                    System.out.println("Please enter the amount of money you want to deposit");
                    int deposit = sc.nextInt();
                    vincent.setMoney((deposit + vincent.getMoney()));
                    System.out.println("Your Current Balance is: " + currency.format(vincent.getMoney()));
                    break;
                case 3:
                    System.out.println("Your Current Balance is: " + currency.format(vincent.getMoney()));
                    break;
                case 4:
                    System.out.println("Please choose an account " + "\n(1) Ali" + "\n(2) Jeremie");
                    int accountToTransfer = sc.nextInt();
                    if (accountToTransfer == 1) {
                        System.out.println("Please Enter the amount of money you want to transfer > ");
                        int tranferedMoney = sc.nextInt();
                        ali.setMoney(ali.getMoney() + tranferedMoney);
                        vincent.setMoney(vincent.getMoney() - tranferedMoney);
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        } else if (username.equals(jeremie.getUserName()) && password.equals(jeremie.getPassWord())) {
            System.out.println(jeremie);
        }
    }
}
