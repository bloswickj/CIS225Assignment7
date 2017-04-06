/**
 * An account class.
 * 
 * @author John Bloswick 
 * @version 1.0
 */

import java.util.ArrayList;

public abstract class Account
{
    float accountBalance;
    String accountNumber;
    ArrayList<Customer> accountHolders;
    
    public Account(String accountNumber, ArrayList<Customer> accountHolders)
    {
        this.accountNumber = accountNumber;
        this.accountHolders = accountHolders;
        accountBalance = 0;
    }
    
    public float getAccountBalance()
    {
        return accountBalance;
    }
    
    public String getAccountNumber()
    {
        return accountNumber;
    }
    
    public ArrayList<Customer> getAccountHolders()
    {
        return accountHolders;
    }
    
    public void addToBalance(float depositAmount)
    {
        accountBalance += depositAmount;
    }
    
    public void subtractFromBalance(float withdrawAmount)
    {
        if(withdrawAmount >= accountBalance)
        {
            accountBalance -= withdrawAmount;
        }
    }
}
