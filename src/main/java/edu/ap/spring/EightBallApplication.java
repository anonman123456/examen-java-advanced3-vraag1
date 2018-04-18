package edu.ap.spring;

import edu.ap.spring.view.EightballFrame;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EightBallApplication {
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return (args) -> {

		};
    }
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(EightBallApplication.class).headless(false).run(args);
		EightballFrame appFrame = context.getBean(EightballFrame.class);
	}
}