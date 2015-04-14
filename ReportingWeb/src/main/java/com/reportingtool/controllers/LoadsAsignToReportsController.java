package com.reportingtool.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.entities.dao.loader.LoadFileDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.reportingtool.controllers.forms.ReportAssignLoadsForm;
import com.reportingtool.validator.RawData;
import com.reportingtool.validator.Syntactic;

@Controller
@RequestMapping(value = "/loadsAssignToReport.do")
@SessionAttributes("reportassign")
public class LoadsAsignToReportsController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(LoadsAsignToReportsController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String DataManagerControllerPre(@RequestParam("id") String id,
			Model model) {

		System.out.println("Form controller");

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");
		long longId = Long.parseLong(id);
		System.out.println("Buscando ReportExecution con id: " + longId);
		ReportExecution reportExecution = reportExecutionDAO
				.findById(longId);
		System.out.println("ReportExecution recuperado: id=" + reportExecution.getId());
		ReportAssignLoadsForm reportAssign = new ReportAssignLoadsForm();
		reportAssign.setReportExecution(reportExecution);

		model.addAttribute("reportassign", reportAssign);

		// TODO falta que la vista loadsassign.jsp muestre los ficheros ya asignados
		
		return "loadsassigntoreport";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("reportassign") ReportAssignLoadsForm reportAssign,
			BindingResult result, Model model, SessionStatus status,
			HttpSession session) {

		LoadFileDAO loadFileDAO = (LoadFileDAO) applicationContext
				.getBean("loadFileDAO");
		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");

		ReportExecution reportExe = (ReportExecution) reportExecutionDAO
				.findById(reportAssign.getReportExecution().getId());
		System.out.println("ReportExecution que va a tratarse: id=" + reportExe.getId());
		reportAssign.setReportExecution(reportExe);

		for (String loadFile : reportAssign.getSelectLoads()) {
			LoadFile lF = loadFileDAO.findById(Long.parseLong(loadFile));
			if(!reportAssign.getReportExecution().getLoadFiles().contains(lF))
				reportAssign.getReportExecution().getLoadFiles().add(lF);
		}

		// Raw to Data
		RawData rawData = new RawData(applicationContext);
		rawData.fileRawToData(reportAssign.getReportExecution());

		// Syntactic analysis
		Syntactic syntactic = new Syntactic(applicationContext);

		System.out.println("DEBUG_" + "TestValidator: starting for list: "
				+ reportAssign.getReportExecution().getReportDatas());
		syntactic.validReportExecution(reportAssign.getReportExecution());

		model.addAttribute("reportassign", reportAssign);
		
		return "companyreports";
	}

	@ModelAttribute("selectLoads")
	public Map<String, String> populateJavaSkillList() {

		System.out.println("preLoad select");

		LoadFileDAO loadFileDAO = (LoadFileDAO) applicationContext
				.getBean("loadFileDAO");
		List<LoadFile> loads = loadFileDAO.findAll();

		Map<String, String> selectLoads = new HashMap<String, String>();
		for (LoadFile load : loads) {
			selectLoads
					.put(Long.toString(load.getId()), load.getLoadFileName());
		}

		return selectLoads;
	}

}
