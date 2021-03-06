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
import com.entities.entity.install.InstallManager;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportField;
import com.entities.entity.reportingtool.ReportFieldList;
import com.reportingtool.controllers.forms.AlertToView;

@Controller
public class AdminController {
	
	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);
	
	/**
	 * TODO:RT Considerar UI y ver necesidad de incorporar m�s funcionalidad.
	 */
	@RequestMapping(value = "/admin.do", method = RequestMethod.GET)
	public String reportCatalogController(Locale locale, Model model) {

		System.out.println("Report Catalog Controller - preForm");
		
		ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
				.getBean("reportCatalogDAO");
		List<ReportCatalog> reportCatalogs = reportCatalogDAO.findAll();

		model.addAttribute("reportcatalogs", reportCatalogs);
		model.addAttribute("alerts", false);
		
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
	
	/**
	 * TODO:RT Falta editar/guardar informaci�n;
	 */
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
		
		AlertToView alert = new AlertToView(false, "Generation of DB complete");
		model.addAttribute("alerts", true);
		model.addAttribute("alert", alert);
		
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
		
		InstallManager installManager = new InstallManager(applicationContext);
		
		installManager.deleteAll();
		installManager.installAll();
		
		// to refresh content, load al data similar to "admin"
		ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
				.getBean("reportCatalogDAO");
		List<ReportCatalog> reportCatalogs = reportCatalogDAO.findAll();

		AlertToView alert = new AlertToView(false, "Install data correct");
		
		model.addAttribute("reportcatalogs", reportCatalogs);
		model.addAttribute("alerts", true);
		model.addAttribute("alert", alert);
		
		return "admin";
	}
	
	@RequestMapping(value="/cleanData.do")
	public String cleanData(Model model){
		
		InstallManager installManager = new InstallManager(applicationContext);
		
		installManager.deleteAll();
		
		// to refresh content, load al data similar to "admin"
		ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
				.getBean("reportCatalogDAO");
		List<ReportCatalog> reportCatalogs = reportCatalogDAO.findAll();

		AlertToView alert = new AlertToView(false, "Delete all data");
		
		model.addAttribute("reportcatalogs", reportCatalogs);
		model.addAttribute("alerts", true);
		model.addAttribute("alert", alert);
		
		return "admin";
	}

}
