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
    float InitialSavings;

    public Account (int AccountNo, String FirstName,String Surname, AccountType AccountT){
        this.AccountNo=AccountNo;
        this.FirstName=FirstName;
        this.Surname=Surname;
        this.AccountT=AccountT;
    }
    public Account (int AccountNo, String FirstName,String Surname, AccountType AccountT, float InitialSavings){
        this.AccountNo=AccountNo;
        this.FirstName=FirstName;
        this.Surname=Surname;
        this.AccountT=AccountT;
        this.InitialSavings=InitialSavings;
    }

    public static int getAccountDetails(int AccountNo){
        return AccountNo;
    }
   /* public void getWithdrawal(int WithdrawalAmount) throws Exception
        if(Withdrawal>=) */

   }
