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


}


    }
}
