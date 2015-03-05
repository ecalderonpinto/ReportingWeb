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

import com.entities.dao.loader.FileConfigDAO;
import com.entities.entity.loader.FileConfig;

@Controller
@SessionAttributes("fileColum")
public class FileColumController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	@RequestMapping(value = "/fileColums.do", method = RequestMethod.GET)
	public String DataManagerControllerPre(@RequestParam("id") String id, Model model) {

		
		FileConfigDAO fileConfigDAO = (FileConfigDAO) applicationContext
				.getBean("fileConfigDAO");
		FileConfig fileConfig = fileConfigDAO.findById(Long.parseLong(id));
		
		model.addAttribute("filecolums", fileConfig.getFileColums());

		System.out.println("File Colum Controller - preForm");
		
		return "filecolum";
	}
	
}
