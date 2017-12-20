package pl.edu.agh.wiet.neo4jlab.dao;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Person;
import pl.edu.agh.wiet.neo4jlab.model.relationships.WorkerRelationship;

import java.util.List;

@Repository
public interface WorkerRelationshipRepository extends Neo4jRepository<WorkerRelationship, Long> {

    @Query("MATCH (p:Person)-[r:WORKS_IN]->(c:Company) WHERE c.name = {0} RETURN p, r, c")
    List<WorkerRelationship> getAllWorkersByCompany(String name);
}
