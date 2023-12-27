package com.bank.app.bankappusingspringboot.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class BankingAppConfiguration 
{
	@Bean
	public OpenAPI swaggerDocOpenApi()
	{
		Server developmentserver = new Server();
		developmentserver.setUrl("http://localhost:8080");
		developmentserver.setDescription("this is for development");
		
		Server productionserver = new Server();
		productionserver.setUrl("http://localhost:8080");
		productionserver.setDescription("this is for prodyuction");
		
		Contact contact =new Contact();
		contact.setName("Banking Application");
		contact.setEmail("help.bank.in");
		contact.setUrl("https://www.sbi.in");
		
		License license = new License();
		license.setName("Two Years License");
		license.setUrl("write license provider's url");
		
		Info info= new Info();
		info.title("State Bank of India");
		info.version("1.0");
		info.contact(contact);
		info.description("Designed For Banking");
		info.termsOfService("pass url");
		info.license(license);
		
		
		OpenAPI OpenApi = new OpenAPI();
		OpenApi.info(info);
		OpenApi.servers(Arrays.asList(developmentserver,productionserver));
		
		return OpenApi;
		
	}

}
