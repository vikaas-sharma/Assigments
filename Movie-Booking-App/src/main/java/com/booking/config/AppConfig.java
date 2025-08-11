package com.booking.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class AppConfig {
	
	
	@Value("${techacademy.openapi.dev-url}")
	private String devUrl;
 
	@Value("${techacademy.openapi.prod-url}")
	private String prodUrl;
 
	@Bean
	public OpenAPI myOpenAPI() {
		Server devServer = new Server();
		devServer.setUrl(devUrl);
		devServer.setDescription("Server URL in Development environment");
 
		Server prodServer = new Server();
		prodServer.setUrl(prodUrl);
		prodServer.setDescription("Server URL in Production environment");
 
		Contact contact = new Contact();
		contact.setEmail("XXXX@gmail.com");
		contact.setName("XXXx");
		contact.setUrl("https://www.XXXX.com");
 
		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
 
		Info info = new Info().title("Ecommerce REST API").version("1.0").contact(contact)
				.description("This API exposes endpoints to manage ECOMMERCE OPERATIONS.")
				.termsOfService("https://www.XXXX.com/terms").license(mitLicense);
 
		return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
	}
	
	@Bean
    public DateParser dateParser() {
        return new DateParser();
    }

    public static class DateParser {
        private static final DateTimeFormatter[] SUPPORTED_FORMATS = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy")
        };

        public LocalDate parse(String dateStr) {
            for (DateTimeFormatter formatter : SUPPORTED_FORMATS) {
                try {
                    return LocalDate.parse(dateStr, formatter);
                } catch (DateTimeParseException ignored) {
                	
                }
            }
            throw new IllegalArgumentException("Invalid date format: " + dateStr);
        }
    }

}
