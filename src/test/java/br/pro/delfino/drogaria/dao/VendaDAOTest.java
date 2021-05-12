package br.pro.delfino.drogaria.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Cliente;
import br.pro.delfino.drogaria.domain.Funcionario;
import br.pro.delfino.drogaria.domain.Venda;


public class VendaDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException {
		Long codigoCliente = 2L;
		Long codigoFuncionario = 2L;
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigoCliente);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigoFuncionario); // sempre colocar um trows lá em cima
		
		Venda venda = new Venda();

		venda.setCliente(cliente);
		venda.setFuncionario(funcionario);
		venda.setHorario(new SimpleDateFormat("dd/MM/yyyy").parse("13/12/2020"));
		venda.setPrecoTotal(new BigDecimal("50.45"));
		
		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.salvar(venda);
	}

	@Test
	@Ignore
	public void listar() {
		VendaDAO vendaDAO = new VendaDAO();
		List<Venda> resultado = vendaDAO.listar();
	
		
		for (Venda venda : resultado) {
			System.out.println(venda.getCliente().getPessoa().getNome());
			System.out.println(venda.getFuncionario().getPessoa().getNome());
			System.out.println(venda.getHorario());
			System.out.println(venda.getPrecoTotal());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(3L);
		
		System.out.println(venda.getCliente().getPessoa().getNome());
		System.out.println(venda.getFuncionario().getPessoa().getNome());
		System.out.println(venda.getHorario());
		System.out.println(venda.getPrecoTotal());
		System.out.println();
	}
	
	
	@Test
	@Ignore
	public void excluir() {
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(3L);
		
		vendaDAO.excluir(venda);
		
		System.out.println(venda.getCliente().getPessoa().getNome());
		System.out.println(venda.getFuncionario().getPessoa().getNome());
		System.out.println(venda.getHorario());
		System.out.println(venda.getPrecoTotal());
	}
	
	@Test
	@Ignore
	public void editar() throws ParseException {
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(3L);
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(3L);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(4L);
		
		System.out.println("Antes de ser editado");
		System.out.println(venda.getCliente().getPessoa().getNome());
		System.out.println(venda.getFuncionario().getPessoa().getNome());
		System.out.println(venda.getHorario());
		System.out.println(venda.getPrecoTotal());
		

		venda.setCliente(cliente);
		venda.setFuncionario(funcionario);
		venda.setHorario(new SimpleDateFormat("dd/MM/yyyy").parse("13/12/2020"));
		venda.setPrecoTotal(new BigDecimal("50.45"));

		vendaDAO.editar(venda);
		
		System.out.println("Depois de ser editado");
		System.out.println(venda.getCliente().getPessoa().getNome());
		System.out.println(venda.getFuncionario().getPessoa().getNome());
		System.out.println(venda.getHorario());
		System.out.println(venda.getPrecoTotal());
	}
	
}
