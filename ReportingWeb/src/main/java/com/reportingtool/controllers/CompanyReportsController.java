package com.reportingtool.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.entity.reportingtool.Company;


@Controller
@RequestMapping(value="/companyReports.do")
@SessionAttributes("company")
public class CompanyReportsController {

	@Autowired
	ApplicationContext applicationContext;
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyReportsController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String DataManagerControllerPre(@RequestParam("id") String id, Model model){
		
		System.out.println("CompanyDetail Controller - Fundid=" + id);
		CompanyDAO companyDao = (CompanyDAO)applicationContext.getBean("companyDAO");
		Company company = companyDao.findById(Long.parseLong(id));
		
		model.addAttribute("company", company);
		
		return "companyreports";
	}
}
