package br.com.exemplo.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.exemplo.cliente.model.Cliente;

/**
 * 
 * @author Marcos Araujo
 *
 */

public interface IClienteRepository extends JpaRepository<Cliente, Long> {

}
