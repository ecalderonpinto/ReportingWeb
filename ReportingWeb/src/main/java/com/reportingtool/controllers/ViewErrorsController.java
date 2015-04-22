package com.reportingtool.controllers;

import java.util.ArrayList;
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

import com.entities.dao.loader.LoadErrorDAO;
import com.entities.dao.loader.LoadFileDAO;
import com.entities.dao.reportingtool.ReportDataErrorDAO;
import com.entities.dao.reportingtool.ReportErrorDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.entity.loader.LoadError;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportDataError;
import com.entities.entity.reportingtool.ReportError;
import com.entities.entity.reportingtool.ReportExecution;

@Controller
public class ViewErrorsController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(ViewErrorsController.class);

	@RequestMapping(value = "/viewErrors.do", method = RequestMethod.GET)
	public String loadErrorDetailController(@RequestParam("id") String id,
			Model model) {

		System.out.println("ViewErrors Controller - LoadId=" + id);
		LoadFileDAO loadFileDAO = (LoadFileDAO) applicationContext
				.getBean("loadFileDAO");
		LoadFile loadFile = loadFileDAO.findById(Long.parseLong(id));

		model.addAttribute("load", loadFile);

		return "viewerrors";
	}

	@RequestMapping(value = "/loadError.do", method = RequestMethod.GET)
	public String loadErrorController(Locale locale, Model model) {

		LoadErrorDAO loadErrorDAO = (LoadErrorDAO) applicationContext
				.getBean("loadErrorDAO");
		List<LoadError> loadErrors = loadErrorDAO.findAll();

		System.out.println(loadErrors.size() + " loadErrors");
		model.addAttribute("loaderrors", loadErrors);

		System.out.println("Load Error Controller - preForm");

		return "loaderror";
	}

	@RequestMapping(value = "/reportError.do", method = RequestMethod.GET)
	public String reportErrorController(@RequestParam("id") String id,
			Locale locale, Model model) {

		ReportErrorDAO reportErrorDAO = (ReportErrorDAO) applicationContext
				.getBean("reportErrorDAO");

		ReportDataErrorDAO reportDataErrorDAO = (ReportDataErrorDAO) applicationContext
				.getBean("reportDataErrorDAO");

		List<ReportError> reportErrors;

		List<ReportDataError> reportDataErrors;

		if (id != null && !id.isEmpty()) {
			ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
					.getBean("reportExecutionDAO");
			ReportExecution reportExecution = reportExecutionDAO.findById(Long
					.parseLong(id));

			ReportError reportErrorExample = new ReportError();
			reportErrorExample.setReportExecution(reportExecution);
			reportErrors = reportErrorDAO.findByExample(reportErrorExample);
			System.out.println("reportError id:" + id);

			reportDataErrors = new ArrayList<ReportDataError>();
			for (ReportData reportData : reportExecution.getReportDatas()) {
				reportDataErrors.addAll(reportData.getReportDataErrors());
			}

		} else {
			reportErrors = reportErrorDAO.findAll();
			System.out.println("reportError all");

			reportDataErrors = reportDataErrorDAO.findAll();
		}

		System.out.println(reportErrors.size() + " reportErrors");
		model.addAttribute("reporterrors", reportErrors);

		model.addAttribute("reportdataerrors", reportDataErrors);

		System.out.println("Report Error Controller - preForm");

		return "reporterror";
	}

	@RequestMapping(value = "/reportDataError.do", method = RequestMethod.GET)
	public String reportdataErrorController(Locale locale, Model model) {

		ReportDataErrorDAO reportDataErrorDAO = (ReportDataErrorDAO) applicationContext
				.getBean("reportDataErrorDAO");
		List<ReportDataError> reportDataErrors = reportDataErrorDAO.findAll();

		System.out.println(reportDataErrors.size() + " reportDataErrors");
		model.addAttribute("reportdataerrors", reportDataErrors);

		System.out.println("Report Data Error Controller - preForm");

		return "reportdataerror";
	}

}
