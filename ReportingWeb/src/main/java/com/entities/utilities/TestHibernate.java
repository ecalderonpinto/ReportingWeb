package com.entities.utilities;


public class TestHibernate {


	public static void main(String[] args) {

		try {
			
			
			// in servlet this context is @autowired, here doesn't work !!!
//			ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
//					"applicationContext.xml");
			
			
//			InstallManager installLoader = new InstallManager();
//			
//			//installLoader.installTest(applicationContext);
//			
//			installLoader.deleteEntities(applicationContext);
//			
//			installLoader.installEntitiesFull(applicationContext);
			
			
			

		} catch (Throwable ex) {
			System.err
					.println("Error creando sesionfactory " + ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
	}

}
