package pl.edu.agh.wiet.neo4jlab.model.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import pl.edu.agh.wiet.neo4jlab.model.relationships.CustomerRelationship;
import pl.edu.agh.wiet.neo4jlab.model.relationships.StockItemRelationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Company() {}

    public Company(String name) {
        this.name = name;
    }

    @Relationship(type = "SELLS_TO")
    private Set<CustomerRelationship> customers = new HashSet<>();

    @Relationship(type = "SELLS_FOR_PRICE")
    private Set<StockItemRelationship> stockItems = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CustomerRelationship> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<CustomerRelationship> customers) {
        this.customers = customers;
    }

    public void addCustomer(CustomerRelationship person) {
        customers.add(person);
    }

    public Set<StockItemRelationship> getStockItems() {
        return stockItems;
    }

    public void setStockItems(Set<StockItemRelationship> stockItems) {
        this.stockItems = stockItems;
    }

    public void addStockItem(StockItemRelationship item) {
        stockItems.add(item);
    }
}
