package hospital;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class HospitalApplication {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();
        String dbHost = dotenv.get("DB_HOST"); 

        System.out.println("--- DEBUG ---");
        System.out.println("Loaded DB_HOST from .env: " + dbHost);
        System.out.println("---------------");

        SpringApplication app = getSpringApplication(dbHost);

        app.run(args);
    }

    private static SpringApplication getSpringApplication(String dbHost) {
        SpringApplication app = new SpringApplication(HospitalApplication.class);

        Map<String, Object> properties = new HashMap<>();
        properties.put("spring.data.mongodb.uri", dbHost);
        properties.put("spring.data.mongodb.database", "hospital"); 
        properties.put("spring.jmx.enabled", false); 

        app.setDefaultProperties(properties);
        return app;
    }
}