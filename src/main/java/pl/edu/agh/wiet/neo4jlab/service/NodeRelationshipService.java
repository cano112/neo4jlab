package pl.edu.agh.wiet.neo4jlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.neo4jlab.dao.CustomerRelationshipRepository;
import pl.edu.agh.wiet.neo4jlab.dao.PersonRepository;
import pl.edu.agh.wiet.neo4jlab.dao.StockItemRelationshipRepository;
import pl.edu.agh.wiet.neo4jlab.dao.WorkerRelationshipRepository;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Person;
import pl.edu.agh.wiet.neo4jlab.model.results.CompanyRelationshipAggregate;

import java.util.Map;

@Service
public class NodeRelationshipService {
    private final CustomerRelationshipRepository customerRelationshipRepository;
    private final WorkerRelationshipRepository workerRelationshipRepository;
    private final StockItemRelationshipRepository stockItemRelationshipRepository;
    private final PersonRepository personRepository;

    @Autowired
    public NodeRelationshipService(CustomerRelationshipRepository customerRelationshipRepository, WorkerRelationshipRepository workerRelationshipRepository, StockItemRelationshipRepository stockItemRelationshipRepository, PersonRepository personRepository) {
        this.customerRelationshipRepository = customerRelationshipRepository;
        this.workerRelationshipRepository = workerRelationshipRepository;
        this.stockItemRelationshipRepository = stockItemRelationshipRepository;
        this.personRepository = personRepository;
    }

    public CompanyRelationshipAggregate getAllCompanyRelationships(String companyName) {
        return new CompanyRelationshipAggregate(
                workerRelationshipRepository.getAllWorkersByCompany(companyName),
                customerRelationshipRepository.getAllCustomersByCompany(companyName),
                stockItemRelationshipRepository.getAllStockItemsByCompany(companyName));
    }

    public Iterable<Map<String, Object>> getShortestPathBetweenPeople(String person1, String person2) {
        return personRepository.getShortestPathBetweenPeople(person1, person2);
    }
}

