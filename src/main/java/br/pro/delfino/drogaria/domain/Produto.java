package br.pro.delfino.drogaria.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Produto extends GenericDomain {

	@Column(length = 80, nullable = false) // not null
	private String descricao;

	@Column(nullable = false) // não precisa de tamanho
	private Short quantidade; // tipo primitivo tem letras minusculas (tem valores padrões zero)
								// classes tem letra maiusculas (objetos, cujo valor padrão é nulo)

	// precision é bom pra falar quantos digitos antes da virgula e depois 4 antes e
	// 2 depois = 6
	// scale (2 numeros depois da virgual)
	@Column(nullable = false, precision = 6, scale = 2) // se tiver Zero, eu não sei se foi o usuário que digitou, por
														// isso isso ajuda
	private BigDecimal preco; // Valores fracionarios mais exatos, valor real. bom para dinheiro
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Fabricante fabricante;
	
	@Transient  // esse caminho é uma variável do produto mais guarda informações temporarias
	private String caminho;
	
	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

}
