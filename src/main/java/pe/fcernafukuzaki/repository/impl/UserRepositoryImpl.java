package pe.fcernafukuzaki.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import pe.fcernafukuzaki.model.Profile;
import pe.fcernafukuzaki.model.User;
import pe.fcernafukuzaki.repository.UserRepository;
import pe.fcernafukuzaki.util.HibernateConfig;

@Repository
public class UserRepositoryImpl extends HibernateConfig implements UserRepository {

	private Session session = getSessionFactory().openSession();
	
	public User insert(User user) {
		/* Have to persist Credential object, 
		 * unless declare CASCADE in annotation @OneToOne in User class */
		session.beginTransaction();
		session.save(user.getUserCredential());
		session.save(user);
		session.getTransaction().commit();
		return user;
	}
	
	public void insertAll(List<User> users) {
		/* Have to persist Credential object, 
		 * unless declare CASCADE in annotation @OneToOne in User class */
		session.beginTransaction();
		for(User user : users) {
			session.save(user.getUserCredential());
			session.save(user);
		}
		session.getTransaction().commit();
	}
	
	public void addProfile(User user, Profile profile) {
		user.addProfile(profile);
	}
	
	public void removeProfile(User user, Profile profile) {
		user.removeProfile(profile);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> list() {
		List<User> users = null;
		try {
			users = (List<User>) session.createQuery("FROM User").getResultList();
			/*for(User user : users) {
				System.out.println(user.toString());
			}*/
			System.out.println("Size of the list of users: " + users.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public User findByUserId(Long uid_user) {
		User user = null;
		try {
			user = session.find(User.class, uid_user);
			if(user != null) {
				System.out.println("* " + user.toString());
			} else {
				System.out.println("* Not found user with uid: " + uid_user);
			}
		} catch (Exception e) {
			System.err.println("Error findByUserId:" + e.fillInStackTrace());
		}
		return user;
	}
	
	public List<Profile> findProfilesByUserId(Long uid_user) {
		User user = session.find(User.class, uid_user);
		List<Profile> profiles = null;
		if(user != null) {
			profiles = user.getProfiles();
			System.out.println("* Find user: " + user.toString());
			for(Profile p : profiles) {
				System.out.println("* " + p.toString());
			}
		} else {
			System.out.println("* Not found user with uid: " + uid_user);
		}
		return profiles;
	}

}
