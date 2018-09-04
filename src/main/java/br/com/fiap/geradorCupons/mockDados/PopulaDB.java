package br.com.fiap.geradorCupons.mockDados;

import br.com.fiap.geradorCupons.persistense.model.Item;
import br.com.fiap.geradorCupons.persistense.model.Pedidos;
import br.com.fiap.geradorCupons.service.PedidoService;
import br.com.fiap.geradorCupons.utils.RandonUtils;

import java.util.HashSet;
import java.util.Set;

public class PopulaDB {

	private static String[] modelos = { "Camiseta", "Babylook", "Casaco", "Camisa polo" };
	private static String[] tam = { "P", "M", "G", "GG", "XG", "XXG" };
	private static int QTD_ITENS = 5;
	
	public static void gerarPedidos(PedidoService pedidoService) {

        try {
            for (int i = 0; i < 100; i++) {
                Pedidos pedido = new Pedidos();
                pedido.setData(RandonUtils.getDataRandom());

                Set<Item> items = new HashSet<>();
                int numitens = RandonUtils.getRandomInt(QTD_ITENS);
                double valor = 0;

                for (int j = 0; j < numitens; j++) {
                    Item item = new Item();
                    item.setPedido(pedido);
                    item.setDescricao(
                            modelos[RandonUtils.getRandomInt(3)] + " " + tam[RandonUtils.getRandomInt(5)]);
                    item.setValor(RandonUtils.getRandomDouble(100));
                    item.setQuantidade(RandonUtils.getRandomInt(5));
                    if(item.getQuantidade() == 0) {
                        item.setQuantidade(1);
                    }

                    items.add(item);
                    valor += item.getValor();
                }
                pedido.setItens(items);
                pedido.setValor(valor);

                pedidoService.novoPedido(pedido);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
