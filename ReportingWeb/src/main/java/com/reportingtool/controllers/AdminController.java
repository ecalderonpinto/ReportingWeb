package com.reportingtool.controllers;

import java.util.List;
import java.util.Locale;

import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.dao.reportingtool.ReportFieldDAO;
import com.entities.dao.reportingtool.ReportFieldListDAO;
import com.entities.entity.InstallEntities;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportField;
import com.entities.entity.reportingtool.ReportFieldList;

@Controller
public class AdminController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);
	
	@RequestMapping(value = "/admin.do", method = RequestMethod.GET)
	public String reportCatalogController(Locale locale, Model model) {

		ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
				.getBean("reportCatalogDAO");
		List<ReportCatalog> reportCatalogs = reportCatalogDAO.findAll();

		System.out.println(reportCatalogs.size() + " reportCatalogs");
		model.addAttribute("reportcatalogs", reportCatalogs);

		System.out.println("Report Catalog Controller - preForm");
		
		return "admin";
	}
	
	@RequestMapping(value="/reportCatalogDetail.do", method=RequestMethod.GET)
	public String reportCatalogDetailController(@RequestParam("id") String id, Model model){
		
		System.out.println("Report Catalog Detail Controller - id=" + id);
		
		ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
				.getBean("reportCatalogDAO");
		ReportCatalog reportCatalog = reportCatalogDAO.findById(Long.parseLong(id));
		
		model.addAttribute("reportcatalog", reportCatalog);
		
		model.addAttribute("reportfields",  reportCatalog.getReportFields());
		
		return "reportcatalogdetail";
	}
	
	@RequestMapping(value="/reportFieldDetail.do", method=RequestMethod.GET)
	public String reportFieldDetailController(@RequestParam("id") String id, Model model){
		
		System.out.println("Report Field Detail Controller - id=" + id);
		
		ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
				.getBean("reportFieldDAO");
		ReportField reportField = reportFieldDAO.findById(Long.parseLong(id));
		
		model.addAttribute("reportfield", reportField);
		
		model.addAttribute("reportcatalog", reportField.getReportCatalog());
		
		return "reportfielddetail";
	}

	@RequestMapping(value="/generateDB.do")
	public String generateDataBase(Model model) {

		LocalSessionFactoryBean sessionFactory = this.applicationContext.getBean("&sessionFactory", LocalSessionFactoryBean.class);
		
		SchemaExport export = new SchemaExport(sessionFactory.getConfiguration());
		export.drop(false, true);
		export.create(false, true);
		
		System.out.println("Controller admin/generateDB");
		
		return "admin";
	}
	
	@RequestMapping(value = "/reportFieldList.do", method = RequestMethod.GET)
	public String reportFieldListController(Locale locale, Model model) {

		ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) applicationContext
				.getBean("reportFieldListDAO");
		List<ReportFieldList> reportFieldList = reportFieldListDAO.findAll();

		System.out.println(reportFieldList.size() + " reportFieldList");
		model.addAttribute("reportfieldlist", reportFieldList);

		System.out.println("Report Field List Controller - preForm");
		
		return "reportfieldlist";
	}
	
	@RequestMapping(value="/installTestData.do")
	public String installTestData(Model model){
		
		InstallEntities install = new InstallEntities();
		
		install.deleteEntities(applicationContext);
		install.installEntitiesFull(applicationContext);
		install.installAIF(applicationContext);
		
		return "admin";
	}
	
	@RequestMapping(value="/cleanData.do")
	public String cleanData(Model model){
		
		InstallEntities install = new InstallEntities();
		
		install.deleteEntities(applicationContext);
		
		return "admin";
	}

}
