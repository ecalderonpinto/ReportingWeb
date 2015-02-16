package com.reportingtool.controllers;

import java.util.Locale;

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
		
		TestValidator testValidator = new TestValidator();
		testValidator.process();
		
		
		
		System.out.println("Test");
		return "test";
	}
	
}
