package com.reportingtool.controllers;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entities.dao.usermanager.UserControlDAO;
import com.entities.dao.usermanager.UserDAO;
import com.entities.entity.usermanager.User;
import com.entities.entity.usermanager.UserControl;

@Controller
public class UserController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@RequestMapping(value = "/user.do", method = RequestMethod.GET)
	public String userListController(Locale locale, Model model) {

		System.out.println("User Controller - preForm");

		UserDAO userDAO = (UserDAO) applicationContext.getBean("userDAO");
		List<User> users = userDAO.findAll();

		UserControlDAO userControlDAO = (UserControlDAO) applicationContext
				.getBean("userControlDAO");
		List<UserControl> userControls = userControlDAO.findAll();

		System.out.println(users.size() + " users");
		model.addAttribute("users", users);
		model.addAttribute("usercontrols", userControls);
		model.addAttribute("alerts", false);

		return "user";
	}

}
