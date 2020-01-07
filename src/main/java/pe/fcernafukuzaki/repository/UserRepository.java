package pe.fcernafukuzaki.repository;

import java.util.List;

import javax.persistence.EntityManager;

import pe.fcernafukuzaki.model.Profile;
import pe.fcernafukuzaki.model.User;

public class UserRepository {

	private EntityManager manager;
	
	public UserRepository() {
		manager = Repository.init();
	}
	
	public User insert(User user) {
		manager.getTransaction().begin();
		/* Have to persist Credential object, 
		 * unless declare CASCADE in annotation @OneToOne in User class */
		//manager.persist(user.getUserCredential());
		manager.persist(user);
		manager.getTransaction().commit();
		return user;
	}
	
	public void addProfile(User user, Profile profile) {
		user.addProfile(profile);
	}
	
	public void removeProfile(User user, Profile profile) {
		user.removeProfile(profile);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> list() {
		List<User> users = (List<User>) manager.createQuery("FROM User").getResultList();
		for(User user : users) {
			System.out.println(user.toString());
		}
		return users;
	}
	
	public User findByUserId(Long uid_user) {
		User user = manager.find(User.class, uid_user);
		if(user != null) {
			System.out.println("* " + user.toString());
		} else {
			System.out.println("* Not found user with uid: " + uid_user);
		}
		return user;
	}
	
	public List<Profile> findProfilesByUserId(Long uid_user) {
		User user = manager.find(User.class, uid_user);
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
