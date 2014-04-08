package bancomat;

import org.junit.Test;
import sft.FixturesHelper;
import sft.Text;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AccountHolderWithdrawCashAlternateCases {

    @FixturesHelper
    private BankHelper bankHelper = new BankHelper();

    @Test
    public void accountHasInSufficientFunds(){
        bankHelper.givenTheAccountBalanceIs(10);
        bankHelper.andTheCardIsValid();
        bankHelper.andTheMachineContainsEnoughMoney();

        bankHelper.whenTheAccountHolderRequests(20);

        thenTheAtmShouldNotDispenseAnyMoney();
        andTheAtmShouldDisplay("Insufficient funds");
        bankHelper.andTheAccountBalanceShouldBe(10);
        bankHelper.andTheCardShouldBeReturned();
    }

    @Test
    public void  cardHasBeenDisabled(){
        bankHelper.givenTheAccountBalanceIs(200);
        givenTheCardIsDisabled();
        bankHelper.andTheMachineContainsEnoughMoney();
        bankHelper.whenTheAccountHolderRequests(20);
        thenTheAtmShouldRetainTheCard();
        andTheAtmShouldDisplay("The card has been retained");
    }

    private void givenTheCardIsDisabled() {
        bankHelper.account.addValidCreditCard("1234");
        bankHelper.account.declareCardLoss();
    }

    @Text("And the atm should displays \"${expectedDisplay}\"")
    private void andTheAtmShouldDisplay(String expectedDisplay) {
        assertEquals(bankHelper.atm.getDisplay(), expectedDisplay);
    }

    private void thenTheAtmShouldNotDispenseAnyMoney() {
        assertEquals(bankHelper.withdrawals,0);
    }

    private void thenTheAtmShouldRetainTheCard() {
        assertFalse("Card returned", bankHelper.atm.returnCard());
    }

}
