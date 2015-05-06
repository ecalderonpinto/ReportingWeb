package com.reportingtool.controllers;

import java.io.File;
import java.io.FileOutputStream;

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
import com.entities.entity.reportingtool.ReportExecution;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.controllers.forms.LoadXMLFileForm;
import com.reportingtool.creation.LoadXML;

@Controller
@RequestMapping(value = "/loadXMLToReport.do")
@SessionAttributes("loadXML")
public class LoadXMLToReport {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(LoadXMLToReport.class);

	@RequestMapping(method = RequestMethod.GET)
	public String LoadXMLController(@RequestParam("id") String id, Model model) {

		System.out.println("XML Form controller");

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");
		ReportExecution reportExecution = reportExecutionDAO.findById(Long
				.parseLong(id));

		LoadXMLFileForm loadXMLFileForm = new LoadXMLFileForm();
		loadXMLFileForm.setReportExecution(reportExecution);

		model.addAttribute("alerts", false);
		model.addAttribute("loadXML", loadXMLFileForm);

		return "loadxmltoreport";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("loadXML") LoadXMLFileForm loadXMLFileForm,
			BindingResult result, Model model, SessionStatus status,
			HttpSession session) {

		System.out.println("Submit XML Form controller");

		AlertToView alert = new AlertToView(false, "All were success");

		File file = null;

		try {

			file = new File(loadXMLFileForm.getInputFile()
					.getOriginalFilename());
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(loadXMLFileForm.getInputFile().getBytes());
			fos.close();

			if (file.exists()) {
				System.out.println("llega fichero xml " + file.getPath());

				LoadXML loadXML = new LoadXML(applicationContext);

				ReportExecution reportExecution = loadXMLFileForm
						.getReportExecution();

				reportExecution = loadXML.loadAIFMFile(file, reportExecution);

				ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
						.getBean("reportExecutionDAO");

				reportExecutionDAO.edit(reportExecution);
				System.out.println("saved changes of reportExecution "
						+ reportExecution.getReportExecutionName());
			}

		} catch (Exception e) {
			alert.setError(true);
			alert.setMessage("Error: " + e.getMessage());
			e.printStackTrace();
		}

		model.addAttribute("alerts", true);
		model.addAttribute("alert", alert);

		return "loadxmltoreport";
	}

}
