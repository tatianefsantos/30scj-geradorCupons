package br.com.fiap.geradorCupons.persistense.repository;

import br.com.fiap.geradorCupons.persistense.model.Item;
import br.com.fiap.geradorCupons.persistense.model.Pedidos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensRepo extends CrudRepository<Item, Long> {
}
