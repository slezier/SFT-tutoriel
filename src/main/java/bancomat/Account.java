package bancomat;

public class Account {


    private int balance;

    public Account(int initialAmount) {
        balance = initialAmount;
    }

    public int balance(){
        return balance;
    }

    public void addValidCreditCard(String pin) {
    }

    public void withdraw(int amount) {
        this.balance -=amount;

    }
}
