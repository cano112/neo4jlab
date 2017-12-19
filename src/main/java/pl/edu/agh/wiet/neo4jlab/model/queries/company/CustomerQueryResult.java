package pl.edu.agh.wiet.neo4jlab.model.queries.company;

import org.springframework.data.neo4j.annotation.QueryResult;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Company;
import pl.edu.agh.wiet.neo4jlab.model.relationships.CustomerRelationship;

@QueryResult
public class CustomerQueryResult {
    Company company;
    CustomerRelationship customerRelationship;
}