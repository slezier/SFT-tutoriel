package bancomat;

public class Atm {

    private final Bank bank;
    private int funds;
    private SessionDab currentSession;
    private SessionDab retainedCard;
    private String message;

    public Atm(int initialFunds, Bank bank) {
        this.bank = bank;
        funds = initialFunds;
    }

    public SessionDab authenticate(User user) {
        currentSession = new SessionDab(bank.getAccount(user), this);
        return currentSession;
    }

    public boolean returnCard() {
        return retainedCard == null;
    }

    public void ejectCard() {
        currentSession = null;
    }

    public String getDisplay() {
        return message;

    }

    public void displays(String text) {
        message=text;
    }

    public void eatTheCard() {
        retainedCard = currentSession;
    }
}
