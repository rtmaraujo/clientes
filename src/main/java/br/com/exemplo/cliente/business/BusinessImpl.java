package br.com.exemplo.cliente.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.exemplo.cliente.model.Cliente;
import br.com.exemplo.cliente.repository.IHistoricoRepository;
import br.com.exemplo.cliente.service.IGerenciador;

@Component
public class BusinessImpl implements IBusiness {
	
	@Autowired
	private IHistoricoRepository historicoRepo;
	
	@Autowired
	private IGerenciador gerenciador;
	
	public void x(String header1, String header2, String remoteAddr, Cliente clienteNovo) {
		historicoRepo.save(gerenciador.prepararHistorico(header1, header2, remoteAddr, clienteNovo));
	}
}
