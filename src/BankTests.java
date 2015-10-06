import org.junit.Test;
import org.junit.Assert;

public class BankTests {
    @Test
    public void ViewAccount_DisplaysAcountnoNameAccounttypeandFunds(){
       AccountType accounttype = AccountType.Standard;
       Account a = new Account(1, "John", "Smith", accounttype, 500, accounttype.getoverdraftlimit());
       Assert.assertEquals("1 (Standard)- John Smith - £500.0", a.getDescription());
    }

    @Test
    public void GetSavings_ReturnsCorrectAmount(){
        AccountType accounttype = AccountType.Standard;
        Account a = new Account(1, "John", "Smith", accounttype, 500, accounttype.getoverdraftlimit());
        Assert.assertEquals(500, a.getSavings(), 0);
    }

    @Test
    public void addFunds_DepositAddedtoSavings(){
        AccountType accounttype = AccountType.Standard;
        Account a = new Account(1, "John", "Smith", AccountType.Standard, 500, accounttype.getoverdraftlimit());
        Assert.assertEquals(800f, a.addFunds(300f), 0);
    }

    @Test
    public void updateSavings_SavingsShouldEqualFinalAmount(){
        AccountType accounttype = AccountType.Standard;
        Account a = new Account(1, "John", "Smith", AccountType.Standard, 500, accounttype.getoverdraftlimit());
        float finalamount=800f;
        a.updateSavings(finalamount);
        Assert.assertEquals(800f, a.getSavings(), 0);
    }

    @Test
    public void withdrawValidAmountforStandard_ShouldbeAllowed(){
        AccountType accounttype = AccountType.Standard;
        Account a = new Account(1, "John", "Smith", AccountType.Standard, 500, accounttype.getoverdraftlimit());
        float withdrawalamount = 200f;
        try {
        Assert.assertEquals(300f, a.withdraw(withdrawalamount), 0);
        }
        catch (Exception AccountWithdrawalException){
            Assert.fail(AccountWithdrawalException.getMessage());
        }
    }

    @Test
    public void withdrawValidAmountforPremium_ShouldbeAllowed(){
        AccountType accounttype = AccountType.Premium;
        Account a = new Account(1, "John", "Smith", AccountType.Premium, 500, accounttype.getoverdraftlimit());
        float withdrawalamount = 2000f;
        try {
            Assert.assertEquals(-1500f, a.withdraw(withdrawalamount), 0);
        }
        catch (Exception AccountWithdrawalException){
            Assert.fail(AccountWithdrawalException.getMessage());
        }
    }

    @Test
     public void withdrawInValidAmount_ShouldThrowException(){
        AccountType accounttype = AccountType.Standard;
        Account a = new Account(1, "John", "Smith", AccountType.Standard, 500, accounttype.getoverdraftlimit());
        float withdrawalamount = 800f;
        try{
           a.withdraw(withdrawalamount);
           Assert.fail("Should not be able to withdraw this amount of funds with this account.");
        }
        catch (Exception AccountWithdrawException) {
            Assert.assertEquals("Insufficient funds",AccountWithdrawException.getMessage());
        }
    }

    @Test
    public void withdrawValidAmountforSaver_ShouldbeAllowedwithChargeDeducted(){
        AccountType accounttype = AccountType.Saver;
        Account a = new Account(1, "John", "Smith", AccountType.Saver, 500, accounttype.getoverdraftlimit());
        float withdrawalamount = 200f;
        try {
            Assert.assertEquals(299f, a.withdraw(withdrawalamount), 0);
        }
        catch (Exception AccountWithdrawalException){
            Assert.fail(AccountWithdrawalException.getMessage());
        }
    }

    @Test
    public void withdrawExactAmountAllowedWithChargeDeducted_WithSaverAccount_ShouldbeAllowed(){
        AccountType accounttype = AccountType.Saver;
        Account a = new Account(1, "John", "Smith", AccountType.Saver, 500, accounttype.getoverdraftlimit());
        float withdrawalamount = 499f;
        try {
            Assert.assertEquals(0, a.withdraw(withdrawalamount), 0);
        }
        catch (Exception AccountWithdrawalException){
            Assert.fail(AccountWithdrawalException.getMessage());
        }
    }

    @Test
    public void withdrawAmountJustOverAllowed_WithSaverAccount_ShouldNotBeAllowed(){
        AccountType accounttype = AccountType.Saver;
        Account a = new Account(1, "John", "Smith", AccountType.Saver, 500, accounttype.getoverdraftlimit());
        float withdrawalamount = 499.01f;
        try {
            a.withdraw(withdrawalamount);
            Assert.fail("Should have prevented withdrawal after £1 deduction");
        }
        catch (Exception AccountWithdrawalException){
            Assert.assertEquals("Insufficient funds", AccountWithdrawalException.getMessage());
        }
    }

        @Test
    public void withdrawjustUnderValidAmountforPremium_ShouldbeAllowed(){
        AccountType accounttype = AccountType.Premium;
        Account a = new Account(1, "John", "Smith", AccountType.Premium, 500, accounttype.getoverdraftlimit());
        float withdrawalamount = 3499.99f;
        try {
            Assert.assertEquals(-2999.99f, a.withdraw(withdrawalamount), 0);
        }
        catch (Exception AccountWithdrawalException){
            Assert.fail(AccountWithdrawalException.getMessage());
        }
    }

    @Test
    public void withdrawjustOverValidAmountforPremium_ShouldNotbeAllowed(){
        AccountType accounttype = AccountType.Premium;
        Account a = new Account(1, "John", "Smith", AccountType.Premium, 500, accounttype.getoverdraftlimit());
        float withdrawalamount = 3500.99f;
        try {
            a.withdraw(withdrawalamount);
            Assert.fail("This exceeds the overdraft limit.");
        }
        catch (Exception AccountWithdrawalException){
            Assert.assertEquals("Insufficient funds", AccountWithdrawalException.getMessage());
        }
    }

    @Test
    public void withdrawjustUnderValidAmountforStandard_ShouldbeAllowed(){
        AccountType accounttype = AccountType.Premium;
        Account a = new Account(1, "John", "Smith", AccountType.Premium, 500, accounttype.getoverdraftlimit());
        float withdrawalamount = 999.99f;
        try {
            Assert.assertEquals(-499.99f, a.withdraw(withdrawalamount), 0);
        }
        catch (Exception AccountWithdrawalException){
            Assert.fail(AccountWithdrawalException.getMessage());
        }
    }

    @Test
    public void withdrawjustOverValidAmountforStandard_ShouldNotbeAllowed(){
        AccountType accounttype = AccountType.Premium;
        Account a = new Account(1, "John", "Smith", AccountType.Premium, 500, accounttype.getoverdraftlimit());
        float withdrawalamount = 1000.99f;
        try {
            a.withdraw(withdrawalamount);
            Assert.fail("This exceeds the overdraft limit.");
        }
        catch (AccountWithdrawalException accountWithdrawalException){
            Assert.assertEquals("Insufficient funds", accountWithdrawalException.getMessage());
        }
    }


}


