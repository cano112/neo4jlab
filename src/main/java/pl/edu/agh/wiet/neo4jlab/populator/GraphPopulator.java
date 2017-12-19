package pl.edu.agh.wiet.neo4jlab.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Company;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Person;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Product;
import pl.edu.agh.wiet.neo4jlab.service.CompanyService;
import pl.edu.agh.wiet.neo4jlab.service.PersonService;
import pl.edu.agh.wiet.neo4jlab.service.ProductService;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GraphPopulator {

    private final CompanyService companyService;
    private final ProductService productService;
    private final PersonService personService;

    @Autowired
    public GraphPopulator(CompanyService companyService, ProductService productService, PersonService personService) {
        this.companyService = companyService;
        this.productService = productService;
        this.personService = personService;
    }

    public void populate() {
        final String productNames[] = {"Chleb", "Ksiazka", "Samochod", "Ryba", "Dlugopis", "Plecak", "Kanapa", "Stol", "Dom", "Narty"};
        final String companyNames[] = {"Januszpol", "Januszex", "Komis", "IT", "Lewiatan", "Rybny", "Sklep", "Kiosk", "Awiteks", "Piekarnia"};
        final String peopleNames[] = {"Kamil", "Krzysztof", "Grazyna", "Adam", "Piotr", "Sylwia", "Eryk", "Wiola", "Edyta", "Bozena"};

        final Random rand  = new Random();

        Arrays.stream(productNames).forEach(productService::addProduct);
        Arrays.stream(companyNames).forEach(companyService::addCompany);
        Arrays.stream(peopleNames).forEach(name -> personService.addPerson(name, Math.abs(rand.nextInt() % 80 + 1)));

        final List<Company> companies = companyService.streamAllCompanies().collect(Collectors.toList());
        final List<Person> people = personService.streamAllPeople().collect(Collectors.toList());
        final List<Product> products = productService.streamAllProducts().collect(Collectors.toList());

        people.forEach(person -> personService.addWorkplace(companies.get(Math.abs(rand.nextInt() % companies.size())), person));

        companies.forEach(company -> {
            final int customersCount = Math.abs(rand.nextInt() % people.size());
            List<Person> shuffledPeople = new LinkedList<>(people);
            Collections.shuffle(shuffledPeople);
            shuffledPeople.stream()
                    .limit(customersCount)
                    .forEach(person -> companyService.addCustomer(company, person));

            final int productsCount = Math.abs(rand.nextInt() % products.size());
            List<Product> shuffledProducts = new LinkedList<>(products);
            Collections.shuffle(shuffledProducts);
            shuffledProducts.stream()
                    .limit(productsCount)
                    .forEach(product -> companyService.addStockItem(company, product, Math.abs(rand.nextDouble() * 10)));
        });
    }
}
