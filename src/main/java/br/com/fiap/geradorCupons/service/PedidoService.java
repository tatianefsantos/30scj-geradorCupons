package br.com.fiap.geradorCupons.service;

import br.com.fiap.geradorCupons.persistense.model.Pedidos;
import br.com.fiap.geradorCupons.persistense.repository.PedidosRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class PedidoService {

	@Autowired
	private PedidosRepo pedidosRepo;

	@Autowired
    private EntityManager em;

	org.apache.log4j.Logger logger = Logger.getLogger(PedidoService.class);

	public void novoPedido(Pedidos pedido) {
		pedidosRepo.save(pedido);
	}

	@Cacheable(value = "buscaPedidoCache")
	public Pedidos buscarPedidoPorId(long id) throws Exception {
		Pedidos pedido = em.find(Pedidos.class, id);
		if (pedido == null)
			throw new Exception("Pedido não encontrado!");
		return pedido;
	}

	public List<Pedidos> buscarMesFevereiro() {
		try {
			TypedQuery<Pedidos> query = em.createQuery(
					"select p from Pedidos p where Month(p.data) = :mes and Year(p.data.) = :ano",
					Pedidos.class);
			query.setParameter("mes", 2);
			query.setParameter("ano", 2018);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
