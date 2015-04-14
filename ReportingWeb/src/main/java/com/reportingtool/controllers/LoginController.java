package com.reportingtool.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TODO:RT falta implementar el control de usuarios;
 * 
 * @author Esteban Calderon
 *
 */
@Controller
@RequestMapping(value="/")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="login.do", method=RequestMethod.GET)
	public String login(Locale locale, Model model){
		
		System.out.println("Login Controller - login");
		return "login";
	}
	
	@RequestMapping(value="index.do", method=RequestMethod.GET)
	public String index(Locale locale, Model model){
		
		System.out.println("Login Controller - index");
		return "dashboard";
	}
}
