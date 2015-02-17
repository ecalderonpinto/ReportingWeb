package com.reportingtool.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;



public class Status {
	
	private ApplicationContext aplicationContext;

	public Status (ApplicationContext aplicationContext) {
		this.aplicationContext = aplicationContext;
	}
	

	// main function to check Status of a report
	public void checkStatus(ReportExecution reportExecution) {

		ReportCatalog reportCatalog = reportExecution.getReportCatalog();

		if (reportCatalog.getReportLevel().equals("AIF")) {

			// check AIF report with aifmdDatas
			checkAIFStatus(reportExecution);

		}

		if (reportCatalog.getReportLevel().equals("AIFMD")) {

			// check AIFMD report with aifmdDatas
			checkAIFMDStatus(reportExecution);

		}

	}

	public ReportExecution checkAIFStatus(ReportExecution reportExecution) {

		return reportExecution;
	}

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

			String oblg = fieldMandatory(reportField, reportExecution);
			if (oblg.contains("M")) {
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
					finalStatus = false;
					// set status to in creation, it has al least one field
					// not populated
					reportExecution.setReportStatus("CREATION");
				}
			}

		}
		// all fields has to be correct to change status to prepared
		if (finalStatus) {
			reportExecution.setReportStatus("PREPARED");
		}

		return reportExecution;
	}
	
	// examine full report and return if this filed has to be Mandatory Opcional or Forbbiden (M/C/F)
	public static String fieldMandatory(ReportField reportField, ReportExecution reportExecution) {
		
		return "M";
	}
}
