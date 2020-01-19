package pe.fcernafukuzaki.service;

import java.util.List;

import pe.fcernafukuzaki.model.User;

public interface UserManagementService {

	public List<User> listUsers();
	
	public List<User> listUsersThread();
	
}
