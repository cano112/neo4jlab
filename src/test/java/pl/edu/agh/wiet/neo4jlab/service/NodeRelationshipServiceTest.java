package pl.edu.agh.wiet.neo4jlab.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphalgo.impl.util.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.agh.wiet.neo4jlab.TestConfig;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Company;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Person;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Product;
import pl.edu.agh.wiet.neo4jlab.model.results.CompanyRelationshipAggregate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= TestConfig.class)
public class NodeRelationshipServiceTest {

    @Autowired
    private NodeRelationshipService nodeRelationshipService;

    @Autowired
    private PersonService personService;

    @Test
    public void testGetAllCompanyRelationships() {
        CompanyRelationshipAggregate rel = nodeRelationshipService
                .getAllCompanyRelationships("Biedronka");


        assertEquals(1, rel.getCustomers().size());
        assertEquals(2, rel.getWorkers().size());
        assertEquals(2, rel.getProducts().size());

        rel.getCustomers().forEach(customerRel -> {
           assertEquals("Biedronka", customerRel.getCompany().getName());
        });

        rel.getProducts().forEach(productRel -> {
            assertEquals("Biedronka", productRel.getCompany().getName());
        });

        rel.getWorkers().forEach(workerRel -> {
            assertEquals("Biedronka", workerRel.getCompany().getName());
        });
    }

    @Test
    public void testGetShortestPathBetweenPeople() {
        Iterable<Map<String, Object>> paths = nodeRelationshipService
                .getShortestPathBetweenPeople("Grzegorz", "Kamil");

        Person grzegorz = personService.getPersonByName("Grzegorz")
                .orElseThrow(() -> new RuntimeException("No such person"));

        Person kamil = personService.getPersonByName("Kamil")
                .orElseThrow(() -> new RuntimeException("No such person"));

        PathImpl path = (PathImpl)paths.iterator().next().get("p");
        assertEquals(1, Stream.of(path).count());
        assertEquals((long)grzegorz.getId(), path.startNode().getId());
        assertEquals((long)kamil.getId(), path.endNode().getId());
        assertEquals(2, path.length());
    }
}
