/**
 * A customer class.
 * 
 * @author John Bloswick 
 * @version 1.0
 */

public class Customer
{
    String customerID, customerFirstName, customerLastName;     
    
    public Customer(String customerID, String customerFirstName, String customerLastName)
    {
        this.customerID = customerID;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
    }
    
    public String getCustomerID()
    {
       return customerID; 
    }
    
    public String getCustomerFirstName()
    {
        return customerFirstName;
    }
    
    public String getCustomerLastName()
    {
        return customerLastName;
    }
}
