package local.alfonso.orders.repos;


import local.alfonso.orders.model.Customers;
import local.alfonso.orders.model.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Long> {
    Orders findByCustomer(String name);
}
