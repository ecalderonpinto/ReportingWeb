package com.entities.utilities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class HibernateUtilities {
	
	private static final Log log = LogFactory.getLog(HibernateUtilities.class);

	public static List findAll(SessionFactory sf, String className){
		
		System.out.println("Find All for class: " + className);
		List objects = null;
	    //try {
	    	Query query = sf.getCurrentSession().createQuery("from " + className);
	    	objects = query.list();
	    /*
	    } catch (HibernateException e) {
	    	log.error("Error getting all records from " + className);
	    }
	    */
	    return objects;
	}
}
