package br.com.exemplo.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 
 * @author Marcos Araujo
 *
 */

@SpringBootApplication
public class ClienteApplication{

	public static void main(String[] args) {
		
		ApplicationContext ctx = SpringApplication.run(ClienteApplication.class, args);
		
		DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
	}
}