/**
 * A bank Class
 * 
 * @author John Bloswick 
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bank
{
    private ArrayList<Customer> customers;
    private ArrayList<SavingsAccount> savingsAccounts;
    private ArrayList<CheckingAccount> checkingAccounts;
    private ArrayList<Card> cards;
    
    public Bank()
    {
        customers = new ArrayList<Customer>();
        savingsAccounts = new ArrayList<SavingsAccount>();
        checkingAccounts = new ArrayList<CheckingAccount>();
        cards = new ArrayList<Card>();
    }
    
    public void addCustomer()
    {
        String firstName, lastName;
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPlease enter customer's first name:");
        firstName = scan.next();
        System.out.println("\nPlease enter customer's last name:");
        lastName = scan.next();
        addCustomer(firstName, lastName);
        System.out.println("\nCustomer created.");
        printCustomerInfo(customers.get(customers.size() - 1));
    }
    
    private void addCustomer(String customerFirstName, String customerLastName)
    {
        String customerID = generateCustomerID();
        
        while(validateCustomerID(customerID) != true)
        {
            customerID = generateCustomerID();
        } 
         customers.add(new Customer(customerID, customerFirstName, customerLastName));
    }
    
    public void addCard()
    {
        String customerID, pin;
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPlease enter the customer's ID: ");
        customerID  = scan.next();
        System.out.println("\nPlease enter a security pin: ");
        pin = scan.next();
        for(Customer customer : customers)
        {
            if(customer.getCustomerID().equals(customerID))
            {
                ArrayList<String> accountNumbers = new ArrayList<String>();
                for(SavingsAccount savingsAccount : savingsAccounts)
                {
                    for(Customer customer2 : savingsAccount.getAccountHolders())
                    {
                        if(customer2.getCustomerID().equals(customerID))
                        {
                            accountNumbers.add(savingsAccount.getAccountNumber());
                        }
                    }
                }
                for(CheckingAccount checkingAccount : checkingAccounts)
                {
                    for(Customer customer2 : checkingAccount.getAccountHolders())
                    {
                        if(customer2.getCustomerID().equals(customerID))
                        {
                            accountNumbers.add(checkingAccount.getAccountNumber());
                        }
                    }
                }
            addCard(customer, pin, accountNumbers);
            System.out.println("\nCard created.");
            printCardInfo(cards.get(cards.size() - 1));
            }
        }
    }
    
    private void addCard(Customer cardholder,String pin, ArrayList<String> accountNumbers)
    {
        String cardNumber = generateCardNumber();
        while(validateCustomerID(cardNumber) != true)
        {
            cardNumber = generateCustomerID();
        }
        cards.add(new Card(cardNumber, pin, cardholder, accountNumbers));      
    }
    
    public void addAccount()
    {
        String accountType;
        boolean complete = false;
        ArrayList<String> accountHolderIDs = new ArrayList<String>();
        ArrayList<Customer> accountHolders = new ArrayList<Customer>();
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPlease enter the customer's ID: ");
        accountHolderIDs.add(scan.next());
        while(!complete)
        {
            String userResponse;
            System.out.println("\nIs there another account holder? (yes or no)");
            userResponse = scan.next();
            if(userResponse.equals("yes"))
            {
                System.out.println("\nPlease enter the customer's ID: ");
                accountHolderIDs.add(scan.next());
            }
            else if(userResponse.equals("no"))
            {
                complete = true;
            }
            else
            {
                System.out.println("\nThat is not valid input.");
            }
        }
        for(String accountHolderID : accountHolderIDs)
        {
            for(Customer accountHolder : customers)
            {
                if(accountHolder.getCustomerID().equals(accountHolderID))
                {
                    accountHolders.add(accountHolder);
                }
            }
        }
        System.out.println("\nPlease enter the type of account (savings or checking) you would like to make: ");
        accountType = scan.next();
        if(accountType.equals("savings"))
        {
            addSavingsAccount(accountHolders);
            System.out.println("Account created.");
            printSavingsAccountInfo(savingsAccounts.get(savingsAccounts.size() - 1));
        }
        else if(accountType.equals("checking"))
        {
            addCheckingAccount(accountHolders);
            System.out.println("Account created.");
            printCheckingAccountInfo(checkingAccounts.get(checkingAccounts.size() - 1));
        }
        
    }
    
    private void addSavingsAccount(ArrayList<Customer> accountHolders)
    {
        String accountNumber = generateAccountNumber();
        while(validateAccountNumber(accountNumber) != true)
        {
            accountNumber = generateAccountNumber();
        }
        savingsAccounts.add(new SavingsAccount(accountNumber, accountHolders));        
    }
    
    private void addCheckingAccount(ArrayList<Customer> accountHolders)
    {
        String accountNumber = generateAccountNumber();
        while(validateAccountNumber(accountNumber) != true)
        {
            accountNumber = generateAccountNumber();
        }
        checkingAccounts.add(new CheckingAccount(accountNumber, accountHolders));        
    }
    
    private String generateCustomerID()
    {
        int[] numbers = new int[5];
        String customerID;
        Random rand = new Random();
        
        numbers[0] = rand.nextInt(9);
        numbers[1] = rand.nextInt(9);
        numbers[2] = rand.nextInt(9);
        numbers[3] = rand.nextInt(9);
        numbers[4] = rand.nextInt(9);
        
        StringBuilder builder = new StringBuilder();
        
        for(int i : numbers)
        {
            builder.append(i);
        }
        customerID = builder.toString();
        return customerID;
    }
    
    private String generateCardNumber()
    {
        int[] numbers = new int[16];
        String cardNumber;
        Random rand = new Random();
        
        numbers[0] = rand.nextInt(9);
        numbers[1] = rand.nextInt(9);
        numbers[2] = rand.nextInt(9);
        numbers[3] = rand.nextInt(9);
        numbers[4] = rand.nextInt(9);
        numbers[5] = rand.nextInt(9);
        numbers[6] = rand.nextInt(9);
        numbers[7] = rand.nextInt(9);
        numbers[8] = rand.nextInt(9);
        numbers[9] = rand.nextInt(9);
        numbers[10] = rand.nextInt(9);
        numbers[11] = rand.nextInt(9);
        numbers[12] = rand.nextInt(9);
        numbers[13] = rand.nextInt(9);
        numbers[14] = rand.nextInt(9);
        numbers[15] = rand.nextInt(9);
        
        StringBuilder builder = new StringBuilder();
        
        for(int i : numbers)
        {
            builder.append(i);
        }
        cardNumber = builder.toString();
        return cardNumber;
    }
    
    private String generateAccountNumber()
    {
        int[] numbers = new int[16];
        String accountNumber;
        Random rand = new Random();
        
        numbers[0] = rand.nextInt(9);
        numbers[1] = rand.nextInt(9);
        numbers[2] = rand.nextInt(9);
        numbers[3] = rand.nextInt(9);
        numbers[4] = rand.nextInt(9);
        numbers[5] = rand.nextInt(9);
        numbers[6] = rand.nextInt(9);
        numbers[7] = rand.nextInt(9);
        numbers[8] = rand.nextInt(9);
        numbers[9] = rand.nextInt(9);
        numbers[10] = rand.nextInt(9);
        numbers[11] = rand.nextInt(9);
        numbers[12] = rand.nextInt(9);
        numbers[13] = rand.nextInt(9);
        numbers[14] = rand.nextInt(9);
        numbers[15] = rand.nextInt(9);
        
        StringBuilder builder = new StringBuilder();
        
        for(int i : numbers)
        {
            builder.append(i);
        }
        accountNumber = builder.toString();
        return accountNumber;
    }
    
    private boolean validateCustomerID(String customerID)
    {
        for(Customer customer : customers)
        {
            if(customer.getCustomerID() == customerID)
            {
                return false;
            }
        }
        return true;
    }
    
    private boolean validateCardNumber(String cardNumber)
    {
        for(Card card : cards)
        {
            if(card.getCardNumber() == cardNumber)
            {
                return false;
            }
        }
        return true;
    }
    
    private boolean validateAccountNumber(String accountNumber)
    {
    for(SavingsAccount savingsAccount : savingsAccounts)
    {
        if(savingsAccount.getAccountNumber() == accountNumber)
        {
                return false;
            }
        }
        for(CheckingAccount checkingAccount : checkingAccounts)
        {
            if(checkingAccount.getAccountNumber() == accountNumber)
            {
                return false;
            }
        }
        return true;
    }
    private void printCustomerInfo(Customer customer)
    {
        System.out.print("\nCustomer ID: " + customer.getCustomerID() + "\nName: " + customer.getCustomerFirstName() + " " + customer.getCustomerLastName());
    }
    
    private void printCardInfo(Card card)
    {
        System.out.print("\nCard Holder: " + card.getCardholder().getCustomerFirstName() + " " + card.getCardholder().getCustomerLastName() + "\nCard Number: " + card.getCardNumber() + "\nCard PIN: " + card.getPin() + "\nAccounts Linked: " + card.getAccountNumbers().toString());
    }
    
    private void printSavingsAccountInfo(SavingsAccount savingsAccount)
    {
        System.out.print("\nAccount Number: " + savingsAccount.getAccountNumber() +"\nBalance: " + savingsAccount.getAccountBalance() + "\nInterest Rate: " + savingsAccount.getInterestRate());
    }
    
    private void printCheckingAccountInfo(CheckingAccount checkingAccount)
    {
        System.out.print("\nAccount Number: " + checkingAccount.getAccountNumber() + "\nBalance: " + checkingAccount.getAccountBalance() + "\nMonthly Fee: $" + checkingAccount.getMonthlyFee());
    }
    
    public ArrayList<Card> getCards()
    {
        return cards;
    }
    
    public ArrayList<SavingsAccount> getSavingsAccounts()
    {
        return savingsAccounts;
    }
    
    public ArrayList<CheckingAccount> getCheckingAccounts()
    {
        return checkingAccounts;
    }
}
