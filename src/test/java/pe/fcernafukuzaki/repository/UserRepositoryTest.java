package pe.fcernafukuzaki.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import pe.fcernafukuzaki.AppConfig;
import pe.fcernafukuzaki.model.Profile;
import pe.fcernafukuzaki.model.User;
import pe.fcernafukuzaki.model.UserCredential;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@TestMethodOrder(OrderAnnotation.class)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	@Order(1)
	void whenNotInsertedUsers() {
		List<User> users = print();
		assertTrue(0 == users.size());
	}

	@Test
	@Disabled
	@Order(2)
	void whenInsertAUser() {
		User user = new User(1L, "Francisco Cerna Fukuzaki", true, LocalDate.of(2020, 1, 7));

		UserCredential userCredential = new UserCredential(1L, "strong password", user);
		user.setUserCredential(userCredential);

		user = userRepository.insert(user);

		List<User> users = print();
		assertTrue(1 == users.size());
	}

	@Test
	@Disabled
	@Order(3)
	void whenInsertUserWithCredential() {
		User user = new User(2L, "Francisco Cerna Fukuzaki", true, LocalDate.of(2020, 1, 7));

		UserCredential userCredential = new UserCredential(2L, "strong password", user);
		user.setUserCredential(userCredential);

		user = userRepository.insert(user);

		List<User> users = print();
		assertTrue(2 == users.size());
	}

	@Test
	@Disabled
	@Order(4)
	void whenInsertUserWithCredentialAndProfile() {
		User user = new User(3L, "Francisco Cerna Fukuzaki", true, LocalDate.of(2020, 1, 7));

		UserCredential userCredential = new UserCredential(3L, "strong password", user);

		Profile profile1 = new Profile(1L, "Administrator", user);
		Profile profile2 = new Profile(2L, "Recruiter", user);
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		profiles.add(profile1);
		profiles.add(profile2);

		user.setUserCredential(userCredential);
		user.setProfiles(profiles);

		userRepository.insert(user);

		List<User> users = print();
		assertTrue(3 == users.size());
	}

	@Test
	@Disabled
	@Order(5)
	void whenFindProfilesByUserId() {
		List<Profile> profiles = userRepository.findProfilesByUserId(3L);
		
		assertTrue(profiles.size() > 0);
	}

	@Test
	@Disabled
	@Order(6)
	void whenNotFindProfilesByUserId() {
		List<Profile> profiles = userRepository.findProfilesByUserId(0L);
		
		assertTrue(profiles == null);
	}
	
	@Test
	@Disabled
	@Order(7)
	void whenAssingProfile() {
		Long uid_user = 1L;
		User user = userRepository.findByUserId(uid_user);
		Profile profile = new Profile(3L, "Candidate", user);
		userRepository.addProfile(user, profile);
		List<Profile> profiles = user.getProfiles();
		
		userRepository.insert(user);
		userRepository.findProfilesByUserId(uid_user);
		
		assertTrue(profiles.size() == 1);
	}
	
	@Test
	@Disabled
	@Order(8)
	void whenRemoveProfile() {
		Long uid_user = 1L;
		User user = userRepository.findByUserId(uid_user);
		Profile profile = new Profile(3L, "Candidate", user);
		
		userRepository.removeProfile(user, profile);
		List<Profile> profiles = userRepository.findProfilesByUserId(uid_user);
		
		assertTrue(profiles.size() == 0);
	}
	
	@Test
	@Disabled
	@Order(9)
	void whenInsertMultipleUserWithProfile() {
		try {
			int sizeOfList = 120000;
			Random rnd = new Random();
			List<User> users = new ArrayList<>();
			for(int i = 113989; i < sizeOfList; i++) {
				System.out.println("Inicia " + i);
				String nombre = "";
				for(int j = 0; j < 7; j++) {
					nombre += (char)(rnd.nextInt(91) + 65);
				}
				System.out.println("Inicia " + nombre);
				User user = new User(new Long(i), nombre, true, LocalDate.of(2020, 1, 7));
				UserCredential userCredential = new UserCredential(new Long(i), nombre, user);
				
				user.setUserCredential(userCredential);
				//user.setProfiles(profiles);
				System.out.println("Inicia insert " + user.toString());
				users.add(user);
				System.out.println("Finaliza insert " + i);
			}
			userRepository.insertAll(users);
			List<User> usersPrint = print();
			assertTrue(sizeOfList == usersPrint.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<User> print() {
		List<User> users = null;
		try {
			users = userRepository.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.printf("Size of the list is: %s\n", users.size());
		return users;
	}
}
