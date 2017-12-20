package pl.edu.agh.wiet.neo4jlab.dao;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Company;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface CompanyRepository extends Neo4jRepository<Company, Long> {
    Optional<Company> findByName(String name);

    @Query("MATCH (c:Company) RETURN c")
    Stream<Company> streamAll();
}
