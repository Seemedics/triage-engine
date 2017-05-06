package seemedics;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import seemedics.serializer.ModelSerializer;

/**
 * @author victorp
 */
@SpringBootApplication(scanBasePackages = "/seemedics")
public class TriageApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TriageApp.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return ModelSerializer.mapper;
    }

}