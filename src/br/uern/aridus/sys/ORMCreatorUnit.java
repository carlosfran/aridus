package br.uern.aridus.sys;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ORMCreatorUnit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("ARIDUSDB");
		
		EntityManager em = emf.createEntityManager();
		
		// em.persist(obj);
		// em.merge(obj);
		// em.contains(obj);
		// em.remove(objeto);
		// em.find(Class.class, obj);
		
		// em.flush(); // Apenas quando com Transaction
		em.close();
		emf.close();
	}

}
