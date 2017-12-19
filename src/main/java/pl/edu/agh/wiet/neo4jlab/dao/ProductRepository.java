package pl.edu.agh.wiet.neo4jlab.dao;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Product;

import java.util.Optional;
import java.util.stream.Stream;

public interface ProductRepository extends Neo4jRepository<Product, Long> {
    Optional<Product> findByName(String name);

    @Query("MATCH (p:Product) RETURN p")
    Stream<Product> streamAll();

}
