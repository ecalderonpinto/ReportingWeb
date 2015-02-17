package com.reportingtool.controllers;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.reportingtool.test.TestValidator;

@Controller
@RequestMapping(value="/")
public class TestController {

	@RequestMapping(value="test.do", method=RequestMethod.GET)
	public String login(Locale locale, Model model){
		
		// TO DO
		
		ApplicationContext aplicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		
		TestValidator testValidator = new TestValidator();
		testValidator.process(aplicationContext);
		
		
		
		System.out.println("Test");
		return "test";
	}
	
}
