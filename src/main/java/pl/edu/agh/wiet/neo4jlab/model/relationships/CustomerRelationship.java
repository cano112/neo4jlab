package pl.edu.agh.wiet.neo4jlab.model.relationships;

import org.neo4j.ogm.annotation.*;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Company;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Person;

@RelationshipEntity(type="SELLS_TO")
public class CustomerRelationship {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Company company;

    @EndNode
    private Person person;

    private CustomerRelationship() {}

    public CustomerRelationship(Company company, Person person) {
        this.company = company;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
