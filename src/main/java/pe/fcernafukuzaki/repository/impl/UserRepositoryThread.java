package pe.fcernafukuzaki.repository.impl;

import java.util.ArrayList;
import java.util.List;

import pe.fcernafukuzaki.model.User;
import pe.fcernafukuzaki.repository.UserRepository;

public class UserRepositoryThread implements Runnable {

	private UserRepository userRepository;
	
	Thread hilo;
	private List<User> users = new ArrayList<>();
	
	public UserRepositoryThread(int tiempoDormir, String name, UserRepository userRepository) {
		this.userRepository = userRepository;
		hilo = new Thread(this, name);
		hilo.start();
		try {//To prevent: Connection pool is no longer active
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Inicio del hilo " + Thread.currentThread().getName());
			users = userRepository.list();
			System.out.printf("Size of the list is: %s\n", users.size());
			System.out.println("Fin del hilo " + Thread.currentThread().getName());
		} catch (Exception e) {
			System.out.println("Error en el hilo " + Thread.currentThread().getName() + ": "+ e.fillInStackTrace());
			e.printStackTrace();
		}
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
