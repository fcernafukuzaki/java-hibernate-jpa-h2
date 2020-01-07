package pe.fcernafukuzaki.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
class UserTest {

	private static EntityManager manager;
	private static EntityManagerFactory emf;

	@BeforeAll
	static void init() {
		/* Create persistence manager */
		/* Access by entity name declared in /META-INF/persistence.xml */
		emf = Persistence.createEntityManagerFactory("PersistenceAuthentication");
		manager = emf.createEntityManager();
	}

	@Test
	@Order(1)
	void whenNotRegisteredUsers() {
		List<User> users = print();
		assertTrue(0 == users.size());
	}

	@Test
	@Order(2)
	void whenRegisterAUser() {
		User user = new User(1L, "Francisco Cerna Fukuzaki", true, LocalDate.of(2020, 1, 7));
		
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
		
		List<User> users = print();
		assertTrue(1 == users.size());
	}

	@Test

	@Order(3)
	void whenRegisterUserWithCredential() {
		User user = new User(2L, "Francisco Cerna Fukuzaki", true, LocalDate.of(2020, 1, 7));
		
		UserCredential userCredential = new UserCredential(2L, "strong password", user);
		user.setUserCredential(userCredential);
		
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
		
		List<User> users = print();
		assertTrue(2 == users.size());
	}

	@Test
	@Order(4)
	void whenRegisterUserWithCredentialAndProfile() {
		User user = new User(3L, "Francisco Cerna Fukuzaki", true, LocalDate.of(2020, 1, 7));

		UserCredential userCredential = new UserCredential(3L, "strong password", user);

		Profile profile1 = new Profile(1L, "Administrator", user);
		Profile profile2 = new Profile(2L, "Recruiter", user);
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		profiles.add(profile1);
		profiles.add(profile2);

		user.setUserCredential(userCredential);
		user.setProfiles(profiles);

		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();

		List<User> users = print();
		assertTrue(3 == users.size());
	}

	@SuppressWarnings("unchecked")
	private List<User> print() {
		/* Use entity name (class) */
		List<User> users = (List<User>) manager.createQuery("FROM User").getResultList();
		System.out.printf("Size of the list is: %s\n", users.size());
		for (User u : users) {
			System.out.println(u.toString());
		}
		return users;
	}
}
