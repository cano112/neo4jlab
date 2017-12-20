package pl.edu.agh.wiet.neo4jlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Company;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Person;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Product;
import pl.edu.agh.wiet.neo4jlab.model.relationships.CustomerRelationship;
import pl.edu.agh.wiet.neo4jlab.model.relationships.StockItemRelationship;
import pl.edu.agh.wiet.neo4jlab.dao.CompanyRepository;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Optional<Company> getCompanyByName(String name) {
        return companyRepository.findByName(name);
    }

    public void addCompany(String name) {
        Company company = new Company(name);
        companyRepository.save(company);
    }

    public void addStockItem(Company company, Product product, double price) {
        StockItemRelationship rel = new StockItemRelationship(company, product, price);
        product.addCompany(rel);
        company.addStockItem(rel);
        companyRepository.save(company);
    }

    public void addCustomer(Company company, Person person) {
        CustomerRelationship rel = new CustomerRelationship(company, person);
        company.addCustomer(rel);
        companyRepository.save(company);
    }

    public Stream<Company> streamAllCompanies() {
        return companyRepository.streamAll();
    }
}
