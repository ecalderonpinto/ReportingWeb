package com.entities.entity.install;

import org.springframework.context.ApplicationContext;

import com.entities.dao.usermanager.UserDAO;
import com.entities.dao.usermanager.UserRolDAO;
import com.entities.entity.usermanager.User;
import com.entities.entity.usermanager.UserRol;
import com.entities.utilities.hibernate.VersionAuditor;

public class InstallUser {
	
	private ApplicationContext applicationContext;

	public InstallUser(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void install() {
		
		VersionAuditor versionAdmin = new VersionAuditor("admin");
		
		UserRol userRolUser = new UserRol("user", "Normal user", null, null, null, versionAdmin);
		UserRol userRolSupervisor = new UserRol("supervisor", "Supervisor", null, null, null, versionAdmin);
		UserRol userRolAdmin = new UserRol("admin", "Administrator", null, null, null, versionAdmin);
		
		UserRolDAO userRolDAO = (UserRolDAO) applicationContext.getBean("userRolDAO");
		userRolDAO.create(userRolUser);
		userRolDAO.create(userRolSupervisor);
		userRolDAO.create(userRolAdmin);
		
		//UserRolPermission userRolPermission1 = new UserRolPermission(userRolUser, "user", "user", null, versionAdmin);
		//UserRolPermission userRolPermission2 = new UserRolPermission(UserRol, "admin", "admin", null, versionAdmin);
				
		User user = new User(userRolUser, "user", "user", "user@company.com", true, null, null, versionAdmin);
		User userSupervisor = new User(userRolSupervisor, "supervisor", "supervisor", "supervisor@company.com", true ,null, null, versionAdmin);
		User userAdmin = new User(userRolAdmin, "admin", "admin", "admin@lynxspa.com", true, null, null, versionAdmin);
		
		UserDAO userDAO = (UserDAO) applicationContext.getBean("userDAO");
		userDAO.create(user);
		userDAO.create(userSupervisor);
		userDAO.create(userAdmin);
		
	}

}
