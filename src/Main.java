import java.util.Scanner;
import java.util.ArrayList;
public class Main {

    static String firstname;
    static String lastname;
    static AccountType accounttype;
    static float depositamount;
    static Scanner input = new Scanner(System.in);
    static int accountno = 0;
    static Boolean returntomainmenu = true;
    static ArrayList <Account> accountsarray = new ArrayList<Account>();
    static float overdraftlimit;
    static boolean withdrawcheck =false;

    public static void main(String[] args) throws AccountWithdrawalException {
        boolean accountNoCheck = false;
        do{
        do{
            System.out.println("MAIN MENU");
            System.out.println("Please select either 1, 2, 3 or 4:");
            System.out.println("1. Create account");
            System.out.println("2. List Accounts");
            System.out.println("3. Deposit/Withdraw");
            System.out.println("4. Exit");
            System.out.println();
            int choice = input.nextInt();
            input.nextLine();
            //repeats until a valid number choice is entered
            switch (choice) {
                case 1:
                    //ensures a firstname and lastname are entered before moving on
                    boolean repeataccountentry = true;
                    do {
                        boolean firstnamecheck = false;
                        do{
                            System.out.print("Please enter First Name: ");
                            firstname = input.nextLine();
                            if (firstname.length()>0){
                                firstnamecheck=true;
                            }
                        } while (firstnamecheck==false);
                        boolean lastnamecheck = false;
                        do{
                            System.out.print("Please enter Last Name: ");
                            lastname = input.nextLine();
                            if (lastname.length()>0){
                                lastnamecheck=true;
                            }
                        } while (lastnamecheck==false);

                        boolean accounttypecheck = false;
                        do{
                            System.out.println("Please enter Account Type (1,2 or 3)");
                            System.out.println("1. Standard");
                            System.out.println("2. Saver");
                            System.out.println("3. Premium");
                            int AccountT = input.nextInt();
                            input.nextLine();
                            switch (AccountT){
                                //selects account type from enum and sets overdraftlimit
                                case 1:
                                    accounttype= AccountType.Standard;
                                    accounttypecheck=true;
                                    System.out.println("Your overdraft amount is 500.");
                                    overdraftlimit=500.0f;
                                    System.out.println(accounttype);
                                    break;
                                case 2:
                                    accounttype=AccountType.Saver;
                                    accounttypecheck=true;
                                    System.out.println("Your overdraft amount is 0.");
                                    overdraftlimit=0.0f;
                                    System.out.println(accounttype);
                                    break;
                                case 3:
                                    accounttype=AccountType.Premium;
                                    accounttypecheck=true;
                                    System.out.println("Your overdraft amount is 3000.");
                                    overdraftlimit=3000f;
                                    System.out.println(accounttype);
                                    break;
                                default:
                                    System.out.println("Invalid option.");
                                    break;
                            }
                        }  while (accounttypecheck==false);

                        boolean depositcheck=false;
                        do {
                            //increments so that accountno starts from 1 not 0
                            accountno+=1;
                            System.out.println("Do you want to make an initial deposit: ");
                            System.out.println("1. Yes ");
                            System.out.println("2. No ");
                            int DepositChoice = input.nextInt();
                            input.nextLine();

                            switch (DepositChoice){
                                case 1:
                                    System.out.println("How much do you wish to deposit?: ");
                                    depositamount = input.nextFloat() ;
                                    input.nextLine();
                                    depositcheck=false;
                                    break;
                                case 2:
                                    //if no deposit is entered amount is set to 0
                                    depositamount = 0;
                                    depositcheck=false;
                                    break;
                                default:
                                    System.out.println("Invalid choice.");
                                    break;
                            }
                        }while (depositcheck==true);

                        //makes account and allows user to either return to main menu or create new account
                        makeAccount();
                        System.out.println("Your account has been created.");
                        System.out.println("Would you like to create a new account?: ");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        int repeatdec = input.nextInt();
                        input.nextLine();
                        switch(repeatdec) {
                            case 1:
                                break;
                            case 2:
                                repeataccountentry=false;
                                break;
                            default:
                                System.out.println("Invalid choice.");
                                break;
                            }
                       } while (repeataccountentry==true);
                    break;


                case 2:
                  DisplayAccounts();
                  break;

                case 3:
                    //Checks account no is valid; if not user is returned to main menu
                        System.out.println("Please enter your account number: ");
                        int accountno = input.nextInt();
                        input.nextLine();
                        if (accountno<=accountsarray.size()){
                            System.out.print("Valid entry. Using account.");
                            //Checks each account systematically for matching accountno and uses matching account
                            for (Account i : accountsarray){
                                if (i.accountno == accountno){
                                    System.out.println("Please select an option:");
                                    System.out.println("1. Deposit");
                                    System.out.println("2. Withdraw");
                                    int depositwithdrawalhoice=input.nextInt();
                                    input.nextLine();
                                    switch (depositwithdrawalhoice){
                                        case 1:
                                            System.out.println("How much do you wish to deposit?");
                                            float depositinput=input.nextFloat();
                                            input.nextLine();
                                            float finalamount= i.addFunds(depositinput);
                                            i.updateSavings(finalamount);
                                            System.out.println("Your balance is now:"+i.getSavings());
                                            break;
                                        case 2:
                                            do{
                                            System.out.println("How much would you like to withdraw?: ");
                                            float withdrawalamount = input.nextInt();
                                            input.nextLine();
                                                //ensures withdrawal amount is £10000 or less
                                                if (withdrawalamount >10000){
                                                   System.out.println("Cannot withdraw more than £10,000 at once.");
                                                }
                                                else {
                                                    try {
                                                        System.out.println("Your funds are currently: "+i.withdraw(accounttype, withdrawalamount));
                                                    }
                                                    catch (Exception AccountWithdrawalException){
                                                        System.out.println(AccountWithdrawalException.getMessage());
                                                    }
                                                }
                                             } while (withdrawcheck ==false);
                                            break;
                                }
                              }
                            }
                          accountNoCheck=true;
                        }
                        else{
                            System.out.print("Invalid Entry.");
                            break;
                        }

                case 4:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please enter 1, 2, 3 or 4.");
                    break;
            }
            System.out.println("Returning to Main Menu.");

            } while(accountNoCheck==false);
        } while (returntomainmenu==true);
    }

    public static void makeAccount(){
        Account user_account = new Account(accountno, firstname, lastname, accounttype, depositamount, overdraftlimit);
        accountsarray.add(user_account);
    }

    public void updateSavings(float depositamount){
        updateSavings(depositamount);
    }

    public static void DisplayAccounts() {
        for(Account account : accountsarray){
            System.out.println();
            System.out.println(account.getDescription());
        }
    }

   }




