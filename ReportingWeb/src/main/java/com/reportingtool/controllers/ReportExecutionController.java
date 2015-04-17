package com.reportingtool.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.entities.dao.reportingtool.ReportFieldListDAO;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportDataError;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;
import com.entities.entity.reportingtool.ReportFieldList;
import com.entities.utilities.hibernate.VersionAuditor;
import com.reportingtool.controllers.forms.ReportSectionForm;
import com.reportingtool.utilities.ReportingErrorManager;
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

		ReportExecution reportExecution = reportExecutionDAO.findById(Long
				.parseLong(id));

		List<String> sections = getSections(reportExecution);

		ReportingErrorManager.checkReportExecutionHasErrors(reportExecution);

		Map<String, String> fieldListMap = getReportFieldListDropdown();
		model.addAttribute("fieldlistmap", fieldListMap);

		List<String> fieldList = getReportFieldListTypeString();
		model.addAttribute("fieldlist", fieldList);

		reportExecution = addReportDatas(reportExecution);

		reportExecution = reportExecutionOrder(reportExecution);

		model.addAttribute("reportexecution", reportExecution);
		model.addAttribute("sections", sections);

		// // new about sections
		// List<ReportSectionForm> reportSectionForms =
		// getReportSections(reportExecution);
		// for(ReportSectionForm reportSectionForm : reportSectionForms) {
		// System.out.println("section : " + reportSectionForm.getSectionName()
		// + " - " + reportSectionForm.isHasBlock());
		// }
		// model.addAttribute("reportsections", reportSectionForms);

		return "reportexecution";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("reportexecution") ReportExecution reportExecution,
			BindingResult result, Model model, SessionStatus status,
			HttpSession session) {

		System.out.println("Submit - ReportExecution;");

		// for (ReportData reportData : reportExecution.getReportDatas()) {
		// System.out.println(reportData.getReportField().getReportFieldName()
		// + " - " + reportData.getReportDataText());
		// }

		System.out.println("cleaning reportDatas");
		reportExecution = cleanReportDatas(reportExecution);

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");

		System.out.println("save reportExecution");
		reportExecutionDAO.edit(reportExecution);
		reportExecution = reportExecutionDAO.findById(reportExecution.getId());

		Syntactic syntactic = new Syntactic(applicationContext);
		Semantic semantic = new Semantic(applicationContext);

		syntactic.validReportExecution(reportExecution);
		semantic.checkSemantic(reportExecution);

		reportExecutionDAO.edit(reportExecution);

		List<String> sections = getSections(reportExecution);

		ReportingErrorManager.checkReportExecutionHasErrors(reportExecution);

		reportExecution = reportExecutionOrder(reportExecution);

		model.addAttribute("reportexecution", reportExecution);
		model.addAttribute("sections", sections);

		Map<String, String> fieldListMap = getReportFieldListDropdown();
		model.addAttribute("fieldlistmap", fieldListMap);

		List<String> fieldList = getReportFieldListTypeString();
		model.addAttribute("fieldlist", fieldList);

		return "reportexecution";
	}

	private List<ReportSectionForm> getReportSections(
			ReportExecution reportExecution) {

		List<ReportSectionForm> result = new ArrayList<ReportSectionForm>();

		String section = "";
		boolean hasBlock = false;

		for (ReportData reportData : reportExecution.getReportDatas()) {
			if (reportData.getReportDataErrors().size() > 0) {
				System.out.println("Data tiene errores...");
				for (ReportDataError error : reportData.getReportDataErrors())
					System.out.println("Error: "
							+ error.getReportDataErrorText());
			}
			if (reportData.getReportField().getReportFieldSection() != null) {
				section = reportData.getReportField().getReportFieldSection();
				if (reportData.getReportDataBlock() != null) {
					hasBlock = true;
				} else {
					hasBlock = false;
				}
				ReportSectionForm reportSectionForm = new ReportSectionForm(
						section, hasBlock);
				if (!result.contains(reportSectionForm))
					result.add(reportSectionForm);
			}
		}

		return result;
	}

	/**
	 * Function that build a List<String> with sections to display
	 * 
	 * @param reportExecution
	 * @return List<String> of sections to display
	 */
	private List<String> getSections(ReportExecution reportExecution) {

		List<String> result = new ArrayList<String>();

		for (ReportData reportData : reportExecution.getReportDatas()) {
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

	/**
	 * Function to sort a reportExecution.reportDatas in order to display
	 * 
	 * @param reportExecution
	 * @return reportExecution sorted
	 */
	private ReportExecution reportExecutionOrder(ReportExecution reportExecution) {

		List<ReportData> reportDatas = new ArrayList<ReportData>(
				reportExecution.getReportDatas());

		List<ReportData> reportDataResult = new ArrayList<ReportData>();

		// temp List with reportDataOrder
		List<String> orderList = new ArrayList<String>();

		String reportDataOrder = "";

		// first for to create reportDataOrder
		for (ReportData reportData : reportDatas) {
			if (reportData.getReportField().getReportFieldSection() != null) {
				if (reportData.getReportDataBlock() != null) {
					String[] order = reportData.getReportField()
							.getReportFieldOrder().split("\\.");
					// System.out.println("spli: " + Arrays.toString(order));

					// reportDataOrder is section.block.fieldNumber
					reportDataOrder = order[0] + "."
							+ reportData.getReportDataBlock()
							+ reportData.getReportField().getReportFieldNum();
					reportData.setReportDataOrder(reportDataOrder);
				} else {
					// reportDataOrder is section.fieldNumber
					reportData.setReportDataOrder(reportData.getReportField()
							.getReportFieldOrder());
				}
				orderList.add(reportData.getReportDataOrder());
			}
			// System.out.println("reportData1 " +
			// reportData.getReportDataOrder() + " " +
			// reportData.getReportField().getReportFieldName());
		}

		Collections.sort(orderList);

		// second for to create reportDataResult with orderList
		for (String order : orderList) {
			// System.out.println("order: " +order);
			for (ReportData reportData : reportDatas) {
				// System.out.println("order: " + order + " -> " +
				// reportData.getReportDataOrder());
				if (reportData.getReportDataOrder().equals(order))
					reportDataResult.add(reportData);
			}
		}

		reportExecution.setReportDatas(reportDataResult);

		return reportExecution;
	}

	/**
	 * Function that add all reportData possible, UI can populate them
	 * 
	 * @param reportExecution
	 * @return reportExecution with all reportDatas to fill
	 */
	private ReportExecution addReportDatas(ReportExecution reportExecution) {

		List<ReportData> reportDatas = new ArrayList<ReportData>();
		List<ReportField> reportFields = new ArrayList<ReportField>(
				reportExecution.getReportCatalog().getReportFields());

		boolean flagField = false;
		for (ReportField reportField : reportFields) {
			if (reportField.getReportFieldSection() == null) {
				continue;
			}
			// System.out.println("reportField -> "
			// + reportField.getReportFieldName());
			for (ReportData reportData : reportExecution.getReportDatas()) {
				// System.out.println("reportData "
				// + reportData.getReportDataText());
				if (reportData.getReportField().equals(reportField)) {
					// reportData already exists
					reportDatas.add(reportData);
					flagField = true;
					// System.out.println("reportData added");
				}
			}
			if (flagField == false) {
				// add reportData
				System.out.println("no reportField, adding reportData of "
						+ reportField.getReportFieldName() + "-"
						+ reportField.getReportFieldNum().toString());
				ReportData reportDataTemp = new ReportData(null, reportField,
						reportExecution, null, null, "", null, null, null,
						new VersionAuditor("generated"));
				reportDatas.add(reportDataTemp);
			}
			flagField = false;
		}

		reportExecution.setReportDatas(reportDatas);

		return reportExecution;
	}

	/**
	 * Function to clean all empty reportDatas which are not populated by UI
	 * 
	 * @param reportExecution
	 * @return reportExecution cleaned of empty reportDatas
	 */
	private ReportExecution cleanReportDatas(ReportExecution reportExecution) {

		List<ReportData> reportDatas = new ArrayList<ReportData>();

		System.out.println("clean data, size entering: "
				+ reportExecution.getReportDatas().size());

		for (ReportData reportData : reportExecution.getReportDatas()) {

			if (reportData.getReportDataText() != null
					&& !reportData.getReportDataText().isEmpty()) {
				reportDatas.add(reportData);
//				System.out.println("adding reportData "
//						+ reportData.getReportDataText() + " from "
//						+ reportData.getReportField().getReportFieldName());
			}
		}

		System.out.println("clean data, size ending: " + reportDatas.size());

		reportExecution.setReportDatas(reportDatas);

		return reportExecution;
	}

	/**
	 * Function create Map<reportFieldType, reportFieldValues> to make dropdown
	 * 
	 * @return Map<String, String> of dropdown content
	 */
	private Map<String, String> getReportFieldListDropdown() {

		Map<String, String> result = new HashMap<String, String>();

		ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) applicationContext
				.getBean("reportFieldListDAO");

		List<ReportFieldList> reportFieldLists = reportFieldListDAO.findAll();

		// list of all fieldType, the relation between
		// reportField.reportFieldMask and reportFieldList
		List<String> filedTypeList = getReportFieldListTypeString();

		// populate result with dropdowns and their values
		for (String fieldType : filedTypeList) {
			String fieldListValues = "";
			for (ReportFieldList reportFieldList : reportFieldLists) {
				if (reportFieldList.getReportFieldListType().equals(fieldType))
					fieldListValues = fieldListValues + ","
							+ reportFieldList.getReportFieldListValue();
			}
			// System.out.println("dropdown: " + fieldType + "- "+
			// fieldListValues);

			result.put(fieldType, fieldListValues);
		}

		return result;
	}

	/**
	 * Function load list of reportFieldListTypes
	 * 
	 * @return List<String> of dropdown types
	 */
	private List<String> getReportFieldListTypeString() {

		ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) applicationContext
				.getBean("reportFieldListDAO");

		List<ReportFieldList> reportFieldLists = reportFieldListDAO.findAll();

		// list of all fieldType, the relation between
		// reportField.reportFieldMask and reportFieldList
		List<String> filedTypeList = new ArrayList<String>();

		// create List of all dropdowns
		for (ReportFieldList reportFieldList : reportFieldLists) {
			if (!filedTypeList.contains(reportFieldList
					.getReportFieldListType())) {
				filedTypeList.add(reportFieldList.getReportFieldListType());
				// System.out.println("list: "
				// + reportFieldList.getReportFieldListType());
			}

		}

		return filedTypeList;
	}
}
