package service;

import domain.Account;
import repository.AccountRepository;

import java.util.UUID;

public class BankServiceImpl implements  BankService{

    private  final AccountRepository accountRepository= new AccountRepository();
    @Override
    public String openAccount( String AccountNumber , String name, String Email, String accountType) {

        String customerid = UUID.randomUUID().toString();

        String accountNumber = getAccountyNumber();



        Account account = new Account(AccountNumber , accountType , (double) 0 , customerid);

accountRepository.save(account);
        return accountNumber;
    }


    // genrate AccountNumber
    private String getAccountyNumber () {

        int size  = accountRepository.findAll().size() + 1;
        return String.format("Ac%06d" , size);

    }
}
