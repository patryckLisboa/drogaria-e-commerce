package br.pro.delfino.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogaria.dao.CidadeDAO;
import br.pro.delfino.drogaria.dao.EstadoDAO;
import br.pro.delfino.drogaria.dao.PessoaDAO;
import br.pro.delfino.drogaria.domain.Cidade;
import br.pro.delfino.drogaria.domain.Estado;
import br.pro.delfino.drogaria.domain.Pessoa;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable{
	
	private List<Pessoa> pessoas;
	private Pessoa pessoa;
	private Estado estado;
	private List<Cidade> cidades;
	private List<Estado> estados;
	
	@PostConstruct
	public void listar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			setPessoas(pessoaDAO.listar("nome"));
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao listar os objetos");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);
			
			pessoas = pessoaDAO.listar("nome");
			
			novo();
			Messages.addFlashGlobalInfo("Produto salvo com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao salvar objeto");
			erro.printStackTrace();
		}
	}
	
	
	public void excluir(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
			
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);
			
			pessoas = pessoaDAO.listar();
			
			Messages.addGlobalInfo("Objeto excluído com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar remover o objeto");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
			
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.buscarPorEstado(pessoa.getCidade().getEstado().getCodigo());
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");
			estado = pessoa.getCidade().getEstado();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar selecionar um produto");
			erro.printStackTrace();
		}
		
	}
	
	
	public void novo() {
		pessoa = new Pessoa();
		
		estado = new Estado();
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");
			cidades = new ArrayList<Cidade>(); // cidades começa vazio
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao gerar novo estado");
			erro.printStackTrace();
		}
		
		
	}
	
	public void popular() {
		try {

			if(estado != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				
				cidades = cidadeDAO.buscarPorEstado(estado.getCodigo()); 
				
			}else {
				cidades = new ArrayList<>(); //  não precisa colocar o "Cidade" dentro do diamante para o tipo. o java entende
			}
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao gerar novo estado");
			erro.printStackTrace();		
		}

	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}