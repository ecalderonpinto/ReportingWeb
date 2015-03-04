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
import com.entities.dao.loader.LoadRawDAO;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.loader.LoadRaw;


@Controller
@RequestMapping(value="/rawDetail.do")
public class RawDetailController {

	@Autowired
	ApplicationContext applicationContext;
	
	private static final Logger logger = LoggerFactory.getLogger(RawDetailController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String DataManagerControllerPre(@RequestParam("id") String id, Model model){
		
		System.out.println("RawDetail Controller - id=" + id);
		LoadRawDAO loadRawDao = (LoadRawDAO)applicationContext.getBean("loadRawDAO");
		LoadRaw loadRaw = loadRawDao.findById(Long.parseLong(id));
		
		model.addAttribute("loadRaw", loadRaw);
		
		return "rawdetail";
	}
}
