package atm;

/**
 * A class encapsulating the concept of an Admin account with attributes user,
 * password and balance. It is an administrative interface. It gives the option
 * to view all accounts registered, specifics about the ATM machine and tests
 * the loyalty of all accounts. It includes static methods, methods and
 * constructors.
 *
 * @author Jeremie Guerchon
 */
public class AdminAccount {

    private static String user = "admin";
    private static String pass = "password";
    private static int bal;

    /**
     * Class constructor for admin account with 3 arguments.
     *
     * @param user
     * @param pass
     * @param bal
     */
    public AdminAccount(String user, String pass, int bal) {
        this.user = user;
        this.pass = pass;
        this.bal = bal;
    }

    /**
     * Method to return the user ID for the admin account.
     *
     * @return
     */
    public static String getUser() {
        return user;
    }

    /**
     * Method to return the user password for the admin account.
     *
     * @return
     */
    public static String getPass() {
        return pass;
    }

    /**
     * Method to set password to something else.
     *
     * @param pass
     * @return
     */
    public AdminAccount setPass(String pass) {
        this.pass = pass;
        return this;
    }

    /**
     *
     * @return
     */
    public static int getBal() {
        return bal;
    }

    public void setMoney(int i) {
    }

    /**
     * toString method for the interface for the AdminAccount class.
     *
     * @return
     */
    @Override
    public String toString() {
        return "-------------------------------------"
                + "\n****ADMIN CONTROLS****"
                + "\nPress (1) to view all accounts registered to bank"
                + "\nPress (2) for specifics about the machine"
                + "\nPress (3) for loyality deposits/removals";
    }

    /**
     * equals method of the AdminAccount class.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AdminAccount)) {
            return false;
        } else {
            AdminAccount objAd = (AdminAccount) o;
            if (user.equals(objAd.user) && pass.equals(objAd.pass)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
