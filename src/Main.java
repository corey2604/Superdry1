import com.sun.org.apache.xerces.internal.impl.xs.models.XSCMRepeatingLeaf;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Scanner;
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


    public static void main(String[] args) {
    System.out.println("Please select either 1, 2 or 3:");
    System.out.println("1. Create account");
    System.out.println("2. List Accounts");
    System.out.println("3. Exit");
    System.out.println();
        int choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1:
                do {
                boolean FirstCheck = false;
                do{
            System.out.print("Please enter First Name: ");
            String FirstName = input.nextLine();
                    if (FirstName.length()>0){
                        FirstCheck=true;
                    }
                } while (FirstCheck==false);
                boolean LastCheck = false;
                do{
            System.out.print("Please enter Last Name: ");
            String LastName = input.nextLine();
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
                        float DepositAmount = input.nextFloat() ;
                        input.nextLine();
                        DepositCheck=true;
                        break;
                    case 2:
                        DepositCheck=false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                    }
                }while (DepositCheck==true);
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


            case 2:
            System.exit(0);
                break;

            default:
            System.out.println("Invalid option. Please enter 1 or 2.");
                break;
        }
        makeAccount();
        System.out.println("Your account has been created.");
        System.out.println(Account.getAccountDetails(AccountNo));
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
    }


}
