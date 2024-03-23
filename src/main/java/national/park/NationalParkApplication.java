package national.park;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//Tells JPA that this is a Spring Boot application. It
@SpringBootApplication
public class NationalParkApplication {

	public static void main(String[] args) {
		SpringApplication.run(NationalParkApplication.class, args);

	}
}