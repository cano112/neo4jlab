package pl.edu.agh.wiet.neo4jlab.dao;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.wiet.neo4jlab.model.relationships.CustomerRelationship;

import java.util.List;

@Repository
public interface CustomerRelationshipRepository extends Neo4jRepository<CustomerRelationshipRepository, Long> {

    @Query("MATCH (c:Company)-[r:SELLS_TO]->(p:Person) WHERE c.name = {0} RETURN c, r, p")
    List<CustomerRelationship> getAllCustomersByCompany(String name);
}
