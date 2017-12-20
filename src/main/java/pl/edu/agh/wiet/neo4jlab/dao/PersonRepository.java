package pl.edu.agh.wiet.neo4jlab.dao;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Person;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {
    Optional<Person> findByName(String name);

    @Query("MATCH (p:Person) RETURN p")
    Stream<Person> streamAll();

    @Query("MATCH p=shortestPath((p1:Person)-[*]-(p2:Person)) WHERE p1.name = {0} AND p2.name = {1} RETURN p")
    Iterable<Map<String, Object>> getShortestPathBetweenPeople(String person1, String person2);
}
