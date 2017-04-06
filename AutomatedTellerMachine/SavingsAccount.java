/**
 * A savings account class.
 * 
 * @author John Bloswick
 * @version 1.0
 */

import java.util.ArrayList;

public class SavingsAccount extends Account
{
    int interestRate;
    
    public SavingsAccount(String accountNumber, ArrayList<Customer> accountHolders)
    {
        super(accountNumber, accountHolders);
    }
    
    public int getInterestRate()
    {
        return interestRate;
    }
}
