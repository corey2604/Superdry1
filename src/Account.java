public class Account {
    int accountno;
    String firstname;
    String surname;
    AccountType accounttype;
    float savings;
    float overdraftlimit;

    public Account (int accountno, String firstname,String surname, AccountType accounttype, int overdraftlimit){
        this.accountno=accountno;
        this.firstname=firstname;
        this.surname=surname;
        this.accounttype=accounttype;
        this.overdraftlimit=overdraftlimit;
    }
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

    public void updateSavings(float deposit){
        savings=deposit;
    }

    public float getSavings (){
       return savings;
    }

    public float addFunds(float depositinput) {
        return savings+depositinput;
    }

    public void withdraw(float withdrawalamount) throws Exception{
        if(withdrawalamount<=(savings+overdraftlimit)){
           System.out.println("Funds removed.");
            //Removes an additional £1 if the account is a saver account
            if(overdraftlimit==0){
                System.out.println("Account is a saver account. £1 deducted.");
                savings=savings-withdrawalamount-1;
                System.out.println("Your funds are currently: "+savings);
                Main.withdrawcheck=true;
            }
            else {
                savings=savings-withdrawalamount-1;
                System.out.println("Your funds are currently: "+savings);
                Main.withdrawcheck=true;
            }
        }
        else throw new AccountWithdrawalException("Insufficient funds");
    }

   }

