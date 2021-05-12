package br.pro.delfino.drogaria.util;

import org.hibernate.Session;

public class HibernateTeste {

	public static void main(String[] args) {
		
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			sessao.close();
			HibernateUtil.getFabricaDeSessoes().close();
	

	}

}
