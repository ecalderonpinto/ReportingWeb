package com.reportingtool.controllers;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.dao.usermanager.UserDAO;
import com.entities.dao.usermanager.UserRolDAO;
import com.entities.entity.reportingtool.Company;
import com.entities.entity.usermanager.User;
import com.entities.entity.usermanager.UserRol;
import com.entities.utilities.hibernate.VersionAuditor;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.controllers.forms.UserDetailForm;

@Controller
public class UserDetailController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(UserDetailController.class);

	@RequestMapping(value = "/userDetail.do", method = RequestMethod.GET)
	public String userDetailController(@RequestParam("id") String id,
			Model model) {

		System.out.println("User Detail Controller - preForm");
		User user = null;

		// if id=0 new User, else we edit User

		if (id.equals("0")) {
			user = new User();
			user.setEnabled(false);
			UserRol userRol = new UserRol();
			userRol.setRolName("user");
			user.setUserRol(userRol);
		} else {
			UserDAO userDAO = (UserDAO) applicationContext.getBean("userDAO");
			user = userDAO.findById(Long.parseLong(id));
		}

		// list of Company
		CompanyDAO companyDAO = (CompanyDAO) applicationContext
				.getBean("companyDAO");
		List<Company> companyList = companyDAO.findAll();
		List<String> userCompany = new ArrayList<String>();
		userCompany.add("--SELECT--");
		for (Company company : companyList) {
			userCompany.add(company.getCompanyName());
		}

		// list of UserRol
		UserRolDAO userRolDAO = (UserRolDAO) applicationContext
				.getBean("userRolDAO");
		List<UserRol> userRolList = userRolDAO.findAll();
		List<String> userRolDrop = new ArrayList<String>();
		for (UserRol userRol : userRolList) {
			userRolDrop.add(userRol.getRolName());
		}

		// enabled: true/false
		List<String> userEnabledDrop = new ArrayList<String>();
		userEnabledDrop.add("true");
		userEnabledDrop.add("false");

		// User form
		UserDetailForm userDetailForm = new UserDetailForm();
		userDetailForm.setUser(user);
		userDetailForm.setSelectUserRol(user.getUserRol().getRolName());
		userDetailForm.setSelectEnabled(Boolean.toString(user.isEnabled()));
		userDetailForm.setUserId(id);

		model.addAttribute("userRolDrop", userRolDrop);
		model.addAttribute("userEnabledDrop", userEnabledDrop);
		model.addAttribute("userCompany", userCompany);
		model.addAttribute("userdetail", userDetailForm);

		return "userdetail";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("userdetail") UserDetailForm userDetailForm,
			BindingResult result, Model model, SessionStatus status,
			HttpSession session) {

		System.out.println("User Detail Controller - save ");

		AlertToView alert = new AlertToView(false, "");

		UserDAO userDAO = (UserDAO) applicationContext.getBean("userDAO");

		// CompanyDAO
		CompanyDAO companyDAO = (CompanyDAO) applicationContext
				.getBean("companyDAO");

		// UserRolDAO
		UserRolDAO userRolDAO = (UserRolDAO) applicationContext
				.getBean("userRolDAO");

		try {

			VersionAuditor versionAdmin = new VersionAuditor("admin");

			User user = userDetailForm.getUser();

			// Company
			Company companyExample = new Company();
			Company company = null;
			if (!userDetailForm.getSelectCompany().equals("--SELECT--")) {
				companyExample
						.setCompanyName(userDetailForm.getSelectCompany());
				company = new Company();
				company = companyDAO.findByExample(companyExample).get(0);
			}

			// UserRol
			UserRol userRolExample = new UserRol();
			userRolExample.setRolName(userDetailForm.getSelectUserRol());
			UserRol userRol = userRolDAO.findByExample(userRolExample).get(0);

			user.setEnabled(Boolean.parseBoolean(userDetailForm
					.getSelectEnabled()));
			user.setCompany(company);
			user.setUserRol(userRol);
			user.setVersionAuditor(versionAdmin);

			// if id=0 new User, else we edit User

			if (userDetailForm.getUserId().equals("0")) {
				userDAO.create(user);
				alert.setMessage("User created.");
			} else {
				user.setId(Long.parseLong(userDetailForm.getUserId()));
				userDAO.edit(user);
				alert.setMessage("User changes saved.");
			}

		} catch (Exception e) {
			alert.setError(true);
			alert.setMessage("User not saved, see error message: "
					+ e.getMessage());
			e.printStackTrace();
		}

		model.addAttribute("alerts", true);
		model.addAttribute("alert", alert);

		userDAO = (UserDAO) applicationContext.getBean("userDAO");
		List<User> users = userDAO.findAll();

		System.out.println(users.size() + " users");
		model.addAttribute("users", users);

		return "user";
	}

}
