package service;

import java.util.UUID;

public class BankServiceImpl implements  BankService{
    @Override
    public String openAccount(String name, String Email, String Type) {

        String customerid = UUID.randomUUID().toString();


        return "";
    }
}
