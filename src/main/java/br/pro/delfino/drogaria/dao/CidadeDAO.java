package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.pro.delfino.drogaria.domain.Cidade;
import br.pro.delfino.drogaria.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade>{ // importaçao de cidade caso esteja em outro pacote
	//a  gente faz os dao de acordo com a erança. o que depende de outro a gente deixa pra depois
	
	@SuppressWarnings("unchecked")
	public List<Cidade> buscarPorEstado(long estadoCodigo) {
	
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			
			try {
				Criteria consulta =  sessao.createCriteria(Cidade.class);
				consulta.add(Restrictions.eq("estado.codigo", estadoCodigo)); // where é o restriction no hibernate
																	// "eq" para campo estrangeiro
				consulta.addOrder(Order.asc("nome"));
				List<Cidade> resultado = consulta.list();
				return resultado;  
			} catch (RuntimeException erro) {					
				throw erro;
			} finally {
				sessao.close();
			}

	}
}
