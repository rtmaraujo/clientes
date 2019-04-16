package br.com.exemplo.cliente.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.exemplo.cliente.controller.ClienteResource;
import br.com.exemplo.cliente.model.Cliente;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteResourceTest {
	
	@Autowired
	private ClienteResource clienteResource;

	@Test
	public void testAdicionarCliente() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlterarCliente() {
		fail("Not yet implemented");
	}

	@Test
	public void testConsultarCliente() {
		ResponseEntity<?> consultarCliente = clienteResource.consultarCliente(1L);
		Object cliente = consultarCliente.getBody();
	}

	@Test
	public void testListar() {
		List<Cliente> lista = clienteResource.listar();
		assertEquals(3, lista.size());
		Cliente cliente = lista.get(0);
		assertEquals(Long.parseLong("1"), cliente.getId(), 0.1);
		assertEquals("Carlos Eduardo", cliente.getNome());
		assertEquals(23, cliente.getIdade());
	}

	@Test
	public void testRemoverCliente() {
		fail("Not yet implemented");
	}

}
