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
		ReportExecution reportExecution = reportExecutionDAO
				.findById(Long.parseLong(id));

		ReportAssignLoadsForm reportAssign = new ReportAssignLoadsForm();
		reportAssign.setReportExecution(reportExecution);

		model.addAttribute("reportassign", reportAssign);

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
				.findByExample(reportAssign.getReportExecution()).get(0);
		reportAssign.setReportExecution(reportExe);

		for (String loadFile : reportAssign.getSelectLoads()) {
			LoadFile lF = loadFileDAO.findById(Long.parseLong(loadFile));
			reportAssign.getReportExecution().getLoadFiles().add(lF);
		}

		// Raw to Data
		RawData rawData = new RawData(applicationContext);
		rawData.fileRawToData(reportAssign.getReportExecution());

		// Syntactic analysis
		Syntactic syntactic = new Syntactic(applicationContext);

		System.out.println("DEBUG_" + "TestValidator: starting for list: "
				+ reportAssign.getReportExecution().getReportDatas());
		Set<ReportData> reportDatas = reportAssign.getReportExecution()
				.getReportDatas();
		for (ReportData reportData : reportDatas) {
			System.out.println("DEBUG_" + "TestValidator: "
					+ reportData.getReportDataDate() + " "
					+ reportData.getReportDataText());
			syntactic.validInValueList(reportData);
			syntactic.validRegex(reportData);
		}

		return "datamanager";
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
