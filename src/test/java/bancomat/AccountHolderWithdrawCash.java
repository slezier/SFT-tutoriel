package bancomat;


import org.junit.Test;
import org.junit.runner.RunWith;
import sft.FixturesHelper;
import sft.SimpleFunctionalTest;
import sft.Text;

import static org.junit.Assert.assertEquals;


/*
As an Account Holder  <br/>
I want to withdraw cash from an ATM <br/>
So that I can get money when the bank is closed
*/
@RunWith(SimpleFunctionalTest.class)
public class AccountHolderWithdrawCash {

    public AccountHolderWithdrawCashAlternateCases accountHolderWithdrawCashAlternateCases = new AccountHolderWithdrawCashAlternateCases();
    @FixturesHelper
    private BankHelper bankHelper = new BankHelper();

    @Test
    public void accountHasSufficientFunds() {
        bankHelper.givenTheAccountBalanceIs(100);
        bankHelper.andTheCardIsValid();
        bankHelper.andTheMachineContainsEnoughMoney();

        bankHelper.whenTheAccountHolderRequests(20);

        thenTheAtmShouldDispense(20);
        bankHelper.andTheAccountBalanceShouldBe(80);
        bankHelper.andTheCardShouldBeReturned();
    }

    @Text("Then the atm should dispense  ${cash} $")
    private void thenTheAtmShouldDispense(int cash) {
        assertEquals(bankHelper.withdrawals, cash);
    }
}
