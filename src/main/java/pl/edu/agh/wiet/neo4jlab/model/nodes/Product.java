package pl.edu.agh.wiet.neo4jlab.model.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import pl.edu.agh.wiet.neo4jlab.model.relationships.StockItemRelationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = "SELLS_FOR_PRICE", direction = "INCOMING")
    private Set<StockItemRelationship> companies = new HashSet<>();

    private Product() {}

    public Product(String name) {
        this.name = name;
    }

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

    public Set<StockItemRelationship> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<StockItemRelationship> companies) {
        this.companies = companies;
    }

    public void addCompany(StockItemRelationship companyRel) {
        companies.add(companyRel);
    }
}
