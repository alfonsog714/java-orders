package local.alfonso.orders.service;


import local.alfonso.orders.model.Agents;
import local.alfonso.orders.model.Customers;
import local.alfonso.orders.model.Orders;
import local.alfonso.orders.repos.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "agentsService")
public class AgentsServiceImpl implements AgentsService
{
    @Autowired
    private AgentsRepository arepos;

    @Override
    public ArrayList<Agents> findAll() {
        ArrayList<Agents> list = new ArrayList<>();
        arepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Agents findAgentById(long id) {
        return arepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public Agents findAgentByName(String name) {
        Agents agent = arepos.findByAgentname(name);

        if (agent == null)
        {
            throw new EntityNotFoundException("Agent by the name of " + name + " not found.");
        }
        return agent;
    }

    @Override
    public void delete(long id) {
        if (arepos.findById(id).isPresent())
        {
            arepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }

    }

    @Transactional
    @Override
    public Agents save(Agents agent) {
        Agents newAgent = new Agents();

        newAgent.setAgentname(agent.getAgentname());
        newAgent.setWorkingarea(agent.getWorkingarea());
        newAgent.setCommission(agent.getCommission());
        newAgent.setPhone(agent.getPhone());
        newAgent.setCountry(agent.getCountry());

        for (Customers c : agent.getCustomers())
        {
            newAgent.getCustomers().add(new Customers(c.getCustname(),c.getCustcity(),c.getWorkingarea(),c.getCustcountry(),c.getGrade(),c.getOpeningamt(),c.getReceiveamt(),c.getPaymentamt(),c.getOutstandingamt(),c.getPhone(), c.getAgent()));
        }

        for(Orders o : agent.getOrders())
        {
            newAgent.getOrders().add(new Orders(o.getOrdamount(),o.getAdvanceamount(),o.getCustomer(),o.getAgent(),o.getOrdersescription()));
        }
        return arepos.save(newAgent);
    }

    @Override
    public Agents update(Agents agent, long id) {
        Agents currentAgent = arepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (agent.getAgentname() != null)
        {
            currentAgent.setAgentname(agent.getAgentname());
        }

        if(agent.getWorkingarea() != null)
        {
            currentAgent.setWorkingarea(agent.getWorkingarea());
        }

        if(agent.getCommission() != currentAgent.getCommission())
        {
            currentAgent.setCommission(agent.getCommission());
        }

        if(agent.getPhone() != null)
        {
            currentAgent.setPhone(agent.getPhone());
        }

        if(agent.getCountry() != null)
        {
            currentAgent.setCountry(agent.getCountry());
        }

        if(agent.getOrders().size() > 0)
        {
            for(Orders o : agent.getOrders())
            {
                currentAgent.getOrders().add(new Orders(o.getOrdamount(),o.getAdvanceamount(),o.getCustomer(),o.getAgent(),o.getOrdersescription()));
            }
        }

        if(agent.getCustomers().size() > 0)
        {
            for (Customers c : agent.getCustomers())
            {
                currentAgent.getCustomers().add(new Customers(c.getCustname(),c.getCustcity(),c.getWorkingarea(),c.getCustcountry(),c.getGrade(),c.getOpeningamt(),c.getReceiveamt(),c.getPaymentamt(),c.getOutstandingamt(),c.getPhone(), c.getAgent()));
            }
        }
        return arepos.save(currentAgent);
    }
}
