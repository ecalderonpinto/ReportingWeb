package com.entities.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.entities.entity.common.Error;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.FileColumList;
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

	public void installAIF(ApplicationContext applicationContext) {

		try {
			VersionAuditor versionAdmin = new VersionAuditor("admin");

			String versionField = "1.2";

			Company company = new Company("Bankia Valores", "Spain", "BNK", "",
					null, null, null, null, versionAdmin);
			Department department = new Department(company,
					"Values department", "VALUE", "", "Spain", null, null,
					null, new VersionAuditor("admin"));
			Fund fund = new Fund(company, "Bankia fund", "ES000002", "FUND2",
					"", null, null, null, versionAdmin);
			FundGroup fundGroup = new FundGroup(fund, department,
					"VALUE FUNDS", "", versionAdmin);

			ReportCatalog reportCatalog = new ReportCatalog(versionField,
					"FUND", "AIF 2014", "", null, null, null, versionAdmin);

			String str1 = "2014-01-01";
			String str2 = "2014-12-31";
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = format.parse(str1);
			Date date2 = format.parse(str2);

			ReportExecution reportExecution = new ReportExecution(
					reportCatalog, company, fund, "Q2", "2014", date2, date1,
					"CREATION", null, null, null, null, null, null, null, null,
					null, null, null, null, versionAdmin);

//			ReportField reportField1 = new ReportField(reportCatalog, "A",
//					"ReportingMemberState", ".{2}", "", "", "COUNTRY",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField2 = new ReportField(reportCatalog, "A",
//					"Version", "([0-9])+\\.([0-9])+", "", "", "VERSION",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField3 = new ReportField(reportCatalog, "D",
//					"CreationDateAndTime",
//					"[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}",
//					"", "", "DATETIME", versionField, null, null, null,
//					versionAdmin);
//			ReportField reportField4 = new ReportField(reportCatalog, "A",
//					"FilingType", ".{4}", "", "", "FILLING", versionField,
//					null, null, null, versionAdmin);
//			ReportField reportField5 = new ReportField(reportCatalog, "N",
//					"AIFContentType", "[0-9]{1}", "", "", "AIF_TYPE",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField6 = new ReportField(reportCatalog, "D",
//					"ReportingPeriodStartDate", "[0-9]{4}-[0-9]{2}-[0-9]{2}",
//					"", "", "DATE", versionField, null, null, null,
//					versionAdmin);
//			ReportField reportField7 = new ReportField(reportCatalog, "D",
//					"ReportingPeriodEndDate", "[0-9]{4}-[0-9]{2}-[0-9]{2}", "",
//					"", "DATE", versionField, null, null, null, versionAdmin);
//			ReportField reportField8 = new ReportField(reportCatalog, "A",
//					"ReportingPeriodType", ".{2}", "", "", "PERIOD",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField9 = new ReportField(reportCatalog, "N",
//					"ReportingPeriodYear", "[0-9]{4}", "", "", "YEAR",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField10 = new ReportField(reportCatalog, "A",
//					"AIFReportingObligationChangeFrequencyCode", ".{2}", "",
//					"", "AIFM_CHANGE_FREQ", versionField, null, null, null,
//					versionAdmin);
//			ReportField reportField11 = new ReportField(reportCatalog, "N",
//					"AIFMReportingObligationChangeContentsCode", "[0-9]{1}",
//					"", "", "AIFM_CHANGE_CONTENT", versionField, null, null,
//					null, versionAdmin);
//			ReportField reportField12 = new ReportField(reportCatalog, "A",
//					"AIFMReportingObligationChangeQuarter", ".{2}", "", "",
//					"QUARTER", versionField, null, null, null, versionAdmin);
//			ReportField reportField13 = new ReportField(reportCatalog, "B",
//					"LastReportingFlag", "true|false", "", "", "BOOLEAN",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField14 = new ReportField(reportCatalog, "N",
//					"QuestionNumber", "[0-9]{0,3}", "", "", "QUESTION",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField15 = new ReportField(reportCatalog, "Z",
//					"AssumptionDescription", ".{0,300}", "", "", "DESCRIPTION",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField16 = new ReportField(reportCatalog, "A",
//					"AIFMReportingCode", ".{1}", "", "", "REPORT_CODE",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField17 = new ReportField(reportCatalog, "A",
//					"AIFJurisdiction", ".{2}", "", "", "COUNTRY", versionField,
//					null, null, null, versionAdmin);
//			ReportField reportField18 = new ReportField(reportCatalog, "Z",
//					"AIFMNationalCode", ".{0,30}", "", "", "NATIONAL",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField19 = new ReportField(reportCatalog, "Z",
//					"AIFName", ".{0,300}", "", "", "AIFM_NAME", versionField,
//					null, null, null, versionAdmin);
//			ReportField reportField20 = new ReportField(reportCatalog, "B",
//					"AIFEEAFlag", "true|false", "", "", "BOOLEAN",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField21 = new ReportField(reportCatalog, "A",
//					"AIFDomicile", ".{2}", "", "", "COUNTRY", versionField,
//					null, null, null, versionAdmin);
//			ReportField reportField22 = new ReportField(reportCatalog, "D",
//					"InceptionDate", "[0-9]{4}-[0-9]{2}-[0-9]{2}", "", "",
//					"DATE", versionField, null, null, null, versionAdmin);
//			ReportField reportField23 = new ReportField(reportCatalog, "B",
//					"AIFNoReportingFlag", "true|false", "", "", "BOOLEAN",
//					versionField, null, null, null, versionAdmin);
//
//			ReportField reportField24 = new ReportField(reportCatalog, "A",
//					"AIFIdentifierLEI", ".{20}", "", "AIFIdentification",
//					"LEI", versionField, null, null, null, versionAdmin);
//			ReportField reportField25 = new ReportField(reportCatalog, "A",
//					"AIFIdentifierISIN", "[A-Z]{2}([A-Z]|[0-9]){9}[0-9]", "",
//					"", "ISIN", versionField, null, null, null, versionAdmin);
//			ReportField reportField26 = new ReportField(reportCatalog, "A",
//					"AIFIdentifierCUSIP", ".{9}", "", "AIFIdentification",
//					"CUSIP", versionField, null, null, null, versionAdmin);
//			ReportField reportField27 = new ReportField(reportCatalog, "A",
//					"AIFIdentifierSEDOL", ".{7}", "", "AIFIdentification",
//					"SEDOL", versionField, null, null, null, versionAdmin);
//			ReportField reportField28 = new ReportField(reportCatalog, "A",
//					"AIFIdentifierTicker", ".{20}", "", "AIFIdentification",
//					"TICKER", versionField, null, null, null, versionAdmin);
//			ReportField reportField29 = new ReportField(reportCatalog, "A",
//					"AIFIdentifierRIC", ".{20}", "", "AIFIdentification",
//					"RIC", versionField, null, null, null, versionAdmin);
//			ReportField reportField30 = new ReportField(reportCatalog, "A",
//					"AIFIdentifierECB", ".{20}", "", "AIFIdentification",
//					"ECB", versionField, null, null, null, versionAdmin);
//
//			ReportField reportField31 = new ReportField(reportCatalog, "A",
//					"ReportingMemberState1", ".{2}", "", "", "COUNTRY",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField32 = new ReportField(reportCatalog, "A",
//					"AIFNationalCode1", ".{30}", "", "", "NATIONAL",
//					versionField, null, null, null, versionAdmin);
//
//			ReportField reportField33 = new ReportField(reportCatalog, "B",
//					"ShareClassFlag", "true|false", "", "ShareClassIdentifier",
//					"BOOLEAN", versionField, null, null, null, versionAdmin);
//			ReportField reportField34 = new ReportField(reportCatalog, "Z",
//					"ShareClassNationalCode", ".{0,30}", "",
//					"ShareClassIdentifier", "NATIONAL", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField35 = new ReportField(reportCatalog, "A",
//					"ShareClassIdentifierISIN",
//					"[A-Z]{2}([A-Z]|[0-9]){9}[0-9]", "",
//					"ShareClassIdentifier", "ISIN", versionField, null, null,
//					null, versionAdmin);
//			ReportField reportField36 = new ReportField(reportCatalog, "A",
//					"ShareClassIdentifierSEDOL", ".{7}", "",
//					"ShareClassIdentifier", "SEDOL", versionField, null, null,
//					null, versionAdmin);
//			ReportField reportField37 = new ReportField(reportCatalog, "A",
//					"ShareClassIdentifierCUSIP", ".{9}", "",
//					"ShareClassIdentifier", "CUSIP", versionField, null, null,
//					null, versionAdmin);
//			ReportField reportField38 = new ReportField(reportCatalog, "A",
//					"ShareClassIdentifierTicker", ".{20}", "",
//					"ShareClassIdentifier", "TICKER", versionField, null, null,
//					null, versionAdmin);
//			ReportField reportField39 = new ReportField(reportCatalog, "A",
//					"ShareClassIdentifierRIC", ".{20}", "",
//					"ShareClassIdentifier", "RIC", versionField, null, null,
//					null, versionAdmin);
//			ReportField reportField40 = new ReportField(reportCatalog, "Z",
//					"ShareClassName", ".{0,300}", "", "ShareClassIdentifier",
//					"SHARECLASS", versionField, null, null, null, versionAdmin);
//
//			ReportField reportField41 = new ReportField(reportCatalog, "A",
//					"AIFMasterFeederStatus", ".{6}", "", "", "FEEDER",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField42 = new ReportField(reportCatalog, "Z",
//					"AIFName", ".{0,300}", "", "MasterAIFIdentification",
//					"AIFM_NAME", versionField, null, null, null, versionAdmin);
//			ReportField reportField43 = new ReportField(reportCatalog, "A",
//					"ReportingMemberState", ".{2}", "",
//					"MasterAIFIdentification", "COUNTRY", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField44 = new ReportField(reportCatalog, "Z",
//					"AIFMNationalCode", ".{0,30}", "",
//					"MasterAIFIdentification", "NATIONAL", versionField, null,
//					null, null, versionAdmin);
//
//			ReportField reportField45 = new ReportField(reportCatalog, "Z",
//					"EntityName", ".{0,300}", "", "PrimeBrokerIdentification",
//					"NAME", versionField, null, null, null, versionAdmin);
//			ReportField reportField46 = new ReportField(reportCatalog, "A",
//					"EntityIdentificationLEI", ".{20}", "",
//					"PrimeBrokerIdentification", "LEI", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField47 = new ReportField(reportCatalog, "A",
//					"EntityIdentificationBIC", ".{11}", "",
//					"PrimeBrokerIdentification", "BIC", versionField, null,
//					null, null, versionAdmin);
//
//			ReportField reportField48 = new ReportField(reportCatalog, "N",
//					"AUMAmountInBaseCurrency", "[0-9]{0,15}?", "",
//					"AIFBaseCurrencyDescription", "NUMBER", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField49 = new ReportField(reportCatalog, "A",
//					"BaseCurrency", ".{3}", "", "AIFBaseCurrencyDescription",
//					"CURRENCY", versionField, null, null, null, versionAdmin);
//			ReportField reportField50 = new ReportField(reportCatalog, "N",
//					"FXEURRate", "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
//					"AIFBaseCurrencyDescription", "DECIMAL", versionField,
//					null, null, null, versionAdmin);
//			ReportField reportField51 = new ReportField(reportCatalog, "A",
//					"FXEURReferenceRateType", ".{3}", "",
//					"AIFBaseCurrencyDescription", "FXRATE", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField52 = new ReportField(reportCatalog, "Z",
//					"FXEUROtherReferenceRateDescription", ".{0,300}", "",
//					"AIFBaseCurrencyDescription", "DESCRIPTION", versionField,
//					null, null, null, versionAdmin);
//
//			ReportField reportField53 = new ReportField(reportCatalog, "N",
//					"AIFNetAssetValue", "[+|-]?[0-9]{1,15}", "", "", "DECIMAL",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField54 = new ReportField(reportCatalog, "A",
//					"FirstFundingSourceCountry", ".{2}", "", "", "COUNTRY",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField55 = new ReportField(reportCatalog, "A",
//					"SecondFundingSourceCountry", ".{2}", "", "", "COUNTRY",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField56 = new ReportField(reportCatalog, "A",
//					"ThirdFundingSourceCountry", ".{2}", "", "", "COUNTRY",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField57 = new ReportField(reportCatalog, "A",
//					"PredominantAIFType", ".{4}", "", "", "AFITYPE",
//					versionField, null, null, null, versionAdmin);
//
//			ReportField reportField58 = new ReportField(reportCatalog, "A",
//					"HedgeFundStrategyType", ".{9}", "", "HedgeFundStrategy",
//					"HEDGE_FUND", versionField, null, null, null, versionAdmin);
//			ReportField reportField59 = new ReportField(reportCatalog, "A",
//					"PrimaryStrategyFlag", "true|false", "",
//					"HedgeFundStrategy", "BOOLEAN", versionField, null, null,
//					null, versionAdmin);
//			ReportField reportField60 = new ReportField(reportCatalog, "A",
//					"StrategyNAVRate", "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
//					"HedgeFundStrategy", "DECIMAL", versionField, null, null,
//					null, versionAdmin);
//			ReportField reportField61 = new ReportField(reportCatalog, "A",
//					"PrivateEquityFundStrategyType", ".{9}", "",
//					"PrivateEquityFundInvestmentStrategy", "PRIVATE_FUND",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField62 = new ReportField(reportCatalog, "A",
//					"PrimaryStrategyFlag", "true|false", "",
//					"PrivateEquityFundInvestmentStrategy", "BOOLEAN",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField63 = new ReportField(reportCatalog, "A",
//					"StrategyNAVRate", "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})", "",
//					"PrivateEquityFundInvestmentStrategy", "DECIMAL",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField64 = new ReportField(reportCatalog, "A",
//					"FundOfFundsStrategyType", ".{9}", "",
//					"FundOfFundsStrategy", "FUND_FUND", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField65 = new ReportField(reportCatalog, "A",
//					"PrimaryStrategyFlag", "true|false", "",
//					"FundOfFundsStrategy", "BOOLEAN", versionField, null, null,
//					null, versionAdmin);
//			ReportField reportField66 = new ReportField(reportCatalog, "A",
//					"StrategyNAVRate", "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
//					"FundOfFundsStrategy", "DECIMAL", versionField, null, null,
//					null, versionAdmin);
//			ReportField reportField67 = new ReportField(reportCatalog, "A",
//					"OtherFundStrategyType", ".{9}", "", "OtherFundStrategy",
//					"OTHER_FUND", versionField, null, null, null, versionAdmin);
//			ReportField reportField68 = new ReportField(reportCatalog, "A",
//					"PrimaryStrategyFlag", "true|false", "",
//					"OtherFundStrategy", "BOOLEAN", versionField, null, null,
//					null, versionAdmin);
//			ReportField reportField69 = new ReportField(reportCatalog, "A",
//					"StrategyNAVRate", "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
//					"OtherFundStrategy", "DECIMAL", versionField, null, null,
//					null, versionAdmin);
//			ReportField reportField70 = new ReportField(reportCatalog, "Z",
//					"StrategyTypeOtherDescription", ".{300}", "", "",
//					"DESCRIPTION", versionField, null, null, null, versionAdmin);
//			ReportField reportField71 = new ReportField(reportCatalog, "A",
//					"RealEstateFundStrategyType", ".{9}", "",
//					"RealEstateFundStrategy", "REAL_FUND", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField72 = new ReportField(reportCatalog, "A",
//					"PrimaryStrategyFlag", "true|false", "",
//					"RealEstateFundStrategy", "BOOLEAN", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField73 = new ReportField(reportCatalog, "A",
//					"StrategyNAVRate", "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
//					"RealEstateFundStrategy", "DECIMAL", versionField, null,
//					null, null, versionAdmin);
//
//			ReportField reportField74 = new ReportField(reportCatalog, "N",
//					"HFTTransactionNumber", "[0-9]{0,15}?", "", "", "NUMBER",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField75 = new ReportField(reportCatalog, "N",
//					"HFTBuySellMarketValue", "[0-9]{0,15}?", "", "", "NUMBER",
//					versionField, null, null, null, versionAdmin);
//
//			ReportField reportField76 = new ReportField(reportCatalog, "N",
//					"Ranking", "[0-9]{1}", "", "MainInstrumentTraded",
//					"RANKING5", versionField, null, null, null, versionAdmin);
//			ReportField reportField77 = new ReportField(reportCatalog, "Z",
//					"SubAssetType", ".{12}", "", "MainInstrumentTraded",
//					"SUB_ASSET", versionField, null, null, null, versionAdmin);
//			ReportField reportField78 = new ReportField(reportCatalog, "A",
//					"InstrumentCodeType", ".{4}", "", "MainInstrumentTraded",
//					"INSTR_TYPE", versionField, null, null, null, versionAdmin);
//			ReportField reportField79 = new ReportField(reportCatalog, "Z",
//					"InstrumentName", ".{300}", "", "MainInstrumentTraded",
//					"INSTR_NAME", versionField, null, null, null, versionAdmin);
//			ReportField reportField80 = new ReportField(reportCatalog, "A",
//					"ISINInstrumentIdentification",
//					"[A-Z]{2}([A-Z]|[0-9]){9}[0-9]", "",
//					"MainInstrumentTraded", "ISIN", versionField, null, null,
//					null, versionAdmin);
//			ReportField reportField81 = new ReportField(reportCatalog, "A",
//					"AIIExchangeCode", ".{4}", "",
//					"AIIInstrumentIdentification", "MIC", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField82 = new ReportField(reportCatalog, "A",
//					"AIIProductCode", ".{1,12}", "",
//					"AIIInstrumentIdentification", "AII_CODE", versionField,
//					null, null, null, versionAdmin);
//			ReportField reportField83 = new ReportField(reportCatalog, "A",
//					"AIIDerivativeType", ".{1}", "",
//					"AIIInstrumentIdentification", "AII_DERIV", versionField,
//					null, null, null, versionAdmin);
//			ReportField reportField84 = new ReportField(reportCatalog, "A",
//					"AIIPutCallIdentifier", ".{1}", "",
//					"AIIInstrumentIdentification", "AII_PUT", versionField,
//					null, null, null, versionAdmin);
//			ReportField reportField85 = new ReportField(reportCatalog, "D",
//					"AIIExpiryDate", "[0-9]{4}-[0-9]{2}-[0-9]{2}", "",
//					"AIIInstrumentIdentification", "DATE", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField86 = new ReportField(reportCatalog, "N",
//					"AIIStrikePrice", "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
//					"AIIInstrumentIdentification", "DECIMAL", versionField,
//					null, null, null, versionAdmin);
//			ReportField reportField87 = new ReportField(reportCatalog, "A",
//					"PositionType", ".{1}", "", "MainInstrumentTraded",
//					"POS_TYPE", versionField, null, null, null, versionAdmin);
//			ReportField reportField88 = new ReportField(reportCatalog, "N",
//					"PositionValue", "[0-9]{0,15}?", "",
//					"MainInstrumentTraded", "NUMBER", versionField, null, null,
//					null, versionAdmin);
//			ReportField reportField89 = new ReportField(reportCatalog, "A",
//					"ShortPositionHedgingRate", "[0-9]{1,15}(\\.[0-9]{1,4})?",
//					"", "MainInstrumentTraded", "DECIMAL", versionField, null,
//					null, null, versionAdmin);
//
//			ReportField reportField90 = new ReportField(reportCatalog, "N",
//					"AfricaNAVRate", "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
//					"NAVGeographicalFocus", "DECIMAL", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField91 = new ReportField(reportCatalog, "N",
//					"AsiaPacificNAVRate", "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
//					"", "NAVGeographicalFocus", "DECIMAL", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField92 = new ReportField(reportCatalog, "N",
//					"EuropeNAVRate", "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
//					"NAVGeographicalFocus", "DECIMAL", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField93 = new ReportField(reportCatalog, "N",
//					"EEANAVRate", "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
//					"NAVGeographicalFocus", "DECIMAL", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField94 = new ReportField(reportCatalog, "N",
//					"MiddleEastNAVRate", "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
//					"", "NAVGeographicalFocus", "DECIMAL", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField95 = new ReportField(reportCatalog, "N",
//					"NorthAmericaNAVRate", "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
//					"", "NAVGeographicalFocus", "DECIMAL", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField96 = new ReportField(reportCatalog, "N",
//					"SouthAmericaNAVRate", "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
//					"", "NAVGeographicalFocus", "DECIMAL", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField97 = new ReportField(reportCatalog, "N",
//					"SupraNationalNAVRate",
//					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
//					"NAVGeographicalFocus", "DECIMAL", versionField, null,
//					null, null, versionAdmin);
//
//			ReportField reportField98 = new ReportField(reportCatalog, "N",
//					"AfricaAUMRate", "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
//					"AUMGeographicalFocus", "PERCENT", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField99 = new ReportField(reportCatalog, "N",
//					"AsiaPacificAUMRate", "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
//					"AUMGeographicalFocus", "PERCENT", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField100 = new ReportField(reportCatalog, "N",
//					"EuropeAUMRate", "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
//					"AUMGeographicalFocus", "PERCENT", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField101 = new ReportField(reportCatalog, "N",
//					"EEAAUMRate", "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
//					"AUMGeographicalFocus", "PERCENT", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField102 = new ReportField(reportCatalog, "N",
//					"MiddleEastAUMRate", "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
//					"AUMGeographicalFocus", "PERCENT", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField103 = new ReportField(reportCatalog, "N",
//					"NorthAmericaAUMRate", "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
//					"AUMGeographicalFocus", "PERCENT", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField104 = new ReportField(reportCatalog, "N",
//					"SouthAmericaAUMRate", "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
//					"AUMGeographicalFocus", "PERCENT", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField105 = new ReportField(reportCatalog, "N",
//					"SupraNationalAUMRate", "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
//					"AUMGeographicalFocus", "PERCENT", versionField, null,
//					null, null, versionAdmin);
//
//			ReportField reportField106 = new ReportField(reportCatalog, "N",
//					"Ranking", "[0-9]{1,2}", "", "PrincipalExposure",
//					"RANKING10", versionField, null, null, null, versionAdmin);
//			ReportField reportField107 = new ReportField(reportCatalog, "A",
//					"AssetMacroType", ".{3}", "", "PrincipalExposure",
//					"ASSET_TYPE", versionField, null, null, null, versionAdmin);
//			ReportField reportField108 = new ReportField(reportCatalog, "Z",
//					"SubAssetType", ".{12}", "", "PrincipalExposure",
//					"SUB_ASSET", versionField, null, null, null, versionAdmin);
//			ReportField reportField109 = new ReportField(reportCatalog, "A",
//					"PositionType", ".{1}", "", "PrincipalExposure",
//					"POS_TYPE", versionField, null, null, null, versionAdmin);
//			ReportField reportField110 = new ReportField(reportCatalog, "N",
//					"AggregatedValueAmount", "[0-9]{1,15}?", "",
//					"PrincipalExposure", "NUMBER", versionField, null, null,
//					null, versionAdmin);
//			ReportField reportField111 = new ReportField(reportCatalog, "N",
//					"AggregatedValueRate", "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
//					"PrincipalExposure", "PERCENT", versionField, null, null,
//					null, versionAdmin);
//			ReportField reportField112 = new ReportField(reportCatalog, "Z",
//					"EntityName", ".{300}", "", "CounterpartyIdentification",
//					"NAME", versionField, null, null, null, versionAdmin);
//			ReportField reportField113 = new ReportField(reportCatalog, "A",
//					"EntityIdentificationLEI", ".{20}", "",
//					"CounterpartyIdentification", "LEI", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField114 = new ReportField(reportCatalog, "A",
//					"EntityIdentificationBIC", ".{11}", "",
//					"CounterpartyIdentification", "BIC", versionField, null,
//					null, null, versionAdmin);
//
//			ReportField reportField115 = new ReportField(reportCatalog, "N",
//					"Ranking", "[0-9]{1,2}", "", "PortfolioConcentration",
//					"RANKING5", versionField, null, null, null, versionAdmin);
//			ReportField reportField116 = new ReportField(reportCatalog, "A",
//					"AssetType", ".{3}", "", "PortfolioConcentration",
//					"ASSET_TYPE", versionField, null, null, null, versionAdmin);
//			ReportField reportField117 = new ReportField(reportCatalog, "A",
//					"PositionType", ".{1}", "", "PortfolioConcentration",
//					"POS_TYPE", versionField, null, null, null, versionAdmin);
//			ReportField reportField118 = new ReportField(reportCatalog, "A",
//					"MarketCodeType", ".{4}", "", "MarketIdentification",
//					"MARKET", versionField, null, null, null, versionAdmin);
//			ReportField reportField119 = new ReportField(reportCatalog, "A",
//					"MarketCode", ".{4}", "", "MarketIdentification", "MIC",
//					versionField, null, null, null, versionAdmin);
//			ReportField reportField120 = new ReportField(reportCatalog, "N",
//					"AggregatedValueAmount", "[0-9]{0,15}?", "",
//					"PortfolioConcentration", "NUMBER", versionField, null,
//					null, null, versionAdmin);
//			ReportField reportField121 = new ReportField(reportCatalog, "N",
//					"AggregatedValueRate", "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
//					"PortfolioConcentration", "PERCENT", versionField, null,
//					null, null, versionAdmin);

			// DAO

			CompanyDAO companyDAO = (CompanyDAO) applicationContext
					.getBean("companyDAO");
			companyDAO.create(company);

			DepartmentDAO departmentDAO = (DepartmentDAO) applicationContext
					.getBean("departmentDAO");
			departmentDAO.create(department);

			FundDAO fundDAO = (FundDAO) applicationContext.getBean("fundDAO");
			fundDAO.create(fund);

			FundGroupDAO fundGroupDAO = (FundGroupDAO) applicationContext
					.getBean("fundGroupDAO");
			fundGroupDAO.create(fundGroup);

			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			reportCatalogDAO.create(reportCatalog);

			ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
					.getBean("reportExecutionDAO");
			reportExecutionDAO.create(reportExecution);

			ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
					.getBean("reportFieldDAO");
//			reportFieldDAO.create(reportField1);
//			reportFieldDAO.create(reportField2);
//			reportFieldDAO.create(reportField3);
//			reportFieldDAO.create(reportField4);
//			reportFieldDAO.create(reportField5);
//			reportFieldDAO.create(reportField6);
//			reportFieldDAO.create(reportField7);
//			reportFieldDAO.create(reportField8);
//			reportFieldDAO.create(reportField9);
//			reportFieldDAO.create(reportField10);
//			reportFieldDAO.create(reportField11);
//			reportFieldDAO.create(reportField12);
//			reportFieldDAO.create(reportField13);
//			reportFieldDAO.create(reportField14);
//			reportFieldDAO.create(reportField15);
//			reportFieldDAO.create(reportField16);
//			reportFieldDAO.create(reportField17);
//			reportFieldDAO.create(reportField18);
//			reportFieldDAO.create(reportField19);
//			reportFieldDAO.create(reportField20);
//			reportFieldDAO.create(reportField21);
//			reportFieldDAO.create(reportField22);
//			reportFieldDAO.create(reportField23);
//			reportFieldDAO.create(reportField24);
//			reportFieldDAO.create(reportField25);
//			reportFieldDAO.create(reportField26);
//			reportFieldDAO.create(reportField27);
//			reportFieldDAO.create(reportField28);
//			reportFieldDAO.create(reportField29);
//			reportFieldDAO.create(reportField30);
//			reportFieldDAO.create(reportField31);
//			reportFieldDAO.create(reportField32);
//			reportFieldDAO.create(reportField33);
//			reportFieldDAO.create(reportField34);
//			reportFieldDAO.create(reportField35);
//			reportFieldDAO.create(reportField36);
//			reportFieldDAO.create(reportField37);
//			reportFieldDAO.create(reportField38);
//			reportFieldDAO.create(reportField39);
//			reportFieldDAO.create(reportField40);
//			reportFieldDAO.create(reportField41);
//			reportFieldDAO.create(reportField42);
//			reportFieldDAO.create(reportField43);
//			reportFieldDAO.create(reportField44);
//			reportFieldDAO.create(reportField45);
//			reportFieldDAO.create(reportField46);
//			reportFieldDAO.create(reportField47);
//			reportFieldDAO.create(reportField48);
//			reportFieldDAO.create(reportField49);
//			reportFieldDAO.create(reportField50);
//			reportFieldDAO.create(reportField51);
//			reportFieldDAO.create(reportField52);
//			reportFieldDAO.create(reportField53);
//			reportFieldDAO.create(reportField54);
//			reportFieldDAO.create(reportField55);
//			reportFieldDAO.create(reportField56);
//			reportFieldDAO.create(reportField57);
//			reportFieldDAO.create(reportField58);
//			reportFieldDAO.create(reportField59);
//			reportFieldDAO.create(reportField60);
//			reportFieldDAO.create(reportField61);
//			reportFieldDAO.create(reportField62);
//			reportFieldDAO.create(reportField63);
//			reportFieldDAO.create(reportField64);
//			reportFieldDAO.create(reportField65);
//			reportFieldDAO.create(reportField66);
//			reportFieldDAO.create(reportField67);
//			reportFieldDAO.create(reportField68);
//			reportFieldDAO.create(reportField69);
//			reportFieldDAO.create(reportField70);
//			reportFieldDAO.create(reportField71);
//			reportFieldDAO.create(reportField72);
//			reportFieldDAO.create(reportField73);
//			reportFieldDAO.create(reportField74);
//			reportFieldDAO.create(reportField75);
//			reportFieldDAO.create(reportField76);
//			reportFieldDAO.create(reportField77);
//			reportFieldDAO.create(reportField78);
//			reportFieldDAO.create(reportField79);
//			reportFieldDAO.create(reportField80);
//			reportFieldDAO.create(reportField81);
//			reportFieldDAO.create(reportField82);
//			reportFieldDAO.create(reportField83);
//			reportFieldDAO.create(reportField84);
//			reportFieldDAO.create(reportField85);
//			reportFieldDAO.create(reportField86);
//			reportFieldDAO.create(reportField87);
//			reportFieldDAO.create(reportField88);
//			reportFieldDAO.create(reportField89);
//			reportFieldDAO.create(reportField90);
//			reportFieldDAO.create(reportField91);
//			reportFieldDAO.create(reportField92);
//			reportFieldDAO.create(reportField93);
//			reportFieldDAO.create(reportField94);
//			reportFieldDAO.create(reportField95);
//			reportFieldDAO.create(reportField96);
//			reportFieldDAO.create(reportField97);
//			reportFieldDAO.create(reportField98);
//			reportFieldDAO.create(reportField99);
//			reportFieldDAO.create(reportField100);
//			reportFieldDAO.create(reportField110);
//			reportFieldDAO.create(reportField102);
//			reportFieldDAO.create(reportField103);
//			reportFieldDAO.create(reportField104);
//			reportFieldDAO.create(reportField105);
//			reportFieldDAO.create(reportField106);
//			reportFieldDAO.create(reportField107);
//			reportFieldDAO.create(reportField108);
//			reportFieldDAO.create(reportField109);
//			reportFieldDAO.create(reportField120);
//			reportFieldDAO.create(reportField121);
//			reportFieldDAO.create(reportField122);
//			reportFieldDAO.create(reportField123);
//			reportFieldDAO.create(reportField124);
//			reportFieldDAO.create(reportField125);
//			reportFieldDAO.create(reportField126);
//			reportFieldDAO.create(reportField127);
//			reportFieldDAO.create(reportField128);
//			reportFieldDAO.create(reportField129);
//			reportFieldDAO.create(reportField130);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void installEntitiesFull(ApplicationContext applicationContext) {

		try {

			VersionAuditor versionAdmin = new VersionAuditor("admin");

			String versionField = "1.2";

			Company company = new Company("Santander Asset Manager", "Spain",
					"SAM", "", null, null, null, null, versionAdmin);
			Department department = new Department(company, "Risk department",
					"RISK", "", "Spain", null, null, null, new VersionAuditor(
							"admin"));
			Fund fund = new Fund(company, "SAM fund 1", "ES000001", "FUND1",
					"", null, null, null, versionAdmin);
			FundGroup fundGroup = new FundGroup(fund, department, "RISK FUNDS",
					"", versionAdmin);

			ReportCatalog reportCatalog = new ReportCatalog(versionField,
					"COMPANY", "AIFMD 2014", "", null, null, null, versionAdmin);

			String str1 = "2014-01-01";
			String str2 = "2014-12-31";
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = format.parse(str1);
			Date date2 = format.parse(str2);

			ReportExecution reportExecution = new ReportExecution(
					reportCatalog, company, null, "Q1", "2014", date2, date1,
					"CREATION", null, null, null, null, null, null, null, null,
					null, null, null, null, versionAdmin);

			// report Fields de AIFM
			
			
			
			ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
					.getBean("reportFieldDAO");
			
			
			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			reportCatalogDAO.create(reportCatalog);
			
			
			ReportField reportFieldx1 = new ReportField(reportCatalog, null, "X",
					"AIFMReportingInfo", new BigInteger("0"), null, "", null, null, "1", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx1);
			ReportField reportField1 = new ReportField(reportCatalog, reportFieldx1, "A",
					"ReportingMemberState", new BigInteger("1"), ".{2}", "", "General Info",
					"COUNTRY", "1", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField1);
			ReportField reportField2 = new ReportField(reportCatalog, reportFieldx1, "A",
					"Version", new BigInteger("2"), "([0-9])+\\.([0-9])+", "", "General Info",
					"VERSION", "1", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField2);
			ReportField reportField3 = new ReportField(reportCatalog, reportFieldx1, "D",
					"CreationDateAndTime", new BigInteger("3"),
					"[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}",
					"", "General Info", "DATETIME", "1", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField3);
			
			ReportField reportFieldx2 = new ReportField(reportCatalog, reportFieldx1, "X",
					"AIFMRecordInfo", new BigInteger("0"), null, "", null, null,
					"1.1", "11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx2);
			ReportField reportField4 = new ReportField(reportCatalog, reportFieldx2, "A",
					"FilingType", new BigInteger("4"), ".{4}", "", "General Info", "FILLING",
					"1.1.01", "11", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField4);
			ReportField reportField5 = new ReportField(reportCatalog, reportFieldx2, "N",
					"AIFMContentType",  new BigInteger("5"), "[0-9]{1}", "", "General Info",
					"AIFM_TYPE", "1.1.02", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField5);
			ReportField reportField6 = new ReportField(reportCatalog, reportFieldx2,"D",
					"ReportingPeriodStartDate", new BigInteger("6"), "[0-9]{4}-[0-9]{2}-[0-9]{2}",
					"", "General Info", "DATE", "1.1.03", "11", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField6);
			ReportField reportField7 = new ReportField(reportCatalog, reportFieldx2, "D",
					"ReportingPeriodEndDate",  new BigInteger("7"), "[0-9]{4}-[0-9]{2}-[0-9]{2}", "",
					"General Info", "DATE", "1.1.04", "11", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField7);
			ReportField reportField8 = new ReportField(reportCatalog, reportFieldx2, "A",
					"ReportingPeriodType", new BigInteger("8"), ".{2}", "", "General Info",
					"PERIOD", "1.1.05", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField8);
			ReportField reportField9 = new ReportField(reportCatalog, reportFieldx2, "N",
					"ReportingPeriodYear", new BigInteger("9"), "[0-9]{4}", "", "General Info",
					"YEAR", "1.1.06", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField9);
			ReportField reportField10 = new ReportField(reportCatalog, reportFieldx2, "A",
					"AIFMReportingObligationChangeFrequencyCode", new BigInteger("10"), ".{2}", "",
					"General Info", "AIFM_CHANGE_FREQ", "1.1.07", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField10);
			ReportField reportField11 = new ReportField(reportCatalog, reportFieldx2, "N",
					"AIFMReportingObligationChangeContentsCode", new BigInteger("11"), "[0-9]{1}",
					"", "General Info", "AIFM_CHANGE_CONTENT", "1.1.08", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField11);
			ReportField reportField12 = new ReportField(reportCatalog, reportFieldx2, "A",
					"AIFMReportingObligationChangeQuarter", new BigInteger("12"), ".{2}", "",
					"General Info", "QUARTER", "1.1.09", "01", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField12);
			ReportField reportField13 = new ReportField(reportCatalog, reportFieldx2, "B",
					"LastReportingFlag", new BigInteger("13"), "true|false", "", "General Info",
					"BOOLEAN", "1.1.10", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField13);

			ReportField reportFieldx3 = new ReportField(reportCatalog, reportFieldx2, "X",
					"Assumptions", new BigInteger("0"), null, "", null, null, "1.1.11",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx3);
			ReportField reportFieldx4 = new ReportField(reportCatalog, reportFieldx3, "X",
					"Assumption", new BigInteger("0"), null, "", null, null, "1.1.11.1",
					"0n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx4);
			ReportField reportField14 = new ReportField(reportCatalog, reportFieldx4, "N",
					"QuestionNumber",new BigInteger("14"), "[0-9]{0,3}", "", "Assumptions",
					"QUESTION", "1.1.11.1.1", "0n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField14);
			ReportField reportField15 = new ReportField(reportCatalog, reportFieldx4, "Z",
					"AssumptionDescription", new BigInteger("15"), ".{0,300}", "", "Assumptions",
					"DESCRIPTION", "1.1.11.1.2", "0n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField15);

			ReportField reportField16 = new ReportField(reportCatalog, reportFieldx2, "A",
					"AIFMReportingCode", new BigInteger("16"), ".{1}", "", "General Info",
					"REPORT_CODE", "1.1.12", "11", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField16);
			ReportField reportField17 = new ReportField(reportCatalog, reportFieldx2, "A",
					"AIFMJurisdiction", new BigInteger("17"), ".{2}", "", "General Info",
					"COUNTRY", "1.1.13", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField17);
			ReportField reportField18 = new ReportField(reportCatalog, reportFieldx2, "Z",
					"AIFMNationalCode", new BigInteger("18"), ".{0,30}", "", "General Info",
					"NATIONAL", "1.1.14", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField18);
			ReportField reportField19 = new ReportField(reportCatalog, reportFieldx2, "Z",
					"AIFMName", new BigInteger("19"), ".{0,300}", "", "General Info", "AIFM_NAME",
					"1.1.15", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField19);
			ReportField reportField20 = new ReportField(reportCatalog, reportFieldx2, "B",
					"AIFMEEAFlag", new BigInteger("20"), "true|false", "", "General Info",
					"BOOLEAN", "1.1.16", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField20);
			ReportField reportField21 = new ReportField(reportCatalog, reportFieldx2, "B",
					"AIFMNoReportingFlag", new BigInteger("21"), "true|false", "", "General Info",
					"BOOLEAN", "1.1.17", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField21);

			ReportField reportFieldx5 = new ReportField(reportCatalog,  reportFieldx2,"X",
					"AIFMCompleteDescription", new BigInteger("13"), null, "", null,
					null, "1.1.18", "11", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportFieldx5);
			ReportField reportFieldx6 = new ReportField(reportCatalog, reportFieldx5, "X",
					"AIFMIdentifier", new BigInteger("0"), null, "", null,
					null, "1.1.18.1", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportFieldx6);
			ReportField reportField22 = new ReportField(reportCatalog, reportFieldx6, "A",
					"AIFMIdentifierLEI", new BigInteger("22"), ".{20}", "", "Complete Description", "LEI",
					"1.1.18.1.1", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField22);
			ReportField reportField23 = new ReportField(reportCatalog, reportFieldx6, "A",
					"AIFMIdentifierBIC", new BigInteger("23"), ".{11}", "", "Complete Description", "BIC",
					"1.1.18.1.2", "01", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField23);
			ReportField reportField24 = new ReportField(reportCatalog, reportFieldx6, "A",
					"ReportingMemberState", new BigInteger("24"), ".{2}", "",
					null, "COUNTRY", "1.1.18.1.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField24);
			ReportField reportField25 = new ReportField(reportCatalog, reportFieldx6, "Z",
					"AIFMNationalCode", new BigInteger("25"), ".{0,30}", "",
					null, "NATIONAL", "1.1.18.1.4", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField25);

			ReportField reportFieldx7 = new ReportField(reportCatalog, reportFieldx5, "X",
					"AIFMPrincipalMarkets", new BigInteger("0"), null, "",
					null, null, "1.1.18.2", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx7);
			ReportField reportFieldx8 = new ReportField(reportCatalog, reportFieldx7, "X",
					"AIFMFivePrincipalMarket", new BigInteger("0"), null, "",
					null, null, "1.1.18.2.1", "15",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx8);
			ReportField reportField26 = new ReportField(reportCatalog, reportFieldx8, "N",
					"Ranking", new BigInteger("26"), "[0-9]{1}", "", "Principal Markets",
					"RANKING5", "1.1.18.2.1.1", "15", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField26);
			ReportField reportFieldx9 = new ReportField(reportCatalog, reportFieldx8, "X",
					"MarketIdentification", new BigInteger("0"), null, "",
					null, null, "1.1.18.2.1.2", "15",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx9);
			ReportField reportField27 = new ReportField(reportCatalog, reportFieldx9, "A",
					"MarketCodeType", new BigInteger("27"), ".{4}", "", null,
					"MARKET", "1.1.18.2.1.2.1", "15", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField27);
			ReportField reportField28 = new ReportField(reportCatalog, reportFieldx9, "A",
					"MarketCode", new BigInteger("28"), ".{4}", "", "Principal Markets", "MIC",
					"1.1.18.2.1.2.2", "05", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField28);
			ReportField reportField29 = new ReportField(reportCatalog, reportFieldx8, "N",
					"AggregatedValueAmount", new BigInteger("29"), "[0-9]{0,15}?", "",
					"Principal Markets", "NUMBER", "1.1.18.2.1.3", "15",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField29);

			ReportField reportFieldx10 = new ReportField(reportCatalog, reportFieldx5, "X",
					"AIFMPrincipalInstruments", new BigInteger("0"), null, "",
					"AIFMCompleteDescription", null, "1.1.18.3", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx10);
			ReportField reportFieldx11 = new ReportField(reportCatalog, reportFieldx10, "X",
					"AIFMPrincipalInstrument", new BigInteger("0"), null, "",
					"AIFMPrincipalInstruments", null, "1.1.18.3.1", "15",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx11);
			ReportField reportField30 = new ReportField(reportCatalog, reportFieldx11, "N",
					"Ranking", new BigInteger("30"), "[0-9]{1}", "", "Principal Instruments",
					"RANKING5", "1.1.18.3.1.1", "15", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField30);
			ReportField reportField31 = new ReportField(reportCatalog, reportFieldx11, "A",
					"SubAssetType", new BigInteger("31"), ".{11}", "", "Principal Instruments",
					"SUB_ASSET", "1.1.18.3.1.2", "15", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField31);
			ReportField reportField32 = new ReportField(reportCatalog, reportFieldx11, "N",
					"AggregatedValueAmount", new BigInteger("32"), "[0-9]{0,15}?", "",
					"Principal Instruments", "NUMBER", "1.1.18.3.1.3", "15",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField32);

			ReportField reportField33 = new ReportField(reportCatalog, reportFieldx5, "N",
					"AUMAmountInEuro", new BigInteger("43"), "[0-9]{0,15}?", "",
					null, "NUMBER", "1.1.18.4", "11",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField33);
			ReportField reportField34 = new ReportField(reportCatalog, reportFieldx5, "N",
					"AUMAmountInBaseCurrency", new BigInteger("134"), "[0-9]{0,15}?", "",
					null, "NUMBER", "1.1.18.5", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField34);

			ReportField reportFieldx12 = new ReportField(reportCatalog, reportFieldx5, "X",
					"AIFMBaseCurrencyDescription", new BigInteger("0"), null, "",
					null, null, "1.1.18.6", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx12);
			ReportField reportField35 = new ReportField(reportCatalog, reportFieldx12, "A",
					"BaseCurrency", new BigInteger("35"), ".{3}", "", null,
					"CURRENCY", "1.1.18.6.1", "01", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField35);
			ReportField reportField36 = new ReportField(reportCatalog, reportFieldx12, "A",
					"FXEURReferenceRateType", new BigInteger("36"), ".{3}", "",
					null, "FXRATE", "1.1.18.6.2",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField36);
			ReportField reportField37 = new ReportField(reportCatalog, reportFieldx12, "N",
					"FXEURRate", new BigInteger("37"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					null, "DECIMAL", "1.1.18.6.3",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField37);
			ReportField reportField38 = new ReportField(reportCatalog, reportFieldx12, "Z",
					"FXEUROtherReferenceRateDescription", new BigInteger("38"), ".{0,300}", "",
					null, "DESCRIPTION", "1.1.18.6.4",
					"01", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField38);

			ReportField reportFieldx13 = new ReportField(reportCatalog, reportFieldx1, "X",
					"CancellationAIFMRecordInfo", new BigInteger("0"), null, "",
					null, null, "1.2", "01", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx13);
			ReportField reportField39 = new ReportField(reportCatalog, reportFieldx13, "Z",
					"CancelledAIFMNationalCode", new BigInteger("39"), ".{0,30}", "",
					"Cancellation Info", "NATIONAL", "1.2.1", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField39);
			ReportField reportField40 = new ReportField(reportCatalog, reportFieldx13, "A",
					"CancelledReportingPeriodType",new BigInteger("40"),  ".{2}", "",
					"Cancellation Info", "PERIOD", "1.2.2", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField40);
			ReportField reportField41 = new ReportField(reportCatalog, reportFieldx13, "D",
					"CancelledReportingPeriodYear", new BigInteger("41"), "[0-9]{4}?", "",
					"Cancellation Info", "YEAR", "1.2.3", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField41);
			ReportField reportField42 = new ReportField(reportCatalog, reportFieldx13, "A",
					"CancelledRecordFlag",new BigInteger("42"),  ".{1}", "",
					"Cancellation Info", "FLAG", "1.2.4", "01",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField42);

			// report Field List
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
			ReportFieldList reportFieldList22 = new ReportFieldList("RANKING5",
					"1", "2", versionAdmin);
			ReportFieldList reportFieldList23 = new ReportFieldList("RANKING5",
					"2", "2", versionAdmin);
			ReportFieldList reportFieldList24 = new ReportFieldList("RANKING5",
					"3", "2", versionAdmin);
			ReportFieldList reportFieldList25 = new ReportFieldList("RANKING5",
					"4", "2", versionAdmin);
			ReportFieldList reportFieldList26 = new ReportFieldList("RANKING5",
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

			FileColum fileColum0 = new FileColum(null, fileConfig, "type",
					new BigDecimal(0), "type", "", "format", null, null,
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
			FileColum fileColum22 = new FileColum(reportField26, fileConfig,
					"type", new BigDecimal(22), "AIFMFivePrincipalMarket2", "",
					"format", null, null, versionAdmin);
			FileColum fileColum23 = new FileColum(reportField27, fileConfig,
					"type", new BigDecimal(23), "MarketCodeType2", "",
					"format", null, null, versionAdmin);
			FileColum fileColum24 = new FileColum(reportField28, fileConfig,
					"type", new BigDecimal(24), "MarketCode2", "", "format",
					null, null, versionAdmin);
			FileColum fileColum25 = new FileColum(reportField29, fileConfig,
					"type", new BigDecimal(25), "MarketAggregatedValueAmount2",
					"", "format", null, null, versionAdmin);
			FileColum fileColum26 = new FileColum(reportField26, fileConfig,
					"type", new BigDecimal(26), "AIFMFivePrincipalMarket3", "",
					"format", null, null, versionAdmin);
			FileColum fileColum27 = new FileColum(reportField27, fileConfig,
					"type", new BigDecimal(27), "MarketCodeType3", "",
					"format", null, null, versionAdmin);
			FileColum fileColum28 = new FileColum(reportField28, fileConfig,
					"type", new BigDecimal(28), "MarketCode3", "", "format",
					null, null, versionAdmin);
			FileColum fileColum29 = new FileColum(reportField29, fileConfig,
					"type", new BigDecimal(29), "MarketAggregatedValueAmount3",
					"", "format", null, null, versionAdmin);
			FileColum fileColum30 = new FileColum(reportField26, fileConfig,
					"type", new BigDecimal(30), "AIFMFivePrincipalMarket4", "",
					"format", null, null, versionAdmin);
			FileColum fileColum31 = new FileColum(reportField27, fileConfig,
					"type", new BigDecimal(31), "MarketCodeType4", "",
					"format", null, null, versionAdmin);
			FileColum fileColum32 = new FileColum(reportField28, fileConfig,
					"type", new BigDecimal(32), "MarketCode4", "", "format",
					null, null, versionAdmin);
			FileColum fileColum33 = new FileColum(reportField29, fileConfig,
					"type", new BigDecimal(33), "MarketAggregatedValueAmount4",
					"", "format", null, null, versionAdmin);
			FileColum fileColum34 = new FileColum(reportField26, fileConfig,
					"type", new BigDecimal(34), "AIFMFivePrincipalMarket5", "",
					"format", null, null, versionAdmin);
			FileColum fileColum35 = new FileColum(reportField27, fileConfig,
					"type", new BigDecimal(35), "MarketCodeType5", "",
					"format", null, null, versionAdmin);
			FileColum fileColum36 = new FileColum(reportField28, fileConfig,
					"type", new BigDecimal(36), "MarketCode5", "", "format",
					null, null, versionAdmin);
			FileColum fileColum37 = new FileColum(reportField29, fileConfig,
					"type", new BigDecimal(37), "MarketAggregatedValueAmount5",
					"", "format", null, null, versionAdmin);
			FileColum fileColum38 = new FileColum(reportField30, fileConfig,
					"type", new BigDecimal(38), "AIFMPrincipalInstrument1", "",
					"format", null, null, versionAdmin);
			FileColum fileColum39 = new FileColum(reportField31, fileConfig,
					"type", new BigDecimal(39), "SubAssetType1", "", "format",
					null, null, versionAdmin);
			FileColum fileColum40 = new FileColum(reportField32, fileConfig,
					"type", new BigDecimal(40),
					"InstrumentAggregatedValueAmount1", "", "format", null,
					null, versionAdmin);
			FileColum fileColum41 = new FileColum(reportField30, fileConfig,
					"type", new BigDecimal(41), "AIFMPrincipalInstrument2", "",
					"format", null, null, versionAdmin);
			FileColum fileColum42 = new FileColum(reportField31, fileConfig,
					"type", new BigDecimal(42), "SubAssetType2", "", "format",
					null, null, versionAdmin);
			FileColum fileColum43 = new FileColum(reportField32, fileConfig,
					"type", new BigDecimal(43),
					"InstrumentAggregatedValueAmount2", "", "format", null,
					null, versionAdmin);
			FileColum fileColum44 = new FileColum(reportField30, fileConfig,
					"type", new BigDecimal(44), "AIFMPrincipalInstrument3", "",
					"format", null, null, versionAdmin);
			FileColum fileColum45 = new FileColum(reportField31, fileConfig,
					"type", new BigDecimal(45), "SubAssetType3", "", "format",
					null, null, versionAdmin);
			FileColum fileColum46 = new FileColum(reportField32, fileConfig,
					"type", new BigDecimal(46),
					"InstrumentAggregatedValueAmount3", "", "format", null,
					null, versionAdmin);
			FileColum fileColum47 = new FileColum(reportField30, fileConfig,
					"type", new BigDecimal(47), "AIFMPrincipalInstrument4", "",
					"format", null, null, versionAdmin);
			FileColum fileColum48 = new FileColum(reportField31, fileConfig,
					"type", new BigDecimal(48), "SubAssetType4", "", "format",
					null, null, versionAdmin);
			FileColum fileColum49 = new FileColum(reportField32, fileConfig,
					"type", new BigDecimal(49),
					"InstrumentAggregatedValueAmount4", "", "format", null,
					null, versionAdmin);
			FileColum fileColum50 = new FileColum(reportField30, fileConfig,
					"type", new BigDecimal(50), "AIFMPrincipalInstrument5", "",
					"format", null, null, versionAdmin);
			FileColum fileColum51 = new FileColum(reportField31, fileConfig,
					"type", new BigDecimal(51), "SubAssetType5", "", "format",
					null, null, versionAdmin);
			FileColum fileColum52 = new FileColum(reportField32, fileConfig,
					"type", new BigDecimal(52),
					"InstrumentAggregatedValueAmount5", "", "format", null,
					null, versionAdmin);
			FileColum fileColum53 = new FileColum(reportField33, fileConfig,
					"type", new BigDecimal(53), "AUMAmountInEuro", "",
					"format", null, null, versionAdmin);
			FileColum fileColum54 = new FileColum(reportField35, fileConfig,
					"type", new BigDecimal(54), "BaseCurrency", "", "format",
					null, null, versionAdmin);
			FileColum fileColum55 = new FileColum(reportField36, fileConfig,
					"type", new BigDecimal(55), "AUMAmountInBaseCurrency", "",
					"format", null, null, versionAdmin);
			FileColum fileColum56 = new FileColum(reportField37, fileConfig,
					"type", new BigDecimal(56), "FXEURReferenceRateType", "",
					"format", null, null, versionAdmin);
			FileColum fileColum57 = new FileColum(reportField38, fileConfig,
					"type", new BigDecimal(57), "FXEURRate", "", "format",
					null, null, versionAdmin);
			FileColum fileColum58 = new FileColum(reportField39, fileConfig,
					"type", new BigDecimal(58), "CancelledAIFMNationalCode",
					"", "format", null, null, versionAdmin);
			FileColum fileColum59 = new FileColum(reportField40, fileConfig,
					"type", new BigDecimal(59), "CancelledReportingPeriodType",
					"", "format", null, null, versionAdmin);
			FileColum fileColum60 = new FileColum(reportField41, fileConfig,
					"type", new BigDecimal(60), "CancelledReportingPeriodYear",
					"", "format", null, null, versionAdmin);
			FileColum fileColum61 = new FileColum(reportField42, fileConfig,
					"type", new BigDecimal(61), "CancelledRecordFlag", "",
					"format", null, null, versionAdmin);

			ReportData reportData1 = new ReportData(null, reportField1,
					reportExecution, null, null, "GB", null, null, null,
					versionAdmin);
			ReportData reportData2 = new ReportData(null, reportField2,
					reportExecution, null, null, "1.2", null, null, null,
					versionAdmin);
			ReportData reportData3 = new ReportData(null, reportField3,
					reportExecution, null, null, "2014-09-01 01:02:03", null, null,
					null, versionAdmin);
			ReportData reportData4 = new ReportData(null, reportField4,
					reportExecution, null, null, "INIT", null, null, null,
					versionAdmin);
			ReportData reportData5 = new ReportData(null, reportField5,
					reportExecution, null, null, "2", null, null, null,
					versionAdmin);
			ReportData reportData6 = new ReportData(null, reportField6,
					reportExecution, null, null, "2014-09-01", null, null,
					null, versionAdmin);
			ReportData reportData7 = new ReportData(null, reportField7,
					reportExecution, null, null, "2014-12-31", null, null,
					null, versionAdmin);
			ReportData reportData8 = new ReportData(null, reportField8,
					reportExecution, null, null, "Q4", null, null, null,
					versionAdmin);
			ReportData reportData9 = new ReportData(null, reportField9,
					reportExecution, null, null, "2014", null, null, null,
					versionAdmin);
			ReportData reportData10 = new ReportData(null, reportField10,
					reportExecution, null, null, "QH", null, null, null,
					versionAdmin);
			ReportData reportData11 = new ReportData(null, reportField11,
					reportExecution, null, null, "13", null, null, null,
					versionAdmin);
			ReportData reportData12 = new ReportData(null, reportField12,
					reportExecution, null, null, "Q1", null, null, null,
					versionAdmin);
			ReportData reportData13 = new ReportData(null, reportField13,
					reportExecution, null, null, "false", null, null, null,
					versionAdmin);
			ReportData reportData14 = new ReportData(null, reportField14,
					reportExecution, null, null, "25", "1", null, null,
					versionAdmin);
			ReportData reportData15 = new ReportData(null, reportField15,
					reportExecution, null, null, "Descripcion pregunta", "1",
					null, null, versionAdmin);
			ReportData reportData16 = new ReportData(null, reportField16,
					reportExecution, null, null, "4", null, null, null,
					versionAdmin);
			ReportData reportData17 = new ReportData(null, reportField17,
					reportExecution, null, null, "GB", null, null, null,
					versionAdmin);
			ReportData reportData18 = new ReportData(null, reportField18,
					reportExecution, null, null, "474286", null, null, null,
					versionAdmin);
			ReportData reportData19 = new ReportData(null, reportField19,
					reportExecution, null, null, "AIFM 1", null, null, null,
					versionAdmin);
			ReportData reportData20 = new ReportData(null, reportField20,
					reportExecution, null, null, "true", null, null, null,
					versionAdmin);
			ReportData reportData21 = new ReportData(null, reportField21,
					reportExecution, null, null, "false", null, null, null,
					versionAdmin);
			ReportData reportData22 = new ReportData(null, reportField22,
					reportExecution, null, null, "969500AA77L4ZJXJ0T02", null,
					null, null, versionAdmin);
			ReportData reportData23 = new ReportData(null, reportField23,
					reportExecution, null, null, "TESTGB21XXX", null, null,
					null, versionAdmin);
			ReportData reportData24 = new ReportData(null, reportField24,
					reportExecution, null, null, "GB", null, null, null,
					versionAdmin);
			ReportData reportData25 = new ReportData(null, reportField25,
					reportExecution, null, null, "UK", null, null, null,
					versionAdmin);

			ReportData reportData26 = new ReportData(null, reportField26,
					reportExecution, null, null, "1", "1", null, null,
					versionAdmin);
			ReportData reportData27 = new ReportData(null, reportField27,
					reportExecution, null, null, "XXX", "1", null, null,
					versionAdmin);
			ReportData reportData28 = new ReportData(null, reportField28,
					reportExecution, null, null, "", "1", null, null,
					versionAdmin);
			ReportData reportData29 = new ReportData(null, reportField29,
					reportExecution, null, null, "452000000", "1", null, null,
					versionAdmin);

			ReportData reportData30 = new ReportData(null, reportField26,
					reportExecution, null, null, "2", "2", null, null,
					versionAdmin);
			ReportData reportData31 = new ReportData(null, reportField27,
					reportExecution, null, null, "MIC", "2", null, null,
					versionAdmin);
			ReportData reportData32 = new ReportData(null, reportField28,
					reportExecution, null, null, "XEUR", "2", null, null,
					versionAdmin);
			ReportData reportData33 = new ReportData(null, reportField29,
					reportExecution, null, null, "42800000", "2", null, null,
					versionAdmin);
			
			ReportData reportData34 = new ReportData(null, reportField30,
					reportExecution, null, null, "1", "2", null, null,
					versionAdmin);
			ReportData reportData35 = new ReportData(null, reportField31,
					reportExecution, null, null, "DER_FID_FIXI", "2", null, null,
					versionAdmin);
			ReportData reportData36 = new ReportData(null, reportField32,
					reportExecution, null, null, "10010", "2", null, null,
					versionAdmin);
			
			ReportData reportData37 = new ReportData(null, reportField33,
					reportExecution, null, null, "5500000", "2", null, null,
					versionAdmin);
			ReportData reportData38 = new ReportData(null, reportField34,
					reportExecution, null, null, "51000", "2", null, null,
					versionAdmin);
			ReportData reportData39 = new ReportData(null, reportField35,
					reportExecution, null, null, "USD", "2", null, null,
					versionAdmin);
			ReportData reportData40 = new ReportData(null, reportField36,
					reportExecution, null, null, "7253950", "2", null, null,
					versionAdmin);
			ReportData reportData41 = new ReportData(null, reportField37,
					reportExecution, null, null, "ECB", "2", null, null,
					versionAdmin);
			ReportData reportData42 = new ReportData(null, reportField38,
					reportExecution, null, null, "12345678901234.7535", "2", null, null,
					versionAdmin);
			
			ReportData reportData43 = new ReportData(null, reportField39,
					reportExecution, null, null, "Id2", "2", null, null,
					versionAdmin);
			ReportData reportData44 = new ReportData(null, reportField40,
					reportExecution, null, null, "X1", "2", null, null,
					versionAdmin);
			ReportData reportData45 = new ReportData(null, reportField41,
					reportExecution, null, null, "2014", "2", null, null,
					versionAdmin);
			ReportData reportData46 = new ReportData(null, reportField42,
					reportExecution, null, null, "C", "2", null, null,
					versionAdmin);


			LoadFile loadFile = new LoadFile(department, fileConfig, date1,
					"Fichero1.txt", null, null, versionAdmin);

			LoadRaw loadRaw = new LoadRaw(loadFile, new BigDecimal(1),
					"SIMPLE", null, null, null, versionAdmin);

			LoadRawData loadRawData1 = new LoadRawData(fileColum1, loadRaw,
					"GESTORA1", "TEXT", versionAdmin);
			LoadRawData loadRawData2 = new LoadRawData(fileColum2, loadRaw,
					"01-10-2014", "DATE", versionAdmin);
			LoadRawData loadRawData3 = new LoadRawData(fileColum3, loadRaw,
					"GB", "TEXT", versionAdmin);
			LoadRawData loadRawData4 = new LoadRawData(fileColum4, loadRaw,
					"FIRST", "TEXT", versionAdmin);
			LoadRawData loadRawData5 = new LoadRawData(fileColum5, loadRaw,
					"2", "TEXT", versionAdmin);
			LoadRawData loadRawData6 = new LoadRawData(fileColum6, loadRaw,
					"01-10-2014", "DATE", versionAdmin);
			LoadRawData loadRawData7 = new LoadRawData(fileColum7, loadRaw,
					"31-12-2014", "DATE", versionAdmin);
			LoadRawData loadRawData8 = new LoadRawData(fileColum8, loadRaw,
					"Q4", "TEXT", versionAdmin);
			LoadRawData loadRawData9 = new LoadRawData(fileColum9, loadRaw,
					"2014", "TEXT", versionAdmin);
			LoadRawData loadRawData10 = new LoadRawData(fileColum10, loadRaw,
					"QH", "TEXT", versionAdmin);
			LoadRawData loadRawData11 = new LoadRawData(fileColum11, loadRaw,
					"Q4", "TEXT", versionAdmin);
			LoadRawData loadRawData12 = new LoadRawData(fileColum12, loadRaw,
					"false", "TEXT", versionAdmin);
			LoadRawData loadRawData13 = new LoadRawData(fileColum13, loadRaw,
					"4", "TEXT", versionAdmin);
			LoadRawData loadRawData14 = new LoadRawData(fileColum14, loadRaw,
					"GB", "TEXT", versionAdmin);
			LoadRawData loadRawData15 = new LoadRawData(fileColum15, loadRaw,
					"474286", "TEXT", versionAdmin);
			LoadRawData loadRawData16 = new LoadRawData(fileColum16, loadRaw,
					"AIFM 1", "TEXT", versionAdmin);
			LoadRawData loadRawData17 = new LoadRawData(fileColum17, loadRaw,
					"true", "TEXT", versionAdmin);
			LoadRawData loadRawData18 = new LoadRawData(fileColum18, loadRaw,
					"false", "TEXT", versionAdmin);
			LoadRawData loadRawData19 = new LoadRawData(fileColum19, loadRaw,
					"969500AA77L4ZJXJ0T02", "TEXT", versionAdmin);
			LoadRawData loadRawData20 = new LoadRawData(fileColum20, loadRaw,
					"TESTGB21XXX", "TEXT", versionAdmin);
			LoadRawData loadRawData21 = new LoadRawData(fileColum21, loadRaw,
					"1", "TEXT", versionAdmin);
			LoadRawData loadRawData22 = new LoadRawData(fileColum22, loadRaw,
					"XXX", "TEXT", versionAdmin);
			LoadRawData loadRawData23 = new LoadRawData(fileColum23, loadRaw,
					"", "TEXT", versionAdmin);

			Error error1 = new Error("LOADER", "Error load", "1",
					"Error in load file", "Reload file", null, null, null,
					versionAdmin);
			Error error2 = new Error("CONTROLLER", "Error controller", "1",
					"Error in servlet", "Reload page", null, null, null,
					versionAdmin);
			Error error3 = new Error("VALIDATOR", "Error validator", "1",
					"Error in validation", "Revise fields", null, null, null,
					versionAdmin);
			Error error4 = new Error("NORMALIZER", "Error normalizer", "1",
					"Error in normalize process", "Reload file", null, null,
					null, versionAdmin);
			Error error5 = new Error("CREATION", "Error load", "1",
					"Error in creation", "Revise conditions", null, null, null,
					versionAdmin);
			Error error6 = new Error("SYNTAXIS", "Error load", "1",
					"Error in syntaxis", "Revise format", null, null, null,
					versionAdmin);
			Error error7 = new Error("SEMANTIC", "Error sematinc", "1",
					"Error in semantic", "Revise rules", null, null, null,
					versionAdmin);
			Error error8 = new Error("TRANSLATE", "Error load", "1",
					"Error in load file", "Reload file", null, null, null,
					versionAdmin);
			Error error9 = new Error("STATUS", "Error status", "1",
					"Error in status checker", "Revise report", null, null,
					null, versionAdmin);
			Error error10 = new Error("REPORTING", "Error report", "1",
					"Error in report", "Revise report", null, null, null,
					versionAdmin);

			FileColumList fileColumList1 = new FileColumList(fileColum4,
					"TEXT", "FIRST", "INIT", versionAdmin);

			// DAO

			CompanyDAO companyDAO = (CompanyDAO) applicationContext
					.getBean("companyDAO");
			companyDAO.create(company);

			DepartmentDAO departmentDAO = (DepartmentDAO) applicationContext
					.getBean("departmentDAO");
			departmentDAO.create(department);

			FundDAO fundDAO = (FundDAO) applicationContext.getBean("fundDAO");
			fundDAO.create(fund);

			FundGroupDAO fundGroupDAO = (FundGroupDAO) applicationContext
					.getBean("fundGroupDAO");
			fundGroupDAO.create(fundGroup);

			ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
					.getBean("reportExecutionDAO");
			reportExecutionDAO.create(reportExecution);

			ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) applicationContext
					.getBean("reportFieldListDAO");
			reportFieldListDAO.deleteAll();

			reportFieldListDAO = (ReportFieldListDAO) applicationContext
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

			FileConfigDAO fileConfigDAO = (FileConfigDAO) applicationContext
					.getBean("fileConfigDAO");
			fileConfigDAO.create(fileConfig);

			FileColumDAO fileColumDAO = (FileColumDAO) applicationContext
					.getBean("fileColumDAO");
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

			ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
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
			reportDataDAO.create(reportData24);
			reportDataDAO.create(reportData25);
			reportDataDAO.create(reportData26);
			reportDataDAO.create(reportData27);
			reportDataDAO.create(reportData28);
			reportDataDAO.create(reportData29);
			reportDataDAO.create(reportData30);
			reportDataDAO.create(reportData31);
			reportDataDAO.create(reportData32);
			reportDataDAO.create(reportData33);
			reportDataDAO.create(reportData34);
			reportDataDAO.create(reportData35);
			reportDataDAO.create(reportData36);
			reportDataDAO.create(reportData37);
			reportDataDAO.create(reportData38);
			reportDataDAO.create(reportData39);
			reportDataDAO.create(reportData40);
			reportDataDAO.create(reportData41);
			reportDataDAO.create(reportData42);
			reportDataDAO.create(reportData43);
			reportDataDAO.create(reportData44);
			reportDataDAO.create(reportData45);
			reportDataDAO.create(reportData46);

			LoadFileDAO loadFileDAO = (LoadFileDAO) applicationContext
					.getBean("loadFileDAO");
			loadFileDAO.create(loadFile);

			LoadRawDAO loadRawDAO = (LoadRawDAO) applicationContext
					.getBean("loadRawDAO");
			loadRawDAO.create(loadRaw);

			LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO) applicationContext
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

			FileColumListDAO fileColumListDAO = (FileColumListDAO) applicationContext
					.getBean("fileColumListDAO");
			fileColumListDAO.create(fileColumList1);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteEntities(ApplicationContext applicationContext) {

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

		LoadFileDAO loadFileDAO = (LoadFileDAO) applicationContext
				.getBean("loadFileDAO");
		loadFileDAO.deleteAll();

		ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
				.getBean("reportDataDAO");
		reportDataDAO.deleteAll();

		FileColumDAO fileColumDAO = (FileColumDAO) applicationContext
				.getBean("fileColumDAO");
		fileColumDAO.deleteAll();

		FileConfigDAO fileConfigDAO = (FileConfigDAO) applicationContext
				.getBean("fileConfigDAO");
		fileConfigDAO.deleteAll();

		ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) applicationContext
				.getBean("reportFieldListDAO");
		reportFieldListDAO.deleteAll();

		ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
				.getBean("reportFieldDAO");
		reportFieldDAO.deleteAll();

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");
		reportExecutionDAO.deleteAll();

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
