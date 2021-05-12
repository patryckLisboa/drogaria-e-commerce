package br.pro.delfino.drogaria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContexto implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		  HibernateUtil.getFabricaDeSessoes().openSession(); // para forçar o ribernate inicializar junto com o tomcat
	}
	
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		HibernateUtil.getFabricaDeSessoes().close(); // fechar a fabrica de sessões quando desligar o tomcat 
	}
    

    
}
