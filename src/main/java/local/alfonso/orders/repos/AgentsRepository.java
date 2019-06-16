package local.alfonso.orders.repos;

import local.alfonso.orders.model.Agents;
import org.springframework.data.repository.CrudRepository;

public interface AgentsRepository extends CrudRepository<Agents, Long> {
    Agents findByAgentname(String name);
}
