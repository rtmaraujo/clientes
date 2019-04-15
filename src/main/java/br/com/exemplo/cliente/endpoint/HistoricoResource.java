package br.com.exemplo.cliente.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.exemplo.cliente.model.Historico;
import br.com.exemplo.cliente.repository.IHistoricoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Marcos Araujo
 *
 */

@RestController
@RequestMapping("/historico")
@Api(value = "HistoricoResource")
public class HistoricoResource {
	
	@Autowired
	private IHistoricoRepository historicoRepository;
	
	@GetMapping("/listar")
	@ApiOperation(value = "Listar Historico", notes = "Essa operação retorna uma lista de historico.")
	public List<Historico> listar() {
		return historicoRepository.findAll();
	}
}
