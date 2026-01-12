package service;

import domain.Account;
import domain.Customer;
import domain.Transaction;
import domain.Type;
import exceptions.AccountNotFoundException;
import exceptions.InsufficientFundsExceptions;
import exceptions.ValidationException;
import repository.AccountRepository;
import repository.CustomerRepository;
import repository.TransactionRepository;
import util.Validation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements  BankService{

    private  final AccountRepository accountRepository= new AccountRepository();
    private final TransactionRepository transactionRepository = new TransactionRepository();
    private  final CustomerRepository customerRepository = new CustomerRepository();


    private final Validation<String> validatename  = name -> {
        if (name == null || name.isBlank()) throw new ValidationException("Name is ReQuired");
    };


    private final Validation<String> validateEmail = Email -> {
        if (Email == null || !Email.contains("@")) throw new ValidationException("Email is required");

    };

    private final Validation<String> validateType = Type -> {

        if (Type == null  || !(Type.equalsIgnoreCase("SAVING")) || Type.equalsIgnoreCase("CURRENT")) throw
        new ValidationException("Type must be Saving or Current");
    };

private final Validation<Double>  validateAmountPositive = amount ->{
   if (amount == null|| amount < 0)
       throw new ValidationException("Please enter valid amount");

    };







    @Override
    public String openAccount(  String name, String Email, String accountType ) {

        validatename.validate(name);
        validateEmail.validate(Email);
        validateType.validate(accountType);


        String CustomerId = UUID.randomUUID().toString();


        Customer c = new Customer(CustomerId, name, Email);
        customerRepository.save(c);





        String accountNumber = getAccountyNumber();



        Account account = new Account( accountNumber, accountType , (double) 0 , CustomerId);

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

        validateAmountPositive.validate(amount);
        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException( "Account Not Found" + accountNumber));
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

    @Override
    public void withdraw(String accountNumber, Double amount, String note) {

        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException ("Account not found " + accountNumber)) ;

        if (account.getBalance() .compareTo(amount) < 0)
            throw new InsufficientFundsExceptions( " Insufficient Balance");


        account.setBalance(account.getBalance()-amount);


        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                account.getAccountNumber(),
                amount,
                LocalDateTime.now(),
                note,
                Type.WITHDRAW
        );
transactionRepository.add(transaction);




    }

    @Override
    public void transfer(String fromAcc, String toAcc, Double amount, String note) {
        validateAmountPositive.validate(amount);

if (fromAcc.equals(toAcc))
    throw new ValidationException( "cannot transfer to your own account");

Account from = accountRepository.findByNumber(fromAcc)
        .orElseThrow(() -> new AccountNotFoundException("Account Not Found"));


Account to = accountRepository.findByNumber(toAcc)
        .orElseThrow(() -> new AccountNotFoundException("Account Not Found "));

if (from.getBalance().compareTo(amount) < 0)
    throw new InsufficientFundsExceptions("Insufficient Balance");


from.setBalance(from.getBalance() - amount);
to.setBalance(to.getBalance() + amount);

transactionRepository.add(new Transaction(from.getAccountNumber(),

        UUID.randomUUID().toString(),
        amount,
        LocalDateTime.now(),
        note,
        Type.TRANSFER_OUT));

transactionRepository.add(new Transaction(to.getAccountNumber(),
        UUID.randomUUID().toString(),

        amount,
        LocalDateTime.now(),
        note,
        Type.WITHDRAW));


    }

    @Override
    public List<Transaction> getStatement(String account) {
        return  transactionRepository.findByAccount(account).stream()
                .sorted(Comparator.comparing(Transaction:: getTimestamp))
                .collect(Collectors.toList());

    }

    @Override
    public List<Account> searchAcoountByCustomername(String q) {

        String query = (q == null) ? "": q.toLowerCase();
        List<Account> result = new ArrayList<>();
        for (Customer c : customerRepository.findAll()){

            if (c.getName().toLowerCase().contains(query))
                result.addAll( accountRepository.findByCustomerId(c.getId()));

        }
        result.sort(Comparator.comparing(Account:: getAccountNumber));

        return result;
    }


    // generate AccountNumber
    private String getAccountyNumber () {

        int size  = accountRepository.findAll().size() + 1;
        return String.format("Ac%06d" , size);

    }
}
