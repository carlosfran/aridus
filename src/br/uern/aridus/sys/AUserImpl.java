package br.uern.aridus.sys;

import java.sql.SQLException;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.uern.aridus.model.UserProfile;

@WebService
@SOAPBinding
public class AUserImpl implements AUser {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	public AUserImpl() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		emf = Persistence.createEntityManagerFactory("ARIDUSDB");
		em = emf.createEntityManager();
	}

	public long create(UserProfile user) {
		Query query = em.createQuery("SELECT COUNT(*) as login "
				+ "FROM UserProfile " + "WHERE username = :username");
		query.setParameter("username", user.getUsername());
		Long rs = (Long) query.getSingleResult();
		if (rs == 0) {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			return user.getUserid();
		}
		return 0L;
	}

	public boolean delete(String username, String psswd) {

		boolean ret = false;
		UserProfile user = new UserProfile();

		user.setUsername(username);
		user.setPsswd(psswd);

		Long l = check(user.getUsername(), user.getPsswd());
		if (l > 0) {
			user = em.find(UserProfile.class, l);
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
			ret = true;
		}
		return ret;
	}

	public long check(String username, String psswd) {
		return AUserImpl.checkCredentials(username, psswd);
	}

	public static long checkCredentials(String username, String psswd) {
		UserProfile user = new UserProfile();
		user.setUsername(username);
		user.setPsswd(psswd);
		Query query = em.createQuery("SELECT userid " + " FROM UserProfile "
				+ "WHERE username = :username AND psswd= :psswd");
		query.setParameter("username", user.getUsername());
		query.setParameter("psswd", user.getPsswd());
		List<Long> rs = (List<Long>) query.getResultList();
		if (rs.isEmpty())
			return 0L;
		return rs.get(0);
	}

	public String getProp(String username, String psswd, String prop) {
		String value = "";
		UserProfile user = new UserProfile();
		user.setPsswd(psswd);
		user.setUsername(username);

		if (check(username, psswd) > 0
				&& !prop.equalsIgnoreCase("psswd")) {
			Query query = em.createQuery("SELECT " + prop.toLowerCase()
					+ " FROM UserProfile "
					+ "WHERE username = :username AND psswd= :psswd");
			query.setParameter("username", user.getUsername());
			query.setParameter("psswd", user.getPsswd());
			List<String> rs = (List<String>) query.getResultList();
			if (rs != null && !rs.isEmpty())
				value = rs.get(0).toString();
		}
		return value;
	}

	public boolean updateProp(String username, String psswd, String prop,
			String newValue) {
		boolean ret = false;
		UserProfile user = new UserProfile();
		user.setPsswd(psswd);
		user.setUsername(username);

		if (check(username, psswd) > 0
				&& !prop.equalsIgnoreCase("psswd")) {
			Query query = em.createQuery("UPDATE UserProfile "
					+ "SET "+prop.toLowerCase()+"= :newvalue" +
					" WHERE username = :username AND psswd= :psswd");
			query.setParameter("username", user.getUsername());
			query.setParameter("psswd", user.getPsswd());
			query.setParameter("newvalue", newValue);
			int i = query.executeUpdate();
			if(i > 0)
				ret = true;
		}
		return ret;
	}
}
