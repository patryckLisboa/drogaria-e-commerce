package br.pro.delfino.drogaria.bean;


import java.io.Serializable;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.pro.delfino.drogaria.dao.EstadoDAO;
import br.pro.delfino.drogaria.domain.Estado;



@SuppressWarnings("serial")
@ManagedBean // diz que essa classe trata do controle o do modelo dentro dessa classe
@ViewScoped // tempo de vida dos objetos de estados é view e ficam vivos em quanto estou na tela de estados
public class EstadoBean implements Serializable {
	private Estado estado; // atributos para guardar os dados da tela
	private List<Estado> estados;
	
	public void salvar() {

		try {
			//EstadoDAO estadoDAO = new EstadoDAO();
			//estadoDAO.merge(estado);
			//novo(); // para reiniciar o objeto
			//estados = estadoDAO.listar(); // para relistar a tabela
			
			Client cliente = ClientBuilder.newClient(); //primeiro cria o cliente
			WebTarget caminho = cliente.target("http://localhost:8080/Drogaria/rest/estado"); // depois cria o caminho
			
			Gson gson = new Gson(); // essas linhas fazem o merge
			String json = gson.toJson(estado);
			caminho.request().post(Entity.json(json)); // entity do ws.rs.client para enviar o a entidade json no post
			
			estado = new Estado();
									//para deletar é só podr .delete
			json = caminho.request().get(String.class); // essas 3 linhas atualisam os estados (listar)
			Estado[] vetorDeEstados = gson.fromJson(json, Estado[].class);
			estados = Arrays.asList(vetorDeEstados);

			Messages.addGlobalInfo("Salvo com sucesso!"); // do omnifaces.util
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o objeto");
			erro.printStackTrace();
		}

		
		
//		String texto = "Programação web com java";
//		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_FATAL, texto, texto); // o tipo da mensagem e o texto dela (curto e longo)
//		//FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, texto); 
//		
//		FacesContext contexto = FacesContext.getCurrentInstance();  // vai capturar a instancia que está rodando so jsf
//		
//		contexto.addMessage(null, mensagem); // vai adicionar a mensagem que eu quero que exiba no "contexto"
//		
		// para não precisar fazer tudo na mão, ficaria assim:
	}
	
	public void novo() {
		estado = new Estado();
	}
	
	@PostConstruct // é chamado logo depois do construtor da classe. depois que o estadoBean é criado (ai já vai listar)
	public void listar() {
		try { // sempre que trabalhar com visão deve-se usar um try catch
			
			// EstadoDAO estadoDAO = new EstadoDAO();
			// estados = estadoDAO.listar();
			///---------------implementando restfull
			
			Client cliente = ClientBuilder.newClient();// rs.client
			WebTarget caminho = cliente.target("http://localhost:8080/Drogaria/rest/estado"); //alvo, caminho, target, path
			String json = caminho.request().get(String.class); // caminho.requisição para chamar a reqisição . protocolo 
																//get passando o tipo e isso vai tetornar um json
			Gson gson = new Gson();
			
			Estado[] vetorDeEstados = gson.fromJson(json, Estado[].class);// vai converter o resultado em um vetor de fabricantes
			
			estados = Arrays.asList(vetorDeEstados);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os objetos");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
			
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.excluir(estado);
			
			estados = estadoDAO.listar();
			
			Messages.addGlobalInfo("Objeto excluído com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar remover o objeto");
			erro.printStackTrace();
		}
	} 
	
	public void editar(ActionEvent evento) {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
	} 
	
	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
}

