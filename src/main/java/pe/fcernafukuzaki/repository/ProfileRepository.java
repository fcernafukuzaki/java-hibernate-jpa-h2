package pe.fcernafukuzaki.repository;

import java.util.List;

import pe.fcernafukuzaki.model.Profile;

public interface ProfileRepository {

	public Profile insert(Profile profile);
	
	public List<Profile> list();
	
	public Profile findById(Long uid_profile);
	
}
