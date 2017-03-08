package seemedics.triage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author victorp
 */
@SpringBootApplication(scanBasePackages = "/seemedics/triage")
public class TriageApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TriageApp.class, args);
    }

}