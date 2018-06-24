package br.com.exemplo.cliente;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.exemplo.cliente.model.Historico;

/**
 * 
 * @author Marcos Araujo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HistoricoTest {

	final static String URL_HISTORICO = "http://localhost:8080/historico/listar";

	@Test
	public void listarHistorico() throws JsonParseException, JsonMappingException, RestClientException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Historico> lista = mapper.readValue(new RestTemplate().getForObject(URL_HISTORICO, String.class),
				mapper.getTypeFactory().constructCollectionType(List.class, Historico.class));
		Assert.assertNotNull(lista);

	}
}
