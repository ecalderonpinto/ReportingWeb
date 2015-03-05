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

import com.entities.dao.loader.FileColumDAO;
import com.entities.entity.loader.FileColum;

@Controller
@RequestMapping(value="/fileColumDetail.do")
@SessionAttributes("filecolum")
public class FileColumDetailController {

	@Autowired
	ApplicationContext applicationContext;
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyDetailController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String DataManagerControllerPre(@RequestParam("id") String id, Model model){
		
		System.out.println("File Colum Detail Controller - id=" + id);
		
		FileColumDAO fileColumDAO = (FileColumDAO) applicationContext
				.getBean("fileColumDAO");
		FileColum fileColum = fileColumDAO.findById(Long.parseLong(id));
		
		model.addAttribute("filecolum", fileColum);
		
		return "filecolumdetail";
	}
	
}
