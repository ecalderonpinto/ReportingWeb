package com.reportingtool.validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportFieldListDAO;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;
import com.entities.entity.reportingtool.ReportFieldList;
import com.reportingtool.utilities.ReportingErrorManager;

/**
 * Class to check syntatic rules of reportExecution
 * 
 * @author alberto.olivan
 *
 */
public class Syntactic {

	private ApplicationContext applicationContext;

	/**
	 * Constructor of Syntactic with an applicationContext
	 * 
	 * @param applicationContext
	 */
	public Syntactic(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Main function to check syntactic rules of a reportExecution, create
	 * reportDataError
	 * 
	 * @param reportExecution
	 */
	public void validReportExecution(ReportExecution reportExecution) {

		System.out.println("DEBUG_" + "Syntactic: starting process "
				+ reportExecution.getReportExecutionName());

		for (ReportData reportData : reportExecution.getReportDatas()) {
			this.validInValueList(reportData);
			this.validRegex(reportData);
		}
	}

	/**
	 * Function to check if reportData.getReportDataText exists in
	 * reportFieldList of his reportField
	 * 
	 * @param reportData
	 */
	private void validInValueList(ReportData reportData) {

		ReportField reportField = reportData.getReportField();

		ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) applicationContext
				.getBean("reportFieldListDAO");

		ReportFieldList reportFieldListExample = new ReportFieldList();
		reportFieldListExample.setReportFieldListType(reportField
				.getReportFieldMask());

		List<ReportFieldList> reportFieldLists = reportFieldListDAO
				.findByExample(reportFieldListExample);

		Boolean hasFieldValue = true;
		for (ReportFieldList reportFieldList : reportFieldLists) {
			hasFieldValue = false;
			// System.out.println("DEBUG_" + "Syntactic: value "
			// + reportFieldList.getReportFieldListValue() + " -> "
			// + reportData.getReportDataText());
			if (reportFieldList.getReportFieldListValue().equals(
					reportData.getReportDataText())) {
				hasFieldValue = true;
				break;
			}

		}
		// System.out.println("DEBUG_" + "Syntactic: hasFieldValue "
		// + hasFieldValue);
		if (hasFieldValue) {
			// ok

//			System.out.println("DEBUG_"
//					+ "Syntactic: hasFieldValue ok, check if error exists "
//					+ reportData.getReportDataText() + " -> "
//					+ reportField.getReportFieldFormat());

			// find and disable if there are a REGEX error
			ReportingErrorManager.disableReportDataError(applicationContext,
					"SYNTAXIS", reportData, "VALUE");

		} else {
			// not valid

			// create a message of error
			ReportingErrorManager.createReportDataError(applicationContext,
					"SYNTAXIS", reportData, "VALUE", "NOT IN VALUE LIST: "
							+ reportData.getReportDataText());
		}
	}

	/**
	 * Function to check regular expression of reportField.getReportFieldFormat
	 * is correct in reportData
	 * 
	 * @param reportData
	 */
	private void validRegex(ReportData reportData) {

		ReportField reportField = reportData.getReportField();

		if (reportData.getReportDataText() != null
				&& !reportData.getReportDataText().isEmpty()) {
			// ok, is not empty

			// Check Regex patters
			// http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
			// http://www.ocpsoft.org/tutorials/regular-expressions/java-visual-regex-tester/
			
//			System.out.println("DEBUG_" + "Syntactic regex: "
//					+ reportData.getReportDataText() + " -> "
//					+ reportField.getReportFieldFormat());
			
			Pattern pattern = Pattern.compile(reportField
					.getReportFieldFormat());
			Matcher matcher = pattern.matcher(reportData.getReportDataText());
			boolean matchVersion = matcher.matches();
			if (matchVersion) {
				// ok Regex

//				System.out.println("DEBUG_"
//						+ "Syntactic: regex ok, check if error exists "
//						+ reportData.getReportDataText() + " -> "
//						+ reportField.getReportFieldFormat());

				// find and disable if there are a REGEX error
				ReportingErrorManager.disableReportDataError(
						applicationContext, "SYNTAXIS", reportData, "REGEX");

			} else {
				// not valid, bad Regex

				ReportingErrorManager.createReportDataError(applicationContext,
						"SYNTAXIS", reportData, "REGEX", "BAD REGEX FOUND: "
								+ reportData.getReportDataText()
								+ " <> "
								+ reportData.getReportField()
										.getReportFieldFormat());
			}
		} else {
			// not valid, is empty
			ReportingErrorManager.createReportDataError(applicationContext,
					"SYNTAXIS", reportData, "EMPTY", "EMPTY FIELD");
		}

	}
}
