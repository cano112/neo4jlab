package pl.edu.agh.wiet.neo4jlab.model.relationships;

import org.neo4j.ogm.annotation.*;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Company;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Product;

@RelationshipEntity(type = "SELLS_FOR_PRICE")
public class StockItemRelationship {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Company company;

    @EndNode
    private Product product;

    private double price;


    private StockItemRelationship() {}

    public StockItemRelationship(Company company, Product product, double price) {
        this.company = company;
        this.product = product;
        this.price = price;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
