package pl.edu.agh.wiet.neo4jlab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Company;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Person;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Product;
import pl.edu.agh.wiet.neo4jlab.service.CompanyService;
import pl.edu.agh.wiet.neo4jlab.service.PersonService;
import pl.edu.agh.wiet.neo4jlab.service.ProductService;

import javax.annotation.PostConstruct;

@Configuration
@EnableAutoConfiguration
@ComponentScan(
        basePackages = "pl.edu.agh.wiet.neo4jlab",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {App.class})})
public class TestConfig {

    private final PersonService personService;
    private final ProductService productService;
    private final CompanyService companyService;

    @Autowired
    public TestConfig(PersonService personService, ProductService productService, CompanyService companyService) {
        this.personService = personService;
        this.productService = productService;
        this.companyService = companyService;
    }

    @PostConstruct
    void init() {
        setUpDb();
    }

    private void setUpDb() {
        personService.addPerson("Kamil", 22);
        personService.addPerson("Grzegorz", 45);

        companyService.addCompany("Biedronka");

        productService.addProduct("Chleb");
        productService.addProduct("Maslo");

        Person kamil = personService.getPersonByName("Kamil")
                .orElseThrow(() -> new RuntimeException("No such person found"));
        Person grzegorz = personService.getPersonByName("Grzegorz")
                .orElseThrow(() -> new RuntimeException("No such person found"));

        Company biedronka = companyService.getCompanyByName("Biedronka")
                .orElseThrow(() -> new RuntimeException("No such company found"));

        Product chleb = productService.getProductByName("Chleb")
                .orElseThrow(() -> new RuntimeException("No such product found"));
        Product maslo = productService.getProductByName("Maslo")
                .orElseThrow(() -> new RuntimeException("No such product found"));

        personService.addWorkplace(biedronka, kamil);
        personService.addWorkplace(biedronka, grzegorz);

        companyService.addCustomer(biedronka, kamil);

        companyService.addStockItem(biedronka, chleb, 5);
        companyService.addStockItem(biedronka, maslo, 3);
    }
}
