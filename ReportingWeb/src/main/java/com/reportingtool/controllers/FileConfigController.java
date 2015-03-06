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
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.dao.loader.FileColumDAO;
import com.entities.dao.loader.FileConfigDAO;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.FileConfig;

@Controller
public class FileConfigController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	@RequestMapping(value = "/fileConfig.do", method = RequestMethod.GET)
	public String fileConfigController(Locale locale, Model model) {

		FileConfigDAO fileConfigDAO = (FileConfigDAO) applicationContext
				.getBean("fileConfigDAO");
		List<FileConfig> fileConfigs = fileConfigDAO.findAll();

		System.out.println(fileConfigs.size() + " fileConfigs");
		model.addAttribute("fileconfigs", fileConfigs);

		System.out.println("File Config Controller - preForm");
		
		return "fileconfig";
	}
	
	@RequestMapping(value="/fileConfigDetail.do", method=RequestMethod.GET)
	public String fileConfigDetailController(@RequestParam("id") String id, Model model){
		
		System.out.println("FileConfigDetail Controller - id=" + id);
		FileConfigDAO fileConfigDAO = (FileConfigDAO) applicationContext
				.getBean("fileConfigDAO");
		FileConfig fileConfig = fileConfigDAO.findById(Long.parseLong(id));
		
		model.addAttribute("fileconfig", fileConfig);
		
		model.addAttribute("filecolums", fileConfig.getFileColums());
		
		return "fileconfigdetail";
	}
	
	@RequestMapping(value="/fileColumDetail.do", method=RequestMethod.GET)
	public String fileColumDetailController(@RequestParam("id") String id, Model model){
		
		System.out.println("File Colum Detail Controller - id=" + id);
		
		FileColumDAO fileColumDAO = (FileColumDAO) applicationContext
				.getBean("fileColumDAO");
		FileColum fileColum = fileColumDAO.findById(Long.parseLong(id));
		
		model.addAttribute("filecolum", fileColum);
		
		model.addAttribute("fileconfig", fileColum.getFileConfig());
		
		return "filecolumdetail";
	}
	
}
