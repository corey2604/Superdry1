import javax.security.auth.login.AccountNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: kuser
 * Date: 01/10/15
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
public class Account {
    int AccountNo;
    String FirstName;
    String Surname;
    AccountType AccountT;
    float Savings;
    float OverDraftLimit;

    public Account (int AccountNo, String FirstName,String Surname, AccountType AccountT, int OverDraftLimit){
        this.AccountNo=AccountNo;
        this.FirstName=FirstName;
        this.Surname=Surname;
        this.AccountT=AccountT;
        this.OverDraftLimit=OverDraftLimit;
    }
    public Account (int AccountNo, String FirstName,String Surname, AccountType AccountT, float Savings,float OverDraftLimit){
        this.AccountNo=AccountNo;
        this.FirstName=FirstName;
        this.Surname=Surname;
        this.AccountT=AccountT;
        this.Savings=Savings;
        this.OverDraftLimit=OverDraftLimit;
    }

    public static String getAccountDetails(int AccountNo, String FirstName, String Surname, AccountType AccountT, float Savings){
        return AccountNo+" "+FirstName+" "+Surname+" "+AccountT+" "+Savings;
    }

    public void updateSavings(float Deposit){
        Savings=Deposit;
    }

    public float getSavings (){
       return Savings;
    }

    public float addFunds(float DepInput) {
        return Savings+DepInput;
    }

    public void withdraw(float WithdrawalAmount) throws Exception{
        if(WithdrawalAmount<=(Savings+OverDraftLimit)){
           System.out.println("Funds removed.");
           Savings-=WithdrawalAmount;
           System.out.println("Your funds are currently: "+Savings);
            Main.WithdrawCheck=true;
        }
        else throw new AccountWithdrawalException("Insufficient funds");
    }

   }

