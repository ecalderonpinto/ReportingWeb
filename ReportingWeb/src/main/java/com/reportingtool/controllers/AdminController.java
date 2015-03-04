package com.reportingtool.controllers;

import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entities.entity.InstallEntities;

@Controller
public class AdminController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	@RequestMapping(value = "/admin.do", method = RequestMethod.GET)
	public String dataManagerControllerPre(Model model) {

		System.out.println("AdminController");

		return "admin";
	}

	@RequestMapping(value="/generateDB.do")
	public String generateDataBase(Model model) {

		LocalSessionFactoryBean sessionFactory = this.applicationContext.getBean("&sessionFactory", LocalSessionFactoryBean.class);
		
		SchemaExport export = new SchemaExport(sessionFactory.getConfiguration());
		export.drop(false, true);
		export.create(false, true);
		
		System.out.println("Controller admin/generateDB");
		
		return "admin";
	}
	
	@RequestMapping(value="/installTestData.do")
	public String installTestData(Model model){
		
		InstallEntities install = new InstallEntities();
		
		install.deleteEntities(applicationContext);
		install.installEntitiesFull(applicationContext);
		install.installAIF(applicationContext);
		
		return "admin";
	}
	
	@RequestMapping(value="/cleanData.do")
	public String cleanData(Model model){
		
		InstallEntities install = new InstallEntities();
		
		install.deleteEntities(applicationContext);
		
		return "admin";
	}
}
