/**
 * A card class.
 * 
 * @author John Bloswick
 * @version 1.0
 */

import java.util.ArrayList;

public class Card
{
    private String cardNumber, pin;
    private Customer cardholder;
    private ArrayList<String> accountNumbers;
    
    public Card(String cardNumber, String pin, Customer cardholder, ArrayList<String> accountNumbers)
    {
        this.cardNumber = cardNumber;
        this.cardholder = cardholder;
        this.accountNumbers = accountNumbers;
        this.pin = pin;
    }
    
    public String getCardNumber()
    {
        return cardNumber;
    }
        
    public String getPin()
    {
        return pin;
    }  
    
    public Customer getCardholder()
    {
        return cardholder;
    }
    
    public ArrayList<String> getAccountNumbers()
    {
        return accountNumbers;
    }    
    public void setPin(String pin)
    {
        this.pin = pin;
    }
}
