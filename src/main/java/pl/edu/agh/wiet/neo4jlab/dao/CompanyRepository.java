package pl.edu.agh.wiet.neo4jlab.dao;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Company;
import pl.edu.agh.wiet.neo4jlab.model.queries.company.CustomerQueryResult;
import pl.edu.agh.wiet.neo4jlab.model.queries.company.StockItemQueryResult;
import pl.edu.agh.wiet.neo4jlab.model.queries.company.WorkersQueryResult;
import pl.edu.agh.wiet.neo4jlab.model.queries.person.WorkerQueryResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface CompanyRepository extends Neo4jRepository<Company, Long> {
    Optional<Company> findByName(String name);

    @Query("MATCH (c:Company) RETURN c")
    Stream<Company> streamAll();

    @Query("MATCH (c:Company)-[r:SELLS_FOR_PRICE]->(p:Product) WHERE c.name = {0} RETURN c as company, r as itemRelationship, p")
    List<StockItemQueryResult> getAllStockItemsForCompany(String companyName);

    @Query("MATCH (c:Company)-[r:SELLS_TO]->(p:Person) WHERE c.name = {0} RETURN c as company, r as customerRelationship, p")
    List<CustomerQueryResult> getAllCustomersForCompany(String companyName);

    @Query("MATCH (p:Person)-[r:WORKS_IN]->(c:Company) WHERE c.name = {0} RETURN c, r as workerRelationship, p as person")
    List<WorkerQueryResult> getAllWorkersForCompany(String companyName);
}
