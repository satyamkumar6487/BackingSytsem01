package service;

import domain.Account;
import domain.Transaction;
import domain.Type;
import repository.AccountRepository;
import repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements  BankService{

    private  final AccountRepository accountRepository= new AccountRepository();
    private final TransactionRepository transactionRepository = new TransactionRepository();

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

    @Override
    public void deposit(String accountNumber, double amount, String note) {

        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account Not Found" + accountNumber));
        account.setBalance(Double.valueOf(account.getBalance() + amount));

        // create transaction
        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),     // id
                account.getAccountNumber(),
                amount,
                LocalDateTime.now(),
                note,
                Type.DEPOSIT
        );
        transactionRepository.add(transaction);
    }
    // generate AccountNumber
    private String getAccountyNumber () {

        int size  = accountRepository.findAll().size() + 1;
        return String.format("Ac%06d" , size);

    }
}
