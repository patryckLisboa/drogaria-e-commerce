package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Cidade;

import br.pro.delfino.drogaria.domain.Pessoa;


public class PessoaDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		Pessoa pessoa = new Pessoa();
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(2L);
		
		//nome
		pessoa.setNome("Teste Patryck Divino Lisboa Pereira");
		
		//documento
		pessoa.setCpf("05759883132");
		pessoa.setRg("6293572");
		
		//endereço
		pessoa.setCep("75370000");
		pessoa.setCidade(cidade);
		pessoa.setBairro("triunfo");
		pessoa.setComplemento("rua: 2, quadra 5");
		pessoa.setRua("r3");
		pessoa.setNumero(new Short("00"));
		
		//contato
		pessoa.setEmail("patryck-lisboa@hotmail.com");
		pessoa.setTelefone("(62) 3516-3232");
		pessoa.setCelular("(62) 9 8594-5456");
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.salvar(pessoa);
	}
	
	@Test
	@Ignore
	public void listar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Pessoa> resultado = pessoaDAO.listar();
		
		for (Pessoa pessoa: resultado) {
			//nome
			System.out.println(pessoa.getNome());
			
			//documento
			System.out.println(pessoa.getCpf());
			System.out.println(pessoa.getRg());
			
			//endereço
			System.out.println(pessoa.getCep());
			System.out.println(pessoa.getCidade().getNome());
			System.out.println(pessoa.getBairro());
			System.out.println(pessoa.getComplemento());
			System.out.println(pessoa.getRua());
			System.out.println(pessoa.getNumero());
			
			//contato
			System.out.println(pessoa.getEmail());
			System.out.println(pessoa.getTelefone());
			System.out.println(pessoa.getCelular());
			
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		Long pessoaCodigo = 1L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(pessoaCodigo);
		
		//nome
		System.out.println(pessoa.getNome());
		
		//documento
		System.out.println(pessoa.getCpf());
		System.out.println(pessoa.getRg());
		
		//endereço
		System.out.println(pessoa.getCep());
		System.out.println(pessoa.getCidade().getNome());
		System.out.println(pessoa.getBairro());
		System.out.println(pessoa.getComplemento());
		System.out.println(pessoa.getRua());
		System.out.println(pessoa.getNumero());
		
		//contato
		System.out.println(pessoa.getEmail());
		System.out.println(pessoa.getTelefone());
		System.out.println(pessoa.getCelular());
		
		System.out.println();
		
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigoCidade = 2L;
		Long pessoaCodigo = 1L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(pessoaCodigo);
		
		System.out	.println("Antes de editado:-------------"+ cidade.getCodigo());
		//nome
		System.out.println(pessoa.getNome());
		
		//documento
		System.out.println(pessoa.getCpf());
		System.out.println(pessoa.getRg());
		
		//endereço
		System.out.println(pessoa.getCep());
		System.out.println(pessoa.getCidade().getNome());
		System.out.println(pessoa.getBairro());
		System.out.println(pessoa.getComplemento());
		System.out.println(pessoa.getRua());
		System.out.println(pessoa.getNumero());
		
		//contato
		System.out.println(pessoa.getEmail());
		System.out.println(pessoa.getTelefone());
		System.out.println(pessoa.getCelular());
		
		System.out.println();
		
		pessoa.setComplemento("rua: 2, quadra 5");
		pessoa.setCidade(cidade);

		
		pessoaDAO.editar(pessoa);
		
		System.out	.println("editado:-------------"+ cidade.getCodigo());
		//nome
		System.out.println(pessoa.getNome());
		
		//documento
		System.out.println(pessoa.getCpf());
		System.out.println(pessoa.getRg());
		
		//endereço
		System.out.println(pessoa.getCep());
		System.out.println(pessoa.getCidade().getNome());
		System.out.println(pessoa.getBairro());
		System.out.println(pessoa.getComplemento());
		System.out.println(pessoa.getRua());
		System.out.println(pessoa.getNumero());
		
		//contato
		System.out.println(pessoa.getEmail());
		System.out.println(pessoa.getTelefone());
		System.out.println(pessoa.getCelular());
		
		System.out.println();
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long pessoaCodigo = 2L;

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(pessoaCodigo);
		
		pessoaDAO.excluir(pessoa);
		
		System.out.println("Pessoa removida:");

		
	}
}
