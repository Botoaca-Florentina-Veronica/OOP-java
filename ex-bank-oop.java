/*
*
*   The Account class models a bank account with a balance
* */
 class Account{
    //The private instance variables
    private int accountNumber;
    private double balance;

    //The constructors(overloaded)

    public Account(double balance, int accountNumber){
        this.balance=balance;
        this.accountNumber=accountNumber;
    }

    public Account(int accountNumber){      // with default balance
        this.balance=0;     // this is optional
        this.accountNumber=accountNumber;
    }

    //the private setters/getters for the private instance variables
    //no setter for accountNumber because it is not designed to be changed

    public int getAccountNumber(){
        return this.accountNumber;  // this is optional
    }

    public double getBalance(){
        return this.balance;    //this is optional
    }

    public void setBalance(double balance){
        this.balance=balance;
    }

    //add the given amount to the balance
    public void addAmount(double amount){
         balance=balance+amount;
    }

    //substract the given amount from balance, if applicable
    public void substractAmount(double amount){
        if(amount<balance){
            balance=balance-amount;
        }
        else{
            System.out.println("Amount withdrawn exceeds the current balance!");
        }
    }

    //the toString() returns a string description of this instance
    public String toString(){
        //Use built-in function System.format() to form a formatted String
        return String.format("Account  number: %d, Balance = %.2f", accountNumber, balance);
    }
}


/*
 *   A Test Driver for the Account class
 *
 * */

public class TestAccount{
    public static void main(String[] args){
        Account a1 = new Account(1234, 99 );
        System.out.println(a1);  //toString()

        Account a2 = new Account(450);
        System.out.println(a2);

        System.out.println("The account Number is: " + a1.getAccountNumber());
        System.out.println("The balance is: " + a1.getBalance());

        //test setters and getters
        a2.setBalance(1200);
        System.out.println(a2);

        a1.substractAmount(100);
        System.out.println(a1);

        a2.addAmount(200);
        System.out.println(a2);


    }
}
