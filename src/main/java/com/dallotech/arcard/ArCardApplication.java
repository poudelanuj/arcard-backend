package com.dallotech.arcard;

import com.dallotech.arcard.config.AppProperties;
import com.dallotech.arcard.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = {"com.dallotech.arcard"}, exclude = {SecurityAutoConfiguration.class})
@EnableConfigurationProperties({AppProperties.class,FileStorageProperties.class})
public class ArCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArCardApplication.class, args);
	}

}
