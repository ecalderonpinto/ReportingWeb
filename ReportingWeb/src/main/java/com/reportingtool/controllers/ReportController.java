package com.reportingtool.controllers;

import java.util.ArrayList;
import java.util.List;

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
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;

@Controller
@RequestMapping(value = "/report.do")
@SessionAttributes("report")
public class ReportController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(ReportController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String ReportControllerPre(@RequestParam("id") String id, Model model) {

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");

		ReportExecution example = new ReportExecution();
		example.setId(Long.valueOf(id).longValue());
		ReportExecution reportExecution = reportExecutionDAO.findByExample(
				example).get(0);
		
		if(reportExecution.getReportErrors().size() > 0)
			System.out.println("Tiene errores...");
		
		List<String> sections = getSections(reportExecution);

		model.addAttribute("report", reportExecution);
		model.addAttribute("sections", sections);

		return "report";
	}

	private List<String> getSections(ReportExecution reportExe) {

		List<String> result = new ArrayList<String>();

		for (ReportData reportData : reportExe.getReportDatas()) {
			if(reportData.getReportDataErrors().size() > 0)
				System.out.println("Data tiene errores...");
			if (reportData.getReportField().getReportFieldSection() != null
					&& !result.contains(reportData.getReportField()
							.getReportFieldSection()))
				result.add(reportData.getReportField().getReportFieldSection());
		}

		return result;
	}
}
