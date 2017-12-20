package pl.edu.agh.wiet.neo4jlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Company;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Person;
import pl.edu.agh.wiet.neo4jlab.model.relationships.WorkerRelationship;
import pl.edu.agh.wiet.neo4jlab.dao.PersonRepository;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> getPersonByName(String name) {
        return personRepository.findByName(name);
    }

    public void addPerson(String name, int age) {
        Person person = new Person(name, age);
        personRepository.save(person);
    }

    public void addWorkplace(Company company, Person person) {
        WorkerRelationship rel = new WorkerRelationship(person,company);
        person.setCompany(rel);
        company.addWorker(rel);
        personRepository.save(person);
    }

    public Stream<Person> streamAllPeople() {
        return personRepository.streamAll();
    }
}
