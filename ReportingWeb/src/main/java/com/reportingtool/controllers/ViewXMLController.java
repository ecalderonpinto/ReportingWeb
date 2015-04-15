package com.reportingtool.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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
import com.reportingtool.creation.GeneratorXML;
import com.reportingtool.normalizer.Format;
import com.reportingtool.utilities.ReportUtilities;
import com.reportingtool.utilities.ReportingErrorManager;

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

		ReportExecution reportExecution = reportExecutionDAO.findById(Long
				.parseLong(id));

		GeneratorXML generatorXML = new GeneratorXML(applicationContext);
		String outputXML = generatorXML.generateXML(reportExecution);

		model.addAttribute("report", reportExecution);
		model.addAttribute("outputXML", outputXML);

		return "viewxml";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected void exportXML(@RequestParam("reportid") String id,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("donwload XML report");

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");

		ReportExecution reportExecution = reportExecutionDAO.findById(Long
				.parseLong(id));

		GeneratorXML generatorXML = new GeneratorXML(applicationContext);
		String outputXML = generatorXML.generateXML(reportExecution);

		DateFormat dateFormat = new SimpleDateFormat(ReportUtilities.datePattern);
		Date today = Calendar.getInstance().getTime();
		String creationDate = dateFormat.format(today);

		String reportName = reportExecution.getReportExecutionName() + "_"
				+ creationDate + ".xml";

		// firefox does not recognize format of file if there are spaces in the
		// name: 'name report DATE.xml' -> is replaced 'name_report_DATE.xml'
		reportName = reportName.replace(" ", "_");

		// tell browser program going to return an application file
		// instead of html page
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ reportName);

		try {

			ServletOutputStream out = response.getOutputStream();
			StringBuffer sb = new StringBuffer(outputXML);

			InputStream in = new ByteArrayInputStream(sb.toString().getBytes(
					"UTF-8"));
			
			IOUtils.copy(in, out);
			in.close();
			out.close();

		} catch (Exception e) {
			e.getMessage();
			// e.printStackTrace();
			ReportingErrorManager.createReportError(applicationContext,
					"CREATION", reportExecution, "FAIL",
					"Error when dowload XML " + e.getMessage());
		}

	}

}
