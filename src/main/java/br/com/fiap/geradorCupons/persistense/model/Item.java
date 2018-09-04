package br.com.fiap.geradorCupons.persistense.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "itens", schema = "fiapRoupas")
@SuppressWarnings("serial")
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iditem")
	private int id;

	@Column(name = "quantidade")
	private int quantidade;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "valor")
	private double valor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idpedido")
	private Pedidos pedido;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() { return valor; }

	public void setValor(double valor) { this.valor = valor; }

	public Pedidos getPedido() {
		return pedido;
	}

	public void setPedido(Pedidos pedido) {
		this.pedido = pedido;
	}
}