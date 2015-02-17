package com.entities.utilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.entities.entity.InstallEntities;

public class TestHibernate {


	public static void main(String[] args) {

		// TODO Auto-generated method stub
		try {
			
			
			// in servlet this context is @autowired, here doesn't work !!!
			ApplicationContext aplicationContext = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			
			
			InstallEntities installLoader = new InstallEntities();
			
			//installLoader.installTest(aplicationContext);
			
			installLoader.deleteEntities(aplicationContext);
			
			installLoader.installEntitiesFull(aplicationContext);
			
			
			

		} catch (Throwable ex) {
			System.err
					.println("Error creando sesionfactory " + ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
	}

}
