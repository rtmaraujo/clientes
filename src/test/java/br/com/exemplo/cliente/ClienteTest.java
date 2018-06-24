package br.com.exemplo.cliente;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.exemplo.cliente.model.Cliente;
/**
 * 
 * @author Marcos Araujo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ClienteTest {

	final static String URL_CLIENTES = "http://localhost:8080/clientes";

	private RestTemplate restTemplate;
	
	private List<Cliente> lista;

	@Before
	public void init() throws JsonParseException, JsonMappingException, RestClientException, IOException {
		restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		lista = mapper.readValue(restTemplate.getForObject(URL_CLIENTES, String.class),
				mapper.getTypeFactory().constructCollectionType(List.class, Cliente.class));
		Assert.assertNotNull(lista);
	}

	@Test
	public void criarCliente() throws JsonProcessingException {
		Cliente response = restTemplate.postForObject(URL_CLIENTES, carregarCliente(20, "Mariana"), Cliente.class);
		Assert.assertEquals("Mariana 20", response.getNome() + " " + response.getIdade());
	}
	
	@Test
	public void alterarCliente() throws IOException{
	    restTemplate.put(URL_CLIENTES + "/" + lista.get(0).getId(), carregarCliente(50, "Moacir"));
	     
	    Cliente cliente2 = restTemplate.getForObject(URL_CLIENTES + "/" + lista.get(0).getId(), Cliente.class);
	    
	    Assert.assertNotNull(cliente2);
	    Assert.assertEquals("Moacir", cliente2.getNome());
	    Assert.assertEquals(50, cliente2.getIdade());
	}

	@Test
	public void buscarCliente() throws IOException {
		Cliente cliente = restTemplate.getForObject(URL_CLIENTES + "/" + lista.get(0).getId(), Cliente.class);
		Assert.assertNotNull(cliente);
		Assert.assertEquals("Roberto Silva", cliente.getNome());
		Assert.assertEquals(28, cliente.getIdade());
	}
	
	@Test
	public void deletarCliente() throws IOException{
	    restTemplate.delete(URL_CLIENTES + "/" + lista.get(0).getId(), lista.get(0));
	}

	private Cliente carregarCliente(int idade, String nome) {
		Cliente cliente = new Cliente();
		cliente.setIdade(idade);
		cliente.setNome(nome);
		return cliente;
	}

}
