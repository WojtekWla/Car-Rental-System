package pl.edu.pjwstk.masfinalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.edu.pjwstk.masfinalproject.test.InsertToDatabase;

@SpringBootApplication
public class MasFinalProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MasFinalProjectApplication.class);
		InsertToDatabase insertToDatabase = context.getBean(InsertToDatabase.class);

		insertToDatabase.insertData();

//		insertToDatabase.getData();
	}

}
