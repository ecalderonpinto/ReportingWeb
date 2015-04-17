package com.reportingtool.utilities;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportDataDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.dao.reportingtool.ReportFieldDAO;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;
import com.entities.utilities.hibernate.VersionAuditor;

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
	public ReportExecution reportExecutionNotDisabled(
			ReportExecution reportExecution) {

		// TODO:RT make a function to delete from object all reportData,
		// reportError and reportDataError which are disabled (isDelete=1)

		for (ReportData reportData : reportExecution.getReportDatas()) {
			if (reportData.getAuditor().isDeleted()) {
				// ?
			}
		}

		return reportExecution;
	}

	/**
	 * Generate default reportData of this reportFields: CreationDateAndTime,
	 * Version and FillingType if does not exists
	 * 
	 * @param applicationContext
	 * @param reportExecution
	 * @param versionNum
	 */
	public static void generateDefaultReportDatas(
			ApplicationContext applicationContext,
			ReportExecution reportExecution, String versionNum) {

		// all dataFields
		List<ReportData> reportDatas = new ArrayList<ReportData>(
				reportExecution.getReportDatas());

		// <CreationDateAndTime>
		if (searchData(reportDatas, "CreationDateAndTime", "3", null) == null) {

			ReportField reportField = new ReportField();
			reportField.setReportCatalog(reportExecution.getReportCatalog());
			reportField.setReportFieldName("CreationDateAndTime");
			reportField.setReportFieldNum(new BigInteger("3"));

			ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
					.getBean("reportFieldDAO");
			reportField = reportFieldDAO.findByExample(reportField).get(0);

			DateFormat dateFormat = new SimpleDateFormat(
					ReportUtilities.dateTimePattern);
			// Get the date today using Calendar object.
			Date today = Calendar.getInstance().getTime();
			// Using DateFormat format method we can create a string
			String creationDateTime = dateFormat.format(today);
			System.out.println("DEBUG_" + "GeneratorXML: new creationDateTime "
					+ creationDateTime);

			ReportData reportData = new ReportData(null, reportField,
					reportExecution, null, null, creationDateTime, null, null,
					null, new VersionAuditor("utilities"));
			reportExecution.getReportDatas().add(reportData);

			// save new reportData
			ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
					.getBean("reportDataDAO");
			reportDataDAO.create(reportData);
		}

		// <Version>
		if (searchData(reportDatas, "Version", "2", null) == null) {

			ReportField reportField = new ReportField();
			reportField.setReportCatalog(reportExecution.getReportCatalog());
			reportField.setReportFieldName("Version");
			reportField.setReportFieldNum(new BigInteger("2"));

			ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
					.getBean("reportFieldDAO");
			reportField = reportFieldDAO.findByExample(reportField).get(0);

			ReportData reportData = new ReportData(null, reportField,
					reportExecution, null, null, versionNum, null, null, null,
					new VersionAuditor("utilities"));
			reportExecution.getReportDatas().add(reportData);

			// save new reportData
			ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
					.getBean("reportDataDAO");
			reportDataDAO.create(reportData);
		}

		// <FillingType>
		if (searchData(reportDatas, "FilingType", "4", null) == null) {

			ReportField reportField = new ReportField();
			reportField.setReportCatalog(reportExecution.getReportCatalog());
			reportField.setReportFieldName("FilingType");
			reportField.setReportFieldNum(new BigInteger("4"));

			ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
					.getBean("reportFieldDAO");
			reportField = reportFieldDAO.findByExample(reportField).get(0);

			String fillingType = "INIT";
			// if the reportExecution is new in this year/period -> INIT
			// if already exists other reportExecution -> AMND

			String year = reportExecution.getReportPeriodYear();
			String period = reportExecution.getReportPeriodType();

			ReportExecution reportExecutionExample = new ReportExecution();
			reportExecutionExample.setReportPeriodType(period);
			reportExecutionExample.setReportPeriodYear(year);
			reportExecutionExample.setReportCatalog(reportExecution
					.getReportCatalog());
			reportExecutionExample.setCompany(reportExecution.getCompany());

			ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
					.getBean("reportExecutionDAO");

			List<ReportExecution> reportExecutionList = reportExecutionDAO
					.findByExample(reportExecutionExample);
			
			if (reportExecutionList.size() > 1) {
				fillingType = "AMND";
			}

			System.out.println("creating fillingType " + fillingType);

			ReportData reportData = new ReportData(null, reportField,
					reportExecution, null, null, fillingType, null, null, null,
					new VersionAuditor("utilities"));
			reportExecution.getReportDatas().add(reportData);

			// save new reportData
			ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
					.getBean("reportDataDAO");
			reportDataDAO.create(reportData);
		}

	}

	/**
	 * Function search a reportDataText with a reportFieldName and reporFieldNum
	 * 
	 * @param reportDatas
	 * @param reportFieldName
	 * @param reportFieldNum
	 * @param reportDataBlock
	 * @return reportData.getReportDataText()
	 */
	public static String searchData(List<ReportData> reportDatas,
			String reportFieldName, String reportFieldNum,
			String reportDataBlock) {
		String result = null;

		if (reportDataBlock != null) {
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals(reportFieldName)
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger(reportFieldNum))
						&& reportData.getReportDataBlock().equals(
								reportDataBlock)) {
					result = reportData.getReportDataText();
					break;
				}
			}
		} else {
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals(reportFieldName)
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger(reportFieldNum))) {
					result = reportData.getReportDataText();
					break;
				}
			}
		}

		// System.out.println("searchData(" + reportFieldName + " "
		// + reportFieldNum + " " + reportDataBlock + ") -> " + result);

		return result;
	}

	/**
	 * Return true if String is a number, false otherwise
	 * 
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
