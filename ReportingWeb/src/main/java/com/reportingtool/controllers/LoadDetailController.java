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

import com.entities.dao.loader.LoadFileDAO;
import com.entities.entity.loader.LoadFile;


@Controller
@RequestMapping(value="/loadDetail.do")
public class LoadDetailController {

	@Autowired
	ApplicationContext applicationContext;
	
	private static final Logger logger = LoggerFactory.getLogger(LoadDetailController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String DataManagerControllerPre(@RequestParam("id") String id, Model model){
		
		System.out.println("LoadDetail Controller - id=" + id);
		LoadFileDAO loadFileDao = (LoadFileDAO)applicationContext.getBean("loadFileDAO");
		LoadFile loadFile = loadFileDao.findById(Long.parseLong(id));
		
		System.out.println("Raws: " + loadFile.getLoadRaws().size());
		
		model.addAttribute("loadFile", loadFile);
		
		return "loaddetail";
	}
}
