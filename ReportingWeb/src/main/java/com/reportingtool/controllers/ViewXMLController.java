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
import com.entities.dictionary.ErrorTypeEnum;
import com.entities.entity.reportingtool.ReportExecution;
import com.reportingtool.controllers.forms.GenerateXMLForm;
import com.reportingtool.creation.GeneratorXML;
import com.reportingtool.utilities.ReportUtilities;
import com.reportingtool.utilities.ReportingErrorManager;

@Controller
@RequestMapping(value = "/viewXML.do")
@SessionAttributes("generateXML")
public class ViewXMLController {

	@Autowired
	ApplicationContext applicationContext;

	// variable with reportExecution and outputXML, filled in GET, used in
	// POST to generate file.xml
	private GenerateXMLForm generateXMLForm = null;

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

		try {
			generateXMLForm = generatorXML.generateXML(reportExecution);

			// this form has reportExecution with new potential errors to check
			reportExecution = generateXMLForm.getReportExecution();
			ReportingErrorManager
					.checkReportExecutionHasErrors(reportExecution);

		} catch (Exception e) {
			System.out.println("DEBUG:_"
					+ "XML Generator, Error when generating XML: "
					+ e.getMessage());
		}

		// can be null if XML has errors
		if (generateXMLForm == null) {
			generateXMLForm = new GenerateXMLForm();
			generateXMLForm.setHasErrors(true);
			generateXMLForm.setOutputXML("");
			generateXMLForm.setReportExecution(reportExecution);
			generateXMLForm.setValid(false);
		}
		
		model.addAttribute("generateXML", generateXMLForm);

		return "viewxml";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected void exportXML(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("download XML report");

		DateFormat dateFormat = new SimpleDateFormat(
				ReportUtilities.datePattern);
		Date today = Calendar.getInstance().getTime();
		String creationDate = dateFormat.format(today);

		String reportName = generateXMLForm.getReportExecution()
				.getReportExecutionName() + "_" + creationDate + ".xml";

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

			StringBuffer sb = new StringBuffer(generateXMLForm.getOutputXML());
			InputStream in = new ByteArrayInputStream(sb.toString().getBytes(
					"UTF-8"));

			IOUtils.copy(in, out);
			in.close();
			out.close();

		} catch (Exception e) {
			e.getMessage();
			// e.printStackTrace();
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(),
					generateXMLForm.getReportExecution(), "FAIL",
					"Error when download XML " + e.getMessage());
		}

	}

}
