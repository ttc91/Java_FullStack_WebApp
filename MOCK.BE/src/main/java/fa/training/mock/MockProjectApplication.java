package fa.training.mock;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan("fa.training.mock.remotes.entities")
public class MockProjectApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MockProjectApplication.class, args);
		
	}
	
	@Bean
	public ModelMapper modelMapper() {
	 return new ModelMapper();
	}

}
