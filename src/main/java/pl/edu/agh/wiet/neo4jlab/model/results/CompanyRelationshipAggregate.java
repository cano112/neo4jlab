package pl.edu.agh.wiet.neo4jlab.model.results;

import pl.edu.agh.wiet.neo4jlab.model.relationships.CustomerRelationship;
import pl.edu.agh.wiet.neo4jlab.model.relationships.StockItemRelationship;
import pl.edu.agh.wiet.neo4jlab.model.relationships.WorkerRelationship;

import java.util.List;

public class CompanyRelationshipAggregate {
    private final List<WorkerRelationship> workers;
    private final List<CustomerRelationship> customers;
    private final List<StockItemRelationship> products;

    public CompanyRelationshipAggregate(List<WorkerRelationship> workers,
                                        List<CustomerRelationship> customers,
                                        List<StockItemRelationship> products) {
        this.workers = workers;
        this.customers = customers;
        this.products = products;
    }

    public List<WorkerRelationship> getWorkers() {
        return workers;
    }

    public List<CustomerRelationship> getCustomers() {
        return customers;
    }

    public List<StockItemRelationship> getProducts() {
        return products;
    }
}
