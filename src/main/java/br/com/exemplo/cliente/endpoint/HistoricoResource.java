package br.com.exemplo.cliente.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.exemplo.cliente.model.Historico;
import br.com.exemplo.cliente.repository.IHistoricoRepository;

/**
 * 
 * @author Marcos Araujo
 *
 */

@RestController
@RequestMapping("/historico")
public class HistoricoResource {
	
	@Autowired
	private IHistoricoRepository historicoRepository;
	
	@GetMapping("/listar")
	public List<Historico> listar() {
		return historicoRepository.findAll();
	}
}
