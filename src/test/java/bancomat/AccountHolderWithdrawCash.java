package bancomat;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import sft.Decorate;
import sft.Displayable;
import sft.FixturesHelper;
import sft.SimpleFunctionalTest;
import sft.Text;
import sft.decorators.Group;
import sft.decorators.TableOfContent;

import static org.junit.Assert.assertEquals;


/*
As an Account Holder  <br/>
I want to withdraw cash from an ATM <br/>
So that I can get money when the bank is closed
*/
@RunWith(SimpleFunctionalTest.class)
@Decorate(decorator = TableOfContent.class)
public class AccountHolderWithdrawCash {

    public AccountHolderWithdrawCashAlternateCases accountHolderWithdrawCashAlternateCases = new AccountHolderWithdrawCashAlternateCases();
    @FixturesHelper
    private static BankHelper bankHelper = new BankHelper();
    @Displayable
    private String ticket;

    @BeforeClass
    public static void setupUseCase(){
        bankHelper.givenABank();
    }
    @Before
    public void setupScenario(){
        bankHelper.givenAClientOfThisBank();
    }

    @Test
    public void accountHasSufficientFunds() {
        bankHelper.theAccountBalanceIs(100);
        bankHelper.andTheCardIsValid();
        bankHelper.andTheMachineContainsEnoughMoney();

        bankHelper.theAccountHolderRequests(20);

        thenTheAtmShouldDispense(20);
        bankHelper.andTheAccountBalanceShouldBe(80);
        bankHelper.andTheCardShouldBeReturned();
    }

    @Decorate(decorator = Group.class,parameters = BankHelper.THEN)
    @Text("The atm should dispense  ${cash} $")
    private void thenTheAtmShouldDispense(int cash) {
        this.ticket= bankHelper.getHtmlTicket();
        assertEquals(bankHelper.withdrawals, cash);
    }
}
