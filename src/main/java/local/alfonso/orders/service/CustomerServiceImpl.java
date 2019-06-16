package local.alfonso.orders.service;

import local.alfonso.orders.model.Customers;
import local.alfonso.orders.model.Orders;
import local.alfonso.orders.repos.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomersService
{
    @Autowired
    private CustomersRepository custrepos;

    @Override
    public ArrayList<Customers> findAll() {

        ArrayList<Customers> list = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customers findCustomerById(long id) {
        return custrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public Customers findCustomerByName(String name) {
        Customers customer = custrepos.findByCustname(name);

        if(customer == null)
        {
            throw new EntityNotFoundException("Customer " + name + " not found.");
        }
        return customer;
    }

    @Override
    public void delete(long id)
    {
        if(custrepos.findById(id).isPresent())
        {
            custrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Customers save(Customers customer) {
        Customers newCustomer = new Customers();

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setAgent(customer.getAgent());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());

        for (Orders o : customer.getOrders())
        {
            newCustomer.getOrders().add(new Orders(o.getOrdamount(), o.getAdvanceamount(), o.getCustomer(), o.getAgent(), o.getOrdersescription()));
        }
        return custrepos.save(newCustomer);
    }

    @Transactional
    @Override
    public Customers update(Customers customer, long id)
    {
        Customers currentCustomer = custrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (customer.getOrders().size() > 0)
        {
            for (Orders o : customer.getOrders())
            {
                currentCustomer.getOrders().add(new Orders(o.getOrdamount(), o.getAdvanceamount(), o.getCustomer(), o.getAgent(), o.getOrdersescription()));
            }
        }

        if(customer.getAgent() != null)
        {
            currentCustomer.setAgent(customer.getAgent());
        }

        if(customer.getCustcity() != null)
        {
            currentCustomer.setCustcity(customer.getCustcity());
        }

        if(customer.getCustcountry() != null)
        {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }

        if(customer.getCustname() != null)
        {
            currentCustomer.setCustname(customer.getCustname());
        }

        if(customer.getWorkingarea() != null)
        {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }

        if(customer.getGrade() != null)
        {
            currentCustomer.setGrade(customer.getGrade());
        }

        if(customer.getOpeningamt() != currentCustomer.getOpeningamt())
        {
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }

        if(customer.getReceiveamt() != currentCustomer.getReceiveamt())
        {
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }

        if(customer.getPaymentamt() != currentCustomer.getPaymentamt())
        {
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }

        if(customer.getOutstandingamt() != currentCustomer.getOutstandingamt())
        {
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }

        if(customer.getPhone() != null)
        {
            currentCustomer.setPhone(customer.getPhone());
        }
        return custrepos.save(currentCustomer);
    }
}
