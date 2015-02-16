package com.reportingtool.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="/scheduler/")
public class ShedulerController {

	private static final Logger logger = LoggerFactory.getLogger(ShedulerController.class);
	
	@RequestMapping(value="dashboard", method=RequestMethod.GET)
	public String login(Locale locale, Model model){
		
		System.out.println("Login Controller");
		return "scheduler-dashboard";
	}
}
