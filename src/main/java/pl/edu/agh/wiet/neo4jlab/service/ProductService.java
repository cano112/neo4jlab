package pl.edu.agh.wiet.neo4jlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.neo4jlab.model.nodes.Product;
import pl.edu.agh.wiet.neo4jlab.dao.ProductRepository;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    public void addProduct(String name) {
        Product product = new Product(name);
        productRepository.save(product);
    }

    public Stream<Product> streamAllProducts() {
        return productRepository.streamAll();
    }
}
