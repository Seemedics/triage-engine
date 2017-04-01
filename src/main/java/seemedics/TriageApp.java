package seemedics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author victorp
 */
@SpringBootApplication(scanBasePackages = "/seemedics")
public class TriageApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TriageApp.class, args);
    }

}