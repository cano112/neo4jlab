package pl.edu.agh.wiet.neo4jlab.dao;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Person;
import pl.edu.agh.wiet.neo4jlab.model.queries.person.WorkerQueryResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {
    Optional<Person> findByName(String name);

    @Query("MATCH (p:Person) RETURN p")
    Stream<Person> streamAll();
}
