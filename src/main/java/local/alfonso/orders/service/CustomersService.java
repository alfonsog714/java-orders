package local.alfonso.orders.service;

import local.alfonso.orders.model.Customers;

import java.util.ArrayList;

public interface CustomersService
{
    ArrayList<Customers> findAll();

    Customers findCustomerById(long id);

    Customers findCustomerByName(String name);

    void delete(long id);

    Customers save(Customers customer);

    Customers update(Customers customer, long id);
}
