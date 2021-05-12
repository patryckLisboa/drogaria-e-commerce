package br.pro.delfino.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Fabricante;
import br.pro.delfino.drogaria.domain.Produto;

public class ProdutoDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(new Long("66")); // pode ser 2L (atalho)
		
		Produto produto = new Produto();
		produto.setDescricao("Ritalina 50 mn com 20 comp");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("10.70")); //campos do tipo real
		produto.setQuantidade(new Short("8"));
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
	}
	
	@Test
	@Ignore
	public void listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> resultado = produtoDAO.listar();
		
		for (Produto produto: resultado) {
			System.out.println("Código do produto: "+ produto.getCodigo());
			System.out.println("Descrição do produto: "+ produto.getDescricao());
			System.out.println("Quantidade: "+ produto.getQuantidade());
			System.out.println("Preço: "+ produto.getPreco());
			System.out.println("Descrição do fabricante: "+ produto.getFabricante().getDescricao());
			System.out.println("Código do fabricante: "+ produto.getFabricante().getCodigo());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		Long codigoProduto = 2L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigoProduto);
		
		System.out.println("Código do produto: "+ produto.getCodigo());
		System.out.println("Descrição do produto: "+ produto.getDescricao());
		System.out.println("Quantidade: "+ produto.getQuantidade());
		System.out.println("Preço: "+ produto.getPreco());
		System.out.println("Descrição do fabricante: "+ produto.getFabricante().getDescricao());
		System.out.println("Código do fabricante: "+ produto.getFabricante().getCodigo());
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigoProduto = 2L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigoProduto);
		
		produtoDAO.excluir(produto);
		
		System.out.println("Produto removido:");
		System.out.println("Código do produto: "+ produto.getCodigo());
		System.out.println("Descrição do produto: "+ produto.getDescricao());
		System.out.println("Quantidade: "+ produto.getQuantidade());
		System.out.println("Preço: "+ produto.getPreco());
		System.out.println("Descrição do fabricante: "+ produto.getFabricante().getDescricao());
		System.out.println("Código do fabricante: "+ produto.getFabricante().getCodigo());
		
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigoProduto = 2L;
		Long codigoFabricante = 3L;
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigoProduto);
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigoFabricante);
		
		System.out.println("Produto a ser editado:-------------");
		System.out.println("Código do produto: "+ produto.getCodigo());
		System.out.println("Descrição do produto: "+ produto.getDescricao());
		System.out.println("Quantidade: "+ produto.getQuantidade());
		System.out.println("Preço: "+ produto.getPreco());
		System.out.println("Descrição do fabricante: "+ produto.getFabricante().getDescricao());
		System.out.println("Código do fabricante: "+ produto.getFabricante().getCodigo());
		
		
		produto.setDescricao("Ritalina 50 mn com 20 comp");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("40.00")); //campos do tipo real
		produto.setQuantidade(new Short("4"));

		
		produtoDAO.editar(produto);
		
		System.out.println("Produto editado:-------------");
		System.out.println("Código do produto: "+ produto.getCodigo());
		System.out.println("Descrição do produto: "+ produto.getDescricao());
		System.out.println("Quantidade: "+ produto.getQuantidade());
		System.out.println("Preço: "+ produto.getPreco());
		System.out.println("Descrição do fabricante: "+ produto.getFabricante().getDescricao());
		System.out.println("Código do fabricante: "+ produto.getFabricante().getCodigo());
	}
	
}
