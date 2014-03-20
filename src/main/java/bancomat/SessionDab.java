package bancomat;

public class SessionDab {

    private final Account account;
    private final Atm atm;

    public SessionDab(Account account, Atm atm) {
        this.account = account;
        this.atm = atm;
    }

    public int withdraw(int amount) {
        account.withdraw(amount);
        atm.ejectCard();
        return amount;
    }
}
