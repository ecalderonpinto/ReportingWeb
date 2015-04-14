package com.reportingtool.controllers;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.entity.reportingtool.Company;


@Controller
public class DataManagerController {

	@Autowired
	ApplicationContext applicationContext;
	
	private static final Logger logger = LoggerFactory.getLogger(DataManagerController.class);
	
	/**
	 * TODO:RT Considerar incorporar más info a esta pantalla;
	 */
	@RequestMapping(value="/dataManager.do", method=RequestMethod.GET)
	public String DataManagerControllerPre(Locale locale, Model model){
		
		CompanyDAO companyDao = (CompanyDAO)applicationContext.getBean("companyDAO");
		List<Company> companies = companyDao.findAll();
		
		System.out.println(companies.size() + " companies");
		model.addAttribute("companies", companies);
		
		System.out.println("DataManager Controller - preForm");
		return "datamanager";
	}
}
