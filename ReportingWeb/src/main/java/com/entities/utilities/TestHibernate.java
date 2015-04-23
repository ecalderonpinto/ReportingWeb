package com.entities.utilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.entities.entity.install.InstallManager;

public class TestHibernate {


	public static void main(String[] args) {

		// TODO Auto-generated method stub
		try {
			
			
			// in servlet this context is @autowired, here doesn't work !!!
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			
			
			InstallManager installLoader = new InstallManager();
			
			//installLoader.installTest(applicationContext);
			
			installLoader.deleteEntities(applicationContext);
			
			installLoader.installEntitiesFull(applicationContext);
			
			
			

		} catch (Throwable ex) {
			System.err
					.println("Error creando sesionfactory " + ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
	}

}
