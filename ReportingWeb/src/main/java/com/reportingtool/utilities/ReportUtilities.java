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

	// Define what to set in FileColum.columBlock if is repeatable
	public static final String fileColumBlockRepeatable = "n";
	
	public static final String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
	public static final String datePattern = "yyyy-MM-dd";
	public static final String yearPattern = "yyyy";
	
	
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
	
	
	/**
	 * Return true if String is a number, false otherwise
	 * @param string
	 * @return boolean
	 */
	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c <= '/' || c >= ':') {
				return false;
			}
		}
		return true;
	}
}
