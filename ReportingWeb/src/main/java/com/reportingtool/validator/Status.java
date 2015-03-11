package com.reportingtool.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;
import com.reportingtool.utilities.ReportingErrorManager;

/**
 * Class to check reportExecution.reportStatus if is ready to be generated
 * 
 * @author alberto.olivan
 *
 */
public class Status {

	private ApplicationContext applicationContext;

	/**
	 * Constructor if Status with an applicationContext
	 * 
	 * @param applicationContext
	 */
	public Status(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Main function to check Status of a reportExecution
	 * 
	 * @param reportExecution
	 */
	public void checkStatus(ReportExecution reportExecution) {

		if (reportExecution.getReportCatalog().getReportLevel()
				.contains("FUND")) {
			// check AIF report
			checkAIFStatus(reportExecution);
		}

		if (reportExecution.getReportCatalog().getReportLevel()
				.contains("COMPANY")) {
			// check AIFMD report
			checkAIFMDStatus(reportExecution);
		}
	}

	/**
	 * Function to check status of AIF report
	 * 
	 * @param reportExecution
	 * @return
	 */
	public ReportExecution checkAIFStatus(ReportExecution reportExecution) {

		return reportExecution;
	}

	/**
	 * Function to check status of AIFM report
	 * 
	 * @param reportExecution
	 * @return
	 */
	public ReportExecution checkAIFMDStatus(ReportExecution reportExecution) {

		// reportCatalog
		ReportCatalog reportCatalog = reportExecution.getReportCatalog();

		// reportFields
		List<ReportField> reportFields = new ArrayList<ReportField>(
				reportCatalog.getReportFields());

		// dataFields
		List<ReportData> reportDatas = new ArrayList<ReportData>(
				reportExecution.getReportDatas());

		boolean finalStatus = true;
		// for each field, semantic has to be checked (mandatory)
		for (ReportField reportField : reportFields) {

			boolean oblg = fieldMandatory(reportField, reportExecution);
			if (oblg) {
				// fieldValid says field is mandatory, it has to appear in
				// dataFields
				boolean hasData = false;
				// search in dataField if this mandatory field exists
				for (ReportData reportData : reportDatas) {
					if (reportData.getReportField() == reportField) {
						hasData = true;
					}
				}
				if (hasData) {
					// ok: field mandatory is in dataField
					System.out.println("DEBUG_" + "Status ok field "
							+ reportField.getReportFieldName());
				} else {
					// not ok
					System.out.println("DEBUG_" + "Status not ok"
							+ reportField.getReportFieldName());

					// create a message of error
					ReportingErrorManager.createReportError(
							applicationContext,
							"STATUS",
							reportExecution,
							"MANDATORY",
							"VALUE MANDATORY NOT PRESENT "
									+ reportField.getReportFieldName());

					finalStatus = false;
					// set status to in creation, it has al least one field
				}
			}

		}
		// all fields has to be correct to change status to prepared
		if (finalStatus) {
			reportExecution.setReportStatus("PREPARED");
		} else {
			reportExecution.setReportStatus("CREATION");
		}
		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");
		reportExecutionDAO.edit(reportExecution);

		return reportExecution;
	}

	/**
	 * Function to check if reportField is mandatory or not in this
	 * reportExecution
	 * 
	 * @param reportField
	 * @param reportExecution
	 * @return true if reportField is mandatory in this reportExecution
	 */
	public static boolean fieldMandatory(ReportField reportField,
			ReportExecution reportExecution) {
		boolean result = false;
		if (reportField.getReportFieldRepe().startsWith("1")) {
			result = true;
		}
		return result;
	}
}
