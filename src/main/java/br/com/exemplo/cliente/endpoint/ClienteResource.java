package br.com.exemplo.cliente.endpoint;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.exemplo.cliente.business.IBusiness;
import br.com.exemplo.cliente.error.CustomErrorType;
import br.com.exemplo.cliente.model.Cliente;
import br.com.exemplo.cliente.model.Historico;
import br.com.exemplo.cliente.repository.IClienteRepository;
import br.com.exemplo.cliente.repository.IHistoricoRepository;

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
	private IBusiness iBusiness;
	
	@PostMapping
	public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente, HttpServletRequest request) {
		Cliente clienteNovo = clienteRepository.save(cliente);
		iBusiness.x(request.getHeader("x-forwarded-for"), request.getHeader("X_FORWARDED_FOR"), request.getRemoteAddr(), clienteNovo);
		return clienteNovo;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> alterarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		Cliente existeCliente = clienteRepository.findOne(id);
		if (existeCliente == null) {
			return new ResponseEntity<>(new CustomErrorType("Cliente não encontrado id: "+ id), HttpStatus.NOT_FOUND);
		}
		BeanUtils.copyProperties(cliente, existeCliente, "id");
		existeCliente = clienteRepository.save(existeCliente);
		
		return ResponseEntity.ok(existeCliente);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> consultarCliente(@PathVariable Long id) {
		Cliente cliente = clienteRepository.findOne(id);
		if (cliente == null) {
			return new ResponseEntity<>(new CustomErrorType("Cliente não encontrado id: "+ id), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(cliente);
	}
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removerCliente(@PathVariable Long id) {
		Cliente cliente = clienteRepository.findOne(id);
		if (cliente == null) {
			return new ResponseEntity<>(new CustomErrorType("Cliente não encontrado id: "+ id), HttpStatus.NOT_FOUND);
		}
		Historico historico = historicoRepo.buscarPorIdCliente(cliente);
		if (historico != null) {
			historicoRepo.delete(historico);
		}
		clienteRepository.delete(cliente);
		
		return ResponseEntity.ok().build();
	}
}
