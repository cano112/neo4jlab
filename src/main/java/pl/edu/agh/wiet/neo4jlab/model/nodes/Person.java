package pl.edu.agh.wiet.neo4jlab.model.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import pl.edu.agh.wiet.neo4jlab.model.relationships.WorkerRelationship;

@NodeEntity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

    @Relationship(type = "WORKS_IN")
    private WorkerRelationship company;

    private Person() {}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public WorkerRelationship getCompany() {
        return company;
    }

    public void setCompany(WorkerRelationship company) {
        this.company = company;
    }
}

