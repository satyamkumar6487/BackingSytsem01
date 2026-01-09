package service;

import domain.Account;
import repository.AccountRepository;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements  BankService{

    private  final AccountRepository accountRepository= new AccountRepository();
    @Override
    public String openAccount(  String name, String Email, String accountType) {

        String customerid = UUID.randomUUID().toString();

        String accountNumber = getAccountyNumber();



        Account account = new Account( accountNumber, accountType , (double) 0 , customerid);

accountRepository.save(account);
        return accountNumber;
    }

    @Override
    public List<Account> listAccount() {
        return accountRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .collect(Collectors.toList());
    }




    // generate AccountNumber
    private String getAccountyNumber () {

        int size  = accountRepository.findAll().size() + 1;
        return String.format("Ac%06d" , size);

    }
}
