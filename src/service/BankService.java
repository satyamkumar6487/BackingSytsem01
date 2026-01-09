package service;

import domain.Account;

import java.util.List;

public interface BankService {
    public String openAccount(   String name , String Email , String accountType );

    public List<Account> listAccount();
}
