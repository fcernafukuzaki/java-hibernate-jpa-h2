package pe.fcernafukuzaki.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import pe.fcernafukuzaki.model.Profile;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class ProfileRepositoryTest {

	private static ProfileRepository profileRepository = new ProfileRepository();
	
	@BeforeAll
	static void init() {
		Profile profile1 = new Profile(1L, "Administrator", null);
		Profile profile2 = new Profile(2L, "Recruiter", null);
		profileRepository.insert(profile1);
		profileRepository.insert(profile2);
	}
	
	@Test
	@Order(1)
	void whenInsertProfile() {
		Profile profile = new Profile(3L, "Candidate", null);
		profileRepository.insert(profile);
		assertTrue(profile != null);
	}
	
	@Test
	@Order(2)
	void whenFindProfile() {
		Profile profile = profileRepository.findById(1L);
		assertTrue(profile != null);
	}
	
	@Test
	@Order(3)
	void whenNotFindProfile() {
		Profile profile = profileRepository.findById(4L);
		assertTrue(profile == null);
	}

}
