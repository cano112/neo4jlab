package pl.edu.agh.wiet.neo4jlab.model.queries.person;

import org.springframework.data.neo4j.annotation.QueryResult;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Person;
import pl.edu.agh.wiet.neo4jlab.model.relationships.WorkerRelationship;

@QueryResult
public class WorkerQueryResult {
    Person person;
    WorkerRelationship workerRelationship;
}
