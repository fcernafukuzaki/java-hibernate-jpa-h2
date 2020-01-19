package pe.fcernafukuzaki;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pe.fcernafukuzaki.repository.ProfileRepository;
import pe.fcernafukuzaki.repository.UserRepository;
import pe.fcernafukuzaki.repository.impl.ProfileRepositoryImpl;
import pe.fcernafukuzaki.repository.impl.UserRepositoryImpl;
import pe.fcernafukuzaki.service.UserManagementService;
import pe.fcernafukuzaki.service.impl.UserManagementServiceImpl;

@Configuration
public class AppConfig {

	@Bean
	public UserRepository userRepository() {
		return new UserRepositoryImpl();
	}
	
	@Bean
	public ProfileRepository profileRepository() {
		return new ProfileRepositoryImpl();
	}
	
	@Bean
	public UserManagementService userManagementService() {
		return new UserManagementServiceImpl();
	}

}