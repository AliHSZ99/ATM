/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

/**
 *
 * @author Vincent Benesen, Jeremie Guerchon, Ali Zoubeidi
 */
public class Accounts {

    private String username;
    private String password;
    private int money;
    private int deposit;
    private int withdraw;

    public Accounts(String username, String password, int money) {
        this.username = username;
        this.password = password;
        this.money = money;
    }

    public String getUserName() {
        return username;

    }

    public String getPassWord() {
        return password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int newMoney) {
        money = newMoney;
    }

    public String options() {
        return "\nPlease choose for the following options "
                + "\nPress (1) for Withdraw"
                + "\nPress (2) for Deposit" + "\nPress (3) for Check Balance"
                + "\nPress (4) for Transferring Money to other Account";
    }

    public String withdrawalReceipt() {
        return "*******************************************"
                + "\n *------------>>>J.A.V BANK<<<-----------*"
                + "\n *----------Transaction  Receipt---------*"
                + "\n*******************************************";
    }

    @Override
    public String toString() {
        return "-------------------------------------"
                + "\n****WELCOME TO YOUR BANK ACCOUNT****"
                + "\nPlease choose for the following options "
                + "\nPress (1) for Withdraw"
                + "\nPress (2) for Deposit" + "\nPress (3) for Check Balance"
                + "\nPress (4) for Transferring Money to other Account";
    }

}
