package com.alway.carrierpigeon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.UnknownHostException;

/**
 * @author alway
 */
@SpringBootApplication
public class CarrierpigeonApplication {
	private static Logger logger = LoggerFactory.getLogger(CarrierpigeonApplication.class);

	public static void main(String[] args) throws UnknownHostException {

		ConfigurableApplicationContext application = SpringApplication.run(CarrierpigeonApplication.class, args);
		Environment env = application.getEnvironment();
		logger.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! Access URLs:\n\t" +
						"Local:     \thttp://localhost:{}\n" +
						"----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				env.getProperty("server.port"));
	}
}