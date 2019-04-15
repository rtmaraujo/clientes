package br.com.exemplo.cliente.endpoint;

import java.util.List;
import java.util.Optional;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author Marcos Araujo
 *
 */
@RestController
@RequestMapping("/clientes")
@Api(value = "ClienteResource")
public class ClienteResource {
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private IHistoricoRepository historicoRepo;
	
	@Autowired
	private IBusiness iBusiness;
	
	@PostMapping
	@ApiOperation(value = "Cadastrar um cliente", response = Cliente.class, notes = "Essa operação salva um novo registro com as informações do cliente.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um Cliente", response = Cliente.class),
			@ApiResponse(code = 500, message = "Caso tenhamos algum erro vamos retornar um Cliente com a Exception", response = Cliente.class)
	
	})
	public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente, HttpServletRequest request) {
		Cliente clienteNovo = clienteRepository.save(cliente);
		iBusiness.x(request.getHeader("x-forwarded-for"), request.getHeader("X_FORWARDED_FOR"), request.getRemoteAddr(),
				clienteNovo);
		return clienteNovo;
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Alterar um cliente", notes = "Essa operação altera um as informações do cliente.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna sucesso"),
			@ApiResponse(code = 500, message = "Caso tenhamos algum erro uma Exception")
	
	})
	public ResponseEntity<?> alterarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		Optional<Cliente> existeCliente = clienteRepository.findById(id);
		if (existeCliente == null) {
			return new ResponseEntity<>(new CustomErrorType("Cliente nao encontrado id: "+ id), HttpStatus.NOT_FOUND);
		}
		BeanUtils.copyProperties(cliente, existeCliente.get(), "id");
		
		return ResponseEntity.ok(clienteRepository.save(existeCliente.get()));
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Consultar um cliente", response= ResponseEntity.class, notes = "Essa operação consulta um cliente por id.")
	public ResponseEntity<?> consultarCliente(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente == null) {
			return new ResponseEntity<>(new CustomErrorType("Cliente não encontrado id: "+ id), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(cliente);
	}
	
	@GetMapping
	@ApiOperation(value = "Listar Clientes", notes = "Essa operação retorna uma lista de clientes.")
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remover Cliente", notes = "Essa operação remove um cliente por id.")
	public ResponseEntity<?> removerCliente(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		if (cliente == null) {
			return new ResponseEntity<>(new CustomErrorType("Cliente nao encontrado id: "+ id), HttpStatus.NOT_FOUND);
		}
		Historico historico = historicoRepo.buscarPorIdCliente(cliente.get());
		if (historico != null) {
			historicoRepo.delete(historico);
		}
		clienteRepository.delete(cliente.get());
		
		return ResponseEntity.ok().build();
	}
}
