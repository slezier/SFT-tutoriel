package bancomat;

public class SessionDab {

    private final Account account;
    private final Atm atm;

    public SessionDab(Account account, Atm atm) {
        this.account = account;
        this.atm = atm;
    }

    public int withdraw(int amount) {
        final int cash ;
        if( account.cardIsLost()){
            cash = 0;
            atm.eatTheCard();
            atm.displays("The card has been retained");
        }else if( account.balance() <= amount ){
            cash = 0;
            atm.displays("insufficient funds");
        }else {
            cash = amount;
            account.withdraw(amount);
        }
        atm.ejectCard();
        return cash;
    }
}
