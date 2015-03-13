package com.reportingtool.utilities;

import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;

/**
 * Class that contains utilities related with Reports
 * 
 * @author alberto.olivan
 *
 */
public class ReportUtilities {

	/**
	 * Function to clean objects from reportExecution which are disabled
	 * 
	 * @param reportExecution
	 * @return reportExecution cleaned of disable objects
	 */
	public ReportExecution reportExecutionNotDisabled (
			ReportExecution reportExecution) {

		// TODO:RT make a function to delete from object all reportData,
		// reportError and reportDataError which are disabled (isDelete=1)
		
		for(ReportData reportData : reportExecution.getReportDatas()) {
			if (reportData.getAuditor().isDeleted()) {
			}
		}

		return reportExecution;
	}
}
