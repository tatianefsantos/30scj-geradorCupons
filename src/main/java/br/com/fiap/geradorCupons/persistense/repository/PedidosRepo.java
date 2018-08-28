package br.com.fiap.geradorCupons.persistense.repository;

import br.com.fiap.geradorCupons.persistense.model.Pedidos;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosRepo extends JpaRepository<Pedidos, Long> {
    @Override
    List<Pedidos> findAll();

    @Override
    List<Pedidos> findAll(Sort sort);

    @Override
    List<Pedidos> findAllById(Iterable<Long> iterable);
}
