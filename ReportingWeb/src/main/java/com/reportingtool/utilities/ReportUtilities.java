package com.reportingtool.utilities;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportDataDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.dao.reportingtool.ReportFieldDAO;
import com.entities.entity.reportingtool.ReportCatalog;
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

	public static final String dateXMLGregorianPattern = "EEE MMM dd zzz HH:mm:ss yyyy";

	public static final String reportVersion = "1.2";

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
			if (reportData.getVersionAuditor().isDeleted()) {
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
			ReportExecution reportExecution, String versionNum,
			String reportingPeriodYear) {

		// all dataFields
		List<ReportData> reportDatas = new ArrayList<ReportData>(
				reportExecution.getReportDatas());

		System.out.println("Creating default fields of catalog "
				+ reportExecution.getReportCatalog().getReportCatalogName());

		ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
				.getBean("reportFieldDAO");

		// (2) <Version>
		if (searchData(reportDatas, "Version", "2", null) == null) {

			ReportField reportField = new ReportField();

			reportField = reportFieldDAO.findAllByProperty(
					findReportFieldDAO("Version", "2",
							reportExecution.getReportCatalog())).get(0);

			// reportField.setReportCatalog(reportExecution.getReportCatalog());
			// reportField.setReportFieldName("Version");
			// reportField.setReportFieldNum(new BigInteger("2"));
			// reportField.setReportFieldEditable(true);
			// reportField = reportFieldDAO.findByExample(reportField).get(0);

			ReportData reportData = new ReportData(null, reportField,
					reportExecution, null, null, versionNum, null, null,
					new VersionAuditor("utilities"));

			// save new reportData
			ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
					.getBean("reportDataDAO");
			reportDataDAO.create(reportData);

			reportExecution.getReportDatas().add(reportData);

			System.out.println("creating <Version>");
		}

		// (3) <CreationDateAndTime>
		if (searchData(reportDatas, "CreationDateAndTime", "3", null) == null) {

			ReportField reportField = new ReportField();

			reportField = reportFieldDAO.findAllByProperty(
					findReportFieldDAO("CreationDateAndTime", "3",
							reportExecution.getReportCatalog())).get(0);

			// reportField.setReportCatalog(reportExecution.getReportCatalog());
			// reportField.setReportFieldName("CreationDateAndTime");
			// reportField.setReportFieldNum(new BigInteger("3"));
			// reportField.setReportFieldEditable(true);
			// reportField = reportFieldDAO.findByExample(reportField).get(0);

			DateFormat dateFormat = new SimpleDateFormat(
					ReportUtilities.dateTimePattern);
			// Get the date today using Calendar object.
			Date today = Calendar.getInstance().getTime();
			// Using DateFormat format method we can create a string
			String creationDateTime = dateFormat.format(today);
			System.out.println("DEBUG_"
					+ "ReportUtilities: new creationDateTime "
					+ creationDateTime);

			ReportData reportData = new ReportData(null, reportField,
					reportExecution, null, null, creationDateTime, null, null,
					new VersionAuditor("utilities"));

			// save new reportData
			ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
					.getBean("reportDataDAO");
			reportDataDAO.create(reportData);

			reportExecution.getReportDatas().add(reportData);

			System.out.println("creating <CreationDateAndTime>");
		}

		// (4) <FillingType>
		if (searchData(reportDatas, "FilingType", "4", null) == null) {

			ReportField reportField = new ReportField();

			reportField = reportFieldDAO.findAllByProperty(
					findReportFieldDAO("FilingType", "4",
							reportExecution.getReportCatalog())).get(0);

			// reportField.setReportCatalog(reportExecution.getReportCatalog());
			// reportField.setReportFieldName("FilingType");
			// reportField.setReportFieldNum(new BigInteger("4"));
			// reportField = reportFieldDAO.findByExample(reportField).get(0);

			String fillingType = "INIT";
			// if the reportExecution is new in this year/period -> INIT
			// if already exists other reportExecution -> AMND

			String year = reportExecution.getReportPeriodYear();
			String period = reportExecution.getReportPeriodType();

			ReportExecution reportExecutionExample = new ReportExecution();
			reportExecutionExample.setReportExecutionName(reportExecution
					.getReportExecutionName());
			reportExecutionExample.setReportPeriodType(period);
			reportExecutionExample.setReportPeriodYear(year);
			reportExecutionExample.setReportCatalog(reportExecution
					.getReportCatalog());
			reportExecutionExample.setCompany(reportExecution.getCompany());
			// TODO puede fallar ya que no considera catalog ni company

			ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
					.getBean("reportExecutionDAO");

			List<ReportExecution> reportExecutionList = reportExecutionDAO
					.findByExample(reportExecutionExample);

			if (reportExecutionList.size() > 1) {
				fillingType = "AMND";
			}

			System.out.println("creating <FillingType> " + fillingType);

			ReportData reportData = new ReportData(null, reportField,
					reportExecution, null, null, fillingType, null, null,
					new VersionAuditor("utilities"));

			// save new reportData
			ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
					.getBean("reportDataDAO");
			reportDataDAO.create(reportData);

			reportExecution.getReportDatas().add(reportData);
		}

		// (9) <ReportingPeriodYear>
		if (searchData(reportDatas, "ReportingPeriodYear", "9", null) == null) {

			ReportField reportField = new ReportField();

			reportField = reportFieldDAO.findAllByProperty(
					findReportFieldDAO("ReportingPeriodYear", "9",
							reportExecution.getReportCatalog())).get(0);

			// reportField.setReportCatalog(reportExecution.getReportCatalog());
			// reportField.setReportFieldName("ReportingPeriodYear");
			// reportField.setReportFieldNum(new BigInteger("9"));
			// reportField = reportFieldDAO.findByExample(reportField).get(0);

			ReportData reportData = new ReportData(null, reportField,
					reportExecution, null, null, reportingPeriodYear, null,
					null, new VersionAuditor("utilities"));

			// save new reportData
			ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
					.getBean("reportDataDAO");
			reportDataDAO.create(reportData);

			reportExecution.getReportDatas().add(reportData);

			System.out.println("creating <ReportingPeriodYear>");
		}

	}

	/**
	 * Function used to find a reportField like findByExmaple using
	 * reportCatalog, because hibernate does not consider it.
	 * 
	 * @param reportFieldName
	 * @param reportFieldNum
	 * @param reportCatalog
	 * @return List to used in reportExecutionDAO.findByProperty(list)
	 */
	public static List<Map<String, Object>> findReportFieldDAO(
			String reportFieldName, String reportFieldNum,
			ReportCatalog reportCatalog) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		HashMap<String, Object> mMap = new HashMap<String, Object>();
		mMap.put("propertyName", "reportFieldName");
		mMap.put("value", reportFieldName);
		list.add(mMap);
		HashMap<String, Object> mMap2 = new HashMap<String, Object>();
		mMap2.put("propertyName", "reportFieldNum");
		mMap2.put("value", new BigInteger(reportFieldNum));
		list.add(mMap2);
		HashMap<String, Object> mMap3 = new HashMap<String, Object>();
		mMap3.put("propertyName", "reportCatalog");
		mMap3.put("value", reportCatalog);
		list.add(mMap3);

		return list;
	}

	/**
	 * Function to search a ReportField from List<ReportField> with
	 * reportFieldName and reportFieldNumber
	 * 
	 * @param reportFields
	 * @param reportFieldName
	 * @param reportFieldNum
	 * @return ReportField
	 */
	public static ReportField searchField(List<ReportField> reportFields,
			String reportFieldName, String reportFieldNum) {

		ReportField result = null;

		for (ReportField reportField : reportFields) {
			if (reportField.getReportFieldName().equals(reportFieldName)
					&& reportField.getReportFieldNum().equals(
							new BigInteger(reportFieldNum))) {
				result = reportField;
				break;
			}
		}

		if (result == null) {
			System.out.println("ERROR ReportUtilities: field does not exists "
					+ reportFieldName + " (" + reportFieldNum + ")");
		}

		return result;
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
				if (reportData.getReportDataBlock() != null) {
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
	 * Function return make a List<String> of reportData.reportDataBlock from
	 * field and number (similar searchData).
	 * 
	 * @param reportDatas
	 * @param reportFieldName
	 * @param reportFieldNum
	 * @return List<String> of reportData.Block from field
	 */
	public static List<String> searchBlockList(List<ReportData> reportDatas,
			String reportFieldName, String reportFieldNum) {
		List<String> result = new ArrayList<String>();

		for (ReportData reportData : reportDatas) {
			if (reportData.getReportDataBlock() != null) {
				if (reportData.getReportField().getReportFieldName()
						.equals(reportFieldName)
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger(reportFieldNum))) {
					if (!result.contains(reportData.getReportDataBlock()))
						result.add(reportData.getReportDataBlock());
				}
			}
		}

		return result;
	}

	/**
	 * Function receive two repeatable fields and check comparing blocks, check
	 * if in second has content, the first exists in all cases. See semantic
	 * rules of AIFM field(14).
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param reportFieldName2
	 * @param reportFieldNum2
	 * @return boolean
	 */
	public static boolean dependencyRepeData(List<ReportData> reportDatas,
			String reportFieldName1, String reportFieldNum1,
			String reportFieldName2, String reportFieldNum2) {

		boolean result = true;

		// Example: field2(14) != null, field1(15) has to have content.
		// We find all field2(14) matching block number with field1(15) and
		// check if this rule works

		List<String> listField2 = searchBlockList(reportDatas,
				reportFieldName2, reportFieldNum2);
		for (String blockNum : listField2) {
			if (searchData(reportDatas, reportFieldName2, reportFieldNum2,
					blockNum) != null) {
				// field2 has the content expected, we find in field1 is exists
				if (searchData(reportDatas, reportFieldName1, reportFieldNum1,
						blockNum) == null) {
					// if one fail, the rule is not satisfied
					result = false;
				}
			}
		}

		return result;
	}

	/**
	 * Function receive two repeatable fields and check comparing blocks, check
	 * if in second has reportDataText2, the first exists in all cases. See
	 * semantic rules of AIFM field(28). Similar to dependencyRepeData()
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param reportFieldName2
	 * @param reportFieldNum2
	 * @param reportDataText2
	 * @return boolean
	 */
	public static boolean dependencyRepeDataExist(List<ReportData> reportDatas,
			String reportFieldName1, String reportFieldNum1,
			String reportFieldName2, String reportFieldNum2,
			String reportDataText2) {

		boolean result = true;

		// Example: field2(27) = MIC, field1(28) has to have content.
		// We find all field2(27) matching block number with field1(28) and
		// check if this rule works

		List<String> listField2 = searchBlockList(reportDatas,
				reportFieldName2, reportFieldNum2);
		for (String blockNum : listField2) {
			if (searchData(reportDatas, reportFieldName2, reportFieldNum2,
					blockNum).equals(reportDataText2)) {
				// field2 has the content expected, we find in field1 is exists
				if (searchData(reportDatas, reportFieldName1, reportFieldNum1,
						blockNum) == null) {
					// if one fail, the rule is not satisfied
					result = false;
				}
			}
		}

		return result;
	}

	/**
	 * Function receive two repeatable fields and check comparing blocks, check
	 * if in second not has reportDataText2, the first exists in all cases. See
	 * semantic rules of AIFM field(29). Similar to dependencyRepeDataExist()
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param reportFieldName2
	 * @param reportFieldNum2
	 * @param reportDataText2
	 * @return boolean
	 */
	public static boolean dependencyRepeDataNot(List<ReportData> reportDatas,
			String reportFieldName1, String reportFieldNum1,
			String reportFieldName2, String reportFieldNum2,
			String reportDataText2) {

		boolean result = true;

		// Example: field2(27) <> NOT, field1(29) has to have content.
		// We find all field2(27) matching block number with field1(28) and
		// check if this rule works

		List<String> listField2 = searchBlockList(reportDatas,
				reportFieldName2, reportFieldNum2);
		for (String blockNum : listField2) {
			if (!searchData(reportDatas, reportFieldName2, reportFieldNum2,
					blockNum).equals(reportDataText2)) {
				// field2 has the content expected, we find in field1 is exists
				if (searchData(reportDatas, reportFieldName1, reportFieldNum1,
						blockNum) == null) {
					// if one fail, the rule is not satisfied
					result = false;
				}
			}
		}

		return result;
	}

	/**
	 * Function receive List<String> from searchBlockList() and return max
	 * number of reportData.Block
	 * 
	 * @param blockList
	 * @return
	 */
	public static String maxBlockFromList(List<String> blockList) {

		String maxBlock = "1";

		for (String block : blockList) {
			if (block.compareTo(maxBlock) > 0) {
				int temp = Integer.parseInt(block);
				temp++;
				maxBlock = Integer.toString(temp);
			}
		}

		return maxBlock;
	}

	/**
	 * Function return true if reportFieldRepe[0] = 1
	 * 
	 * @param reportField
	 * @return boolean
	 */
	public static boolean reportFieldIsMandatory(ReportField reportField) {
		boolean result = false;

		try {
			String[] parts = reportField.getReportFieldRepe().split(",");
			String part0 = parts[0]; // 0 Optional, 1 Mandatory
			String part1 = parts[1]; // number of repetitions 1,5,10,n...

			if (part0.equals("1"))
				result = true;

		} catch (Exception e) {
			System.out.println("ERROR reportField.isMandatory() : "
					+ e.getMessage());
			// e.printStackTrace();
		}

		return result;
	}

	/**
	 * Function return true if reportFieldRepe[1] is > than 1 or n
	 * 
	 * @param reportField
	 * @return boolean
	 */
	public static boolean reportFieldIsRepe(ReportField reportField) {
		boolean result = false;

		try {
			String[] parts = reportField.getReportFieldRepe().split(",");
			String part0 = parts[0]; // 0 Optional, 1 Mandatory
			String part1 = parts[1]; // number of repetitions 1,5,10,n...

			if (part1.equals("n")) {
				result = true;
			} else {
				if (Integer.parseInt(part1) > 1)
					result = true;
			}

		} catch (Exception e) {
			System.out
					.println("ERROR reportField.isRepe() : " + e.getMessage());
			// e.printStackTrace();
		}

		return result;
	}

	/**
	 * Function reportFieldRepe[1] convert to int : 1,5,10,n->99
	 * 
	 * @param reportField
	 * @return boolean
	 */
	public static int reportFieldNumberRepe(ReportField reportField) {
		int result = 1;

		try {
			String[] parts = reportField.getReportFieldRepe().split(",");
			String part0 = parts[0]; // 0 Optional, 1 Mandatory
			String part1 = parts[1]; // number of repetitions 1,5,10,n...

			if (part1.equals("n"))
				result = 99;
			else
				result = Integer.parseInt(part1);

		} catch (Exception e) {
			System.out.println("ERROR reportField.numberRepe() : "
					+ e.getMessage());
			// e.printStackTrace();
		}

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
