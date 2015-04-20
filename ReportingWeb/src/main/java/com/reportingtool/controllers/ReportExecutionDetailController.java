package com.reportingtool.controllers;

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

import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.entity.reportingtool.Company;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.utilities.hibernate.VersionAuditor;

@Controller
@RequestMapping(value = "/reportExecutionDetail.do")
@SessionAttributes("reportexecutiondetail")
public class ReportExecutionDetailController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(ReportExecutionDetailController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String ReportExecutionDetailControllerPre(
			@RequestParam("id") String id, Model model) {

		System.out.println("ReportExecutionDetailControllerPre");

		try {

			CompanyDAO companyDao = (CompanyDAO) applicationContext
					.getBean("companyDAO");
			Company company = companyDao.findById(Long.parseLong(id));

			ReportCatalog reportCatalog = new ReportCatalog();
			reportCatalog.setReportLevel("COMPANY");
			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			reportCatalog = reportCatalogDAO.findByExample(reportCatalog)
					.get(0);

			ReportExecution reportExecution = new ReportExecution();
			reportExecution.setReportCatalog(reportCatalog);
			reportExecution.setCompany(company);
			reportExecution.setAuditor(new VersionAuditor("report"));

			System.out.println("new report: " + company.getCompanyName() + " "
					+ reportCatalog.getReportCatalogName());

			model.addAttribute("company", company);
			model.addAttribute("catalog", reportCatalog);
			model.addAttribute("reportexecutiondetail", reportExecution);
			
			model.addAttribute("result", "");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "reportexecutiondetail";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("reportexecutiondetail") ReportExecution reportExecution,
			BindingResult result, Model model, SessionStatus status,
			HttpSession session) {

		System.out.println("SUBMIT reportExecution: "
				+ reportExecution.getReportExecutionName());

		String resultMessage;
		
		if (reportExecution.getReportExecutionName() != null
				&& !reportExecution.getReportExecutionName().isEmpty() &&
				reportExecution.getReportPeriodType() != null
				&& !reportExecution.getReportPeriodType().isEmpty() &&
				reportExecution.getReportPeriodYear() != null
				&& !reportExecution.getReportPeriodYear().isEmpty() ) {
			
			ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) this.applicationContext
					.getBean("reportExecutionDAO");
			reportExecutionDAO.create(reportExecution);
			
			resultMessage = "Report Execution created";
			
		} else {
			resultMessage = "Report Execution field is missing";
		}
		
		System.out.println("result: " + resultMessage);
		
		return "companyreports";
	}

}
