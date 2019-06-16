package local.alfonso.orders.repos;


import local.alfonso.orders.model.Customers;

import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customers, Long>
{
    Customers findByCustname(String name);
}
