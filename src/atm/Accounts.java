package atm;

/**
 * A class that creates the accounts to run in the ATM client class. It also
 * provides a nice output for user experience and interface. It includes a
 * constructor and methods.
 *
 * @author Vincent Benesen
 */
public class Accounts {

    // Instance variables
    private String username;
    private String password;
    private int money;
    private int deposit;
    private int withdraw;

    /**
     * Class constructor with three arguments/parameters.
     *
     * @param username
     * @param password
     * @param money
     */
    public Accounts(String username, String password, int money) {
        this.username = username;
        this.password = password;
        this.money = money;
    }

    /**
     * Method that returns the username of a user.
     *
     * @return
     */
    public String getUserName() {
        return username;

    }

    /**
     * Method that returns the password of a user.
     *
     * @return
     */
    public String getPassWord() {
        return password;
    }

    /**
     * Method that returns a user's budget.
     *
     * @return
     */
    public int getMoney() {
        return money;
    }

    /**
     * Mutator method to set a user total money.
     *
     * @param newMoney
     */
    public void setMoney(int newMoney) {
        money = newMoney;
    }

    /**
     * Method to display user options with nice output.
     *
     * @return
     */
    public String options() {
        return "\nPlease choose for the following options "
                + "\nPress (1) for Withdraw"
                + "\nPress (2) for Deposit"
                + "\nPress (3) for Check Balance"
                + "\nPress (4) for Transferring Money to other Account"
                + "\nPress (5) for Currency conversion";
    }

    /**
     * Method to print the user's receipt with nice output.
     *
     * @return
     */
    public String receipt() {
        return "*******************************************"
                + "\n *------------>>>J.A.V BANK<<<-----------*"
                + "\n *----------Transaction  Receipt---------*"
                + "\n*******************************************"
                + "\n-------------------------------------------";
    }

    /**
     * Method to return bank statement after receipt for nice output.
     *
     * @return
     */
    public String bankStatement() {
        return "\n-------------------------------------------"
                + "\n BANK FROM HOME, THE OFFICE, OR THE ROAD,"
                + "\n     JAV.com MAKES BANKING EASIER!       "
                + "\n          MESSAGE FROM J.A.V             "
                + "\n           YOU'RE BEAUTIFUL!             "
                + "\nVIEW ACCOUNT STATEMENTS & CHECK & PAY BILLS. "
                + "\nSET THE ATM PREFERENCES. ACTIVATE PERSONALIZED "
                + "\n        ALERTS-AT YOUR CONVENIENCE "
                + "\n" + "\n              ENROLL TODAY!\n"
                + "\n       J.A.V BANK 1-800-555-3232"
                + "\n-------------------------------------------";
    }

    /**
     * The toString method of the Accounts class.
     *
     * @return
     */
    @Override
    public String toString() {
        return "-------------------------------------"
                + "\n****WELCOME TO YOUR BANK ACCOUNT****"
                + "\nPlease choose for the following options "
                + "\nPress (1) for Withdraw"
                + "\nPress (2) for Deposit" + "\nPress (3) for Check Balance"
                + "\nPress (4) for Transferring Money to other Account"
                + "\nPress (5) for Currency conversion";
    }

    /**
     * The equals method of the account class.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Accounts)) {
            return false;
        } else {
            Accounts objAcc = (Accounts) o;
            if (username.equals(objAcc.username)
                    && password.equals(objAcc.password)) {
                return true;
            } else {
                return false;
            }
        }
    }

}
