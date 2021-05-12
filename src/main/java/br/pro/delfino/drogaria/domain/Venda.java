package br.pro.delfino.drogaria.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Venda extends GenericDomain {

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP) // para data e hora
	private Date horario;

	@Column(nullable = false, precision = 7, scale = 2) // 7 numeros totais sendo 2 depois da virgula
	private BigDecimal precoTotal;

	@ManyToOne
	// @JoinColumn(nullable = false) // pra que não tenha venda orfã (não precisa,
	// motivo a baixo)
	private Cliente cliente; // mudar no modelo relacional (pode ter venda sem cliente) em casos de ser
								// compra a vista
								// muda a setinha preenchida para setinha branca, para falar que a venda pode
								// não ter cliente
	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
