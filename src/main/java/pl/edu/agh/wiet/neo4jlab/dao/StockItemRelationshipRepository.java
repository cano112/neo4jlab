package pl.edu.agh.wiet.neo4jlab.dao;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import pl.edu.agh.wiet.neo4jlab.model.relationships.StockItemRelationship;

public interface StockItemRelationshipRepository extends Neo4jRepository<StockItemRelationship, Long> {
}
