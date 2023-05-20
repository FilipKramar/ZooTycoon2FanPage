package zootycoon.project.zoowebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ZoowebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZoowebsiteApplication.class, args);
	}

}
