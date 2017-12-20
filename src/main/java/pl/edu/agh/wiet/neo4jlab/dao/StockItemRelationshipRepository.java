package pl.edu.agh.wiet.neo4jlab.dao;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.wiet.neo4jlab.model.relationships.CustomerRelationship;
import pl.edu.agh.wiet.neo4jlab.model.relationships.StockItemRelationship;

import java.util.List;

@Repository
public interface StockItemRelationshipRepository extends Neo4jRepository<StockItemRelationship, Long> {

    @Query("MATCH (c:Company)-[r:SELLS_FOR_PRICE]->(p:Product) WHERE c.name = {0} RETURN c, r, p")
    List<StockItemRelationship> getAllStockItemsByCompany(String name);
}
