package pl.edu.agh.wiet.neo4jlab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.edu.agh.wiet.neo4jlab.model.results.CompanyRelationshipAggregate;
import pl.edu.agh.wiet.neo4jlab.populator.GraphPopulator;
import pl.edu.agh.wiet.neo4jlab.service.NodeRelationshipService;

@SpringBootApplication
@EnableTransactionManagement
public class App implements CommandLineRunner {

	private final GraphPopulator graphPopulator;
	private final NodeRelationshipService nodeRelationshipService;

	@Autowired
	public App(GraphPopulator graphPopulator, NodeRelationshipService nodeRelationshipService) {
		this.graphPopulator = graphPopulator;
		this.nodeRelationshipService = nodeRelationshipService;
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		graphPopulator.populate();

		CompanyRelationshipAggregate res = nodeRelationshipService.getAllCompanyRelationships("Januszex");
	}
}
