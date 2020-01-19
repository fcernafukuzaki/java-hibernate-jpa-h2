package pe.fcernafukuzaki;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pe.fcernafukuzaki.service.UserManagementService;
import pe.fcernafukuzaki.service.impl.UserManagementServiceImpl;

public class App {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
		UserManagementService m = (UserManagementServiceImpl) appContext.getBean("userManagementService");
		m.listUsers();
	}

}
