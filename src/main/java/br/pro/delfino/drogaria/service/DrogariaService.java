package br.pro.delfino.drogaria.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

//http://localhost:8080/Drogaria/rest/[nome do repositório de serviços]   esse rest fica no DrogariaResourceConfig. é o nome do restfull

@Path("drogaria") // quando for chamar esse serviço, vai chamar por esse nome na url
public class DrogariaService { // um serviço é um método (voce envia ou não parametros e esse serviço devolve resposta)
	
	@GET // navegador sempre usa GET e se o protocolo for GET, ele vai chamar o exibir
	public String exibir() { // esse método vai serum serviço dentro do repositório "drogaria"
		return "curso de java";
	} // esse serviço não recebe parametro e retorna uma "String"
}
