package com.reportingtool.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.dao.usermanager.UserDAO;
import com.entities.entity.reportingtool.Company;
import com.entities.entity.usermanager.User;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.controllers.forms.LoginForm;
import com.reportingtool.utilities.ReportUtilities;

/**
 * TODO:RT falta implementar el control de usuarios;
 * 
 * @author Esteban Calderon
 *
 */
@Controller
@RequestMapping(value = "/")
@SessionAttributes("login")
public class LoginController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {

		System.out.println("Login Controller - login");

		LoginForm loginForm = new LoginForm();

		model.addAttribute("alerts", false);
		model.addAttribute("login", loginForm);

		return "login";
	}

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String submitLogin(@ModelAttribute("login") LoginForm loginForm,
			BindingResult result, Model model, SessionStatus status,
			HttpSession session) {

		System.out.println("Submit Login Controller - login");

		String resultModel = "";

		User userLogin = null;

		UserDAO userDAO = (UserDAO) applicationContext.getBean("userDAO");
		List<User> userList = userDAO.findAllByProperty(ReportUtilities
				.findUserDAO(loginForm.getUser(), loginForm.getPassword()));

		if (userList.size() == 0) {
			String message = "User not found.";
			System.out.println(message);

			AlertToView alert = new AlertToView(true, message);

			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);

			ReportUtilities.createUserControl(applicationContext, null,
					"Fail access with user " + loginForm.getUser(), true);

			resultModel = "login";
		} else {
			userLogin = userList.get(0);

			userLogin.setLastLoginTms(new Date(Calendar.getInstance()
					.getTimeInMillis()));
			userDAO.edit(userLogin);

			ReportUtilities.createUserControl(applicationContext, userLogin,
					"User access correctly of " + loginForm.getUser(), false);

			// model.addAttribute("user", userLogin);

			//resultModel = "dashboard";
			resultModel = "datamanager";
		}

		System.out.println("resultModel " + resultModel);
		return resultModel;
	}

	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {

		System.out.println("Login Controller - index");
		
		CompanyDAO companyDao = (CompanyDAO)applicationContext.getBean("companyDAO");
		List<Company> companies = companyDao.findAll();
		
		System.out.println(companies.size() + " companies");
		model.addAttribute("companies", companies);
		
		// return "dashboard";
		return "datamanager";
	}
}
