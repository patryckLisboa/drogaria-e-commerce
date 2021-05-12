package br.pro.delfino.drogaria.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Cliente extends GenericDomain{  //conta de cliente 
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)  // para data apenas (pode ser data e hora ou hora ou só data)	
	private Date data;
	
	@Column(nullable = false)
	private Boolean liberado;
	
	@OneToOne  // a chave estrangeira sempre vai pro lado da "parte"
	@JoinColumn(nullable = false)
	private Pessoa pessoa;
	

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean getLiberado() {
		return liberado;
	}

	public void setLiberado(Boolean liberado) {
		this.liberado = liberado;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
