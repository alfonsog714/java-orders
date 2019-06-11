package local.alfonso.orders.service;


import local.alfonso.orders.model.Orders;
import local.alfonso.orders.repos.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "ordersService")
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersRepository orepos;

    @Override
    public ArrayList<Orders> findAll() {
        ArrayList<Orders> list = new ArrayList<>();
        orepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Orders findCustomerById(long id) {
        return orepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public Orders findCustomerByName(String name) {
        Orders order = orepos.findByCustomer(name);

        if(order == null)
        {
            throw new EntityNotFoundException("Order not found");
        }
        return order;
    }

    @Override
    public void delete(long id) {
        if(orepos.findById(id).isPresent())
        {
            orepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }

    }

    @Transactional
    @Override
    public Orders save(Orders order) {
        Orders newOrder = new Orders();

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrdersescription(order.getOrdersescription());
        newOrder.setCustomer(order.getCustomer());
        newOrder.setAgent(order.getAgent());

        return orepos.save(newOrder);
    }

    @Transactional
    @Override
    public Orders update(Orders order, long id) {
        Orders currentOrder = orepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if( order.getOrdamount() != currentOrder.getOrdamount())
        {
            currentOrder.setOrdamount(order.getOrdamount());
        }

        if (order.getAdvanceamount() != currentOrder.getAdvanceamount())
        {
            currentOrder.setAdvanceamount(order.getAdvanceamount());
        }

        if(order.getOrdersescription() != null)
        {
            currentOrder.setOrdersescription(order.getOrdersescription());
        }

        if (order.getCustomer() != null)
        {
            currentOrder.setCustomer(order.getCustomer());
        }

        if(order.getAgent() != null)
        {
            currentOrder.setAgent(order.getAgent());
        }

        return orepos.save(currentOrder);
    }
}
