package br.pro.delfino.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Cliente;
import br.pro.delfino.drogaria.domain.Pessoa;

public class ClienteDAOTest {
	
	
	@Test
	@Ignore
	public void salvar() throws ParseException {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = new Cliente();
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(77L);
		
		cliente.setData(new SimpleDateFormat("dd/MM/yyyy").parse("10/06/2020"));
		cliente.setLiberado(true);
		cliente.setPessoa(pessoa);


		
		clienteDAO.salvar(cliente);
	}
	
	@Test
	@Ignore
	public void listar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> resultado = clienteDAO.listar();
	
		
		for (Cliente cliente : resultado) {
			System.out.println(cliente.getCodigo());
			System.out.println(cliente.getData());
			System.out.println(cliente.getPessoa().getNome());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(1L);
		
		System.out.println(cliente.getCodigo());
		System.out.println(cliente.getData());
		System.out.println(cliente.getPessoa().getNome());
		System.out.println();
	}
	
	
	@Test
	@Ignore
	public void excluir() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(1L);
		
		clienteDAO.excluir(cliente);
		
		System.out.println("Cliente Excluido");
		System.out.println(cliente.getCodigo());
		System.out.println(cliente.getData());
		System.out.println(cliente.getPessoa().getNome());
	}
	
	@Test
	@Ignore
	public void editar() throws ParseException {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(1L);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(2L);
		
		
		System.out.println("Antes de ser editado");
		System.out.println(cliente.getCodigo());
		System.out.println(cliente.getData());
		System.out.println(cliente.getPessoa().getNome());
		
		
		cliente.setData(new SimpleDateFormat("dd/MM/yyyy").parse("09/06/2020"));
		cliente.setLiberado(true);
		cliente.setPessoa(pessoa);

		clienteDAO.editar(cliente);
		
		System.out.println("depois de editar");
		System.out.println(cliente.getCodigo());
		System.out.println(cliente.getData());
		System.out.println(cliente.getPessoa().getNome());
	}
	
	
}
