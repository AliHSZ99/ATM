package atm;

public class AdminAccount {
    private static String user = "admin";
    private static String pass = "password";
    private static int  bal;

    public AdminAccount(String user, String pass, int bal) {
        this.user = user;
        this.pass = pass;
        this.bal = bal;
    }

    public static String getUser(){
        return user;
    }

    public static String getPass(){
        return pass;
    }

    public static Integer getBal(){
        return bal;
    }

    @Override
    public String toString(){
        return "-------------------------------------"
                + "\n****ADMIN CONTROLS****"
                + "\nPress (1) to view all accounts registered to bank"
                + "\nPress (2) for specifics about the machine"
                + "\nPress (3) for loyality deposits/removals";
    }

    public void setMoney(int i) {
    }
}