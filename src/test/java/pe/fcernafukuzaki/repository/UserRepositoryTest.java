package pe.fcernafukuzaki.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import pe.fcernafukuzaki.model.Profile;
import pe.fcernafukuzaki.model.User;
import pe.fcernafukuzaki.model.UserCredential;

@TestMethodOrder(OrderAnnotation.class)
class UserRepositoryTest {

	private static UserRepository userRepository = new UserRepository();

	@Test
	@Order(1)
	void whenNotInsertedUsers() {
		List<User> users = print();
		assertTrue(0 == users.size());
	}

	@Test
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
	@Order(5)
	void whenFindProfilesByUserId() {
		List<Profile> profiles = userRepository.findProfilesByUserId(3L);
		
		assertTrue(profiles.size() > 0);
	}

	@Test
	@Order(6)
	void whenNotFindProfilesByUserId() {
		List<Profile> profiles = userRepository.findProfilesByUserId(0L);
		
		assertTrue(profiles == null);
	}
	
	@Test
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
	@Order(8)
	void whenRemoveProfile() {
		Long uid_user = 1L;
		User user = userRepository.findByUserId(uid_user);
		Profile profile = new Profile(3L, "Candidate", user);
		
		userRepository.removeProfile(user, profile);
		List<Profile> profiles = userRepository.findProfilesByUserId(uid_user);
		
		assertTrue(profiles.size() == 0);
	}

	private List<User> print() {
		List<User> users = userRepository.list();
		System.out.printf("Size of the list is: %s\n", users.size());
		return users;
	}
}
