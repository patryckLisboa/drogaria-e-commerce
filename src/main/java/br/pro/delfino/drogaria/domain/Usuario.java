package br.pro.delfino.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain{
	
	@Column(length = 32, nullable = false) //32 pois na criptografia md5 hash vem um código de 32 caracteres para qualquer senha
	private String senha;
	
	@Column(nullable = false)
	private Character tipo;
	
	@Column(nullable = false)
	private Boolean ativo;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Character getTipo() {
		return tipo;
	}
	
	@Transient // javax persistence
	public String getTipoFormatado() {
		String tipoFormatado = null;
		
		switch (tipo) {
			case 'A': tipoFormatado = "Administrador"; break;
			case 'B': tipoFormatado = "Balconista"; break;
			case 'G': tipoFormatado = "Gerente"; break;
			default: break;
		}
		
		return tipoFormatado;
	}
	
	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
