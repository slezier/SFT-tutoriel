package bancomat;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import sft.*;
import sft.decorators.TableOfContent;
import sft.plugins.sequenceDiagramPlugin.SequenceDiagram;

import static org.junit.Assert.assertEquals;


/*
As an Account Holder  <br/>
I want to withdraw cash from an ATM <br/>
So that I can get money when the bank is closed
*/
@RunWith(SimpleFunctionalTest.class)
@Decorate(decorator = TableOfContent.class)
@Using(CustomConfiguration.class)
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

        bankHelper.requestCash(20);

        dispenseCash(20);
        bankHelper.andTheAccountBalanceShouldBe(80);
        bankHelper.cardIsReturned();
    }

    @Decorate(decorator = SequenceDiagram.class,parameters = "atm --> account_holder")
    @Text("dispenses ${cash} $")
    private void dispenseCash(int cash) {
        this.ticket= bankHelper.getHtmlTicket();
        assertEquals(bankHelper.withdrawals, cash);
    }
}
