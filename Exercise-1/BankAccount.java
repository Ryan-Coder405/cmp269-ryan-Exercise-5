
public class BankAccount {

    int balance = 1000;

    public int getBalance(){
        return balance;
    }

    public synchronized void withdraw(int amount){
        if(balance - amount > 0){
            balance = balance - amount;
        }
    }
    
}
