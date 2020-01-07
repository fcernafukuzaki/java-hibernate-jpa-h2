package pe.fcernafukuzaki.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Repository {

	private static EntityManagerFactory emf;
	
	public static EntityManager init() {
		emf = Persistence.createEntityManagerFactory("PersistenceAuthentication");
		return emf.createEntityManager();
	}
}
