package bancomat;


import org.junit.Test;
import org.junit.runner.RunWith;
import sft.SimpleFunctionalTest;
import sft.Text;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/*
As an Account Holder  <br/>
I want to withdraw cash from an ATM <br/>
So that I can get money when the bank is closed
*/
@RunWith(SimpleFunctionalTest.class)
public class AccountHolderWithdrawCash {

    private SessionDab atmSession;
    private int withdrawals;
    private Bank bank;
    private User user;
    private Account account;
    private Atm atm;

    @Test
    public void accountHasSufficientFunds() {
        givenTheAccountBalanceIs100Dollars();
        andTheCardIsValid();
        andTheMachineContainsEnoughMoney();

        whenTheAccountHolderRequests20Dollars();

        thenTheAtmShouldDispense20Dollars();
        andTheAccountBalanceShouldBe80Dollars();
        andTheCardShouldBeReturned();
    }

    @Test
    public void accountHasInSufficientFunds(){
        givenTheAccountBalanceIs10Dollars();
        andTheCardIsValid();
        andTheMachineContainsEnoughMoney();

        whenTheAccountHolderRequests20Dollars();

        thenTheAtmShouldNotDispenseAnyMoney();
        andTheAtmShouldDisplayInsufficientFunds();
        andTheAccountBalanceShouldBe10Dollars();
        andTheCardShouldBeReturned();
    }

    @Test
    public void  cardHasBeenDisabled(){
        givenTheCardIsDisabled();
        whenTheAccountHolderRequests20Dollars();
        thenTheAtmShouldRetainTheCard();
        andTheAtmShouldDisplayTheCardHasBeenRetained();
    }

    private void andTheCardShouldBeReturned() {
        assertTrue("Card not returned", atm.returnCard());
    }

    @Text("And the account balance should be 80$")
    private void andTheAccountBalanceShouldBe80Dollars() {
        assertEquals(account.balance(), 80);
    }
    @Text("And the account balance should be 10$")
    private void andTheAccountBalanceShouldBe10Dollars() {
        assertEquals(account.balance(), 10);
    }

    @Text("Then the atm should dispense 20$")
    private void thenTheAtmShouldDispense20Dollars() {
        assertEquals(withdrawals, 20);
    }

    private void thenTheAtmShouldRetainTheCard() {
        assertFalse("Card returned", atm.returnCard());
    }

    private void andTheAtmShouldDisplayInsufficientFunds() {
        assertEquals(atm.getDisplay(), "Insufficient funds");
    }

    private void andTheAtmShouldDisplayTheCardHasBeenRetained() {
        assertEquals(atm.getDisplay(), "The card has been retained");
    }
    @Text("When the account holder requests 20$")
    private void whenTheAccountHolderRequests20Dollars() {
        atmSession = atm.authenticate(user);
        withdrawals = atmSession.withdraw(20);
    }

    private void andTheMachineContainsEnoughMoney() {
        atm = bank.getAtm(1000);
    }

    private void andTheCardIsValid() {
        account.addValidCreditCard("1234");
    }

    @Text("Given the account balance is 100$")
    private void givenTheAccountBalanceIs100Dollars() {
        bank = new Bank();
        user = new User();
        account = bank.createAccount(user, 100);
    }

    @Text("Given the account balance is 10$")
    private void givenTheAccountBalanceIs10Dollars() {
        bank = new Bank();
        user = new User();
        account = bank.createAccount(user, 10);
    }

    private void givenTheCardIsDisabled() {
        account.addValidCreditCard("1234");
        account.declareCardLoss();
    }

    private void thenTheAtmShouldNotDispenseAnyMoney() {
        assertEquals(withdrawals,0);
    }

}
