package pe.fcernafukuzaki.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.fcernafukuzaki.model.User;
import pe.fcernafukuzaki.repository.UserRepository;
import pe.fcernafukuzaki.repository.impl.UserRepositoryThread;
import pe.fcernafukuzaki.service.UserManagementService;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> listUsers() {
		List<User> listaUsuarios = null;
		try {
			listaUsuarios = userRepository.list();
		} catch (Exception e) {
			System.out.println("Error en Service:" + e.fillInStackTrace());
			e.printStackTrace();
		}
		return listaUsuarios;
	}
	
	@Override
	public List<User> listUsersThread() {
		List<User> listaUsuarios = new ArrayList<>();
		try {
			UserRepositoryThread hilo1 = new UserRepositoryThread(50000, "Thread-User-1", userRepository);
			UserRepositoryThread hilo2 = new UserRepositoryThread(70000, "Thread-User-2", userRepository);
			
			listaUsuarios.addAll(hilo1.getUsers());
			listaUsuarios.addAll(hilo2.getUsers());
			
			System.out.println("Tamaño final de la lista: " + listaUsuarios.size());
		} catch (Exception e) {
			System.out.println("Error en Service:" + e.fillInStackTrace());
			e.printStackTrace();
		}
		return listaUsuarios;
	}

}
