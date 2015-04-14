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

import com.entities.dao.reportingtool.FundDAO;
import com.entities.entity.reportingtool.Fund;


@Controller
@RequestMapping(value="/fundReports.do")
@SessionAttributes("fund")
public class FundReportsController {

	@Autowired
	ApplicationContext applicationContext;
	
	private static final Logger logger = LoggerFactory.getLogger(FundReportsController.class);
	
	/**
	 * TODO:RT falta implementar los filtros y acciones de la botonera ACTIONS
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String DataManagerControllerPre(@RequestParam("id") String id, Model model){
		
		System.out.println("CompanyDetail Controller - Fundid=" + id);
		FundDAO fundDao = (FundDAO)applicationContext.getBean("fundDAO");
		Fund fund = fundDao.findById(Long.parseLong(id));
		
		model.addAttribute("fund", fund);
		
		return "fundreports";
	}
}
