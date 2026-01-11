package service;

import domain.Account;
import domain.Transaction;

import java.util.List;

public interface BankService {
    public String openAccount(   String name , String Email , String accountType );

    public List<Account> listAccount();

     void deposit(String accountNumber, double amount, String deposit);

    void withdraw(String accountNumber, Double amount, String note);

    void transfer(String from, String to, Double amount, String transefer);

    List<Transaction> getStatement(String account);
     List <Account> searchAcoountByCustomername(String q);
}
