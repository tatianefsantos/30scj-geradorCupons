package br.com.fiap.geradorCupons.controller;

import br.com.fiap.geradorCupons.jms.Produtor;
import br.com.fiap.geradorCupons.mockDados.PopulaDB;
import br.com.fiap.geradorCupons.persistense.model.Pedidos;
import br.com.fiap.geradorCupons.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public final class PedidoController {

	public PedidoController() {
		super();
	}

	@Autowired
	PedidoService pedidoService;

	@Autowired
	Produtor produtor;

	@GetMapping(path = "/populadb")
	public void createOrder() {
		PopulaDB.gerarPedidos(pedidoService);
	}
	
	public Pedidos buscarPedido(long id) throws Exception {
		return pedidoService.buscarPedidoPorId(id);
	}
	
	public List<Pedidos> buscarPedidosMesFevereiro(){
		return pedidoService.buscarMesFevereiro();
	}

	@RequestMapping(path = "/cupom/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(code=HttpStatus.OK)
	public boolean gerarCupom(@PathVariable("id") long id) {
		produtor.send(String.valueOf(id));
		return true;
	}

}
