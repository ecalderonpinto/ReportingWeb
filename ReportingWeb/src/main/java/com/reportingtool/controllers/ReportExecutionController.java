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

import com.entities.dao.reportingtool.ReportDataDAO;
import com.entities.dao.reportingtool.ReportDataErrorDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.dao.reportingtool.ReportFieldListDAO;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportDataError;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;
import com.entities.entity.reportingtool.ReportFieldList;
import com.entities.utilities.hibernate.VersionAuditor;
import com.reportingtool.controllers.forms.ReportSectionForm;
import com.reportingtool.utilities.ReportUtilities;
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

	/**
	 * Controller to show reportExecution
	 * 
	 * @param id
	 * @param model
	 * @return reportexecution -> tiles.xml
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String ReportControllerPre(@RequestParam("id") String id, Model model) {

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");

		// load reportExecution from id to have all this child entities
		ReportExecution reportExecution = reportExecutionDAO.findById(Long
				.parseLong(id));

		// set reportExecution.hasErrors and reportData.hasErrors true/false to
		// show only active
		ReportingErrorManager.checkReportExecutionHasErrors(reportExecution);

		// showReportDatas(reportExecution);

		// list of fields with dropdowns
		List<String> fieldList = getReportFieldListTypeString();
		model.addAttribute("fieldlist", fieldList);

		// Map of data to populate dropdowns
		Map<String, String> fieldListMap = getReportFieldListDropdown();
		model.addAttribute("fieldlistmap", fieldListMap);

		// add all reportDatas empty when are not populated yet
		reportExecution = addReportDatas(reportExecution);

		// showReportDatas(reportExecution);

		// get all visual sections of the report
		List<String> sections = getSections(reportExecution);

		// showReportDatas(reportExecution);

		// order reportDatas to display correctly
		reportExecution = reportExecutionOrder(reportExecution);

		// showReportDatas(reportExecution);

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

	/**
	 * Controller to save reportDatas from reportExecution UI
	 * 
	 * @param reportExecution
	 * @param result
	 * @param model
	 * @param status
	 * @param session
	 * @return reportexecution -> tiles.xml
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("reportexecution") ReportExecution reportExecution,
			BindingResult result, Model model, SessionStatus status,
			HttpSession session) {

		System.out.println("Submit - ReportExecution;");

		// showReportDatas(reportExecution);

		// delete reportDatas with id and null in database
		reportExecution = deleteReportDatas(reportExecution);

		// clean all empty reportData to avoid been saved
		reportExecution = cleanReportDatas(reportExecution);

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");

		// save changes of reportExecution
		reportExecutionDAO.edit(reportExecution);

		// showReportDatas(reportExecution);

		// load reportExecution from id to have all this child entities
		reportExecution = reportExecutionDAO.findById(reportExecution.getId());

		// Syntactic analysis
		Syntactic syntactic = new Syntactic(applicationContext);
		syntactic.validReportExecution(reportExecution);

		// Semantic analysis
		Semantic semantic = new Semantic(applicationContext);
		semantic.checkSemantic(reportExecution);

		// save changes
		reportExecutionDAO.edit(reportExecution);

		// showReportDatas(reportExecution);

		reportExecution = reportExecutionDAO.findById(reportExecution.getId());

		// set reportExecution.hasErrors and reportData.hasErrors true/false to
		// show only active
		ReportingErrorManager.checkReportExecutionHasErrors(reportExecution);

		// list of fields with dropdowns
		List<String> fieldList = getReportFieldListTypeString();
		model.addAttribute("fieldlist", fieldList);

		// Map of data to populate dropdowns
		Map<String, String> fieldListMap = getReportFieldListDropdown();
		model.addAttribute("fieldlistmap", fieldListMap);

		// add all reportDatas empty when are not populated yet
		reportExecution = addReportDatas(reportExecution);

		// get all visual sections of the report
		List<String> sections = getSections(reportExecution);

		// order reportDatas to display correctly
		reportExecution = reportExecutionOrder(reportExecution);

		// showReportDatas(reportExecution);

		model.addAttribute("reportexecution", reportExecution);
		model.addAttribute("sections", sections);

		return "reportexecution";
	}

	/**
	 * Show reportData content in console
	 * 
	 * @param reportExecution
	 */
	public void showReportDatas(ReportExecution reportExecution) {
		int count = 0;
		System.out.println(" *** showReportDatas *** ");
		for (ReportData reportData : reportExecution.getReportDatas()) {
			System.out.println(count + ": "
					+ reportData.getReportField().getReportFieldName() + "("
					+ reportData.getReportField().getReportFieldNum()
					+ ") -> '" + reportData.getReportDataText() + "' ["
					+ reportData.getReportDataBlock() + "]");
			count++;
		}
	}

	/**
	 * Function delete empty reportDatas with id
	 * 
	 * @param reportExecution
	 * @return reportExecution
	 */
	private ReportExecution deleteReportDatas(ReportExecution reportExecution) {

		ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
				.getBean("reportDataDAO");

		ReportDataErrorDAO reportDataErrorDAO = (ReportDataErrorDAO) applicationContext
				.getBean("reportDataErrorDAO");

		for (ReportData reportData : reportExecution.getReportDatas()) {
			if ((reportData.getReportDataText().equals(ReportUtilities.emptySelect)
					|| reportData.getReportDataText() == null || reportData
					.getReportDataText().isEmpty()) && reportData.getId() > 0) {
				// System.out.println("deleting "
				// + reportData.getReportField().getReportFieldName());

				List<ReportDataError> reportDataErrorList = reportData
						.getReportDataErrors();

				for (ReportDataError reportDataError : reportDataErrorList) {
					reportDataErrorDAO.delete(reportDataError);
				}

				reportDataDAO.delete(reportData);

			}
		}

		return reportExecution;
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
			// if (reportData.getReportDataErrors().size() > 0) {
			// System.out.println("Data tiene errores...");
			// for (ReportDataError error : reportData.getReportDataErrors())
			// System.out.println("Error: "
			// + error.getReportDataErrorText());
			// }
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
					// System.out.println("split: " + Arrays.toString(order));

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

		// System.out.println("orderList " + orderList.toString());

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
		boolean repeAdded = false;
		for (ReportField reportField : reportFields) {
			// is field without content
			if (reportField.getReportFieldSection() == null) {
				continue;
			}
			// System.out.println("reportField -> "
			// + reportField.getReportFieldName());
			for (ReportData reportData : reportExecution.getReportDatas()) {
				// System.out.println("reportData "
				// + reportData.getReportDataText()
				// + " -> "
				// + reportData.getReportField().getReportCatalog()
				// .getReportCatalogName() + " <> "
				// + reportField.getReportCatalog().getReportCatalogName()
				// + " "
				// + reportData.getReportField().getReportFieldName()
				// + " <> " + reportField.getReportFieldName() + " "
				// + reportData.getReportField().getReportFieldNum()
				// + " <> " + reportField.getReportFieldNum() + " "
				// + reportData.getReportField().getReportFieldType()
				// + " <> " + reportField.getReportFieldType()
				// );
				if (reportData.getReportField().equals(reportField)) {
					// reportData already exists
					// System.out.println("yes reportField, adding reportData of "
					// + reportField.getReportFieldName() + "-"
					// + reportField.getReportFieldNum().toString()
					// + " content: " + reportData.getReportDataText());
					reportDatas.add(reportData);
					flagField = true;
				}
			}
			if (flagField == false) {
				// add reportData

				ReportData reportDataTemp = new ReportData(null, reportField,
						reportExecution, null, null, "", null, null,
						new VersionAuditor("generated"));

				// if is a field repeated and doesn't exist, we set block = "1"
				if (ReportUtilities.reportFieldIsRepe(reportField)) {
					reportDataTemp.setReportDataBlock("1");
					repeAdded = true;
				}

				// System.out.println("no reportField, adding reportData of "
				// + reportField.getReportFieldName() + "-"
				// + reportField.getReportFieldNum().toString());
				reportDatas.add(reportDataTemp);

			}

			// if is a field repeated and exists, we find last number block
			// we add next number block until is full (5,10) or one more (n)
			if (ReportUtilities.reportFieldIsRepe(reportField)) {
				int count = ReportUtilities.reportFieldNumberRepe(reportField);

				// System.out.println("exists field and is repeated "
				// + reportField.getReportFieldName() + " "
				// + reportField.getReportFieldRepe());

				List<String> blockList = ReportUtilities.searchBlockList(
						reportExecution.getReportDatas(), reportField
								.getReportFieldName(), reportField
								.getReportFieldNum().toString());

				// String maxBlock = ReportUtilities
				// .maxBlockFromList(ReportUtilities.searchBlockList(
				// reportExecution.getReportDatas(), reportField
				// .getReportFieldName(), reportField
				// .getReportFieldNum().toString()));

				String block = "1";

				for (int i = 1; i <= count; i++) {
					block = Integer.toString(i);
					// if this field + block doesn't exist, we add it
					if (ReportUtilities.searchData(reportDatas, reportField
							.getReportFieldName(), reportField
							.getReportFieldNum().toString(), block) == null) {

						ReportData reportDataTemp = new ReportData(null,
								reportField, reportExecution, null, null, "",
								null, null, new VersionAuditor("generated"));

						reportDataTemp.setReportDataBlock(block);

						// System.out
						// .println("yes reportField and repeated, adding reportData of "
						// + reportField.getReportFieldName()
						// + "-"
						// + reportField.getReportFieldNum()
						// .toString()
						// + " with Block "
						// + block);

						// n repe already added, we skip
						if (count == 99 && repeAdded) {
							break;
						}

						reportDatas.add(reportDataTemp);

						// n repes only one added
						if (count == 99) {
							break;
						}

					}
				}
			}

			flagField = false;
			repeAdded = false;
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
					&& !reportData.getReportDataText().isEmpty()
					&& !reportData.getReportDataText().equals(ReportUtilities.emptySelect)) {
				reportDatas.add(reportData);
				// System.out.println("adding reportData "
				// + reportData.getReportDataText() + " from "
				// + reportData.getReportField().getReportFieldName());
			}
		}

		// System.out.println("clean data, size ending: " + reportDatas.size());

		reportExecution.setReportDatas(reportDatas);

		return reportExecution;
	}

	/**
	 * Function create Map<reportFieldType, reportFieldValues> to make dropdowns
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
			// this value represent no populate dropdown, it is necessary to
			// clean before save
			// fieldListValues = fieldListValues + ",<empty>";
			// System.out.println("dropdown: " + fieldType + "-> "+
			// fieldListValues);

			result.put(fieldType, fieldListValues);
		}

		// TODO dropdown of country code are unsorted

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

	/**
	 * Function generate a List of ReportSectionForm which can be used to make
	 * sections in UI
	 * 
	 * @param reportExecution
	 * @return List<ReportSectionForm>
	 */
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

}
