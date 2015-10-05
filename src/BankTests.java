import org.junit.Test;
import org.junit.Assert;

public class BankTests {
    @Test
    public void ViewAccount_DisplaysAcountnoNameAccounttypeandFunds(){
       Account a = new Account(1, "John", "Smith", AccountType.Standard, 500, 200);
       Assert.assertEquals("1 (Standard)- John Smith - £500.0", a.getDescription());
    }

    @Test
    public void GetSavings_ReturnsCorrectAmount(){
        Account a = new Account(1, "John", "Smith", AccountType.Standard, 500, 500);
        Assert.assertEquals(500, a.getSavings(), 0);
    }

    @Test
    public void addFunds_DepositAddedtoSavings(){
        Account a = new Account(1, "John", "Smith", AccountType.Standard, 500, 500);
        Assert.assertEquals(800f, a.addFunds(300f), 0);
    }

    @Test
    public void updateSavings_SavingsShouldEqualFinalAmount(){
        Account a = new Account(1, "John", "Smith", AccountType.Standard, 500, 500);
        float finalamount=800f;
        a.updateSavings(finalamount);
        Assert.assertEquals(800f, a.getSavings(), 0);
    }

    @Test
    public void withdrawValidAmountforStandard_ShouldbeAllowed(){
        Account a = new Account(1, "John", "Smith", AccountType.Standard, 500, 200);
        AccountType accounttype = AccountType.Standard;
        float withdrawalamount = 200f;
        try {
        Assert.assertEquals(300f, a.withdraw(accounttype, withdrawalamount), 0);
        }
        catch (Exception AccountWithdrawalException){
            Assert.fail(AccountWithdrawalException.getMessage());
        }
    }

    @Test
    public void withdrawValidAmountforPremium_ShouldbeAllowed(){
        Account a = new Account(1, "John", "Smith", AccountType.Premium, 500, 3000);
        AccountType accounttype = AccountType.Premium;
        float withdrawalamount = 2000f;
        try {
            Assert.assertEquals(-1500f, a.withdraw(accounttype,withdrawalamount), 0);
        }
        catch (Exception AccountWithdrawalException){
            Assert.fail(AccountWithdrawalException.getMessage());
        }
    }

    @Test
     public void withdrawInValidAmount_ShouldThrowException(){
        Account a = new Account(1, "John", "Smith", AccountType.Standard, 500, 200);
        float withdrawalamount = 800f;
        AccountType accounttype = AccountType.Standard;
        try{
           a.withdraw(accounttype, withdrawalamount);
           Assert.fail("Should not be able to withdraw this amount of funds with this account.");
        }
        catch (Exception AccountWithdrawException) {
            Assert.assertEquals("Insufficient funds",AccountWithdrawException.getMessage());
        }
    }

    @Test
    public void withdrawValidAmountforSaver_ShouldbeAllowedwith£1Deducted(){
        Account a = new Account(1, "John", "Smith", AccountType.Saver, 500, 0);
        AccountType accounttype = AccountType.Saver;
        float withdrawalamount = 200f;
        try {
            Assert.assertEquals(299f, a.withdraw(accounttype, withdrawalamount), 0);
        }
        catch (Exception AccountWithdrawalException){
            Assert.fail(AccountWithdrawalException.getMessage());
        }
    }

    @Test
    public void withdrawExactAmountAllowedWith£1Deducted_WithSaverAccount_ShouldbeAllowed(){
        Account a = new Account(1, "John", "Smith", AccountType.Saver, 500, 0);
        AccountType accounttype = AccountType.Saver;
        float withdrawalamount = 499f;
        try {
            Assert.assertEquals(0, a.withdraw(accounttype, withdrawalamount), 0);
        }
        catch (Exception AccountWithdrawalException){
            Assert.fail(AccountWithdrawalException.getMessage());
        }
    }

    @Test
    public void withdrawAmountJustOverAllowed_WithSaverAccount_ShouldNotBeAllowed(){
        Account a = new Account(1, "John", "Smith", AccountType.Saver, 500, 0);
        AccountType accounttype = AccountType.Saver;
        float withdrawalamount = 499.01f;
        try {
            a.withdraw(accounttype, withdrawalamount);
            Assert.fail("Should have prevented withdrawal after £1 deduction");
        }
        catch (Exception AccountWithdrawalException){
            Assert.assertEquals("Insufficient funds", AccountWithdrawalException.getMessage());
        }
    }

        @Test
    public void withdrawjustUnderValidAmountforPremium_ShouldbeAllowed(){
        Account a = new Account(1, "John", "Smith", AccountType.Premium, 500, 3000);
        AccountType accounttype = AccountType.Premium;
        float withdrawalamount = 3499.99f;
        try {
            Assert.assertEquals(-2999.99f, a.withdraw(accounttype,withdrawalamount), 0);
        }
        catch (Exception AccountWithdrawalException){
            Assert.fail(AccountWithdrawalException.getMessage());
        }
    }

    @Test
    public void withdrawjustOverValidAmountforPremium_ShouldNotbeAllowed(){
        Account a = new Account(1, "John", "Smith", AccountType.Premium, 500, 3000);
        AccountType accounttype = AccountType.Premium;
        float withdrawalamount = 3500.99f;
        try {
            a.withdraw(accounttype,withdrawalamount);
            Assert.fail("This exceeds the overdraft limit.");
        }
        catch (Exception AccountWithdrawalException){
            Assert.assertEquals("Insufficient funds", AccountWithdrawalException.getMessage());
        }
    }

    @Test
    public void withdrawjustUnderValidAmountforStandard_ShouldbeAllowed(){
        Account a = new Account(1, "John", "Smith", AccountType.Premium, 500, 500);
        AccountType accounttype = AccountType.Premium;
        float withdrawalamount = 999.99f;
        try {
            Assert.assertEquals(-499.99f, a.withdraw(accounttype,withdrawalamount), 0);
        }
        catch (Exception AccountWithdrawalException){
            Assert.fail(AccountWithdrawalException.getMessage());
        }
    }

    @Test
    public void withdrawjustOverValidAmountforStandard_ShouldNotbeAllowed(){
        Account a = new Account(1, "John", "Smith", AccountType.Premium, 500, 500);
        AccountType accounttype = AccountType.Premium;
        float withdrawalamount = 1000.99f;
        try {
            a.withdraw(accounttype,withdrawalamount);
            Assert.fail("This exceeds the overdraft limit.");
        }
        catch (Exception AccountWithdrawalException){
            Assert.assertEquals("Insufficient funds", AccountWithdrawalException.getMessage());
        }
    }


}


