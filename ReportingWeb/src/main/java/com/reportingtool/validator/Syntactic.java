package com.reportingtool.validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.entity.reportingtool.ReportData;
import com.entity.reportingtool.ReportDataError;
import com.entity.reportingtool.ReportExecution;
import com.entity.reportingtool.ReportField;
import com.entity.reportingtool.ReportFieldList;
import com.dao.reportingtool.ReportFieldListDAO;

public class Syntactic {

	public void validInValueList(ReportData reportData) {
		// Check if exists in value list

		ReportField reportField = reportData.getReportField();

		ReportFieldListDAO reportFieldListDAO = new ReportFieldListDAO();
		

		ReportFieldList reportFieldListExample = new ReportFieldList();
		reportFieldListExample.setReportFieldListType(reportField
				.getReportFieldMask());

		List<ReportFieldList> reportFieldLists = reportFieldListDAO
				.findByExample(reportFieldListExample);


		Boolean hasFieldValue = true;
		for (ReportFieldList reportFieldList : reportFieldLists) {
			hasFieldValue = false;
			System.out.println("DEBUG_" + "Syntactic: value "
					+ reportFieldList.getReportFieldListValue() + " -> "
					+ reportData.getReportDataText());
			if (reportFieldList.getReportFieldListValue().equals(
					reportData.getReportDataText())) {
				hasFieldValue = true;
				break;
			}

		}
		System.out.println("DEBUG_" + "Syntactic: hasFieldValue "
				+ hasFieldValue);
		if (hasFieldValue) {
			// ok
		} else {
			// not valid

			// create a message of error
			createReportDataError(reportData, "VALUE", "NOT IN VALUE LIST: "
					+ reportData.getReportDataText());
		}
	}

	public void validRegex(ReportData reportData) {

		ReportField reportField = reportData.getReportField();

		// Check format, type, regex...

		System.out.println("DEBUG_" + "Syntactic: valid reg: "
				+ reportField.getReportFieldFormat());

		if (reportData.getReportDataText() != null
				&& !reportData.getReportDataText().isEmpty()) {
			// ok, is not empty

			// Check Regex patters
			// http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
			// http://www.ocpsoft.org/tutorials/regular-expressions/java-visual-regex-tester/
			System.out.println("DEBUG_" + "Syntactic regex: "
					+ reportData.getReportDataText() + " -> "
					+ reportField.getReportFieldFormat());
			Pattern pattern = Pattern.compile(reportField
					.getReportFieldFormat());
			Matcher matcher = pattern.matcher(reportData.getReportDataText());
			boolean matchVersion = matcher.matches();
			if (matchVersion) {
				// ok Regex
			} else {
				// not valid, bad Regex

				createReportDataError(
						reportData,
						"REGEX",
						"BAD REGEX FOUND: "
								+ reportData.getReportDataText()
								+ " <> " + reportData.getReportDataText());
			}
		} else {
			// not valid, is empty
			createReportDataError(reportData, "EMPTY", "EMPTY FIELD");
		}

	}

	public static void createReportDataError(ReportData reportData,
			String type, String text) {
		ReportDataError reportDataError = new ReportDataError();

		// reportDataError.setStatus();
		reportDataError.setReportDataErrorText(text);
		reportDataError.setReportDataErrorType(type);
		reportDataError.setReportData(reportData);

		System.out.println("DEBUG_" + "Syntactic: error final: "
				+ reportDataError.getReportDataErrorText()
				+ reportDataError.getReportDataErrorType()
				+ reportDataError.getReportData());

		// AifmdDataValidDAO aifmdDataValidDAO = new AifmdDataValidDAO();
		// Session session = aifmdDataValidDAO.getSession();
		// session.beginTransaction();

		// aifmdDataValidDAO.merge(reportDataError);

		// session.close();
	}

	public void validAifmdReportResult(ReportExecution reportExecution) {
		// validation of full report
	}
}
