package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Estado;

public class EstadoDAOTest {
	
	@Test
	@Ignore
     // vai ignorar esse metodo
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Espirito Santo"); // se eu esquecer um dos campos, eu gero um erro e não adiciono no banco,  pois é nullable false. (não pode ter nulo)
		estado.setSigla("ES");
		
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado); // vai salvar o estado no banco
	}
	
	@Test 
	@Ignore
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();
		
		System.out.println("total de estados encontrados:" + resultado.size() +"/");
		
		for (Estado estado: resultado) {
			System.out.println(estado.getSigla() + "|" + estado.getNome() + "|" + estado.getCodigo() +".");
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L; // L de long para não achar que é int
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if(estado == null) {
			System.out.println("registro não encontrado");
		} else {
			System.out.println("registro encontrado:");
			System.out.println("Estado: " + estado.getSigla() + "|" + estado.getNome() + "|" + estado.getCodigo() +".");
		}
	}
	
	@Test
	@Ignore
	public void escluir() { // não pode excluir um pai com filhos
		Long codigo = 3L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo); // busca primeiro o que eu quero

		if(estado == null) {
			System.out.println("registro não encontrado");
		} else {
			estadoDAO.excluir(estado);
			System.out.println("registro excluido:");
			System.out.println("Estado: " + estado.getSigla() + "|" + estado.getNome() + "|" + estado.getCodigo() +".");
			// e ainda fala qual ele removeu
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigo = 5L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo); // essas tres linhas fazem a pesquisa

		if(estado == null) {
			System.out.println("registro não encontrado");
		} else {
			System.out.println("registro antes:");
			System.out.println("Estado: " + estado.getSigla() + "|" + estado.getNome() + "|" + estado.getCodigo() +".");
		
			estado.setSigla("SC");  // setei a sigla 
			estado.setNome("Santa Catarina"); // setei o nome
			estadoDAO.editar(estado);
			
			System.out.println("registro editado para:");
			System.out.println("Estado: " + estado.getSigla() + "|" + estado.getNome() + "|" + estado.getCodigo() +".");
			// e ainda fala qual ele editou
		}
	}
}
