package br.pro.delfino.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Funcionario;
import br.pro.delfino.drogaria.domain.Pessoa;

public class FuncionarioDAOTest {
	
	@Test
	@Ignore
	public void salvar() throws ParseException {
		Long codigoPessoa = 1L;
		
		Funcionario funcionario = new Funcionario();
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);
		
		funcionario.setCarteiraTrabalho("5435436545");
		funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("09/06/2020"));
		funcionario.setPessoa(pessoa);
		
		
		FuncionarioDAO funcionariodao = new FuncionarioDAO();
		funcionariodao.salvar(funcionario);
		
	}
	
	@Test
	@Ignore
	public void listar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> resultado = funcionarioDAO.listar();
	
		
		for (Funcionario funcionario : resultado) {
			System.out.println(funcionario.getCarteiraTrabalho());
			System.out.println(funcionario.getDataAdmissao());
			System.out.println(funcionario.getPessoa().getNome());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(1L);
		
		System.out.println(funcionario.getCarteiraTrabalho());
		System.out.println(funcionario.getDataAdmissao());
		System.out.println(funcionario.getPessoa().getNome());
		System.out.println();
	}
	
	
	@Test
	@Ignore
	public void excluir() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(1L);
		
		funcionarioDAO.excluir(funcionario);
		
		System.out.println("Funcionario Excluido");
		System.out.println(funcionario.getCarteiraTrabalho());
		System.out.println(funcionario.getDataAdmissao());
		System.out.println(funcionario.getPessoa().getNome());
	}
	
	@Test
	@Ignore
	public void editar() throws ParseException {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(1L);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(2L);
		
		funcionario.setCarteiraTrabalho("5435436547");
		funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("10/06/2020"));
		funcionario.setPessoa(pessoa);
		
		System.out.println("funcionario antes de editar:");
		System.out.println(funcionario.getCarteiraTrabalho());
		System.out.println(funcionario.getDataAdmissao());
		System.out.println(funcionario.getPessoa().getNome());
		
		funcionarioDAO.editar(funcionario);
		
		System.out.println("depois de editar");
		System.out.println(funcionario.getCarteiraTrabalho());
		System.out.println(funcionario.getDataAdmissao());
		System.out.println(funcionario.getPessoa().getNome());
	}
	
	
}
