package pe.fcernafukuzaki.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import pe.fcernafukuzaki.AppConfig;
import pe.fcernafukuzaki.service.UserManagementService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class UserManagementServiceTest {

	@Autowired
	private UserManagementService service;
	
	@Test
	@Disabled
	void whenListUsers() {
		service.listUsers();
	}
	
	@Test
	void whenListUsersWithThread() {
		service.listUsersThread();
	}

}
