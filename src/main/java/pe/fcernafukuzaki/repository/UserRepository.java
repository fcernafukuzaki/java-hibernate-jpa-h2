package pe.fcernafukuzaki.repository;

import java.util.List;

import pe.fcernafukuzaki.model.Profile;
import pe.fcernafukuzaki.model.User;

public interface UserRepository {

	public User insert(User user);
	
	public void insertAll(List<User> users);
	
	public void addProfile(User user, Profile profile);
	
	public void removeProfile(User user, Profile profile);
	
	public List<User> list();
	
	public User findByUserId(Long uid_user);
	
	public List<Profile> findProfilesByUserId(Long uid_user);

}
