package pe.fcernafukuzaki.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import pe.fcernafukuzaki.AppConfig;
import pe.fcernafukuzaki.model.Profile;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Order;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@TestMethodOrder(OrderAnnotation.class)
class ProfileRepositoryTest {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Test
	@Disabled
	@Order(1)
	void whenInsertProfile() {
		Profile profile = new Profile(4L, "Head Hunter", null);
		profileRepository.insert(profile);
		Profile profile1 = new Profile(5L, "CEO", null);
		profileRepository.insert(profile1);
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
		Profile profile = profileRepository.findById(40L);
		assertTrue(profile == null);
	}
	
	@Test
	@Order(4)
	void whenListProfiles() {
		List<Profile> profiles = profileRepository.list();
		assertTrue(!profiles.isEmpty());
	}
}
