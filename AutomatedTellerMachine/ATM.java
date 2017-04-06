/**
 * An ATM class.
 * 
 * @author John Bloswick
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Scanner;

public class ATM
{
    private String currentCardNumber;
    private ArrayList<String> transactionLog;    
    private Bank bank;
    
    public ATM(Bank bank)
    {
        transactionLog = new ArrayList<String>();
        this.bank = bank;
    }
    
    public void transaction()
    {
        String cardNumber, selectedAccount = "", selectedAction;
        ArrayList<String> accountNumbers = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        System.out.println("\nWelcome to the ATM, please enter your card number: ");
        cardNumber = scan.next();
        for(Card card : bank.getCards())
        {
            if(card.getCardNumber().equals(cardNumber))
            {
                if(validateUser(card))
                {
                    accountNumbers = getCardAccountNumbers(card);
                    printCardAccountNumbers(card);
                    int userInput = -1;
                    while(userInput < 0 || userInput > accountNumbers.size() - 1)
                    {
                        System.out.println("\nPlease select an account to make an action with that account: ");
                        userInput = Integer.parseInt(scan.next());                    
                    }
                    selectedAccount = accountNumbers.get(userInput);
                    boolean complete = false;
                    while(!complete)
                    {
                        String receipt;
                        System.out.println("\nPlease select an action (deposit | withdraw | balance | end): ");
                        selectedAction = scan.next();
                        switch (selectedAction)
                        {
                            case "deposit":
                                transactionLog.add(makeDeposit(selectedAccount));
                                System.out.println("\n\tTransaction Receipt: " + transactionLog.get(transactionLog.size() - 1));
                            break;
                            
                            case "withdraw":
                                transactionLog.add(makeWithdraw(selectedAccount));
                                System.out.println("\n\tTransaction Receipt: " + transactionLog.get(transactionLog.size() - 1));                                   
                            break;
                            
                            case "balance":
                                checkBalance(selectedAccount);                                    
                            break; 
                            
                            case "end":
                                complete = true;
                            break;
                            
                            default:
                                System.out.println("\nThat is not a valid selection.");
                            break;
                        }
                    }                    
                }
                else
                {
                    System.out.println("\nToo many failed attempts!");
                }
            }
            else
            {
                System.out.println("\nThat is not a valid card!");
            }
        }
        System.out.println("\n\tGoodbye...");
    }
    
    private boolean validateUser(Card card)
    {
        int counter = 0;
        String pin;
        Scanner scan = new Scanner(System.in);
        while(counter < 3)
        {
            System.out.println("\nPlease enter your pin: ");
            pin = scan.next();
            if(card.getPin().equals(pin))
            {
                return true;
            }
            else
            {
                counter++;
            }
        }
        return false;
    }
    
    private ArrayList<String> getCardAccountNumbers(Card card)
    {
        ArrayList<String> accountNumbers = new ArrayList<String>();
        for(String accountNumber : card.getAccountNumbers())
        {
            for(SavingsAccount savingsAccount : bank.getSavingsAccounts())
            {
                if(savingsAccount.getAccountNumber().equals(accountNumber))
                {
                    accountNumbers.add(accountNumber);
                }
            }
            for(CheckingAccount checkingAccount : bank.getCheckingAccounts())
            {
                if(checkingAccount.getAccountNumber().equals(accountNumber))
                {
                    accountNumbers.add(accountNumber);
                }
            }
        }
        return accountNumbers;
    }
    
    private void printCardAccountNumbers(Card card)
    {
        int counter = 0;
        System.out.println("\nAccounts linked to this card: ");
        for(String accountNumber : card.getAccountNumbers())
        {
            for(SavingsAccount savingsAccount : bank.getSavingsAccounts())
            {
                if(savingsAccount.getAccountNumber().equals(accountNumber))
                {
                    System.out.println("\n" + counter + " - Savings Account: " + accountNumber);
                    counter++;
                }
            }
            for(CheckingAccount checkingAccount : bank.getCheckingAccounts())
            {
                if(checkingAccount.getAccountNumber().equals(accountNumber))
                {
                    System.out.println("\n" + counter + " - Checking Account: " + accountNumber);
                    counter++;
                }
            }
        }
    }
    
    private String makeDeposit(String selectedAccount)
    {
        float depositAmount;
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPlease enter deposit amount in a multiple of 20: ");
        depositAmount = Float.parseFloat(scan.next());
        if(depositAmount != 0 && depositAmount % 4 == 0)
        {
            for(SavingsAccount savingsAccount : bank.getSavingsAccounts())
            {
                if(savingsAccount.getAccountNumber().equals(selectedAccount))
                {
                    savingsAccount.addToBalance(depositAmount);
                    return "Deposit OF $" + depositAmount + " TO " + selectedAccount; 
                }
            }
            for(CheckingAccount checkingAccount : bank.getCheckingAccounts())
            {
                if(checkingAccount.getAccountNumber().equals(selectedAccount))
                {
                    checkingAccount.addToBalance(depositAmount);
                    return "Deposit OF $" + depositAmount + " TO " + selectedAccount; 
                }
            }
            return "FAILED DEPOSIT OF $" + depositAmount + " TO " + selectedAccount;
        }
        else
        {
            return "FAILED DEPOSIT OF $" + depositAmount + " TO " + selectedAccount;
        }
    }
    
    private String makeWithdraw(String selectedAccount)
    {
        float withdrawAmount;
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPlease enter the amount to withdraw in a multiple of 20: ");
        withdrawAmount = Float.parseFloat(scan.next());
        if(withdrawAmount > 0 && withdrawAmount % 4 == 0)
        {
            for(SavingsAccount savingsAccount : bank.getSavingsAccounts())
            {
                if(savingsAccount.getAccountNumber().equals(selectedAccount))
                {                 
                    if(savingsAccount.getAccountBalance() >= withdrawAmount)
                    {
                        savingsAccount.subtractFromBalance(withdrawAmount);
                        return "Withdraw OF $" + withdrawAmount + " FROM " + selectedAccount;
                    }
                    else
                    {
                        return "FAILED WITHDRAW OF $" + withdrawAmount + " FROM " + selectedAccount + "(INSUFFICIENT FUNDS)";
                    }
                }
            }
            for(CheckingAccount checkingAccount : bank.getCheckingAccounts())
            {
                if(checkingAccount.getAccountNumber().equals(selectedAccount))
                {
                    if(checkingAccount.getAccountBalance() >= withdrawAmount)
                    {
                        checkingAccount.subtractFromBalance(withdrawAmount);
                        return "Withdraw OF $" + withdrawAmount + " FROM " + selectedAccount; 
                    }
                    else
                    {
                        return "FAILED WITHDRAW OF $" + withdrawAmount + " FROM " + selectedAccount + "(INSUFFICIENT FUNDS)";                    
                    }
                }
            }
            return "FAILED WITHDRAW OF $" + withdrawAmount + " FROM " + selectedAccount;
        }
        else
        {
            return "FAILED WITHDRAW OF $" + withdrawAmount + " FROM " + selectedAccount;
        }
    }
    
    private void checkBalance(String selectedAccount)
    {
        for(SavingsAccount savingsAccount : bank.getSavingsAccounts())
        {
            if(savingsAccount.getAccountNumber().equals(selectedAccount))
            {                 
                System.out.println("\nAccount Balance: $" + savingsAccount.getAccountBalance());
            }
        }
        for(CheckingAccount checkingAccount : bank.getCheckingAccounts())
        {
            if(checkingAccount.getAccountNumber().equals(selectedAccount))
            {
                System.out.println("\nAccount Balance: $" + checkingAccount.getAccountBalance());
            }
        }          
    }
}
