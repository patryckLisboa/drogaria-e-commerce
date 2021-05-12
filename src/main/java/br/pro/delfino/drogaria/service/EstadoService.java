package br.pro.delfino.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.pro.delfino.drogaria.dao.EstadoDAO;

import br.pro.delfino.drogaria.domain.Estado;



@Path("estado") //http://localhost:8080/Drogaria/rest/estado
public class EstadoService {
	
	@GET //http://localhost:8080/Drogaria/rest/fabricante
	public String listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		
		List<Estado> estados = estadoDAO.listar("nome");
	
		Gson gson = new Gson();
		
		String json = gson.toJson(estados);
		
		return json;
	}
	//http://localhost:8080/Drogaria/rest/estado (exemplo)
	@POST // não precisa colocar o "path" pois essa string não vai ser injetada por meio de parametro
	public String salvar(String json) { // salva e edita adicionando o campo codigo
		Gson gsonEntrada = new Gson();
		Estado estado = gsonEntrada.fromJson(json, Estado.class); // a string json, tipo da classe para converter
	
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.merge(estado);
		
		String jsonSaida = gsonEntrada.toJson(estado);
		
		return jsonSaida;
	} 
}
