package br.com.fiap.geradorCupons.jms;

import br.com.fiap.geradorCupons.controller.PedidoController;
import br.com.fiap.geradorCupons.persistense.model.Pedidos;
import br.com.fiap.geradorCupons.utils.GeradorCupom;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Consumidor {

	@Autowired
	PedidoController pedidoController;

	Logger logger = Logger.getLogger(Consumidor.class);

	@JmsListener(destination = "queue.cupom")
	public void receive(String msg) throws Exception {
		try {
			int id = Integer.parseInt(msg);
			if (id == -1) { // list all from february
				List<Pedidos> pedidos = pedidoController.buscarPedidosMesFevereiro();
				pedidos.forEach(item->{
					GeradorCupom.generate(item);
				});
			} else if (id > 0) {
				Pedidos pedido = pedidoController.buscarPedido(id);
				GeradorCupom.generate(pedido);
			}
		} catch (NumberFormatException nfe) {
			throw new Exception("O número de pedido é inválido.");
		}
	}
}
