package br.com.exemplo.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 
 * @author Marcos Araujo
 *
 */

@SpringBootApplication
public class ClienteApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ClienteApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(ClienteApplication.class, args);
	}
}
