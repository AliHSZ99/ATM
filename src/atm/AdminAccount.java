package atm;

public class AdminAccount {
    String user;
    String pass;
    int bal;

    public AdminAccount(String User, String Pass, int Bal) {
        this.user = User;
        this.pass = Pass;
        this.bal = Bal;
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
                + "\n****ADMIN CONTROLS****"
                + "\nPress (1) to view all accounts registered to bank"
                + "\nPress (2) for specifics about the machine";
    }

    public void setMoney(int i) {
    }
}