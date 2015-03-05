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

import com.entities.dao.loader.FileConfigDAO;
import com.entities.entity.loader.FileConfig;

@Controller
public class FileConfigController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	@RequestMapping(value = "/fileConfig.do", method = RequestMethod.GET)
	public String DataManagerControllerPre(Locale locale, Model model) {

		FileConfigDAO fileConfigDAO = (FileConfigDAO) applicationContext
				.getBean("fileConfigDAO");
		List<FileConfig> fileConfigs = fileConfigDAO.findAll();

		System.out.println(fileConfigs.size() + " fileConfigs");
		model.addAttribute("fileconfigs", fileConfigs);

		System.out.println("File Config Controller - preForm");
		
		return "fileconfig";
	}
	
}
