package bancomat;

public class Atm {

    private final Bank bank;
    private int funds;
    private SessionDab currentSession;

    public Atm(int initialFunds,Bank bank) {
        this.bank = bank;
        funds = initialFunds;
    }

    public SessionDab authenticate(User user) {
        currentSession = new SessionDab(bank.getAccount(user),this);
        return currentSession;
    }

    public boolean returnCard() {
        return currentSession == null;
    }

    public void ejectCard() {
        currentSession=null;
    }
}
