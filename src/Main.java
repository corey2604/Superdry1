import com.sun.org.apache.xerces.internal.impl.xs.models.XSCMRepeatingLeaf;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
public class Main {

    static String FirstName;
    static String LastName;
    static AccountType AccountText;
    static float DepositAmount;
    static Boolean DepositEntered = false;
    static Scanner input = new Scanner(System.in);
    static int AccountNo = 0;
    static Boolean Repeat = false;
    static Boolean Tasks = false;
    static ArrayList <Account> Accounts = new ArrayList<Account>();


    public static void main(String[] args) {
    do{
    System.out.println("Please select either 1, 2, 3 or 4:");
    System.out.println("1. Create account");
    System.out.println("2. List Accounts");
    System.out.println("3. Deposit/Withdraw");
    System.out.println("4. Exit");
    System.out.println();
        int choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1:
                do {
                Repeat = false;
                boolean FirstCheck = false;
                do{
            System.out.print("Please enter First Name: ");
            FirstName = input.nextLine();
                    if (FirstName.length()>0){
                        FirstCheck=true;
                    }
                } while (FirstCheck==false);
                boolean LastCheck = false;
                do{
            System.out.print("Please enter Last Name: ");
            LastName = input.nextLine();
                    if (LastName.length()>0){
                        LastCheck=true;
                    }
                } while (LastCheck==false);
                boolean AccountCheck = false;
                do{
            System.out.println("Please enter Account Type (1,2 or 3");
            System.out.println("1. Standard");
            System.out.println("2. Saver");
            System.out.println("3. Premium");
           int AccountT = input.nextInt();
                input.nextLine();
            switch (AccountT){
                    case 1:
                    AccountText= AccountType.Standard;
                        AccountCheck=true;
                        System.out.println("Your overdraft amount is 500.");
                        System.out.println(AccountText);
                        break;
                case 2:
                    AccountText=AccountType.Saver;
                        AccountCheck=true;
                        System.out.println("Your overdraft amount is 0.");
                        System.out.println(AccountText);
                        break;
                case 3:
                    AccountText=AccountType.Premium;
                        AccountCheck=true;
                    System.out.println("Your overdraft amount is 3000.");
                    System.out.println(AccountText);
                        break;
                default:
                    System.out.println("Invalid option.");
                    break;
                }
            }  while (AccountCheck==false);
                boolean DepositCheck=false;
                do {
                AccountNo+=1;
                System.out.println("Do you want to make an initial deposit: ");
                System.out.println("1. Yes ");
                System.out.println("2. No ");
                   int DepositChoice = input.nextInt();
                    input.nextLine();
                switch (DepositChoice){
                    case 1:
                        System.out.println("How much do you wish to deposit?: ");
                        DepositAmount = input.nextFloat() ;
                        input.nextLine();
                        DepositCheck=false;
                        break;
                    case 2:
                        DepositCheck=false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                    }
                }while (DepositCheck==true);
            makeAccount();
            System.out.println("Your account has been created.");
            System.out.println("Would you like to create a new account?: ");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int repeatdec = input.nextInt();
            input.nextLine();
            switch(repeatdec) {
                case 1:
                    Repeat=true;
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (Repeat==true);
                break;


            case 2:
            for(Account values : Accounts){
                System.out.println("<"+values.AccountNo+"> ("+values.AccountT+ " account) <"+values.FirstName+"> <:"+values.Surname+">");
                System.out.println();
            }
                break;

            case 3:
            boolean AccountNoCheck = false;
            do {
            System.out.println("Please enter your account number: ");
            int AccountNo = input.nextInt();
                input.nextLine();
            if (AccountNo<=Accounts.size()){
               System.out.print("Valid entry. Using account.");
                for (int i=0; i>=Accounts.size(); i++){

                }

               AccountNoCheck=true;
            }
                else{
                System.out.print("Invalid Entry.");
             }
            }while(AccountNoCheck==false);

            System.out.println("Please select an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
             int DWChoice=input.nextInt();
                input.nextLine();
                switch (DWChoice){
                    case 1:
                        System.out.println("How much do you wish to deposit?");
                        float DepInput=input.nextFloat();
                        input.nextLine();
                        float FinalAmount=DepositAmount+DepInput;
                        Account.updateSavings(FinalAmount);
                        System.out.println("Your balance is now:"+Account.getSavings(AccountNo));
                        break;

                    case 2:
                        break;
                }

            case 4:
                System.exit(0);
                break;

            default:
            System.out.println("Invalid option. Please enter 1 or 2.");
                break;
        }
         //System.out.println("Your account number is " + Account.getAccountDetails(AccountNo));
        System.out.println("Returning to initial options.");
    } while (Tasks==false);

        boolean OverdraftCheck = false;
        do{
        System.out.println("Would you like to make an overdraft?");
        System.out.println("1. Yes ");
        System.out.println("2. No ");
        int OverdraftChoice = input.nextInt();
        input.nextLine();
            switch(OverdraftChoice){
                case 1:
                    System.out.println("How much would you like to withdraw?: ");
                    float OverdraftAmount = input.nextInt();
                    OverdraftCheck=true;
                    break;
                case 2:
                   OverdraftCheck=true;
                   System.exit(0);
                   break;
            }
        }while (OverdraftCheck == false);

        }
    public static void makeAccount(){
    Account UserAccount = new Account(AccountNo, FirstName, LastName, AccountText, DepositAmount);
    Accounts.add(UserAccount);
    }

    public void updateSavings(float DepositAmount){
    updateSavings(DepositAmount);
    }
}


