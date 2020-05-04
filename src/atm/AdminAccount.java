package atm;

public class AdminAccount {
    String user;
    String pass;
    int bal;

    public AdminAccount(String newUser, String newPass, int newBal) {
        this.user = newUser;
        this.pass = newPass;
        this.bal = newBal;
    }

    public String getUser(){
        return user;
    }

    public String getPass(){
        return pass;
    }

    public Integer getBal(){
        return bal;
    }

    @Override
    public String toString(){
        return "-------------------------------------"
                + "\n****WELCOME TO YOUR BANK ACCOUNT****"
                + "\nPlease choose for the following options "
                +"\nPress (1) for Withdraw"
                + "\nPress (2) for Deposit" + "\nPress (3) for Check Balance"
                + "\nPress (4) for Transferring Money to other Account"
                + "\nPress (5) to view all accounts registered to bank";
    }
}
