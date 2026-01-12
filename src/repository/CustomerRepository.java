package repository;

import domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository {

    private final Map<String , Customer> customersByid = new HashMap<>();

public List<Customer> findAll() {
    return new ArrayList<>(customersByid.values());
}


    public void save(Customer c) {
    customersByid.put(c.getId(),c);
    }
}
