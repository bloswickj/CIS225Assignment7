/**
 * A checking account class.
 * 
 * @author John Bloswick
 * @version 1.0
 */

import java.util.ArrayList;

public class CheckingAccount extends Account
{
    int monthlyFee;
    
    public CheckingAccount(String accountNumber, ArrayList<Customer> accountHolders)
    {
        super(accountNumber, accountHolders);
    }
    
    public int getMonthlyFee()
    {
        return monthlyFee;
    }
}
