package app;


import service.BankService;
import service.BankServiceImpl;

import java.util.Scanner;

public class Main {

    public static void main (String []args){
        BankService bankService = new BankServiceImpl();


        boolean running = true;

        System.out.println(" Welcome  to your Console ");

Scanner sc = new Scanner (System.in);

while (running) {
    System.out.println("""
            
            
            1 ) open Account
            2) Deposit
            3)withdraw
            4)Transfer
            5)Account Statement
            6)List Account
            7)Search Account By Customer
            0)exit
            """);
System.out.println("CHOSE");


    String Chose = sc.nextLine().trim();
    System.out.println(Chose);

    switch (Chose) {
        case "1" -> openAccount(sc , bankService);
        case "2" -> deposit(sc , bankService);
        case "3" -> withdraw(sc, bankService);
        case "4" -> transfer(sc);
        case "5" -> statements(sc);
        case "6" -> listAccount(sc , bankService);
        case "7" -> searchAccount(sc);
        case "0" -> running = false;




    }
}


    }
    private static void openAccount(Scanner sc , BankService bankService) {

        System.out.println(" Enter Name ");
        String name = sc.nextLine().trim();

        System.out.println("Enter Email ");
        String Email = sc.nextLine().trim();

        System.out.println("Account Type / SAVING / CURRENT");
        String Type = sc.nextLine().trim();


        System .out.println("Initial Depostit");
        String amountstr = sc. nextLine().trim();

        Double initial = Double.valueOf(amountstr);

        String accountNumber =  bankService.openAccount(name, Email, Type);
        if (initial > 0 )
            bankService.deposit(accountNumber , initial ,"Initial Deposit");

System.out.println(" Account  Opened" + accountNumber);




    }

        private static void deposit(Scanner sc  , BankService bankService ) {

        System.out.println("Account Number");

        String accountNumber = sc.nextLine().trim();

        System .out.println("Amount");
        double amount = Double.valueOf(sc.nextLine().trim());

        bankService.deposit(accountNumber , amount , "Deposit");
        System.out.println("Deposited");


    }

    private static void withdraw(Scanner sc , BankService bankService) {
        System.out.println("Account Number");
        String accountNumber = sc.nextLine().trim();

        System.out.println("Amount");
        Double amount = Double.valueOf(sc.nextLine().trim());

        bankService.withdraw(accountNumber, amount , " Withdrawl");

        System.out.println("Withdrawn");




    }

    private static void transfer (Scanner sc) {

    }

    private static void statements(Scanner sc) {

    }

    private static void listAccount(Scanner sc , BankService  bankService) {
bankService.listAccount().forEach(a-> {
    System.out.println(a.getAccountNumber() + " |" + a.getAccountType() + "|" + a.getBalance());
});


    }
    private static void searchAccount(Scanner sc) {

    }


}

