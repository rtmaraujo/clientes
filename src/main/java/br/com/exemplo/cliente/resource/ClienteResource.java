package br.com.exemplo.cliente.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.exemplo.cliente.model.Cliente;
import br.com.exemplo.cliente.model.Historico;
import br.com.exemplo.cliente.repository.IClienteRepository;
import br.com.exemplo.cliente.repository.IHistoricoRepository;
import br.com.exemplo.cliente.service.IGerenciador;

/**
 * 
 * @author Marcos Araujo
 *
 */
@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private IHistoricoRepository historicoRepo;
	
	@Autowired
	private IGerenciador gerenciador;
	
	@PostMapping
	public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente, HttpServletRequest request) {
		Cliente clienteNovo = clienteRepository.save(cliente);
		historicoRepo.save(gerenciador.prepararHistorico(request, clienteNovo));		
		return clienteNovo;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> alterarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		Cliente existeCliente = clienteRepository.findOne(id);
		if (existeCliente == null) {
			return ResponseEntity.notFound().build();
		}
		BeanUtils.copyProperties(cliente, existeCliente, "id");
		existeCliente = clienteRepository.save(existeCliente);
		
		return ResponseEntity.ok(existeCliente);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> consultarCliente(@PathVariable Long id) {
		Cliente cliente = clienteRepository.findOne(id);
		
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removerCliente(@PathVariable Long id) {
		Cliente cliente = clienteRepository.findOne(id);
		
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		Historico historico = historicoRepo.buscarPorIdCliente(cliente);
		if (historico != null) {
			historicoRepo.delete(historico);
		}
		clienteRepository.delete(cliente);
		
		return ResponseEntity.ok().build();
	}
}
