package br.pro.delfino.drogaria.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.pro.delfino.drogaria.util.HibernateUtil;

public class GenericDAO<Entidade> {
	private Class<Entidade> classe; // vai pegar o nome da classe que eu estou para conseguir fazer os algoritmos de pesquisa
	
	@SuppressWarnings("unchecked") // apenas para tirar o warning
	public GenericDAO() { // estudar a api (reflect)
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}// só para saber o tipo da classe apenas de chamar o construtor
	
	public void salvar(Entidade entidade) { // entidade, uma coisa genérica/ interpreta como se fose um tipo
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // hibernateUtil é a nossa fabrica de sessão
																			// peguei uma sessão da nossa fabrica de
																			// seções e abri ela
		Transaction transacao = null;// transaction funciona como transferencias de banco, tira de um e adiciona no
										// outro com certeza
		// isso é bom para opereçoes de salvar e excluir

		// comecar a transacao
		try {
			transacao = sessao.beginTransaction(); // se der problema ele desfaz qualquer coisa a partir da linha 20 do
													// meu codigo e entra to catch

			sessao.save(entidade); // se der algum problema aqui, ele desfaz, entro no catch

			transacao.commit(); // vou comitar/ aqui ela finaliza/ se der problema eu desfaço a transacao

		} catch (RuntimeException erro) { // para desfazer // exceção em tempo de execução
			if (transacao != null) { //só é diferente de null, quando o comando na linha 19 der certo
									// pois criou a transação 
				transacao.rollback(); // se a transação foi aberta, ele desfaz	
			}
			System.out.println("erro ao fazer transação");
			throw erro; //aqui ele repropaga o erro para as camadas superiores
		} finally {
			//tem que fechar a ceção em qualquer um dos casos
			sessao.close();
		}
	} // transacao é melhor para coisas arriscadas como salvar e excluir
	
	@SuppressWarnings("unchecked")
	public List<Entidade> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta =  sessao.createCriteria(classe);//pegar secao aberta 
					//classe é a variável que eu criei que carrega a classe do objeto quanto eu instanciar
			List<Entidade> resultado = consulta.list(); // ele não sabe o tipo que vai pegar
			
			return resultado; //retornar a listagem 
		} catch (RuntimeException erro) {					
			throw erro;
		} finally {
			sessao.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Entidade> listar(String campoOrdenacao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta =  sessao.createCriteria(classe);
			consulta.addOrder(Order.asc(campoOrdenacao));
			List<Entidade> resultado = consulta.list();
			return resultado;  
		} catch (RuntimeException erro) {					
			throw erro;
		} finally {
			sessao.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public Entidade buscar(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta =  sessao.createCriteria(classe);//pegar secao aberta 
					//classe é a variável que eu criei que carrega a classe do objeto quanto eu instanciar
			
			consulta.add(Restrictions.idEq(codigo));
			Entidade resultado = (Entidade) consulta.uniqueResult(); // vai retornar apenas um
									// Converter para o tipo Entidade
			return resultado; //retornar a listagem 
		} catch (RuntimeException erro) {					
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	public void excluir(Entidade entidade) { // entidade, uma coisa genérica/ interpreta como se fose um tipo
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // hibernateUtil é a nossa fabrica de sessão
																			// peguei uma sessão da nossa fabrica de
																			// seções e abri ela
		Transaction transacao = null;// transaction funciona como transferencias de banco, tira de um e adiciona no
										// outro com certeza
		// isso é bom para opereçoes de salvar e excluir

		// comecar a transacao
		try {
			transacao = sessao.beginTransaction(); // se der problema ele desfaz qualquer coisa a partir da linha 20 do
													// meu codigo e entra to catch

			sessao.delete(entidade); // se der algum problema aqui, ele desfaz, entro no catch

			transacao.commit(); // vou comitar/ aqui ela finaliza/ se der problema eu desfaço a transacao

		} catch (RuntimeException erro) { // para desfazer // exceção em tempo de execução
			if (transacao != null) { //só é diferente de null, quando o comando na linha 19 der certo
									// pois criou a transação 
				transacao.rollback(); // se a transação foi aberta, ele desfaz	
			}
			
			throw erro; //aqui ele repropaga o erro para as camadas superiores

		} finally {
			//tem que fechar a ceção em qualquer um dos casos
			sessao.close();
		}
	} 
	
	public void editar(Entidade entidade) { 
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // abrir sessao
																			
		Transaction transacao = null;
		// isso é bom para opereçoes de salvar e excluir

		// comecar a transacao
		try {
			transacao = sessao.beginTransaction(); // se der problema ele desfaz qualquer coisa a partir da linha 20 do
													// meu codigo e entra to catch

			sessao.update(entidade); // se der algum problema aqui, ele desfaz, entro no catch

			transacao.commit(); // vou comitar/ aqui ela finaliza/ se der problema eu desfaço a transacao

		} catch (RuntimeException erro) { // para desfazer // exceção em tempo de execução
			if (transacao != null) { //só é diferente de null, quando o comando na linha 19 der certo
									// pois criou a transação 
				transacao.rollback(); // se a transação foi aberta, ele desfaz	
			}
			
			throw erro; //aqui ele repropaga o erro para as camadas superiores

		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Entidade merge(Entidade entidade) { // exatamente como o salvar
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); 
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			Entidade retorno = (Entidade) sessao.merge(entidade); 
			transacao.commit();
			return retorno; // vai tetornar a entidade
		} catch (RuntimeException erro) {
			if (transacao != null) { 
				transacao.rollback();
			
			}
			throw erro;
			
		} finally {
			sessao.close();
			
		}
	}
	
}
