package br.pro.delfino.drogaria.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.pro.delfino.drogaria.dao.FabricanteDAO;
import br.pro.delfino.drogaria.domain.Fabricante;

@Path("fabricante") //http://localhost:8080/Drogaria/rest/fabricante
public class FabricanteService {
	
	@GET //http://localhost:8080/Drogaria/rest/fabricante
	public String listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		
		List<Fabricante> fabricantes = fabricanteDAO.listar("descricao");
		
		Gson gson = new Gson();
		
		String json = gson.toJson(fabricantes);
		
		return json; // retornei o arquivo convertido
	}
	
	@GET //http://localhost:8080/Drogaria/rest/fabricante/10 (exemplo)
	@Path("{codigo}")
	public String buscar(@PathParam("codigo") Long codigo) {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		Gson gson = new Gson();
		
		String json = gson.toJson(fabricante);
		
		return json;
	}
	
	//http://localhost:8080/Drogaria/rest/fabricante (exemplo)
	@POST // não precisa colocar o "path" pois essa string não vai ser injetada por meio de parametro
	public String salvar(String json) { // salva e edita adicionando o campo codigo
		Gson gsonEntrada = new Gson();
		Fabricante fabricante = gsonEntrada.fromJson(json, Fabricante.class); // a string json, tipo da classe para converter
	
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.merge(fabricante);
		
		String jsonSaida = gsonEntrada.toJson(fabricante);
		
		return jsonSaida;
	} // da para fazer ediar e salvar eme emtodos difereentes : post (salvar) e put (editar)
	
	@DELETE
	public String excluir(String json) { // ele vai deletar apenas passando o código sozinho como parâmetro no json
		Gson gsonEntrada = new Gson();
		Fabricante fabricante = gsonEntrada.fromJson(json, Fabricante.class); 
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricante = fabricanteDAO.buscar(fabricante.getCodigo()); 
		fabricanteDAO.excluir(fabricante);
		
		String jsonSaida = gsonEntrada.toJson(fabricante);
		
		return jsonSaida;
	}
}
