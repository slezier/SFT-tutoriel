package bancomat;

public class Account {


    private int balance;
    private boolean cardIsValid = false;

    public Account(int initialAmount) {
        balance = initialAmount;
    }

    public int balance(){
        return balance;
    }

    public void addValidCreditCard(String pin) {
        cardIsValid=true;
    }

    public void withdraw(int amount) {
        this.balance -=amount;

    }


    public boolean cardIsLost() {
        return ! cardIsValid;
    }

    public void declareCardLoss() {
        cardIsValid=false;
    }
}
