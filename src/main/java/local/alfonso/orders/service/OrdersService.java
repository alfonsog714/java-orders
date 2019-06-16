package local.alfonso.orders.service;

import local.alfonso.orders.model.Orders;

import java.util.ArrayList;

public interface OrdersService
{
    ArrayList<Orders> findAll();

    Orders findCustomerById(long id);

    Orders findCustomerByName(String name);

    void delete(long id);

    Orders save(Orders order);

    Orders update(Orders order, long id);
}
