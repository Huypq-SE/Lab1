package fall.hsf301.slot02.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fall.hsf301.slot02.pojo.Account;

public class AccountDAO {
	private static EntityManager em;
	private static EntityManagerFactory emf;

	public AccountDAO(String persistanceName) {
		emf = Persistence.createEntityManagerFactory(persistanceName);
	}

	public Account findByUserName(String userName) {
		Account account = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			account = em.find(Account.class, userName);
		} catch (Exception ex) {
			System.out.println("Error" + ex.getMessage());
		} finally {
			em.close();
		}
		return account;
	}
}
