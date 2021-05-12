package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;


import br.pro.delfino.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	
	@Test
	@Ignore
	public void Salvar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Droga Nira");
	
		
		FabricanteDAO fabricantedao = new FabricanteDAO();
		fabricantedao.salvar(fabricante);
	}
	
	@Test
	@Ignore
	public void listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> resultado = fabricanteDAO.listar();
		
		System.out.println("total de fabricantes encontrados" + resultado.size());
		
		for (Fabricante fabricante: resultado) {
			System.out.println(fabricante.getDescricao()  + "|" + fabricante.getCodigo() + ".");
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		Long codigo = 1L; // L de long para não achar que é int
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		
		if(fabricante == null) {
			System.out.println("registro não encontrado");
		} else {
			System.out.println("registro encontrado:");
			System.out.println(fabricante.getDescricao() + "|" + fabricante.getCodigo() + ".");
		}
	}
	
	@Test
	@Ignore
	public void escluir() {
		Long codigo = 2L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);;

		if(fabricante == null) {
			System.out.println("registro não encontrado");
		} else {
			fabricanteDAO.excluir(fabricante);
			System.out.println("registro encontrado:");
			System.out.println("Fabricante: " + fabricante.getDescricao() + "|" + fabricante.getCodigo() +".");
			// e ainda fala qual ele removeu
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigo = 2L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo); // essas tres linhas fazem a pesquisa

		if(fabricante == null) {
			System.out.println("registro não encontrado");
		} else {
			System.out.println("registro antes:");
			System.out.println("Fabricante: " + fabricante.getDescricao() + "|" + fabricante.getCodigo() +".");
		
			fabricante.setDescricao("Farma Forma");  // setei a descricao
			fabricanteDAO.editar(fabricante);
			
			System.out.println("registro editado para:");
			System.out.println("Fabricante: " + fabricante.getDescricao() + "|" + fabricante.getCodigo() +".");
			// e ainda fala qual ele editou
		}
	}
	
	@Test
	
	public void merge() { //merge incluir
//		Fabricante fabricante = new Fabricante();
//		fabricante.setDescricao("sarms.com");
////incerir	
//		FabricanteDAO fabricantedao = new FabricanteDAO();
//		fabricantedao.merge(fabricante);
//		
		Long codigo = 1L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo); // essas tres linhas fazem a pesquisa
		
		fabricante.setDescricao("sarms.org");  // se deixar o obj sem chave primaria ele vai incluir
												// se der uma chave primaria, ele vai verificar se já existe
												// e vai substituir
		fabricanteDAO.merge(fabricante);
	}
}
