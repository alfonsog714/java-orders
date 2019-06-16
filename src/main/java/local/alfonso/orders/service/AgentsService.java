package local.alfonso.orders.service;

import local.alfonso.orders.model.Agents;
import local.alfonso.orders.model.Customers;

import java.util.ArrayList;

public interface AgentsService
{
    ArrayList<Agents> findAll();

    Agents findAgentById(long id);

    Agents findAgentByName(String name);

    void delete(long id);

    Agents save(Agents agent);

    Agents update(Agents agent, long id);
}
