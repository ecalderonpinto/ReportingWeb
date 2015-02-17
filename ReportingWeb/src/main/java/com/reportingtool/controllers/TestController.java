package com.reportingtool.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entities.entity.InstallEntities;
import com.reportingtool.test.TestValidator;

@Controller
@RequestMapping(value="/")
public class TestController {

	@Autowired
	ApplicationContext applicationContext;
	
	@RequestMapping(value="test.do", method=RequestMethod.GET)
	public String login(Locale locale, Model model){
		
		// TO DO
		TestValidator testValidator = new TestValidator();
		testValidator.process(applicationContext);
		
		System.out.println("Test");
		return "test";
	}
	
	@RequestMapping(value="install.do", method=RequestMethod.GET)
	public String install(Locale locale, Model model){
		
		// TO DO
		InstallEntities installLoader = new InstallEntities();
		//installLoader.installTest(applicationContext);
		installLoader.deleteEntities(applicationContext);
		installLoader.installEntitiesFull(applicationContext);
		
		System.out.println("Installed");
		return "install";
	}
	
	
	
	
	
}
