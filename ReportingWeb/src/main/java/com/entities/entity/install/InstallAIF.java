package com.entities.entity.install;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.dao.reportingtool.ReportFieldDAO;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportField;
import com.entities.utilities.hibernate.VersionAuditor;
import com.reportingtool.utilities.ReportUtilities;

public class InstallAIF {

	private ApplicationContext applicationContext;

	public InstallAIF(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Process to install in database reportCatalog and reportFields from AIF
	 * report
	 * 
	 * @param applicationContext
	 */
	public void install() {

		// TODO:RT not inserting company/department by default in the future

		try {
			VersionAuditor versionAdmin = new VersionAuditor("admin");

			String versionField = ReportUtilities.reportVersion;

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
					null, null, "01.000", "1,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportFieldx1);

			// (1) <ReportingMemberState>
			ReportField reportField1 = new ReportField(reportCatalog,
					reportFieldx1, "A", "ReportingMemberState", new BigInteger(
							"1"), ".{2}", "", "General Info",
					"CountryCodeType", "01.001", "1,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField1);

			// (2) <Version>
			ReportField reportField2 = new ReportField(reportCatalog,
					reportFieldx1, "A", "Version", new BigInteger("2"),
					"([0-9])+\\.([0-9])+", "", "General Info", "VERSION",
					"01.002", "1,1", versionField, null, null, null,
					versionAdmin);
			reportField2.setReportFieldEditable(true);
			reportFieldDAO.create(reportField2);

			// (3) <CreationDateAndTime>
			ReportField reportField3 = new ReportField(reportCatalog,
					reportFieldx1, "D", "CreationDateAndTime", new BigInteger(
							"3"),
					"[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}",
					"", "General Info", "DATETIME", "01.003", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField3.setReportFieldEditable(true);
			reportFieldDAO.create(reportField3);

			ReportField reportFieldx2 = new ReportField(reportCatalog,
					reportFieldx1, "X", "AIFRecordInfo", new BigInteger("0"),
					null, "", null, null, "01.1", "1,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx2);

			// (4) <FilingType>
			ReportField reportField4 = new ReportField(reportCatalog,
					reportFieldx2, "A", "FilingType", new BigInteger("4"),
					".{4}", "", "General Info", "FilingTypeType", "01.004",
					"1,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField4);

			// (5) <AIFContentType>
			ReportField reportField5 = new ReportField(reportCatalog,
					reportFieldx2, "N", "AIFContentType", new BigInteger("5"),
					"[0-9]{1}", "", "General Info", "AIFContentTypeType",
					"01.005", "1,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField5);

			// (6) <ReportingPeriodStartDate>
			ReportField reportField6 = new ReportField(reportCatalog,
					reportFieldx2, "D", "ReportingPeriodStartDate",
					new BigInteger("6"), "[0-9]{4}-[0-9]{2}-[0-9]{2}", "",
					"General Info", "DATE", "01.006", "1,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField6);

			// (7) <ReportingPeriodEndDate>
			ReportField reportField7 = new ReportField(reportCatalog,
					reportFieldx2, "D", "ReportingPeriodEndDate",
					new BigInteger("7"), "[0-9]{4}-[0-9]{2}-[0-9]{2}", "",
					"General Info", "DATE", "01.007", "1,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField7);

			// (8) <ReportingPeriodType>
			ReportField reportField8 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ReportingPeriodType", new BigInteger(
							"8"), ".{2}", "", "General Info",
					"ReportingPeriodTypeType", "01.008", "1,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField8);

			// (9) <ReportingPeriodYear>
			ReportField reportField9 = new ReportField(reportCatalog,
					reportFieldx2, "N", "ReportingPeriodYear", new BigInteger(
							"9"), "[0-9]{4}", "", "General Info", "YEAR",
					"01.009", "1,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField9);

			// (10) <AIFReportingObligationChangeFrequencyCode>
			ReportField reportField10 = new ReportField(reportCatalog,
					reportFieldx2, "A",
					"AIFReportingObligationChangeFrequencyCode",
					new BigInteger("10"), ".{2}", "", "General Info",
					"ReportingObligationChangeFrequencyCodeType", "01.010",
					"0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField10);

			// (11) <AIFReportingObligationChangeContentsCode>
			ReportField reportField11 = new ReportField(reportCatalog,
					reportFieldx2, "N",
					"AIFReportingObligationChangeContentsCode", new BigInteger(
							"11"), "[0-9]{1}", "", "General Info",
					"AIFReportingObligationChangeContentsCodeType", "01.011",
					"0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField11);

			// (12) <AIFReportingObligationChangeQuarter>
			ReportField reportField12 = new ReportField(reportCatalog,
					reportFieldx2, "A", "AIFReportingObligationChangeQuarter",
					new BigInteger("12"), ".{2}", "", "General Info",
					"ReportingObligationChangeQuarterType", "01.012", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField12);

			// (13) <LastReportingFlag>
			ReportField reportField13 = new ReportField(reportCatalog,
					reportFieldx2, "B", "LastReportingFlag", new BigInteger(
							"13"), "true|false", "", "General Info", "BOOLEAN",
					"01.013", "1,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField13);

			ReportField reportFieldx3 = new ReportField(reportCatalog,
					reportFieldx2, "X", "Assumptions", new BigInteger("0"),
					null, "", null, null, "02.1", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx3);
			ReportField reportFieldx4 = new ReportField(reportCatalog,
					reportFieldx3, "X", "Assumption", new BigInteger("0"),
					null, "", null, null, "02.1", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx4);

			// (14) <QuestionNumber>
			ReportField reportField14 = new ReportField(reportCatalog,
					reportFieldx4, "N", "QuestionNumber", new BigInteger("14"),
					"[0-9]{0,3}", "", "Assumptions", "QUESTION", "02.014",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField14);

			// (15) <AssumptionDescription>
			ReportField reportField15 = new ReportField(reportCatalog,
					reportFieldx4, "Z", "AssumptionDescription",
					new BigInteger("15"), ".{0,300}", "", "Assumptions",
					"DESCRIPTION", "02.015", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField15);

			// (16) <AIFMNationalCode>
			ReportField reportField16 = new ReportField(reportCatalog,
					reportFieldx2, "Z", "AIFMNationalCode",
					new BigInteger("16"), ".{0,30}", "", "General Info",
					"AIFMNationalCodeType", "01.016", "1,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField16);

			// (17) <AIFNationalCode>
			ReportField reportField17 = new ReportField(reportCatalog,
					reportFieldx2, "Z", "AIFNationalCode",
					new BigInteger("17"), ".{0,30}", "", "General Info",
					"AIFNationalCodeType", "01.017", "1,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField17);

			// (18) <AIFName>
			ReportField reportField18 = new ReportField(reportCatalog,
					reportFieldx2, "Z", "AIFName", new BigInteger("18"),
					".{0,300}", "", "General Info", "AIF_NAME", "01.018",
					"1,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField18);

			// (19) <AIFEEAFlag>
			ReportField reportField19 = new ReportField(reportCatalog,
					reportFieldx2, "B", "AIFEEAFlag", new BigInteger("19"),
					"true|false", "", "General Info", "BOOLEAN", "01.019",
					"1,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField19);

			// (20) <AIFReportingCode>
			ReportField reportField20 = new ReportField(reportCatalog,
					reportFieldx2, "A", "AIFReportingCode",
					new BigInteger("20"), ".{2}", "", "General Info",
					"CountryCodeType", "01.020", "1,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField20);

			// (21) <AIFDomicile>
			ReportField reportField21 = new ReportField(reportCatalog,
					reportFieldx2, "A", "AIFDomicile", new BigInteger("21"),
					".{2}", "", "General Info", "CountryCodeType", "01.021",
					"1,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField21);

			// (22) <InceptionDate>
			ReportField reportField22 = new ReportField(reportCatalog,
					reportFieldx2, "D", "InceptionDate", new BigInteger("22"),
					"[0-9]{4}-[0-9]{2}-[0-9]{2}", "", "General Info", "DATE",
					"01.022", "1,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField22);

			// (23) <AIFNoReportingFlag>
			ReportField reportField23 = new ReportField(reportCatalog,
					reportFieldx2, "B", "AIFNoReportingFlag", new BigInteger(
							"23"), "true|false", "", "General Info", "BOOLEAN",
					"01.023", "1,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField23);

			// /////////////////////////////////////////////////////////////

			ReportField reportFieldx5 = new ReportField(reportCatalog,
					reportFieldx2, "X", "AIFCompleteDescription",
					new BigInteger("0"), null, "", null, null, "03.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx5);

			ReportField reportFieldx6 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFPrincipalInfo",
					new BigInteger("0"), null, "", null, null, "03.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx6);
			ReportField reportFieldx7 = new ReportField(reportCatalog,
					reportFieldx6, "X", "AIFIdentification",
					new BigInteger("0"), null, "", null, null, "03.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx7);

			// (24) <AIFIdentifierLEI>
			ReportField reportField24 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierLEI",
					new BigInteger("24"), ".{20}", "", "Complete Description",
					"AIFIdentification", "03.024", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField24);

			// (25) <AIFIdentifierISIN>
			ReportField reportField25 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierISIN", new BigInteger(
							"25"), "[A-Z]{2}([A-Z]|[0-9]){9}[0-9]", "",
					"Complete Description", "ISINInstrumentIdentificationType",
					"03.025", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField25);

			// (26) <AIFIdentifierCUSIP>
			ReportField reportField26 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierCUSIP", new BigInteger(
							"26"), ".{9}", "", "Complete Description",
					"CusipCodeType", "03.026", "0,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField26);

			// (27) <AIFIdentifierSEDOL>
			ReportField reportField27 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierSEDOL", new BigInteger(
							"27"), ".{7}", "", "Complete Description",
					"SedolCodeType", "03.027", "0,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField27);

			// (28) <AIFIdentifierTicker>
			ReportField reportField28 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierTicker", new BigInteger(
							"28"), ".{20}", "", "Complete Description",
					"TickerCodeType", "03.028", "0,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField28);

			// (29) <AIFIdentifierRIC>
			ReportField reportField29 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierRIC",
					new BigInteger("29"), ".{20}", "", "Complete Description",
					"RICCodeType", "03.029", "0,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField29);

			// (30) <AIFIdentifierECB>
			ReportField reportField30 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierECB",
					new BigInteger("30"), ".{20}", "", "Complete Description",
					"ECBCodeType", "03.030", "0,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField30);

			// (31) <Old_ReportingMemberState>
			ReportField reportField31 = new ReportField(reportCatalog,
					reportFieldx7, "A", "Old_ReportingMemberState", new BigInteger(
							"31"), ".{2}", "", "Complete Description",
					"CountryCodeType", "03.031", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField31);

			// (32) <Old_AIFNationalCode>
			ReportField reportField32 = new ReportField(reportCatalog,
					reportFieldx7, "A", "Old_AIFNationalCode",
					new BigInteger("32"), ".{30}", "", "Complete Description",
					"AIFMNationalCodeType", "03.032", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField32);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx8 = new ReportField(reportCatalog,
					reportFieldx7, "X", "ShareClassIdentifier", new BigInteger(
							"0"), null, "", null, null, "04.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx8);

			// (33) <ShareClassFlag>
			ReportField reportField33 = new ReportField(reportCatalog,
					reportFieldx8, "B", "ShareClassFlag", new BigInteger("33"),
					"true|false", "", "Share Class Identifier", "BOOLEAN",
					"04.033", "1,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField33);

			// (34) <ShareClassNationalCode>
			ReportField reportField34 = new ReportField(reportCatalog,
					reportFieldx8, "Z", "ShareClassNationalCode",
					new BigInteger("34"), ".{0,30}", "",
					"Share Class Identifier", "ShareClassNationalCodeType",
					"04.034", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField34);

			// (35) <ShareClassIdentifierISIN>
			ReportField reportField35 = new ReportField(reportCatalog,
					reportFieldx8, "A", "ShareClassIdentifierISIN",
					new BigInteger("35"), "[A-Z]{2}([A-Z]|[0-9]){9}[0-9]", "",
					"Share Class Identifier",
					"ISINInstrumentIdentificationType", "04.035", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField35);

			// (36) <ShareClassIdentifierSEDOL>
			ReportField reportField36 = new ReportField(reportCatalog,
					reportFieldx8, "A", "ShareClassIdentifierSEDOL",
					new BigInteger("36"), ".{7}", "", "Share Class Identifier",
					"SedolCodeType", "04.036", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField36);

			// (37) <ShareClassIdentifierCUSIP>
			ReportField reportField37 = new ReportField(reportCatalog,
					reportFieldx8, "A", "ShareClassIdentifierCUSIP",
					new BigInteger("37"), ".{9}", "", "Share Class Identifier",
					"CusipCodeType", "04.037", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField37);

			// (38) <ShareClassIdentifierTicker>
			ReportField reportField38 = new ReportField(reportCatalog,
					reportFieldx8, "A", "ShareClassIdentifierTicker",
					new BigInteger("38"), ".{20}", "",
					"Share Class Identifier", "TickerCodeType", "04.038", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField38);

			// (39) <ShareClassIdentifierRIC>
			ReportField reportField39 = new ReportField(reportCatalog,
					reportFieldx8, "A", "ShareClassIdentifierRIC",
					new BigInteger("39"), ".{20}", "",
					"Share Class Identifier", "RICCodeType", "04.039", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField39);

			// (40) <ShareClassName>
			ReportField reportField40 = new ReportField(reportCatalog,
					reportFieldx8, "Z", "ShareClassName", new BigInteger("40"),
					".{0,300}", "", "Share Class Identifier", "SHARECLASS",
					"04.040", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField40);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx9 = new ReportField(reportCatalog,
					reportFieldx6, "X", "AIFDescription", new BigInteger("0"),
					null, "", null, null, "05.1", "1,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx9);

			// (41) <AIFMasterFeederStatus>
			ReportField reportField41 = new ReportField(reportCatalog,
					reportFieldx9, "A", "AIFMasterFeederStatus",
					new BigInteger("41"), ".{6}", "", "AIF Description",
					"AIFMasterFeederStatusType", "05.041", "1,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField41);

			ReportField reportFieldx10 = new ReportField(reportCatalog,
					reportFieldx9, "X", "MasterAIFsIdentification",
					new BigInteger("0"), null, "", null, null, "05.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx10);
			ReportField reportFieldx11 = new ReportField(reportCatalog,
					reportFieldx10, "X", "MasterAIFIdentification",
					new BigInteger("0"), null, "", null, null, "05.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx11);

			// (42) <AIFName>
			ReportField reportField42 = new ReportField(reportCatalog,
					reportFieldx11, "Z", "AIFName", new BigInteger("42"),
					".{0,300}", "", "AIF Description", "AIF_NAME", "05.042",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField42);

			// (43) <ReportingMemberState>
			ReportField reportField43 = new ReportField(reportCatalog,
					reportFieldx11, "A", "ReportingMemberState",
					new BigInteger("43"), ".{2}", "", "AIF Description",
					"CountryCodeType", "05.043", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField43);

			// (44) <AIFMNationalCode>
			ReportField reportField44 = new ReportField(reportCatalog,
					reportFieldx11, "Z", "AIFMNationalCode", new BigInteger(
							"44"), ".{0,30}", "", "AIF Description",
					"AIFNationalCodeType", "05.044", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField44);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx12 = new ReportField(reportCatalog,
					reportFieldx9, "X", "PrimeBrokers", new BigInteger("0"),
					null, "", null, null, "06.1", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx12);
			ReportField reportFieldx13 = new ReportField(reportCatalog,
					reportFieldx12, "X", "PrimeBrokerIdentification",
					new BigInteger("0"), null, "", null, null, "06.001", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx13);

			// (45) <EntityName>
			ReportField reportField45 = new ReportField(reportCatalog,
					reportFieldx13, "Z", "EntityName", new BigInteger("45"),
					".{0,300}", "", "Prime Brokers", "NAME", "06.045", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField45);

			// (46) <EntityIdentificationLEI>
			ReportField reportField46 = new ReportField(reportCatalog,
					reportFieldx13, "A", "EntityIdentificationLEI",
					new BigInteger("46"), ".{20}", "", "Prime Brokers",
					"LEICodeType", "06.046", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField46);

			// (47) <EntityIdentificationBIC>
			ReportField reportField47 = new ReportField(reportCatalog,
					reportFieldx13, "A", "EntityIdentificationBIC",
					new BigInteger("47"), ".{11}", "", "Prime Brokers",
					"BICCodeType", "06.047", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField47);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx14 = new ReportField(reportCatalog,
					reportFieldx9, "X", "AIFBaseCurrencyDescription",
					new BigInteger("0"), null, "", null, null, "07.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx14);

			// (48) <AUMAmountInBaseCurrency>
			ReportField reportField48 = new ReportField(reportCatalog,
					reportFieldx14, "N", "AUMAmountInBaseCurrency",
					new BigInteger("48"), "[0-9]{0,15}?", "",
					"AIF Base Currency Description", "NUMBER", "07.048", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField48);

			// (49) <BaseCurrency>
			ReportField reportField49 = new ReportField(reportCatalog,
					reportFieldx14, "A", "BaseCurrency", new BigInteger("49"),
					".{3}", "", "AIF Base Currency Description",
					"CurrencyCodeType", "07.049", "1,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField49);

			// (50) <FXEURRate>
			ReportField reportField50 = new ReportField(reportCatalog,
					reportFieldx14, "N", "FXEURRate", new BigInteger("50"),
					"[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"AIF Base Currency Description", "DECIMAL", "07.050", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField50);

			// (51) <FXEURReferenceRateType>
			ReportField reportField51 = new ReportField(reportCatalog,
					reportFieldx14, "A", "FXEURReferenceRateType",
					new BigInteger("51"), ".{3}", "",
					"AIF Base Currency Description",
					"FXEURReferenceRateTypeType", "07.051", "0,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField51);

			// (52) <FXEUROtherReferenceRateDescription>
			ReportField reportField52 = new ReportField(reportCatalog,
					reportFieldx14, "Z", "FXEUROtherReferenceRateDescription",
					new BigInteger("52"), ".{0,300}", "",
					"AIF Base Currency Description", "DESCRIPTION", "07.052",
					"0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField52);

			// (53) <AIFNetAssetValue>
			ReportField reportField53 = new ReportField(reportCatalog,
					reportFieldx9, "N", "AIFNetAssetValue",
					new BigInteger("53"), "[+|-]?[0-9]{1,15}", "",
					"AIF Base Currency Description", "NUMBER", "07.053", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField53);

			// //////////////////////////////////////////////////////////////

			// (54) <FirstFundingSourceCountry>
			ReportField reportField54 = new ReportField(reportCatalog,
					reportFieldx9, "A", "FirstFundingSourceCountry",
					new BigInteger("54"), ".{2}", "", "Jurisdiction",
					"CountryCodeType", "08.054", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField54);

			// (55) <SecondFundingSourceCountry>
			ReportField reportField55 = new ReportField(reportCatalog,
					reportFieldx9, "A", "SecondFundingSourceCountry",
					new BigInteger("55"), ".{2}", "", "Jurisdiction",
					"CountryCodeType", "08.055", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField55);

			// (56) <ThirdFundingSourceCountry>
			ReportField reportField56 = new ReportField(reportCatalog,
					reportFieldx9, "A", "ThirdFundingSourceCountry",
					new BigInteger("56"), ".{2}", "", "Jurisdiction",
					"CountryCodeType", "08.056", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField56);

			// //////////////////////////////////////////////////////////////

			// (57) <PredominantAIFType>
			ReportField reportField57 = new ReportField(reportCatalog,
					reportFieldx9, "A", "PredominantAIFType", new BigInteger(
							"57"), ".{4}", "", "AIF Type", "AIFTypeType",
					"09.057", "1,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField57);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx15 = new ReportField(reportCatalog,
					reportFieldx9, "X", "HedgeFundInvestmentStrategies",
					new BigInteger("0"), null, "", null, null, "10.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx15);
			ReportField reportFieldx16 = new ReportField(reportCatalog,
					reportFieldx15, "X", "HedgeFundStrategy", new BigInteger(
							"0"), null, "", null, null, "10.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx16);

			// (58) <HedgeFundStrategyType>
			ReportField reportField58_H = new ReportField(reportCatalog,
					reportFieldx16, "A", "HedgeFundStrategyType",
					new BigInteger("58"), ".{9}", "", "Hedge Fund Strategy",
					"HedgeFundStrategy", "10.058", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField58_H);

			// (59) <PrimaryStrategyFlag>
			ReportField reportField59_H = new ReportField(reportCatalog,
					reportFieldx16, "B", "PrimaryStrategyFlag", new BigInteger(
							"59"), "true|false", "", "Hedge Fund Strategy",
					"BOOLEAN", "10.059", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField59_H);

			// (60) <StrategyNAVRate>
			ReportField reportField60_H = new ReportField(reportCatalog,
					reportFieldx16, "N", "StrategyNAVRate",
					new BigInteger("60"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Hedge Fund Strategy", "DECIMAL", "10.060", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField60_H);

			ReportField reportFieldx17 = new ReportField(reportCatalog,
					reportFieldx9, "X",
					"PrivateEquityFundInvestmentStrategies",
					new BigInteger("0"), null, "", null, null, "10.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx17);
			ReportField reportFieldx18 = new ReportField(reportCatalog,
					reportFieldx17, "X", "PrivateEquityFundInvestmentStrategy",
					new BigInteger("0"), null, "", null, null, "10.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx18);

			// (58) <PrivateEquityFundStrategyType>
			ReportField reportField58_P = new ReportField(reportCatalog,
					reportFieldx18, "A", "PrivateEquityFundStrategyType",
					new BigInteger("58"), ".{9}", "", "Hedge Fund Strategy",
					"PrivateEquityFundInvestmentStrategy", "10.058", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField58_P);

			// (59) <PrimaryStrategyFlag>
			ReportField reportField59_P = new ReportField(reportCatalog,
					reportFieldx18, "B", "PrimaryStrategyFlag", new BigInteger(
							"59"), "true|false", "", "Hedge Fund Strategy",
					"BOOLEAN", "10.059", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField59_P);

			// (60) <StrategyNAVRate>
			ReportField reportField60_P = new ReportField(reportCatalog,
					reportFieldx18, "N", "StrategyNAVRate",
					new BigInteger("60"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Hedge Fund Strategy", "DECIMAL", "10.060", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField60_P);

			ReportField reportFieldx19 = new ReportField(reportCatalog,
					reportFieldx9, "X", "FundOfFundsInvestmentStrategies",
					new BigInteger("0"), null, "", null, null, "10.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx19);
			ReportField reportFieldx20 = new ReportField(reportCatalog,
					reportFieldx19, "X", "FundOfFundsStrategy", new BigInteger(
							"0"), null, "", null, null, "10.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx20);

			// (58) <FundOfFundsStrategyType>
			ReportField reportField58_F = new ReportField(reportCatalog,
					reportFieldx20, "A", "FundOfFundsStrategyType",
					new BigInteger("58"), ".{9}", "", "Hedge Fund Strategy",
					"FundOfFundsStrategy", "10.058", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField58_F);

			// (59) <PrimaryStrategyFlag>
			ReportField reportField59_F = new ReportField(reportCatalog,
					reportFieldx20, "B", "PrimaryStrategyFlag", new BigInteger(
							"59"), "true|false", "", "Hedge Fund Strategy",
					"BOOLEAN", "10.059", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField59_F);

			// (60) <StrategyNAVRate>
			ReportField reportField60_F = new ReportField(reportCatalog,
					reportFieldx20, "N", "StrategyNAVRate",
					new BigInteger("60"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Hedge Fund Strategy", "DECIMAL", "10.060", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField60_F);

			ReportField reportFieldx21 = new ReportField(reportCatalog,
					reportFieldx9, "X", "OtherFundInvestmentStrategies",
					new BigInteger("0"), null, "", null, null, "10.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx21);
			ReportField reportFieldx22 = new ReportField(reportCatalog,
					reportFieldx21, "X", "OtherFundStrategy", new BigInteger(
							"0"), null, "", null, null, "10.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx22);

			// (58) <OtherFundStrategyType>
			ReportField reportField58_O = new ReportField(reportCatalog,
					reportFieldx22, "A", "OtherFundStrategyType",
					new BigInteger("58"), ".{9}", "", "Hedge Fund Strategy",
					"OtherFundStrategy", "10.058", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField58_O);

			// (59) <PrimaryStrategyFlag>
			ReportField reportField59_O = new ReportField(reportCatalog,
					reportFieldx22, "B", "PrimaryStrategyFlag", new BigInteger(
							"59"), "true|false", "", "Hedge Fund Strategy",
					"BOOLEAN", "10.059", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField59_O);

			// (60) <StrategyNAVRate>
			ReportField reportField60_O = new ReportField(reportCatalog,
					reportFieldx22, "N", "StrategyNAVRate",
					new BigInteger("60"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Hedge Fund Strategy", "DECIMAL", "10.060", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField60_O);

			// (61) <StrategyTypeOtherDescription>
			ReportField reportField61_O = new ReportField(reportCatalog,
					reportFieldx22, "Z", "StrategyTypeOtherDescription",
					new BigInteger("61"), ".{300}", "", "Hedge Fund Strategy",
					"DESCRIPTION", "10.061", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField61_O);

			ReportField reportFieldx23 = new ReportField(reportCatalog,
					reportFieldx9, "X", "RealEstateFundInvestmentStrategies",
					new BigInteger("0"), null, "", null, null, "10.1",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx23);
			ReportField reportFieldx24 = new ReportField(reportCatalog,
					reportFieldx23, "X", "RealEstateFundStrategy",
					new BigInteger("0"), null, "", null, null, "10.1",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx24);

			// (58) <RealEstateFundStrategyType>
			ReportField reportField58_R = new ReportField(reportCatalog,
					reportFieldx24, "A", "RealEstateFundStrategyType",
					new BigInteger("58"), ".{9}", "", "Hedge Fund Strategy",
					"RealEstateFundStrategy", "10.058", "0,n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField58_R);

			// (59) <PrimaryStrategyFlag>
			ReportField reportField59_R = new ReportField(reportCatalog,
					reportFieldx24, "B", "PrimaryStrategyFlag", new BigInteger(
							"59"), "true|false", "", "Hedge Fund Strategy",
					"BOOLEAN", "10.059", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField59_R);

			// (60) <StrategyNAVRate>
			ReportField reportField60_R = new ReportField(reportCatalog,
					reportFieldx24, "N", "StrategyNAVRate",
					new BigInteger("60"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Hedge Fund Strategy", "DECIMAL", "10.060", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField60_R);

			// (62) <HFTTransactionNumber>
			ReportField reportField62 = new ReportField(reportCatalog,
					reportFieldx9, "N", "HFTTransactionNumber", new BigInteger(
							"62"), "[0-9]{0,15}?", "", "Hedge Fund Strategy",
					"NUMBER", "10.062", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField62);

			// (63) <HFTBuySellMarketValue>
			ReportField reportField63 = new ReportField(reportCatalog,
					reportFieldx9, "N", "HFTBuySellMarketValue",
					new BigInteger("63"), "[0-9]{0,15}?", "",
					"Hedge Fund Strategy", "NUMBER", "10.063", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField63);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx25 = new ReportField(reportCatalog,
					reportFieldx6, "X", "MainInstrumentsTraded",
					new BigInteger("0"), null, "", null, null, "11.1",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx25);
			ReportField reportFieldx26 = new ReportField(reportCatalog,
					reportFieldx25, "X", "MainInstrumentTraded",
					new BigInteger("0"), null, "", null, null, "11.1",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx26);

			// (64) <Ranking>
			ReportField reportField64 = new ReportField(reportCatalog,
					reportFieldx26, "N", "Ranking", new BigInteger("64"),
					"[0-9]{1}", "", "Main Instruments Traded",
					"FiveRankingType", "11.064", "5,5", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField64);

			// (65) <SubAssetType>
			ReportField reportField65 = new ReportField(reportCatalog,
					reportFieldx26, "Z", "SubAssetType", new BigInteger("65"),
					".{12}", "", "Main Instruments Traded", "SubAssetTypeType",
					"11.065", "5,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField65);

			// (66) <InstrumentCodeType>
			ReportField reportField66 = new ReportField(reportCatalog,
					reportFieldx26, "A", "InstrumentCodeType", new BigInteger(
							"66"), ".{4}", "", "Main Instruments Traded",
					"InstrumentCodeTypeType", "11.066", "0,5", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField66);

			// (67) <InstrumentName>
			ReportField reportField67 = new ReportField(reportCatalog,
					reportFieldx26, "Z", "InstrumentName",
					new BigInteger("67"), ".{300}", "",
					"Main Instruments Traded", "INSTR_NAME", "11.067", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField67);

			// (68) <ISINInstrumentIdentification>
			ReportField reportField68 = new ReportField(reportCatalog,
					reportFieldx26, "A", "ISINInstrumentIdentification",
					new BigInteger("68"), "[A-Z]{2}([A-Z]|[0-9]){9}[0-9]", "",
					"Main Instruments Traded",
					"ISINInstrumentIdentificationType", "11.068", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField68);

			ReportField reportFieldx27 = new ReportField(reportCatalog,
					reportFieldx26, "X", "AIIInstrumentIdentification",
					new BigInteger("0"), null, "", null, null, "11.1",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx27);

			// (69) <AIIExchangeCode>
			ReportField reportField69 = new ReportField(reportCatalog,
					reportFieldx27, "A", "AIIExchangeCode",
					new BigInteger("69"), ".{4}", "",
					"Main Instruments Traded", "MICCodeType", "11.069", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField69);

			// (70) <AIIProductCode>
			ReportField reportField70 = new ReportField(reportCatalog,
					reportFieldx27, "A", "AIIProductCode",
					new BigInteger("70"), ".{1,12}", "",
					"Main Instruments Traded", "AIIProductCodeType", "11.070",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField70);

			// (71) <AIIDerivativeType>
			ReportField reportField71 = new ReportField(reportCatalog,
					reportFieldx27, "A", "AIIDerivativeType", new BigInteger(
							"71"), ".{1}", "", "Main Instruments Traded",
					"AIIDerivativeTypeType", "11.071", "0,5", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField71);

			// (72) <AIIPutCallIdentifier>
			ReportField reportField72 = new ReportField(reportCatalog,
					reportFieldx27, "A", "AIIPutCallIdentifier",
					new BigInteger("72"), ".{1}", "",
					"Main Instruments Traded", "AIIPutCallIdentifierType",
					"11.072", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField72);

			// (73) <AIIExpiryDate>
			ReportField reportField73 = new ReportField(reportCatalog,
					reportFieldx27, "D", "AIIExpiryDate", new BigInteger("73"),
					"[0-9]{4}-[0-9]{2}-[0-9]{2}", "",
					"Main Instruments Traded", "DATE", "11.073", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField73);

			// (74) <AIIStrikePrice>
			ReportField reportField74 = new ReportField(reportCatalog,
					reportFieldx27, "N", "AIIStrikePrice",
					new BigInteger("74"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Main Instruments Traded", "DECIMAL", "11.076", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField74);

			// (75) <PositionType>
			ReportField reportField75 = new ReportField(reportCatalog,
					reportFieldx26, "A", "PositionType", new BigInteger("75"),
					".{1}", "", "Main Instruments Traded", "PositionTypeType",
					"11.075", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField75);

			// (76) <PositionValue>
			ReportField reportField76 = new ReportField(reportCatalog,
					reportFieldx26, "N", "PositionValue", new BigInteger("76"),
					"[0-9]{0,15}?", "", "Main Instruments Traded", "NUMBER",
					"11.076", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField76);

			// (77) <ShortPositionHedgingRate>
			ReportField reportField77 = new ReportField(reportCatalog,
					reportFieldx26, "N", "ShortPositionHedgingRate",
					new BigInteger("77"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Main Instruments Traded", "DECIMAL", "11.077", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField77);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx28 = new ReportField(reportCatalog,
					reportFieldx6, "X", "NAVGeographicalFocus", new BigInteger(
							"0"), null, "", null, null, "12.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx28);

			// (78) <AfricaNAVRate>
			ReportField reportField78 = new ReportField(reportCatalog,
					reportFieldx28, "N", "AfricaNAVRate", new BigInteger("78"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "DECIMAL", "12.078", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField78);

			// (79) <AsiaPacificNAVRate>
			ReportField reportField79 = new ReportField(reportCatalog,
					reportFieldx28, "N", "AsiaPacificNAVRate", new BigInteger(
							"79"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "DECIMAL", "12.079", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField79);

			// (80) <EuropeNAVRate>
			ReportField reportField80 = new ReportField(reportCatalog,
					reportFieldx28, "N", "EuropeNAVRate", new BigInteger("80"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "DECIMAL", "12.080", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField80);

			// (81) <EEANAVRate>
			ReportField reportField81 = new ReportField(reportCatalog,
					reportFieldx28, "N", "EEANAVRate", new BigInteger("81"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "DECIMAL", "12.081", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField81);

			// (82) <MiddleEastNAVRate>
			ReportField reportField82 = new ReportField(reportCatalog,
					reportFieldx28, "N", "MiddleEastNAVRate", new BigInteger(
							"82"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "DECIMAL", "12.082", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField82);

			// (83) <NorthAmericaNAVRate>
			ReportField reportField83 = new ReportField(reportCatalog,
					reportFieldx28, "N", "NorthAmericaNAVRate", new BigInteger(
							"83"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "DECIMAL", "12.083", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField83);

			// (84) <SouthAmericaNAVRate>
			ReportField reportField84 = new ReportField(reportCatalog,
					reportFieldx28, "N", "SouthAmericaNAVRate", new BigInteger(
							"84"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "DECIMAL", "12.084", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField84);

			// (85) <SupraNationalNAVRate>
			ReportField reportField85 = new ReportField(reportCatalog,
					reportFieldx28, "N", "SupraNationalNAVRate",
					new BigInteger("85"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "NAV Geographical Focus", "DECIMAL", "12.085", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField85);

			ReportField reportFieldx29 = new ReportField(reportCatalog,
					reportFieldx6, "X", "AUMGeographicalFocus", new BigInteger(
							"0"), null, "", null, null, "12.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx29);

			// (86) <AfricaAUMRate>
			ReportField reportField86 = new ReportField(reportCatalog,
					reportFieldx29, "N", "AfricaAUMRate", new BigInteger("86"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "NAV Geographical Focus",
					"PERCENT", "12.086", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField86);

			// (87) <AsiaPacificAUMRate>
			ReportField reportField87 = new ReportField(reportCatalog,
					reportFieldx29, "N", "AsiaPacificAUMRate", new BigInteger(
							"87"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "PERCENT", "12.087", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField87);

			// (88) <EuropeAUMRate>
			ReportField reportField88 = new ReportField(reportCatalog,
					reportFieldx29, "N", "EuropeAUMRate", new BigInteger("88"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "NAV Geographical Focus",
					"PERCENT", "12.088", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField88);

			// (89) <EEAAUMRate>
			ReportField reportField89 = new ReportField(reportCatalog,
					reportFieldx29, "N", "EEAAUMRate", new BigInteger("89"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "NAV Geographical Focus",
					"PERCENT", "12.089", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField89);

			// (90) <MiddleEastAUMRate>
			ReportField reportField90 = new ReportField(reportCatalog,
					reportFieldx29, "N", "MiddleEastAUMRate", new BigInteger(
							"90"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "PERCENT", "12.090", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField90);

			// (91) <NorthAmericaAUMRate>
			ReportField reportField91 = new ReportField(reportCatalog,
					reportFieldx29, "N", "NorthAmericaAUMRate", new BigInteger(
							"91"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "PERCENT", "12.091", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField91);

			// (92) <SouthAmericaAUMRate>
			ReportField reportField92 = new ReportField(reportCatalog,
					reportFieldx29, "N", "SouthAmericaAUMRate", new BigInteger(
							"92"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "PERCENT", "12.092", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField92);

			// (93) <SupraNationalAUMRate>
			ReportField reportField93 = new ReportField(reportCatalog,
					reportFieldx29, "N", "SupraNationalAUMRate",
					new BigInteger("93"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "PERCENT", "12.093", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField93);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx30 = new ReportField(reportCatalog,
					reportFieldx6, "X", "PrincipalExposures", new BigInteger(
							"0"), null, "", null, null, "14.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx30);
			ReportField reportFieldx31 = new ReportField(reportCatalog,
					reportFieldx30, "X", "PrincipalExposure", new BigInteger(
							"0"), null, "", null, null, "14.1", "10,10",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx31);

			// (94) <Ranking>
			ReportField reportField94 = new ReportField(reportCatalog,
					reportFieldx31, "N", "Ranking", new BigInteger("94"),
					"[0-9]{1,2}", "", "Principal Exposures", "TenRankingType",
					"14.094", "10,10", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField94);

			// (95) <AssetMacroType>
			ReportField reportField95 = new ReportField(reportCatalog,
					reportFieldx31, "A", "AssetMacroType",
					new BigInteger("95"), ".{3}", "", "Principal Exposures",
					"AssetMacroTypeType", "14.095", "10,10", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField95);

			// (96) <SubAssetType>
			ReportField reportField96 = new ReportField(reportCatalog,
					reportFieldx31, "Z", "SubAssetType", new BigInteger("96"),
					".{12}", "", "Principal Exposures", "SubAssetTypeType",
					"14.096", "0,10", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField96);

			// (97) <PositionType>
			ReportField reportField97 = new ReportField(reportCatalog,
					reportFieldx31, "A", "PositionType", new BigInteger("97"),
					".{1}", "", "Principal Exposures", "PositionTypeType",
					"14.097", "0,10", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField97);

			// (98) <AggregatedValueAmount>
			ReportField reportField98 = new ReportField(reportCatalog,
					reportFieldx31, "N", "AggregatedValueAmount",
					new BigInteger("98"), "[0-9]{1,15}?", "",
					"Principal Exposures", "NUMBER", "14.098", "0,10",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField98);

			// (99) <AggregatedValueRate>
			ReportField reportField99 = new ReportField(reportCatalog,
					reportFieldx31, "N", "AggregatedValueRate", new BigInteger(
							"99"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Principal Exposures", "PERCENT", "14.099", "0,10",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField99);

			// (100) <EntityName>
			ReportField reportField100 = new ReportField(reportCatalog,
					reportFieldx31, "Z", "EntityName", new BigInteger("100"),
					".{300}", "", "Principal Exposures", "NAME", "14.100",
					"0,10", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField100);

			// (101) <EntityIdentificationLEI>
			ReportField reportField101 = new ReportField(reportCatalog,
					reportFieldx31, "A", "EntityIdentificationLEI",
					new BigInteger("101"), ".{20}", "", "Principal Exposures",
					"LEICodeType", "14.101", "0,10", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField101);

			// (102) <EntityIdentificationBIC>
			ReportField reportField102 = new ReportField(reportCatalog,
					reportFieldx31, "A", "EntityIdentificationBIC",
					new BigInteger("102"), ".{11}", "", "Principal Exposures",
					"BICCodeType", "14.102", "0,10", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField102);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx32 = new ReportField(reportCatalog,
					reportFieldx6, "X", "MostImportantConcentration",
					new BigInteger("0"), null, "", null, null, "15.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx32);
			ReportField reportFieldx33 = new ReportField(reportCatalog,
					reportFieldx32, "X", "PortfolioConcentrations",
					new BigInteger("0"), null, "", null, null, "15.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx33);
			ReportField reportFieldx34 = new ReportField(reportCatalog,
					reportFieldx33, "X", "PortfolioConcentration",
					new BigInteger("0"), null, "", null, null, "15.1", "5,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx34);

			// (103) <Ranking>
			ReportField reportField103 = new ReportField(reportCatalog,
					reportFieldx34, "N", "Ranking", new BigInteger("103"),
					"[0-9]{1}", "", "Portfolio Concentration",
					"FiveRankingType", "15.103", "5,5", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField103);

			// (104) <AssetType>
			ReportField reportField104 = new ReportField(reportCatalog,
					reportFieldx34, "A", "AssetType", new BigInteger("104"),
					".{7}", "", "Portfolio Concentration",
					"AssetTypeType", "15.104", "5,5", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField104);

			// (105) <PositionType>
			ReportField reportField105 = new ReportField(reportCatalog,
					reportFieldx34, "A", "PositionType", new BigInteger("105"),
					".{1}", "", "Portfolio Concentration",
					"PositionTypeType", "15.105", "0,5", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField105);

			ReportField reportFieldx35 = new ReportField(reportCatalog,
					reportFieldx34, "X", "MarketIdentification",
					new BigInteger("0"), null, "", null, null, "15.1", "5,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx35);

			// (106) <MarketCodeType>
			ReportField reportField106 = new ReportField(reportCatalog,
					reportFieldx35, "A", "MarketCodeType",
					new BigInteger("106"), ".{3}", "",
					"Portfolio Concentration",
					"MarketCodeTypeWithoutNOTType", "15.106", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField106);

			// (107) <MarketCode>
			ReportField reportField107 = new ReportField(reportCatalog,
					reportFieldx35, "A", "MarketCode", new BigInteger("107"),
					".{4}", "", "Portfolio Concentration", "MICCodeType",
					"15.107", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField107);

			// (108) <AggregatedValueAmount>
			ReportField reportField108 = new ReportField(reportCatalog,
					reportFieldx34, "N", "AggregatedValueAmount",
					new BigInteger("108"), "[0-9]{0,15}?", "",
					"Portfolio Concentration", "NUMBER", "15.108", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField108);

			// (109) <AggregatedValueRate>
			ReportField reportField109 = new ReportField(reportCatalog,
					reportFieldx34, "N", "AggregatedValueRate", new BigInteger(
							"109"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Portfolio Concentration", "PERCENT", "15.109", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField109);

			ReportField reportFieldx36 = new ReportField(reportCatalog,
					reportFieldx35, "X", "CounterpartyIdentification",
					new BigInteger("0"), null, "", null, null, "15.1", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx36);

			// (110) <EntityName>
			ReportField reportField110 = new ReportField(reportCatalog,
					reportFieldx36, "Z", "EntityName", new BigInteger("110"),
					".{0,300}", "", "Portfolio Concentration", "NAME",
					"15.110", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField110);

			// (111) <EntityIdentificationLEI>
			ReportField reportField111 = new ReportField(reportCatalog,
					reportFieldx36, "A", "EntityIdentificationLEI",
					new BigInteger("111"), ".{20}", "",
					"Portfolio Concentration", "LEICodeType", "15.111",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField111);

			// (112) <EntityIdentificationBIC>
			ReportField reportField112 = new ReportField(reportCatalog,
					reportFieldx36, "A", "EntityIdentificationBIC",
					new BigInteger("112"), ".{11}", "",
					"Portfolio Concentration", "BICCodeType", "15.112",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField112);

			// //////////////////////////////////////////////////////////////

			// (113) <TypicalPositionSize>
			ReportField reportField113 = new ReportField(reportCatalog,
					reportFieldx32, "A", "TypicalPositionSize", new BigInteger(
							"113"), ".{4}", "", "Typical Position Size",
					"TypicalPositionSizeType", "16.113", "0,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField113);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx37 = new ReportField(reportCatalog,
					reportFieldx32, "X", "AIFPrincipalMarkets", new BigInteger(
							"0"), null, "", null, null, "17.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx37);
			ReportField reportFieldx38 = new ReportField(reportCatalog,
					reportFieldx37, "X", "AIFPrincipalMarket", new BigInteger(
							"0"), null, "", null, null, "17.1", "3,3",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx38);

			// (114) <Ranking>
			ReportField reportField114 = new ReportField(reportCatalog,
					reportFieldx38, "N", "Ranking", new BigInteger("114"),
					"[0-9]{1}", "", "AIF Principal Markets",
					"ThreeRankingType", "17.114", "3,3", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField114);

			ReportField reportFieldx39 = new ReportField(reportCatalog,
					reportFieldx38, "X", "MarketIdentification",
					new BigInteger("0"), null, "", null, null, "17.1", "3,3",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx39);

			// (115) <MarketCodeType>
			ReportField reportField115 = new ReportField(reportCatalog,
					reportFieldx39, "A", "MarketCodeType",
					new BigInteger("115"), ".{3}", "", "AIF Principal Markets",
					"MarketCodeTypeWithoutNOTType", "17.115", "3,3",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField115);

			// (116) <MarketCode>
			ReportField reportField116 = new ReportField(reportCatalog,
					reportFieldx39, "A", "MarketCode", new BigInteger("116"),
					".{4}", "", "AIF Principal Markets", "MICCodeType",
					"17.116", "0,3", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField116);

			// (117) <AggregatedValueAmount>
			ReportField reportField117 = new ReportField(reportCatalog,
					reportFieldx38, "N", "AggregatedValueAmount",
					new BigInteger("117"), "[0-9]{0,15}?", "",
					"AIF Principal Markets", "NUMBER", "17.117", "0,3",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField117);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx40 = new ReportField(reportCatalog,
					reportFieldx32, "X", "InvestorConcentration",
					new BigInteger("0"), null, "", null, null, "18.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx40);

			// (118) <MainBeneficialOwnersRate>
			ReportField reportField118 = new ReportField(reportCatalog,
					reportFieldx40, "N", "MainBeneficialOwnersRate",
					new BigInteger("118"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Investor Concentration", "PERCENT", "18.118", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField118);

			// (119) <ProfessionalInvestorConcentrationRate>
			ReportField reportField119 = new ReportField(reportCatalog,
					reportFieldx40, "N",
					"ProfessionalInvestorConcentrationRate", new BigInteger(
							"119"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Investor Concentration", "PERCENT", "18.119", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField119);

			// (120) <RetailInvestorConcentrationRate>
			ReportField reportField120 = new ReportField(reportCatalog,
					reportFieldx40, "N", "RetailInvestorConcentrationRate",
					new BigInteger("120"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Investor Concentration", "PERCENT", "18.120", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField120);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx41 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFIndividualInfo",
					new BigInteger("0"), null, "", null, null, "19.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx41);

			ReportField reportFieldx42 = new ReportField(reportCatalog,
					reportFieldx41, "X", "IndividualExposure", new BigInteger(
							"0"), null, "", null, null, "19.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx42);
			ReportField reportFieldx43 = new ReportField(reportCatalog,
					reportFieldx42, "X", "AssetTypeExposures", new BigInteger(
							"0"), null, "", null, null, "19.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx43);
			ReportField reportFieldx44 = new ReportField(reportCatalog,
					reportFieldx43, "X", "AssetTypeExposure", new BigInteger(
							"0"), null, "", null, null, "19.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx44);

			// (121) <SubAssetType>
			ReportField reportField121 = new ReportField(reportCatalog,
					reportFieldx44, "A", "SubAssetType", new BigInteger("121"),
					".{1,12}", "", "Individual Exposure", "SubAssetTypeType",
					"19.121", "1,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField121);

			// (122) <GrossValue>
			ReportField reportField122 = new ReportField(reportCatalog,
					reportFieldx44, "N", "GrossValue", new BigInteger("122"),
					"[0-9]{0,15}?", "", "Individual Exposure", "NUMBER",
					"19.122", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField122);

			// (123) <LongValue>
			ReportField reportField123 = new ReportField(reportCatalog,
					reportFieldx44, "N", "LongValue", new BigInteger("123"),
					"[0-9]{0,15}?", "", "Individual Exposure", "NUMBER",
					"19.123", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField123);

			// (124) <ShortValue>
			ReportField reportField124 = new ReportField(reportCatalog,
					reportFieldx44, "N", "ShortValue", new BigInteger("124"),
					"[0-9]{0,15}?", "", "Individual Exposure", "NUMBER",
					"19.124", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField124);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx45 = new ReportField(reportCatalog,
					reportFieldx42, "X", "AssetTypeTurnovers", new BigInteger(
							"0"), null, "", null, null, "20.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx45);
			ReportField reportFieldx46 = new ReportField(reportCatalog,
					reportFieldx45, "X", "AssetTypeTurnover", new BigInteger(
							"0"), null, "", null, null, "20.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx46);

			// (125) <TurnoverSubAssetType>
			ReportField reportField125 = new ReportField(reportCatalog,
					reportFieldx46, "A", "TurnoverSubAssetType",
					new BigInteger("125"), ".{1,12}", "",
					"Asset Type Turnovers", "TurnoverSubAssetTypeType",
					"20.125", "1,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField125);

			// (126) <MarketValue>
			ReportField reportField126 = new ReportField(reportCatalog,
					reportFieldx46, "N", "MarketValue", new BigInteger("126"),
					"[0-9]{0,15}?", "", "Asset Type Turnovers", "NUMBER",
					"20.126", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField126);

			// (127) <NotionalValue>
			ReportField reportField127 = new ReportField(reportCatalog,
					reportFieldx46, "N", "NotionalValue",
					new BigInteger("127"), "[0-9]{0,15}?", "",
					"Asset Type Turnovers", "NUMBER", "20.127", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField127);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx47 = new ReportField(reportCatalog,
					reportFieldx42, "X", "CurrencyExposures", new BigInteger(
							"0"), null, "", null, null, "21.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx47);
			ReportField reportFieldx48 = new ReportField(reportCatalog,
					reportFieldx47, "X", "CurrencyExposure",
					new BigInteger("0"), null, "", null, null, "21.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx48);

			// (128) <ExposureCurrency>
			ReportField reportField128 = new ReportField(reportCatalog,
					reportFieldx48, "A", "ExposureCurrency", new BigInteger(
							"128"), ".{3}", "", "Currency Exposures",
					"CurrencyCodeType", "21.128", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField128);

			// (129) <LongPositionValue>
			ReportField reportField129 = new ReportField(reportCatalog,
					reportFieldx48, "N", "LongPositionValue", new BigInteger(
							"129"), "[0-9]{0,15}?", "", "Currency Exposures",
					"NUMBER", "21.129", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField129);

			// (130) <ShortPositionValue>
			ReportField reportField130 = new ReportField(reportCatalog,
					reportFieldx48, "N", "ShortPositionValue", new BigInteger(
							"130"), "[0-9]{0,15}?", "", "Currency Exposures",
					"NUMBER", "21.130", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField130);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx49 = new ReportField(reportCatalog,
					reportFieldx42, "X", "CompaniesDominantInfluence",
					new BigInteger("0"), null, "", null, null, "22.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx49);
			ReportField reportFieldx50 = new ReportField(reportCatalog,
					reportFieldx49, "X", "CompanyDominantInfluence",
					new BigInteger("0"), null, "", null, null, "22.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx50);
			ReportField reportFieldx51 = new ReportField(reportCatalog,
					reportFieldx50, "X", "CompanyIdentification",
					new BigInteger("0"), null, "", null, null, "22.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx51);

			// (131) <EntityName>
			ReportField reportField131 = new ReportField(reportCatalog,
					reportFieldx51, "Z", "EntityName", new BigInteger("131"),
					".{0,300}", "", "Companies Dominant Influence", "NAME",
					"22.131", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField131);

			// (132) <EntityIdentificationLEI>
			ReportField reportField132 = new ReportField(reportCatalog,
					reportFieldx51, "A", "EntityIdentificationLEI",
					new BigInteger("132"), ".{20}", "",
					"Companies Dominant Influence", "LEICodeType", "22.132",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField132);

			// (133) <EntityIdentificationBIC>
			ReportField reportField133 = new ReportField(reportCatalog,
					reportFieldx51, "A", "EntityIdentificationBIC",
					new BigInteger("133"), ".{11}", "",
					"Companies Dominant Influence", "BICCodeType", "22.133",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField133);

			// (134) <TransactionType>
			ReportField reportField134 = new ReportField(reportCatalog,
					reportFieldx50, "A", "TransactionType", new BigInteger(
							"134"), ".{4}", "", "Companies Dominant Influence",
					"TransactionTypeType", "22.134", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField134);

			// (135) <OtherTransactionTypeDescription>
			ReportField reportField135 = new ReportField(reportCatalog,
					reportFieldx50, "Z", "OtherTransactionTypeDescription",
					new BigInteger("135"), ".{0,300}", "",
					"Companies Dominant Influence", "NAME", "22.135", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField135);

			// (136) <VotingRightsRate>
			ReportField reportField136 = new ReportField(reportCatalog,
					reportFieldx50, "N", "VotingRightsRate", new BigInteger(
							"136"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Companies Dominant Influence", "PERCENT", "22.136", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField136);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx52 = new ReportField(reportCatalog,
					reportFieldx41, "X", "RiskProfile", new BigInteger("0"),
					null, "", null, null, "23.1", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx52);

			ReportField reportFieldx53 = new ReportField(reportCatalog,
					reportFieldx52, "X", "MarketRiskProfile", new BigInteger(
							"0"), null, "", null, null, "23.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx53);

			// (137) <AnnualInvestmentReturnRate>
			ReportField reportField137 = new ReportField(reportCatalog,
					reportFieldx53, "N", "AnnualInvestmentReturnRate",
					new BigInteger("137"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Risk Profile", "DECIMAL", "23.137", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField137);

			ReportField reportFieldx54 = new ReportField(reportCatalog,
					reportFieldx53, "X", "MarketRiskMeasures", new BigInteger(
							"0"), null, "", null, null, "23.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx54);
			ReportField reportFieldx55 = new ReportField(reportCatalog,
					reportFieldx54, "X", "MarketRiskMeasure", new BigInteger(
							"0"), null, "", null, null, "23.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx55);

			// (138) <RiskMeasureType>
			ReportField reportField138 = new ReportField(reportCatalog,
					reportFieldx55, "Z", "RiskMeasureType", new BigInteger(
							"138"), ".{15}", "", "Risk Profile",
					"RiskMeasureTypeType", "23.138", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField138);

			// (139) <RiskMeasureValue>
			ReportField reportField139 = new ReportField(reportCatalog,
					reportFieldx55, "N", "RiskMeasureValue", new BigInteger(
							"139"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Risk Profile", "DECIMAL", "23.139", "0,n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField139);

			ReportField reportFieldx56 = new ReportField(reportCatalog,
					reportFieldx55, "X", "BucketRiskMeasureValues",
					new BigInteger("0"), null, "", null, null, "23.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx56);

			// (140) <LessFiveYearsRiskMeasureValue>
			ReportField reportField140 = new ReportField(reportCatalog,
					reportFieldx56, "N", "LessFiveYearsRiskMeasureValue",
					new BigInteger("140"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Risk Profile", "DECIMAL", "23.140", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField140);

			// (141) <FifthteenYearsRiskMeasureValue>
			ReportField reportField141 = new ReportField(reportCatalog,
					reportFieldx56, "N", "FifthteenYearsRiskMeasureValue",
					new BigInteger("141"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Risk Profile", "DECIMAL", "23.141", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField141);

			// (142) <MoreFifthteenYearsRiskMeasureValue>
			ReportField reportField142 = new ReportField(reportCatalog,
					reportFieldx56, "N", "MoreFifthteenYearsRiskMeasureValue",
					new BigInteger("142"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Risk Profile", "DECIMAL", "23.142", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField142);

			ReportField reportFieldx57 = new ReportField(reportCatalog,
					reportFieldx55, "X", "VegaRiskMeasureValues",
					new BigInteger("0"), null, "", null, null, "23.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx57);

			// (143) <CurrentMarketRiskMeasureValue>
			ReportField reportField143 = new ReportField(reportCatalog,
					reportFieldx57, "N", "CurrentMarketRiskMeasureValue",
					new BigInteger("143"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Risk Profile", "DECIMAL", "23.143", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField143);

			// (144) <LowerMarketRiskMeasureValue>
			ReportField reportField144 = new ReportField(reportCatalog,
					reportFieldx57, "N", "LowerMarketRiskMeasureValue",
					new BigInteger("144"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Risk Profile", "DECIMAL", "23.144", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField144);

			// (145) <HigherMarketRiskMeasureValue>
			ReportField reportField145 = new ReportField(reportCatalog,
					reportFieldx57, "N", "HigherMarketRiskMeasureValue",
					new BigInteger("145"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Risk Profile", "DECIMAL", "23.145", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField145);

			ReportField reportFieldx58 = new ReportField(reportCatalog,
					reportFieldx55, "X", "VARRiskMeasureValues",
					new BigInteger("0"), null, "", null, null, "23.1",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx58);

			// (302) <VARValue>
			ReportField reportField302 = new ReportField(reportCatalog,
					reportFieldx58, "N", "VARValue", new BigInteger("302"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "Risk Profile",
					"PERCENT", "23.302", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField302);

			// (146) <VARCalculationMethodCodeType>
			ReportField reportField146 = new ReportField(reportCatalog,
					reportFieldx58, "A", "VARCalculationMethodCodeType",
					new BigInteger("146"), ".{5}", "", "Risk Profile",
					"VARCalculationMethodCodeTypeType", "23.146", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField146);

			// (147) <RiskMeasureDescription>
			ReportField reportField147 = new ReportField(reportCatalog,
					reportFieldx55, "Z", "RiskMeasureDescription",
					new BigInteger("147"), ".{0,300}", "", "Risk Profile",
					"DESCRIPTION", "23.147", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField147);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx59 = new ReportField(reportCatalog,
					reportFieldx52, "X", "CounterpartyRiskProfile",
					new BigInteger("0"), null, "", null, null, "24.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx59);

			ReportField reportFieldx60 = new ReportField(reportCatalog,
					reportFieldx59, "X", "TradingClearingMechanism",
					new BigInteger("0"), null, "", null, null, "24.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx60);

			ReportField reportFieldx61 = new ReportField(reportCatalog,
					reportFieldx60, "X", "TradedSecurities",
					new BigInteger("0"), null, "", null, null, "24.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx61);

			// (148) <RegulatedMarketRate>
			ReportField reportField148 = new ReportField(reportCatalog,
					reportFieldx61, "N", "RegulatedMarketRate", new BigInteger(
							"148"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "24.148", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField148);

			// (149) <OTCRate>
			ReportField reportField149 = new ReportField(reportCatalog,
					reportFieldx61, "N", "OTCRate", new BigInteger("149"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "24.149", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField149);

			ReportField reportFieldx62 = new ReportField(reportCatalog,
					reportFieldx60, "X", "TradedDerivatives", new BigInteger(
							"0"), null, "", null, null, "24.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx62);

			// (150) <RegulatedMarketRate>
			ReportField reportField150 = new ReportField(reportCatalog,
					reportFieldx62, "N", "RegulatedMarketRate", new BigInteger(
							"150"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "24.150", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField150);

			// (151) <OTCRate>
			ReportField reportField151 = new ReportField(reportCatalog,
					reportFieldx62, "N", "OTCRate", new BigInteger("151"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "24.151", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField151);

			ReportField reportFieldx63 = new ReportField(reportCatalog,
					reportFieldx59, "X", "ClearedDerivativesRate",
					new BigInteger("0"), null, "", null, null, "24.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx63);

			// (152) <CCPRate>
			ReportField reportField152 = new ReportField(reportCatalog,
					reportFieldx63, "N", "CCPRate", new BigInteger("152"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "24.152", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField152);

			// (153) <BilateralClearingRate>
			ReportField reportField153 = new ReportField(reportCatalog,
					reportFieldx63, "N", "BilateralClearingRate",
					new BigInteger("153"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "24.153", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField153);

			ReportField reportFieldx64 = new ReportField(reportCatalog,
					reportFieldx59, "X", "ClearedReposRate",
					new BigInteger("0"), null, "", null, null, "24.154", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx64);

			// (154) <CCPRate>
			ReportField reportField154 = new ReportField(reportCatalog,
					reportFieldx64, "N", "CCPRate", new BigInteger("154"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "24.154", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField154);

			// (155) <BilateralClearingRate>
			ReportField reportField155 = new ReportField(reportCatalog,
					reportFieldx64, "N", "BilateralClearingRate",
					new BigInteger("155"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "24.155", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField155);

			// (156) <TriPartyRepoClearingRate>
			ReportField reportField156 = new ReportField(reportCatalog,
					reportFieldx64, "N", "TriPartyRepoClearingRate",
					new BigInteger("156"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "24.156", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField156);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx65 = new ReportField(reportCatalog,
					reportFieldx59, "X", "AllCounterpartyCollateral",
					new BigInteger("0"), null, "", null, null, "25.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx65);

			// (157) <AllCounterpartyCollateralCash>
			ReportField reportField157 = new ReportField(reportCatalog,
					reportFieldx65, "N", "AllCounterpartyCollateralCash",
					new BigInteger("157"), "[0-9]{0,15}?", "",
					"All Counterparty Collateral", "NUMBER", "25.157", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField157);

			// (158) <AllCounterpartyCollateralSecurities>
			ReportField reportField158 = new ReportField(reportCatalog,
					reportFieldx65, "N", "AllCounterpartyCollateralSecurities",
					new BigInteger("158"), "[0-9]{0,15}?", "",
					"All Counterparty Collateral", "NUMBER", "25.158", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField158);

			// (159) <AllCounterpartyOtherCollateralPosted>
			ReportField reportField159 = new ReportField(reportCatalog,
					reportFieldx65, "N",
					"AllCounterpartyOtherCollateralPosted", new BigInteger(
							"159"), "[0-9]{0,15}?", "",
					"All Counterparty Collateral", "NUMBER", "25.159", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField159);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx66 = new ReportField(reportCatalog,
					reportFieldx59, "X", "FundToCounterpartyExposures",
					new BigInteger("0"), null, "", null, null, "26.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx66);
			ReportField reportFieldx67 = new ReportField(reportCatalog,
					reportFieldx66, "X", "FundToCounterpartyExposure",
					new BigInteger("0"), null, "", null, null, "26.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx67);

			// (160) <Ranking>
			ReportField reportField160 = new ReportField(reportCatalog,
					reportFieldx67, "N", "Ranking", new BigInteger("160"),
					"[0-9]{1}", "", "Fund To Counterparty Exposures",
					"FiveRankingType", "26.160", "5,5", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField160);

			// (161) <CounterpartyExposureFlag>
			ReportField reportField161 = new ReportField(reportCatalog,
					reportFieldx67, "B", "CounterpartyExposureFlag",
					new BigInteger("161"), "true|false", "",
					"Fund To Counterparty Exposures", "BOOLEAN", "26.161",
					"5,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField161);

			ReportField reportFieldx68 = new ReportField(reportCatalog,
					reportFieldx67, "X", "CounterpartyIdentification",
					new BigInteger("0"), null, "", null, null, "26.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx68);

			// (162) <EntityName>
			ReportField reportField162 = new ReportField(reportCatalog,
					reportFieldx68, "Z", "EntityName", new BigInteger("162"),
					".{0,300}", "", "Fund To Counterparty Exposures", "NAME",
					"26.162", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField162);

			// (163) <EntityIdentificationLEI>
			ReportField reportField163 = new ReportField(reportCatalog,
					reportFieldx68, "A", "EntityIdentificationLEI",
					new BigInteger("163"), ".{20}", "",
					"Fund To Counterparty Exposures", "LEICodeType", "26.163",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField163);

			// (164) <EntityIdentificationBIC>
			ReportField reportField164 = new ReportField(reportCatalog,
					reportFieldx68, "A", "EntityIdentificationBIC",
					new BigInteger("164"), ".{11}", "",
					"Fund To Counterparty Exposures", "BICCodeType", "26.164",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField164);

			// (165) <CounterpartyTotalExposureRate>
			ReportField reportField165 = new ReportField(reportCatalog,
					reportFieldx67, "N", "CounterpartyTotalExposureRate",
					new BigInteger("165"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Fund To Counterparty Exposures", "PERCENT", "26.165",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField165);

			ReportField reportFieldx69 = new ReportField(reportCatalog,
					reportFieldx59, "X", "CounterpartyToFundExposures",
					new BigInteger("0"), null, "", null, null, "26.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx69);
			ReportField reportFieldx70 = new ReportField(reportCatalog,
					reportFieldx69, "X", "CounterpartyToFundExposure",
					new BigInteger("0"), null, "", null, null, "26.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx70);

			// (166) <Ranking>
			ReportField reportField166 = new ReportField(reportCatalog,
					reportFieldx70, "N", "Ranking", new BigInteger("166"),
					"[0-9]{1}", "", "Fund To Counterparty Exposures",
					"FiveRankingType", "26.166", "5,5", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField166);

			// (167) <CounterpartyExposureFlag>
			ReportField reportField167 = new ReportField(reportCatalog,
					reportFieldx70, "B", "CounterpartyExposureFlag",
					new BigInteger("167"), "true|false", "",
					"Fund To Counterparty Exposures", "BOOLEAN", "26.167",
					"5,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField167);

			ReportField reportFieldx71 = new ReportField(reportCatalog,
					reportFieldx70, "X", "CounterpartyIdentification",
					new BigInteger("0"), null, "", null, null, "26.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx71);

			// (168) <EntityName>
			ReportField reportField168 = new ReportField(reportCatalog,
					reportFieldx71, "Z", "EntityName", new BigInteger("168"),
					".{0,300}", "", "Fund To Counterparty Exposures", "NAME",
					"26.168", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField168);

			// (169) <EntityIdentificationLEI>
			ReportField reportField169 = new ReportField(reportCatalog,
					reportFieldx71, "A", "EntityIdentificationLEI",
					new BigInteger("169"), ".{20}", "",
					"Fund To Counterparty Exposures", "LEICodeType", "26.169",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField169);

			// (170) <EntityIdentificationBIC>
			ReportField reportField170 = new ReportField(reportCatalog,
					reportFieldx71, "A", "EntityIdentificationBIC",
					new BigInteger("170"), ".{11}", "",
					"Fund To Counterparty Exposures", "BICCodeType", "26.170",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField170);

			// (171) <CounterpartyTotalExposureRate>
			ReportField reportField171 = new ReportField(reportCatalog,
					reportFieldx70, "N", "CounterpartyTotalExposureRate",
					new BigInteger("171"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Fund To Counterparty Exposures", "PERCENT", "26.171",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField171);

			// //////////////////////////////////////////////////////////////

			// (172) <CounterpartyTotalExposureRate>
			ReportField reportField172 = new ReportField(reportCatalog,
					reportFieldx59, "B", "CounterpartyTotalExposureRate",
					new BigInteger("172"), "true|false", "", "CCP Exposures",
					"BOOLEAN", "27.172", "1,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField172);

			ReportField reportFieldx72 = new ReportField(reportCatalog,
					reportFieldx59, "X", "CCPExposures", new BigInteger("0"),
					null, "", null, null, "27.1", "1,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx72);
			ReportField reportFieldx73 = new ReportField(reportCatalog,
					reportFieldx72, "X", "CCPExposure", new BigInteger("0"),
					null, "", null, null, "27.1", "1,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx73);

			// (173) <Ranking>
			ReportField reportField173 = new ReportField(reportCatalog,
					reportFieldx73, "N", "Ranking", new BigInteger("173"),
					"[0-9]{1}", "", "CCP Exposures", "ThreeRankingType",
					"27.173", "0,3", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField173);

			ReportField reportFieldx74 = new ReportField(reportCatalog,
					reportFieldx73, "X", "CCPIdentification", new BigInteger(
							"0"), null, "", null, null, "27.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx74);

			// (174) <EntityName>
			ReportField reportField174 = new ReportField(reportCatalog,
					reportFieldx74, "Z", "EntityName", new BigInteger("174"),
					".{0,300}", "", "CCP Exposures", "NAME", "27.174", "0,3",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField174);

			// (175) <EntityIdentificationLEI>
			ReportField reportField175 = new ReportField(reportCatalog,
					reportFieldx74, "A", "EntityIdentificationLEI",
					new BigInteger("175"), ".{20}", "", "CCP Exposures",
					"LEICodeType", "27.175", "0,3", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField175);

			// (176) <EntityIdentificationBIC>
			ReportField reportField176 = new ReportField(reportCatalog,
					reportFieldx74, "A", "EntityIdentificationBIC",
					new BigInteger("176"), ".{11}", "", "CCP Exposures",
					"BICCodeType", "27.176", "0,3", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField176);

			// (177) <CCPExposureValue>
			ReportField reportField177 = new ReportField(reportCatalog,
					reportFieldx73, "N", "CCPExposureValue", new BigInteger(
							"177"), "[0-9]{0,15}?", "", "CCP Exposures",
					"NUMBER", "27.177", "0,3", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField177);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx75 = new ReportField(reportCatalog,
					reportFieldx52, "X", "LiquidityRiskProfile",
					new BigInteger("0"), null, "", null, null, "28.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx75);

			ReportField reportFieldx76 = new ReportField(reportCatalog,
					reportFieldx75, "X", "PortfolioLiquidityProfile",
					new BigInteger("0"), null, "", null, null, "28.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx76);

			// (178) <PortfolioLiquidityInDays0to1Rate>
			ReportField reportField178 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays0to1Rate",
					new BigInteger("178"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Portfolio Liquidity Profile", "DECIMAL", "28.178", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField178);

			// (179) <PortfolioLiquidityInDays2to7Rate>
			ReportField reportField179 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays2to7Rate",
					new BigInteger("179"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Portfolio Liquidity Profile", "DECIMAL", "28.179", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField179);

			// (180) <PortfolioLiquidityInDays8to30Rate>
			ReportField reportField180 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays8to30Rate",
					new BigInteger("180"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Portfolio Liquidity Profile", "DECIMAL", "28.180", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField180);

			// (181) <PortfolioLiquidityInDays31to90Rate>
			ReportField reportField181 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays31to90Rate",
					new BigInteger("181"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Portfolio Liquidity Profile", "DECIMAL", "28.181", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField181);

			// (182) <PortfolioLiquidityInDays91to180Rate>
			ReportField reportField182 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays91to180Rate",
					new BigInteger("182"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Portfolio Liquidity Profile", "DECIMAL", "28.182", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField182);

			// (183) <PortfolioLiquidityInDays181to365Rate>
			ReportField reportField183 = new ReportField(reportCatalog,
					reportFieldx76, "N",
					"PortfolioLiquidityInDays181to365Rate", new BigInteger(
							"183"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Portfolio Liquidity Profile", "DECIMAL", "28.183", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField183);

			// (184) <PortfolioLiquidityInDays365MoreRate>
			ReportField reportField184 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays365MoreRate",
					new BigInteger("184"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Portfolio Liquidity Profile", "DECIMAL", "28.184", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField184);

			// (185) <UnencumberedCash>
			ReportField reportField185 = new ReportField(reportCatalog,
					reportFieldx76, "N", "UnencumberedCash", new BigInteger(
							"185"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Portfolio Liquidity Profile", "DECIMAL", "28.185", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField185);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx77 = new ReportField(reportCatalog,
					reportFieldx75, "X", "InvestorLiquidityProfile",
					new BigInteger("0"), null, "", null, null, "29.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx77);

			// (186) <InvestorLiquidityInDays0to1Rate>
			ReportField reportField186 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays0to1Rate",
					new BigInteger("186"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Investor Liquidity Profile", "DECIMAL", "29.186", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField186);

			// (187) <InvestorLiquidityInDays2to7Rate>
			ReportField reportField187 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays2to7Rate",
					new BigInteger("187"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Investor Liquidity Profile", "DECIMAL", "29.187", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField187);

			// (188) <InvestorLiquidityInDays8to30Rate>
			ReportField reportField188 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays8to30Rate",
					new BigInteger("188"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Investor Liquidity Profile", "DECIMAL", "29.188", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField188);

			// (189) <InvestorLiquidityInDays31to90Rate>
			ReportField reportField189 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays31to90Rate",
					new BigInteger("189"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Investor Liquidity Profile", "DECIMAL", "29.189", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField189);

			// (190) <InvestorLiquidityInDays91to180Rate>
			ReportField reportField190 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays91to180Rate",
					new BigInteger("190"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Investor Liquidity Profile", "DECIMAL", "29.190", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField190);

			// (191) <InvestorLiquidityInDays181to365Rate>
			ReportField reportField191 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays181to365Rate",
					new BigInteger("191"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Investor Liquidity Profile", "DECIMAL", "29.191", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField191);

			// (192) <InvestorLiquidityInDays365MoreRate>
			ReportField reportField192 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays365MoreRate",
					new BigInteger("192"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Investor Liquidity Profile", "DECIMAL", "29.192", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField192);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx78 = new ReportField(reportCatalog,
					reportFieldx75, "X", "InvestorRedemption", new BigInteger(
							"0"), null, "", null, null, "30.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx78);

			// (193) <ProvideWithdrawalRightsFlag>
			ReportField reportField193 = new ReportField(reportCatalog,
					reportFieldx78, "B", "ProvideWithdrawalRightsFlag",
					new BigInteger("193"), "true|false", "",
					"Investor Redemption", "BOOLEAN", "30.193", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField193);

			// (194) <InvestorRedemptionFrequency>
			ReportField reportField194 = new ReportField(reportCatalog,
					reportFieldx78, "A", "InvestorRedemptionFrequency",
					new BigInteger("194"), ".{1}", "", "Investor Redemption",
					"InvestorRedemptionFrequencyType", "30.194", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField194);

			// (195) <InvestorRedemptionNoticePeriod>
			ReportField reportField195 = new ReportField(reportCatalog,
					reportFieldx78, "N", "InvestorRedemptionNoticePeriod",
					new BigInteger("195"), "[0-9]{0,4}?", "",
					"Investor Redemption", "NUMBER", "30.195", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField195);

			// (196) <InvestorRedemptionLockUpPeriod>
			ReportField reportField196 = new ReportField(reportCatalog,
					reportFieldx78, "N", "InvestorRedemptionLockUpPeriod",
					new BigInteger("196"), "[0-9]{0,4}?", "",
					"Investor Redemption", "NUMBER", "30.196", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField196);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx79 = new ReportField(reportCatalog,
					reportFieldx75, "X", "InvestorArrangement", new BigInteger(
							"0"), null, "", null, null, "31.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx79);

			ReportField reportFieldx80 = new ReportField(reportCatalog,
					reportFieldx79, "X", "InvestorIlliquidAssetArrangement",
					new BigInteger("0"), null, "", null, null, "31.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx80);

			// (197) <SidePocketRate>
			ReportField reportField197 = new ReportField(reportCatalog,
					reportFieldx80, "N", "SidePocketRate",
					new BigInteger("197"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Investor Arrangement", "PERCENT", "31.197", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField197);

			// (198) <GatesRate>
			ReportField reportField198 = new ReportField(reportCatalog,
					reportFieldx80, "N", "GatesRate", new BigInteger("198"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "Investor Arrangement",
					"PERCENT", "31.198", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField198);

			// (199) <DealingSuspensionRate>
			ReportField reportField199 = new ReportField(reportCatalog,
					reportFieldx80, "N", "DealingSuspensionRate",
					new BigInteger("199"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Investor Arrangement", "PERCENT", "31.199", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField199);

			ReportField reportFieldx81 = new ReportField(reportCatalog,
					reportFieldx80, "X", "OtherArrangement",
					new BigInteger("0"), null, "", null, null, "31.1",
					"0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx81);

			// (200) <OtherArrangementType>
			ReportField reportField200 = new ReportField(reportCatalog,
					reportFieldx81, "Z", "OtherArrangementType",
					new BigInteger("200"), ".{0,300}", "",
					"Investor Arrangement", "DESCRIPTION", "31.200", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField200);

			// (201) <OtherArrangementRate>
			ReportField reportField201 = new ReportField(reportCatalog,
					reportFieldx81, "N", "OtherArrangementRate",
					new BigInteger("201"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Investor Arrangement", "PERCENT", "31.201", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField201);

			// (202) <TotalArrangementRate>
			ReportField reportField202 = new ReportField(reportCatalog,
					reportFieldx80, "N", "TotalArrangementRate",
					new BigInteger("202"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Investor Arrangement", "PERCENT", "31.202", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField202);

			ReportField reportFieldx82 = new ReportField(reportCatalog,
					reportFieldx79, "X", "InvestorPreferentialTreatment",
					new BigInteger("0"), null, "", null, null, "31.2", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx82);

			// (203) <InvestorPreferentialTreatmentFlag>
			ReportField reportField203 = new ReportField(reportCatalog,
					reportFieldx82, "B", "InvestorPreferentialTreatmentFlag",
					new BigInteger("203"), "true|false", "",
					"Investor Arrangement", "BOOLEAN", "31.203", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField203);

			// (204) <DisclosureTermsPreferentialTreatmentFlag>
			ReportField reportField204 = new ReportField(reportCatalog,
					reportFieldx82, "B",
					"DisclosureTermsPreferentialTreatmentFlag", new BigInteger(
							"204"), "true|false", "", "Investor Arrangement",
					"BOOLEAN", "31.204", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField204);

			// (205) <LiquidityTermsPreferentialTreatmentFlag>
			ReportField reportField205 = new ReportField(reportCatalog,
					reportFieldx82, "B",
					"LiquidityTermsPreferentialTreatmentFlag", new BigInteger(
							"205"), "true|false", "", "Investor Arrangement",
					"BOOLEAN", "31.205", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField205);

			// (206) <FeeTermsPreferentialTreatmentFlag>
			ReportField reportField206 = new ReportField(reportCatalog,
					reportFieldx82, "B", "FeeTermsPreferentialTreatmentFlag",
					new BigInteger("206"), "true|false", "",
					"Investor Arrangement", "BOOLEAN", "31.206", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField206);

			// (207) <OtherTermsPreferentialTreatmentFlag>
			ReportField reportField207 = new ReportField(reportCatalog,
					reportFieldx82, "B", "OtherTermsPreferentialTreatmentFlag",
					new BigInteger("207"), "true|false", "",
					"Investor Arrangement", "BOOLEAN", "31.207", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField207);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx83 = new ReportField(reportCatalog,
					reportFieldx75, "X", "InvestorGroups", new BigInteger("0"),
					null, "", null, null, "32.1", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx83);
			ReportField reportFieldx84 = new ReportField(reportCatalog,
					reportFieldx83, "X", "InvestorGroup", new BigInteger("0"),
					null, "", null, null, "32.1", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx84);

			// (208) <InvestorGroupType>
			ReportField reportField208 = new ReportField(reportCatalog,
					reportFieldx84, "A", "InvestorGroupType", new BigInteger(
							"208"), ".{4}", "", "Investor Groups",
					"InvestorGroupTypeType", "32.208", "1,n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField208);

			// (209) <InvestorGroupRate>
			ReportField reportField209 = new ReportField(reportCatalog,
					reportFieldx84, "N", "InvestorGroupRate", new BigInteger(
							"209"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Investor Groups", "PERCENT", "32.209", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField209);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx85 = new ReportField(reportCatalog,
					reportFieldx75, "X", "FinancingLiquidityProfile",
					new BigInteger("0"), null, "", null, null, "33.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx85);

			// (210) <TotalFinancingAmount>
			ReportField reportField210 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingAmount",
					new BigInteger("210"), "[0-9]{0,15}?", "",
					"Financing Liquidity Profile", "NUMBER", "33.210", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField210);

			// (211) <TotalFinancingInDays0to1Rate>
			ReportField reportField211 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays0to1Rate",
					new BigInteger("211"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Financing Liquidity Profile", "DECIMAL", "33.211", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField211);

			// (212) <TotalFinancingInDays2to7Rate>
			ReportField reportField212 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays2to7Rate",
					new BigInteger("212"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Financing Liquidity Profile", "DECIMAL", "33.212", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField212);

			// (213) <TotalFinancingInDays8to30Rate>
			ReportField reportField213 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays8to30Rate",
					new BigInteger("213"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Financing Liquidity Profile", "DECIMAL", "33.213", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField213);

			// (214) <TotalFinancingInDays31to90Rate>
			ReportField reportField214 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays31to90Rate",
					new BigInteger("214"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Financing Liquidity Profile", "DECIMAL", "33.214", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField214);

			// (215) <TotalFinancingInDays91to180Rate>
			ReportField reportField215 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays91to180Rate",
					new BigInteger("215"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Financing Liquidity Profile", "DECIMAL", "33.215", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField215);

			// (216) <TotalFinancingInDays181to365Rate>
			ReportField reportField216 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays181to365Rate",
					new BigInteger("216"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Financing Liquidity Profile", "DECIMAL", "33.216", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField216);

			// (217) <TotalFinancingInDays365MoreRate>
			ReportField reportField217 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays365MoreRate",
					new BigInteger("217"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Financing Liquidity Profile", "DECIMAL", "33.217", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField217);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx86 = new ReportField(reportCatalog,
					reportFieldx52, "X", "OperationalRisk",
					new BigInteger("0"), null, "", null, null, "34.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx86);

			// (218) <TotalOpenPositions>
			ReportField reportField218 = new ReportField(reportCatalog,
					reportFieldx86, "N", "TotalOpenPositions", new BigInteger(
							"218"), "[0-9]{0,15}?", "", "Operational Risk",
					"NUMBER", "34.218", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField218);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx87 = new ReportField(reportCatalog,
					reportFieldx86, "X", "HistoricalRiskProfile",
					new BigInteger("0"), null, "", null, null, "35.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx87);

			ReportField reportFieldx88 = new ReportField(reportCatalog,
					reportFieldx87, "X", "GrossInvestmentReturnsRate",
					new BigInteger("0"), null, "", null, null, "35.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx88);

			// (219) <RateJanuary>
			ReportField reportField219 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateJanuary", new BigInteger("219"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.219", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField219);

			// (220) <RateFebruary>
			ReportField reportField220 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateFebruary", new BigInteger("220"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.220", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField220);

			// (221) <RateMarch>
			ReportField reportField221 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateMarch", new BigInteger("221"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.221", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField221);

			// (222) <RateApril>
			ReportField reportField222 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateApril", new BigInteger("222"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.222", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField222);

			// (223) <RateMay>
			ReportField reportField223 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateMay", new BigInteger("223"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.223", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField223);

			// (224) <RateJune>
			ReportField reportField224 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateJune", new BigInteger("224"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.224", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField224);

			// (225) <RateJuly>
			ReportField reportField225 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateJuly", new BigInteger("225"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.225", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField225);

			// (226) <RateAugust>
			ReportField reportField226 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateAugust", new BigInteger("226"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.226", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField226);

			// (227) <RateSeptember>
			ReportField reportField227 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateSeptember",
					new BigInteger("227"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Historical Risk Profile", "DECIMAL", "35.227", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField227);

			// (228) <RateOctober>
			ReportField reportField228 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateOctober", new BigInteger("228"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.228", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField228);

			// (229) <RateNovember>
			ReportField reportField229 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateNovember", new BigInteger("229"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.229", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField229);

			// (230) <RateDecember>
			ReportField reportField230 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateDecember", new BigInteger("230"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.230", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField230);

			ReportField reportFieldx89 = new ReportField(reportCatalog,
					reportFieldx87, "X", "NetInvestmentReturnsRate",
					new BigInteger("0"), null, "", null, null, "35.2", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx89);

			// (231) <RateJanuary>
			ReportField reportField231 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateJanuary", new BigInteger("231"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.231", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField231);

			// (232) <RateFebruary>
			ReportField reportField232 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateFebruary", new BigInteger("232"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.232", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField232);

			// (233) <RateMarch>
			ReportField reportField233 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateMarch", new BigInteger("233"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.233", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField233);

			// (234) <RateApril>
			ReportField reportField234 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateApril", new BigInteger("234"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.234", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField234);

			// (235) <RateMay>
			ReportField reportField235 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateMay", new BigInteger("235"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.235", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField235);

			// (236) <RateJune>
			ReportField reportField236 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateJune", new BigInteger("236"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.236", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField236);

			// (237) <RateJuly>
			ReportField reportField237 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateJuly", new BigInteger("237"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.237", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField237);

			// (238) <RateAugust>
			ReportField reportField238 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateAugust", new BigInteger("238"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.238", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField238);

			// (239) <RateSeptember>
			ReportField reportField239 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateSeptember",
					new BigInteger("239"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Historical Risk Profile", "DECIMAL", "35.239", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField239);

			// (240) <RateOctober>
			ReportField reportField240 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateOctober", new BigInteger("240"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.240", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField240);

			// (241) <RateNovember>
			ReportField reportField241 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateNovember", new BigInteger("241"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.241", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField241);

			// (242) <RateDecember>
			ReportField reportField242 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateDecember", new BigInteger("242"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.242", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField242);

			ReportField reportFieldx90 = new ReportField(reportCatalog,
					reportFieldx87, "X", "NAVChangeRate", new BigInteger("0"),
					null, "", null, null, "35.2", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx90);

			// (243) <RateJanuary>
			ReportField reportField243 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateJanuary", new BigInteger("243"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.243", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField243);

			// (244) <RateFebruary>
			ReportField reportField244 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateFebruary", new BigInteger("244"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.244", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField244);

			// (245) <RateMarch>
			ReportField reportField245 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateMarch", new BigInteger("245"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.245", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField245);

			// (246) <RateApril>
			ReportField reportField246 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateApril", new BigInteger("246"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.246", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField246);

			// (247) <RateMay>
			ReportField reportField247 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateMay", new BigInteger("247"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.247", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField247);

			// (248) <RateJune>
			ReportField reportField248 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateJune", new BigInteger("248"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.248", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField248);

			// (249) <RateJuly>
			ReportField reportField249 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateJuly", new BigInteger("249"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.249", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField249);

			// (250) <RateAugust>
			ReportField reportField250 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateAugust", new BigInteger("250"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.250", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField250);

			// (251) <RateSeptember>
			ReportField reportField251 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateSeptember",
					new BigInteger("251"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Historical Risk Profile", "DECIMAL", "35.251", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField251);

			// (252) <RateOctober>
			ReportField reportField252 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateOctober", new BigInteger("252"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.252", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField252);

			// (253) <RateNovember>
			ReportField reportField253 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateNovember", new BigInteger("253"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.253", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField253);

			// (254) <RateDecember>
			ReportField reportField254 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateDecember", new BigInteger("254"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "35.254", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField254);

			ReportField reportFieldx91 = new ReportField(reportCatalog,
					reportFieldx87, "X", "Subscription", new BigInteger("0"),
					null, "", null, null, "35.2", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx91);

			// (255) <QuantityJanuary>
			ReportField reportField255 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityJanuary", new BigInteger(
							"255"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.255", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField255);

			// (256) <QuantityFebruary>
			ReportField reportField256 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityFebruary", new BigInteger(
							"256"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.256", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField256);

			// (257) <QuantityMarch>
			ReportField reportField257 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityMarch",
					new BigInteger("257"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.257", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField257);

			// (258) <QuantityApril>
			ReportField reportField258 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityApril",
					new BigInteger("258"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.258", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField258);

			// (259) <QuantityMay>
			ReportField reportField259 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityMay", new BigInteger("259"),
					"[0-9]{0,15}?", "", "Historical Risk Profile", "NUMBER",
					"35.259", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField259);

			// (260) <QuantityJune>
			ReportField reportField260 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityJune", new BigInteger("260"),
					"[0-9]{0,15}?", "", "Historical Risk Profile", "NUMBER",
					"35.260", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField260);

			// (261) <QuantityJuly>
			ReportField reportField261 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityJuly", new BigInteger("261"),
					"[0-9]{0,15}?", "", "Historical Risk Profile", "NUMBER",
					"35.261", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField261);

			// (262) <QuantityAugust>
			ReportField reportField262 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityAugust",
					new BigInteger("262"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.262", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField262);

			// (263) <QuantitySeptember>
			ReportField reportField263 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantitySeptember", new BigInteger(
							"263"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.263", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField263);

			// (264) <QuantityOctober>
			ReportField reportField264 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityOctober", new BigInteger(
							"264"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.264", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField264);

			// (265) <QuantityNovember>
			ReportField reportField265 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityNovember", new BigInteger(
							"265"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.265", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField265);

			// (266) <QuantityDecember>
			ReportField reportField266 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityDecember", new BigInteger(
							"266"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.266", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField266);

			ReportField reportFieldx92 = new ReportField(reportCatalog,
					reportFieldx87, "X", "Redemption", new BigInteger("0"),
					null, "", null, null, "35.1", "0,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx92);

			// (267) <QuantityJanuary>
			ReportField reportField267 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityJanuary", new BigInteger(
							"267"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.267", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField267);

			// (268) <QuantityFebruary>
			ReportField reportField268 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityFebruary", new BigInteger(
							"268"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.268", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField268);

			// (269) <QuantityMarch>
			ReportField reportField269 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityMarch",
					new BigInteger("269"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.269", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField269);

			// (270) <QuantityApril>
			ReportField reportField270 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityApril",
					new BigInteger("270"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.270", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField270);

			// (271) <QuantityMay>
			ReportField reportField271 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityMay", new BigInteger("271"),
					"[0-9]{0,15}?", "", "Historical Risk Profile", "NUMBER",
					"35.271", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField271);

			// (272) <QuantityJune>
			ReportField reportField272 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityJune", new BigInteger("272"),
					"[0-9]{0,15}?", "", "Historical Risk Profile", "NUMBER",
					"35.272", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField272);

			// (273) <QuantityJuly>
			ReportField reportField273 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityJuly", new BigInteger("273"),
					"[0-9]{0,15}?", "", "Historical Risk Profile", "NUMBER",
					"35.273", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField273);

			// (274) <QuantityAugust>
			ReportField reportField274 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityAugust",
					new BigInteger("274"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.274", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField274);

			// (275) <QuantitySeptember>
			ReportField reportField275 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantitySeptember", new BigInteger(
							"275"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.275", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField275);

			// (276) <QuantityOctober>
			ReportField reportField276 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityOctober", new BigInteger(
							"276"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.276", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField276);

			// (277) <QuantityNovember>
			ReportField reportField277 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityNovember", new BigInteger(
							"277"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.277", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField277);

			// (278) <QuantityDecember>
			ReportField reportField278 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityDecember", new BigInteger(
							"278"), "[0-9]{0,15}?", "",
					"Historical Risk Profile", "NUMBER", "35.278", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField278);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx93 = new ReportField(reportCatalog,
					reportFieldx41, "X", "StressTests", new BigInteger("0"),
					null, "", null, null, "36.1", "1,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx93);

			// (279) <StressTestsResultArticle15>
			ReportField reportField279 = new ReportField(reportCatalog,
					reportFieldx93, "Z", "StressTestsResultArticle15",
					new BigInteger("279"), ".{0,32000}", "", "Stress Tests",
					"StressTestsResultType", "36.279", "1,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField279);

			// (280) <StressTestsResultArticle16>
			ReportField reportField280 = new ReportField(reportCatalog,
					reportFieldx93, "Z", "StressTestsResultArticle16",
					new BigInteger("280"), ".{0,32000}", "", "Stress Tests",
					"StressTestsResultType", "36.280", "1,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField280);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx94 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFLeverageInfo", new BigInteger("0"),
					null, "", null, null, "37.1", "1,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx94);

			ReportField reportFieldx95 = new ReportField(reportCatalog,
					reportFieldx94, "X", "AIFLeverageArticle24-2",
					new BigInteger("0"), null, "", null, null, "37.1",
					"1,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx95);

			// (281) <AllCounterpartyCollateralRehypothecationFlag>
			ReportField reportField281 = new ReportField(reportCatalog,
					reportFieldx95, "B",
					"AllCounterpartyCollateralRehypothecationFlag",
					new BigInteger("281"), "true|false", "",
					"AIF Leverage Info", "BOOLEAN", "37.281", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField281);

			// (282) <AllCounterpartyCollateralRehypothecatedRate>
			ReportField reportField282 = new ReportField(reportCatalog,
					reportFieldx95, "N",
					"AllCounterpartyCollateralRehypothecatedRate",
					new BigInteger("282"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"AIF Leverage Info", "PERCENT", "37.282", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField282);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx96 = new ReportField(reportCatalog,
					reportFieldx95, "X", "SecuritiesCashBorrowing",
					new BigInteger("0"), null, "", null, null, "38.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx96);

			// (283) <UnsecuredBorrowingAmount>
			ReportField reportField283 = new ReportField(reportCatalog,
					reportFieldx96, "N", "UnsecuredBorrowingAmount",
					new BigInteger("283"), "[0-9]{0,15}?", "",
					"Securities Cash Borrowing", "NUMBER", "38.283", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField283);

			// (284) <SecuredBorrowingPrimeBrokerageAmount>
			ReportField reportField284 = new ReportField(reportCatalog,
					reportFieldx96, "N",
					"SecuredBorrowingPrimeBrokerageAmount", new BigInteger(
							"284"), "[0-9]{0,15}?", "",
					"Securities Cash Borrowing", "NUMBER", "38.284", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField284);

			// (285) <SecuredBorrowingReverseRepoAmount>
			ReportField reportField285 = new ReportField(reportCatalog,
					reportFieldx96, "N", "SecuredBorrowingReverseRepoAmount",
					new BigInteger("285"), "[0-9]{0,15}?", "",
					"Securities Cash Borrowing", "NUMBER", "38.285", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField285);

			// (286) <SecuredBorrowingOtherAmount>
			ReportField reportField286 = new ReportField(reportCatalog,
					reportFieldx96, "N", "SecuredBorrowingOtherAmount",
					new BigInteger("286"), "[0-9]{0,15}?", "",
					"Securities Cash Borrowing", "NUMBER", "38.286", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField286);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx97 = new ReportField(reportCatalog,
					reportFieldx95, "X", "FinancialInstrumentBorrowing",
					new BigInteger("0"), null, "", null, null, "39.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx97);

			// (287) <ExchangedTradedDerivativesExposureValue>
			ReportField reportField287 = new ReportField(reportCatalog,
					reportFieldx97, "N",
					"ExchangedTradedDerivativesExposureValue", new BigInteger(
							"287"), "[0-9]{0,15}?", "",
					"Financial Instrument Borrowing", "NUMBER", "39.287",
					"0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField287);

			// (288) <OTCDerivativesAmount>
			ReportField reportField288 = new ReportField(reportCatalog,
					reportFieldx97, "N", "OTCDerivativesAmount",
					new BigInteger("288"), "[0-9]{0,15}?", "",
					"Financial Instrument Borrowing", "NUMBER", "39.288",
					"0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField288);

			// (289) <ShortPositionBorrowedSecuritiesValue>
			ReportField reportField289 = new ReportField(reportCatalog,
					reportFieldx95, "N",
					"ShortPositionBorrowedSecuritiesValue", new BigInteger(
							"289"), "[0-9]{0,15}?", "",
					"Financial Instrument Borrowing", "NUMBER", "39.289",
					"0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField289);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx98 = new ReportField(reportCatalog,
					reportFieldx95, "X", "ControlledStructures",
					new BigInteger("0"), null, "", null, null, "40.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx98);
			ReportField reportFieldx99 = new ReportField(reportCatalog,
					reportFieldx98, "X", "ControlledStructure", new BigInteger(
							"0"), null, "", null, null, "40.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx99);
			ReportField reportFieldx100 = new ReportField(reportCatalog,
					reportFieldx99, "X", "ControlledStructureIdentification",
					new BigInteger("0"), null, "", null, null, "40.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx100);

			// (290) <EntityName>
			ReportField reportField290 = new ReportField(reportCatalog,
					reportFieldx100, "Z", "EntityName", new BigInteger("290"),
					".{0,300}", "", "Controlled Structures", "NAME", "40.290",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField290);

			// (291) <EntityIdentificationLEI>
			ReportField reportField291 = new ReportField(reportCatalog,
					reportFieldx100, "A", "EntityIdentificationLEI",
					new BigInteger("291"), ".{20}", "",
					"Controlled Structures", "LEICodeType", "40.291", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField291);

			// (292) <EntityIdentificationBIC>
			ReportField reportField292 = new ReportField(reportCatalog,
					reportFieldx100, "A", "EntityIdentificationBIC",
					new BigInteger("292"), ".{11}", "",
					"Controlled Structures", "BICCodeType", "40.292", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField292);

			// (293) <ControlledStructureExposureValue>
			ReportField reportField293 = new ReportField(reportCatalog,
					reportFieldx99, "N", "ControlledStructureExposureValue",
					new BigInteger("293"), "[0-9]{0,15}?", "",
					"Controlled Structures", "NUMBER", "40.293", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField293);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx101 = new ReportField(reportCatalog,
					reportFieldx95, "X", "LeverageAIF", new BigInteger("0"),
					null, "", null, null, "41.1", "1,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx101);

			// (294) <GrossMethodRate>
			ReportField reportField294 = new ReportField(reportCatalog,
					reportFieldx101, "N", "GrossMethodRate", new BigInteger(
							"294"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Leverage AIF", "DECIMAL", "41.294", "1,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField294);

			// (295) <CommitmentMethodRate>
			ReportField reportField295 = new ReportField(reportCatalog,
					reportFieldx101, "N", "CommitmentMethodRate",
					new BigInteger("295"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Leverage AIF", "DECIMAL", "41.295", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField295);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx102 = new ReportField(reportCatalog,
					reportFieldx94, "X", "AIFLeverageArticle24-4",
					new BigInteger("0"), null, "", null, null, "42.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx102);

			ReportField reportFieldx103 = new ReportField(reportCatalog,
					reportFieldx102, "X", "BorrowingSource",
					new BigInteger("0"), null, "", null, null, "42.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx103);

			// (296) <Ranking>
			ReportField reportField296 = new ReportField(reportCatalog,
					reportFieldx103, "N", "Ranking", new BigInteger("296"),
					"[0-9]{1}", "", "Borrowing Source", "FiveRankingType",
					"42.296", "5,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField296);

			// (297) <BorrowingSourceFlag>
			ReportField reportField297 = new ReportField(reportCatalog,
					reportFieldx103, "B", "BorrowingSourceFlag",
					new BigInteger("297"), "true|false", "",
					"Borrowing Source", "BOOLEAN", "42.297", "5,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField297);

			ReportField reportFieldx104 = new ReportField(reportCatalog,
					reportFieldx103, "X", "SourceIdentification",
					new BigInteger("0"), null, "", null, null, "42.2", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx104);

			// (298) <EntityName>
			ReportField reportField298 = new ReportField(reportCatalog,
					reportFieldx104, "Z", "EntityName", new BigInteger("298"),
					".{0,300}", "", "Borrowing Source", "NAME", "42.298",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField298);

			// (299) <EntityIdentificationLEI>
			ReportField reportField299 = new ReportField(reportCatalog,
					reportFieldx104, "A", "EntityIdentificationLEI",
					new BigInteger("299"), ".{20}", "", "Borrowing Source",
					"LEICodeType", "42.299", "0,5", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField299);

			// (300) <EntityIdentificationBIC>
			ReportField reportField300 = new ReportField(reportCatalog,
					reportFieldx104, "A", "EntityIdentificationBIC",
					new BigInteger("300"), ".{11}", "", "Borrowing Source",
					"BICCodeType", "42.300", "0,5", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField300);

			// (301) <LeverageAmount>
			ReportField reportField301 = new ReportField(reportCatalog,
					reportFieldx103, "N", "LeverageAmount", new BigInteger(
							"301"), "[0-9]{0,15}?", "", "Borrowing Source",
					"NUMBER", "42.301", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField301);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx105 = new ReportField(reportCatalog,
					reportFieldx1, "X", "CancellationAIFRecordInfo",
					new BigInteger("0"), null, "", null, null, "43.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx105);

			// (303) <CancelledAIFNationalCode>
			ReportField reportField303 = new ReportField(reportCatalog,
					reportFieldx105, "Z", "CancelledAIFNationalCode",
					new BigInteger("303"), ".{0,30}", "", "Cancellation Info",
					"AIFNationalCodeType", "43.303", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField303);

			// (304) <CancelledAIFMNationalCode>
			ReportField reportField304 = new ReportField(reportCatalog,
					reportFieldx105, "Z", "CancelledAIFMNationalCode",
					new BigInteger("304"), ".{0,30}", "", "Cancellation Info",
					"AIFMNationalCodeType", "43.304", "0,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField304);

			// (305) <CancelledReportingPeriodType>
			ReportField reportField305 = new ReportField(reportCatalog,
					reportFieldx105, "A", "CancelledReportingPeriodType",
					new BigInteger("305"), ".{2}", "", "Cancellation Info",
					"ReportingPeriodTypeType", "43.305", "0,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField305);

			// (306) <CancelledReportingPeriodYear>
			ReportField reportField306 = new ReportField(reportCatalog,
					reportFieldx105, "D", "CancelledReportingPeriodYear",
					new BigInteger("306"), "[0-9]{4}?", "",
					"Cancellation Info", "YEAR", "43.306", "0,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField306);

			// (307) <CancelledRecordFlag>
			ReportField reportField307 = new ReportField(reportCatalog,
					reportFieldx105, "A", "CancelledRecordFlag",
					new BigInteger("307"), ".{1}", "", "Cancellation Info",
					"CancelledRecordFlagType", "43.307", "0,1", versionField,
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

}
