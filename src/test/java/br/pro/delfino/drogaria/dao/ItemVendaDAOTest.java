package br.pro.delfino.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.ItemVenda;
import br.pro.delfino.drogaria.domain.Produto;
import br.pro.delfino.drogaria.domain.Venda;

public class ItemVendaDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(new Long("2")); // pode ser 2L (atalho)
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(2L);
		
		ItemVenda itemVenda = new ItemVenda();
		itemVenda.setPrecoParcial(new BigDecimal("2.40"));
		itemVenda.setProduto(produto);
		itemVenda.setQuantidade(new Short("2"));
		itemVenda.setVenda(venda);
		
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		itemVendaDAO.salvar(itemVenda);
	}	
	
	@Test
	@Ignore
	public void listar() {
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		List<ItemVenda> resultado = itemVendaDAO.listar();
		
		for (ItemVenda itemVenda : resultado) {
			System.out.println(itemVenda.getPrecoParcial());
			System.out.println(itemVenda.getProduto().getDescricao());
			System.out.println(itemVenda.getQuantidade());
			System.out.println(itemVenda.getVenda().getCodigo());
			System.out.println();
		}	
	}
	
	@Test
	@Ignore
	public void buscar() {
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(1L);
		
		System.out.println(itemVenda.getPrecoParcial());
		System.out.println(itemVenda.getProduto().getDescricao());
		System.out.println(itemVenda.getQuantidade());
		System.out.println(itemVenda.getVenda().getCodigo());
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(1L);
		
		itemVendaDAO.excluir(itemVenda);
		
		System.out.println("Item excluido:");
		System.out.println(itemVenda.getPrecoParcial());
		System.out.println(itemVenda.getProduto().getDescricao());
		System.out.println(itemVenda.getQuantidade());
		System.out.println(itemVenda.getVenda().getCodigo());
	}
	
	@Test
	@Ignore
	public void editar() {
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(1L);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(new Long("2"));
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(2L);
		
		System.out.println("Antes De editar:");
		System.out.println(itemVenda.getPrecoParcial());
		System.out.println(itemVenda.getProduto().getDescricao());
		System.out.println(itemVenda.getQuantidade());
		System.out.println(itemVenda.getVenda().getCodigo());
		
		
		itemVenda.setPrecoParcial(new BigDecimal("4.20"));
		itemVenda.setProduto(produto);
		itemVenda.setQuantidade(new Short("4"));
		itemVenda.setVenda(venda);
		
		itemVendaDAO.editar(itemVenda);
		
		System.out.println("Depois de editar:");
		System.out.println(itemVenda.getPrecoParcial());
		System.out.println(itemVenda.getProduto().getDescricao());
		System.out.println(itemVenda.getQuantidade());
		System.out.println(itemVenda.getVenda().getCodigo());
		
	}
	
}


