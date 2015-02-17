package com.entities.entity;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.entities.dao.loader.FileColumDAO;
import com.entities.dao.loader.FileConfigDAO;
import com.entities.dao.loader.LoadFileDAO;
import com.entities.dao.loader.LoadRawDAO;
import com.entities.dao.loader.LoadRawDataDAO;
import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.dao.reportingtool.DepartmentDAO;
import com.entities.dao.reportingtool.FundDAO;
import com.entities.dao.reportingtool.FundGroupDAO;
import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.dao.reportingtool.ReportDataDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.dao.reportingtool.ReportFieldDAO;
import com.entities.dao.reportingtool.ReportFieldListDAO;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.FileConfig;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.loader.LoadRaw;
import com.entities.entity.loader.LoadRawData;
import com.entities.entity.reportingtool.Company;
import com.entities.entity.reportingtool.Department;
import com.entities.entity.reportingtool.Fund;
import com.entities.entity.reportingtool.FundGroup;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;
import com.entities.entity.reportingtool.ReportFieldList;
import com.entities.utilities.hibernate.VersionAuditor;

public class InstallEntities {

	public InstallEntities() {

	}

	public void installTest(ApplicationContext aplicationContext) {

		// Company company = new Company("Santander Asset Manager", "Spain",
		// "SAM", "", null, null, null, null, new VersionAuditor("admin"));
		//
		// Company company2 = new Company("Santander Asset Manager2", "Spain",
		// "SAM2", "", null, null, null, null, new VersionAuditor("admin"));

		CompanyDAO companyDAO = (CompanyDAO) aplicationContext
				.getBean("companyDAO");

		// companyDAO.create(company);
		// companyDAO.create(company2);

		companyDAO.findAll();

		Company company = new Company();
		company.setCompanyName("Santander Asset Manager");
		company.setAuditor(new VersionAuditor("admin"));

		List<Company> company2 = (List<Company>) companyDAO
				.findByExample(company);

		for (Company comp : company2) {
			System.out.println("Compañia 2: " + comp.getCompanyName());

			companyDAO.delete(comp);
		}

		// companyDAO.deleteAll();
		// companyDAO.deleteAll();
		// companyDAO.create(company);
	}

	public void installEntitiesFull(ApplicationContext aplicationContext) {

		try {

			VersionAuditor versionAdmin = new VersionAuditor("admin");

			Company company = new Company("Santander Asset Manager", "Spain",
					"SAM", "", null, null, null, null, new VersionAuditor(
							"admin"));
			Department department = new Department(company, "Risk department",
					"RISK", "", "Spain", null, null, null, new VersionAuditor(
							"admin"));
			Fund fund = new Fund(company, "SAM fund 1", "ES000001", "FUND1",
					"", null, null, null, versionAdmin);
			FundGroup fundGroup = new FundGroup(fund, department, "RISK FUNDS",
					"", versionAdmin);

			ReportCatalog reportCatalog = new ReportCatalog("1.2", "COMPANY",
					"AIFMD 2014", "", null, null, null, new VersionAuditor(
							"admin"));

			String str1 = "2014-01-01";
			String str2 = "2014-12-31";
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = format.parse(str1);
			Date date2 = format.parse(str2);

			ReportExecution reportExecution = new ReportExecution(
					reportCatalog, company, fund, "Q1", "2014", date2, date1,
					"CREATION", null, null, null, null, null, null, null, null,
					null, null, null, null, versionAdmin);

			ReportField reportField1 = new ReportField(reportCatalog, "A",
					"ReportingMemberState", ".{2}", "", "", "COUNTRY", "1.2",
					null, null, null, versionAdmin);
			ReportField reportField2 = new ReportField(reportCatalog, "A",
					"Version", "([0-9])+\\.([0-9])+", "", "", "VERSION", "1.2",
					null, null, null, versionAdmin);
			ReportField reportField3 = new ReportField(reportCatalog, "D",
					"CreationDateAndTime",
					"[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}",
					"", "", "DATETIME", "1.2", null, null, null, versionAdmin);
			ReportField reportField4 = new ReportField(reportCatalog, "A",
					"FilingType", ".{4}", "", "", "FILLING", "1.2", null, null,
					null, versionAdmin);
			ReportField reportField5 = new ReportField(reportCatalog, "N",
					"AIFMContentType", "[0-9]{1}", "", "", "AIFM_TYPE", "1.2",
					null, null, null, versionAdmin);
			ReportField reportField6 = new ReportField(reportCatalog, "D",
					"ReportingPeriodStartDate", "[0-9]{4}-[0-9]{2}-[0-9]{2}",
					"", "", "DATE", "1.2", null, null, null, versionAdmin);
			ReportField reportField7 = new ReportField(reportCatalog, "D",
					"ReportingPeriodEndDate", "[0-9]{4}-[0-9]{2}-[0-9]{2}", "",
					"", "DATE", "1.2", null, null, null, versionAdmin);
			ReportField reportField8 = new ReportField(reportCatalog, "A",
					"ReportingPeriodType", ".{2}", "", "", "PERIOD", "1.2",
					null, null, null, versionAdmin);
			ReportField reportField9 = new ReportField(reportCatalog, "N",
					"ReportingPeriodYear", "[0-9]{4}", "", "", "YEAR", "1.2",
					null, null, null, versionAdmin);
			ReportField reportField10 = new ReportField(reportCatalog, "A",
					"AIFMReportingObligationChangeFrequencyCode", ".{2}", "",
					"", "AIFM_CHANGE_FREQ", "1.2", null, null, null,
					versionAdmin);
			ReportField reportField11 = new ReportField(reportCatalog, "N",
					"AIFMReportingObligationChangeContentsCode", "[0-9]{1}",
					"", "", "AIFM_CHANGE_CONTENT", "1.2", null, null, null,
					versionAdmin);
			ReportField reportField12 = new ReportField(reportCatalog, "A",
					"AIFMReportingObligationChangeQuarter", ".{2}", "", "",
					"QUARTER", "1.2", null, null, null, versionAdmin);
			ReportField reportField13 = new ReportField(reportCatalog, "B",
					"LastReportingFlag", "true|false", "", "", "BOOLEAN",
					"1.2", null, null, null, versionAdmin);
			ReportField reportField14 = new ReportField(reportCatalog, "N",
					"QuestionNumber", "[0-9]{0,3}", "", "", "QUESTION", "1.2",
					null, null, null, versionAdmin);
			ReportField reportField15 = new ReportField(reportCatalog, "Z",
					"AssumptionDescription", ".{0,300}", "", "", "DESCRIPTION",
					"1.2", null, null, null, versionAdmin);
			ReportField reportField16 = new ReportField(reportCatalog, "A",
					"AIFMReportingCode", ".{1}", "", "", "REPORT_CODE", "1.2",
					null, null, null, versionAdmin);
			ReportField reportField17 = new ReportField(reportCatalog, "A",
					"AIFMJurisdiction", ".{2}", "", "", "COUNTRY", "1.2", null,
					null, null, versionAdmin);
			ReportField reportField18 = new ReportField(reportCatalog, "Z",
					"AIFMNationalCode", ".{0,30}", "", "", "NATIONAL", "1.2",
					null, null, null, versionAdmin);
			ReportField reportField19 = new ReportField(reportCatalog, "Z",
					"AIFMName", ".{0,300}", "", "", "AIFM_NAME", "1.2", null,
					null, null, versionAdmin);
			ReportField reportField20 = new ReportField(reportCatalog, "B",
					"AIFMEEAFlag", "true|false", "", "", "BOOLEAN", "1.2",
					null, null, null, versionAdmin);
			ReportField reportField21 = new ReportField(reportCatalog, "B",
					"AIFMNoReportingFlag", "true|false", "", "", "BOOLEAN",
					"1.2", null, null, null, versionAdmin);
			ReportField reportField22 = new ReportField(reportCatalog, "A",
					"AIFMIdentifierLEI", ".{20}", "", "", "LEI", "1.2", null,
					null, null, versionAdmin);
			ReportField reportField23 = new ReportField(reportCatalog, "A",
					"AIFMIdentifierBIC", ".{11}", "", "", "BIC", "1.2", null,
					null, null, versionAdmin);
			ReportField reportField24 = new ReportField(reportCatalog, "A",
					"ReportingMemberState1", ".{2}", "", "", "COUNTRY", "1.2",
					null, null, null, versionAdmin);
			ReportField reportField25 = new ReportField(reportCatalog, "Z",
					"AIFMNationalCode1", ".{0,30}", "", "", "", "1.2", null,
					null, null, versionAdmin);

			ReportField reportField26 = new ReportField(reportCatalog, "N",
					"AIFMFivePrincipalMarket1", "[0-9]{1}", "", "", "RANKING",
					"1.2", null, null, null, versionAdmin);
			ReportField reportField27 = new ReportField(reportCatalog, "A",
					"MarketCodeType1", ".{4}", "", "", "MARKET", "1.2", null,
					null, null, versionAdmin);
			ReportField reportField28 = new ReportField(reportCatalog, "A",
					"MarketCode1", ".{4}", "", "", "MIC", "1.2", null, null,
					null, versionAdmin);
			ReportField reportField29 = new ReportField(reportCatalog, "N",
					"MarketAggregatedValueAmount1", "[0-9]{0,15}?", "", "",
					"NUMBER", "1.2", null, null, null, versionAdmin);
			ReportField reportField30 = new ReportField(reportCatalog, "N",
					"AIFMFivePrincipalMarket2", "[0-9]{1}", "", "", "RANKING",
					"1.2", null, null, null, versionAdmin);
			ReportField reportField31 = new ReportField(reportCatalog, "A",
					"MarketCodeType2", ".{4}", "", "", "MARKET", "1.2", null,
					null, null, versionAdmin);
			ReportField reportField32 = new ReportField(reportCatalog, "A",
					"MarketCode2", ".{4}", "", "", "MIC", "1.2", null, null,
					null, versionAdmin);
			ReportField reportField33 = new ReportField(reportCatalog, "N",
					"MarketAggregatedValueAmount2", "[0-9]{0,15}?", "", "",
					"NUMBER", "1.2", null, null, null, versionAdmin);
			ReportField reportField34 = new ReportField(reportCatalog, "N",
					"AIFMFivePrincipalMarket3", "[0-9]{1}", "", "", "RANKING",
					"1.2", null, null, null, versionAdmin);
			ReportField reportField35 = new ReportField(reportCatalog, "A",
					"MarketCodeType3", ".{4}", "", "", "MARKET", "1.2", null,
					null, null, versionAdmin);
			ReportField reportField36 = new ReportField(reportCatalog, "A",
					"MarketCode3", ".{4}", "", "", "MIC", "1.2", null, null,
					null, versionAdmin);
			ReportField reportField37 = new ReportField(reportCatalog, "N",
					"MarketAggregatedValueAmount3", "[0-9]{0,15}?", "", "",
					"NUMBER", "1.2", null, null, null, versionAdmin);
			ReportField reportField38 = new ReportField(reportCatalog, "N",
					"AIFMFivePrincipalMarket4", "[0-9]{1}", "", "", "RANKING",
					"1.2", null, null, null, versionAdmin);
			ReportField reportField39 = new ReportField(reportCatalog, "A",
					"MarketCodeType4", ".{4}", "", "", "MARKET", "1.2", null,
					null, null, versionAdmin);
			ReportField reportField40 = new ReportField(reportCatalog, "A",
					"MarketCode4", ".{4}", "", "", "MIC", "1.2", null, null,
					null, versionAdmin);
			ReportField reportField41 = new ReportField(reportCatalog, "N",
					"MarketAggregatedValueAmount4", "[0-9]{0,15}?", "", "",
					"NUMBER", "1.2", null, null, null, versionAdmin);
			ReportField reportField42 = new ReportField(reportCatalog, "N",
					"AIFMFivePrincipalMarket5", "[0-9]{1}", "", "", "RANKING",
					"1.2", null, null, null, versionAdmin);
			ReportField reportField43 = new ReportField(reportCatalog, "A",
					"MarketCodeType5", ".{4}", "", "", "MARKET", "1.2", null,
					null, null, versionAdmin);
			ReportField reportField44 = new ReportField(reportCatalog, "A",
					"MarketCode5", ".{4}", "", "", "MIC", "1.2", null, null,
					null, versionAdmin);
			ReportField reportField45 = new ReportField(reportCatalog, "N",
					"MarketAggregatedValueAmount5", "[0-9]{0,15}?", "", "",
					"NUMBER", "1.2", null, null, null, versionAdmin);

			ReportField reportField46 = new ReportField(reportCatalog, "N",
					"AIFMPrincipalInstrument1", "[0-9]{1}", "", "", "RANKING",
					"1.2", null, null, null, versionAdmin);
			ReportField reportField47 = new ReportField(reportCatalog, "A",
					"SubAssetType1", ".{11}", "", "", "SUB_ASSET", "1.2", null,
					null, null, versionAdmin);
			ReportField reportField48 = new ReportField(reportCatalog, "N",
					"InstrumentAggregatedValueAmount1", "[0-9]{0,15}?", "", "",
					"NUMBER", "1.2", null, null, null, versionAdmin);
			ReportField reportField49 = new ReportField(reportCatalog, "N",
					"AIFMPrincipalInstrument2", "[0-9]{1}", "", "", "RANKING",
					"1.2", null, null, null, versionAdmin);
			ReportField reportField50 = new ReportField(reportCatalog, "A",
					"SubAssetType2", ".{11}", "", "", "SUB_ASSET", "1.2", null,
					null, null, versionAdmin);
			ReportField reportField51 = new ReportField(reportCatalog, "N",
					"InstrumentAggregatedValueAmount2", "[0-9]{0,15}?", "", "",
					"NUMBER", "1.2", null, null, null, versionAdmin);
			ReportField reportField52 = new ReportField(reportCatalog, "N",
					"AIFMPrincipalInstrument3", "[0-9]{1}", "", "", "RANKING",
					"1.2", null, null, null, versionAdmin);
			ReportField reportField53 = new ReportField(reportCatalog, "A",
					"SubAssetType3", ".{11}", "", "", "SUB_ASSET", "1.2", null,
					null, null, versionAdmin);
			ReportField reportField54 = new ReportField(reportCatalog, "N",
					"InstrumentAggregatedValueAmount3", "[0-9]{0,15}?", "", "",
					"NUMBER", "1.2", null, null, null, versionAdmin);
			ReportField reportField55 = new ReportField(reportCatalog, "type",
					"AIFMPrincipalInstrument4", "[0-9]{1}", "", "", "RANKING",
					"1.2", null, null, null, versionAdmin);
			ReportField reportField56 = new ReportField(reportCatalog, "A",
					"SubAssetType4", ".{11}", "", "", "SUB_ASSET", "1.2", null,
					null, null, versionAdmin);
			ReportField reportField57 = new ReportField(reportCatalog, "N",
					"InstrumentAggregatedValueAmount4", "[0-9]{0,15}?", "", "",
					"NUMBER", "1.2", null, null, null, versionAdmin);
			ReportField reportField58 = new ReportField(reportCatalog, "N",
					"AIFMPrincipalInstrument5", "[0-9]{1}", "", "", "RANKING",
					"1.2", null, null, null, versionAdmin);
			ReportField reportField59 = new ReportField(reportCatalog, "A",
					"SubAssetType5", ".{11}", "", "", "SUB_ASSET", "1.2", null,
					null, null, versionAdmin);
			ReportField reportField60 = new ReportField(reportCatalog, "N",
					"InstrumentAggregatedValueAmount5", "[0-9]{0,15}?", "", "",
					"NUMBER", "1.2", null, null, null, versionAdmin);

			ReportField reportField61 = new ReportField(reportCatalog, "N",
					"AUMAmountInEuro", "[0-9]{0,15}?", "", "", "NUMBER", "1.2",
					null, null, null, versionAdmin);
			ReportField reportField62 = new ReportField(reportCatalog, "N",
					"AUMAmountInBaseCurrency", "[0-9]{0,15}?", "", "",
					"NUMBER", "1.2", null, null, null, versionAdmin);
			ReportField reportField63 = new ReportField(reportCatalog, "A",
					"BaseCurrency", ".{3}", "", "", "CURRENCY", "1.2", null,
					null, null, versionAdmin);
			ReportField reportField64 = new ReportField(reportCatalog, "A",
					"FXEURReferenceRateType", ".{3}", "", "", "FXRATE", "1.2",
					null, null, null, versionAdmin);
			ReportField reportField65 = new ReportField(reportCatalog, "N",
					"FXEURRate", "[0-9]{1,15}\\.[0-9]{1,4}", "", "", "DECIMAL",
					"1.2", null, null, null, versionAdmin);
			ReportField reportField66 = new ReportField(reportCatalog, "Z",
					"FXEUROtherReferenceRateDescription", ".{0,300}", "", "",
					"DESCRIPTION", "1.2", null, null, null, versionAdmin);
			ReportField reportField67 = new ReportField(reportCatalog, "Z",
					"CancelledAIFMNationalCode", ".{0,30}", "", "", "NATIONAL",
					"1.2", null, null, null, versionAdmin);
			ReportField reportField68 = new ReportField(reportCatalog, "A",
					"CancelledReportingPeriodType", ".{2}", "", "", "PERIOD",
					"1.2", null, null, null, versionAdmin);
			ReportField reportField69 = new ReportField(reportCatalog, "D",
					"CancelledReportingPeriodYear", "[0-9]{4}?", "", "",
					"YEAR", "1.2", null, null, null, versionAdmin);
			ReportField reportField70 = new ReportField(reportCatalog, "A",
					"CancelledRecordFlag", ".{1}", "", "", "FLAG", "1.2", null,
					null, null, versionAdmin);

			ReportFieldList reportFieldList1 = new ReportFieldList("FILLING",
					"AMND", "2", versionAdmin);
			ReportFieldList reportFieldList2 = new ReportFieldList("FILLING",
					"INIT", "2", versionAdmin);
			ReportFieldList reportFieldList3 = new ReportFieldList("PERIOD",
					"Q1", "2", versionAdmin);
			ReportFieldList reportFieldList4 = new ReportFieldList("PERIOD",
					"Q2", "2", versionAdmin);
			ReportFieldList reportFieldList5 = new ReportFieldList("PERIOD",
					"Q3", "2", versionAdmin);
			ReportFieldList reportFieldList6 = new ReportFieldList("PERIOD",
					"Q4", "2", versionAdmin);
			ReportFieldList reportFieldList7 = new ReportFieldList("PERIOD",
					"H1", "2", versionAdmin);
			ReportFieldList reportFieldList8 = new ReportFieldList("PERIOD",
					"H2", "2", versionAdmin);
			ReportFieldList reportFieldList9 = new ReportFieldList("PERIOD",
					"X1", "2", versionAdmin);
			ReportFieldList reportFieldList10 = new ReportFieldList("PERIOD",
					"X2", "2", versionAdmin);
			ReportFieldList reportFieldList11 = new ReportFieldList("PERIOD",
					"Y1", "2", versionAdmin);
			ReportFieldList reportFieldList12 = new ReportFieldList("QUARTER",
					"Q1", "2", versionAdmin);
			ReportFieldList reportFieldList13 = new ReportFieldList("QUARTER",
					"Q2", "2", versionAdmin);
			ReportFieldList reportFieldList14 = new ReportFieldList("QUARTER",
					"Q3", "2", versionAdmin);
			ReportFieldList reportFieldList15 = new ReportFieldList("QUARTER",
					"Q4", "2", versionAdmin);
			ReportFieldList reportFieldList16 = new ReportFieldList("MARKET",
					"MIC", "2", versionAdmin);
			ReportFieldList reportFieldList17 = new ReportFieldList("MARKET",
					"OTC", "2", versionAdmin);
			ReportFieldList reportFieldList18 = new ReportFieldList("MARKET",
					"XXX", "2", versionAdmin);
			ReportFieldList reportFieldList19 = new ReportFieldList("MARKET",
					"NOT", "2", versionAdmin);
			ReportFieldList reportFieldList20 = new ReportFieldList("FXRATE",
					"ECB", "2", versionAdmin);
			ReportFieldList reportFieldList21 = new ReportFieldList("FXRATE",
					"OTH", "2", versionAdmin);
			ReportFieldList reportFieldList22 = new ReportFieldList("RANKING",
					"1", "2", versionAdmin);
			ReportFieldList reportFieldList23 = new ReportFieldList("RANKING",
					"2", "2", versionAdmin);
			ReportFieldList reportFieldList24 = new ReportFieldList("RANKING",
					"3", "2", versionAdmin);
			ReportFieldList reportFieldList25 = new ReportFieldList("RANKING",
					"4", "2", versionAdmin);
			ReportFieldList reportFieldList26 = new ReportFieldList("RANKING",
					"5", "2", versionAdmin);
			ReportFieldList reportFieldList27 = new ReportFieldList("AIFMTYPE",
					"1", "2", versionAdmin);
			ReportFieldList reportFieldList28 = new ReportFieldList("AIFMTYPE",
					"2", "2", versionAdmin);
			ReportFieldList reportFieldList29 = new ReportFieldList("AIFMTYPE",
					"3", "2", versionAdmin);

			FileConfig fileConfig = new FileConfig(department, "AIFMD",
					"AIFMD2014", "|", "SIMPLE", "*", null, null, null,
					versionAdmin);

			FileColum fileColum1 = new FileColum(null, fileConfig, "type",
					new BigDecimal(1), "ID_GESTORA", "", "format", null, null,
					versionAdmin);
			FileColum fileColum2 = new FileColum(null, fileConfig, "type",
					new BigDecimal(2), "DATE_REPORT", "", "format", null, null,
					versionAdmin);
			FileColum fileColum3 = new FileColum(reportField1, fileConfig,
					"type", new BigDecimal(3), "ReportingMemberState", "",
					"format", null, null, versionAdmin);
			FileColum fileColum4 = new FileColum(reportField4, fileConfig,
					"type", new BigDecimal(4), "FilingType", "", "format",
					null, null, versionAdmin);
			FileColum fileColum5 = new FileColum(reportField6, fileConfig,
					"type", new BigDecimal(5), "ReportingPeriodStartDate", "",
					"format", null, null, versionAdmin);
			FileColum fileColum6 = new FileColum(reportField7, fileConfig,
					"type", new BigDecimal(6), "ReportingPeriodEndDate", "",
					"format", null, null, versionAdmin);
			FileColum fileColum7 = new FileColum(reportField8, fileConfig,
					"type", new BigDecimal(7), "ReportingPeriodType", "",
					"format", null, null, versionAdmin);
			FileColum fileColum8 = new FileColum(reportField9, fileConfig,
					"type", new BigDecimal(8), "ReportingPeriodYear", "",
					"format", null, null, versionAdmin);
			FileColum fileColum9 = new FileColum(reportField10, fileConfig,
					"type", new BigDecimal(9),
					"AIFMReportingObligationChangeFrequencyCode", "", "format",
					null, null, versionAdmin);
			FileColum fileColum10 = new FileColum(reportField12, fileConfig,
					"type", new BigDecimal(10),
					"AIFMReportingObligationChangeQuarter", "", "format", null,
					null, versionAdmin);
			FileColum fileColum11 = new FileColum(reportField13, fileConfig,
					"type", new BigDecimal(11), "LastReportingFlag", "",
					"format", null, null, versionAdmin);
			FileColum fileColum12 = new FileColum(reportField16, fileConfig,
					"type", new BigDecimal(12), "AIFMReportingCode", "",
					"format", null, null, versionAdmin);
			FileColum fileColum13 = new FileColum(reportField17, fileConfig,
					"type", new BigDecimal(13), "AIFMJurisdiction", "",
					"format", null, null, versionAdmin);
			FileColum fileColum14 = new FileColum(reportField18, fileConfig,
					"type", new BigDecimal(14), "AIFMNationalCode", "",
					"format", null, null, versionAdmin);
			FileColum fileColum15 = new FileColum(reportField19, fileConfig,
					"type", new BigDecimal(15), "AIFMName", "", "format", null,
					null, versionAdmin);
			FileColum fileColum16 = new FileColum(reportField20, fileConfig,
					"type", new BigDecimal(16), "AIFMEEAFlag", "", "format",
					null, null, versionAdmin);
			FileColum fileColum17 = new FileColum(reportField21, fileConfig,
					"type", new BigDecimal(17), "AIFMNoReportingFlag", "",
					"format", null, null, versionAdmin);
			FileColum fileColum18 = new FileColum(reportField26, fileConfig,
					"type", new BigDecimal(18), "AIFMFivePrincipalMarket1", "",
					"format", null, null, versionAdmin);
			FileColum fileColum19 = new FileColum(reportField27, fileConfig,
					"type", new BigDecimal(19), "MarketCodeType1", "",
					"format", null, null, versionAdmin);
			FileColum fileColum20 = new FileColum(reportField28, fileConfig,
					"type", new BigDecimal(20), "MarketCode1", "", "format",
					null, null, versionAdmin);
			FileColum fileColum21 = new FileColum(reportField29, fileConfig,
					"type", new BigDecimal(21), "MarketAggregatedValueAmount1",
					"", "format", null, null, versionAdmin);
			FileColum fileColum22 = new FileColum(reportField30, fileConfig,
					"type", new BigDecimal(22), "AIFMFivePrincipalMarket2", "",
					"format", null, null, versionAdmin);
			FileColum fileColum23 = new FileColum(reportField31, fileConfig,
					"type", new BigDecimal(23), "MarketCodeType2", "",
					"format", null, null, versionAdmin);
			FileColum fileColum24 = new FileColum(reportField32, fileConfig,
					"type", new BigDecimal(24), "MarketCode2", "", "format",
					null, null, versionAdmin);
			FileColum fileColum25 = new FileColum(reportField33, fileConfig,
					"type", new BigDecimal(25), "MarketAggregatedValueAmount2",
					"", "format", null, null, versionAdmin);
			FileColum fileColum26 = new FileColum(reportField34, fileConfig,
					"type", new BigDecimal(26), "AIFMFivePrincipalMarket3", "",
					"format", null, null, versionAdmin);
			FileColum fileColum27 = new FileColum(reportField35, fileConfig,
					"type", new BigDecimal(27), "MarketCodeType3", "",
					"format", null, null, versionAdmin);
			FileColum fileColum28 = new FileColum(reportField36, fileConfig,
					"type", new BigDecimal(28), "MarketCode3", "", "format",
					null, null, versionAdmin);
			FileColum fileColum29 = new FileColum(reportField37, fileConfig,
					"type", new BigDecimal(29), "MarketAggregatedValueAmount3",
					"", "format", null, null, versionAdmin);
			FileColum fileColum30 = new FileColum(reportField38, fileConfig,
					"type", new BigDecimal(30), "AIFMFivePrincipalMarket4", "",
					"format", null, null, versionAdmin);
			FileColum fileColum31 = new FileColum(reportField39, fileConfig,
					"type", new BigDecimal(31), "MarketCodeType4", "",
					"format", null, null, versionAdmin);
			FileColum fileColum32 = new FileColum(reportField40, fileConfig,
					"type", new BigDecimal(32), "MarketCode4", "", "format",
					null, null, versionAdmin);
			FileColum fileColum33 = new FileColum(reportField41, fileConfig,
					"type", new BigDecimal(33), "MarketAggregatedValueAmount4",
					"", "format", null, null, versionAdmin);
			FileColum fileColum34 = new FileColum(reportField42, fileConfig,
					"type", new BigDecimal(34), "AIFMFivePrincipalMarket5", "",
					"format", null, null, versionAdmin);
			FileColum fileColum35 = new FileColum(reportField43, fileConfig,
					"type", new BigDecimal(35), "MarketCodeType5", "",
					"format", null, null, versionAdmin);
			FileColum fileColum36 = new FileColum(reportField44, fileConfig,
					"type", new BigDecimal(36), "MarketCode5", "", "format",
					null, null, versionAdmin);
			FileColum fileColum37 = new FileColum(reportField45, fileConfig,
					"type", new BigDecimal(37), "MarketAggregatedValueAmount5",
					"", "format", null, null, versionAdmin);
			FileColum fileColum38 = new FileColum(reportField46, fileConfig,
					"type", new BigDecimal(38), "AIFMPrincipalInstrument1", "",
					"format", null, null, versionAdmin);
			FileColum fileColum39 = new FileColum(reportField47, fileConfig,
					"type", new BigDecimal(39), "SubAssetType1", "", "format",
					null, null, versionAdmin);
			FileColum fileColum40 = new FileColum(reportField48, fileConfig,
					"type", new BigDecimal(40),
					"InstrumentAggregatedValueAmount1", "", "format", null,
					null, versionAdmin);
			FileColum fileColum41 = new FileColum(reportField49, fileConfig,
					"type", new BigDecimal(41), "AIFMPrincipalInstrument2", "",
					"format", null, null, versionAdmin);
			FileColum fileColum42 = new FileColum(reportField50, fileConfig,
					"type", new BigDecimal(42), "SubAssetType2", "", "format",
					null, null, versionAdmin);
			FileColum fileColum43 = new FileColum(reportField51, fileConfig,
					"type", new BigDecimal(43),
					"InstrumentAggregatedValueAmount2", "", "format", null,
					null, versionAdmin);
			FileColum fileColum44 = new FileColum(reportField52, fileConfig,
					"type", new BigDecimal(44), "AIFMPrincipalInstrument3", "",
					"format", null, null, versionAdmin);
			FileColum fileColum45 = new FileColum(reportField53, fileConfig,
					"type", new BigDecimal(45), "SubAssetType3", "", "format",
					null, null, versionAdmin);
			FileColum fileColum46 = new FileColum(reportField54, fileConfig,
					"type", new BigDecimal(46),
					"InstrumentAggregatedValueAmount3", "", "format", null,
					null, versionAdmin);
			FileColum fileColum47 = new FileColum(reportField55, fileConfig,
					"type", new BigDecimal(47), "AIFMPrincipalInstrument4", "",
					"format", null, null, versionAdmin);
			FileColum fileColum48 = new FileColum(reportField56, fileConfig,
					"type", new BigDecimal(48), "SubAssetType4", "", "format",
					null, null, versionAdmin);
			FileColum fileColum49 = new FileColum(reportField57, fileConfig,
					"type", new BigDecimal(49),
					"InstrumentAggregatedValueAmount4", "", "format", null,
					null, versionAdmin);
			FileColum fileColum50 = new FileColum(reportField58, fileConfig,
					"type", new BigDecimal(50), "AIFMPrincipalInstrument5", "",
					"format", null, null, versionAdmin);
			FileColum fileColum51 = new FileColum(reportField59, fileConfig,
					"type", new BigDecimal(51), "SubAssetType5", "", "format",
					null, null, versionAdmin);
			FileColum fileColum52 = new FileColum(reportField60, fileConfig,
					"type", new BigDecimal(52),
					"InstrumentAggregatedValueAmount5", "", "format", null,
					null, versionAdmin);
			FileColum fileColum53 = new FileColum(reportField61, fileConfig,
					"type", new BigDecimal(53), "AUMAmountInEuro", "",
					"format", null, null, versionAdmin);
			FileColum fileColum54 = new FileColum(reportField62, fileConfig,
					"type", new BigDecimal(54), "BaseCurrency", "", "format",
					null, null, versionAdmin);
			FileColum fileColum55 = new FileColum(reportField63, fileConfig,
					"type", new BigDecimal(55), "AUMAmountInBaseCurrency", "",
					"format", null, null, versionAdmin);
			FileColum fileColum56 = new FileColum(reportField64, fileConfig,
					"type", new BigDecimal(56), "FXEURReferenceRateType", "",
					"format", null, null, versionAdmin);
			FileColum fileColum57 = new FileColum(reportField65, fileConfig,
					"type", new BigDecimal(57), "FXEURRate", "", "format",
					null, null, versionAdmin);
			FileColum fileColum58 = new FileColum(reportField66, fileConfig,
					"type", new BigDecimal(58), "CancelledAIFMNationalCode",
					"", "format", null, null, versionAdmin);
			FileColum fileColum59 = new FileColum(reportField67, fileConfig,
					"type", new BigDecimal(59), "CancelledReportingPeriodType",
					"", "format", null, null, versionAdmin);
			FileColum fileColum60 = new FileColum(reportField68, fileConfig,
					"type", new BigDecimal(60), "CancelledReportingPeriodYear",
					"", "format", null, null, versionAdmin);
			FileColum fileColum61 = new FileColum(reportField69, fileConfig,
					"type", new BigDecimal(61), "CancelledRecordFlag", "",
					"format", null, null, versionAdmin);

			
			ReportData reportData1 = new ReportData(null, reportField1,
					reportExecution, null, null, "GB", null, null, versionAdmin);
			ReportData reportData2 = new ReportData(null, reportField2,
					reportExecution, null, null, "1.2", null, null, versionAdmin);
			ReportData reportData3 = new ReportData(null, reportField3,
					reportExecution, null, null, "2014-09-01", null, null, versionAdmin);
			ReportData reportData4 = new ReportData(null, reportField4,
					reportExecution, null, null, "INIT", null, null, versionAdmin);
			ReportData reportData5 = new ReportData(null, reportField5,
					reportExecution, null, null, "2", null, null, versionAdmin);
			ReportData reportData6 = new ReportData(null, reportField6,
					reportExecution, null, null, "2014-09-01", null, null, versionAdmin);
			ReportData reportData7 = new ReportData(null, reportField7,
					reportExecution, null, null, "2014-12-31", null, null, versionAdmin);
			ReportData reportData8 = new ReportData(null, reportField8,
					reportExecution, null, null, "Q4", null, null, versionAdmin);
			ReportData reportData9 = new ReportData(null, reportField9,
					reportExecution, null, null, "2014", null, null, versionAdmin);
			ReportData reportData10 = new ReportData(null, reportField10,
					reportExecution, null, null, "QH", null, null, versionAdmin);
			ReportData reportData11 = new ReportData(null, reportField11,
					reportExecution, null, null, "Q4", null, null, versionAdmin);
			ReportData reportData12 = new ReportData(null, reportField12,
					reportExecution, null, null, "1", null, null, versionAdmin);
			ReportData reportData13 = new ReportData(null, reportField13,
					reportExecution, null, null, "false", null, null, versionAdmin);
			ReportData reportData14 = new ReportData(null, reportField14,
					reportExecution, null, null, "25", null, null, versionAdmin);
			ReportData reportData15 = new ReportData(null, reportField15,
					reportExecution, null, null, "Descripcion pregunta", null, null, versionAdmin);
			ReportData reportData16 = new ReportData(null, reportField16,
					reportExecution, null, null, "4", null, null, versionAdmin);
			ReportData reportData17 = new ReportData(null, reportField17,
					reportExecution, null, null, "GB", null, null, versionAdmin);
			ReportData reportData18 = new ReportData(null, reportField18,
					reportExecution, null, null, "474286", null, null, versionAdmin);
			ReportData reportData19 = new ReportData(null, reportField19,
					reportExecution, null, null, "AIFM 1", null, null, versionAdmin);
			ReportData reportData20 = new ReportData(null, reportField20,
					reportExecution, null, null, "true", null, null, versionAdmin);
			ReportData reportData21 = new ReportData(null, reportField21,
					reportExecution, null, null, "false", null, null, versionAdmin);
			ReportData reportData22 = new ReportData(null, reportField22,
					reportExecution, null, null, "969500AA77L4ZJXJ0T02", null, null, versionAdmin);
			ReportData reportData23 = new ReportData(null, reportField23,
					reportExecution, null, null, "TESTGB21XXX", null, null, versionAdmin);

			
			LoadFile loadFile = new LoadFile(department, fileConfig, date1, "Fichero1.txt", null, null, versionAdmin);
			
			LoadRaw loadRaw = new LoadRaw(loadFile, new BigDecimal(1), "SIMPLE", null, null, null, versionAdmin);
			
			LoadRawData loadRawData1 = new LoadRawData(fileColum1, loadRaw, "GESTORA1", "TEXT", versionAdmin);
			LoadRawData loadRawData2 = new LoadRawData(fileColum2, loadRaw, "01-10-2014", "DATE", versionAdmin);
			LoadRawData loadRawData3 = new LoadRawData(fileColum3, loadRaw, "GB", "TEXT", versionAdmin);
			LoadRawData loadRawData4 = new LoadRawData(fileColum4, loadRaw, "FIRST", "TEXT", versionAdmin);
			LoadRawData loadRawData5 = new LoadRawData(fileColum5, loadRaw, "2", "TEXT", versionAdmin);
			LoadRawData loadRawData6 = new LoadRawData(fileColum6, loadRaw, "01-10-2014", "DATE", versionAdmin);
			LoadRawData loadRawData7 = new LoadRawData(fileColum7, loadRaw, "31-12-2014", "DATE", versionAdmin);
			LoadRawData loadRawData8 = new LoadRawData(fileColum8, loadRaw, "Q4", "TEXT", versionAdmin);
			LoadRawData loadRawData9 = new LoadRawData(fileColum9, loadRaw, "2014", "TEXT", versionAdmin);
			LoadRawData loadRawData10 = new LoadRawData(fileColum10, loadRaw, "QH", "TEXT", versionAdmin);
			LoadRawData loadRawData11 = new LoadRawData(fileColum11, loadRaw, "Q4", "TEXT", versionAdmin);
			LoadRawData loadRawData12 = new LoadRawData(fileColum12, loadRaw, "false", "TEXT", versionAdmin);
			LoadRawData loadRawData13 = new LoadRawData(fileColum13, loadRaw, "4", "TEXT", versionAdmin);
			LoadRawData loadRawData14 = new LoadRawData(fileColum14, loadRaw, "GB", "TEXT", versionAdmin);
			LoadRawData loadRawData15 = new LoadRawData(fileColum15, loadRaw, "474286", "TEXT", versionAdmin);
			LoadRawData loadRawData16 = new LoadRawData(fileColum16, loadRaw, "AIFM 1", "TEXT", versionAdmin);
			LoadRawData loadRawData17 = new LoadRawData(fileColum17, loadRaw, "true", "TEXT", versionAdmin);
			LoadRawData loadRawData18 = new LoadRawData(fileColum18, loadRaw, "false", "TEXT", versionAdmin);
			LoadRawData loadRawData19 = new LoadRawData(fileColum19, loadRaw, "969500AA77L4ZJXJ0T02", "TEXT", versionAdmin);
			LoadRawData loadRawData20 = new LoadRawData(fileColum20, loadRaw, "TESTGB21XXX", "TEXT", versionAdmin);
			LoadRawData loadRawData21 = new LoadRawData(fileColum21, loadRaw, "1", "TEXT", versionAdmin);
			LoadRawData loadRawData22 = new LoadRawData(fileColum22, loadRaw, "XXX", "TEXT", versionAdmin);
			LoadRawData loadRawData23 = new LoadRawData(fileColum23, loadRaw, "", "TEXT", versionAdmin);
			
			
			// DAO 
			
			CompanyDAO companyDAO = (CompanyDAO) aplicationContext
					.getBean("companyDAO");
			companyDAO.create(company);

			DepartmentDAO departmentDAO = (DepartmentDAO) aplicationContext
					.getBean("departmentDAO");
			departmentDAO.create(department);

			FundDAO fundDAO = (FundDAO) aplicationContext.getBean("fundDAO");
			fundDAO.create(fund);

			FundGroupDAO fundGroupDAO = (FundGroupDAO) aplicationContext
					.getBean("fundGroupDAO");
			fundGroupDAO.create(fundGroup);

			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) aplicationContext
					.getBean("reportCatalogDAO");
			reportCatalogDAO.create(reportCatalog);

			ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) aplicationContext
					.getBean("reportExecutionDAO");
			reportExecutionDAO.create(reportExecution);

			ReportFieldDAO reportFieldDAO = (ReportFieldDAO) aplicationContext
					.getBean("reportFieldDAO");
			reportFieldDAO.create(reportField1);
			reportFieldDAO.create(reportField2);
			reportFieldDAO.create(reportField3);
			reportFieldDAO.create(reportField4);
			reportFieldDAO.create(reportField5);
			reportFieldDAO.create(reportField6);
			reportFieldDAO.create(reportField7);
			reportFieldDAO.create(reportField8);
			reportFieldDAO.create(reportField9);
			reportFieldDAO.create(reportField10);
			reportFieldDAO.create(reportField11);
			reportFieldDAO.create(reportField12);
			reportFieldDAO.create(reportField13);
			reportFieldDAO.create(reportField14);
			reportFieldDAO.create(reportField15);
			reportFieldDAO.create(reportField16);
			reportFieldDAO.create(reportField17);
			reportFieldDAO.create(reportField18);
			reportFieldDAO.create(reportField19);
			reportFieldDAO.create(reportField20);
			reportFieldDAO.create(reportField21);
			reportFieldDAO.create(reportField22);
			reportFieldDAO.create(reportField23);
			reportFieldDAO.create(reportField24);
			reportFieldDAO.create(reportField25);
			reportFieldDAO.create(reportField26);
			reportFieldDAO.create(reportField27);
			reportFieldDAO.create(reportField28);
			reportFieldDAO.create(reportField29);
			reportFieldDAO.create(reportField30);
			reportFieldDAO.create(reportField31);
			reportFieldDAO.create(reportField32);
			reportFieldDAO.create(reportField33);
			reportFieldDAO.create(reportField34);
			reportFieldDAO.create(reportField35);
			reportFieldDAO.create(reportField36);
			reportFieldDAO.create(reportField37);
			reportFieldDAO.create(reportField38);
			reportFieldDAO.create(reportField39);
			reportFieldDAO.create(reportField40);
			reportFieldDAO.create(reportField41);
			reportFieldDAO.create(reportField42);
			reportFieldDAO.create(reportField43);
			reportFieldDAO.create(reportField44);
			reportFieldDAO.create(reportField45);
			reportFieldDAO.create(reportField46);
			reportFieldDAO.create(reportField47);
			reportFieldDAO.create(reportField48);
			reportFieldDAO.create(reportField49);
			reportFieldDAO.create(reportField50);
			reportFieldDAO.create(reportField51);
			reportFieldDAO.create(reportField52);
			reportFieldDAO.create(reportField53);
			reportFieldDAO.create(reportField54);
			reportFieldDAO.create(reportField55);
			reportFieldDAO.create(reportField56);
			reportFieldDAO.create(reportField57);
			reportFieldDAO.create(reportField58);
			reportFieldDAO.create(reportField59);
			reportFieldDAO.create(reportField60);
			reportFieldDAO.create(reportField61);
			reportFieldDAO.create(reportField62);
			reportFieldDAO.create(reportField63);
			reportFieldDAO.create(reportField64);
			reportFieldDAO.create(reportField65);
			reportFieldDAO.create(reportField66);
			reportFieldDAO.create(reportField67);
			reportFieldDAO.create(reportField68);
			reportFieldDAO.create(reportField69);
			reportFieldDAO.create(reportField70);

			ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) aplicationContext
					.getBean("reportFieldListDAO");
			reportFieldListDAO.deleteAll();

			reportFieldListDAO = (ReportFieldListDAO) aplicationContext
					.getBean("reportFieldListDAO");
			reportFieldListDAO.create(reportFieldList1);
			reportFieldListDAO.create(reportFieldList2);
			reportFieldListDAO.create(reportFieldList3);
			reportFieldListDAO.create(reportFieldList4);
			reportFieldListDAO.create(reportFieldList5);
			reportFieldListDAO.create(reportFieldList6);
			reportFieldListDAO.create(reportFieldList7);
			reportFieldListDAO.create(reportFieldList8);
			reportFieldListDAO.create(reportFieldList9);
			reportFieldListDAO.create(reportFieldList10);
			reportFieldListDAO.create(reportFieldList11);
			reportFieldListDAO.create(reportFieldList12);
			reportFieldListDAO.create(reportFieldList13);
			reportFieldListDAO.create(reportFieldList14);
			reportFieldListDAO.create(reportFieldList15);
			reportFieldListDAO.create(reportFieldList16);
			reportFieldListDAO.create(reportFieldList17);
			reportFieldListDAO.create(reportFieldList18);
			reportFieldListDAO.create(reportFieldList19);
			reportFieldListDAO.create(reportFieldList20);
			reportFieldListDAO.create(reportFieldList21);
			reportFieldListDAO.create(reportFieldList22);
			reportFieldListDAO.create(reportFieldList23);
			reportFieldListDAO.create(reportFieldList24);
			reportFieldListDAO.create(reportFieldList25);
			reportFieldListDAO.create(reportFieldList26);
			reportFieldListDAO.create(reportFieldList27);
			reportFieldListDAO.create(reportFieldList28);
			reportFieldListDAO.create(reportFieldList29);

			FileConfigDAO fileConfigDAO = (FileConfigDAO) aplicationContext
					.getBean("fileConfigDAO");
			fileConfigDAO.create(fileConfig);

			FileColumDAO fileColumDAO = (FileColumDAO) aplicationContext
					.getBean("fileColumDAO");
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
			
			ReportDataDAO reportDataDAO = (ReportDataDAO) aplicationContext
					.getBean("reportDataDAO");
			reportDataDAO.create(reportData1);
			reportDataDAO.create(reportData2);
			reportDataDAO.create(reportData3);
			reportDataDAO.create(reportData4);
			reportDataDAO.create(reportData5);
			reportDataDAO.create(reportData6);
			reportDataDAO.create(reportData7);
			reportDataDAO.create(reportData8);
			reportDataDAO.create(reportData9);
			reportDataDAO.create(reportData10);
			reportDataDAO.create(reportData11);
			reportDataDAO.create(reportData12);
			reportDataDAO.create(reportData13);
			reportDataDAO.create(reportData14);
			reportDataDAO.create(reportData15);
			reportDataDAO.create(reportData16);
			reportDataDAO.create(reportData17);
			reportDataDAO.create(reportData18);
			reportDataDAO.create(reportData19);
			reportDataDAO.create(reportData20);
			reportDataDAO.create(reportData21);
			reportDataDAO.create(reportData22);
			reportDataDAO.create(reportData23);
			
			LoadFileDAO loadFileDAO = (LoadFileDAO) aplicationContext
					.getBean("loadFileDAO");
			loadFileDAO.create(loadFile);
			
			LoadRawDAO loadRawDAO = (LoadRawDAO) aplicationContext
					.getBean("loadRawDAO");
			loadRawDAO.create(loadRaw);
			
			LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO) aplicationContext
					.getBean("loadRawDataDAO");
			loadRawDataDAO.create(loadRawData1);
			loadRawDataDAO.create(loadRawData2);
			loadRawDataDAO.create(loadRawData3);
			loadRawDataDAO.create(loadRawData4);
			loadRawDataDAO.create(loadRawData5);
			loadRawDataDAO.create(loadRawData6);
			loadRawDataDAO.create(loadRawData7);
			loadRawDataDAO.create(loadRawData8);
			loadRawDataDAO.create(loadRawData9);
			loadRawDataDAO.create(loadRawData10);
			loadRawDataDAO.create(loadRawData11);
			loadRawDataDAO.create(loadRawData12);
			loadRawDataDAO.create(loadRawData13);
			loadRawDataDAO.create(loadRawData14);
			loadRawDataDAO.create(loadRawData15);
			loadRawDataDAO.create(loadRawData16);
			loadRawDataDAO.create(loadRawData17);
			loadRawDataDAO.create(loadRawData18);
			loadRawDataDAO.create(loadRawData19);
			loadRawDataDAO.create(loadRawData20);
			loadRawDataDAO.create(loadRawData21);
			loadRawDataDAO.create(loadRawData22);
			loadRawDataDAO.create(loadRawData23);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteEntities(ApplicationContext aplicationContext) {

		LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO) aplicationContext
				.getBean("loadRawDataDAO");
		loadRawDataDAO.deleteAll();
		
		LoadRawDAO loadRawDAO = (LoadRawDAO) aplicationContext
				.getBean("loadRawDAO");
		loadRawDAO.deleteAll();
		
		LoadFileDAO loadFileDAO = (LoadFileDAO) aplicationContext
				.getBean("loadFileDAO");
		loadFileDAO.deleteAll();
		
		ReportDataDAO reportDataDAO = (ReportDataDAO) aplicationContext
				.getBean("reportDataDAO");
		reportDataDAO.deleteAll();
		
		FileColumDAO fileColumDAO = (FileColumDAO) aplicationContext
				.getBean("fileColumDAO");
		fileColumDAO.deleteAll();

		FileConfigDAO fileConfigDAO = (FileConfigDAO) aplicationContext
				.getBean("fileConfigDAO");
		fileConfigDAO.deleteAll();

		ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) aplicationContext
				.getBean("reportFieldListDAO");
		reportFieldListDAO.deleteAll();

		ReportFieldDAO reportFieldDAO = (ReportFieldDAO) aplicationContext
				.getBean("reportFieldDAO");
		reportFieldDAO.deleteAll();

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) aplicationContext
				.getBean("reportExecutionDAO");
		reportExecutionDAO.deleteAll();

		ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) aplicationContext
				.getBean("reportCatalogDAO");
		reportCatalogDAO.deleteAll();

		FundGroupDAO fundGroupDAO = (FundGroupDAO) aplicationContext
				.getBean("fundGroupDAO");
		fundGroupDAO.deleteAll();

		FundDAO fundDAO = (FundDAO) aplicationContext.getBean("fundDAO");
		fundDAO.deleteAll();

		DepartmentDAO departmentDAO = (DepartmentDAO) aplicationContext
				.getBean("departmentDAO");
		departmentDAO.deleteAll();

		CompanyDAO companyDAO = (CompanyDAO) aplicationContext
				.getBean("companyDAO");
		companyDAO.deleteAll();

	}
}
