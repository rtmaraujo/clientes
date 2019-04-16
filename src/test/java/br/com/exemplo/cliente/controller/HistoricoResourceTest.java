package br.com.exemplo.cliente.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.exemplo.cliente.controller.HistoricoResource;
import br.com.exemplo.cliente.model.Historico;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HistoricoResourceTest {

	@Autowired
	private HistoricoResource historicoResource;
	
	@Test
	public void testListar() {
		List<Historico> lista = historicoResource.listar();
		assertEquals(3, lista.size());
		Historico historico = lista.get(0);
		assertEquals("Bahia", historico.getLocal());
		assertEquals(Double.parseDouble("30"), historico.getTempMinima(), 0.1);
		assertEquals(Double.parseDouble("33"), historico.getTempMaxima(), 0.1);
	}
}

