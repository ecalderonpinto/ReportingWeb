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

import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.entity.reportingtool.ReportExecution;
import com.reportingtool.controllers.forms.ReportAssignLoadsForm;
import com.reportingtool.creation.GeneratorXML;

@Controller
@RequestMapping(value = "/viewXML.do")
@SessionAttributes("report")
public class ViewXMLController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(ViewXMLController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String DataManagerControllerPre(@RequestParam("id") String id,
			Model model) {

		System.out.println("view XML controller");

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");
		
//		ReportExecution reportExecution = new ReportExecution();
//		reportExecution.setId(Long.valueOf(id).longValue());
//		reportExecution = (ReportExecution) reportExecutionDAO.findByExample(
//				reportExecution).get(0);
		
		ReportExecution reportExecution = reportExecutionDAO.findById(Long.parseLong(id));

		GeneratorXML generatorXML = new GeneratorXML(applicationContext);
		String outputXML = generatorXML.generateXML(reportExecution);
		
		model.addAttribute("report", reportExecution);
		model.addAttribute("outputXML", outputXML);

		return "viewxml";
	}

}
