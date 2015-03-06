package com.reportingtool.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportDataError;
import com.entities.entity.reportingtool.ReportExecution;
import com.reportingtool.controllers.forms.ReportAssignLoadsForm;
import com.reportingtool.validator.Semantic;
import com.reportingtool.validator.Syntactic;

@Controller
@RequestMapping(value = "/reportExecution.do")
@SessionAttributes("reportexecution")
public class ReportExecutionController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(ReportExecutionController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String ReportControllerPre(@RequestParam("id") String id, Model model) {

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");

		// ReportExecution example = new ReportExecution();
		// example.setId(Long.valueOf(id).longValue());
		// ReportExecution reportExecution = reportExecutionDAO.findByExample(
		// example).get(0);

		ReportExecution reportExecution = reportExecutionDAO.findById(Long
				.parseLong(id));

		if (reportExecution.getReportErrors().size() > 0) {
			System.out.println("Tiene errores...");
		}

		List<String> sections = getSections(reportExecution);

		model.addAttribute("reportexecution", reportExecution);
		model.addAttribute("sections", sections);

		return "reportexecution";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("reportexecution") ReportExecution reportExecution,
			BindingResult result, Model model, SessionStatus status,
			HttpSession session) {

		System.out.println("Submit - ReportExecution;");

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");
		reportExecutionDAO.edit(reportExecution);
		
		Syntactic syntactic = new Syntactic(applicationContext);
		Semantic semantic = new Semantic(applicationContext);
		
		syntactic.validReportExecution(reportExecution);
		semantic.checkSemantic(reportExecution);

		List<String> sections = getSections(reportExecution);

		model.addAttribute("reportexecution", reportExecution);
		model.addAttribute("sections", sections);

		return "reportexecution";
	}

	private List<String> getSections(ReportExecution reportExe) {

		List<String> result = new ArrayList<String>();

		for (ReportData reportData : reportExe.getReportDatas()) {
			if (reportData.getReportDataErrors().size() > 0) {
				System.out.println("Data tiene errores...");
				for (ReportDataError error : reportData.getReportDataErrors())
					System.out.println("Error: "
							+ error.getReportDataErrorText());
			}
			if (reportData.getReportField().getReportFieldSection() != null
					&& !result.contains(reportData.getReportField()
							.getReportFieldSection()))
				result.add(reportData.getReportField().getReportFieldSection());
		}

		return result;
	}
}
