package br.com.fiap.geradorCupons.persistense.repository;

import br.com.fiap.geradorCupons.persistense.model.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidosRepo extends CrudRepository<Pedidos, Long> {
}
