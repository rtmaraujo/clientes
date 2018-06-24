package br.com.exemplo.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.exemplo.cliente.model.Cliente;
import br.com.exemplo.cliente.model.Historico;

/**
 * 
 * @author Marcos Araujo
 *
 */

public interface IHistoricoRepository extends JpaRepository<Historico, Long> {

	@Query("SELECT h FROM Historico h WHERE h.cliente = ?1")
	public Historico buscarPorIdCliente(Cliente cliente);
}
