package com.reportingtool.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;

import com.entities.dao.common.ErrorDAO;
import com.entities.dao.reportingtool.ReportDataErrorDAO;
import com.entities.dao.reportingtool.ReportFieldListDAO;
import com.entities.entity.common.Error;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportDataError;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;
import com.entities.entity.reportingtool.ReportFieldList;
import com.entities.utilities.hibernate.VersionAuditor;

public class Syntactic {

	private ApplicationContext aplicationContext;

	public Syntactic(ApplicationContext aplicationContext) {
		this.aplicationContext = aplicationContext;
	}

	public void validInValueList(ReportData reportData) {
		// Check if exists in value list

		ReportField reportField = reportData.getReportField();

		ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) aplicationContext
				.getBean("reportFieldListDAO");

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

				createReportDataError(reportData, "REGEX",
						"BAD REGEX FOUND: " + reportData.getReportDataText()
								+ " <> " + reportData.getReportDataText());
			}
		} else {
			// not valid, is empty
			createReportDataError(reportData, "EMPTY", "EMPTY FIELD");
		}

	}

	public void createReportDataError(ReportData reportData,
			String type, String text) {
		
		ErrorDAO errorDAO = (ErrorDAO) aplicationContext
				.getBean("errorDAO");
		Error errorExample = new Error();
		errorExample.setErrorType("SYNTAXIS");
		List<Error> errors = new ArrayList<Error>(errorDAO.findByExample(errorExample));
		Error error = new Error();
		error = errors.get(0);
		
		ReportDataError reportDataError = new ReportDataError(reportData, error, type, text, new VersionAuditor("error"));
		

		System.out.println("DEBUG_" + "Syntactic: error final: "
				+ reportDataError.getReportDataErrorText()
				+ reportDataError.getReportDataErrorType()
				+ reportDataError.getReportData());

		ReportDataErrorDAO reportDataErrorDAO = (ReportDataErrorDAO) aplicationContext
				.getBean("reportDataErrorDAO");

		reportDataErrorDAO.create(reportDataError);

	}

	public void validReportExecution(ReportExecution reportExecution) {
		// validation of full report
	}
}
