package pe.fcernafukuzaki.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import pe.fcernafukuzaki.model.Profile;
import pe.fcernafukuzaki.repository.ProfileRepository;
import pe.fcernafukuzaki.util.HibernateConfig;

@Repository
public class ProfileRepositoryImpl extends HibernateConfig implements ProfileRepository {

	private Session session = getSessionFactory().openSession();
	
	public Profile insert(Profile profile) {
		try {
			session.beginTransaction();
			session.save(profile);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profile;
	}
	
	@SuppressWarnings("unchecked")
	public List<Profile> list() {
		List<Profile> profiles = null;
		try {
			profiles = (List<Profile>) session.createQuery("FROM Profile").getResultList();
			if(profiles != null) {
				System.out.println("* " + profiles.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profiles;
	}
	
	public Profile findById(Long uid_profile) {
		Profile profile = session.find(Profile.class, uid_profile);
		if(profile != null) {
			System.out.println("* Find profile with uid: " + uid_profile + ". " + profile.toString());
		} else {
			System.out.println("* Not found profile with uid: " + uid_profile);
		}
		return profile;
	}
	
}
