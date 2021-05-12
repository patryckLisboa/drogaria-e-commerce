package br.pro.delfino.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial") //pra resolver o problema
@Entity  //diz que o estado é  uma entidade do hibernate (gera uma tabela) (nome da tabela vai ser o nome da classe)
																		// (nome das colunas vai ser o nome dos atrigutos)
																		// colunas: Código que vem por erança, sigla e nome
public class Estado extends GenericDomain {
	@Column(length = 2, nullable = false)     // precisa ser do javax persistence para falar que é do banco de dados
	private String sigla;
	
	@Column(length = 50, nullable = false)  //falo que o tamanho da tabela é 50 e é um campo obrigatório
	private String nome;  

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
