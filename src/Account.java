import java.util.ArrayList;

public class Account {
        int accountno;
    String firstname;
    String surname;
    AccountType accounttype;
    float savings;
    float overdraftlimit;
    static ArrayList<Account> accountsarray = new ArrayList<Account>();

    public Account (int accountno, String firstname, String surname, AccountType accounttype, float savings,float overdraftlimit){
        this.accountno=accountno;
        this.firstname=firstname;
        this.surname=surname;
        this.accounttype=accounttype;
        this.savings=savings;
        this.overdraftlimit=overdraftlimit;
    }

    public static String getAccountDetails(int accountno, String firstname, String surname, AccountType accounttype, float savings){
        return accountno+" "+firstname+" "+surname+" "+accounttype+" "+savings;
    }

    public void updateSavings(float finalamount){
        savings=finalamount;
    }

    public float getSavings (){
       return savings;
    }

    public float addFunds(float depositinput) {
        return savings+depositinput;
    }

    public float withdraw(AccountType accounttype,float withdrawalamount) throws Exception{
        if(withdrawalamount<=(savings+overdraftlimit)){
           System.out.println("Funds removed.");
            //Removes an additional £1 if the account is a saver account
            if(accounttype==AccountType.Saver){
                if(withdrawalamount<=(savings+overdraftlimit-1)){
                Main.withdrawcheck=true;
                System.out.println("Account is a saver account. £1 deducted.");
                savings=savings-withdrawalamount-1;
                return savings;
                }
                else throw new AccountWithdrawalException("Insufficient funds");
                }
            else {
                Main.withdrawcheck=true;
                savings=savings-withdrawalamount;
                return savings;
                 }
        }
        else throw new AccountWithdrawalException("Insufficient funds");
    }

    public String getDescription(){
       return (this.accountno + " " + "(" + this.accounttype + ")" + "- " +this.firstname + " "  +this.surname + " - " + "£" +this.savings);
    }

    }

