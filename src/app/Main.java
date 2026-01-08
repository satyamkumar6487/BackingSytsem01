package app;

import java.util.Scanner;

public class Main {

    public static void main (String []args){
        boolean running = true;

        System.out.println(" Welcome  to your Console ");

Scanner sc = new Scanner (System.in);

while (true) {
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
        case "1" -> openAccount(sc);
        case "2" -> deposit(sc);
        case "3" -> withdraw(sc);
        case "4" -> transfer(sc);
        case "5" -> statements(sc);
        case "6" -> listAccount(sc);
        case "7" -> searchAccount(sc);
        case "0" -> running = false;



    }
}


    }
    private static void openAccount(Scanner sc) {

        System.out.println(" Enter Name ");
        String name = sc.nextLine().trim();

        System.out.println("Enter Email ");
        String email = sc.nextLine().trim();

        System.out.println("Account Type / SAVING / CURRENT");
        String type = sc.nextLine().trim();


        System .out.println("Initial Depostit");
        String amountstr = sc. nextLine().trim();

        Double initial = Double.valueOf(amountstr);
    }

        private static void deposit(Scanner sc) {
    }

    private static void withdraw(Scanner sc) {

    }

    private static void transfer (Scanner sc) {

    }

    private static void statements(Scanner sc) {

    }

    private static void listAccount(Scanner sc) {

    }

    private static void searchAccount(Scanner sc) {

    }


}

