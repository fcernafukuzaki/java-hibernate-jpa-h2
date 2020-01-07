package pe.fcernafukuzaki.repository;

import javax.persistence.EntityManager;

import pe.fcernafukuzaki.model.Profile;

public class ProfileRepository {

	private EntityManager manager;
	
	public ProfileRepository() {
		manager = Repository.init();
	}
	
	public Profile insert(Profile profile) {
		manager.getTransaction().begin();
		manager.persist(profile);
		manager.getTransaction().commit();
		return profile;
	}
	
	public Profile findById(Long uid_profile) {
		Profile profile = manager.find(Profile.class, uid_profile);
		if(profile != null) {
			System.out.println("* " + profile.toString());
		} else {
			System.out.println("* Not found profile with uid: " + uid_profile);
		}
		return profile;
	}
	
}
