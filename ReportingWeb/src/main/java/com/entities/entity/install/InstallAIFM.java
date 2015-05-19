package com.entities.entity.install;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.context.ApplicationContext;

import com.entities.dao.loader.FileColumDAO;
import com.entities.dao.loader.FileConfigDAO;
import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.dao.reportingtool.DepartmentDAO;
import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.dao.reportingtool.ReportFieldDAO;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.FileConfig;
import com.entities.entity.reportingtool.Company;
import com.entities.entity.reportingtool.Department;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportField;
import com.entities.utilities.hibernate.VersionAuditor;
import com.reportingtool.utilities.ReportUtilities;

public class InstallAIFM {

	private ApplicationContext applicationContext;

	public InstallAIFM(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Process to install in database reportCatalog and reportFields from AIFM
	 * report, also an example of load file.
	 * 
	 * @param applicationContext
	 */
	public void install() {

		try {

			VersionAuditor versionAdmin = new VersionAuditor("admin");

			String versionField = ReportUtilities.reportVersion;

			Company company1 = new Company("Santander Asset Manager", "Spain",
					"SAM", "", versionAdmin);
			
			Company company2 = new Company("Santander Private Banking", "Spain",
					"SPBF", "", versionAdmin);
			
			Company company3 = new Company("Santander Funds Administration (COFFER)", "Spain",
					"SDF", "", versionAdmin);

			Department department1 = new Department(company1, "SAM Operation department",
					"SAM", "", "Spain", new VersionAuditor("admin"));
			
			Department department2 = new Department(company2, "SPBF Operation department",
					"SPBF", "", "Spain", new VersionAuditor("admin"));
			
			Department department3 = new Department(company3, "SDF Operation department",
					"SDF", "", "Spain", new VersionAuditor("admin"));

//			Fund fund1 = new Fund(company3, "SAM fund 1", "ES000001", "FUND1",
//					"", null, versionAdmin);
//
//			Fund fund2 = new Fund(company3, "SAM fund 2", "ES000002", "FUND2",
//					"", null, versionAdmin);
//
//			FundGroup fundGroup = new FundGroup(fund1, department,
//					"RISK FUNDS", "", versionAdmin);

			ReportCatalog reportCatalog = new ReportCatalog(versionField,
					"COMPANY", "AIFM 2014", "", null, null, null, versionAdmin);

			// String str1 = "2014-01-01";
			// String str2 = "2014-12-31";
			// DateFormat format = new SimpleDateFormat(
			// ReportUtilities.datePattern);

			// Date date1 = format.parse(str1);
			// Date date2 = format.parse(str2);

			// ReportExecution reportExecution = new ReportExecution(
			// reportCatalog, company, null, "Prueba Q1 2014", "", "Q1",
			// "2014", date2, date1,
			// ReportExecutionStatusEnum.CREATION
			// .getReportExecutionStatus(), null, null, null,
			// null, null, null, null, null, null, null, versionAdmin);

			ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
					.getBean("reportFieldDAO");

			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			reportCatalogDAO.create(reportCatalog);

			ReportField reportFieldx1 = new ReportField(reportCatalog, null,
					"X", "AIFMReportingInfo", new BigInteger("0"), null, "",
					null, null, "1.", "1,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportFieldx1);

			// (1) <ReportingMemberState>
			ReportField reportField1 = new ReportField(reportCatalog,
					reportFieldx1, "A", "ReportingMemberState", new BigInteger(
							"1"), ".{2}", "", "General Info",
					"CountryCodeType", "1.01", "1,1", versionField, null, null,
					null, versionAdmin);
			reportField1.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField1);

			// (2) <Version>
			ReportField reportField2 = new ReportField(reportCatalog,
					reportFieldx1, "A", "Version", new BigInteger("2"),
					"([0-9])+\\.([0-9])+", "", "General Info", "VERSION",
					"1.02", "1,1", versionField, null, null, null, versionAdmin);
			reportField2.setReportFieldEditable(true);
			reportField2.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField2);

			// (3) <CreationDateAndTime>
			ReportField reportField3 = new ReportField(reportCatalog,
					reportFieldx1, "D", "CreationDateAndTime", new BigInteger(
							"3"),
					"[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}",
					"", "General Info", "DATETIME", "1.03", "1,1", versionField,
					null, null, null, versionAdmin);
			reportField3.setReportFieldEditable(true);
			reportField3.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField3);

			ReportField reportFieldx2 = new ReportField(reportCatalog,
					reportFieldx1, "X", "AIFMRecordInfo", new BigInteger("0"),
					null, "", null, null, "1.", "1,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportFieldx2);

			// (4) <FilingType>
			ReportField reportField4 = new ReportField(reportCatalog,
					reportFieldx2, "A", "FilingType", new BigInteger("4"),
					".{4}", "", "General Info", "FilingTypeType", "1.04", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField4.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField4);

			// (5) <AIFMContentType>
			ReportField reportField5 = new ReportField(reportCatalog,
					reportFieldx2, "N", "AIFMContentType", new BigInteger("5"),
					"[0-9]{1}", "", "General Info", "AIFMContentTypeType",
					"1.05", "1,1", versionField, null, null, null, versionAdmin);
			reportField5.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField5);

			// (6) <ReportingPeriodStartDate>
			ReportField reportField6 = new ReportField(reportCatalog,
					reportFieldx2, "D", "ReportingPeriodStartDate",
					new BigInteger("6"), "[0-9]{4}-[0-9]{2}-[0-9]{2}", "",
					"General Info", "DATE", "1.06", "1,1", versionField, null,
					null, null, versionAdmin);
			reportField6.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField6);

			// (7) <ReportingPeriodEndDate>
			ReportField reportField7 = new ReportField(reportCatalog,
					reportFieldx2, "D", "ReportingPeriodEndDate",
					new BigInteger("7"), "[0-9]{4}-[0-9]{2}-[0-9]{2}", "",
					"General Info", "DATE", "1.07", "1,1", versionField, null,
					null, null, versionAdmin);
			reportField7.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField7);

			// (8) <ReportingPeriodType>
			ReportField reportField8 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ReportingPeriodType", new BigInteger(
							"8"), ".{2}", "", "General Info",
					"ReportingPeriodTypeType", "1.08", "1,1", versionField,
					null, null, null, versionAdmin);
			reportField8.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField8);

			// (9) <ReportingPeriodYear>
			ReportField reportField9 = new ReportField(reportCatalog,
					reportFieldx2, "N", "ReportingPeriodYear", new BigInteger(
							"9"), "[0-9]{4}", "", "General Info", "YEAR",
					"1.09", "1,1", versionField, null, null, null, versionAdmin);
			reportField9.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField9);

			// (10) <AIFMReportingObligationChangeFrequencyCode>
			ReportField reportField10 = new ReportField(reportCatalog,
					reportFieldx2, "A",
					"AIFMReportingObligationChangeFrequencyCode",
					new BigInteger("10"), ".{2}", "", "General Info",
					"ReportingObligationChangeFrequencyCodeType", "1.10", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField10);

			// (11) <AIFMReportingObligationChangeContentsCode>
			ReportField reportField11 = new ReportField(reportCatalog,
					reportFieldx2, "N",
					"AIFMReportingObligationChangeContentsCode",
					new BigInteger("11"), "[0-9]{1}", "", "General Info",
					"AIFMReportingObligationChangeContentsCodeType", "1.11",
					"0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField11);

			// (12) <AIFMReportingObligationChangeQuarter>
			ReportField reportField12 = new ReportField(reportCatalog,
					reportFieldx2, "A", "AIFMReportingObligationChangeQuarter",
					new BigInteger("12"), ".{2}", "", "General Info",
					"ReportingObligationChangeQuarterType", "1.12", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField12);

			// (13) <LastReportingFlag>
			ReportField reportField13 = new ReportField(reportCatalog,
					reportFieldx2, "B", "LastReportingFlag", new BigInteger(
							"13"), "true|false", "", "General Info", "BOOLEAN",
					"1.13", "1,1", versionField, null, null, null, versionAdmin);
			reportField13.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField13);

			ReportField reportFieldx3 = new ReportField(reportCatalog,
					reportFieldx2, "X", "Assumptions", new BigInteger("0"),
					null, "", null, null, "2.", "0,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportFieldx3);
			ReportField reportFieldx4 = new ReportField(reportCatalog,
					reportFieldx3, "X", "Assumption", new BigInteger("0"),
					null, "", null, null, "2.", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportFieldx4);

			// (14) <QuestionNumber>
			ReportField reportField14 = new ReportField(reportCatalog,
					reportFieldx4, "N", "QuestionNumber", new BigInteger("14"),
					"[0-9]{0,3}", "", "Assumptions", "QUESTION_AIFM", "2.14", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField14);

			// (15) <AssumptionDescription>
			ReportField reportField15 = new ReportField(reportCatalog,
					reportFieldx4, "Z", "AssumptionDescription",
					new BigInteger("15"), ".{0,300}", "", "Assumptions",
					"DESCRIPTION", "2.15", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField15);

			// (16) <AIFMReportingCode>
			ReportField reportField16 = new ReportField(reportCatalog,
					reportFieldx2, "A", "AIFMReportingCode", new BigInteger(
							"16"), ".{1}", "", "General Info",
					"AIFMReportingCodeType", "1.16", "1,1", versionField, null,
					null, null, versionAdmin);
			reportField16.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField16);

			// (17) <AIFMJurisdiction>
			ReportField reportField17 = new ReportField(reportCatalog,
					reportFieldx2, "A", "AIFMJurisdiction",
					new BigInteger("17"), ".{2}", "", "General Info",
					"CountryCodeType", "1.17", "1,1", versionField, null, null,
					null, versionAdmin);
			reportField17.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField17);

			// (18) <AIFMNationalCode>
			ReportField reportField18 = new ReportField(reportCatalog,
					reportFieldx2, "Z", "AIFMNationalCode",
					new BigInteger("18"), ".{0,30}", "", "General Info",
					"AIFMNationalCodeType", "1.18", "1,1", versionField, null,
					null, null, versionAdmin);
			reportField18.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField18);

			// (19) <AIFMName>
			ReportField reportField19 = new ReportField(reportCatalog,
					reportFieldx2, "Z", "AIFMName", new BigInteger("19"),
					".{0,300}", "", "General Info", "AIFM_NAME", "1.19", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField19.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField19);

			// (20) <AIFMEEAFlag>
			ReportField reportField20 = new ReportField(reportCatalog,
					reportFieldx2, "B", "AIFMEEAFlag", new BigInteger("20"),
					"true|false", "", "General Info", "BOOLEAN", "1.20", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField20.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField20);

			// (21) <AIFMNoReportingFlag>
			ReportField reportField21 = new ReportField(reportCatalog,
					reportFieldx2, "B", "AIFMNoReportingFlag", new BigInteger(
							"21"), "true|false", "", "General Info", "BOOLEAN",
					"1.21", "1,1", versionField, null, null, null, versionAdmin);
			reportField21.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField21);

			ReportField reportFieldx5 = new ReportField(reportCatalog,
					reportFieldx2, "X", "AIFMCompleteDescription",
					new BigInteger("0"), null, "", null, null, "1.", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx5);
			ReportField reportFieldx6 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFMIdentifier", new BigInteger("0"),
					null, "", null, null, "3.", "0,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportFieldx6);

			// (22) <AIFMIdentifierLEI>
			ReportField reportField22 = new ReportField(reportCatalog,
					reportFieldx6, "A", "AIFMIdentifierLEI", new BigInteger(
							"22"), ".{20}", "", "Complete Description",
					"LEICodeType", "3.22", "0,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField22);

			// (23) <AIFMIdentifierBIC>
			ReportField reportField23 = new ReportField(reportCatalog,
					reportFieldx6, "A", "AIFMIdentifierBIC", new BigInteger(
							"23"), ".{11}", "", "Complete Description",
					"BICCodeType", "3.23", "0,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField23);

			// (24) <Old_ReportingMemberState>
			ReportField reportField24 = new ReportField(reportCatalog,
					reportFieldx6, "A", "Old_ReportingMemberState", new BigInteger(
							"24"), ".{2}", "", "General Info",
					"CountryCodeType", "1.24", "0,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField24);

			// (25) <Old_AIFMNationalCode>
			ReportField reportField25 = new ReportField(reportCatalog,
					reportFieldx6, "Z", "Old_AIFMNationalCode",
					new BigInteger("25"), ".{0,30}", "", "General Info",
					"AIFMNationalCodeType", "1.25", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField25);

			ReportField reportFieldx7 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFMPrincipalMarkets", new BigInteger(
							"0"), null, "", null, null, "4.", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx7);
			ReportField reportFieldx8 = new ReportField(reportCatalog,
					reportFieldx7, "X", "AIFMFivePrincipalMarket",
					new BigInteger("0"), null, "", null, null, "4.", "1,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx8);

			// (26) <Ranking>
			ReportField reportField26 = new ReportField(reportCatalog,
					reportFieldx8, "N", "Ranking", new BigInteger("26"),
					"[0-9]{1}", "", "Principal Markets", "FiveRankingType",
					"4.26", "5,5", versionField, null, null, null, versionAdmin);
			reportField26.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField26);

			ReportField reportFieldx9 = new ReportField(reportCatalog,
					reportFieldx8, "X", "MarketIdentification", new BigInteger(
							"0"), null, "", null, null, "4.", "5,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx9);

			// (27) <MarketCodeType>
			ReportField reportField27 = new ReportField(reportCatalog,
					reportFieldx9, "A", "MarketCodeType", new BigInteger("27"),
					".{3,4}", "", "Principal Markets",
					"MarketCodeTypeWithNOTType", "4.27", "5,5", versionField,
					null, null, null, versionAdmin);
			reportField27.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField27);

			// (28) <MarketCode>
			ReportField reportField28 = new ReportField(reportCatalog,
					reportFieldx9, "A", "MarketCode", new BigInteger("28"),
					".{3,4}", "", "Principal Markets", "MICCodeType", "4.28",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField28);

			// (29) <AggregatedValueAmount>
			ReportField reportField29 = new ReportField(reportCatalog,
					reportFieldx8, "N", "AggregatedValueAmount",
					new BigInteger("29"), "[0-9]{0,15}?", "",
					"Principal Markets", "NUMBER", "4.29", "0,5", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField29);

			ReportField reportFieldx10 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFMPrincipalInstruments",
					new BigInteger("0"), null, "", null, null, "5.", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx10);
			ReportField reportFieldx11 = new ReportField(reportCatalog,
					reportFieldx10, "X", "AIFMPrincipalInstrument",
					new BigInteger("0"), null, "", null, null, "5.", "1,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx11);

			// (30) <Ranking>
			ReportField reportField30 = new ReportField(reportCatalog,
					reportFieldx11, "N", "Ranking", new BigInteger("30"),
					"[0-9]{1}", "", "Principal Instruments", "FiveRankingType",
					"5.30", "5,5", versionField, null, null, null, versionAdmin);
			reportField30.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField30);

			// (31) <SubAssetType>
			ReportField reportField31 = new ReportField(reportCatalog,
					reportFieldx11, "A", "SubAssetType", new BigInteger("31"),
					".{1,12}", "", "Principal Instruments", "SubAssetTypeType",
					"5.31", "5,5", versionField, null, null, null, versionAdmin);
			reportField31.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField31);

			// (32) <AggregatedValueAmount>
			ReportField reportField32 = new ReportField(reportCatalog,
					reportFieldx11, "N", "AggregatedValueAmount",
					new BigInteger("32"), "[0-9]{0,15}?", "",
					"Principal Instruments", "NUMBER", "5.32", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField32);

			// (33) <AUMAmountInEuro>
			ReportField reportField33 = new ReportField(reportCatalog,
					reportFieldx5, "N", "AUMAmountInEuro",
					new BigInteger("33"), "[0-9]{0,15}?", "",
					"Complete Description", "NUMBER", "3.33", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField33.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField33);

			// (34) <AUMAmountInBaseCurrency>
			ReportField reportField34 = new ReportField(reportCatalog,
					reportFieldx5, "N", "AUMAmountInBaseCurrency",
					new BigInteger("34"), "[0-9]{0,15}?", "",
					"Complete Description", "NUMBER", "3.34", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField34);

			ReportField reportFieldx12 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFMBaseCurrencyDescription",
					new BigInteger("0"), null, "", null, null, "1.", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx12);

			// (35) <BaseCurrency>
			ReportField reportField35 = new ReportField(reportCatalog,
					reportFieldx12, "A", "BaseCurrency", new BigInteger("35"),
					".{3}", "", "Complete Description", "CurrencyCodeType",
					"3.35", "0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField35);

			// (36) <FXEURReferenceRateType>
			ReportField reportField36 = new ReportField(reportCatalog,
					reportFieldx12, "A", "FXEURReferenceRateType",
					new BigInteger("36"), ".{3}", "", "Complete Description",
					"FXEURReferenceRateTypeType", "3.36", "0,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField36);

			// (37) <FXEURRate>
			ReportField reportField37 = new ReportField(reportCatalog,
					reportFieldx12, "N", "FXEURRate", new BigInteger("37"),
					"[0-9]{1,15}(\\.[0-9]{1,4})?", "", "Complete Description",
					"DECIMAL", "3.37", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField37);

			// (38) <FXEUROtherReferenceRateDescription>
			ReportField reportField38 = new ReportField(reportCatalog,
					reportFieldx12, "Z", "FXEUROtherReferenceRateDescription",
					new BigInteger("38"), ".{0,30}", "",
					"Complete Description", "DESCRIPTION", "3.38", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField38);

			ReportField reportFieldx13 = new ReportField(reportCatalog,
					reportFieldx1, "X", "CancellationAIFMRecordInfo",
					new BigInteger("0"), null, "", null, null, "6.", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx13);

			// (39) <CancelledAIFMNationalCode>
			ReportField reportField39 = new ReportField(reportCatalog,
					reportFieldx13, "Z", "CancelledAIFMNationalCode",
					new BigInteger("39"), ".{0,30}", "", "Cancellation Info",
					"AIFMNationalCodeType", "6.39", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField39);

			// (40) <CancelledReportingPeriodType>
			ReportField reportField40 = new ReportField(reportCatalog,
					reportFieldx13, "A", "CancelledReportingPeriodType",
					new BigInteger("40"), ".{2}", "", "Cancellation Info",
					"ReportingPeriodTypeType", "6.40", "0,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField40);

			// (41) <CancelledReportingPeriodYear>
			ReportField reportField41 = new ReportField(reportCatalog,
					reportFieldx13, "D", "CancelledReportingPeriodYear",
					new BigInteger("41"), "[0-9]{4}?", "", "Cancellation Info",
					"YEAR", "6.41", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField41);

			// (42) <CancelledRecordFlag>
			ReportField reportField42 = new ReportField(reportCatalog,
					reportFieldx13, "A", "CancelledRecordFlag", new BigInteger(
							"42"), ".{1}", "", "Cancellation Info",
					"CancelledRecordFlagType", "6.42", "0,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField42);

			FileConfig fileConfig0 = new FileConfig(department1, "AIFM",
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

			FileConfig fileConfig = new FileConfig(department1, "AIFM",
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
					"NUMBER", new BigDecimal(60), "FXEURRate", "", "#,#",
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

			// FileColumList fileColumList1 = new FileColumList(fileColum4,
			// "TEXT", "FIRST", "INIT", versionAdmin);

			InstallReportSemantic installReportSemantic = new InstallReportSemantic(
					applicationContext);
			installReportSemantic.installAIFM(reportCatalog);

			// DAO

			CompanyDAO companyDAO = (CompanyDAO) applicationContext
					.getBean("companyDAO");
			companyDAO.create(company1);
			companyDAO.create(company2);
			companyDAO.create(company3);

			DepartmentDAO departmentDAO = (DepartmentDAO) applicationContext
					.getBean("departmentDAO");
			departmentDAO.create(department1);
			departmentDAO.create(department2);
			departmentDAO.create(department3);
//
//			FundDAO fundDAO = (FundDAO) applicationContext.getBean("fundDAO");
//			fundDAO.create(fund1);
//			fundDAO.create(fund2);
//
//			FundGroupDAO fundGroupDAO = (FundGroupDAO) applicationContext
//					.getBean("fundGroupDAO");
//			fundGroupDAO.create(fundGroup);

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

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void installExampleAIFM(ApplicationContext applicationContext) {

		// TODO make better example

		// VersionAuditor versionAdmin = new VersionAuditor("admin");

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

}
