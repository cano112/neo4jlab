package pl.edu.agh.wiet.neo4jlab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.edu.agh.wiet.neo4jlab.dao.CompanyRepository;
import pl.edu.agh.wiet.neo4jlab.dao.StockItemRelationshipRepository;
import pl.edu.agh.wiet.neo4jlab.model.queries.company.CustomerQueryResult;
import pl.edu.agh.wiet.neo4jlab.model.queries.company.StockItemQueryResult;
import pl.edu.agh.wiet.neo4jlab.model.queries.company.WorkersQueryResult;
import pl.edu.agh.wiet.neo4jlab.model.queries.person.WorkerQueryResult;
import pl.edu.agh.wiet.neo4jlab.model.relationships.StockItemRelationship;
import pl.edu.agh.wiet.neo4jlab.populator.GraphPopulator;

import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
public class App implements CommandLineRunner {

	private final GraphPopulator graphPopulator;
	private final StockItemRelationshipRepository stockItemRelationshipRepository;

	@Autowired
	public App(GraphPopulator graphPopulator, StockItemRelationshipRepository stockItemRelationshipRepository) {
		this.graphPopulator = graphPopulator;
		this.stockItemRelationshipRepository = stockItemRelationshipRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		graphPopulator.populate();

		Iterable<StockItemRelationship> res = stockItemRelationshipRepository.findAll();
	}
}
