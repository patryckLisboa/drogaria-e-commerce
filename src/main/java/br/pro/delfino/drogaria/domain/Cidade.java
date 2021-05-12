package br.pro.delfino.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Cidade extends GenericDomain { // herda para se ter uma chave primária
	@Column(length = 50, nullable = false)
	private String nome;

	@ManyToOne			//Com o nullable = false, eu falo que nao pode ter filho sem pai 1..*
	@JoinColumn(nullable = false) // não pode usar o @column para falar o nullable, a solução é essa aqui (usar o
									// joinColumn para chave estranjeira)
	private Estado estado; // vai ser chave estrangeira
							// era pra ser inteiro se fosse valor inteiro. agora se for framework orientado
							// a objeto relacional
							// a chave estrangeira é todo o estado, (obj que a cidade está ligado)

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
