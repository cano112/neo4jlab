package pl.edu.agh.wiet.neo4jlab.model.relationships;

import org.neo4j.ogm.annotation.*;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Company;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Person;

@RelationshipEntity(type="WORKS_IN")
public class WorkerRelationship {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Person person;

    @EndNode
    private Company company;

    private WorkerRelationship() {}

    public WorkerRelationship(Person person, Company company) {
        this.person = person;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
