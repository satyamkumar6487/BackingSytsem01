package domain;

public class Account {
    private String AccountNumber;
    private String customerid ;
    private Double balance ;
    private String accountType;

    public Account(String accountNumber, String customerid, Double balance, String accountType) {
        AccountNumber = accountNumber;
        this.customerid = customerid;
        this.balance = balance;
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
