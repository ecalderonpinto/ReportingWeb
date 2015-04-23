package com.entities.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.entities.dao.common.ErrorDAO;
import com.entities.dao.loader.FileColumDAO;
import com.entities.dao.loader.FileColumListDAO;
import com.entities.dao.loader.FileConfigDAO;
import com.entities.dao.loader.LoadErrorDAO;
import com.entities.dao.loader.LoadFileDAO;
import com.entities.dao.loader.LoadRawDAO;
import com.entities.dao.loader.LoadRawDataDAO;
import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.dao.reportingtool.DepartmentDAO;
import com.entities.dao.reportingtool.FundDAO;
import com.entities.dao.reportingtool.FundGroupDAO;
import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.dao.reportingtool.ReportDataDAO;
import com.entities.dao.reportingtool.ReportDataErrorDAO;
import com.entities.dao.reportingtool.ReportErrorDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.dao.reportingtool.ReportFieldDAO;
import com.entities.dao.reportingtool.ReportFieldListDAO;
import com.entities.dao.reportingtool.ReportSemanticDAO;
import com.entities.dictionary.ReportExecutionStatusEnum;
import com.entities.entity.common.Error;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.FileConfig;
import com.entities.entity.reportingtool.Company;
import com.entities.entity.reportingtool.Department;
import com.entities.entity.reportingtool.Fund;
import com.entities.entity.reportingtool.FundGroup;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;
import com.entities.entity.reportingtool.ReportFieldList;
import com.entities.entity.reportingtool.ReportSemantic;
import com.entities.utilities.hibernate.VersionAuditor;
import com.reportingtool.utilities.ReportUtilities;

/**
 * Class to install configuration in database
 * 
 * @author alberto.olivan
 *
 */
public class InstallEntities {

	/**
	 * Default constructor of InstallEntities
	 */
	public InstallEntities() {

	}

	/**
	 * Process to install in database reportCatalog and reportFields from AIF
	 * report
	 * 
	 * @param applicationContext
	 */
	public void installAIF(ApplicationContext applicationContext) {

		// TODO:RT not inserting company/department by default in the future

		try {
			VersionAuditor versionAdmin = new VersionAuditor("admin");

			String versionField = "1.2";

			// Company company = new Company("Santander Funds Group", "Spain",
			// "SFG", "",
			// null, null, null, null, versionAdmin);
			//
			// Department department = new Department(company,
			// "Values department", "VALUE", "", "Spain", null, null,
			// null, new VersionAuditor("admin"));
			//
			// Fund fund1 = new Fund(company, "Fund 3", "ES000002", "FUND3",
			// "", null, null, null, versionAdmin);
			//
			// FundGroup fundGroup = new FundGroup(fund1, department,
			// "VALUE FUNDS", "", versionAdmin);

			ReportCatalog reportCatalog = new ReportCatalog(versionField,
					"FUND", "AIF 2014", "", null, null, null, versionAdmin);

			String str1 = "2014-01-01";
			String str2 = "2014-12-31";
			DateFormat format = new SimpleDateFormat(
					ReportUtilities.datePattern);
			Date date1 = format.parse(str1);
			Date date2 = format.parse(str2);

			// ReportExecution reportExecution = new ReportExecution(
			// reportCatalog, company, fund1, "Prueba AIF fund3 2014", "",
			// "Q2", "2014", date2, date1,
			// ReportExecutionStatusEnum.CREATION.getReportExecutionStatus(),
			// null, null, null,
			// null, null, null, null, null, null, null, null, null,
			// versionAdmin);

			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			reportCatalogDAO.create(reportCatalog);

			ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
					.getBean("reportFieldDAO");

			ReportField reportFieldx1 = new ReportField(reportCatalog, null,
					"X", "AIFReportingInfo", new BigInteger("0"), null, "",
					null, null, "1", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportFieldx1);

			ReportField reportField1 = new ReportField(reportCatalog,
					reportFieldx1, "A", "ReportingMemberState", new BigInteger(
							"1"), ".{2}", "", "General Info",
					"CountryCodeType", "1", "11", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField1);
			ReportField reportField2 = new ReportField(reportCatalog,
					reportFieldx1, "A", "Version", new BigInteger("2"),
					"([0-9])+\\.([0-9])+", "", "General Info", "VERSION", "1",
					"11", versionField, null, null, null, versionAdmin);
			reportField2.setReportFieldEditable(true);
			reportFieldDAO.create(reportField2);
			ReportField reportField3 = new ReportField(reportCatalog,
					reportFieldx1, "D", "CreationDateAndTime", new BigInteger(
							"3"),
					"[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}",
					"", "General Info", "DATETIME", "1", "11", versionField,
					null, null, null, versionAdmin);
			reportField3.setReportFieldEditable(true);
			reportFieldDAO.create(reportField3);

			ReportField reportFieldx2 = new ReportField(reportCatalog,
					reportFieldx1, "X", "AIFRecordInfo", new BigInteger("0"),
					null, "", null, null, "1.1", "11", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx2);
			ReportField reportField4 = new ReportField(reportCatalog,
					reportFieldx2, "A", "FilingType", new BigInteger("4"),
					".{4}", "", "General Info", "FilingTypeType", "1.1.01",
					"11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField4);
			ReportField reportField5 = new ReportField(reportCatalog,
					reportFieldx2, "N", "AIFContentType", new BigInteger("5"),
					"[0-9]{1}", "", "General Info", "AIFContentTypeType",
					"1.1.02", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField5);
			ReportField reportField6 = new ReportField(reportCatalog,
					reportFieldx2, "D", "ReportingPeriodStartDate",
					new BigInteger("6"), "[0-9]{4}-[0-9]{2}-[0-9]{2}", "",
					"General Info", "DATE", "1.1.03", "11", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField6);
			ReportField reportField7 = new ReportField(reportCatalog,
					reportFieldx2, "D", "ReportingPeriodEndDate",
					new BigInteger("7"), "[0-9]{4}-[0-9]{2}-[0-9]{2}", "",
					"General Info", "DATE", "1.1.04", "11", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField7);
			ReportField reportField8 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ReportingPeriodType", new BigInteger(
							"8"), ".{2}", "", "General Info",
					"ReportingPeriodTypeType", "1.1.05", "11", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField8);
			ReportField reportField9 = new ReportField(reportCatalog,
					reportFieldx2, "N", "ReportingPeriodYear", new BigInteger(
							"9"), "[0-9]{4}", "", "General Info", "YEAR",
					"1.1.06", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField9);
			ReportField reportField10 = new ReportField(reportCatalog,
					reportFieldx2, "A",
					"AIFReportingObligationChangeFrequencyCode",
					new BigInteger("10"), ".{2}", "", "General Info",
					"ReportingObligationChangeFrequencyCodeType", "1.1.07",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField10);
			ReportField reportField11 = new ReportField(reportCatalog,
					reportFieldx2, "N",
					"AIFReportingObligationChangeContentsCode", new BigInteger(
							"11"), "[0-9]{1}", "", "General Info",
					"AIFReportingObligationChangeContentsCodeType", "1.1.08",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField11);
			ReportField reportField12 = new ReportField(reportCatalog,
					reportFieldx2, "A", "AIFReportingObligationChangeQuarter",
					new BigInteger("12"), ".{2}", "", "General Info",
					"ReportingObligationChangeQuarterType", "1.1.09", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField12);
			ReportField reportField13 = new ReportField(reportCatalog,
					reportFieldx2, "B", "LastReportingFlag", new BigInteger(
							"13"), "true|false", "", "General Info", "BOOLEAN",
					"1.1.10", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField13);

			ReportField reportFieldx3 = new ReportField(reportCatalog,
					reportFieldx2, "X", "Assumptions", new BigInteger("0"),
					null, "", null, null, "1.1.11", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx3);
			ReportField reportFieldx4 = new ReportField(reportCatalog,
					reportFieldx3, "X", "Assumption", new BigInteger("0"),
					null, "", null, null, "1.1.11.1", "0n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx4);
			ReportField reportField14 = new ReportField(reportCatalog,
					reportFieldx4, "N", "QuestionNumber", new BigInteger("14"),
					"[0-9]{0,3}", "", "Assumptions", "QUESTION", "1.1.11.1.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField14);
			ReportField reportField15 = new ReportField(reportCatalog,
					reportFieldx4, "Z", "AssumptionDescription",
					new BigInteger("15"), ".{0,300}", "", "Assumptions",
					"DESCRIPTION", "1.1.11.1.2", "0n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField15);

			ReportField reportField16 = new ReportField(reportCatalog,
					reportFieldx2, "Z", "AIFMNationalCode",
					new BigInteger("16"), ".{0,30}", "", "General Info",
					"AIFMNationalCodeType", "1.1.14", "11", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField16);
			ReportField reportField17 = new ReportField(reportCatalog,
					reportFieldx2, "Z", "AIFNationalCode",
					new BigInteger("17"), ".{0,30}", "", "General Info",
					"AIFNationalCodeType", "1.1.15", "11", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField17);
			ReportField reportField18 = new ReportField(reportCatalog,
					reportFieldx2, "Z", "AIFName", new BigInteger("18"),
					".{0,300}", "", "General Info", "AIF_NAME", "1.1.15", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField18);
			ReportField reportField19 = new ReportField(reportCatalog,
					reportFieldx2, "B", "AIFEEAFlag", new BigInteger("19"),
					"true|false", "", "General Info", "BOOLEAN", "1.1.16",
					"11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField19);
			ReportField reportField20 = new ReportField(reportCatalog,
					reportFieldx2, "A", "AIFReportingCode",
					new BigInteger("20"), ".{2}", "", "General Info",
					"CountryCodeType", "", "11", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField20);
			ReportField reportField21 = new ReportField(reportCatalog,
					reportFieldx2, "A", "AIFDomicile", new BigInteger("21"),
					".{2}", "", "General Info", "CountryCodeType", "", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField21);
			ReportField reportField22 = new ReportField(reportCatalog,
					reportFieldx2, "D", "InceptionDate", new BigInteger("22"),
					"[0-9]{4}-[0-9]{2}-[0-9]{2}", "", "General Info", "DATE",
					"", "11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField22);
			ReportField reportField23 = new ReportField(reportCatalog,
					reportFieldx2, "B", "AIFNoReportingFlag", new BigInteger(
							"23"), "true|false", "", "General Info", "BOOLEAN",
					"", "11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField23);

			ReportField reportFieldx5 = new ReportField(reportCatalog,
					reportFieldx2, "X", "AIFCompleteDescription",
					new BigInteger("0"), null, "", null, null, "1.1.18", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx5);

			ReportField reportFieldx6 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFPrincipalInfo",
					new BigInteger("0"), null, "", null, null, "1.1.18", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx6);
			ReportField reportFieldx7 = new ReportField(reportCatalog,
					reportFieldx6, "X", "AIFIdentification",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx7);

			ReportField reportField24 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierLEI",
					new BigInteger("24"), ".{20}", "", "General Info",
					"AIFIdentification", "1.1.18.1.4", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField24);
			ReportField reportField25 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierISIN", new BigInteger(
							"25"), "[A-Z]{2}([A-Z]|[0-9]){9}[0-9]", "",
					"General Info", "ISINInstrumentIdentificationType",
					"1.1.18.1.4", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField25);
			ReportField reportField26 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierCUSIP", new BigInteger(
							"26"), ".{9}", "", "General Info", "CusipCodeType",
					"1.1.18.1.4", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField26);
			ReportField reportField27 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierSEDOL", new BigInteger(
							"27"), ".{7}", "", "General Info", "SedolCodeType",
					"1.1.18.1.4", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField27);
			ReportField reportField28 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierTicker", new BigInteger(
							"28"), ".{20}", "", "General Info",
					"TickerCodeType", "1.1.18.1.4", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField28);
			ReportField reportField29 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierRIC",
					new BigInteger("29"), ".{20}", "", "General Info",
					"RICCodeType", "1.1.18.1.4", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField29);
			ReportField reportField30 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierECB",
					new BigInteger("30"), ".{20}", "", "General Info",
					"ECBCodeType", "1.1.18.1.4", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField30);
			ReportField reportField31 = new ReportField(reportCatalog,
					reportFieldx7, "A", "ReportingMemberState", new BigInteger(
							"31"), ".{2}", "", "General Info",
					"CountryCodeType", "1.1.18.1.4", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField31);
			ReportField reportField32 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFNationalCode",
					new BigInteger("32"), ".{30}", "", "General Info",
					"AIFMNationalCodeType", "1.1.18.1.4", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField32);

			ReportField reportFieldx8 = new ReportField(reportCatalog,
					reportFieldx7, "X", "ShareClassIdentifier", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx8);

			ReportField reportField33 = new ReportField(reportCatalog,
					reportFieldx8, "B", "ShareClassFlag", new BigInteger("33"),
					"true|false", "", "General Info", "BOOLEAN", "1.1.18.1",
					"11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField33);
			ReportField reportField34 = new ReportField(reportCatalog,
					reportFieldx8, "Z", "ShareClassNationalCode",
					new BigInteger("34"), ".{0,30}", "", "General Info",
					"ShareClassNationalCodeType", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField34);
			ReportField reportField35 = new ReportField(reportCatalog,
					reportFieldx8, "A", "ShareClassIdentifierISIN",
					new BigInteger("35"), "[A-Z]{2}([A-Z]|[0-9]){9}[0-9]", "",
					"General Info", "ISINInstrumentIdentificationType",
					"1.1.18.1", "0n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField35);
			ReportField reportField36 = new ReportField(reportCatalog,
					reportFieldx8, "A", "ShareClassIdentifierSEDOL",
					new BigInteger("36"), ".{7}", "", "General Info",
					"SedolCodeType", "1.1.18.1", "0n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField36);
			ReportField reportField37 = new ReportField(reportCatalog,
					reportFieldx8, "A", "ShareClassIdentifierCUSIP",
					new BigInteger("37"), ".{9}", "", "General Info",
					"CusipCodeType", "1.1.18.1", "0n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField37);
			ReportField reportField38 = new ReportField(reportCatalog,
					reportFieldx8, "A", "ShareClassIdentifierTicker",
					new BigInteger("38"), ".{20}", "", "General Info",
					"TickerCodeType", "1.1.18.1", "0n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField38);
			ReportField reportField39 = new ReportField(reportCatalog,
					reportFieldx8, "A", "ShareClassIdentifierRIC",
					new BigInteger("39"), ".{20}", "", "General Info",
					"RICCodeType", "1.1.18.1", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField39);
			ReportField reportField40 = new ReportField(reportCatalog,
					reportFieldx8, "Z", "ShareClassName", new BigInteger("40"),
					".{0,300}", "", "General Info", "SHARECLASS", "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField40);

			ReportField reportFieldx9 = new ReportField(reportCatalog,
					reportFieldx6, "X", "AIFDescription", new BigInteger("0"),
					null, "", null, null, "1.1.18.1", "11", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx9);

			ReportField reportField41 = new ReportField(reportCatalog,
					reportFieldx9, "A", "AIFMasterFeederStatus",
					new BigInteger("41"), ".{6}", "", "General Info",
					"AIFMasterFeederStatusType", "1.1.18.1", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField41);

			ReportField reportFieldx10 = new ReportField(reportCatalog,
					reportFieldx9, "X", "MasterAIFsIdentification",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx10);
			ReportField reportFieldx11 = new ReportField(reportCatalog,
					reportFieldx10, "X", "MasterAIFIdentification",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx11);

			ReportField reportField42 = new ReportField(reportCatalog,
					reportFieldx11, "Z", "AIFName", new BigInteger("42"),
					".{0,300}", "", "General Info", "AIF_NAME", "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField42);
			ReportField reportField43 = new ReportField(reportCatalog,
					reportFieldx11, "A", "ReportingMemberState",
					new BigInteger("43"), ".{2}", "", "General Info",
					"CountryCodeType", "1.1.18.1", "0n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField43);
			ReportField reportField44 = new ReportField(reportCatalog,
					reportFieldx11, "Z", "AIFMNationalCode", new BigInteger(
							"44"), ".{0,30}", "", "General Info",
					"AIFNationalCodeType", "1.1.18.1", "0n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField44);

			ReportField reportFieldx12 = new ReportField(reportCatalog,
					reportFieldx9, "X", "PrimeBrokers", new BigInteger("0"),
					null, "", null, null, "1.1.18.1", "0n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx12);
			ReportField reportFieldx13 = new ReportField(reportCatalog,
					reportFieldx12, "X", "PrimeBrokerIdentification",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx13);
			ReportField reportField45 = new ReportField(reportCatalog,
					reportFieldx13, "Z", "EntityName", new BigInteger("45"),
					".{0,300}", "", "General Info", "NAME", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField45);
			ReportField reportField46 = new ReportField(reportCatalog,
					reportFieldx13, "A", "EntityIdentificationLEI",
					new BigInteger("46"), ".{20}", "", "General Info",
					"LEICodeType", "1.1.18.1", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField46);
			ReportField reportField47 = new ReportField(reportCatalog,
					reportFieldx13, "A", "EntityIdentificationBIC",
					new BigInteger("47"), ".{11}", "", "General Info",
					"BICCodeType", "1.1.18.1", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField47);

			ReportField reportFieldx14 = new ReportField(reportCatalog,
					reportFieldx9, "X", "AIFBaseCurrencyDescription",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx14);
			ReportField reportField48 = new ReportField(reportCatalog,
					reportFieldx14, "N", "AUMAmountInBaseCurrency",
					new BigInteger("48"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.1", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField48);
			ReportField reportField49 = new ReportField(reportCatalog,
					reportFieldx14, "A", "BaseCurrency", new BigInteger("49"),
					".{3}", "", "General Info", "CurrencyCodeType", "1.1.18.1",
					"11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField49);
			ReportField reportField50 = new ReportField(reportCatalog,
					reportFieldx14, "N", "FXEURRate", new BigInteger("50"),
					"[0-9]{1,15}(\\.[0-9]{1,4})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField50);
			ReportField reportField51 = new ReportField(reportCatalog,
					reportFieldx14, "A", "FXEURReferenceRateType",
					new BigInteger("51"), ".{3}", "", "General Info",
					"FXEURReferenceRateTypeType", "1.1.18.1", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField51);
			ReportField reportField52 = new ReportField(reportCatalog,
					reportFieldx14, "Z", "FXEUROtherReferenceRateDescription",
					new BigInteger("52"), ".{0,300}", "", "General Info",
					"DESCRIPTION", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField52);

			ReportField reportField53 = new ReportField(reportCatalog,
					reportFieldx9, "N", "AIFNetAssetValue",
					new BigInteger("53"), "[+|-]?[0-9]{1,15}", "",
					"General Info", "NUMBER", "1.1.18.1", "11", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField53);
			ReportField reportField54 = new ReportField(reportCatalog,
					reportFieldx9, "A", "FirstFundingSourceCountry",
					new BigInteger("54"), ".{2}", "", "General Info",
					"CountryCodeType", "1.1.18.1", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField54);
			ReportField reportField55 = new ReportField(reportCatalog,
					reportFieldx9, "A", "SecondFundingSourceCountry",
					new BigInteger("55"), ".{2}", "", "General Info",
					"CountryCodeType", "1.1.18.1", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField55);
			ReportField reportField56 = new ReportField(reportCatalog,
					reportFieldx9, "A", "ThirdFundingSourceCountry",
					new BigInteger("56"), ".{2}", "", "General Info",
					"CountryCodeType", "1.1.18.1", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField56);
			ReportField reportField57 = new ReportField(reportCatalog,
					reportFieldx9, "A", "PredominantAIFType", new BigInteger(
							"57"), ".{4}", "", "General Info", "AIFTypeType",
					"1.1.18.1", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField57);

			ReportField reportFieldx15 = new ReportField(reportCatalog,
					reportFieldx9, "X", "HedgeFundInvestmentStrategies",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx15);
			ReportField reportFieldx16 = new ReportField(reportCatalog,
					reportFieldx15, "X", "HedgeFundStrategy", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx16);
			ReportField reportField58_H = new ReportField(reportCatalog,
					reportFieldx16, "A", "HedgeFundStrategyType",
					new BigInteger("58"), ".{9}", "", "General Info",
					"HedgeFundStrategy", "1.1.18.1", "0n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField58_H);
			ReportField reportField59_H = new ReportField(reportCatalog,
					reportFieldx16, "B", "PrimaryStrategyFlag", new BigInteger(
							"59"), "true|false", "", "General Info", "BOOLEAN",
					"1.1.18.1", "0n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField59_H);
			ReportField reportField60_H = new ReportField(reportCatalog,
					reportFieldx16, "N", "StrategyNAVRate",
					new BigInteger("60"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField60_H);

			ReportField reportFieldx17 = new ReportField(reportCatalog,
					reportFieldx9, "X",
					"PrivateEquityFundInvestmentStrategies",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx17);
			ReportField reportFieldx18 = new ReportField(reportCatalog,
					reportFieldx17, "X", "PrivateEquityFundInvestmentStrategy",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx18);
			ReportField reportField58_P = new ReportField(reportCatalog,
					reportFieldx18, "A", "PrivateEquityFundStrategyType",
					new BigInteger("58"), ".{9}", "", "General Info",
					"PrivateEquityFundInvestmentStrategy", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField58_P);
			ReportField reportField59_P = new ReportField(reportCatalog,
					reportFieldx18, "B", "PrimaryStrategyFlag", new BigInteger(
							"59"), "true|false", "", "General Info", "BOOLEAN",
					"1.1.18.1", "0n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField59_P);
			ReportField reportField60_P = new ReportField(reportCatalog,
					reportFieldx18, "N", "StrategyNAVRate",
					new BigInteger("60"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField60_P);

			ReportField reportFieldx19 = new ReportField(reportCatalog,
					reportFieldx9, "X", "FundOfFundsInvestmentStrategies",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx19);
			ReportField reportFieldx20 = new ReportField(reportCatalog,
					reportFieldx19, "X", "FundOfFundsStrategy", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx20);
			ReportField reportField58_F = new ReportField(reportCatalog,
					reportFieldx20, "A", "FundOfFundsStrategyType",
					new BigInteger("58"), ".{9}", "", "General Info",
					"FundOfFundsStrategy", "1.1.18.1", "0n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField58_F);
			ReportField reportField59_F = new ReportField(reportCatalog,
					reportFieldx20, "B", "PrimaryStrategyFlag", new BigInteger(
							"59"), "true|false", "", "General Info", "BOOLEAN",
					"1.1.18.1", "0n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField59_F);
			ReportField reportField60_F = new ReportField(reportCatalog,
					reportFieldx20, "N", "StrategyNAVRate",
					new BigInteger("60"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField60_F);

			ReportField reportFieldx21 = new ReportField(reportCatalog,
					reportFieldx9, "X", "OtherFundInvestmentStrategies",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx21);
			ReportField reportFieldx22 = new ReportField(reportCatalog,
					reportFieldx21, "X", "OtherFundStrategy", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx22);
			ReportField reportField58_O = new ReportField(reportCatalog,
					reportFieldx22, "A", "OtherFundStrategyType",
					new BigInteger("58"), ".{9}", "", "General Info",
					"OtherFundStrategy", "1.1.18.1", "0n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField58_O);
			ReportField reportField59_O = new ReportField(reportCatalog,
					reportFieldx22, "B", "PrimaryStrategyFlag", new BigInteger(
							"59"), "true|false", "", "General Info", "BOOLEAN",
					"1.1.18.1", "0n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField59_O);
			ReportField reportField60_O = new ReportField(reportCatalog,
					reportFieldx22, "N", "StrategyNAVRate",
					new BigInteger("60"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField60_O);
			ReportField reportField61_O = new ReportField(reportCatalog,
					reportFieldx22, "Z", "StrategyTypeOtherDescription",
					new BigInteger("61"), ".{300}", "", "General Info",
					"DESCRIPTION", "1.1.18.1", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField61_O);

			ReportField reportFieldx23 = new ReportField(reportCatalog,
					reportFieldx9, "X", "RealEstateFundInvestmentStrategies",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx23);
			ReportField reportFieldx24 = new ReportField(reportCatalog,
					reportFieldx23, "X", "RealEstateFundStrategy",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx24);
			ReportField reportField58_R = new ReportField(reportCatalog,
					reportFieldx24, "A", "RealEstateFundStrategyType",
					new BigInteger("58"), ".{9}", "", "General Info",
					"RealEstateFundStrategy", "1.1.18.1", "0n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField58_R);
			ReportField reportField59_R = new ReportField(reportCatalog,
					reportFieldx24, "B", "PrimaryStrategyFlag", new BigInteger(
							"59"), "true|false", "", "General Info", "BOOLEAN",
					"1.1.18.1", "0n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField59_R);
			ReportField reportField60_R = new ReportField(reportCatalog,
					reportFieldx24, "N", "StrategyNAVRate",
					new BigInteger("60"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField60_R);

			ReportField reportField62 = new ReportField(reportCatalog,
					reportFieldx9, "N", "HFTTransactionNumber", new BigInteger(
							"62"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.1", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField62);
			ReportField reportField63 = new ReportField(reportCatalog,
					reportFieldx9, "N", "HFTBuySellMarketValue",
					new BigInteger("63"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.1", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField63);

			ReportField reportFieldx25 = new ReportField(reportCatalog,
					reportFieldx6, "X", "MainInstrumentsTraded",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"05", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx25);
			ReportField reportFieldx26 = new ReportField(reportCatalog,
					reportFieldx25, "X", "MainInstrumentTraded",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"05", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx26);
			ReportField reportField64 = new ReportField(reportCatalog,
					reportFieldx26, "N", "Ranking", new BigInteger("64"),
					"[0-9]{1}", "", "General Info", "FiveRankingType",
					"1.1.18.1", "55", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField64);
			ReportField reportField65 = new ReportField(reportCatalog,
					reportFieldx26, "Z", "SubAssetType", new BigInteger("65"),
					".{12}", "", "General Info", "SubAssetTypeType",
					"1.1.18.1", "55", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField65);
			ReportField reportField66 = new ReportField(reportCatalog,
					reportFieldx26, "A", "InstrumentCodeType", new BigInteger(
							"66"), ".{4}", "", "General Info",
					"InstrumentCodeTypeType", "1.1.18.1", "05", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField66);
			ReportField reportField67 = new ReportField(reportCatalog,
					reportFieldx26, "Z", "InstrumentName",
					new BigInteger("67"), ".{300}", "", "General Info",
					"INSTR_NAME", "1.1.18.1", "05", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField67);
			ReportField reportField68 = new ReportField(reportCatalog,
					reportFieldx26, "A", "ISINInstrumentIdentification",
					new BigInteger("68"), "[A-Z]{2}([A-Z]|[0-9]){9}[0-9]", "",
					"General Info", "ISINInstrumentIdentificationType",
					"1.1.18.1", "05", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField68);
			ReportField reportFieldx27 = new ReportField(reportCatalog,
					reportFieldx26, "X", "AIIInstrumentIdentification",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"05", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx27);
			ReportField reportField69 = new ReportField(reportCatalog,
					reportFieldx27, "A", "AIIExchangeCode",
					new BigInteger("69"), ".{4}", "", "General Info",
					"MICCodeType", "1.1.18.1", "05", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField69);
			ReportField reportField70 = new ReportField(reportCatalog,
					reportFieldx27, "A", "AIIProductCode",
					new BigInteger("70"), ".{1,12}", "", "General Info",
					"AIIProductCodeType", "1.1.18.1", "05", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField70);
			ReportField reportField71 = new ReportField(reportCatalog,
					reportFieldx27, "A", "AIIDerivativeType", new BigInteger(
							"71"), ".{1}", "", "General Info",
					"AIIDerivativeTypeType", "1.1.18.1", "05", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField71);
			ReportField reportField72 = new ReportField(reportCatalog,
					reportFieldx27, "A", "AIIPutCallIdentifier",
					new BigInteger("72"), ".{1}", "", "General Info",
					"AIIPutCallIdentifierType", "1.1.18.1", "05", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField72);
			ReportField reportField73 = new ReportField(reportCatalog,
					reportFieldx27, "D", "AIIExpiryDate", new BigInteger("73"),
					"[0-9]{4}-[0-9]{2}-[0-9]{2}", "", "General Info", "DATE",
					"1.1.18.1", "05", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField73);
			ReportField reportField74 = new ReportField(reportCatalog,
					reportFieldx27, "N", "AIIStrikePrice",
					new BigInteger("74"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.1", "05", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField74);
			ReportField reportField75 = new ReportField(reportCatalog,
					reportFieldx26, "A", "PositionType", new BigInteger("75"),
					".{1}", "", "General Info", "PositionTypeType", "1.1.18.1",
					"05", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField75);
			ReportField reportField76 = new ReportField(reportCatalog,
					reportFieldx26, "N", "PositionValue", new BigInteger("76"),
					"[0-9]{0,15}?", "", "General Info", "NUMBER", "1.1.18.1",
					"05", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField76);
			ReportField reportField77 = new ReportField(reportCatalog,
					reportFieldx26, "N", "ShortPositionHedgingRate",
					new BigInteger("77"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.1", "05", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField77);

			ReportField reportFieldx28 = new ReportField(reportCatalog,
					reportFieldx6, "X", "NAVGeographicalFocus", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx28);
			ReportField reportField78 = new ReportField(reportCatalog,
					reportFieldx28, "N", "AfricaNAVRate", new BigInteger("78"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "11", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField78);
			ReportField reportField79 = new ReportField(reportCatalog,
					reportFieldx28, "N", "AsiaPacificNAVRate", new BigInteger(
							"79"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"General Info", "DECIMAL", "1.1.18.1", "11", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField79);
			ReportField reportField80 = new ReportField(reportCatalog,
					reportFieldx28, "N", "EuropeNAVRate", new BigInteger("80"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "11", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField80);
			ReportField reportField81 = new ReportField(reportCatalog,
					reportFieldx28, "N", "EEANAVRate", new BigInteger("81"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "11", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField81);
			ReportField reportField82 = new ReportField(reportCatalog,
					reportFieldx28, "N", "MiddleEastNAVRate", new BigInteger(
							"82"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"General Info", "DECIMAL", "1.1.18.1", "11", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField82);
			ReportField reportField83 = new ReportField(reportCatalog,
					reportFieldx28, "N", "NorthAmericaNAVRate", new BigInteger(
							"83"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"General Info", "DECIMAL", "1.1.18.1", "11", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField83);
			ReportField reportField84 = new ReportField(reportCatalog,
					reportFieldx28, "N", "SouthAmericaNAVRate", new BigInteger(
							"84"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"General Info", "DECIMAL", "1.1.18.1", "11", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField84);
			ReportField reportField85 = new ReportField(reportCatalog,
					reportFieldx28, "N", "SupraNationalNAVRate",
					new BigInteger("85"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField85);

			ReportField reportFieldx29 = new ReportField(reportCatalog,
					reportFieldx6, "X", "AUMGeographicalFocus", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx29);
			ReportField reportField86 = new ReportField(reportCatalog,
					reportFieldx29, "N", "AfricaAUMRate", new BigInteger("86"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "General Info",
					"PERCENT", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField86);
			ReportField reportField87 = new ReportField(reportCatalog,
					reportFieldx29, "N", "AsiaPacificAUMRate", new BigInteger(
							"87"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField87);
			ReportField reportField88 = new ReportField(reportCatalog,
					reportFieldx29, "N", "EuropeAUMRate", new BigInteger("88"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "General Info",
					"PERCENT", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField88);
			ReportField reportField89 = new ReportField(reportCatalog,
					reportFieldx29, "N", "EEAAUMRate", new BigInteger("89"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "General Info",
					"PERCENT", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField89);
			ReportField reportField90 = new ReportField(reportCatalog,
					reportFieldx29, "N", "MiddleEastAUMRate", new BigInteger(
							"90"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField90);
			ReportField reportField91 = new ReportField(reportCatalog,
					reportFieldx29, "N", "NorthAmericaAUMRate", new BigInteger(
							"91"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField91);
			ReportField reportField92 = new ReportField(reportCatalog,
					reportFieldx29, "N", "SouthAmericaAUMRate", new BigInteger(
							"92"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField92);
			ReportField reportField93 = new ReportField(reportCatalog,
					reportFieldx29, "N", "SupraNationalAUMRate",
					new BigInteger("93"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField93);

			ReportField reportFieldx30 = new ReportField(reportCatalog,
					reportFieldx6, "X", "PrincipalExposures", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx30);
			ReportField reportFieldx31 = new ReportField(reportCatalog,
					reportFieldx30, "X", "PrincipalExposure", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "00",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx31);
			ReportField reportField94 = new ReportField(reportCatalog,
					reportFieldx31, "N", "Ranking", new BigInteger("94"),
					"[0-9]{1,2}", "", "General Info", "TenRankingType",
					"1.1.18.1", "00", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField94);
			ReportField reportField95 = new ReportField(reportCatalog,
					reportFieldx31, "A", "AssetMacroType",
					new BigInteger("95"), ".{3}", "", "General Info",
					"AssetMacroTypeType", "1.1.18.1", "00", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField95);
			ReportField reportField96 = new ReportField(reportCatalog,
					reportFieldx31, "Z", "SubAssetType", new BigInteger("96"),
					".{12}", "", "General Info", "SubAssetTypeType",
					"1.1.18.1", "00", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField96);
			ReportField reportField97 = new ReportField(reportCatalog,
					reportFieldx31, "A", "PositionType", new BigInteger("97"),
					".{1}", "", "General Info", "PositionTypeType", "1.1.18.1",
					"00", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField97);
			ReportField reportField98 = new ReportField(reportCatalog,
					reportFieldx31, "N", "AggregatedValueAmount",
					new BigInteger("98"), "[0-9]{1,15}?", "", "General Info",
					"NUMBER", "1.1.18.1", "00", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField98);
			ReportField reportField99 = new ReportField(reportCatalog,
					reportFieldx31, "N", "AggregatedValueRate", new BigInteger(
							"99"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "00", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField99);
			ReportField reportField100 = new ReportField(reportCatalog,
					reportFieldx31, "Z", "EntityName", new BigInteger("100"),
					".{300}", "", "General Info", "NAME", "1.1.18.1", "00",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField100);
			ReportField reportField101 = new ReportField(reportCatalog,
					reportFieldx31, "A", "EntityIdentificationLEI",
					new BigInteger("101"), ".{20}", "", "General Info",
					"LEICodeType", "1.1.18.1", "00", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField101);
			ReportField reportField102 = new ReportField(reportCatalog,
					reportFieldx31, "A", "EntityIdentificationBIC",
					new BigInteger("102"), ".{11}", "", "General Info",
					"BICCodeType", "1.1.18.1", "00", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField102);

			ReportField reportFieldx32 = new ReportField(reportCatalog,
					reportFieldx6, "X", "MostImportantConcentration",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx32);
			ReportField reportFieldx33 = new ReportField(reportCatalog,
					reportFieldx32, "X", "PortfolioConcentrations",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx33);
			ReportField reportFieldx34 = new ReportField(reportCatalog,
					reportFieldx33, "X", "PortfolioConcentration",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"55", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx34);
			ReportField reportField103 = new ReportField(reportCatalog,
					reportFieldx34, "N", "Ranking", new BigInteger("103"),
					"[0-9]{1}", "", "General Info", "FiveRankingType",
					"1.1.18.1", "55", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField103);
			ReportField reportField104 = new ReportField(reportCatalog,
					reportFieldx34, "A", "AssetType", new BigInteger("104"),
					".{7}", "", "General Info", "AssetTypeType", "1.1.18.1",
					"55", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField104);
			ReportField reportField105 = new ReportField(reportCatalog,
					reportFieldx34, "A", "PositionType", new BigInteger("105"),
					".{1}", "", "General Info", "PositionTypeType", "1.1.18.1",
					"05", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField105);
			ReportField reportFieldx35 = new ReportField(reportCatalog,
					reportFieldx34, "X", "MarketIdentification",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"55", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx35);
			ReportField reportField106 = new ReportField(reportCatalog,
					reportFieldx35, "A", "MarketCodeType",
					new BigInteger("106"), ".{3}", "", "General Info",
					"MarketCodeTypeWithoutNOTType", "1.1.18.1", "05",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField106);
			ReportField reportField107 = new ReportField(reportCatalog,
					reportFieldx35, "A", "MarketCode", new BigInteger("107"),
					".{4}", "", "General Info", "MICCodeType", "1.1.18.1",
					"05", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField107);
			ReportField reportField108 = new ReportField(reportCatalog,
					reportFieldx34, "N", "AggregatedValueAmount",
					new BigInteger("108"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.1", "05", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField108);
			ReportField reportField109 = new ReportField(reportCatalog,
					reportFieldx34, "N", "AggregatedValueRate", new BigInteger(
							"109"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "05", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField109);
			ReportField reportFieldx36 = new ReportField(reportCatalog,
					reportFieldx35, "X", "CounterpartyIdentification",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"05", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx36);
			ReportField reportField110 = new ReportField(reportCatalog,
					reportFieldx36, "Z", "EntityName", new BigInteger("110"),
					".{0,300}", "", "General Info", "NAME", "1.1.18.1", "05",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField110);
			ReportField reportField111 = new ReportField(reportCatalog,
					reportFieldx36, "A", "EntityIdentificationLEI",
					new BigInteger("111"), ".{20}", "", "General Info",
					"LEICodeType", "1.1.18.1", "05", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField111);
			ReportField reportField112 = new ReportField(reportCatalog,
					reportFieldx36, "A", "EntityIdentificationBIC",
					new BigInteger("112"), ".{11}", "", "General Info",
					"BICCodeType", "1.1.18.1", "05", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField112);

			ReportField reportField113 = new ReportField(reportCatalog,
					reportFieldx32, "A", "TypicalPositionSize", new BigInteger(
							"113"), ".{4}", "", "General Info",
					"TypicalPositionSizeType", "1.1.18.1", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField113);

			ReportField reportFieldx37 = new ReportField(reportCatalog,
					reportFieldx32, "X", "AIFPrincipalMarkets", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx37);
			ReportField reportFieldx38 = new ReportField(reportCatalog,
					reportFieldx37, "X", "AIFPrincipalMarket", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "33",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx38);
			ReportField reportField114 = new ReportField(reportCatalog,
					reportFieldx38, "N", "Ranking", new BigInteger("114"),
					"[0-9]{1}", "", "General Info", "ThreeRankingType",
					"1.1.18.1", "33", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField114);
			ReportField reportFieldx39 = new ReportField(reportCatalog,
					reportFieldx38, "X", "MarketIdentification",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"33", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx39);
			ReportField reportField115 = new ReportField(reportCatalog,
					reportFieldx39, "A", "MarketCodeType",
					new BigInteger("115"), ".{3}", "", "General Info",
					"MarketCodeTypeWithoutNOTType", "1.1.18.1", "33",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField115);
			ReportField reportField116 = new ReportField(reportCatalog,
					reportFieldx39, "A", "MarketCode", new BigInteger("116"),
					".{4}", "", "General Info", "MICCodeType", "1.1.18.1",
					"03", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField116);
			ReportField reportField117 = new ReportField(reportCatalog,
					reportFieldx38, "N", "AggregatedValueAmount",
					new BigInteger("117"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "03", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField117);

			ReportField reportFieldx40 = new ReportField(reportCatalog,
					reportFieldx32, "X", "InvestorConcentration",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx40);
			ReportField reportField118 = new ReportField(reportCatalog,
					reportFieldx40, "N", "MainBeneficialOwnersRate",
					new BigInteger("118"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "11", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField118);
			ReportField reportField119 = new ReportField(reportCatalog,
					reportFieldx40, "N",
					"ProfessionalInvestorConcentrationRate", new BigInteger(
							"119"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "11", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField119);
			ReportField reportField120 = new ReportField(reportCatalog,
					reportFieldx40, "N", "RetailInvestorConcentrationRate",
					new BigInteger("120"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "11", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField120);

			ReportField reportFieldx41 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFIndividualInfo",
					new BigInteger("0"), null, "", null, null, "1.1.18", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx41);

			ReportField reportFieldx42 = new ReportField(reportCatalog,
					reportFieldx41, "X", "IndividualExposure", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx42);
			ReportField reportFieldx43 = new ReportField(reportCatalog,
					reportFieldx42, "X", "AssetTypeExposures", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx43);
			ReportField reportFieldx44 = new ReportField(reportCatalog,
					reportFieldx43, "X", "AssetTypeExposure", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "1n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx44);
			ReportField reportField121 = new ReportField(reportCatalog,
					reportFieldx44, "A", "SubAssetType", new BigInteger("121"),
					".{1,12}", "", "General Info", "SubAssetTypeType",
					"1.1.18.3.1.2", "1n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField121);
			ReportField reportField122 = new ReportField(reportCatalog,
					reportFieldx44, "N", "GrossValue", new BigInteger("122"),
					"[0-9]{0,15}?", "", "General Info", "NUMBER",
					"1.1.18.2.1.3", "0n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField122);
			ReportField reportField123 = new ReportField(reportCatalog,
					reportFieldx44, "N", "LongValue", new BigInteger("123"),
					"[0-9]{0,15}?", "", "General Info", "NUMBER",
					"1.1.18.2.1.3", "0n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField123);
			ReportField reportField124 = new ReportField(reportCatalog,
					reportFieldx44, "N", "ShortValue", new BigInteger("124"),
					"[0-9]{0,15}?", "", "General Info", "NUMBER",
					"1.1.18.2.1.3", "0n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField124);

			ReportField reportFieldx45 = new ReportField(reportCatalog,
					reportFieldx42, "X", "AssetTypeTurnovers", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx45);
			ReportField reportFieldx46 = new ReportField(reportCatalog,
					reportFieldx45, "X", "AssetTypeTurnover", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "1n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx46);
			ReportField reportField125 = new ReportField(reportCatalog,
					reportFieldx46, "A", "TurnoverSubAssetType",
					new BigInteger("125"), ".{1,12}", "", "General Info",
					"TurnoverSubAssetTypeType", "1.1.18.3.1.2", "1n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField125);
			ReportField reportField126 = new ReportField(reportCatalog,
					reportFieldx46, "N", "MarketValue", new BigInteger("126"),
					"[0-9]{0,15}?", "", "General Info", "NUMBER",
					"1.1.18.2.1.3", "0n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField126);
			ReportField reportField127 = new ReportField(reportCatalog,
					reportFieldx46, "N", "NotionalValue",
					new BigInteger("127"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField127);

			ReportField reportFieldx47 = new ReportField(reportCatalog,
					reportFieldx42, "X", "CurrencyExposures", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx47);
			ReportField reportFieldx48 = new ReportField(reportCatalog,
					reportFieldx47, "X", "CurrencyExposure",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx48);
			ReportField reportField128 = new ReportField(reportCatalog,
					reportFieldx48, "A", "ExposureCurrency", new BigInteger(
							"128"), ".{3}", "", "General Info",
					"CurrencyCodeType", "1.1.18.6.1", "0n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField128);
			ReportField reportField129 = new ReportField(reportCatalog,
					reportFieldx48, "N", "LongPositionValue", new BigInteger(
							"129"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField129);
			ReportField reportField130 = new ReportField(reportCatalog,
					reportFieldx48, "N", "ShortPositionValue", new BigInteger(
							"130"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField130);

			ReportField reportFieldx49 = new ReportField(reportCatalog,
					reportFieldx42, "X", "CompaniesDominantInfluence",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx49);
			ReportField reportFieldx50 = new ReportField(reportCatalog,
					reportFieldx49, "X", "CompanyDominantInfluence",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx50);
			ReportField reportFieldx51 = new ReportField(reportCatalog,
					reportFieldx50, "X", "CompanyIdentification",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx51);
			ReportField reportField131 = new ReportField(reportCatalog,
					reportFieldx51, "Z", "EntityName", new BigInteger("131"),
					".{0,300}", "", "General Info", "NAME", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField131);
			ReportField reportField132 = new ReportField(reportCatalog,
					reportFieldx51, "A", "EntityIdentificationLEI",
					new BigInteger("132"), ".{20}", "", "General Info",
					"LEICodeType", "1.1.18.1", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField132);
			ReportField reportField133 = new ReportField(reportCatalog,
					reportFieldx51, "A", "EntityIdentificationBIC",
					new BigInteger("133"), ".{11}", "", "General Info",
					"BICCodeType", "1.1.18.1", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField133);
			ReportField reportField134 = new ReportField(reportCatalog,
					reportFieldx50, "A", "TransactionType", new BigInteger(
							"134"), ".{4}", "", "General Info",
					"TransactionTypeType", "1.1.18.1", "0n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField134);
			ReportField reportField135 = new ReportField(reportCatalog,
					reportFieldx50, "Z", "OtherTransactionTypeDescription",
					new BigInteger("135"), ".{0,300}", "", "General Info",
					"NAME", "1.1.18.1", "0n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField135);
			ReportField reportField136 = new ReportField(reportCatalog,
					reportFieldx50, "N", "VotingRightsRate", new BigInteger(
							"136"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "0n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField136);

			ReportField reportFieldx52 = new ReportField(reportCatalog,
					reportFieldx41, "X", "RiskProfile", new BigInteger("0"),
					null, "", null, null, "1.1.18.1", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx52);

			ReportField reportFieldx53 = new ReportField(reportCatalog,
					reportFieldx52, "X", "MarketRiskProfile", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx53);
			ReportField reportField137 = new ReportField(reportCatalog,
					reportFieldx53, "N", "AnnualInvestmentReturnRate",
					new BigInteger("137"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField137);

			ReportField reportFieldx54 = new ReportField(reportCatalog,
					reportFieldx53, "X", "MarketRiskMeasures", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "1n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx54);
			ReportField reportFieldx55 = new ReportField(reportCatalog,
					reportFieldx54, "X", "MarketRiskMeasure", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "1n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx55);
			ReportField reportField138 = new ReportField(reportCatalog,
					reportFieldx55, "Z", "RiskMeasureType", new BigInteger(
							"138"), ".{15}", "", "General Info",
					"RiskMeasureTypeType", "1.1.18.1", "0n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField138);
			ReportField reportField139 = new ReportField(reportCatalog,
					reportFieldx55, "N", "RiskMeasureValue", new BigInteger(
							"139"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"General Info", "DECIMAL", "1.1.18.1", "0n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField139);

			ReportField reportFieldx56 = new ReportField(reportCatalog,
					reportFieldx55, "X", "BucketRiskMeasureValues",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"1n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx56);
			ReportField reportField140 = new ReportField(reportCatalog,
					reportFieldx56, "N", "LessFiveYearsRiskMeasureValue",
					new BigInteger("140"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField140);
			ReportField reportField141 = new ReportField(reportCatalog,
					reportFieldx56, "N", "FifthteenYearsRiskMeasureValue",
					new BigInteger("141"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField141);
			ReportField reportField142 = new ReportField(reportCatalog,
					reportFieldx56, "N", "MoreFifthteenYearsRiskMeasureValue",
					new BigInteger("142"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField142);

			ReportField reportFieldx57 = new ReportField(reportCatalog,
					reportFieldx55, "X", "VegaRiskMeasureValues",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx57);
			ReportField reportField143 = new ReportField(reportCatalog,
					reportFieldx57, "N", "CurrentMarketRiskMeasureValue",
					new BigInteger("143"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField143);
			ReportField reportField144 = new ReportField(reportCatalog,
					reportFieldx57, "N", "LowerMarketRiskMeasureValue",
					new BigInteger("144"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField144);
			ReportField reportField145 = new ReportField(reportCatalog,
					reportFieldx57, "N", "HigherMarketRiskMeasureValue",
					new BigInteger("145"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField145);

			ReportField reportFieldx58 = new ReportField(reportCatalog,
					reportFieldx55, "X", "VARRiskMeasureValues",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx58);
			ReportField reportField302 = new ReportField(reportCatalog,
					reportFieldx58, "N", "VARValue", new BigInteger("302"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "General Info",
					"PERCENT", "1.1.18.1", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField302);
			ReportField reportField146 = new ReportField(reportCatalog,
					reportFieldx58, "A", "VARCalculationMethodCodeType",
					new BigInteger("146"), ".{5}", "", "General Info",
					"VARCalculationMethodCodeTypeType", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField146);
			ReportField reportField147 = new ReportField(reportCatalog,
					reportFieldx55, "Z", "RiskMeasureDescription",
					new BigInteger("147"), ".{0,300}", "", "General Info",
					"DESCRIPTION", "1.1.11.1.2", "0n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField147);

			ReportField reportFieldx59 = new ReportField(reportCatalog,
					reportFieldx52, "X", "CounterpartyRiskProfile",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx59);

			ReportField reportFieldx60 = new ReportField(reportCatalog,
					reportFieldx59, "X", "TradingClearingMechanism",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"1n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx60);

			ReportField reportFieldx61 = new ReportField(reportCatalog,
					reportFieldx60, "X", "TradedSecurities",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"1n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx61);
			ReportField reportField148 = new ReportField(reportCatalog,
					reportFieldx61, "N", "RegulatedMarketRate", new BigInteger(
							"148"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField148);
			ReportField reportField149 = new ReportField(reportCatalog,
					reportFieldx61, "N", "OTCRate", new BigInteger("149"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "General Info",
					"PERCENT", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField149);

			ReportField reportFieldx62 = new ReportField(reportCatalog,
					reportFieldx60, "X", "TradedDerivatives", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "1n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx62);
			ReportField reportField150 = new ReportField(reportCatalog,
					reportFieldx62, "N", "RegulatedMarketRate", new BigInteger(
							"150"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField150);
			ReportField reportField151 = new ReportField(reportCatalog,
					reportFieldx62, "N", "OTCRate", new BigInteger("151"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "General Info",
					"PERCENT", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField151);

			ReportField reportFieldx63 = new ReportField(reportCatalog,
					reportFieldx59, "X", "ClearedDerivativesRate",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"1n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx63);
			ReportField reportField152 = new ReportField(reportCatalog,
					reportFieldx63, "N", "CCPRate", new BigInteger("152"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "General Info",
					"PERCENT", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField152);
			ReportField reportField153 = new ReportField(reportCatalog,
					reportFieldx63, "N", "BilateralClearingRate",
					new BigInteger("153"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField153);

			ReportField reportFieldx64 = new ReportField(reportCatalog,
					reportFieldx59, "X", "ClearedReposRate",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"1n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx64);
			ReportField reportField154 = new ReportField(reportCatalog,
					reportFieldx64, "N", "CCPRate", new BigInteger("154"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "General Info",
					"PERCENT", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField154);
			ReportField reportField155 = new ReportField(reportCatalog,
					reportFieldx64, "N", "BilateralClearingRate",
					new BigInteger("155"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField155);
			ReportField reportField156 = new ReportField(reportCatalog,
					reportFieldx64, "N", "TriPartyRepoClearingRate",
					new BigInteger("156"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField156);

			ReportField reportFieldx65 = new ReportField(reportCatalog,
					reportFieldx59, "X", "AllCounterpartyCollateral",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"1n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx65);
			ReportField reportField157 = new ReportField(reportCatalog,
					reportFieldx65, "N", "AllCounterpartyCollateralCash",
					new BigInteger("157"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField157);
			ReportField reportField158 = new ReportField(reportCatalog,
					reportFieldx65, "N", "AllCounterpartyCollateralSecurities",
					new BigInteger("158"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField158);
			ReportField reportField159 = new ReportField(reportCatalog,
					reportFieldx65, "N",
					"AllCounterpartyOtherCollateralPosted", new BigInteger(
							"159"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField159);

			ReportField reportFieldx66 = new ReportField(reportCatalog,
					reportFieldx59, "X", "FundToCounterpartyExposures",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"1n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx66);
			ReportField reportFieldx67 = new ReportField(reportCatalog,
					reportFieldx66, "X", "FundToCounterpartyExposure",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"1n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx67);
			ReportField reportField160 = new ReportField(reportCatalog,
					reportFieldx67, "N", "Ranking", new BigInteger("160"),
					"[0-9]{1}", "", "General Info", "FiveRankingType",
					"1.1.18.1", "55", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField160);
			ReportField reportField161 = new ReportField(reportCatalog,
					reportFieldx67, "B", "CounterpartyExposureFlag",
					new BigInteger("161"), "true|false", "", "General Info",
					"BOOLEAN", "1.1.10", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField161);
			ReportField reportFieldx68 = new ReportField(reportCatalog,
					reportFieldx67, "X", "CounterpartyIdentification",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"1n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx68);
			ReportField reportField162 = new ReportField(reportCatalog,
					reportFieldx68, "Z", "EntityName", new BigInteger("162"),
					".{0,300}", "", "General Info", "NAME", "1.1.18.1", "05",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField162);
			ReportField reportField163 = new ReportField(reportCatalog,
					reportFieldx68, "A", "EntityIdentificationLEI",
					new BigInteger("163"), ".{20}", "", "General Info",
					"LEICodeType", "1.1.18.1", "05", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField163);
			ReportField reportField164 = new ReportField(reportCatalog,
					reportFieldx68, "A", "EntityIdentificationBIC",
					new BigInteger("164"), ".{11}", "", "General Info",
					"BICCodeType", "1.1.18.1", "05", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField164);
			ReportField reportField165 = new ReportField(reportCatalog,
					reportFieldx67, "N", "CounterpartyTotalExposureRate",
					new BigInteger("165"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "05", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField165);

			ReportField reportFieldx69 = new ReportField(reportCatalog,
					reportFieldx59, "X", "CounterpartyToFundExposures",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"1n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx69);
			ReportField reportFieldx70 = new ReportField(reportCatalog,
					reportFieldx69, "X", "CounterpartyToFundExposure",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"1n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx70);
			ReportField reportField166 = new ReportField(reportCatalog,
					reportFieldx70, "N", "Ranking", new BigInteger("166"),
					"[0-9]{1}", "", "General Info", "FiveRankingType",
					"1.1.18.1", "55", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField166);
			ReportField reportField167 = new ReportField(reportCatalog,
					reportFieldx70, "B", "CounterpartyExposureFlag",
					new BigInteger("167"), "true|false", "", "General Info",
					"BOOLEAN", "1.1.10", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField167);
			ReportField reportFieldx71 = new ReportField(reportCatalog,
					reportFieldx70, "X", "CounterpartyIdentification",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"1n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx71);
			ReportField reportField168 = new ReportField(reportCatalog,
					reportFieldx71, "Z", "EntityName", new BigInteger("168"),
					".{0,300}", "", "General Info", "NAME", "1.1.18.1", "05",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField168);
			ReportField reportField169 = new ReportField(reportCatalog,
					reportFieldx71, "A", "EntityIdentificationLEI",
					new BigInteger("169"), ".{20}", "", "General Info",
					"LEICodeType", "1.1.18.1", "05", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField169);
			ReportField reportField170 = new ReportField(reportCatalog,
					reportFieldx71, "A", "EntityIdentificationBIC",
					new BigInteger("170"), ".{11}", "", "General Info",
					"BICCodeType", "1.1.18.1", "05", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField170);
			ReportField reportField171 = new ReportField(reportCatalog,
					reportFieldx70, "N", "CounterpartyTotalExposureRate",
					new BigInteger("171"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "05", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField171);
			ReportField reportField172 = new ReportField(reportCatalog,
					reportFieldx59, "B", "CounterpartyTotalExposureRate",
					new BigInteger("172"), "true|false", "", "General Info",
					"BOOLEAN", "1.1.10", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField172);

			ReportField reportFieldx72 = new ReportField(reportCatalog,
					reportFieldx59, "X", "CCPExposures", new BigInteger("0"),
					null, "", null, null, "1.1.18.1", "1n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx72);
			ReportField reportFieldx73 = new ReportField(reportCatalog,
					reportFieldx72, "X", "CCPExposure", new BigInteger("0"),
					null, "", null, null, "1.1.18.1", "1n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx73);
			ReportField reportField173 = new ReportField(reportCatalog,
					reportFieldx73, "N", "Ranking", new BigInteger("173"),
					"[0-9]{1}", "", "General Info", "ThreeRankingType",
					"1.1.18.1", "33", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField173);
			ReportField reportFieldx74 = new ReportField(reportCatalog,
					reportFieldx73, "X", "CCPIdentification", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "1n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx74);
			ReportField reportField174 = new ReportField(reportCatalog,
					reportFieldx74, "Z", "EntityName", new BigInteger("174"),
					".{0,300}", "", "General Info", "NAME", "1.1.18.1", "03",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField174);
			ReportField reportField175 = new ReportField(reportCatalog,
					reportFieldx74, "A", "EntityIdentificationLEI",
					new BigInteger("175"), ".{20}", "", "General Info",
					"LEICodeType", "1.1.18.1", "03", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField175);
			ReportField reportField176 = new ReportField(reportCatalog,
					reportFieldx74, "A", "EntityIdentificationBIC",
					new BigInteger("176"), ".{11}", "", "General Info",
					"BICCodeType", "1.1.18.1", "03", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField176);
			ReportField reportField177 = new ReportField(reportCatalog,
					reportFieldx73, "N", "CCPExposureValue", new BigInteger(
							"177"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "03", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField177);

			ReportField reportFieldx75 = new ReportField(reportCatalog,
					reportFieldx52, "X", "LiquidityRiskProfile",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx75);

			ReportField reportFieldx76 = new ReportField(reportCatalog,
					reportFieldx75, "X", "PortfolioLiquidityProfile",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx76);
			ReportField reportField178 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays0to1Rate",
					new BigInteger("178"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField178);
			ReportField reportField179 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays2to7Rate",
					new BigInteger("179"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField179);
			ReportField reportField180 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays8to30Rate",
					new BigInteger("180"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField180);
			ReportField reportField181 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays31to90Rate",
					new BigInteger("181"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField181);
			ReportField reportField182 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays91to180Rate",
					new BigInteger("182"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField182);
			ReportField reportField183 = new ReportField(reportCatalog,
					reportFieldx76, "N",
					"PortfolioLiquidityInDays181to365Rate", new BigInteger(
							"183"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField183);
			ReportField reportField184 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays365MoreRate",
					new BigInteger("184"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField184);
			ReportField reportField185 = new ReportField(reportCatalog,
					reportFieldx76, "N", "UnencumberedCash", new BigInteger(
							"185"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField185);

			ReportField reportFieldx77 = new ReportField(reportCatalog,
					reportFieldx75, "X", "InvestorLiquidityProfile",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx77);
			ReportField reportField186 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays0to1Rate",
					new BigInteger("186"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField186);
			ReportField reportField187 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays2to7Rate",
					new BigInteger("187"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField187);
			ReportField reportField188 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays8to30Rate",
					new BigInteger("188"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField188);
			ReportField reportField189 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays31to90Rate",
					new BigInteger("189"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField189);
			ReportField reportField190 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays91to180Rate",
					new BigInteger("190"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField190);
			ReportField reportField191 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays181to365Rate",
					new BigInteger("191"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField191);
			ReportField reportField192 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays365MoreRate",
					new BigInteger("192"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField192);

			ReportField reportFieldx78 = new ReportField(reportCatalog,
					reportFieldx75, "X", "InvestorRedemption", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx78);
			ReportField reportField193 = new ReportField(reportCatalog,
					reportFieldx78, "B", "ProvideWithdrawalRightsFlag",
					new BigInteger("193"), "true|false", "", "General Info",
					"BOOLEAN", "1.1.10", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField193);
			ReportField reportField194 = new ReportField(reportCatalog,
					reportFieldx78, "A", "InvestorRedemptionFrequency",
					new BigInteger("194"), ".{1}", "", "General Info",
					"InvestorRedemptionFrequencyType", "1.1.18.1", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField194);
			ReportField reportField195 = new ReportField(reportCatalog,
					reportFieldx78, "N", "InvestorRedemptionNoticePeriod",
					new BigInteger("195"), "[0-9]{0,4}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField195);
			ReportField reportField196 = new ReportField(reportCatalog,
					reportFieldx78, "N", "InvestorRedemptionLockUpPeriod",
					new BigInteger("196"), "[0-9]{0,4}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField196);

			ReportField reportFieldx79 = new ReportField(reportCatalog,
					reportFieldx75, "X", "InvestorArrangement", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx79);

			ReportField reportFieldx80 = new ReportField(reportCatalog,
					reportFieldx79, "X", "InvestorIlliquidAssetArrangement",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx80);
			ReportField reportField197 = new ReportField(reportCatalog,
					reportFieldx80, "N", "SidePocketRate",
					new BigInteger("197"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField197);
			ReportField reportField198 = new ReportField(reportCatalog,
					reportFieldx80, "N", "GatesRate", new BigInteger("198"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "General Info",
					"PERCENT", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField198);
			ReportField reportField199 = new ReportField(reportCatalog,
					reportFieldx80, "N", "DealingSuspensionRate",
					new BigInteger("199"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField199);
			ReportField reportFieldx81 = new ReportField(reportCatalog,
					reportFieldx80, "X", "OtherArrangement",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx81);
			ReportField reportField200 = new ReportField(reportCatalog,
					reportFieldx81, "Z", "OtherArrangementType",
					new BigInteger("200"), ".{0,300}", "", "General info",
					"DESCRIPTION", "1.1.11.1.2", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField200);
			ReportField reportField201 = new ReportField(reportCatalog,
					reportFieldx81, "N", "OtherArrangementRate",
					new BigInteger("201"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField201);
			ReportField reportField202 = new ReportField(reportCatalog,
					reportFieldx80, "N", "TotalArrangementRate",
					new BigInteger("202"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField202);

			ReportField reportFieldx82 = new ReportField(reportCatalog,
					reportFieldx79, "X", "InvestorPreferentialTreatment",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx82);
			ReportField reportField203 = new ReportField(reportCatalog,
					reportFieldx82, "B", "InvestorPreferentialTreatmentFlag",
					new BigInteger("203"), "true|false", "", "General Info",
					"BOOLEAN", "1.1.10", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField203);
			ReportField reportField204 = new ReportField(reportCatalog,
					reportFieldx82, "B",
					"DisclosureTermsPreferentialTreatmentFlag", new BigInteger(
							"204"), "true|false", "", "General Info",
					"BOOLEAN", "1.1.10", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField204);
			ReportField reportField205 = new ReportField(reportCatalog,
					reportFieldx82, "B",
					"LiquidityTermsPreferentialTreatmentFlag", new BigInteger(
							"205"), "true|false", "", "General Info",
					"BOOLEAN", "1.1.10", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField205);
			ReportField reportField206 = new ReportField(reportCatalog,
					reportFieldx82, "B", "FeeTermsPreferentialTreatmentFlag",
					new BigInteger("206"), "true|false", "", "General Info",
					"BOOLEAN", "1.1.10", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField206);
			ReportField reportField207 = new ReportField(reportCatalog,
					reportFieldx82, "B", "OtherTermsPreferentialTreatmentFlag",
					new BigInteger("207"), "true|false", "", "General Info",
					"BOOLEAN", "1.1.10", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField207);

			ReportField reportFieldx83 = new ReportField(reportCatalog,
					reportFieldx75, "X", "InvestorGroups", new BigInteger("0"),
					null, "", null, null, "1.1.18.1", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx83);
			ReportField reportFieldx84 = new ReportField(reportCatalog,
					reportFieldx83, "X", "InvestorGroup", new BigInteger("0"),
					null, "", null, null, "1.1.18.1", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx84);
			ReportField reportField208 = new ReportField(reportCatalog,
					reportFieldx84, "A", "InvestorGroupType", new BigInteger(
							"208"), ".{4}", "", "General Info",
					"InvestorGroupTypeType", "1.1.18.3.1.2", "1n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField208);
			ReportField reportField209 = new ReportField(reportCatalog,
					reportFieldx84, "N", "InvestorGroupRate", new BigInteger(
							"209"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "1n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField209);

			ReportField reportFieldx85 = new ReportField(reportCatalog,
					reportFieldx75, "X", "FinancingLiquidityProfile",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx85);
			ReportField reportField210 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingAmount",
					new BigInteger("210"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField210);
			ReportField reportField211 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays0to1Rate",
					new BigInteger("211"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField211);
			ReportField reportField212 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays2to7Rate",
					new BigInteger("212"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField212);
			ReportField reportField213 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays8to30Rate",
					new BigInteger("213"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField213);
			ReportField reportField214 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays31to90Rate",
					new BigInteger("214"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField214);
			ReportField reportField215 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays91to180Rate",
					new BigInteger("215"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField215);
			ReportField reportField216 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays181to365Rate",
					new BigInteger("216"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField216);
			ReportField reportField217 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays365MoreRate",
					new BigInteger("217"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"General Info", "DECIMAL", "1.1.18.6.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField217);

			ReportField reportFieldx86 = new ReportField(reportCatalog,
					reportFieldx52, "X", "OperationalRisk",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx86);
			ReportField reportField218 = new ReportField(reportCatalog,
					reportFieldx86, "N", "TotalOpenPositions", new BigInteger(
							"218"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField218);
			ReportField reportFieldx87 = new ReportField(reportCatalog,
					reportFieldx86, "X", "HistoricalRiskProfile",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx87);

			ReportField reportFieldx88 = new ReportField(reportCatalog,
					reportFieldx87, "X", "GrossInvestmentReturnsRate",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx88);
			ReportField reportField219 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateJanuary", new BigInteger("219"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField219);
			ReportField reportField220 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateFebruary", new BigInteger("220"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField220);
			ReportField reportField221 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateMarch", new BigInteger("221"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField221);
			ReportField reportField222 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateApril", new BigInteger("222"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField222);
			ReportField reportField223 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateMay", new BigInteger("223"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField223);
			ReportField reportField224 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateJune", new BigInteger("224"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField224);
			ReportField reportField225 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateJuly", new BigInteger("225"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField225);
			ReportField reportField226 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateAugust", new BigInteger("226"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField226);
			ReportField reportField227 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateSeptember",
					new BigInteger("227"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField227);
			ReportField reportField228 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateOctober", new BigInteger("228"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField228);
			ReportField reportField229 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateNovember", new BigInteger("229"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField229);
			ReportField reportField230 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateDecember", new BigInteger("230"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField230);

			ReportField reportFieldx89 = new ReportField(reportCatalog,
					reportFieldx87, "X", "NetInvestmentReturnsRate",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx89);
			ReportField reportField231 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateJanuary", new BigInteger("231"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField231);
			ReportField reportField232 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateFebruary", new BigInteger("232"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField232);
			ReportField reportField233 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateMarch", new BigInteger("233"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField233);
			ReportField reportField234 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateApril", new BigInteger("234"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField234);
			ReportField reportField235 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateMay", new BigInteger("235"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField235);
			ReportField reportField236 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateJune", new BigInteger("236"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField236);
			ReportField reportField237 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateJuly", new BigInteger("237"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField237);
			ReportField reportField238 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateAugust", new BigInteger("238"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField238);
			ReportField reportField239 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateSeptember",
					new BigInteger("239"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField239);
			ReportField reportField240 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateOctober", new BigInteger("240"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField240);
			ReportField reportField241 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateNovember", new BigInteger("241"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField241);
			ReportField reportField242 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateDecember", new BigInteger("242"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField242);

			ReportField reportFieldx90 = new ReportField(reportCatalog,
					reportFieldx87, "X", "NAVChangeRate", new BigInteger("0"),
					null, "", null, null, "1.1.18.1", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx90);
			ReportField reportField243 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateJanuary", new BigInteger("243"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField243);
			ReportField reportField244 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateFebruary", new BigInteger("244"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField244);
			ReportField reportField245 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateMarch", new BigInteger("245"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField245);
			ReportField reportField246 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateApril", new BigInteger("246"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField246);
			ReportField reportField247 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateMay", new BigInteger("247"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField247);
			ReportField reportField248 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateJune", new BigInteger("248"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField248);
			ReportField reportField249 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateJuly", new BigInteger("249"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField249);
			ReportField reportField250 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateAugust", new BigInteger("250"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField250);
			ReportField reportField251 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateSeptember",
					new BigInteger("251"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField251);
			ReportField reportField252 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateOctober", new BigInteger("252"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField252);
			ReportField reportField253 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateNovember", new BigInteger("253"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField253);
			ReportField reportField254 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateDecember", new BigInteger("254"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "", "General Info",
					"DECIMAL", "1.1.18.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField254);

			ReportField reportFieldx91 = new ReportField(reportCatalog,
					reportFieldx87, "X", "Subscription", new BigInteger("0"),
					null, "", null, null, "1.1.18.1", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx91);
			ReportField reportField255 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityJanuary", new BigInteger(
							"255"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField255);
			ReportField reportField256 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityFebruary", new BigInteger(
							"256"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField256);
			ReportField reportField257 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityMarch",
					new BigInteger("257"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField257);
			ReportField reportField258 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityApril",
					new BigInteger("258"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField258);
			ReportField reportField259 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityMay", new BigInteger("259"),
					"[0-9]{0,15}?", "", "General Info", "NUMBER",
					"1.1.18.2.1.3", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField259);
			ReportField reportField260 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityJune", new BigInteger("260"),
					"[0-9]{0,15}?", "", "General Info", "NUMBER",
					"1.1.18.2.1.3", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField260);
			ReportField reportField261 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityJuly", new BigInteger("261"),
					"[0-9]{0,15}?", "", "General Info", "NUMBER",
					"1.1.18.2.1.3", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField261);
			ReportField reportField262 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityAugust",
					new BigInteger("262"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField262);
			ReportField reportField263 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantitySeptember", new BigInteger(
							"263"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField263);
			ReportField reportField264 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityOctober", new BigInteger(
							"264"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField264);
			ReportField reportField265 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityNovember", new BigInteger(
							"265"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField265);
			ReportField reportField266 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityDecember", new BigInteger(
							"266"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField266);

			ReportField reportFieldx92 = new ReportField(reportCatalog,
					reportFieldx87, "X", "Redemption", new BigInteger("0"),
					null, "", null, null, "1.1.18.1", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx92);
			ReportField reportField267 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityJanuary", new BigInteger(
							"267"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField267);
			ReportField reportField268 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityFebruary", new BigInteger(
							"268"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField268);
			ReportField reportField269 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityMarch",
					new BigInteger("269"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField269);
			ReportField reportField270 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityApril",
					new BigInteger("270"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField270);
			ReportField reportField271 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityMay", new BigInteger("271"),
					"[0-9]{0,15}?", "", "General Info", "NUMBER",
					"1.1.18.2.1.3", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField271);
			ReportField reportField272 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityJune", new BigInteger("272"),
					"[0-9]{0,15}?", "", "General Info", "NUMBER",
					"1.1.18.2.1.3", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField272);
			ReportField reportField273 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityJuly", new BigInteger("273"),
					"[0-9]{0,15}?", "", "General Info", "NUMBER",
					"1.1.18.2.1.3", "0n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField273);
			ReportField reportField274 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityAugust",
					new BigInteger("274"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField274);
			ReportField reportField275 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantitySeptember", new BigInteger(
							"275"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField275);
			ReportField reportField276 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityOctober", new BigInteger(
							"276"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField276);
			ReportField reportField277 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityNovember", new BigInteger(
							"277"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField277);
			ReportField reportField278 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityDecember", new BigInteger(
							"278"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField278);

			ReportField reportFieldx93 = new ReportField(reportCatalog,
					reportFieldx41, "X", "StressTests", new BigInteger("0"),
					null, "", null, null, "1.1.18.1", "11", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx93);
			ReportField reportField279 = new ReportField(reportCatalog,
					reportFieldx93, "Z", "StressTestsResultArticle15",
					new BigInteger("279"), ".{0,32000}", "", "General Info",
					"StressTestsResultType", "1.1.14", "11", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField279);
			ReportField reportField280 = new ReportField(reportCatalog,
					reportFieldx93, "Z", "StressTestsResultArticle16",
					new BigInteger("280"), ".{0,32000}", "", "General Info",
					"StressTestsResultType", "1.1.14", "11", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField280);

			// /////////////////////////////////////
			ReportField reportFieldx94 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFLeverageInfo", new BigInteger("0"),
					null, "", null, null, "1.1.18.1", "11", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx94);

			ReportField reportFieldx95 = new ReportField(reportCatalog,
					reportFieldx94, "X", "AIFLeverageArticle24-2",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx95);
			ReportField reportField281 = new ReportField(reportCatalog,
					reportFieldx95, "B",
					"AllCounterpartyCollateralRehypothecationFlag",
					new BigInteger("281"), "true|false", "", "General Info",
					"BOOLEAN", "1.1.10", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField281);
			ReportField reportField282 = new ReportField(reportCatalog,
					reportFieldx95, "N",
					"AllCounterpartyCollateralRehypothecatedRate",
					new BigInteger("282"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"General Info", "PERCENT", "1.1.18.1", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField282);

			ReportField reportFieldx96 = new ReportField(reportCatalog,
					reportFieldx95, "X", "SecuritiesCashBorrowing",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx96);
			ReportField reportField283 = new ReportField(reportCatalog,
					reportFieldx96, "N", "UnsecuredBorrowingAmount",
					new BigInteger("283"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField283);
			ReportField reportField284 = new ReportField(reportCatalog,
					reportFieldx96, "N",
					"SecuredBorrowingPrimeBrokerageAmount", new BigInteger(
							"284"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField284);
			ReportField reportField285 = new ReportField(reportCatalog,
					reportFieldx96, "N", "SecuredBorrowingReverseRepoAmount",
					new BigInteger("285"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField285);
			ReportField reportField286 = new ReportField(reportCatalog,
					reportFieldx96, "N", "SecuredBorrowingOtherAmount",
					new BigInteger("286"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField286);

			ReportField reportFieldx97 = new ReportField(reportCatalog,
					reportFieldx95, "X", "FinancialInstrumentBorrowing",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx97);
			ReportField reportField287 = new ReportField(reportCatalog,
					reportFieldx97, "N",
					"ExchangedTradedDerivativesExposureValue", new BigInteger(
							"287"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField287);
			ReportField reportField288 = new ReportField(reportCatalog,
					reportFieldx97, "N", "OTCDerivativesAmount",
					new BigInteger("288"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField288);

			ReportField reportField289 = new ReportField(reportCatalog,
					reportFieldx95, "N",
					"ShortPositionBorrowedSecuritiesValue", new BigInteger(
							"289"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField289);

			ReportField reportFieldx98 = new ReportField(reportCatalog,
					reportFieldx95, "X", "ControlledStructures",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx98);
			ReportField reportFieldx99 = new ReportField(reportCatalog,
					reportFieldx98, "X", "ControlledStructure", new BigInteger(
							"0"), null, "", null, null, "1.1.18.1", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx99);
			ReportField reportFieldx100 = new ReportField(reportCatalog,
					reportFieldx99, "X", "ControlledStructureIdentification",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx100);
			ReportField reportField290 = new ReportField(reportCatalog,
					reportFieldx100, "Z", "EntityName", new BigInteger("290"),
					".{0,300}", "", "General Info", "NAME", "1.1.18.1", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField290);
			ReportField reportField291 = new ReportField(reportCatalog,
					reportFieldx100, "A", "EntityIdentificationLEI",
					new BigInteger("291"), ".{20}", "", "General Info",
					"LEICodeType", "1.1.18.1", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField291);
			ReportField reportField292 = new ReportField(reportCatalog,
					reportFieldx100, "A", "EntityIdentificationBIC",
					new BigInteger("292"), ".{11}", "", "General Info",
					"BICCodeType", "1.1.18.1", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField292);
			ReportField reportField293 = new ReportField(reportCatalog,
					reportFieldx99, "N", "ControlledStructureExposureValue",
					new BigInteger("293"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField293);

			ReportField reportFieldx101 = new ReportField(reportCatalog,
					reportFieldx95, "X", "LeverageAIF", new BigInteger("0"),
					null, "", null, null, "1.1.18.1", "11", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx101);
			ReportField reportField294 = new ReportField(reportCatalog,
					reportFieldx101, "N", "GrossMethodRate", new BigInteger(
							"294"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"General Info", "DECIMAL", "1.1.18.1", "11", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField294);
			ReportField reportField295 = new ReportField(reportCatalog,
					reportFieldx101, "N", "CommitmentMethodRate",
					new BigInteger("295"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "General Info", "DECIMAL", "1.1.18.1", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField295);

			ReportField reportFieldx102 = new ReportField(reportCatalog,
					reportFieldx94, "X", "AIFLeverageArticle24-4",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx102);
			ReportField reportFieldx103 = new ReportField(reportCatalog,
					reportFieldx102, "X", "BorrowingSource",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx103);
			ReportField reportField296 = new ReportField(reportCatalog,
					reportFieldx103, "N", "Ranking", new BigInteger("296"),
					"[0-9]{1}", "", "General Info", "FiveRankingType",
					"1.1.18.1", "55", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField296);
			ReportField reportField297 = new ReportField(reportCatalog,
					reportFieldx103, "B", "BorrowingSourceFlag",
					new BigInteger("297"), "true|false", "", "General Info",
					"BOOLEAN", "1.1.10", "55", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField297);

			ReportField reportFieldx104 = new ReportField(reportCatalog,
					reportFieldx103, "X", "SourceIdentification",
					new BigInteger("0"), null, "", null, null, "1.1.18.1",
					"11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx104);
			ReportField reportField298 = new ReportField(reportCatalog,
					reportFieldx104, "Z", "EntityName", new BigInteger("298"),
					".{0,300}", "", "General Info", "NAME", "1.1.18.1", "03",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField298);
			ReportField reportField299 = new ReportField(reportCatalog,
					reportFieldx104, "A", "EntityIdentificationLEI",
					new BigInteger("299"), ".{20}", "", "General Info",
					"LEICodeType", "1.1.18.1", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField299);
			ReportField reportField300 = new ReportField(reportCatalog,
					reportFieldx104, "A", "EntityIdentificationBIC",
					new BigInteger("300"), ".{11}", "", "General Info",
					"BICCodeType", "1.1.18.1", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField300);
			ReportField reportField301 = new ReportField(reportCatalog,
					reportFieldx103, "N", "LeverageAmount", new BigInteger(
							"301"), "[0-9]{0,15}?", "", "General Info",
					"NUMBER", "1.1.18.2.1.3", "05", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField301);

			ReportField reportFieldx105 = new ReportField(reportCatalog,
					reportFieldx1, "X", "CancellationAIFRecordInfo",
					new BigInteger("0"), null, "", null, null, "1.2", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx105);
			ReportField reportField303 = new ReportField(reportCatalog,
					reportFieldx105, "Z", "CancelledAIFNationalCode",
					new BigInteger("303"), ".{0,30}", "", "Cancellation Info",
					"AIFNationalCodeType", "1.2.1", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField303);
			ReportField reportField304 = new ReportField(reportCatalog,
					reportFieldx105, "Z", "CancelledAIFMNationalCode",
					new BigInteger("303"), ".{0,30}", "", "Cancellation Info",
					"AIFMNationalCodeType", "1.2.1", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField304);
			ReportField reportField305 = new ReportField(reportCatalog,
					reportFieldx105, "A", "CancelledReportingPeriodType",
					new BigInteger("40"), ".{2}", "", "Cancellation Info",
					"ReportingPeriodTypeType", "1.2.2", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField305);
			ReportField reportField306 = new ReportField(reportCatalog,
					reportFieldx105, "D", "CancelledReportingPeriodYear",
					new BigInteger("41"), "[0-9]{4}?", "", "Cancellation Info",
					"YEAR", "1.2.3", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField306);
			ReportField reportField307 = new ReportField(reportCatalog,
					reportFieldx105, "A", "CancelledRecordFlag",
					new BigInteger("42"), ".{1}", "", "Cancellation Info",
					"CancelledRecordFlagType", "1.2.4", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField307);

			// /////////////////////////////////////////////////////////////////
			// DAO

			// CompanyDAO companyDAO = (CompanyDAO) applicationContext
			// .getBean("companyDAO");
			// companyDAO.create(company);
			//
			// DepartmentDAO departmentDAO = (DepartmentDAO) applicationContext
			// .getBean("departmentDAO");
			// departmentDAO.create(department);
			//
			// FundDAO fundDAO = (FundDAO)
			// applicationContext.getBean("fundDAO");
			// fundDAO.create(fund1);
			//
			// FundGroupDAO fundGroupDAO = (FundGroupDAO) applicationContext
			// .getBean("fundGroupDAO");
			// fundGroupDAO.create(fundGroup);
			//
			// ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO)
			// applicationContext
			// .getBean("reportExecutionDAO");
			// reportExecutionDAO.create(reportExecution);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Process to install in database reportCatalog and reportFields from AIFM
	 * report, also an example of load file.
	 * 
	 * @param applicationContext
	 */
	public void installEntitiesFull(ApplicationContext applicationContext) {

		try {

			VersionAuditor versionAdmin = new VersionAuditor("admin");

			String versionField = "1.2";

			Company company = new Company("Santander Asset Manager", "Spain",
					"SAM", "", versionAdmin);

			Department department = new Department(company, "Risk department",
					"RISK", "", "Spain", new VersionAuditor(
							"admin"));

			Fund fund1 = new Fund(company, "SAM fund 1", "ES000001", "FUND1",
					"", null, versionAdmin);

			Fund fund2 = new Fund(company, "SAM fund 2", "ES000002", "FUND2",
					"", null, versionAdmin);

			FundGroup fundGroup = new FundGroup(fund1, department,
					"RISK FUNDS", "", versionAdmin);

			ReportCatalog reportCatalog = new ReportCatalog(versionField,
					"COMPANY", "AIFM 2014", "", null, null, null, versionAdmin);

			String str1 = "2014-01-01";
			String str2 = "2014-12-31";
			DateFormat format = new SimpleDateFormat(
					ReportUtilities.datePattern);
			Date date1 = format.parse(str1);
			Date date2 = format.parse(str2);

			ReportExecution reportExecution = new ReportExecution(
					reportCatalog, company, null, "Prueba Q1 2014", "", "Q1",
					"2014", date2, date1,
					ReportExecutionStatusEnum.CREATION
							.getReportExecutionStatus(), null, null, null,
					null, null, null, null, null, null, null, versionAdmin);

			// report Fields de AIFM

			ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
					.getBean("reportFieldDAO");

			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			reportCatalogDAO.create(reportCatalog);

			ReportField reportFieldx1 = new ReportField(reportCatalog, null,
					"X", "AIFMReportingInfo", new BigInteger("0"), null, "",
					null, null, "1.", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportFieldx1);
			ReportField reportField1 = new ReportField(reportCatalog,
					reportFieldx1, "A", "ReportingMemberState", new BigInteger(
							"1"), ".{2}", "", "General Info",
					"CountryCodeType", "1.01", "11", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField1);
			ReportField reportField2 = new ReportField(reportCatalog,
					reportFieldx1, "A", "Version", new BigInteger("2"),
					"([0-9])+\\.([0-9])+", "", "General Info", "VERSION",
					"1.02", "11", versionField, null, null, null, versionAdmin);
			reportField2.setReportFieldEditable(true);
			reportFieldDAO.create(reportField2);
			ReportField reportField3 = new ReportField(reportCatalog,
					reportFieldx1, "D", "CreationDateAndTime", new BigInteger(
							"3"),
					"[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}",
					"", "General Info", "DATETIME", "1.03", "11", versionField,
					null, null, null, versionAdmin);
			reportField3.setReportFieldEditable(true);
			reportFieldDAO.create(reportField3);

			ReportField reportFieldx2 = new ReportField(reportCatalog,
					reportFieldx1, "X", "AIFMRecordInfo", new BigInteger("0"),
					null, "", null, null, "1.", "11", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportFieldx2);
			ReportField reportField4 = new ReportField(reportCatalog,
					reportFieldx2, "A", "FilingType", new BigInteger("4"),
					".{4}", "", "General Info", "FilingTypeType", "1.04", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField4);
			ReportField reportField5 = new ReportField(reportCatalog,
					reportFieldx2, "N", "AIFMContentType", new BigInteger("5"),
					"[0-9]{1}", "", "General Info", "AIFMContentTypeType",
					"1.05", "11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField5);
			ReportField reportField6 = new ReportField(reportCatalog,
					reportFieldx2, "D", "ReportingPeriodStartDate",
					new BigInteger("6"), "[0-9]{4}-[0-9]{2}-[0-9]{2}", "",
					"General Info", "DATE", "1.06", "11", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField6);
			ReportField reportField7 = new ReportField(reportCatalog,
					reportFieldx2, "D", "ReportingPeriodEndDate",
					new BigInteger("7"), "[0-9]{4}-[0-9]{2}-[0-9]{2}", "",
					"General Info", "DATE", "1.07", "11", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField7);
			ReportField reportField8 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ReportingPeriodType", new BigInteger(
							"8"), ".{2}", "", "General Info",
					"ReportingPeriodTypeType", "1.08", "11", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField8);
			ReportField reportField9 = new ReportField(reportCatalog,
					reportFieldx2, "N", "ReportingPeriodYear", new BigInteger(
							"9"), "[0-9]{4}", "", "General Info", "YEAR",
					"1.09", "11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField9);
			ReportField reportField10 = new ReportField(reportCatalog,
					reportFieldx2, "A",
					"AIFMReportingObligationChangeFrequencyCode",
					new BigInteger("10"), ".{2}", "", "General Info",
					"ReportingObligationChangeFrequencyCodeType", "1.10", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField10);
			ReportField reportField11 = new ReportField(reportCatalog,
					reportFieldx2, "N",
					"AIFMReportingObligationChangeContentsCode",
					new BigInteger("11"), "[0-9]{1}", "", "General Info",
					"AIFMReportingObligationChangeContentsCodeType", "1.11",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField11);
			ReportField reportField12 = new ReportField(reportCatalog,
					reportFieldx2, "A", "AIFMReportingObligationChangeQuarter",
					new BigInteger("12"), ".{2}", "", "General Info",
					"ReportingObligationChangeQuarterType", "1.12", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField12);
			ReportField reportField13 = new ReportField(reportCatalog,
					reportFieldx2, "B", "LastReportingFlag", new BigInteger(
							"13"), "true|false", "", "General Info", "BOOLEAN",
					"1.13", "11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField13);

			ReportField reportFieldx3 = new ReportField(reportCatalog,
					reportFieldx2, "X", "Assumptions", new BigInteger("0"),
					null, "", null, null, "2.", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportFieldx3);
			ReportField reportFieldx4 = new ReportField(reportCatalog,
					reportFieldx3, "X", "Assumption", new BigInteger("0"),
					null, "", null, null, "2.", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportFieldx4);
			ReportField reportField14 = new ReportField(reportCatalog,
					reportFieldx4, "N", "QuestionNumber", new BigInteger("14"),
					"[0-9]{0,3}", "", "Assumptions", "QUESTION", "2.14", "0n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField14);
			ReportField reportField15 = new ReportField(reportCatalog,
					reportFieldx4, "Z", "AssumptionDescription",
					new BigInteger("15"), ".{0,300}", "", "Assumptions",
					"DESCRIPTION", "2.15", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField15);

			ReportField reportField16 = new ReportField(reportCatalog,
					reportFieldx2, "A", "AIFMReportingCode", new BigInteger(
							"16"), ".{1}", "", "General Info",
					"AIFMReportingCodeType", "1.16", "11", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField16);
			ReportField reportField17 = new ReportField(reportCatalog,
					reportFieldx2, "A", "AIFMJurisdiction",
					new BigInteger("17"), ".{2}", "", "General Info",
					"CountryCodeType", "1.17", "11", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField17);
			ReportField reportField18 = new ReportField(reportCatalog,
					reportFieldx2, "Z", "AIFMNationalCode",
					new BigInteger("18"), ".{0,30}", "", "General Info",
					"AIFMNationalCodeType", "1.18", "11", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField18);
			ReportField reportField19 = new ReportField(reportCatalog,
					reportFieldx2, "Z", "AIFMName", new BigInteger("19"),
					".{0,300}", "", "General Info", "AIFM_NAME", "1.19", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField19);
			ReportField reportField20 = new ReportField(reportCatalog,
					reportFieldx2, "B", "AIFMEEAFlag", new BigInteger("20"),
					"true|false", "", "General Info", "BOOLEAN", "1.20", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField20);
			ReportField reportField21 = new ReportField(reportCatalog,
					reportFieldx2, "B", "AIFMNoReportingFlag", new BigInteger(
							"21"), "true|false", "", "General Info", "BOOLEAN",
					"1.21", "11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField21);

			ReportField reportFieldx5 = new ReportField(reportCatalog,
					reportFieldx2, "X", "AIFMCompleteDescription",
					new BigInteger("0"), null, "", null, null, "1.", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx5);
			ReportField reportFieldx6 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFMIdentifier", new BigInteger("0"),
					null, "", null, null, "3.", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportFieldx6);
			ReportField reportField22 = new ReportField(reportCatalog,
					reportFieldx6, "A", "AIFMIdentifierLEI", new BigInteger(
							"22"), ".{20}", "", "Complete Description",
					"LEICodeType", "3.22", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField22);
			ReportField reportField23 = new ReportField(reportCatalog,
					reportFieldx6, "A", "AIFMIdentifierBIC", new BigInteger(
							"23"), ".{11}", "", "Complete Description",
					"BICCodeType", "3.23", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField23);
			ReportField reportField24 = new ReportField(reportCatalog,
					reportFieldx6, "A", "ReportingMemberState", new BigInteger(
							"24"), ".{2}", "", "General Info",
					"CountryCodeType", "1.24", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField24);
			ReportField reportField25 = new ReportField(reportCatalog,
					reportFieldx6, "Z", "AIFMNationalCode",
					new BigInteger("25"), ".{0,30}", "", "General Info",
					"AIFMNationalCodeType", "1.25", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField25);

			ReportField reportFieldx7 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFMPrincipalMarkets", new BigInteger(
							"0"), null, "", null, null, "4.", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx7);
			ReportField reportFieldx8 = new ReportField(reportCatalog,
					reportFieldx7, "X", "AIFMFivePrincipalMarket",
					new BigInteger("0"), null, "", null, null, "4.", "15",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx8);
			ReportField reportField26 = new ReportField(reportCatalog,
					reportFieldx8, "N", "Ranking", new BigInteger("26"),
					"[0-9]{1}", "", "Principal Markets", "FiveRankingType",
					"4.26", "15", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField26);
			ReportField reportFieldx9 = new ReportField(reportCatalog,
					reportFieldx8, "X", "MarketIdentification", new BigInteger(
							"0"), null, "", null, null, "4.", "15",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx9);
			ReportField reportField27 = new ReportField(reportCatalog,
					reportFieldx9, "A", "MarketCodeType", new BigInteger("27"),
					".{3,4}", "", "Principal Markets",
					"MarketCodeTypeWithNOTType", "4.27", "15", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField27);
			ReportField reportField28 = new ReportField(reportCatalog,
					reportFieldx9, "A", "MarketCode", new BigInteger("28"),
					".{3,4}", "", "Principal Markets", "MICCodeType", "4.28",
					"05", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField28);
			ReportField reportField29 = new ReportField(reportCatalog,
					reportFieldx8, "N", "AggregatedValueAmount",
					new BigInteger("29"), "[0-9]{0,15}?", "",
					"Principal Markets", "NUMBER", "4.29", "15", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField29);

			ReportField reportFieldx10 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFMPrincipalInstruments",
					new BigInteger("0"), null, "", null, null, "5.", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx10);
			ReportField reportFieldx11 = new ReportField(reportCatalog,
					reportFieldx10, "X", "AIFMPrincipalInstrument",
					new BigInteger("0"), null, "", null, null, "5.", "15",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx11);
			ReportField reportField30 = new ReportField(reportCatalog,
					reportFieldx11, "N", "Ranking", new BigInteger("30"),
					"[0-9]{1}", "", "Principal Instruments", "FiveRankingType",
					"5.30", "15", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField30);
			ReportField reportField31 = new ReportField(reportCatalog,
					reportFieldx11, "A", "SubAssetType", new BigInteger("31"),
					".{1,12}", "", "Principal Instruments", "SubAssetTypeType",
					"5.31", "15", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField31);
			ReportField reportField32 = new ReportField(reportCatalog,
					reportFieldx11, "N", "AggregatedValueAmount",
					new BigInteger("32"), "[0-9]{0,15}?", "",
					"Principal Instruments", "NUMBER", "5.32", "15",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField32);

			ReportField reportField33 = new ReportField(reportCatalog,
					reportFieldx5, "N", "AUMAmountInEuro",
					new BigInteger("33"), "[0-9]{0,15}?", "",
					"Complete Description", "NUMBER", "3.33", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField33);
			ReportField reportField34 = new ReportField(reportCatalog,
					reportFieldx5, "N", "AUMAmountInBaseCurrency",
					new BigInteger("34"), "[0-9]{0,15}?", "",
					"Complete Description", "NUMBER", "3.34", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField34);

			ReportField reportFieldx12 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFMBaseCurrencyDescription",
					new BigInteger("0"), null, "", null, null, "1.", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx12);
			ReportField reportField35 = new ReportField(reportCatalog,
					reportFieldx12, "A", "BaseCurrency", new BigInteger("35"),
					".{3}", "", "Complete Description", "CurrencyCodeType",
					"3.35", "01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField35);
			ReportField reportField36 = new ReportField(reportCatalog,
					reportFieldx12, "A", "FXEURReferenceRateType",
					new BigInteger("36"), ".{3}", "", "Complete Description",
					"FXEURReferenceRateTypeType", "3.36", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField36);
			ReportField reportField37 = new ReportField(reportCatalog,
					reportFieldx12, "N", "FXEURRate", new BigInteger("37"),
					"[0-9]{1,15}(\\.[0-9]{1,4})?", "", "Complete Description",
					"DECIMAL", "3.37", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField37);
			ReportField reportField38 = new ReportField(reportCatalog,
					reportFieldx12, "Z", "FXEUROtherReferenceRateDescription",
					new BigInteger("38"), ".{0,300}", "",
					"Complete Description", "DESCRIPTION", "3.38", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField38);

			ReportField reportFieldx13 = new ReportField(reportCatalog,
					reportFieldx1, "X", "CancellationAIFMRecordInfo",
					new BigInteger("0"), null, "", null, null, "6.", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx13);
			ReportField reportField39 = new ReportField(reportCatalog,
					reportFieldx13, "Z", "CancelledAIFMNationalCode",
					new BigInteger("39"), ".{0,30}", "", "Cancellation Info",
					"AIFMNationalCodeType", "6.39", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField39);
			ReportField reportField40 = new ReportField(reportCatalog,
					reportFieldx13, "A", "CancelledReportingPeriodType",
					new BigInteger("40"), ".{2}", "", "Cancellation Info",
					"ReportingPeriodTypeType", "6.40", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField40);
			ReportField reportField41 = new ReportField(reportCatalog,
					reportFieldx13, "D", "CancelledReportingPeriodYear",
					new BigInteger("41"), "[0-9]{4}?", "", "Cancellation Info",
					"YEAR", "6.41", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField41);
			ReportField reportField42 = new ReportField(reportCatalog,
					reportFieldx13, "A", "CancelledRecordFlag", new BigInteger(
							"42"), ".{1}", "", "Cancellation Info",
					"CancelledRecordFlagType", "6.42", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField42);

			// report Field List
			this.installFileList(applicationContext);

			FileConfig fileConfig0 = new FileConfig(department, "AIFM",
					"AIFM2014_QUESTIONS", ";", "SIMPLE", "*", null, true, null,
					null, versionAdmin);

			FileColum fileColum01 = new FileColum(reportField14, fileConfig0,
					"type", new BigDecimal(0), "QuestionNumber", "", "format",
					null, null, versionAdmin);
			fileColum01.setColumBlock(ReportUtilities.fileColumBlockRepeatable);
			FileColum fileColum02 = new FileColum(reportField15, fileConfig0,
					"type", new BigDecimal(1), "AssumptionDescription", "",
					"format", null, null, versionAdmin);
			fileColum02.setColumBlock(ReportUtilities.fileColumBlockRepeatable);

			FileConfig fileConfig = new FileConfig(department, "AIFM",
					"AIFM2014_GENERAL", ";", "SIMPLE", "*", null, true, null,
					null, versionAdmin);

			// FileConfig fileConfig = new FileConfig(department, "AIFM",
			// "AIFM2014", ";", "SIMPLE", "*", null, false, null, null,
			// versionAdmin);
			// FileColum fileColum0 = new FileColum(null, fileConfig, "type",
			// new BigDecimal(0), "type", "", "format", null, null,
			// versionAdmin);
			// FileColum fileColum1 = new FileColum(null, fileConfig, "type",
			// new BigDecimal(1), "ID_GESTORA", "", "format", null, null,
			// versionAdmin);
			// FileColum fileColum2 = new FileColum(null, fileConfig, "type",
			// new BigDecimal(2), "DATE_REPORT", "", "format", null, null,
			// versionAdmin);
			// FileColum fileColum3 = new FileColum(reportField1, fileConfig,
			// "type", new BigDecimal(3), "ReportingMemberState", "",
			// "format", null, null, versionAdmin);
			// FileColum fileColum4 = new FileColum(reportField4, fileConfig,
			// "type", new BigDecimal(4), "FilingType", "", "format",
			// null, null, versionAdmin);
			// FileColum fileColum5 = new FileColum(reportField5, fileConfig,
			// "type", new BigDecimal(5), "AIFMContentType", "", "format",
			// null, null, versionAdmin);
			// FileColum fileColum6 = new FileColum(reportField6, fileConfig,
			// "type", new BigDecimal(6), "ReportingPeriodStartDate", "",
			// "format", null, null, versionAdmin);
			// FileColum fileColum7 = new FileColum(reportField7, fileConfig,
			// "type", new BigDecimal(7), "ReportingPeriodEndDate", "",
			// "format", null, null, versionAdmin);
			// FileColum fileColum8 = new FileColum(reportField8, fileConfig,
			// "type", new BigDecimal(8), "ReportingPeriodType", "",
			// "format", null, null, versionAdmin);
			// FileColum fileColum9 = new FileColum(reportField9, fileConfig,
			// "type", new BigDecimal(9), "ReportingPeriodYear", "",
			// "format", null, null, versionAdmin);
			// FileColum fileColum10 = new FileColum(reportField10, fileConfig,
			// "type", new BigDecimal(10),
			// "AIFMReportingObligationChangeFrequencyCode", "", "format",
			// null, null, versionAdmin);
			// FileColum fileColum11 = new FileColum(reportField12, fileConfig,
			// "type", new BigDecimal(11),
			// "AIFMReportingObligationChangeQuarter", "", "format", null,
			// null, versionAdmin);
			// FileColum fileColum12 = new FileColum(reportField13, fileConfig,
			// "type", new BigDecimal(12), "LastReportingFlag", "",
			// "format", null, null, versionAdmin);
			// FileColum fileColum13 = new FileColum(reportField16, fileConfig,
			// "type", new BigDecimal(13), "AIFMReportingCode", "",
			// "format", null, null, versionAdmin);
			// FileColum fileColum14 = new FileColum(reportField17, fileConfig,
			// "type", new BigDecimal(14), "AIFMJurisdiction", "",
			// "format", null, null, versionAdmin);
			// FileColum fileColum15 = new FileColum(reportField18, fileConfig,
			// "type", new BigDecimal(15), "AIFMNationalCode", "",
			// "format", null, null, versionAdmin);
			// FileColum fileColum16 = new FileColum(reportField19, fileConfig,
			// "type", new BigDecimal(16), "AIFMName", "", "format", null,
			// null, versionAdmin);
			// FileColum fileColum17 = new FileColum(reportField20, fileConfig,
			// "type", new BigDecimal(17), "AIFMEEAFlag", "", "format",
			// null, null, versionAdmin);
			// FileColum fileColum18 = new FileColum(reportField21, fileConfig,
			// "type", new BigDecimal(18), "AIFMNoReportingFlag", "",
			// "format", null, null, versionAdmin);
			// FileColum fileColum19 = new FileColum(reportField22, fileConfig,
			// "type", new BigDecimal(19), "AIFMIdentifierLEI", "",
			// "format", null, null, versionAdmin);
			// FileColum fileColum20 = new FileColum(reportField23, fileConfig,
			// "type", new BigDecimal(20), "AIFMIdentifierBIC", "",
			// "format", null, null, versionAdmin);
			// FileColum fileColum21 = new FileColum(reportField26, fileConfig,
			// "type", new BigDecimal(21), "AIFMFivePrincipalMarket1", "",
			// "format", null, null, versionAdmin);
			// fileColum18.setColumBlock("1");
			// FileColum fileColum22 = new FileColum(reportField27, fileConfig,
			// "type", new BigDecimal(22), "MarketCodeType1", "",
			// "format", null, null, versionAdmin);
			// fileColum19.setColumBlock("1");
			// FileColum fileColum23 = new FileColum(reportField28, fileConfig,
			// "type", new BigDecimal(23), "MarketCode1", "", "format",
			// null, null, versionAdmin);
			// fileColum20.setColumBlock("1");
			// FileColum fileColum24 = new FileColum(reportField29, fileConfig,
			// "type", new BigDecimal(24), "MarketAggregatedValueAmount1",
			// "", "format", null, null, versionAdmin);
			// fileColum21.setColumBlock("1");
			// FileColum fileColum25 = new FileColum(reportField26, fileConfig,
			// "type", new BigDecimal(25), "AIFMFivePrincipalMarket2", "",
			// "format", null, null, versionAdmin);
			// fileColum22.setColumBlock("2");
			// FileColum fileColum26 = new FileColum(reportField27, fileConfig,
			// "type", new BigDecimal(26), "MarketCodeType2", "",
			// "format", null, null, versionAdmin);
			// fileColum23.setColumBlock("2");
			// FileColum fileColum27 = new FileColum(reportField28, fileConfig,
			// "type", new BigDecimal(27), "MarketCode2", "", "format",
			// null, null, versionAdmin);
			// fileColum24.setColumBlock("2");
			// FileColum fileColum28 = new FileColum(reportField29, fileConfig,
			// "type", new BigDecimal(28), "MarketAggregatedValueAmount2",
			// "", "format", null, null, versionAdmin);
			// fileColum25.setColumBlock("2");
			// FileColum fileColum29 = new FileColum(reportField26, fileConfig,
			// "type", new BigDecimal(29), "AIFMFivePrincipalMarket3", "",
			// "format", null, null, versionAdmin);
			// fileColum26.setColumBlock("3");
			// FileColum fileColum30 = new FileColum(reportField27, fileConfig,
			// "type", new BigDecimal(30), "MarketCodeType3", "",
			// "format", null, null, versionAdmin);
			// fileColum27.setColumBlock("3");
			// FileColum fileColum31 = new FileColum(reportField28, fileConfig,
			// "type", new BigDecimal(31), "MarketCode3", "", "format",
			// null, null, versionAdmin);
			// fileColum28.setColumBlock("3");
			// FileColum fileColum32 = new FileColum(reportField29, fileConfig,
			// "type", new BigDecimal(32), "MarketAggregatedValueAmount3",
			// "", "format", null, null, versionAdmin);
			// fileColum29.setColumBlock("3");
			// FileColum fileColum33 = new FileColum(reportField26, fileConfig,
			// "type", new BigDecimal(33), "AIFMFivePrincipalMarket4", "",
			// "format", null, null, versionAdmin);
			// fileColum30.setColumBlock("4");
			// FileColum fileColum34 = new FileColum(reportField27, fileConfig,
			// "type", new BigDecimal(34), "MarketCodeType4", "",
			// "format", null, null, versionAdmin);
			// fileColum31.setColumBlock("4");
			// FileColum fileColum35 = new FileColum(reportField28, fileConfig,
			// "type", new BigDecimal(35), "MarketCode4", "", "format",
			// null, null, versionAdmin);
			// fileColum32.setColumBlock("4");
			// FileColum fileColum36 = new FileColum(reportField29, fileConfig,
			// "type", new BigDecimal(36), "MarketAggregatedValueAmount4",
			// "", "format", null, null, versionAdmin);
			// fileColum33.setColumBlock("4");
			// FileColum fileColum37 = new FileColum(reportField26, fileConfig,
			// "type", new BigDecimal(37), "AIFMFivePrincipalMarket5", "",
			// "format", null, null, versionAdmin);
			// fileColum34.setColumBlock("5");
			// FileColum fileColum38 = new FileColum(reportField27, fileConfig,
			// "type", new BigDecimal(38), "MarketCodeType5", "",
			// "format", null, null, versionAdmin);
			// fileColum35.setColumBlock("5");
			// FileColum fileColum39 = new FileColum(reportField28, fileConfig,
			// "type", new BigDecimal(39), "MarketCode5", "", "format",
			// null, null, versionAdmin);
			// fileColum36.setColumBlock("5");
			// FileColum fileColum40 = new FileColum(reportField29, fileConfig,
			// "type", new BigDecimal(40), "MarketAggregatedValueAmount5",
			// "", "format", null, null, versionAdmin);
			// fileColum37.setColumBlock("5");
			// FileColum fileColum41 = new FileColum(reportField30, fileConfig,
			// "type", new BigDecimal(41), "AIFMPrincipalInstrument1", "",
			// "format", null, null, versionAdmin);
			// fileColum38.setColumBlock("1");
			// FileColum fileColum42 = new FileColum(reportField31, fileConfig,
			// "type", new BigDecimal(42), "SubAssetType1", "", "format",
			// null, null, versionAdmin);
			// fileColum39.setColumBlock("1");
			// FileColum fileColum43 = new FileColum(reportField32, fileConfig,
			// "type", new BigDecimal(43),
			// "InstrumentAggregatedValueAmount1", "", "format", null,
			// null, versionAdmin);
			// fileColum40.setColumBlock("1");
			// FileColum fileColum44 = new FileColum(reportField30, fileConfig,
			// "type", new BigDecimal(44), "AIFMPrincipalInstrument2", "",
			// "format", null, null, versionAdmin);
			// fileColum41.setColumBlock("2");
			// FileColum fileColum45 = new FileColum(reportField31, fileConfig,
			// "type", new BigDecimal(45), "SubAssetType2", "", "format",
			// null, null, versionAdmin);
			// fileColum42.setColumBlock("2");
			// FileColum fileColum46 = new FileColum(reportField32, fileConfig,
			// "type", new BigDecimal(46),
			// "InstrumentAggregatedValueAmount2", "", "format", null,
			// null, versionAdmin);
			// fileColum43.setColumBlock("2");
			// FileColum fileColum47 = new FileColum(reportField30, fileConfig,
			// "type", new BigDecimal(47), "AIFMPrincipalInstrument3", "",
			// "format", null, null, versionAdmin);
			// fileColum44.setColumBlock("3");
			// FileColum fileColum48 = new FileColum(reportField31, fileConfig,
			// "type", new BigDecimal(48), "SubAssetType3", "", "format",
			// null, null, versionAdmin);
			// fileColum45.setColumBlock("3");
			// FileColum fileColum49 = new FileColum(reportField32, fileConfig,
			// "type", new BigDecimal(49),
			// "InstrumentAggregatedValueAmount3", "", "format", null,
			// null, versionAdmin);
			// fileColum46.setColumBlock("3");
			// FileColum fileColum50 = new FileColum(reportField30, fileConfig,
			// "type", new BigDecimal(50), "AIFMPrincipalInstrument4", "",
			// "format", null, null, versionAdmin);
			// fileColum47.setColumBlock("4");
			// FileColum fileColum51 = new FileColum(reportField31, fileConfig,
			// "type", new BigDecimal(51), "SubAssetType4", "", "format",
			// null, null, versionAdmin);
			// fileColum48.setColumBlock("4");
			// FileColum fileColum52 = new FileColum(reportField32, fileConfig,
			// "type", new BigDecimal(52),
			// "InstrumentAggregatedValueAmount4", "", "format", null,
			// null, versionAdmin);
			// fileColum49.setColumBlock("4");
			// FileColum fileColum53 = new FileColum(reportField30, fileConfig,
			// "type", new BigDecimal(53), "AIFMPrincipalInstrument5", "",
			// "format", null, null, versionAdmin);
			// fileColum50.setColumBlock("5");
			// FileColum fileColum54 = new FileColum(reportField31, fileConfig,
			// "type", new BigDecimal(54), "SubAssetType5", "", "format",
			// null, null, versionAdmin);
			// fileColum51.setColumBlock("5");
			// FileColum fileColum55 = new FileColum(reportField32, fileConfig,
			// "type", new BigDecimal(55),
			// "InstrumentAggregatedValueAmount5", "", "format", null,
			// null, versionAdmin);
			// fileColum52.setColumBlock("5");
			// FileColum fileColum56 = new FileColum(reportField33, fileConfig,
			// "type", new BigDecimal(56), "AUMAmountInEuro", "",
			// "format", null, null, versionAdmin);
			// FileColum fileColum57 = new FileColum(reportField35, fileConfig,
			// "type", new BigDecimal(57), "BaseCurrency", "", "format",
			// null, null, versionAdmin);
			// FileColum fileColum58 = new FileColum(reportField36, fileConfig,
			// "type", new BigDecimal(58), "AUMAmountInBaseCurrency", "",
			// "format", null, null, versionAdmin);
			// FileColum fileColum59 = new FileColum(reportField37, fileConfig,
			// "type", new BigDecimal(59), "FXEURReferenceRateType", "",
			// "format", null, null, versionAdmin);
			// FileColum fileColum60 = new FileColum(reportField38, fileConfig,
			// "type", new BigDecimal(60), "FXEURRate", "", "format",
			// null, null, versionAdmin);
			// FileColum fileColum61 = new FileColum(reportField39, fileConfig,
			// "type", new BigDecimal(61), "CancelledAIFMNationalCode",
			// "", "format", null, null, versionAdmin);
			// FileColum fileColum62 = new FileColum(reportField40, fileConfig,
			// "type", new BigDecimal(62), "CancelledReportingPeriodType",
			// "", "format", null, null, versionAdmin);
			// FileColum fileColum63 = new FileColum(reportField41, fileConfig,
			// "type", new BigDecimal(63), "CancelledReportingPeriodYear",
			// "", "format", null, null, versionAdmin);
			// FileColum fileColum64 = new FileColum(reportField42, fileConfig,
			// "type", new BigDecimal(64), "CancelledRecordFlag", "",
			// "format", null, null, versionAdmin);

			FileColum fileColum0 = new FileColum(reportField1, fileConfig,
					"type", new BigDecimal(0), "ReportingMemberState", "",
					"format", null, null, versionAdmin);
			FileColum fileColum1 = new FileColum(reportField4, fileConfig,
					"type", new BigDecimal(1), "FilingType", "", "format",
					null, null, versionAdmin);
			FileColum fileColum2 = new FileColum(reportField5, fileConfig,
					"type", new BigDecimal(2), "AIFMContentType", "", "format",
					null, null, versionAdmin);
			FileColum fileColum3 = new FileColum(reportField6, fileConfig,
					"DATE", new BigDecimal(3), "ReportingPeriodStartDate", "",
					"dd/MM/yyyy", null, null, versionAdmin);
			FileColum fileColum4 = new FileColum(reportField7, fileConfig,
					"DATE", new BigDecimal(4), "ReportingPeriodEndDate", "",
					"dd/MM/yyyy", null, null, versionAdmin);
			FileColum fileColum5 = new FileColum(reportField8, fileConfig,
					"type", new BigDecimal(5), "ReportingPeriodType", "",
					"format", null, null, versionAdmin);
			FileColum fileColum6 = new FileColum(reportField9, fileConfig,
					"type", new BigDecimal(6), "ReportingPeriodYear", "",
					"format", null, null, versionAdmin);
			FileColum fileColum7 = new FileColum(reportField10, fileConfig,
					"type", new BigDecimal(7),
					"AIFMReportingObligationChangeFrequencyCode", "", "format",
					null, null, versionAdmin);
			FileColum fileColum8 = new FileColum(reportField11, fileConfig,
					"type", new BigDecimal(8),
					"AIFReportingObligationChangeContentsCode", "", "format",
					null, null, versionAdmin);
			FileColum fileColum9 = new FileColum(reportField12, fileConfig,
					"type", new BigDecimal(9),
					"AIFMReportingObligationChangeQuarter", "", "format", null,
					null, versionAdmin);
			FileColum fileColum10 = new FileColum(reportField13, fileConfig,
					"type", new BigDecimal(10), "LastReportingFlag", "",
					"format", null, null, versionAdmin);
			FileColum fileColum11 = new FileColum(reportField16, fileConfig,
					"type", new BigDecimal(11), "AIFMReportingCode", "",
					"format", null, null, versionAdmin);
			FileColum fileColum12 = new FileColum(reportField17, fileConfig,
					"type", new BigDecimal(12), "AIFMJurisdiction", "",
					"format", null, null, versionAdmin);
			FileColum fileColum13 = new FileColum(reportField18, fileConfig,
					"type", new BigDecimal(13), "AIFMNationalCode", "",
					"format", null, null, versionAdmin);
			FileColum fileColum14 = new FileColum(reportField19, fileConfig,
					"type", new BigDecimal(14), "AIFMName", "", "format", null,
					null, versionAdmin);
			FileColum fileColum15 = new FileColum(reportField20, fileConfig,
					"type", new BigDecimal(15), "AIFMEEAFlag", "", "format",
					null, null, versionAdmin);
			FileColum fileColum16 = new FileColum(reportField21, fileConfig,
					"type", new BigDecimal(16), "AIFMNoReportingFlag", "",
					"format", null, null, versionAdmin);
			FileColum fileColum17 = new FileColum(reportField22, fileConfig,
					"type", new BigDecimal(17), "AIFMIdentifierLEI", "",
					"format", null, null, versionAdmin);
			FileColum fileColum18 = new FileColum(reportField23, fileConfig,
					"type", new BigDecimal(18), "AIFMIdentifierBIC", "",
					"format", null, null, versionAdmin);
			FileColum fileColum19 = new FileColum(reportField24, fileConfig,
					"type", new BigDecimal(19), "Old_ReportingMemberState", "",
					"format", null, null, versionAdmin);
			FileColum fileColum20 = new FileColum(reportField25, fileConfig,
					"type", new BigDecimal(20), "Old_AIFMNationalCode", "",
					"format", null, null, versionAdmin);
			FileColum fileColum21 = new FileColum(reportField26, fileConfig,
					"type", new BigDecimal(21), "AIFMFivePrincipalMarket1", "",
					"format", null, null, versionAdmin);
			fileColum21.setColumBlock("1");
			FileColum fileColum22 = new FileColum(reportField27, fileConfig,
					"type", new BigDecimal(22), "MarketCodeType1", "",
					"format", null, null, versionAdmin);
			fileColum22.setColumBlock("1");
			FileColum fileColum23 = new FileColum(reportField28, fileConfig,
					"type", new BigDecimal(23), "MarketCode1", "", "format",
					null, null, versionAdmin);
			fileColum23.setColumBlock("1");
			FileColum fileColum24 = new FileColum(reportField29, fileConfig,
					"type", new BigDecimal(24), "MarketAggregatedValueAmount1",
					"", "format", null, null, versionAdmin);
			fileColum24.setColumBlock("1");
			FileColum fileColum25 = new FileColum(reportField26, fileConfig,
					"type", new BigDecimal(25), "AIFMFivePrincipalMarket2", "",
					"format", null, null, versionAdmin);
			fileColum25.setColumBlock("2");
			FileColum fileColum26 = new FileColum(reportField27, fileConfig,
					"type", new BigDecimal(26), "MarketCodeType2", "",
					"format", null, null, versionAdmin);
			fileColum26.setColumBlock("2");
			FileColum fileColum27 = new FileColum(reportField28, fileConfig,
					"type", new BigDecimal(27), "MarketCode2", "", "format",
					null, null, versionAdmin);
			fileColum27.setColumBlock("2");
			FileColum fileColum28 = new FileColum(reportField29, fileConfig,
					"type", new BigDecimal(28), "MarketAggregatedValueAmount2",
					"", "format", null, null, versionAdmin);
			fileColum28.setColumBlock("2");
			FileColum fileColum29 = new FileColum(reportField26, fileConfig,
					"type", new BigDecimal(29), "AIFMFivePrincipalMarket3", "",
					"format", null, null, versionAdmin);
			fileColum29.setColumBlock("3");
			FileColum fileColum30 = new FileColum(reportField27, fileConfig,
					"type", new BigDecimal(30), "MarketCodeType3", "",
					"format", null, null, versionAdmin);
			fileColum30.setColumBlock("3");
			FileColum fileColum31 = new FileColum(reportField28, fileConfig,
					"type", new BigDecimal(31), "MarketCode3", "", "format",
					null, null, versionAdmin);
			fileColum31.setColumBlock("3");
			FileColum fileColum32 = new FileColum(reportField29, fileConfig,
					"type", new BigDecimal(32), "MarketAggregatedValueAmount3",
					"", "format", null, null, versionAdmin);
			fileColum32.setColumBlock("3");
			FileColum fileColum33 = new FileColum(reportField26, fileConfig,
					"type", new BigDecimal(33), "AIFMFivePrincipalMarket4", "",
					"format", null, null, versionAdmin);
			fileColum33.setColumBlock("4");
			FileColum fileColum34 = new FileColum(reportField27, fileConfig,
					"type", new BigDecimal(34), "MarketCodeType4", "",
					"format", null, null, versionAdmin);
			fileColum34.setColumBlock("4");
			FileColum fileColum35 = new FileColum(reportField28, fileConfig,
					"type", new BigDecimal(35), "MarketCode4", "", "format",
					null, null, versionAdmin);
			fileColum35.setColumBlock("4");
			FileColum fileColum36 = new FileColum(reportField29, fileConfig,
					"type", new BigDecimal(36), "MarketAggregatedValueAmount4",
					"", "format", null, null, versionAdmin);
			fileColum36.setColumBlock("4");
			FileColum fileColum37 = new FileColum(reportField26, fileConfig,
					"type", new BigDecimal(37), "AIFMFivePrincipalMarket5", "",
					"format", null, null, versionAdmin);
			fileColum37.setColumBlock("5");
			FileColum fileColum38 = new FileColum(reportField27, fileConfig,
					"type", new BigDecimal(38), "MarketCodeType5", "",
					"format", null, null, versionAdmin);
			fileColum38.setColumBlock("5");
			FileColum fileColum39 = new FileColum(reportField28, fileConfig,
					"type", new BigDecimal(39), "MarketCode5", "", "format",
					null, null, versionAdmin);
			fileColum39.setColumBlock("5");
			FileColum fileColum40 = new FileColum(reportField29, fileConfig,
					"type", new BigDecimal(40), "MarketAggregatedValueAmount5",
					"", "format", null, null, versionAdmin);
			fileColum40.setColumBlock("5");
			FileColum fileColum41 = new FileColum(reportField30, fileConfig,
					"type", new BigDecimal(41), "AIFMPrincipalInstrument1", "",
					"format", null, null, versionAdmin);
			fileColum41.setColumBlock("1");
			FileColum fileColum42 = new FileColum(reportField31, fileConfig,
					"type", new BigDecimal(42), "SubAssetType1", "", "format",
					null, null, versionAdmin);
			fileColum42.setColumBlock("1");
			FileColum fileColum43 = new FileColum(reportField32, fileConfig,
					"type", new BigDecimal(43),
					"InstrumentAggregatedValueAmount1", "", "format", null,
					null, versionAdmin);
			fileColum43.setColumBlock("1");
			FileColum fileColum44 = new FileColum(reportField30, fileConfig,
					"type", new BigDecimal(44), "AIFMPrincipalInstrument2", "",
					"format", null, null, versionAdmin);
			fileColum44.setColumBlock("2");
			FileColum fileColum45 = new FileColum(reportField31, fileConfig,
					"type", new BigDecimal(45), "SubAssetType2", "", "format",
					null, null, versionAdmin);
			fileColum45.setColumBlock("2");
			FileColum fileColum46 = new FileColum(reportField32, fileConfig,
					"type", new BigDecimal(46),
					"InstrumentAggregatedValueAmount2", "", "format", null,
					null, versionAdmin);
			fileColum46.setColumBlock("2");
			FileColum fileColum47 = new FileColum(reportField30, fileConfig,
					"type", new BigDecimal(47), "AIFMPrincipalInstrument3", "",
					"format", null, null, versionAdmin);
			fileColum47.setColumBlock("3");
			FileColum fileColum48 = new FileColum(reportField31, fileConfig,
					"type", new BigDecimal(48), "SubAssetType3", "", "format",
					null, null, versionAdmin);
			fileColum48.setColumBlock("3");
			FileColum fileColum49 = new FileColum(reportField32, fileConfig,
					"type", new BigDecimal(49),
					"InstrumentAggregatedValueAmount3", "", "format", null,
					null, versionAdmin);
			fileColum49.setColumBlock("3");
			FileColum fileColum50 = new FileColum(reportField30, fileConfig,
					"type", new BigDecimal(50), "AIFMPrincipalInstrument4", "",
					"format", null, null, versionAdmin);
			fileColum50.setColumBlock("4");
			FileColum fileColum51 = new FileColum(reportField31, fileConfig,
					"type", new BigDecimal(51), "SubAssetType4", "", "format",
					null, null, versionAdmin);
			fileColum51.setColumBlock("4");
			FileColum fileColum52 = new FileColum(reportField32, fileConfig,
					"type", new BigDecimal(52),
					"InstrumentAggregatedValueAmount4", "", "format", null,
					null, versionAdmin);
			fileColum52.setColumBlock("4");
			FileColum fileColum53 = new FileColum(reportField30, fileConfig,
					"type", new BigDecimal(53), "AIFMPrincipalInstrument5", "",
					"format", null, null, versionAdmin);
			fileColum53.setColumBlock("5");
			FileColum fileColum54 = new FileColum(reportField31, fileConfig,
					"type", new BigDecimal(54), "SubAssetType5", "", "format",
					null, null, versionAdmin);
			fileColum54.setColumBlock("5");
			FileColum fileColum55 = new FileColum(reportField32, fileConfig,
					"type", new BigDecimal(55),
					"InstrumentAggregatedValueAmount5", "", "format", null,
					null, versionAdmin);
			fileColum55.setColumBlock("5");
			FileColum fileColum56 = new FileColum(reportField33, fileConfig,
					"type", new BigDecimal(56), "AUMAmountInEuro", "",
					"format", null, null, versionAdmin);
			FileColum fileColum57 = new FileColum(reportField34, fileConfig,
					"type", new BigDecimal(57), "BaseCurrency", "", "format",
					null, null, versionAdmin);
			FileColum fileColum58 = new FileColum(reportField35, fileConfig,
					"type", new BigDecimal(58), "AUMAmountInBaseCurrency", "",
					"format", null, null, versionAdmin);
			FileColum fileColum59 = new FileColum(reportField36, fileConfig,
					"type", new BigDecimal(59), "FXEURReferenceRateType", "",
					"format", null, null, versionAdmin);
			FileColum fileColum60 = new FileColum(reportField37, fileConfig,
					"type", new BigDecimal(60), "FXEURRate", "", "format",
					null, null, versionAdmin);
			FileColum fileColum61 = new FileColum(reportField38, fileConfig,
					"type", new BigDecimal(61),
					"FXEUROtherReferenceRateDescription", "", "format", null,
					null, versionAdmin);
			FileColum fileColum62 = new FileColum(reportField39, fileConfig,
					"type", new BigDecimal(62), "CancelledAIFMNationalCode",
					"", "format", null, null, versionAdmin);
			FileColum fileColum63 = new FileColum(reportField40, fileConfig,
					"type", new BigDecimal(63), "CancelledReportingPeriodType",
					"", "format", null, null, versionAdmin);
			FileColum fileColum64 = new FileColum(reportField41, fileConfig,
					"type", new BigDecimal(64), "CancelledReportingPeriodYear",
					"", "format", null, null, versionAdmin);
			FileColum fileColum65 = new FileColum(reportField42, fileConfig,
					"type", new BigDecimal(65), "CancelledRecordFlag", "",
					"format", null, null, versionAdmin);

			Error error1 = new Error("LOADER", "Error load", "1",
					"Error in load file", "Reload file", null, null, null,
					versionAdmin);
			Error error2 = new Error("CONTROLLER", "Error controller", "1",
					"Error in servlet", "Reload page", null, null, null,
					versionAdmin);
			Error error3 = new Error("VALIDATOR", "Error validator", "1",
					"Error in validation", "Check fields", null, null, null,
					versionAdmin);
			Error error4 = new Error("NORMALIZER", "Error normalizer", "1",
					"Error in normalize process", "Reload file", null, null,
					null, versionAdmin);
			Error error5 = new Error("CREATION", "Error load", "1",
					"Error in creation", "Check conditions", null, null, null,
					versionAdmin);
			Error error6 = new Error("SYNTAXIS", "Error load", "1",
					"Error in syntaxis", "Check format", null, null, null,
					versionAdmin);
			Error error7 = new Error("SEMANTIC", "Error sematinc", "1",
					"Error in semantic", "Check rules", null, null, null,
					versionAdmin);
			Error error8 = new Error("TRANSLATE", "Error load", "1",
					"Error in load file", "Reload file", null, null, null,
					versionAdmin);
			Error error9 = new Error("STATUS", "Error status", "1",
					"Error in status checker", "Check report", null, null,
					null, versionAdmin);
			Error error10 = new Error("REPORTING", "Error report", "1",
					"Error in report", "Check report", null, null, null,
					versionAdmin);

			// FileColumList fileColumList1 = new FileColumList(fileColum4,
			// "TEXT", "FIRST", "INIT", versionAdmin);

			ReportSemantic reportSemantic1 = new ReportSemantic(
					reportCatalog,
					"Field(2) is mandatory",
					"result = ReportUtilities.searchData(reportExecution.getReportDatas(), \"Version\", \"2\", null)",
					null, "Fill field(2)", versionAdmin);

			// DAO

			CompanyDAO companyDAO = (CompanyDAO) applicationContext
					.getBean("companyDAO");
			companyDAO.create(company);

			DepartmentDAO departmentDAO = (DepartmentDAO) applicationContext
					.getBean("departmentDAO");
			departmentDAO.create(department);

			FundDAO fundDAO = (FundDAO) applicationContext.getBean("fundDAO");
			fundDAO.create(fund1);
			fundDAO.create(fund2);

			FundGroupDAO fundGroupDAO = (FundGroupDAO) applicationContext
					.getBean("fundGroupDAO");
			fundGroupDAO.create(fundGroup);

			// ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO)
			// applicationContext
			// .getBean("reportExecutionDAO");
			// reportExecutionDAO.create(reportExecution);

			FileConfigDAO fileConfigDAO = (FileConfigDAO) applicationContext
					.getBean("fileConfigDAO");
			fileConfigDAO.create(fileConfig);
			fileConfigDAO.create(fileConfig0);

			FileColumDAO fileColumDAO = (FileColumDAO) applicationContext
					.getBean("fileColumDAO");
			fileColumDAO.create(fileColum01);
			fileColumDAO.create(fileColum02);

			fileColumDAO.create(fileColum0);
			fileColumDAO.create(fileColum1);
			fileColumDAO.create(fileColum2);
			fileColumDAO.create(fileColum3);
			fileColumDAO.create(fileColum4);
			fileColumDAO.create(fileColum5);
			fileColumDAO.create(fileColum6);
			fileColumDAO.create(fileColum7);
			fileColumDAO.create(fileColum8);
			fileColumDAO.create(fileColum9);
			fileColumDAO.create(fileColum10);
			fileColumDAO.create(fileColum11);
			fileColumDAO.create(fileColum12);
			fileColumDAO.create(fileColum13);
			fileColumDAO.create(fileColum14);
			fileColumDAO.create(fileColum15);
			fileColumDAO.create(fileColum16);
			fileColumDAO.create(fileColum17);
			fileColumDAO.create(fileColum18);
			fileColumDAO.create(fileColum19);
			fileColumDAO.create(fileColum20);
			fileColumDAO.create(fileColum21);
			fileColumDAO.create(fileColum22);
			fileColumDAO.create(fileColum23);
			fileColumDAO.create(fileColum24);
			fileColumDAO.create(fileColum25);
			fileColumDAO.create(fileColum26);
			fileColumDAO.create(fileColum27);
			fileColumDAO.create(fileColum28);
			fileColumDAO.create(fileColum29);
			fileColumDAO.create(fileColum30);
			fileColumDAO.create(fileColum31);
			fileColumDAO.create(fileColum32);
			fileColumDAO.create(fileColum33);
			fileColumDAO.create(fileColum34);
			fileColumDAO.create(fileColum35);
			fileColumDAO.create(fileColum36);
			fileColumDAO.create(fileColum37);
			fileColumDAO.create(fileColum38);
			fileColumDAO.create(fileColum39);
			fileColumDAO.create(fileColum40);
			fileColumDAO.create(fileColum41);
			fileColumDAO.create(fileColum42);
			fileColumDAO.create(fileColum43);
			fileColumDAO.create(fileColum44);
			fileColumDAO.create(fileColum45);
			fileColumDAO.create(fileColum46);
			fileColumDAO.create(fileColum47);
			fileColumDAO.create(fileColum48);
			fileColumDAO.create(fileColum49);
			fileColumDAO.create(fileColum50);
			fileColumDAO.create(fileColum51);
			fileColumDAO.create(fileColum52);
			fileColumDAO.create(fileColum53);
			fileColumDAO.create(fileColum54);
			fileColumDAO.create(fileColum55);
			fileColumDAO.create(fileColum56);
			fileColumDAO.create(fileColum57);
			fileColumDAO.create(fileColum58);
			fileColumDAO.create(fileColum59);
			fileColumDAO.create(fileColum60);
			fileColumDAO.create(fileColum61);
			fileColumDAO.create(fileColum62);
			fileColumDAO.create(fileColum63);
			fileColumDAO.create(fileColum64);
			fileColumDAO.create(fileColum65);

			ErrorDAO errorDAO = (ErrorDAO) applicationContext
					.getBean("errorDAO");
			errorDAO.create(error1);
			errorDAO.create(error2);
			errorDAO.create(error3);
			errorDAO.create(error4);
			errorDAO.create(error5);
			errorDAO.create(error6);
			errorDAO.create(error7);
			errorDAO.create(error8);
			errorDAO.create(error9);
			errorDAO.create(error10);

			ReportSemanticDAO reportSemanticDAO = (ReportSemanticDAO) applicationContext
					.getBean("reportSemanticDAO");
			reportSemanticDAO.create(reportSemantic1);
			System.out.println("reportSemantic1 "
					+ reportSemantic1.getReportingSemanticRule());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Process to install in database reportFieldList for all AIFMD reports
	 * 
	 * @param applicationContext
	 */
	public void installFileList(ApplicationContext applicationContext) {

		List<ReportFieldList> repotFieldList = new ArrayList<ReportFieldList>();

		VersionAuditor versionAdmin = new VersionAuditor("admin");

		ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) applicationContext
				.getBean("reportFieldListDAO");
		reportFieldListDAO.deleteAll();

		repotFieldList.add(new ReportFieldList("BOOLEAN", "true", "true",
				versionAdmin));
		repotFieldList.add(new ReportFieldList("BOOLEAN", "false", "false",
				versionAdmin));

		repotFieldList.add(new ReportFieldList("AIFMasterFeederStatusType",
				"MASTER", "MASTER", versionAdmin));
		repotFieldList.add(new ReportFieldList("AIFMasterFeederStatusType",
				"FEEDER", "FEEDER", versionAdmin));
		repotFieldList.add(new ReportFieldList("AIFMasterFeederStatusType",
				"NONE", "NONE", versionAdmin));

		repotFieldList.add(new ReportFieldList("AIFTypeType", "HFND", "HFND",
				versionAdmin));
		repotFieldList.add(new ReportFieldList("AIFTypeType", "PEQF", "PEQF",
				versionAdmin));
		repotFieldList.add(new ReportFieldList("AIFTypeType", "REST", "REST",
				versionAdmin));
		repotFieldList.add(new ReportFieldList("AIFTypeType", "FOFS", "FOFS",
				versionAdmin));
		repotFieldList.add(new ReportFieldList("AIFTypeType", "OTHR", "OTHR",
				versionAdmin));
		repotFieldList.add(new ReportFieldList("AIFTypeType", "NONE", "NONE",
				versionAdmin));

		repotFieldList.add(new ReportFieldList("AIIDerivativeTypeType", "O",
				"O", versionAdmin));
		repotFieldList.add(new ReportFieldList("AIIDerivativeTypeType", "F",
				"F", versionAdmin));

		repotFieldList.add(new ReportFieldList("AIIPutCallIdentifierType", "P",
				"P", versionAdmin));
		repotFieldList.add(new ReportFieldList("AIIPutCallIdentifierType", "C",
				"C", versionAdmin));
		repotFieldList.add(new ReportFieldList("AIIPutCallIdentifierType", "F",
				"F", versionAdmin));

		repotFieldList.add(new ReportFieldList("AlternateTextType", "NA", "NA",
				versionAdmin));

		repotFieldList.add(new ReportFieldList("AssetMacroTypeType", "SEC",
				"SEC", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetMacroTypeType", "DER",
				"DER", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetMacroTypeType", "CIU",
				"CIU", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetMacroTypeType", "PHY",
				"PHY", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetMacroTypeType", "OTH",
				"OTH", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetMacroTypeType", "NTA",
				"NTA", versionAdmin));

		repotFieldList.add(new ReportFieldList("AssetTypeType", "SEC_CSH",
				"SEC_CSH", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "SEC_LEQ",
				"SEC_LEQ", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "SEC_UEQ",
				"SEC_UEQ", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "SEC_CPN",
				"SEC_CPN", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "SEC_CPI",
				"SEC_CPI", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "SEC_SBD",
				"SEC_SBD", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "SEC_MBN",
				"SEC_MBN", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "SEC_CBN",
				"SEC_CBN", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "SEC_CBI",
				"SEC_CBI", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "SEC_LON",
				"SEC_LON", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "SEC_SSP",
				"SEC_SSP", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "DER_EQD",
				"DER_EQD", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "DER_FID",
				"DER_FID", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "DER_CDS",
				"DER_CDS", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "DER_FEX",
				"DER_FEX", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "DER_IRD",
				"DER_IRD", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "DER_CTY",
				"DER_CTY", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "DER_OTH",
				"DER_OTH", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "PHY_RES",
				"PHY_RES", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "PHY_CTY",
				"PHY_CTY", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "PHY_TIM",
				"PHY_TIM", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "PHY_ART",
				"PHY_ART", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "PHY_TPT",
				"PHY_TPT", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "PHY_OTH",
				"PHY_OTH", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "CIU_OAM",
				"CIU_OAM", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "CIU_NAM",
				"CIU_NAM", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "OTH_OTH",
				"OTH_OTH", versionAdmin));
		repotFieldList.add(new ReportFieldList("AssetTypeType", "NTA_NTA",
				"NTA_NTA", versionAdmin));

		repotFieldList.add(new ReportFieldList("CancelledRecordFlagType", "C",
				"C", versionAdmin));
		repotFieldList.add(new ReportFieldList("CancelledRecordFlagType", "D",
				"D", versionAdmin));

		repotFieldList.add(new ReportFieldList("FilingTypeType", "AMND",
				"AMND", versionAdmin));
		repotFieldList.add(new ReportFieldList("FilingTypeType", "INIT",
				"INIT", versionAdmin));

		repotFieldList.add(new ReportFieldList("FundOfFundsStrategyTypeType",
				"FOFS_FHFS", "FOFS_FHFS", versionAdmin));
		repotFieldList.add(new ReportFieldList("FundOfFundsStrategyTypeType",
				"FOFS_PRIV", "FOFS_PRIV", versionAdmin));
		repotFieldList.add(new ReportFieldList("FundOfFundsStrategyTypeType",
				"OTHR_FOFS", "OTHR_FOFS", versionAdmin));

		repotFieldList.add(new ReportFieldList("FXEURReferenceRateTypeType",
				"ECB", "ECB", versionAdmin));
		repotFieldList.add(new ReportFieldList("FXEURReferenceRateTypeType",
				"OTH", "OTH", versionAdmin));

		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"NONE", "NONE", versionAdmin));

		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EQTY_LGBS", "EQTY_LGBS", versionAdmin));
		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EQTY_LGST", "EQTY_LGST", versionAdmin));
		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EQTY_MTNL", "EQTY_MTNL", versionAdmin));
		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EQTY_STBS", "EQTY_STBS", versionAdmin));
		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"RELV_FXIA", "RELV_FXIA", versionAdmin));
		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"RELV_CBAR", "RELV_CBAR", versionAdmin));
		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"RELV_VLAR", "RELV_VLAR", versionAdmin));
		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EVDR_DSRS", "EVDR_DSRS", versionAdmin));
		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EVDR_RAMA", "EVDR_RAMA", versionAdmin));
		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EVDR_EYSS", "EVDR_EYSS", versionAdmin));
		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"CRED_LGST", "CRED_LGST", versionAdmin));
		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"CRED_ABLG", "CRED_ABLG", versionAdmin));
		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"MACR_MACR", "MACR_MACR", versionAdmin));
		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"MANF_CTAF", "MANF_CTAF", versionAdmin));
		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"MANF_CTAQ", "MANF_CTAQ", versionAdmin));
		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"MULT_HFND", "MULT_HFND", versionAdmin));
		repotFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"OTHR_HFND", "OTHR_HFND", versionAdmin));

		repotFieldList.add(new ReportFieldList("InstrumentCodeTypeType",
				"ISIN", "ISIN", versionAdmin));
		repotFieldList.add(new ReportFieldList("InstrumentCodeTypeType", "AII",
				"AII", versionAdmin));
		repotFieldList.add(new ReportFieldList("InstrumentCodeTypeType",
				"NONE", "NONE", versionAdmin));

		repotFieldList.add(new ReportFieldList("InvestorGroupTypeType", "NFCO",
				"NFCO", versionAdmin));
		repotFieldList.add(new ReportFieldList("InvestorGroupTypeType", "BANK",
				"BANK", versionAdmin));
		repotFieldList.add(new ReportFieldList("InvestorGroupTypeType", "INSC",
				"INSC", versionAdmin));
		repotFieldList.add(new ReportFieldList("InvestorGroupTypeType", "OFIN",
				"OFIN", versionAdmin));
		repotFieldList.add(new ReportFieldList("InvestorGroupTypeType", "PFND",
				"PFND", versionAdmin));
		repotFieldList.add(new ReportFieldList("InvestorGroupTypeType", "GENG",
				"GENG", versionAdmin));
		repotFieldList.add(new ReportFieldList("InvestorGroupTypeType", "OCIU",
				"OCIU", versionAdmin));
		repotFieldList.add(new ReportFieldList("InvestorGroupTypeType", "HHLD",
				"HHLD", versionAdmin));
		repotFieldList.add(new ReportFieldList("InvestorGroupTypeType", "UNKN",
				"UNKN", versionAdmin));
		repotFieldList.add(new ReportFieldList("InvestorGroupTypeType", "NONE",
				"NONE", versionAdmin));

		repotFieldList
				.add(new ReportFieldList("InvestorRedemptionFrequencyType",
						"NONE", "NONE", versionAdmin));

		repotFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "D", "D", versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "W", "W", versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "F", "F", versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "M", "M", versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "Q", "Q", versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "H", "H", versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "Y", "Y", versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "O", "O", versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "N", "N", versionAdmin));

		repotFieldList.add(new ReportFieldList("MarketCodeTypeWithNOTType",
				"NOT", "NOT", versionAdmin));
		repotFieldList.add(new ReportFieldList("MarketCodeTypeWithNOTType",
				"MIC", "MIC", versionAdmin));
		repotFieldList.add(new ReportFieldList("MarketCodeTypeWithNOTType",
				"OTC", "OTC", versionAdmin));
		repotFieldList.add(new ReportFieldList("MarketCodeTypeWithNOTType",
				"XXX", "XXX", versionAdmin));

		repotFieldList.add(new ReportFieldList("MarketCodeTypeWithoutNOTType",
				"MIC", "MIC", versionAdmin));
		repotFieldList.add(new ReportFieldList("MarketCodeTypeWithoutNOTType",
				"OTC", "OTC", versionAdmin));
		repotFieldList.add(new ReportFieldList("MarketCodeTypeWithoutNOTType",
				"XXX", "XXX", versionAdmin));

		repotFieldList.add(new ReportFieldList("OtherFundStrategyTypeType",
				"OTHR_COMF", "OTHR_COMF", versionAdmin));
		repotFieldList.add(new ReportFieldList("OtherFundStrategyTypeType",
				"OTHR_EQYF", "OTHR_EQYF", versionAdmin));
		repotFieldList.add(new ReportFieldList("OtherFundStrategyTypeType",
				"OTHR_FXIF", "OTHR_FXIF", versionAdmin));
		repotFieldList.add(new ReportFieldList("OtherFundStrategyTypeType",
				"OTHR_INFF", "OTHR_INFF", versionAdmin));
		repotFieldList.add(new ReportFieldList("OtherFundStrategyTypeType",
				"OTHR_OTHF", "OTHR_OTHF", versionAdmin));

		repotFieldList.add(new ReportFieldList("PositionTypeType", "L", "L",
				versionAdmin));
		repotFieldList.add(new ReportFieldList("PositionTypeType", "S", "S",
				versionAdmin));

		repotFieldList.add(new ReportFieldList(
				"PrivateEquityFundStrategyTypeType", "VENT_CAPL", "VENT_CAPL",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"PrivateEquityFundStrategyTypeType", "GRTH_CAPL", "GRTH_CAPL",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"PrivateEquityFundStrategyTypeType", "MZNE_CAPL", "MZNE_CAPL",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"PrivateEquityFundStrategyTypeType", "MULT_PEQF", "MULT_PEQF",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"PrivateEquityFundStrategyTypeType", "OTHR_PEQF", "OTHR_PEQF",
				versionAdmin));

		repotFieldList.add(new ReportFieldList(
				"RealEstateFundStrategyTypeType", "RESL_REST", "RESL_REST",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"RealEstateFundStrategyTypeType", "COML_REST", "COML_REST",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"RealEstateFundStrategyTypeType", "INDL_REST", "INDL_REST",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"RealEstateFundStrategyTypeType", "MULT_REST", "MULT_REST",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"RealEstateFundStrategyTypeType", "OTHR_REST", "OTHR_REST",
				versionAdmin));

		repotFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "YH", "YH",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "YQ", "YQ",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "HY", "HY",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "HQ", "HQ",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "QY", "QY",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "QH", "QH",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "NQ", "NQ",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "NH", "NH",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "NY", "NY",
				versionAdmin));

		repotFieldList.add(new ReportFieldList(
				"ReportingObligationChangeQuarterType", "Q1", "Q1",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"ReportingObligationChangeQuarterType", "Q2", "Q2",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"ReportingObligationChangeQuarterType", "Q3", "Q3",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"ReportingObligationChangeQuarterType", "Q4", "Q4",
				versionAdmin));

		repotFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "Q1",
				"Q1", versionAdmin));
		repotFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "Q2",
				"Q2", versionAdmin));
		repotFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "Q3",
				"Q3", versionAdmin));
		repotFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "Q4",
				"Q4", versionAdmin));
		repotFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "H1",
				"H1", versionAdmin));
		repotFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "H2",
				"H2", versionAdmin));
		repotFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "Y1",
				"Y1", versionAdmin));
		repotFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "X1",
				"X1", versionAdmin));
		repotFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "X2",
				"X2", versionAdmin));

		repotFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"NET_EQTY_DELTA", "NET_EQTY_DELTA", versionAdmin));
		repotFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"NET_DV01", "NET_DV01", versionAdmin));
		repotFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"NET_CS01", "NET_CS01", versionAdmin));
		repotFieldList.add(new ReportFieldList("RiskMeasureTypeType", "VAR",
				"VAR", versionAdmin));
		repotFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"VEGA_EXPO", "VEGA_EXPO", versionAdmin));
		repotFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"NET_FX_DELTA", "NET_FX_DELTA", versionAdmin));
		repotFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"NET_CTY_DELTA", "NET_CTY_DELTA", versionAdmin));

		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CSH_CODP", "SEC_CSH_CODP", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CSH_COMP", "SEC_CSH_COMP", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CSH_OTHD", "SEC_CSH_OTHD", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CSH_OTHC", "SEC_CSH_OTHC", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_LEQ_IFIN", "SEC_LEQ_IFIN", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_LEQ_OTHR", "SEC_LEQ_OTHR", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_UEQ_UEQY", "SEC_UEQ_UEQY", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CPN_INVG", "SEC_CPN_INVG", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CPN_NIVG", "SEC_CPN_NIVG", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CPI_INVG", "SEC_CPI_INVG", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CPI_NIVG", "SEC_CPI_NIVG", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_EUBY", "SEC_SBD_EUBY", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_EUBM", "SEC_SBD_EUBM", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_NOGY", "SEC_SBD_NOGY", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_NOGM", "SEC_SBD_NOGM", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_EUGY", "SEC_SBD_EUGY", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_EUGM", "SEC_SBD_EUGM", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_MBN_MNPL", "SEC_MBN_MNPL", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CBN_INVG", "SEC_CBN_INVG", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CBN_NIVG", "SEC_CBN_NIVG", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CBI_INVG", "SEC_CBI_INVG", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CBI_NIVG", "SEC_CBI_NIVG", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_LON_LEVL", "SEC_LON_LEVL", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_LON_OTHL", "SEC_LON_OTHL", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_SABS", "SEC_SSP_SABS", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_RMBS", "SEC_SSP_RMBS", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_CMBS", "SEC_SSP_CMBS", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_AMBS", "SEC_SSP_AMBS", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_ABCP", "SEC_SSP_ABCP", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_CDOC", "SEC_SSP_CDOC", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_STRC", "SEC_SSP_STRC", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_SETP", "SEC_SSP_SETP", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_OTHS", "SEC_SSP_OTHS", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_EQD_FINI", "DER_EQD_FINI", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_EQD_OTHD", "DER_EQD_OTHD", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_FID_FIXI", "DER_FID_FIXI", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_SNFI", "DER_CDS_SNFI", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_SNSO", "DER_CDS_SNSO", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_SNOT", "DER_CDS_SNOT", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_INDX", "DER_CDS_INDX", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_EXOT", "DER_CDS_EXOT", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_OTHR", "DER_CDS_OTHR", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_FEX_INVT", "DER_FEX_INVT", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_FEX_HEDG", "DER_FEX_HEDG", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_IRD_INTR", "DER_IRD_INTR", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_ECOL", "DER_CTY_ECOL", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_ENNG", "DER_CTY_ENNG", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_ENPW", "DER_CTY_ENPW", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_ENOT", "DER_CTY_ENOT", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_PMGD", "DER_CTY_PMGD", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_PMOT", "DER_CTY_PMOT", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_OTIM", "DER_CTY_OTIM", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_OTLS", "DER_CTY_OTLS", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_OTAP", "DER_CTY_OTAP", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_OTHR", "DER_CTY_OTHR", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_OTH_OTHR", "DER_OTH_OTHR", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_RES_RESL", "PHY_RES_RESL", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_RES_COML", "PHY_RES_COML", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_RES_OTHR", "PHY_RES_OTHR", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_CTY_PCTY", "PHY_CTY_PCTY", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_TIM_PTIM", "PHY_TIM_PTIM", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_ART_PART", "PHY_ART_PART", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_TPT_PTPT", "PHY_TPT_PTPT", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_OTH_OTHR", "PHY_OTH_OTHR", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_OAM_MMFC", "CIU_OAM_MMFC", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_OAM_AETF", "CIU_OAM_AETF", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_OAM_OTHR", "CIU_OAM_OTHR", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_NAM_MMFC", "CIU_NAM_MMFC", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_NAM_AETF", "CIU_NAM_AETF", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_NAM_OTHR", "CIU_NAM_OTHR", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"OTH_OTH_OTHR", "OTH_OTH_OTHR", versionAdmin));
		repotFieldList.add(new ReportFieldList("SubAssetTypeType",
				"NTA_NTA_NOTA", "NTA_NTA_NOTA", versionAdmin));

		repotFieldList.add(new ReportFieldList("TransactionTypeType", "ACAP",
				"ACAP", versionAdmin));
		repotFieldList.add(new ReportFieldList("TransactionTypeType", "BOUT",
				"BOUT", versionAdmin));
		repotFieldList.add(new ReportFieldList("TransactionTypeType", "CONS",
				"CONS", versionAdmin));
		repotFieldList.add(new ReportFieldList("TransactionTypeType", "CDIV",
				"CDIV", versionAdmin));
		repotFieldList.add(new ReportFieldList("TransactionTypeType", "ESOP",
				"ESOP", versionAdmin));
		repotFieldList.add(new ReportFieldList("TransactionTypeType", "GCAP",
				"GCAP", versionAdmin));
		repotFieldList.add(new ReportFieldList("TransactionTypeType", "RCAP",
				"RCAP", versionAdmin));
		repotFieldList.add(new ReportFieldList("TransactionTypeType", "SLIQ",
				"SLIQ", versionAdmin));
		repotFieldList.add(new ReportFieldList("TransactionTypeType", "TURN",
				"TURN", versionAdmin));
		repotFieldList.add(new ReportFieldList("TransactionTypeType", "OTHR",
				"OTHR", versionAdmin));

		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_CSH_CSH", "SEC_CSH_CSH", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_LEQ_LEQ", "SEC_LEQ_LEQ", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_UEQ_UEQ", "SEC_UEQ_UEQ", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_CPN_IVG", "SEC_CPN_IVG", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_CPN_NIG", "SEC_CPN_NIG", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_CPI_CPI", "SEC_CPI_CPI", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_SBD_EUB", "SEC_SBD_EUB", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_SBD_NEU", "SEC_SBD_NEU", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_MUN_MUN", "SEC_MUN_MUN", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_CBD_CBD", "SEC_CBD_CBD", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_LON_LON", "SEC_LON_LON", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_SSP_SSP", "SEC_SSP_SSP", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_EQD_EQD", "DER_EQD_EQD", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_FID_FID", "DER_FID_FID", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_CDS_CDS", "DER_CDS_CDS", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_FEX_INV", "DER_FEX_INV", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_FEX_HED", "DER_FEX_HED", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_IRD_IRD", "DER_IRD_IRD", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_CTY_CTY", "DER_CTY_CTY", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_OTH_OTH", "DER_OTH_OTH", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_RES_RES", "PHY_RES_RES", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_CTY_CTY", "PHY_CTY_CTY", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_TIM_TIM", "PHY_TIM_TIM", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_ART_ART", "PHY_ART_ART", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_TPT_TPT", "PHY_TPT_TPT", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_OTH_OTH", "PHY_OTH_OTH", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"CIU_CIU_CIU", "CIU_CIU_CIU", versionAdmin));
		repotFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"OTH_OTH_OTH", "OTH_OTH_OTH", versionAdmin));

		repotFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"V_SMALL", "V_SMALL", versionAdmin));
		repotFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"SMALL", "SMALL", versionAdmin));
		repotFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"LOW_MID_MKT", "LOW_MID_MKT", versionAdmin));
		repotFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"UP_MID_MKT", "UP_MID_MKT", versionAdmin));
		repotFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"L_CAP", "L_CAP", versionAdmin));
		repotFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"M_CAP", "M_CAP", versionAdmin));

		repotFieldList.add(new ReportFieldList(
				"VARCalculationMethodCodeTypeType", "HISTO", "HISTO",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"VARCalculationMethodCodeTypeType", "CARLO", "CARLO",
				versionAdmin));
		repotFieldList.add(new ReportFieldList(
				"VARCalculationMethodCodeTypeType", "PARAM", "PARAM",
				versionAdmin));

		for (ReportFieldList reportFieldListExample : repotFieldList) {
			reportFieldListDAO.create(reportFieldListExample);
		}

	}

	public void installExampleAIFM(ApplicationContext applicationContext) {

		// TODO make better example

		VersionAuditor versionAdmin = new VersionAuditor("admin");

		// ReportData reportData1 = new ReportData(null, reportField1,
		// reportExecution, null, null, "GB", null, null, null,
		// versionAdmin);
		// ReportData reportData2 = new ReportData(null, reportField2,
		// reportExecution, null, null, "1.2", null, null, null,
		// versionAdmin);
		// ReportData reportData3 = new ReportData(null, reportField3,
		// reportExecution, null, null, "2014-09-01 01:02:03", null, null,
		// null, versionAdmin);
		// ReportData reportData4 = new ReportData(null, reportField4,
		// reportExecution, null, null, "INIT", null, null, null,
		// versionAdmin);
		// ReportData reportData5 = new ReportData(null, reportField5,
		// reportExecution, null, null, "2", null, null, null,
		// versionAdmin);
		// ReportData reportData6 = new ReportData(null, reportField6,
		// reportExecution, null, null, "2014-09-01", null, null, null,
		// versionAdmin);
		// ReportData reportData7 = new ReportData(null, reportField7,
		// reportExecution, null, null, "2014-12-31", null, null, null,
		// versionAdmin);
		// ReportData reportData8 = new ReportData(null, reportField8,
		// reportExecution, null, null, "Q4", null, null, null,
		// versionAdmin);
		// ReportData reportData9 = new ReportData(null, reportField9,
		// reportExecution, null, null, "2014", null, null, null,
		// versionAdmin);
		// ReportData reportData10 = new ReportData(null, reportField10,
		// reportExecution, null, null, "QH", null, null, null,
		// versionAdmin);
		// ReportData reportData11 = new ReportData(null, reportField11,
		// reportExecution, null, null, "6", null, null, null,
		// versionAdmin);
		// ReportData reportData12 = new ReportData(null, reportField12,
		// reportExecution, null, null, "Q1", null, null, null,
		// versionAdmin);
		// ReportData reportData13 = new ReportData(null, reportField13,
		// reportExecution, null, null, "false", null, null, null,
		// versionAdmin);
		// ReportData reportData14 = new ReportData(null, reportField14,
		// reportExecution, null, null, "25", "1", null, null,
		// versionAdmin);
		// ReportData reportData15 = new ReportData(null, reportField15,
		// reportExecution, null, null, "Descripcion pregunta", "1", null,
		// null, versionAdmin);
		// ReportData reportData16 = new ReportData(null, reportField16,
		// reportExecution, null, null, "4", null, null, null,
		// versionAdmin);
		// ReportData reportData17 = new ReportData(null, reportField17,
		// reportExecution, null, null, "GB", null, null, null,
		// versionAdmin);
		// ReportData reportData18 = new ReportData(null, reportField18,
		// reportExecution, null, null, "474286", null, null, null,
		// versionAdmin);
		// ReportData reportData19 = new ReportData(null, reportField19,
		// reportExecution, null, null, "AIFM 1", null, null, null,
		// versionAdmin);
		// ReportData reportData20 = new ReportData(null, reportField20,
		// reportExecution, null, null, "true", null, null, null,
		// versionAdmin);
		// ReportData reportData21 = new ReportData(null, reportField21,
		// reportExecution, null, null, "false", null, null, null,
		// versionAdmin);
		// ReportData reportData22 = new ReportData(null, reportField22,
		// reportExecution, null, null, "969500AA77L4ZJXJ0T02", null,
		// null, null, versionAdmin);
		// ReportData reportData23 = new ReportData(null, reportField23,
		// reportExecution, null, null, "TESTGB21XXX", null, null, null,
		// versionAdmin);
		// ReportData reportData24 = new ReportData(null, reportField24,
		// reportExecution, null, null, "GB", null, null, null,
		// versionAdmin);
		// ReportData reportData25 = new ReportData(null, reportField25,
		// reportExecution, null, null, "UK", null, null, null,
		// versionAdmin);
		//
		// ReportData reportData26 = new ReportData(null, reportField26,
		// reportExecution, null, null, "1", "1", null, null, versionAdmin);
		// ReportData reportData27 = new ReportData(null, reportField27,
		// reportExecution, null, null, "XXX", "1", null, null,
		// versionAdmin);
		// ReportData reportData28 = new ReportData(null, reportField28,
		// reportExecution, null, null, "EU", "1", null, null,
		// versionAdmin);
		// ReportData reportData29 = new ReportData(null, reportField29,
		// reportExecution, null, null, "452000000", "1", null, null,
		// versionAdmin);
		//
		// ReportData reportData30 = new ReportData(null, reportField26,
		// reportExecution, null, null, "2", "2", null, null, versionAdmin);
		// ReportData reportData31 = new ReportData(null, reportField27,
		// reportExecution, null, null, "MIC", "2", null, null,
		// versionAdmin);
		// ReportData reportData32 = new ReportData(null, reportField28,
		// reportExecution, null, null, "XEUR", "2", null, null,
		// versionAdmin);
		// ReportData reportData33 = new ReportData(null, reportField29,
		// reportExecution, null, null, "42800000", "2", null, null,
		// versionAdmin);
		//
		// ReportData reportData34 = new ReportData(null, reportField30,
		// reportExecution, null, null, "1", "2", null, null, versionAdmin);
		// ReportData reportData35 = new ReportData(null, reportField31,
		// reportExecution, null, null, "DER_FID_FIXI", "2", null, null,
		// versionAdmin);
		// ReportData reportData36 = new ReportData(null, reportField32,
		// reportExecution, null, null, "10010", "2", null, null,
		// versionAdmin);
		//
		// ReportData reportData37 = new ReportData(null, reportField33,
		// reportExecution, null, null, "5500000", "2", null, null,
		// versionAdmin);
		// ReportData reportData38 = new ReportData(null, reportField34,
		// reportExecution, null, null, "51000", "2", null, null,
		// versionAdmin);
		// ReportData reportData39 = new ReportData(null, reportField35,
		// reportExecution, null, null, "USD", "2", null, null,
		// versionAdmin);
		// ReportData reportData40 = new ReportData(null, reportField36,
		// reportExecution, null, null, "ECB", "2", null, null,
		// versionAdmin);
		// ReportData reportData41 = new ReportData(null, reportField37,
		// reportExecution, null, null, "123444.7535", "2", null, null,
		// versionAdmin);
		// ReportData reportData42 = new ReportData(null, reportField38,
		// reportExecution, null, null, "description", "2", null, null,
		// versionAdmin);
		//
		// ReportData reportData43 = new ReportData(null, reportField39,
		// reportExecution, null, null, "Id2", "2", null, null,
		// versionAdmin);
		// ReportData reportData44 = new ReportData(null, reportField40,
		// reportExecution, null, null, "X1", "2", null, null,
		// versionAdmin);
		// ReportData reportData45 = new ReportData(null, reportField41,
		// reportExecution, null, null, "2014", "2", null, null,
		// versionAdmin);
		// ReportData reportData46 = new ReportData(null, reportField42,
		// reportExecution, null, null, "C", "2", null, null, versionAdmin);
		//
		// ReportData reportData47 = new ReportData(null, reportField14,
		// reportExecution, null, null, "21", "2", null, null,
		// versionAdmin);
		// ReportData reportData48 = new ReportData(null, reportField15,
		// reportExecution, null, null, "Descripcion pregunta 2", "2",
		// null, null, versionAdmin);
		//
		// LoadFile loadFile = new LoadFile(department, fileConfig, date1,
		// "Fichero1.txt", null, null, versionAdmin);

		// LoadRaw loadRaw = new LoadRaw(loadFile, new BigDecimal(1), "SIMPLE",
		// null, null, null, versionAdmin);
		//
		// LoadRawData loadRawData1 = new LoadRawData(fileColum1, loadRaw,
		// "GESTORA1", "TEXT", versionAdmin);
		// LoadRawData loadRawData2 = new LoadRawData(fileColum2, loadRaw,
		// "01-10-2014", "DATE", versionAdmin);
		// LoadRawData loadRawData3 = new LoadRawData(fileColum3, loadRaw, "GB",
		// "TEXT", versionAdmin);
		// LoadRawData loadRawData4 = new LoadRawData(fileColum4, loadRaw,
		// "FIRST", "TEXT", versionAdmin);
		// LoadRawData loadRawData5 = new LoadRawData(fileColum5, loadRaw, "2",
		// "TEXT", versionAdmin);
		// LoadRawData loadRawData6 = new LoadRawData(fileColum6, loadRaw,
		// "01-10-2014", "DATE", versionAdmin);
		// LoadRawData loadRawData7 = new LoadRawData(fileColum7, loadRaw,
		// "31-12-2014", "DATE", versionAdmin);
		// LoadRawData loadRawData8 = new LoadRawData(fileColum8, loadRaw, "Q4",
		// "TEXT", versionAdmin);
		// LoadRawData loadRawData9 = new LoadRawData(fileColum9, loadRaw,
		// "2014",
		// "TEXT", versionAdmin);
		// LoadRawData loadRawData10 = new LoadRawData(fileColum10, loadRaw,
		// "QH",
		// "TEXT", versionAdmin);
		// LoadRawData loadRawData11 = new LoadRawData(fileColum11, loadRaw,
		// "Q4",
		// "TEXT", versionAdmin);
		// LoadRawData loadRawData12 = new LoadRawData(fileColum12, loadRaw,
		// "false", "TEXT", versionAdmin);
		// LoadRawData loadRawData13 = new LoadRawData(fileColum13, loadRaw,
		// "4",
		// "TEXT", versionAdmin);
		// LoadRawData loadRawData14 = new LoadRawData(fileColum14, loadRaw,
		// "GB",
		// "TEXT", versionAdmin);
		// LoadRawData loadRawData15 = new LoadRawData(fileColum15, loadRaw,
		// "474286", "TEXT", versionAdmin);
		// LoadRawData loadRawData16 = new LoadRawData(fileColum16, loadRaw,
		// "AIFM 1", "TEXT", versionAdmin);
		// LoadRawData loadRawData17 = new LoadRawData(fileColum17, loadRaw,
		// "true", "TEXT", versionAdmin);
		// LoadRawData loadRawData18 = new LoadRawData(fileColum18, loadRaw,
		// "false", "TEXT", versionAdmin);
		// LoadRawData loadRawData19 = new LoadRawData(fileColum19, loadRaw,
		// "969500AA77L4ZJXJ0T02", "TEXT", versionAdmin);
		// LoadRawData loadRawData20 = new LoadRawData(fileColum20, loadRaw,
		// "TESTGB21XXX", "TEXT", versionAdmin);
		// LoadRawData loadRawData21 = new LoadRawData(fileColum21, loadRaw,
		// "1",
		// "TEXT", versionAdmin);
		// LoadRawData loadRawData22 = new LoadRawData(fileColum22, loadRaw,
		// "XXX", "TEXT", versionAdmin);
		// LoadRawData loadRawData23 = new LoadRawData(fileColum23, loadRaw, "",
		// "TEXT", versionAdmin);

		// ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
		// .getBean("reportDataDAO");
		// reportDataDAO.create(reportData1);
		// reportDataDAO.create(reportData2);
		// reportDataDAO.create(reportData3);
		// reportDataDAO.create(reportData4);
		// reportDataDAO.create(reportData5);
		// reportDataDAO.create(reportData6);
		// reportDataDAO.create(reportData7);
		// reportDataDAO.create(reportData8);
		// reportDataDAO.create(reportData9);
		// reportDataDAO.create(reportData10);
		// reportDataDAO.create(reportData11);
		// reportDataDAO.create(reportData12);
		// reportDataDAO.create(reportData13);
		// reportDataDAO.create(reportData14);
		// reportDataDAO.create(reportData15);
		// reportDataDAO.create(reportData16);
		// reportDataDAO.create(reportData17);
		// reportDataDAO.create(reportData18);
		// reportDataDAO.create(reportData19);
		// reportDataDAO.create(reportData20);
		// reportDataDAO.create(reportData21);
		// reportDataDAO.create(reportData22);
		// reportDataDAO.create(reportData23);
		// reportDataDAO.create(reportData24);
		// reportDataDAO.create(reportData25);
		// reportDataDAO.create(reportData26);
		// reportDataDAO.create(reportData27);
		// reportDataDAO.create(reportData28);
		// reportDataDAO.create(reportData29);
		// reportDataDAO.create(reportData30);
		// reportDataDAO.create(reportData31);
		// reportDataDAO.create(reportData32);
		// reportDataDAO.create(reportData33);
		// reportDataDAO.create(reportData34);
		// reportDataDAO.create(reportData35);
		// reportDataDAO.create(reportData36);
		// reportDataDAO.create(reportData37);
		// reportDataDAO.create(reportData38);
		// reportDataDAO.create(reportData39);
		// reportDataDAO.create(reportData40);
		// reportDataDAO.create(reportData41);
		// reportDataDAO.create(reportData42);
		// reportDataDAO.create(reportData43);
		// reportDataDAO.create(reportData44);
		// reportDataDAO.create(reportData45);
		// reportDataDAO.create(reportData46);
		// reportDataDAO.create(reportData47);
		// reportDataDAO.create(reportData48);

		// LoadFileDAO loadFileDAO = (LoadFileDAO) applicationContext
		// .getBean("loadFileDAO");
		// loadFileDAO.create(loadFile);
		//
		// LoadRawDAO loadRawDAO = (LoadRawDAO) applicationContext
		// .getBean("loadRawDAO");
		// loadRawDAO.create(loadRaw);
		//
		// LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO)
		// applicationContext
		// .getBean("loadRawDataDAO");
		// loadRawDataDAO.create(loadRawData1);
		// loadRawDataDAO.create(loadRawData2);
		// loadRawDataDAO.create(loadRawData3);
		// loadRawDataDAO.create(loadRawData4);
		// loadRawDataDAO.create(loadRawData5);
		// loadRawDataDAO.create(loadRawData6);
		// loadRawDataDAO.create(loadRawData7);
		// loadRawDataDAO.create(loadRawData8);
		// loadRawDataDAO.create(loadRawData9);
		// loadRawDataDAO.create(loadRawData10);
		// loadRawDataDAO.create(loadRawData11);
		// loadRawDataDAO.create(loadRawData12);
		// loadRawDataDAO.create(loadRawData13);
		// loadRawDataDAO.create(loadRawData14);
		// loadRawDataDAO.create(loadRawData15);
		// loadRawDataDAO.create(loadRawData16);
		// loadRawDataDAO.create(loadRawData17);
		// loadRawDataDAO.create(loadRawData18);
		// loadRawDataDAO.create(loadRawData19);
		// loadRawDataDAO.create(loadRawData20);
		// loadRawDataDAO.create(loadRawData21);
		// loadRawDataDAO.create(loadRawData22);
		// loadRawDataDAO.create(loadRawData23);

		// FileColumListDAO fileColumListDAO = (FileColumListDAO)
		// applicationContext
		// .getBean("fileColumListDAO");
		// fileColumListDAO.create(fileColumList1);
	}

	/**
	 * Process to delete all data from database
	 * 
	 * @param applicationContext
	 */
	public void deleteEntities(ApplicationContext applicationContext) {

		ReportSemanticDAO reportSemanticDAO = (ReportSemanticDAO) applicationContext
				.getBean("reportSemanticDAO");
		reportSemanticDAO.deleteAll();

		FileColumListDAO fileColumListDAO = (FileColumListDAO) applicationContext
				.getBean("fileColumListDAO");
		fileColumListDAO.deleteAll();

		ReportErrorDAO reportErrorDAO = (ReportErrorDAO) applicationContext
				.getBean("reportErrorDAO");
		reportErrorDAO.deleteAll();

		ReportDataErrorDAO reportDataErrorDAO = (ReportDataErrorDAO) applicationContext
				.getBean("reportDataErrorDAO");
		reportDataErrorDAO.deleteAll();

		LoadErrorDAO loadErrorDAO = (LoadErrorDAO) applicationContext
				.getBean("loadErrorDAO");
		loadErrorDAO.deleteAll();

		ErrorDAO errorDAO = (ErrorDAO) applicationContext.getBean("errorDAO");
		errorDAO.deleteAll();

		LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO) applicationContext
				.getBean("loadRawDataDAO");
		loadRawDataDAO.deleteAll();

		LoadRawDAO loadRawDAO = (LoadRawDAO) applicationContext
				.getBean("loadRawDAO");
		loadRawDAO.deleteAll();

		ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
				.getBean("reportDataDAO");
		reportDataDAO.deleteAll();

		FileColumDAO fileColumDAO = (FileColumDAO) applicationContext
				.getBean("fileColumDAO");
		fileColumDAO.deleteAll();

		ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) applicationContext
				.getBean("reportFieldListDAO");
		reportFieldListDAO.deleteAll();

		ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
				.getBean("reportFieldDAO");
		reportFieldDAO.deleteAll();

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");
		reportExecutionDAO.deleteAll();

		LoadFileDAO loadFileDAO = (LoadFileDAO) applicationContext
				.getBean("loadFileDAO");
		loadFileDAO.deleteAll();

		FileConfigDAO fileConfigDAO = (FileConfigDAO) applicationContext
				.getBean("fileConfigDAO");
		fileConfigDAO.deleteAll();

		ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
				.getBean("reportCatalogDAO");
		reportCatalogDAO.deleteAll();

		FundGroupDAO fundGroupDAO = (FundGroupDAO) applicationContext
				.getBean("fundGroupDAO");
		fundGroupDAO.deleteAll();

		FundDAO fundDAO = (FundDAO) applicationContext.getBean("fundDAO");
		fundDAO.deleteAll();

		DepartmentDAO departmentDAO = (DepartmentDAO) applicationContext
				.getBean("departmentDAO");
		departmentDAO.deleteAll();

		CompanyDAO companyDAO = (CompanyDAO) applicationContext
				.getBean("companyDAO");
		companyDAO.deleteAll();

	}
}
