package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Cidade;
import br.pro.delfino.drogaria.domain.Estado;

public class CidadeDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		//antes de criar a cidade tenho que pesquisar o estado
		
		EstadoDAO estadoDAO = new EstadoDAO();
		
		Estado estado = estadoDAO.buscar(5L);
		
		//agora eu posso criar a cidade
		Cidade cidade = new Cidade();
		cidade.setNome("Rio de Janeiro");
		cidade.setEstado(estado); // setei o estado de codigo 1
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}
	
	@Test
	@Ignore
	public void listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.listar();
		
		for (Cidade cidade: resultado) {
			System.out.println("Código da cidade: "+ cidade.getCodigo());
			System.out.println("Nome da cidade: "+ cidade.getNome());
			System.out.println("Código do estado: "+ cidade.getEstado().getCodigo());
			System.out.println("Sigla do estado: "+ cidade.getEstado().getSigla());
			System.out.println("Nome do estado: "+ cidade.getEstado().getNome());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		Long codigo = 1L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
		System.out.println("Código da cidade: "+ cidade.getCodigo());
		System.out.println("Nome da cidade: "+ cidade.getNome());
		System.out.println("Código do estado: "+ cidade.getEstado().getCodigo());
		System.out.println("Sigla do estado: "+ cidade.getEstado().getSigla());
		System.out.println("Nome do estado: "+ cidade.getEstado().getNome());
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 1L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
		cidadeDAO.excluir(cidade);
		
		System.out.println("Cidade removoda:"+ cidade.getCodigo());
		System.out.println("Código da cidade: "+ cidade.getCodigo());
		System.out.println("Nome da cidade: "+ cidade.getNome());
		System.out.println("Código do estado: "+ cidade.getEstado().getCodigo());
		System.out.println("Sigla do estado: "+ cidade.getEstado().getSigla());
		System.out.println("Nome do estado: "+ cidade.getEstado().getNome());
		
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigoCidade = 2L;
		Long codigoEstado = 6L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigoEstado);
		
		System.out.println("Código do estado: "+ estado.getCodigo());
		System.out.println("Sigla do estado: "+ estado.getSigla());
		System.out.println("Nome do estado: "+ estado.getNome());
		

		
		System.out.println("Cidade A ser editada:-------------"+ cidade.getCodigo());
		System.out.println("Código da cidade: "+ cidade.getCodigo());
		System.out.println("Nome da cidade: "+ cidade.getNome());
		System.out.println("Código do estado: "+ cidade.getEstado().getCodigo());
		System.out.println("Sigla do estado: "+ cidade.getEstado().getSigla());
		System.out.println("Nome do estado: "+ cidade.getEstado().getNome());
		
		cidade.setNome("Guarapuava");
		cidade.setEstado(estado);

		
		cidadeDAO.editar(cidade);
		
		System.out	.println("Cidade editada:-------------"+ cidade.getCodigo());
		System.out.println("Código da cidade: "+ cidade.getCodigo());
		System.out.println("Nome da cidade: "+ cidade.getNome());
		System.out.println("Código do estado: "+ cidade.getEstado().getCodigo());
		System.out.println("Sigla do estado: "+ cidade.getEstado().getSigla());
		System.out.println("Nome do estado: "+ cidade.getEstado().getNome());
	}
	
	@Test
	
	public void buscarPorEstado() {
		Long estadoCodigo = 50L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.buscarPorEstado(estadoCodigo);
		
		for (Cidade cidade: resultado) {
			System.out.println("Código da cidade: "+ cidade.getCodigo());
			System.out.println("Nome da cidade: "+ cidade.getNome());
			System.out.println("Código do estado: "+ cidade.getEstado().getCodigo());
			System.out.println("Sigla do estado: "+ cidade.getEstado().getSigla());
			System.out.println("Nome do estado: "+ cidade.getEstado().getNome());
			System.out.println();
		}
	}
}
