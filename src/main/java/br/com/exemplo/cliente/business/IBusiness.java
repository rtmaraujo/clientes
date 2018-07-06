package br.com.exemplo.cliente.business;

import br.com.exemplo.cliente.model.Cliente;

public interface IBusiness {
	
	public void x(String header1, String header2, String remoteAddr, Cliente clienteNovo);
}
