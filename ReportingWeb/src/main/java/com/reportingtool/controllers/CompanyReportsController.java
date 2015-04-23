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

import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.dictionary.ReportExecutionStatusEnum;
import com.entities.entity.reportingtool.Company;
import com.entities.entity.reportingtool.ReportExecution;
import com.reportingtool.utilities.ReportingErrorManager;

@Controller
@RequestMapping(value = "/companyReports.do")
@SessionAttributes("company")
public class CompanyReportsController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyReportsController.class);

	/**
	 * TODO:RT Falta implementar filtros;
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String DataManagerControllerPre(@RequestParam("id") String id,
			Model model) {

		System.out.println("CompanyDetail Controller - Fundid=" + id);
		CompanyDAO companyDAO = (CompanyDAO) applicationContext
				.getBean("companyDAO");
		Company company = companyDAO.findById(Long.parseLong(id));

		// check some issues in every reportExecution before been showed
		for (ReportExecution reportExecution : company.getReportExecutions()) {
			// set reportExecution.hasErrors and reportData.hasErrors true/false
			// to show link error/view XML
			ReportingErrorManager
					.checkReportExecutionHasErrors(reportExecution);

			// change reportExecution.reportStatus EMPTY -> CREATION
			if (reportExecution.getReportDatas().size() > 2) {
				if (reportExecution.getReportStatus().equals(
						ReportExecutionStatusEnum.EMPTY
								.getReportExecutionStatus())) {
					reportExecution
							.setReportStatus(ReportExecutionStatusEnum.CREATION
									.getReportExecutionStatus());
				}
			}
		}

		model.addAttribute("company", company);

		return "companyreports";
	}

}
