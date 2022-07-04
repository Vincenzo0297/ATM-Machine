import java.util.*;

class Account {
    
    //Declare Variables
    private String cardId;   
    private String userName;  
    private String passWord;  
    private double quotaMoney;  
    private double money; 

    //Declare Constructor
    public Account(String cardId, String userName, String passWord, double quotaMoney) {
        this.cardId = cardId;
        this.userName = userName;
        this.passWord = passWord;
        this.quotaMoney = quotaMoney;
    }

    //Get method
    public String getCardId() {
        return cardId;
    }  
    
    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }  
    
    public double getQuotaMoney() {
        return quotaMoney;
    }    
    
    public double getMoney() {
        return money;
    }

    //Set method
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

     public void setQuotaMoney(double quotaMoney) {
        this.quotaMoney = quotaMoney;
    }
    
    public void setMoney(double money) {
        this.money = money;
    }
}

public class Main {
    public static void main(String[] args) {
        
        ArrayList<Account> list = new ArrayList<>();
        showMain(list);
    }

    public static void showMain(ArrayList<Account> list) {
        
        System.out.println("");
        System.out.println("==================Welcome to Home Page======================");
        Scanner scan = new Scanner(System.in);
        int Choice = 1;
        do{
            System.out.println("Please enter the action you want to do:");
            System.out.println("1) Sign in");
            System.out.println("2) Open an account");
            System.out.println("3) Quit");
            System.out.printf("Select your option: ");
            int Input = scan.nextInt();
            
            switch(Input){
                case 1:
                    Login(list);
                    break;
                case 2:
                    Register(list);
                    break;
                case 3:
                    System.out.println("Bye");
                    System.exit(0);
                    break;
                default:
                    System.out.println("The command that currently entered is not supported ");
            }
            
        }while(Choice != 3);
    }
    
     public static void Register(ArrayList<Account> list) {
         
        Scanner scan = new Scanner(System.in); 
        System.out.println("");
        System.out.println("==================User Account Registration======================");
 
        System.out.printf("Enter Account name: ");
        String name = scan.nextLine();

        String passWord = " ";
        while(true) {
            
            System.out.printf("Enter Account password: ");
            passWord = scan.nextLine();
            
            System.out.printf("Enter your confirmation password: ");
            String ConfirmPassword = scan.nextLine();
            
            //whether the passwords entered twice are consistent
            if(ConfirmPassword.equals(passWord)){
                System.out.println("Password has been created");
                System.out.println(" ");
                break;
            }else{
                System.out.println("Passwords must be the same");
                System.out.println(" ");
            }
        }
            System.out.printf("Enter the current limit: ");
            double quotaMoney = scan.nextDouble();

            //Generate the card number of the 8 digits and cannot be duplicated with other account card numbers
            String cardId = createCardId(list);

            Account test1 = new Account(cardId,name,passWord,quotaMoney);
            list.add(test1);
            
            System.out.println("Account has been successfully created. The card number is: " + test1.getCardId());
            System.out.println(" ");
    }

    public static String createCardId(ArrayList<Account> list){
        while (true) {
            //Generate 8-digit random number to represent the card number
            String cardId = "";
            Random rand = new Random();
            for (int i = 0; i < 8; i++) {
                cardId += rand.nextInt(10);
            }

            //Whether the card number is repeated
            Account test2 = getAccountByCardId(cardId, list);
            if(test2 == null){
                //Indicates that the current card number is not duplicate
                return cardId;
            }
        }
    }

    public static Account getAccountByCardId(String cardId, ArrayList<Account> list) {
        //Query account object according to card number
        for (int i = 0; i < list.size(); i++) {
            Account test3 = list.get(i);
            if(test3.getCardId().equals(cardId)){
                return test3;
            }
        }
        return null; //If no such account, it means that the card number is not repeated
    }

    public static void Login(ArrayList<Account> list) {
        
        System.out.println("");
        System.out.println("==================User login function======================");
        Scanner scan = new Scanner(System.in);
        
        //Must have an account in the system to log in
        if(list.size()==0) {
            System.out.println("There is no account in the current system. You need to register first!");
            System.out.println(" ");
            return;   //Directly end the execution of the method
        }

        //User enter the card number and query according to the card number
        while(true) {
            System.out.printf("Enter login card number: ");
            String cardId = scan.nextLine();
            
            //Query account object according to card number
            Account test4 = getAccountByCardId(cardId, list);

            //Whether the account object exists, which indicates that the card number is OK
            if(test4 != null) {
                while (true) {

                    System.out.printf("Enter login password: ");
                    String password = scan.nextLine();
                    //whether the password is correct
                    if(test4.getPassWord().equals(password)){
                        //The password is correct and the login is successful
                        //Display the operation interface after system login
                        System.out.println(test4.getUserName() + " has been successfully entered the system,Your card number is: " + test4.getCardId());
                       
                        //Display operation page
                        printAll(test4, list);
                        return;  //Continue end login method
                    }
                    else {
                        System.out.println("Password is incorrect");
                    }
                }
            }
            else {
                System.out.println("Sorry, there is no account with this card number!");
            }
        }
    }

    public static void printAll(Account acc, ArrayList<Account> list) {
        Scanner scan = new Scanner(System.in);
        int Choice = 1;
        
        do {
            System.out.println("");
            System.out.println("==================User interface======================");
            System.out.println("1) Query");
            System.out.println("2) Deposit");
            System.out.println("3) Withdraw money");
            System.out.println("4) Transfer accounts");
            System.out.println("5) Change Password");
            System.out.println("6) sign out");
            System.out.printf("Please enter the operation command: ");
            int Input = scan.nextInt();
            
                switch (Input) {
                    case 1:
                        //Query account
                        ShowAccount(acc);
                        break;
                    case 2:
                        //deposit
                        DepositMoney(acc);
                        break;
                    case 3:
                        //withdraw money
                        DrawMoney(acc);
                        break;
                    case 4:
                        //transfer accounts
                       TransferMoney(acc, list);
                        break;
                    case 5:
                        //Change Password
                      updatePassWord(acc);
                        return;
                    case 6:
                          //sign out
                        System.out.println("Thank you come again.");
                        System.exit(0);
                    default:
                        System.out.println("Command input is incorrect");
                }

        }while(Choice != 7);
    }

    public static void ShowAccount(Account acc) {
        System.out.println("");
        System.out.println("==================Current account details======================");
        System.out.println("Card Number:" + acc.getCardId());
        System.out.println("Full Name: " + acc.getUserName());
        System.out.println("Balance: " + acc.getMoney());
        System.out.println("Current cash withdrawal limit: "+ acc.getQuotaMoney());
    }
    
     public static void DepositMoney(Account acc) {

        System.out.println(" ");
        System.out.println("==================Saving operation======================");
        Scanner scan = new Scanner(System.in);
        System.out.printf("Enter the deposit amount: ");
        double money = scan.nextDouble();

        //Directly modify the amount to the money attribute of the account object
        acc.setMoney(acc.getMoney() + money);
        System.out.println("Deposit completed!");
    }

    public static void DrawMoney(Account acc) {

        Scanner scan = new Scanner(System.in);
        System.out.println("");
        System.out.println("==================Withdrawal operation======================");
        //whether is account is enough
          if(acc.getMoney() >= 100) {
            while (true) {
                System.out.printf("Enter the withdrawal amount: ");
                double money = scan.nextDouble();
                //whether the amount exceeds the current limit
                if(money > acc.getQuotaMoney()) {
                    System.out.println("Withdrawal amount exceeds the limit of each time, only can withdraw at most: "+ acc.getQuotaMoney());
                }
                else {
                    //whether the current balance is enough for you to withdraw money
                    if(acc.getMoney() >= money){
                        //Enough money to withdraw
                        acc.setMoney(acc.getMoney() - money);
                        System.out.println("Successfully withdraw the money " + money + ". Current balance remaining:" + acc.getMoney());
                        return; //Kill the withdrawal method after withdrawing the money
                    }
                    else {
                        System.out.println("Insufficient balance!");
                    }
                }
            }
        }
        else {
            System.out.println("Don't withdraw if your balance doesn't exceed 100 yuan~~~");
        }
    }
   
   public static void TransferMoney(Account acc, ArrayList<Account> list) {
       Scanner scan = new Scanner(System.in);
       //whether there are 2 or more Account
       if(list.size() < 2) {
           System.out.println("There are no other accounts in the system to transfer!");
           return;
       }
       //whether there is money in the accounts
       if(acc.getMoney() == 0) {
           System.out.println("Sorry, don't have any money");
           return;
       }
       
       //transfer login
          while (true) {
            System.out.printf("Enter the card number of the other party:");
            String cardId = scan.nextLine();
            Account test5 = getAccountByCardId(cardId,list);
            //whether this account object exists, which indicates that the opposite card number is entered correctly
            if(test5 != null) {
                //Judge whether this account object is the currently logged in account
                if(test5.getCardId() == acc.getCardId()) {
  
                    System.out.println("Cannot transfer money on your own Account!");
                }
                else {
                    //Confirm the last name of the other party
                    String name = "*" + test5.getUserName().substring(1);
                    System.out.printf("Please confirm [" + name + " ] is the name: ");
                    String preName = scan.nextLine();

                    if(test5.getUserName().startsWith(preName)) {
                        System.out.printf("Enter the transfer amount:");
                        double money = scan.nextDouble();
                        //Judge whether this amount exceeds your balance
                        if(money > acc.getMoney()){
                            System.out.println("Limit exceed over " + acc.getMoney() + ". Try again");
                        }
                        else {
                            //transfer money
                            acc.setMoney(acc.getMoney() - money);
                            test5.setMoney(test5.getMoney() + money);
                            System.out.println("Transfer has been successful into "+ test5.getUserName() + " accounts " + money);
                            return;
                        }
                    }
                    else {
                        System.out.println("Authentication information error");
                    }
                }
            }
            else {
                System.out.println("Error with the transfer card number that is entered!");
            }
        }
   }
   
    public static void updatePassWord(Account acc) {
     
        Scanner scan = new Scanner(System.in);  
        System.out.println("");
        System.out.println("==================Change Password======================");
        while (true) {
           System.out.printf("Enter current password:");
           String ConfirmPassword = scan.nextLine();
            //Determine whether the password is correct
            if(acc.getPassWord().equals(ConfirmPassword)){
                while (true) {
                    //You can enter a new password
                    System.out.printf("Enter a new password:");
                    String newPassWord = scan.next();

                    System.out.printf("Enter confirmation password:");
                    ConfirmPassword = scan.nextLine();

                    if(newPassWord.equals(ConfirmPassword)){
                        //Change the password of the account object to the new password
                        acc.setPassWord(newPassWord);
                        return;  //End it directly!!
                    }
                    else {
                        System.out.println("The passwords entered twice are inconsistent");
                    }
                }
            }
            else {
                System.out.println("The password currently entered is incorrect");
            }
        }
    }
}

