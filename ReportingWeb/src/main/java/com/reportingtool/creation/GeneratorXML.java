package com.reportingtool.creation;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.springframework.context.ApplicationContext;

import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.reportingtool.utilities.ReportingErrorManager;
import com.reportingtool.utilities.XMLGregorianCalendarConverter;
import com.reportingtool.xml.AIFMReportingInfo;
import com.reportingtool.xml.AIFReportingInfo;
import com.reportingtool.xml.CancelledRecordFlagType;
import com.reportingtool.xml.ComplexAIFCompleteDescriptionType;
import com.reportingtool.xml.ComplexAIFDescriptionType;
import com.reportingtool.xml.ComplexAIFIdentifierType;
import com.reportingtool.xml.ComplexAIFIndividualInfoType;
import com.reportingtool.xml.ComplexAIFLeverageArticle242Type;
import com.reportingtool.xml.ComplexAIFLeverageArticle244Type;
import com.reportingtool.xml.ComplexAIFLeverageInfoType;
import com.reportingtool.xml.ComplexAIFMCompleteDescriptionType;
import com.reportingtool.xml.ComplexAIFMIdentifierType;
import com.reportingtool.xml.ComplexAIFMNationalIdentifierType;
import com.reportingtool.xml.ComplexAIFMPrincipalInstrumentsType;
import com.reportingtool.xml.ComplexAIFMPrincipalMarketsType;
import com.reportingtool.xml.ComplexAIFMRecordInfoType;
import com.reportingtool.xml.ComplexAIFPrincipalInfoType;
import com.reportingtool.xml.ComplexAIFPrincipalMarketsType;
import com.reportingtool.xml.ComplexAIFRecordInfoType;
import com.reportingtool.xml.ComplexAUMGeographicalFocusType;
import com.reportingtool.xml.ComplexAssumptionType;
import com.reportingtool.xml.ComplexAssumptionsType;
import com.reportingtool.xml.ComplexBaseCurrencyDescriptionType;
import com.reportingtool.xml.ComplexCancellationAIFMRecordInfoType;
import com.reportingtool.xml.ComplexCancellationAIFRecordInfoType;
import com.reportingtool.xml.ComplexFivePrincipalMarketType;
import com.reportingtool.xml.ComplexIndividualExposureType;
import com.reportingtool.xml.ComplexInvestorConcentrationType;
import com.reportingtool.xml.ComplexMainInstrumentTradedType;
import com.reportingtool.xml.ComplexMainInstrumentsTradedType;
import com.reportingtool.xml.ComplexMarketIdentificationWithNOTType;
import com.reportingtool.xml.ComplexMostImportantConcentrationType;
import com.reportingtool.xml.ComplexNAVGeographicalFocusType;
import com.reportingtool.xml.ComplexPortfolioConcentrationsType;
import com.reportingtool.xml.ComplexPrincipalExposuresType;
import com.reportingtool.xml.ComplexPrincipalInstrumentType;
import com.reportingtool.xml.ComplexRiskProfileType;
import com.reportingtool.xml.ComplexShareClassIdentificationType;
import com.reportingtool.xml.ComplexStressTestsType;
import com.reportingtool.xml.FXEURReferenceRateTypeType;
import com.reportingtool.xml.FilingTypeType;
import com.reportingtool.xml.MarketCodeTypeWithNOTType;
import com.reportingtool.xml.ObjectFactoryAIF;
import com.reportingtool.xml.ObjectFactoryAIFM;
import com.reportingtool.xml.ReportingObligationChangeFrequencyCodeType;
import com.reportingtool.xml.ReportingObligationChangeQuarterType;
import com.reportingtool.xml.ReportingPeriodTypeType;
import com.reportingtool.xml.SubAssetTypeType;
import com.reportingtool.xml.TypicalPositionSizeType;

public class GeneratorXML {

	private ApplicationContext applicationContext;

	public static final String stringDateFormat = "yyyy-MM-dd";
	public static final String stringYearFormat = "yyyy";
	public static final String stringDateTimeFormat = "yyyy-MM-dd hh:mm:ss";

	public GeneratorXML(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public String generateXML(ReportExecution reportExecution) {
		ReportCatalog reportCatalog = reportExecution.getReportCatalog();

		System.out.println("DEBUG_" + "GeneratorXML: starting XML with report "
				+ reportExecution.getReportCatalog().getReportLevel() + " "
				+ reportExecution.getReportPeriodType() + " "
				+ reportExecution.getReportPeriodYear() + " "
				+ reportExecution.getReportCatalog().getReportCatalogName());

		if (reportCatalog.getReportLevel().contains("FUND")) {

			// generate AIF report
			return generateXMLAIF(reportExecution);

		}

		if (reportCatalog.getReportLevel().contains("COMPANY")) {

			// generate AIFMD report
			return generateXMLAIFMD(reportExecution);

		}

		return null;
	}

	public String generateXMLAIF(ReportExecution reportExecution) {

		System.out.println("DEBUG_" + "GeneratorXML: starting XML generation ");

		// all dataFields
		List<ReportData> reportDatas = new ArrayList<ReportData>(
				reportExecution.getReportDatas());

		// show all reportData to make sure the content
		Map<String, String> reportMap = new HashMap<String, String>();
		for (ReportData reportData : reportDatas) {
			// this hashmap contain the content and <name> of every field
			reportMap.put(reportData.getReportField().getReportFieldName(),
					reportData.getReportDataText());
		}
		System.out.println(reportMap.toString());

		// ///////////////////////////////////////////////////////////
		// ONLY STATUS = PENDING WILL CREATE XML REPORTS
		// if (aifmdReportResult.getAifmdReportResultStat().equals("PENDING")) {
		// }

		try {
			DateFormat formatDate = new SimpleDateFormat(stringDateFormat);
			DateFormat formatYear = new SimpleDateFormat(stringYearFormat);
			DateFormat formatDateTime = new SimpleDateFormat(
					stringDateTimeFormat);

			// ObjectFactory to make every class to AIFM report
			ObjectFactoryAIF objectFactoryAIF = new ObjectFactoryAIF();

			AIFReportingInfo aifReportingInfo = objectFactoryAIF
					.createAIFReportingInfo();

			ComplexCancellationAIFRecordInfoType complexCancellationAIFRecordInfoType = objectFactoryAIF
					.createComplexCancellationAIFRecordInfoType();

			ComplexAIFRecordInfoType complexAIFRecordInfoType = objectFactoryAIF
					.createComplexAIFRecordInfoType();

			ComplexAIFCompleteDescriptionType complexAIFCompleteDescriptionType = objectFactoryAIF
					.createComplexAIFCompleteDescriptionType();

			ComplexAIFIndividualInfoType complexAIFIndividualInfoType = objectFactoryAIF
					.createComplexAIFIndividualInfoType();
			ComplexAIFLeverageInfoType complexAIFLeverageInfoType = objectFactoryAIF
					.createComplexAIFLeverageInfoType();
			ComplexAIFPrincipalInfoType complexAIFPrincipalInfoType = objectFactoryAIF
					.createComplexAIFPrincipalInfoType();

			ComplexAssumptionsType complexAssumptionsType = objectFactoryAIF
					.createComplexAssumptionsType();

			ComplexIndividualExposureType complexIndividualExposureType = objectFactoryAIF
					.createComplexIndividualExposureType();

			ComplexRiskProfileType complexRiskProfileType = objectFactoryAIF
					.createComplexRiskProfileType();

			ComplexStressTestsType complexStressTestsType = objectFactoryAIF
					.createComplexStressTestsType();

			ComplexAIFLeverageArticle242Type complexAIFLeverageArticle242Type = objectFactoryAIF
					.createComplexAIFLeverageArticle242Type();

			ComplexAIFLeverageArticle244Type complexAIFLeverageArticle244Type = objectFactoryAIF
					.createComplexAIFLeverageArticle244Type();

			ComplexAIFDescriptionType complexAIFDescriptionType = objectFactoryAIF
					.createComplexAIFDescriptionType();

			ComplexAIFIdentifierType complexAIFIdentifierType = objectFactoryAIF
					.createComplexAIFIdentifierType();

			ComplexAUMGeographicalFocusType complexAUMGeographicalFocusType = objectFactoryAIF
					.createComplexAUMGeographicalFocusType();

			ComplexMainInstrumentsTradedType complexMainInstrumentsTradedType = objectFactoryAIF
					.createComplexMainInstrumentsTradedType();

			ComplexMostImportantConcentrationType complexMostImportantConcentrationType = objectFactoryAIF
					.createComplexMostImportantConcentrationType();

			ComplexNAVGeographicalFocusType complexNAVGeographicalFocusType = objectFactoryAIF
					.createComplexNAVGeographicalFocusType();

			ComplexPrincipalExposuresType complexPrincipalExposuresType = objectFactoryAIF
					.createComplexPrincipalExposuresType();

			ComplexShareClassIdentificationType complexShareClassIdentificationType = objectFactoryAIF
					.createComplexShareClassIdentificationType();

			// /////////////////////////////////////////////////////////////////
			// <AIFPrincipalInfo><IndividualExposure>

			complexIndividualExposureType
					.setAssetTypeExposures(complexAssetTypeExposuresType);
			complexIndividualExposureType
					.setAssetTypeTurnovers(complexAssetTypeTurnoversType);
			complexIndividualExposureType
					.setCompaniesDominantInfluence(complexCompaniesDominantInfluenceType);
			complexIndividualExposureType
					.setCurrencyExposures(complexCurrencyExposuresType);

			// /////////////////////////////////////////////////////////////////
			// <AIFPrincipalInfo><RiskProfile>
			
			complexRiskProfileType
					.setCounterpartyRiskProfile(complexCounterpartyRiskProfileType);
			complexRiskProfileType
					.setLiquidityRiskProfile(complexLiquidityRiskProfileType);
			complexRiskProfileType
					.setMarketRiskProfile(complexMarketRiskProfileType);
			complexRiskProfileType
					.setOperationalRisk(complexOperationalRiskType);

			// /////////////////////////////////////////////////////////////////
			// <AIFPrincipalInfo><StressTests>
			complexStressTestsType.setStressTestsResultArticle15("");
			complexStressTestsType.setStressTestsResultArticle16("");

			// /////////////////////////////////////////////////////////////////
			// <AIFLeverageInfo><AIFLeverageArticle24-2>
			complexAIFLeverageArticle242Type
					.setAllCounterpartyCollateralRehypothecatedRate(new BigDecimal(
							"0"));
			complexAIFLeverageArticle242Type
					.setAllCounterpartyCollateralRehypothecationFlag(true);
			complexAIFLeverageArticle242Type
					.setControlledStructures(complexControlledStructuresType);
			complexAIFLeverageArticle242Type
					.setFinancialInstrumentBorrowing(complexFinancialInstrumentBorrowingType);
			complexAIFLeverageArticle242Type
					.setLeverageAIF(complexLeverateAIFType);
			complexAIFLeverageArticle242Type
					.setSecuritiesCashBorrowing(complexSecuritiesCashBorrowingType);
			complexAIFLeverageArticle242Type
					.setShortPositionBorrowedSecuritiesValue(new BigDecimal("0"));

			// /////////////////////////////////////////////////////////////////
			// <AIFLeverageInfo><AIFLeverageArticle24-4>
			complexAIFLeverageArticle244Type.getBorrowingSource();

			// /////////////////////////////////////////////////////////////////
			// <AIFPrincipalInfo><AIFDescription>
			complexAIFDescriptionType
					.setAIFBaseCurrencyDescription(complexBaseCurrencyDescriptionType);
			complexAIFDescriptionType
					.setAIFMasterFeederStatus(aifMasterFeederStatusType);
			complexAIFDescriptionType.setAIFNetAssetValue(0);
			complexAIFDescriptionType.setFirstFundingSourceCountry("");
			complexAIFDescriptionType
					.setFundOfFundsInvestmentStrategies(complexFundOfFundsInvestmentStrategiesType);
			complexAIFDescriptionType
					.setHedgeFundInvestmentStrategies(complexHedgeFundInvestmentStrategiesType);
			complexAIFDescriptionType.setHFTBuySellMarketValue(new BigDecimal(
					"0"));
			complexAIFDescriptionType.setHFTTransactionNumber(new BigDecimal(
					"0"));
			complexAIFDescriptionType
					.setMasterAIFsIdentification(complexMasterAIFsIdentificationType);
			complexAIFDescriptionType
					.setOtherFundInvestmentStrategies(complexOtherFundInvestmentStrategiesType);
			complexAIFDescriptionType.setPredominantAIFType(aifTypeType);
			complexAIFDescriptionType.setPrimeBrokers(complexPrimeBrokersType);
			complexAIFDescriptionType
					.setPrivateEquityFundInvestmentStrategies(complexPrivateEquityFundInvestmentStrategiesType);
			complexAIFDescriptionType
					.setRealEstateFundInvestmentStrategies(complexRealEstateFundInvestmentStrategiesType);
			complexAIFDescriptionType.setSecondFundingSourceCountry("");
			complexAIFDescriptionType.setThirdFundingSourceCountry("");

			// /////////////////////////////////////////////////////////////////
			// <AIFPrincipalInfo><AIFIdentifier>
			complexAIFIdentifierType.setAIFIdentifierCUSIP("");
			complexAIFIdentifierType.setAIFIdentifierECB("");
			complexAIFIdentifierType.setAIFIdentifierISIN("");
			complexAIFIdentifierType.setAIFIdentifierLEI("");
			complexAIFIdentifierType.setAIFIdentifierRIC("");
			complexAIFIdentifierType.setAIFIdentifierSEDOL("");
			complexAIFIdentifierType.setAIFIdentifierTicker("");
			complexAIFIdentifierType
					.setOldAIFIdentifierNCA(complexAIFNationalIdentifierType);

			// /////////////////////////////////////////////////////////////////
			// <AIFPrincipalInfo><AUMGeographicalFocus>

			// <AUMGeographicalFocus><AfricaAUMRate>
			BigDecimal africaAUMRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AfricaAUMRate"))
					africaAUMRate = new BigDecimal(
							reportData.getReportDataText());
			}
			complexAUMGeographicalFocusType.setAfricaAUMRate(africaAUMRate);

			// <AUMGeographicalFocus><AsiaPacificAUMRate>
			BigDecimal asiaPacificAUMRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AsiaPacificAUMRate"))
					asiaPacificAUMRate = new BigDecimal(
							reportData.getReportDataText());
			}
			complexAUMGeographicalFocusType
					.setAsiaPacificAUMRate(asiaPacificAUMRate);

			// <AUMGeographicalFocus><EEAAUMRate>
			BigDecimal eEAAUMRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("EEAAUMRate"))
					eEAAUMRate = new BigDecimal(reportData.getReportDataText());
			}
			complexAUMGeographicalFocusType.setEEAAUMRate(eEAAUMRate);

			// <AUMGeographicalFocus><EuropeAUMRate>
			BigDecimal europeAUMRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("EuropeAUMRate"))
					europeAUMRate = new BigDecimal(
							reportData.getReportDataText());
			}
			complexAUMGeographicalFocusType.setEuropeAUMRate(europeAUMRate);

			// <AUMGeographicalFocus><MiddleEastAUMRate>
			BigDecimal middleEastAUMRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("MiddleEastAUMRate"))
					middleEastAUMRate = new BigDecimal(
							reportData.getReportDataText());
			}
			complexAUMGeographicalFocusType
					.setMiddleEastAUMRate(middleEastAUMRate);

			// <AUMGeographicalFocus><NorthAmericaAUMRate>
			BigDecimal northAmericaAUMRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("NorthAmericaAUMRate"))
					northAmericaAUMRate = new BigDecimal(
							reportData.getReportDataText());
			}
			complexAUMGeographicalFocusType
					.setNorthAmericaAUMRate(northAmericaAUMRate);

			// <AUMGeographicalFocus><SouthAmericaAUMRate>
			BigDecimal southAmericaAUMRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("SouthAmericaAUMRate"))
					southAmericaAUMRate = new BigDecimal(
							reportData.getReportDataText());
			}
			complexAUMGeographicalFocusType
					.setSouthAmericaAUMRate(southAmericaAUMRate);

			// <AUMGeographicalFocus><SupraNationalAUMRate>
			BigDecimal supraNationalAUMRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("SupraNationalAUMRate"))
					supraNationalAUMRate = new BigDecimal(
							reportData.getReportDataText());
			}
			complexAUMGeographicalFocusType
					.setSupraNationalAUMRate(supraNationalAUMRate);

			// /////////////////////////////////////////////////////////////////
			// <AIFPrincipalInfo><MainInstrumentsTraded>
			List<ComplexMainInstrumentTradedType> complexMainInstrumentTradedTypeList = complexMainInstrumentsTradedType
					.getMainInstrumentTraded();

			// falta rellenarlos
			
			// /////////////////////////////////////////////////////////////////
			// <AIFPrincipalInfo><MostImportantConcentration>

			ComplexAIFPrincipalMarketsType complexAIFPrincipalMarketsType = objectFactoryAIF
					.createComplexAIFPrincipalMarketsType();

			ComplexInvestorConcentrationType complexInvestorConcentrationType = objectFactoryAIF
					.createComplexInvestorConcentrationType();

			ComplexPortfolioConcentrationsType complexPortfolioConcentrationsType = objectFactoryAIF
					.createComplexPortfolioConcentrationsType();
			
			// falta rellenarlos

			complexMostImportantConcentrationType
					.setAIFPrincipalMarkets(complexAIFPrincipalMarketsType);
			complexMostImportantConcentrationType
					.setInvestorConcentration(complexInvestorConcentrationType);
			complexMostImportantConcentrationType
					.setPortfolioConcentrations(complexPortfolioConcentrationsType);

			// <MostImportantConcentration><TypicalPositionSize>
			String typicalPositionSizeTypeString = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("ReportingPeriodType"))
					typicalPositionSizeTypeString = reportData
							.getReportDataText();
			}
			TypicalPositionSizeType typicalPositionSizeType = TypicalPositionSizeType
					.fromValue(typicalPositionSizeTypeString);
			complexMostImportantConcentrationType
					.setTypicalPositionSize(typicalPositionSizeType);

			// /////////////////////////////////////////////////////////////////
			// <AIFPrincipalInfo><NAVGeographicalFocus>

			// <NAVGeographicalFocus><AfricaNAVRate>
			BigDecimal africaNAVRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AfricaNAVRate"))
					africaNAVRate = new BigDecimal(
							reportData.getReportDataText());
			}
			complexNAVGeographicalFocusType.setAfricaNAVRate(africaNAVRate);

			// <NAVGeographicalFocus><AsiaPacificNAVRate>
			BigDecimal asiaPacificNAVRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AsiaPacificNAVRate"))
					asiaPacificNAVRate = new BigDecimal(
							reportData.getReportDataText());
			}
			complexNAVGeographicalFocusType
					.setAsiaPacificNAVRate(asiaPacificNAVRate);

			// <NAVGeographicalFocus><EEANAVRate>
			BigDecimal eEANAVRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("EEANAVRate"))
					eEANAVRate = new BigDecimal(reportData.getReportDataText());
			}
			complexNAVGeographicalFocusType.setEEANAVRate(eEANAVRate);

			// <NAVGeographicalFocus><EuropeNAVRate>
			BigDecimal europeNAVRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("EuropeNAVRate"))
					europeNAVRate = new BigDecimal(
							reportData.getReportDataText());
			}
			complexNAVGeographicalFocusType.setEuropeNAVRate(europeNAVRate);

			// <NAVGeographicalFocus><MiddleEastNAVRate>
			BigDecimal middleEastNAVRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("MiddleEastNAVRate"))
					middleEastNAVRate = new BigDecimal(
							reportData.getReportDataText());
			}
			complexNAVGeographicalFocusType
					.setMiddleEastNAVRate(middleEastNAVRate);

			// <NAVGeographicalFocus><NorthAmericaNAVRate>
			BigDecimal northAmericaNAVRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("NorthAmericaNAVRate"))
					northAmericaNAVRate = new BigDecimal(
							reportData.getReportDataText());
			}
			complexNAVGeographicalFocusType
					.setNorthAmericaNAVRate(northAmericaNAVRate);

			// <NAVGeographicalFocus><SouthAmericaNAVRate>
			BigDecimal southAmericaNAVRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("SouthAmericaNAVRate"))
					southAmericaNAVRate = new BigDecimal(
							reportData.getReportDataText());
			}
			complexNAVGeographicalFocusType
					.setSouthAmericaNAVRate(southAmericaNAVRate);

			// <NAVGeographicalFocus><SupraNationalNAVRate>
			BigDecimal supraNationalNAVRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("SupraNationalNAVRate"))
					supraNationalNAVRate = new BigDecimal(
							reportData.getReportDataText());
			}
			complexNAVGeographicalFocusType
					.setSupraNationalNAVRate(supraNationalNAVRate);

			// /////////////////////////////////////////////////////////////////
			// <AIFPrincipalInfo><PrincipalExposures>
			complexPrincipalExposuresType.getPrincipalExposure();

			// /////////////////////////////////////////////////////////////////
			// <AIFPrincipalInfo><ShareClassIdentification>
			complexShareClassIdentificationType.getShareClassIdentifier();

			// /////////////////////////////////////////////////////////////////
			// MAIN PART
			// /////////////////////////////////////////////////////////////////

			// /////////////////////////////////////////////////////////////////
			// <AIFCompleteDescription><AIFPrincipalInfo>

			complexAIFIndividualInfoType
					.setIndividualExposure(complexIndividualExposureType);
			complexAIFIndividualInfoType.setRiskProfile(complexRiskProfileType);
			complexAIFIndividualInfoType.setStressTests(complexStressTestsType);

			// /////////////////////////////////////////////////////////////////
			// <AIFCompleteDescription><AIFLeverageInfo>

			complexAIFLeverageInfoType
					.setAIFLeverageArticle242(complexAIFLeverageArticle242Type);
			complexAIFLeverageInfoType
					.setAIFLeverageArticle244(complexAIFLeverageArticle244Type);

			// /////////////////////////////////////////////////////////////////
			// <AIFCompleteDescription><AIFPrincipalInfo>

			complexAIFPrincipalInfoType
					.setAIFDescription(complexAIFDescriptionType);
			complexAIFPrincipalInfoType
					.setAIFIdentification(complexAIFIdentifierType);
			complexAIFPrincipalInfoType
					.setAUMGeographicalFocus(complexAUMGeographicalFocusType);
			complexAIFPrincipalInfoType
					.setMainInstrumentsTraded(complexMainInstrumentsTradedType);
			complexAIFPrincipalInfoType
					.setMostImportantConcentration(complexMostImportantConcentrationType);
			complexAIFPrincipalInfoType
					.setNAVGeographicalFocus(complexNAVGeographicalFocusType);
			complexAIFPrincipalInfoType
					.setPrincipalExposures(complexPrincipalExposuresType);

			// <AIFPrincipalInfo><ShareClassFlag>
			String shareClassFlag = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("ShareClassFlag"))
					shareClassFlag = reportData.getReportDataText();
			}
			complexAIFPrincipalInfoType.setShareClassFlag(Boolean
					.parseBoolean(shareClassFlag));

			complexAIFPrincipalInfoType
					.setShareClassIdentification(complexShareClassIdentificationType);

			// /////////////////////////////////////////////////////////////////
			// <AIFRecordInfo><AIFCompleteDescription>

			complexAIFCompleteDescriptionType
					.setAIFIndividualInfo(complexAIFIndividualInfoType);
			complexAIFCompleteDescriptionType
					.setAIFLeverageInfo(complexAIFLeverageInfoType);
			complexAIFCompleteDescriptionType
					.setAIFPrincipalInfo(complexAIFPrincipalInfoType);

			// /////////////////////////////////////////////////////////////////
			// <AIFRecordInfo>

			complexAIFRecordInfoType
					.setAIFCompleteDescription(complexAIFCompleteDescriptionType);

			// <AIFRecordInfo><AIFContentType>
			String aifContentType = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFContentType"))
					aifContentType = reportData.getReportDataText();
			}
			complexAIFRecordInfoType.setAIFContentType(aifContentType);

			// <AIFRecordInfo><AIFDomicile>
			String aifDomicile = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFDomicile"))
					aifDomicile = reportData.getReportDataText();
			}
			complexAIFRecordInfoType.setAIFDomicile(aifDomicile);

			// <AIFRecordInfo><AIFEEAFlag>
			String aifEEAFlag = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFEEAFlag"))
					aifEEAFlag = reportData.getReportDataText();
			}
			complexAIFRecordInfoType.setAIFEEAFlag(Boolean
					.parseBoolean(aifEEAFlag));

			// <AIFRecordInfo><AIFMNationalCode>
			String aifmNationalCode = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMNationalCode")
						&& reportData.getReportField().getReportFieldParent()
								.getReportFieldName().equals("AIFRecordInfo"))
					aifmNationalCode = reportData.getReportDataText();
			}
			complexAIFRecordInfoType.setAIFMNationalCode(aifmNationalCode);

			// <AIFRecordInfo><AIFName>
			String aifName = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFName")
						&& reportData.getReportField().getReportFieldParent()
								.getReportFieldName().equals("AIFRecordInfo"))
					aifName = reportData.getReportDataText();
			}
			complexAIFRecordInfoType.setAIFName(aifName);

			// <AIFRecordInfo><AIFNationalCode>
			String aifNationalCode = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFNationalCode")
						&& reportData.getReportField().getReportFieldParent()
								.getReportFieldName().equals("AIFRecordInfo"))
					aifNationalCode = reportData.getReportDataText();
			}
			complexAIFRecordInfoType.setAIFNationalCode(aifNationalCode);

			// <AIFRecordInfo><AIFNoReportingFlag>
			String aifNoReportingFlag = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFNoReportingFlag"))
					aifNoReportingFlag = reportData.getReportDataText();
			}
			complexAIFRecordInfoType.setAIFNoReportingFlag(Boolean
					.parseBoolean(aifNoReportingFlag));

			// <AIFRecordInfo><AIFReportingCode>
			BigInteger iafReportingCode = new BigInteger("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFReportingCode"))
					iafReportingCode = new BigInteger(
							reportData.getReportDataText());
			}
			complexAIFRecordInfoType.setAIFReportingCode(iafReportingCode);

			// <AIFRecordInfo><AIFReportingObligationChangeContentsCode>
			BigInteger aifReportingObligationChangeContentsCode = new BigInteger(
					"0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFReportingObligationChangeQuarter"))
					aifReportingObligationChangeContentsCode = new BigInteger(
							reportData.getReportDataText());
			}
			complexAIFRecordInfoType
					.setAIFReportingObligationChangeContentsCode(aifReportingObligationChangeContentsCode);

			// <AIFRecordInfo><AIFMReportingObligationChangeQuarter>
			String reportingObligationChangeQuarter = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFReportingObligationChangeQuarter"))
					reportingObligationChangeQuarter = reportData
							.getReportDataText();
			}
			ReportingObligationChangeQuarterType reportingObligationChangeQuarterType = ReportingObligationChangeQuarterType
					.fromValue(reportingObligationChangeQuarter);
			complexAIFRecordInfoType
					.setAIFReportingObligationChangeQuarter(reportingObligationChangeQuarterType);

			// <AIFRecordInfo><AIFReportingObligationChangeFrequencyCode>
			String reportingObligationChangeFrequency = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFReportingObligationChangeFrequencyCode"))
					reportingObligationChangeFrequency = reportData
							.getReportDataText();
			}
			ReportingObligationChangeFrequencyCodeType reportingObligationChangeFrequencyCodeType = ReportingObligationChangeFrequencyCodeType
					.fromValue(reportingObligationChangeFrequency);
			complexAIFRecordInfoType
					.setAIFReportingObligationChangeFrequencyCode(reportingObligationChangeFrequencyCodeType);

			// <AIFRecordInfo><Assumptions>
			List<ComplexAssumptionType> complexAssumptionTypeList = new ArrayList<ComplexAssumptionType>(
					complexAssumptionsType.getAssumption());
			int assumptionCount = 0;
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AssumptionDescription"))
					assumptionCount++;
			}
			for (int i = 1; i < assumptionCount + 1; i++) {
				// <Assumption>
				for (ReportData reportData : reportDatas) {
					// <QuestionNumber>
					BigInteger questionNumber = new BigInteger("0");
					if (reportData.getReportField().getReportFieldName()
							.equals("QuestionNumber")
							&& Integer
									.parseInt(reportData.getReportDataBlock()) == i) {
						questionNumber = new BigInteger(
								reportData.getReportDataText());
					}
					// <AssumptionDescription>
					String assumptionDescription = "";
					if (reportData.getReportField().getReportFieldName()
							.equals("AssumptionDescription")
							&& Integer
									.parseInt(reportData.getReportDataBlock()) == i) {
						questionNumber = new BigInteger(
								reportData.getReportDataText());
					}
					ComplexAssumptionType complexAssumptionType = objectFactoryAIF
							.createComplexAssumptionType();
					complexAssumptionType
							.setAssumptionDescription(assumptionDescription);
					complexAssumptionType.setQuestionNumber(questionNumber);
					complexAssumptionTypeList.add(complexAssumptionType);
				}
			}
			complexAIFRecordInfoType.setAssumptions(complexAssumptionsType);

			// <AIFRecordInfo><FilingType>
			String filingType = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("FilingType"))
					filingType = reportData.getReportDataText();
			}
			FilingTypeType filingTypeType = FilingTypeType.valueOf(filingType);
			complexAIFRecordInfoType.setFilingType(filingTypeType);

			// <AIFRecordInfo><InceptionDate>
			String inceptionDateString = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("InceptionDate"))
					inceptionDateString = reportData.getReportDataText();
			}
			Date inceptionDate = formatYear.parse(inceptionDateString);
			XMLGregorianCalendar inceptionDateXML = XMLGregorianCalendarConverter
					.asXMLGregorianCalendarDate(inceptionDate);
			complexAIFRecordInfoType.setInceptionDate(inceptionDateXML);

			// <AIFRecordInfo><LastReportingFlag>
			String lastReportingFlag = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("LastReportingFlag"))
					lastReportingFlag = reportData.getReportDataText();
			}
			complexAIFRecordInfoType.setLastReportingFlag(Boolean
					.parseBoolean(lastReportingFlag));

			// <AIFRecordInfo><ReportingPeriodEndDate>
			String reportingPeriodEndDateString = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("ReportingPeriodEndDate"))
					reportingPeriodEndDateString = reportData
							.getReportDataText();
			}
			Date reportingPeriodEndDate = formatYear
					.parse(reportingPeriodEndDateString);
			XMLGregorianCalendar reportingPeriodEndDateXML = XMLGregorianCalendarConverter
					.asXMLGregorianCalendarDate(reportingPeriodEndDate);
			complexAIFRecordInfoType
					.setReportingPeriodEndDate(reportingPeriodEndDateXML);

			// <AIFRecordInfo><ReportingPeriodStartDate>
			String reportingPeriodStartDateString = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("ReportingPeriodStartDate"))
					reportingPeriodStartDateString = reportData
							.getReportDataText();
			}
			Date reportingPeriodStartDate = formatYear
					.parse(reportingPeriodStartDateString);
			XMLGregorianCalendar reportingPeriodStartDateXML = XMLGregorianCalendarConverter
					.asXMLGregorianCalendarDate(reportingPeriodStartDate);
			complexAIFRecordInfoType
					.setReportingPeriodStartDate(reportingPeriodStartDateXML);

			// <AIFRecordInfo><ReportingPeriodType>
			String reportingPeriodType = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("ReportingPeriodType"))
					reportingPeriodType = reportData.getReportDataText();
			}
			ReportingPeriodTypeType reportingPeriodTypeType = ReportingPeriodTypeType
					.fromValue(reportingPeriodType);
			complexAIFRecordInfoType
					.setReportingPeriodType(reportingPeriodTypeType);

			// <AIFRecordInfo><ReportingPeriodYear>
			String reportingPeriodYearString = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("ReportingPeriodYear"))
					reportingPeriodYearString = reportData.getReportDataText();
			}
			Date reportingPeriodYear = formatYear
					.parse(reportingPeriodYearString);
			XMLGregorianCalendar reportingPeriodYearXML = XMLGregorianCalendarConverter
					.asXMLGregorianCalendarDate(reportingPeriodYear);
			complexAIFRecordInfoType
					.setReportingPeriodYear(reportingPeriodYearXML);

			// /////////////////////////////////////////////////////////////////
			// <CancellationAIFMRecordInfo>

			// <CancelledAIFMNationalCode>
			String cancelledAIFMNationalCode = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("CancelledAIFMNationalCode"))
					cancelledAIFMNationalCode = reportData.getReportDataText();
			}
			complexCancellationAIFRecordInfoType
					.setCancelledAIFMNationalCode(cancelledAIFMNationalCode);

			// <CancelledAIFNationalCode>
			String cancelledAIFNationalCode = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("CancelledAIFNationalCode"))
					cancelledAIFNationalCode = reportData.getReportDataText();
			}
			complexCancellationAIFRecordInfoType
					.setCancelledAIFNationalCode(cancelledAIFNationalCode);

			// <CancelledRecordFlag>
			String cancelledRecordFlag = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("CancelledRecordFlag"))
					cancelledRecordFlag = reportData.getReportDataText();
			}
			CancelledRecordFlagType cancelledRecordFlagXML = CancelledRecordFlagType
					.fromValue(cancelledRecordFlag);
			complexCancellationAIFRecordInfoType
					.setCancelledRecordFlag(cancelledRecordFlagXML);

			// <CancelledReportingPeriodYear>
			String cancelledReportingPeriodYearString = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("CancelledReportingPeriodYear"))
					cancelledReportingPeriodYearString = reportData
							.getReportDataText();
			}
			Date cancelledReportingPeriodYear = formatYear
					.parse(cancelledReportingPeriodYearString);
			XMLGregorianCalendar cancelledReportingPeriodYearXML = XMLGregorianCalendarConverter
					.asXMLGregorianCalendarDate(cancelledReportingPeriodYear);
			complexCancellationAIFRecordInfoType
					.setCancelledReportingPeriodYear(cancelledReportingPeriodYearXML);

			// <CancelledReportingPeriodType>
			String cancelledReportingPeriodType = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("CancelledReportingPeriodType"))
					cancelledReportingPeriodType = reportData
							.getReportDataText();
			}
			ReportingPeriodTypeType cancelledReportingPeriodTypeType = ReportingPeriodTypeType
					.fromValue(cancelledReportingPeriodType);
			complexCancellationAIFRecordInfoType
					.setCancelledReportingPeriodType(cancelledReportingPeriodTypeType);

			// create list of root elements: AIFMRecordInfo and
			// CancellationAIFMRecordInfo
			List<Object> listAIFRepoting = aifReportingInfo
					.getCancellationAIFRecordInfoOrAIFRecordInfo();
			listAIFRepoting.add(complexAIFRecordInfoType);
			listAIFRepoting.add(complexCancellationAIFRecordInfoType);

			// GENERATING XML WITH JAXB

			// http://www.tutorialspoint.com/java/xml/javax_xml_bind_jaxbelement.htm

			// create JAXBElement of type AIFMReportingInfo
			JAXBElement<AIFReportingInfo> jaxbElement = new JAXBElement(
					new QName(AIFReportingInfo.class.getSimpleName()),
					AIFReportingInfo.class, aifReportingInfo);

			// create JAXBContext which will be used to update writer
			JAXBContext context = JAXBContext
					.newInstance(AIFMReportingInfo.class);

			// Create a String writer object which will be
			// used to write jaxbElment XML to string
			// StringWriter writer = new StringWriter();
			// marshall or convert jaxbElement
			// context.createMarshaller().marshal(jaxbElement, writer);
			// print XML string
			// System.out.println(writer.toString());

			// http://blog.sanaulla.info/2010/08/29/using-jaxb-to-generate-xml-from-the-java-xsd/
			// print nice in XML
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
			marshaller.marshal(jaxbElement, System.out);
			StringWriter st = new StringWriter();
			marshaller.marshal(jaxbElement, st);

			return st.toString();

		} catch (Exception e) {
			e.printStackTrace();
			ReportingErrorManager.createReportError(applicationContext,
					"CREATION", reportExecution, "FAIL",
					"Fail when parsing XML");
		}

		return null;
	}

	public String generateXMLAIFMD(ReportExecution reportExecution) {

		System.out.println("DEBUG_" + "GeneratorXML: starting XML generation ");

		// all dataFields
		List<ReportData> reportDatas = new ArrayList<ReportData>(
				reportExecution.getReportDatas());

		// show all reportData to make sure the content
		Map<String, String> reportMap = new HashMap<String, String>();
		for (ReportData reportData : reportDatas) {
			// this hashmap contain the content and <name> of every field
			reportMap.put(reportData.getReportField().getReportFieldName(),
					reportData.getReportDataText());
		}
		System.out.println(reportMap.toString());

		// ///////////////////////////////////////////////////////////
		// ONLY STATUS = PENDING WILL CREATE XML REPORTS
		// if (aifmdReportResult.getAifmdReportResultStat().equals("PENDING")) {
		// }

		try {

			DateFormat formatDate = new SimpleDateFormat(stringDateFormat);
			DateFormat formatYear = new SimpleDateFormat(stringYearFormat);
			DateFormat formatDateTime = new SimpleDateFormat(
					stringDateTimeFormat);

			// ObjectFactory to make every class to AIFM report
			ObjectFactoryAIFM objectFactoryAIFM = new ObjectFactoryAIFM();

			AIFMReportingInfo aifmReportingInfo = objectFactoryAIFM
					.createAIFMReportingInfo();

			ComplexCancellationAIFMRecordInfoType complexCancellationAIFMRecordInfoType = objectFactoryAIFM
					.createComplexCancellationAIFMRecordInfoType();

			ComplexAIFMRecordInfoType complexAIFMRecordInfoType = objectFactoryAIFM
					.createComplexAIFMRecordInfoType();

			ComplexAIFMCompleteDescriptionType complexAIFMCompleteDescriptionType = objectFactoryAIFM
					.createComplexAIFMCompleteDescriptionType();

			ComplexBaseCurrencyDescriptionType complexBaseCurrencyDescriptionType = objectFactoryAIFM
					.createComplexBaseCurrencyDescriptionType();

			ComplexAIFMIdentifierType complexAIFMIdentifierType = objectFactoryAIFM
					.createComplexAIFMIdentifierType();

			ComplexAIFMNationalIdentifierType complexAIFMNationalIdentifierType = objectFactoryAIFM
					.createComplexAIFMNationalIdentifierType();

			ComplexAIFMPrincipalInstrumentsType complexAIFMPrincipalInstrumentsType = objectFactoryAIFM
					.createComplexAIFMPrincipalInstrumentsType();

			ComplexAIFMPrincipalMarketsType complexAIFMPrincipalMarketsType = objectFactoryAIFM
					.createComplexAIFMPrincipalMarketsType();

			ComplexAssumptionsType complexAssumptionsType = objectFactoryAIFM
					.createComplexAssumptionsType();

			// /////////////////////////////////////////////////////////////////
			// <AIFMPrincipalMarkets>

			List<ComplexFivePrincipalMarketType> complexFivePrincipalMarketTypeList = complexAIFMPrincipalMarketsType
					.getAIFMFivePrincipalMarket();

			BigInteger aggregatedValueAmount = new BigInteger("0");
			BigInteger ranking = new BigInteger("0");
			ComplexMarketIdentificationWithNOTType complexMarketIdentificationWithNOTType = new ComplexMarketIdentificationWithNOTType();
			String marketCode = "";
			String marketCodeType = "";
			Boolean rankingFlag = false;
			for (int i = 1; i < 6; i++) {
				for (ReportData reportData : reportDatas) {

					// <AIFMPrincipalMarket><Ranking>
					if (reportData.getReportField().getReportFieldName()
							.equals("Ranking")
							&& reportData.getReportField()
									.getReportFieldParent()
									.getReportFieldName()
									.equals("AIFMFivePrincipalMarket")
							&& Integer
									.parseInt(reportData.getReportDataBlock()) == i) {
						ranking = new BigInteger(reportData.getReportDataText());
						rankingFlag = true;
					}

					// <AIFMPrincipalMarket><AggregatedValueAmount>
					if (reportData.getReportField().getReportFieldName()
							.equals("AggregatedValueAmount")
							&& reportData.getReportField()
									.getReportFieldParent()
									.getReportFieldName()
									.equals("AIFMFivePrincipalMarket")
							&& Integer
									.parseInt(reportData.getReportDataBlock()) == i)
						aggregatedValueAmount = new BigInteger(
								reportData.getReportDataText());

					// <AIFMPrincipalMarket><MarketCodeType>
					if (reportData.getReportField().getReportFieldName()
							.equals("MarketCodeType")
							&& Integer
									.parseInt(reportData.getReportDataBlock()) == i)
						marketCodeType = reportData.getReportDataText();

					// <AIFMPrincipalMarket><MarketCode>
					if (reportData.getReportField().getReportFieldName()
							.equals("MarketCode")
							&& Integer
									.parseInt(reportData.getReportDataBlock()) == i)
						marketCode = reportData.getReportDataText();

				}
				if (rankingFlag) {
					ComplexFivePrincipalMarketType complexFivePrincipalMarketType = objectFactoryAIFM
							.createComplexFivePrincipalMarketType();
					complexFivePrincipalMarketType
							.setAggregatedValueAmount(aggregatedValueAmount);

					MarketCodeTypeWithNOTType marketCodeTypeWithNOTType = MarketCodeTypeWithNOTType
							.fromValue(marketCodeType);
					complexMarketIdentificationWithNOTType
							.setMarketCode(marketCode);
					complexMarketIdentificationWithNOTType
							.setMarketCodeType(marketCodeTypeWithNOTType);

					complexFivePrincipalMarketType
							.setMarketIdentification(complexMarketIdentificationWithNOTType);
					complexFivePrincipalMarketType.setRanking(ranking);

					complexFivePrincipalMarketTypeList
							.add(complexFivePrincipalMarketType);

					rankingFlag = false;
				}

			}

			// /////////////////////////////////////////////////////////////////
			// <AIFMPrincipalInstruments>

			List<ComplexPrincipalInstrumentType> complexPrincipalInstrumentTypeList = complexAIFMPrincipalInstrumentsType
					.getAIFMPrincipalInstrument();

			BigInteger aggregatedValueAmountInstrument = new BigInteger("0");
			BigInteger rankingInstrument = new BigInteger("0");
			String subAssetType = "";
			Boolean rankingInstrumentFlag = false;
			for (int i = 1; i < 6; i++) {
				for (ReportData reportData : reportDatas) {

					// <AIFMPrincipalInstrument><Ranking>
					if (reportData.getReportField().getReportFieldName()
							.equals("Ranking")
							&& reportData.getReportField()
									.getReportFieldParent()
									.getReportFieldName()
									.equals("AIFMPrincipalInstrument")
							&& Integer
									.parseInt(reportData.getReportDataBlock()) == i) {
						rankingInstrument = new BigInteger(
								reportData.getReportDataText());
						rankingInstrumentFlag = true;
					}

					// <AIFMPrincipalInstrument><AggregatedValueAmount>
					if (reportData.getReportField().getReportFieldName()
							.equals("AggregatedValueAmount")
							&& reportData.getReportField()
									.getReportFieldParent()
									.getReportFieldName()
									.equals("AIFMPrincipalInstrument")
							&& Integer
									.parseInt(reportData.getReportDataBlock()) == i)
						aggregatedValueAmountInstrument = new BigInteger(
								reportData.getReportDataText());

					// <AIFMPrincipalInstrument><SubAssetType>
					if (reportData.getReportField().getReportFieldName()
							.equals("SubAssetType")
							&& Integer
									.parseInt(reportData.getReportDataBlock()) == i)
						subAssetType = reportData.getReportDataText();

				}
				if (rankingInstrumentFlag) {
					ComplexPrincipalInstrumentType complexPrincipalInstrumentType = objectFactoryAIFM
							.createComplexPrincipalInstrumentType();

					complexPrincipalInstrumentType
							.setAggregatedValueAmount(aggregatedValueAmountInstrument);

					SubAssetTypeType subAssetTypeType = SubAssetTypeType
							.fromValue(subAssetType);

					complexPrincipalInstrumentType
							.setSubAssetType(subAssetTypeType);

					complexPrincipalInstrumentType
							.setRanking(rankingInstrument);

					complexPrincipalInstrumentTypeList
							.add(complexPrincipalInstrumentType);

					rankingInstrumentFlag = false;
				}

			}

			// /////////////////////////////////////////////////////////////////
			// <AIFMCompleteDescription>

			// <AUMAmountInBaseCurrency>
			BigInteger AUMAmountInBaseCurrency = new BigInteger("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AUMAmountInBaseCurrency"))
					AUMAmountInBaseCurrency = new BigInteger(
							reportData.getReportDataText());
			}
			complexBaseCurrencyDescriptionType
					.setAUMAmountInBaseCurrency(AUMAmountInBaseCurrency);

			// <BaseCurrency>
			String baseCurrency = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("BaseCurrency"))
					baseCurrency = reportData.getReportDataText();
			}
			complexBaseCurrencyDescriptionType.setBaseCurrency(baseCurrency);

			// <FXEUROtherReferenceRateDescription>
			String fxEUROtherReferenceRateDescription = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("FXEUROtherReferenceRateDescription"))
					fxEUROtherReferenceRateDescription = reportData
							.getReportDataText();
			}
			complexBaseCurrencyDescriptionType
					.setFXEUROtherReferenceRateDescription(fxEUROtherReferenceRateDescription);

			// <FXEURRate>
			BigDecimal fxEURRate = new BigDecimal("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("FXEURRate"))
					fxEURRate = new BigDecimal(reportData.getReportDataText());
			}
			complexBaseCurrencyDescriptionType.setFXEURRate(fxEURRate);

			// <FXEURReferenceRateType>
			String fxEURReferenceRateType = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("FXEURReferenceRateType"))
					fxEUROtherReferenceRateDescription = reportData
							.getReportDataText();
			}
			FXEURReferenceRateTypeType fxEURReferenceRateTypeType = FXEURReferenceRateTypeType
					.fromValue(fxEURReferenceRateType);
			complexBaseCurrencyDescriptionType
					.setFXEURReferenceRateType(fxEURReferenceRateTypeType);

			// <OLD_AIFMNationalCode>
			String oldAIFMNationalCode = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMNationalCode")
						&& reportData.getReportField().getReportFieldParent()
								.getReportFieldName().equals("AIFMIdentifier"))
					oldAIFMNationalCode = reportData.getReportDataText();
			}
			complexAIFMNationalIdentifierType
					.setAIFMNationalCode(oldAIFMNationalCode);

			// <OLD_ReportingMemberState>
			String oldReportingMemberState = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("ReportingMemberState")
						&& reportData.getReportField().getReportFieldParent()
								.getReportFieldName().equals("AIFMIdentifier"))
					oldReportingMemberState = reportData.getReportDataText();
			}
			complexAIFMNationalIdentifierType
					.setReportingMemberState(oldReportingMemberState);

			// <AIFMIdentifierBIC>
			String aifmIdentifierBIC = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMIdentifierBIC"))
					aifmIdentifierBIC = reportData.getReportDataText();
			}
			complexAIFMIdentifierType.setAIFMIdentifierBIC(aifmIdentifierBIC);

			// <AIFMIdentifierLEI>
			String aifmIdentifierLEI = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMIdentifierLEI"))
					aifmIdentifierLEI = reportData.getReportDataText();
			}
			complexAIFMIdentifierType.setAIFMIdentifierLEI(aifmIdentifierLEI);

			// <AUMAmountInEuro>
			BigInteger AUMAmountInEuro = new BigInteger("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AUMAmountInEuro"))
					AUMAmountInEuro = new BigInteger(
							reportData.getReportDataText());
			}
			complexAIFMCompleteDescriptionType
					.setAUMAmountInEuro(AUMAmountInEuro);

			complexAIFMIdentifierType
					.setOldAIFMIdentifierNCA(complexAIFMNationalIdentifierType);

			complexAIFMCompleteDescriptionType
					.setAIFMIdentifier(complexAIFMIdentifierType);
			complexAIFMCompleteDescriptionType
					.setAIFMBaseCurrencyDescription(complexBaseCurrencyDescriptionType);
			complexAIFMCompleteDescriptionType
					.setAIFMPrincipalInstruments(complexAIFMPrincipalInstrumentsType);
			complexAIFMCompleteDescriptionType
					.setAIFMPrincipalMarkets(complexAIFMPrincipalMarketsType);

			// /////////////////////////////////////////////////////////////////
			// <AIFMRecordInfo>

			complexAIFMRecordInfoType
					.setAIFMCompleteDescription(complexAIFMCompleteDescriptionType);

			// <AIFMContentType>
			String aifmContentType = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMContentType"))
					aifmContentType = reportData.getReportDataText();
			}
			complexAIFMRecordInfoType.setAIFMContentType(aifmContentType);

			// <AIFMEEAFlag>
			String aifmEEAFlag = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMEEAFlag"))
					aifmEEAFlag = reportData.getReportDataText();
			}
			complexAIFMRecordInfoType.setAIFMEEAFlag(Boolean
					.parseBoolean(aifmEEAFlag));

			// <AIFMJurisdiction>
			String aifmJurisdiction = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMJurisdiction"))
					aifmJurisdiction = reportData.getReportDataText();
			}
			complexAIFMRecordInfoType.setAIFMJurisdiction(aifmJurisdiction);

			// <AIFMName>
			String aifmName = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMName"))
					aifmName = reportData.getReportDataText();
			}
			complexAIFMRecordInfoType.setAIFMName(aifmName);

			// <AIFMNationalCode>
			String aifmNationalCode = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMNationalCode")
						&& reportData.getReportField().getReportFieldParent()
								.getReportFieldName().equals("AIFMRecordInfo"))
					aifmNationalCode = reportData.getReportDataText();
			}
			complexAIFMRecordInfoType.setAIFMNationalCode(aifmNationalCode);

			// <AIFMNoReportingFlag>
			String aifmNoReportingFlag = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMNoReportingFlag"))
					aifmNoReportingFlag = reportData.getReportDataText();
			}
			complexAIFMRecordInfoType.setAIFMNoReportingFlag(Boolean
					.parseBoolean(aifmNoReportingFlag));

			// <LastReportingFlag>
			String lastReportingFlag = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("LastReportingFlag"))
					lastReportingFlag = reportData.getReportDataText();
			}
			complexAIFMRecordInfoType.setLastReportingFlag(Boolean
					.parseBoolean(lastReportingFlag));

			// <AIFMReportingCode>
			BigInteger aifmReportingCode = new BigInteger("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMReportingCode"))
					aifmReportingCode = new BigInteger(
							reportData.getReportDataText());
			}
			complexAIFMRecordInfoType.setAIFMReportingCode(aifmReportingCode);

			// <AIFMReportingObligationChangeContentsCode>
			BigInteger aifmReportingObligationChangeContentsCode = new BigInteger(
					"0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMReportingObligationChangeContentsCode"))
					aifmReportingObligationChangeContentsCode = new BigInteger(
							reportData.getReportDataText());
			}
			complexAIFMRecordInfoType
					.setAIFMReportingObligationChangeContentsCode(aifmReportingObligationChangeContentsCode);

			// <AIFMReportingObligationChangeQuarter>
			String reportingObligationChangeQuarter = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMReportingObligationChangeQuarter"))
					reportingObligationChangeQuarter = reportData
							.getReportDataText();
			}
			ReportingObligationChangeQuarterType reportingObligationChangeQuarterType = ReportingObligationChangeQuarterType
					.fromValue(reportingObligationChangeQuarter);
			complexAIFMRecordInfoType
					.setAIFMReportingObligationChangeQuarter(reportingObligationChangeQuarterType);

			// <AIFMReportingObligationChangeFrequencyCode>
			String reportingObligationChangeFrequency = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMReportingObligationChangeFrequencyCode"))
					reportingObligationChangeFrequency = reportData
							.getReportDataText();
			}
			ReportingObligationChangeFrequencyCodeType reportingObligationChangeFrequencyCode = ReportingObligationChangeFrequencyCodeType
					.fromValue(reportingObligationChangeFrequency);
			complexAIFMRecordInfoType
					.setAIFMReportingObligationChangeFrequencyCode(reportingObligationChangeFrequencyCode);

			// <Assumptions>
			List<ComplexAssumptionType> complexAssumptionTypeList = new ArrayList<ComplexAssumptionType>(
					complexAssumptionsType.getAssumption());
			int assumptionCount = 0;
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AssumptionDescription"))
					assumptionCount++;
			}
			for (int i = 1; i < assumptionCount + 1; i++) {
				// <Assumption>
				for (ReportData reportData : reportDatas) {
					// <QuestionNumber>
					BigInteger questionNumber = new BigInteger("0");
					if (reportData.getReportField().getReportFieldName()
							.equals("QuestionNumber")
							&& Integer
									.parseInt(reportData.getReportDataBlock()) == i) {
						questionNumber = new BigInteger(
								reportData.getReportDataText());
					}
					// <AssumptionDescription>
					String assumptionDescription = "";
					if (reportData.getReportField().getReportFieldName()
							.equals("AssumptionDescription")
							&& Integer
									.parseInt(reportData.getReportDataBlock()) == i) {
						questionNumber = new BigInteger(
								reportData.getReportDataText());
					}
					ComplexAssumptionType complexAssumptionType = objectFactoryAIFM
							.createComplexAssumptionType();
					complexAssumptionType
							.setAssumptionDescription(assumptionDescription);
					complexAssumptionType.setQuestionNumber(questionNumber);
					complexAssumptionTypeList.add(complexAssumptionType);
				}
			}
			complexAIFMRecordInfoType.setAssumptions(complexAssumptionsType);

			// <FilingType>
			String filingType = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("FilingType"))
					filingType = reportData.getReportDataText();
			}
			FilingTypeType filingTypeType = FilingTypeType.valueOf(filingType);
			complexAIFMRecordInfoType.setFilingType(filingTypeType);

			// <ReportingPeriodEndDate>
			String reportingPerdiodEndDateString = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("ReportingPeriodEndDate"))
					reportingPerdiodEndDateString = reportData
							.getReportDataText();
			}
			Date reportingPerdiodEndDate = formatDate
					.parse(reportingPerdiodEndDateString);
			XMLGregorianCalendar reportingPerdiodEndDateXML = XMLGregorianCalendarConverter
					.asXMLGregorianCalendarDate(reportingPerdiodEndDate);
			complexAIFMRecordInfoType
					.setReportingPeriodEndDate(reportingPerdiodEndDateXML);

			// <ReportingPeriodStartDate>
			String reportingPeriodStartDateString = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("ReportingPeriodStartDate"))
					reportingPeriodStartDateString = reportData
							.getReportDataText();
			}
			Date reportingPeriodStartDate = formatDate
					.parse(reportingPeriodStartDateString);
			XMLGregorianCalendar reportingPeriodStartDateXML = XMLGregorianCalendarConverter
					.asXMLGregorianCalendarDate(reportingPeriodStartDate);
			complexAIFMRecordInfoType
					.setReportingPeriodStartDate(reportingPeriodStartDateXML);

			// <ReportingPeriodType>
			String reportingPeriodType = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("ReportingPeriodType"))
					reportingPeriodType = reportData.getReportDataText();
			}
			ReportingPeriodTypeType reportingPeriodTypeType = ReportingPeriodTypeType
					.fromValue(reportingPeriodType);
			complexAIFMRecordInfoType
					.setReportingPeriodType(reportingPeriodTypeType);

			// <ReportingPeriodYear>
			String reportingPeriodYearString = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("ReportingPeriodYear"))
					reportingPeriodYearString = reportData.getReportDataText();
			}
			Date reportingPeriodYear = formatYear
					.parse(reportingPeriodYearString);
			XMLGregorianCalendar reportingPeriodYearXML = XMLGregorianCalendarConverter
					.asXMLGregorianCalendarDate(reportingPeriodYear);
			complexAIFMRecordInfoType
					.setReportingPeriodYear(reportingPeriodYearXML);

			// /////////////////////////////////////////////////////////////////
			// <AifmReportingInfo>

			// <CreationDateAndTime>
			String creationDateAndTimeString = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("CreationDateAndTime"))
					creationDateAndTimeString = reportData.getReportDataText();
			}
			Date creationDateAndTime = formatDateTime
					.parse(creationDateAndTimeString);
			XMLGregorianCalendar creationDateAndTimeXML = XMLGregorianCalendarConverter
					.asXMLGregorianCalendarDateTime(creationDateAndTime);
			aifmReportingInfo.setCreationDateAndTime(creationDateAndTimeXML);

			// <ReportingMemberState>
			String reportingMemberState = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("ReportingMemberState")
						&& reportData.getReportField().getReportFieldParent()
								.getReportFieldName()
								.equals("AIFMReportingInfo"))
					reportingMemberState = reportData.getReportDataText();
			}
			aifmReportingInfo.setReportingMemberState(reportingMemberState);

			// <Version>
			String version = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("Version"))
					version = reportData.getReportDataText();
			}
			aifmReportingInfo.setVersion(version);

			// /////////////////////////////////////////////////////////////////
			// <CancellationAIFMRecordInfo>

			// <CancelledAIFMNationalCode>
			String cancelledAIFMNationalCode = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("CancelledAIFMNationalCode"))
					cancelledAIFMNationalCode = reportData.getReportDataText();
			}
			complexCancellationAIFMRecordInfoType
					.setCancelledAIFMNationalCode(cancelledAIFMNationalCode);

			// <CancelledRecordFlag>
			String cancelledRecordFlag = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("CancelledRecordFlag"))
					cancelledRecordFlag = reportData.getReportDataText();
			}
			CancelledRecordFlagType cancelledRecordFlagXML = CancelledRecordFlagType
					.fromValue(cancelledRecordFlag);
			complexCancellationAIFMRecordInfoType
					.setCancelledRecordFlag(cancelledRecordFlagXML);

			// <CancelledReportingPeriodType>
			String cancelledReportingPeriodType = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("CancelledReportingPeriodType"))
					cancelledReportingPeriodType = reportData
							.getReportDataText();
			}
			ReportingPeriodTypeType cancelledReportingPeriodTypeType = ReportingPeriodTypeType
					.fromValue(cancelledReportingPeriodType);
			complexCancellationAIFMRecordInfoType
					.setCancelledReportingPeriodType(cancelledReportingPeriodTypeType);

			// <CancelledReportingPeriodYear>
			String cancelledReportingPeriodYearString = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("CancelledReportingPeriodYear"))
					cancelledReportingPeriodYearString = reportData
							.getReportDataText();
			}
			Date cancelledReportingPeriodYear = formatYear
					.parse(cancelledReportingPeriodYearString);
			XMLGregorianCalendar cancelledReportingPeriodYearXML = XMLGregorianCalendarConverter
					.asXMLGregorianCalendarDate(cancelledReportingPeriodYear);
			complexCancellationAIFMRecordInfoType
					.setCancelledReportingPeriodYear(cancelledReportingPeriodYearXML);

			// /////////////////////////////////////////////////////////////////
			// create list of root elements:
			// AIFMRecordInfo and CancellationAIFMRecordInfo
			List<Object> listAIFMRepoting = aifmReportingInfo
					.getCancellationAIFMRecordInfoOrAIFMRecordInfo();
			listAIFMRepoting.add(complexAIFMRecordInfoType);
			listAIFMRepoting.add(complexCancellationAIFMRecordInfoType);

			// GENERATING XML WITH JAXB

			// http://www.tutorialspoint.com/java/xml/javax_xml_bind_jaxbelement.htm

			// create JAXBElement of type AIFMReportingInfo
			JAXBElement<AIFMReportingInfo> jaxbElement = new JAXBElement(
					new QName(AIFMReportingInfo.class.getSimpleName()),
					AIFMReportingInfo.class, aifmReportingInfo);

			// create JAXBContext which will be used to update writer
			JAXBContext context = JAXBContext
					.newInstance(AIFMReportingInfo.class);

			// Create a String writer object which will be
			// used to write jaxbElment XML to string
			// StringWriter writer = new StringWriter();
			// marshall or convert jaxbElement
			// context.createMarshaller().marshal(jaxbElement, writer);
			// print XML string
			// System.out.println(writer.toString());

			// http://blog.sanaulla.info/2010/08/29/using-jaxb-to-generate-xml-from-the-java-xsd/
			// print nice in XML
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
			marshaller.marshal(jaxbElement, System.out);
			StringWriter st = new StringWriter();
			marshaller.marshal(jaxbElement, st);

			return st.toString();

		} catch (Exception e) {
			e.printStackTrace();
			ReportingErrorManager.createReportError(applicationContext,
					"CREATION", reportExecution, "FAIL",
					"Fail when parsing XML");
		}

		return null;

	}
	// public void generateXMLAIFM_OLD(ReportExecution reportExecution) {
	//
	// System.out.println("DEBUG_" + "GeneratorXML: starting XML generation ");
	//
	// ReportCatalog reportCatalog = reportExecution.getReportCatalog();
	//
	// List<ReportField> reportFields = new ArrayList<ReportField>(
	// reportCatalog.getReportFields());
	//
	// // all dataFields
	// List<ReportData> reportDatas = new ArrayList<ReportData>(
	// reportExecution.getReportDatas());
	//
	// // ///////////////////////////////////////////////////////////
	// // ONLY STATUS = PENDING WILL CREATE XML REPORTS
	// // if (aifmdReportResult.getAifmdReportResultStat().equals("PENDING")) {
	// // }
	//
	// try {
	//
	// DocumentBuilderFactory docFactory = DocumentBuilderFactory
	// .newInstance();
	// DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	//
	// /*
	// * XML SCHEMA <AIFMReportingInfo
	// * CreationDateAndTime="2014-01-26T15:55:02" Version="1.2"
	// * ReportingMemberState="GB"
	// * xsi:noNamespaceSchemaLocation="AIFMD_DATMAN_V1.2.xsd"
	// * xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	// * <AIFMRecordInfo> ... </AIFMRecordInfo>
	// * <CancellationAIFMRecordInfo> ... </CancellationAIFMRecordInfo>
	// * </AIFMReportingInfo>
	// */
	//
	// Map<String, String> reportMap = new HashMap<String, String>();
	//
	// for (ReportData reportData : reportDatas) {
	// // this hashmap contain the content and <name> of every field
	// reportMap.put(reportData.getReportField().getReportFieldName(),
	// reportData.getReportDataText());
	// }
	//
	// // http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
	//
	// System.out.println(reportMap.toString());
	//
	// // root elements
	// Document doc = docBuilder.newDocument();
	// Element rootElement = doc.createElement("AIFMReportingInfo");
	// doc.appendChild(rootElement);
	//
	// Element recordInfo = doc.createElement("AIFMRecordInfo");
	// rootElement.appendChild(recordInfo);
	//
	// // first block of AIFMRecordInfo
	// /*
	// * <FilingType>INIT</FilingType>
	// * <AIFMContentType>2</AIFMContentType>
	// * <ReportingPeriodStartDate>2014-10-01</ReportingPeriodStartDate>
	// * <ReportingPeriodEndDate>2014-12-31</ReportingPeriodEndDate>
	// * <ReportingPeriodType>Q4</ReportingPeriodType>
	// * <ReportingPeriodYear>2014</ReportingPeriodYear>
	// * <AIFMReportingObligationChangeFrequencyCode
	// * >QH</AIFMReportingObligationChangeFrequencyCode>
	// * <AIFMReportingObligationChangeQuarter
	// * >Q4</AIFMReportingObligationChangeQuarter>
	// * <LastReportingFlag>false</LastReportingFlag>
	// * <AIFMReportingCode>4</AIFMReportingCode>
	// * <AIFMJurisdiction>GB</AIFMJurisdiction>
	// * <AIFMNationalCode>474286</AIFMNationalCode> <AIFMName>AIFM
	// * 1</AIFMName> <AIFMEEAFlag>true</AIFMEEAFlag>
	// * <AIFMNoReportingFlag>false</AIFMNoReportingFlag>
	// */
	//
	// Element elementFilingType = doc.createElement("FilingType");
	// elementFilingType.appendChild(doc.createTextNode(reportMap
	// .get("FilingType")));
	// recordInfo.appendChild(elementFilingType);
	//
	// Element elementAIFMContentType = doc
	// .createElement("AIFMContentType");
	// elementAIFMContentType.appendChild(doc.createTextNode(reportMap
	// .get("AIFMContentType")));
	// recordInfo.appendChild(elementAIFMContentType);
	//
	// Element elementReportingPeriodStartDate = doc
	// .createElement("ReportingPeriodStartDate");
	// elementReportingPeriodStartDate.appendChild(doc
	// .createTextNode(reportMap.get("ReportingPeriodStartDate")));
	// recordInfo.appendChild(elementReportingPeriodStartDate);
	//
	// Element elementReportingPeriodEndDate = doc
	// .createElement("ReportingPeriodEndDate");
	// elementReportingPeriodEndDate.appendChild(doc
	// .createTextNode(reportMap.get("ReportingPeriodEndDate")));
	// recordInfo.appendChild(elementReportingPeriodEndDate);
	//
	// Element elementReportingPeriodType = doc
	// .createElement("ReportingPeriodType");
	// elementReportingPeriodType.appendChild(doc.createTextNode(reportMap
	// .get("ReportingPeriodType")));
	// recordInfo.appendChild(elementReportingPeriodType);
	//
	// Element elementReportingPeriodYear = doc
	// .createElement("ReportingPeriodYear");
	// elementReportingPeriodYear.appendChild(doc.createTextNode(reportMap
	// .get("ReportingPeriodYear")));
	// recordInfo.appendChild(elementReportingPeriodYear);
	//
	// Element elementAIFMReportingObligationChangeFrequencyCode = doc
	// .createElement("AIFMReportingObligationChangeFrequencyCode");
	// elementAIFMReportingObligationChangeFrequencyCode
	// .appendChild(doc.createTextNode(reportMap
	// .get("AIFMReportingObligationChangeFrequencyCode")));
	// recordInfo
	// .appendChild(elementAIFMReportingObligationChangeFrequencyCode);
	//
	// Element elementAIFMReportingObligationChangeQuarter = doc
	// .createElement("AIFMReportingObligationChangeQuarter");
	// elementAIFMReportingObligationChangeQuarter.appendChild(doc
	// .createTextNode(reportMap
	// .get("AIFMReportingObligationChangeQuarter")));
	// recordInfo.appendChild(elementAIFMReportingObligationChangeQuarter);
	//
	// Element elementLastReportingFlag = doc
	// .createElement("LastReportingFlag");
	// elementLastReportingFlag.appendChild(doc.createTextNode(reportMap
	// .get("LastReportingFlag")));
	// recordInfo.appendChild(elementLastReportingFlag);
	//
	// Element elementAIFMReportingCode = doc
	// .createElement("AIFMReportingCode");
	// elementAIFMReportingCode.appendChild(doc.createTextNode(reportMap
	// .get("AIFMReportingCode")));
	// recordInfo.appendChild(elementAIFMReportingCode);
	//
	// Element elementAIFMJurisdiction = doc
	// .createElement("AIFMJurisdiction");
	// elementAIFMJurisdiction.appendChild(doc.createTextNode(reportMap
	// .get("AIFMJurisdiction")));
	// recordInfo.appendChild(elementAIFMJurisdiction);
	//
	// Element elementAIFMNationalCode = doc
	// .createElement("AIFMNationalCode");
	// elementAIFMNationalCode.appendChild(doc.createTextNode(reportMap
	// .get("AIFMNationalCode")));
	// recordInfo.appendChild(elementAIFMNationalCode);
	//
	// Element elementAIFMName = doc.createElement("AIFMName");
	// elementAIFMName.appendChild(doc.createTextNode(reportMap
	// .get("AIFMName")));
	// recordInfo.appendChild(elementAIFMName);
	//
	// Element elemenAIFMEEAFlag = doc.createElement("AIFMEEAFlag");
	// elemenAIFMEEAFlag.appendChild(doc.createTextNode(reportMap
	// .get("AIFMEEAFlag")));
	// recordInfo.appendChild(elemenAIFMEEAFlag);
	//
	// Element elementAIFMNoReportingFlag = doc
	// .createElement("AIFMNoReportingFlag");
	// elementAIFMNoReportingFlag.appendChild(doc.createTextNode(reportMap
	// .get("AIFMNoReportingFlag")));
	// recordInfo.appendChild(elementAIFMNoReportingFlag);
	//
	// // second part, AIFMCompleteDescription has market and instruments
	// // rankings
	// /*
	// * <AIFMCompleteDescription> <AIFMIdentifier>
	// * <AIFMIdentifierLEI>969500AA77L4ZJXJ0T02</AIFMIdentifierLEI>
	// * <AIFMIdentifierBIC>TESTGB21XXX</AIFMIdentifierBIC>
	// * </AIFMIdentifier> <AIFMPrincipalMarkets>
	// * <AIFMFivePrincipalMarket> <Ranking>1</Ranking>
	// * <MarketIdentification> <MarketCodeType>XXX</MarketCodeType>
	// * </MarketIdentification>
	// * <AggregatedValueAmount>452000000</AggregatedValueAmount>
	// * </AIFMFivePrincipalMarket> .. </AIFMPrincipalMarkets>
	// * <AIFMPrincipalInstruments> <AIFMPrincipalInstrument>
	// * <Ranking>1</Ranking> <SubAssetType>SEC_LEQ_OTHR</SubAssetType>
	// * <AggregatedValueAmount>248478000</AggregatedValueAmount>
	// * </AIFMPrincipalInstrument> ... </AIFMPrincipalInstruments>
	// * </AIFMCompleteDescription>
	// */
	//
	// Element elementAIFMCompleteDescription = doc
	// .createElement("AIFMCompleteDescription");
	// recordInfo.appendChild(elementAIFMCompleteDescription);
	//
	// Element elementAIFMIdentifier = doc.createElement("AIFMIdentifier");
	// elementAIFMCompleteDescription.appendChild(elementAIFMIdentifier);
	//
	// Element elementAIFMIdentifierLEI = doc
	// .createElement("AIFMIdentifierLEI");
	// elementAIFMIdentifierLEI.appendChild(doc.createTextNode(reportMap
	// .get("AIFMIdentifierLEI")));
	// elementAIFMIdentifier.appendChild(elementAIFMIdentifierLEI);
	//
	// Element elementAIFMIdentifierBIC = doc
	// .createElement("AIFMIdentifierBIC");
	// elementAIFMIdentifierBIC.appendChild(doc.createTextNode(reportMap
	// .get("AIFMIdentifierBIC")));
	// elementAIFMIdentifier.appendChild(elementAIFMIdentifierBIC);
	//
	// Element elementAIFMPrincipalMarkets = doc
	// .createElement("AIFMPrincipalMarkets");
	// elementAIFMCompleteDescription
	// .appendChild(elementAIFMPrincipalMarkets);
	//
	// // market ranking 1
	// Element elementAIFMFivePrincipalMarket1 = doc
	// .createElement("AIFMFivePrincipalMarket");
	// elementAIFMPrincipalMarkets
	// .appendChild(elementAIFMFivePrincipalMarket1);
	//
	// Element elementRanking1 = doc.createElement("Ranking");
	// elementRanking1.appendChild(doc.createTextNode("1"));
	// elementAIFMFivePrincipalMarket1.appendChild(elementRanking1);
	//
	// Element elementMarketIdentification1 = doc
	// .createElement("MarketIdentification");
	// elementAIFMFivePrincipalMarket1
	// .appendChild(elementMarketIdentification1);
	//
	// Element elementMarketCodeType1 = doc
	// .createElement("MarketCodeType");
	// elementMarketCodeType1.appendChild(doc.createTextNode(reportMap
	// .get("MarketCodeType1")));
	// elementMarketIdentification1.appendChild(elementMarketCodeType1);
	//
	// if (reportMap.get("MarketCode1") != null) {
	// Element elementMarketCode1 = doc.createElement("MarketCode");
	// elementMarketCode1.appendChild(doc.createTextNode(reportMap
	// .get("MarketCode1")));
	// elementMarketIdentification1.appendChild(elementMarketCode1);
	// }
	//
	// Element elementAggregatedValueAmount1 = doc
	// .createElement("AggregatedValueAmount");
	// elementAggregatedValueAmount1.appendChild(doc
	// .createTextNode(reportMap
	// .get("MarketAggregatedValueAmount1")));
	// elementAIFMFivePrincipalMarket1
	// .appendChild(elementAggregatedValueAmount1);
	//
	// // market ranking 2
	// Element elementAIFMFivePrincipalMarket2 = doc
	// .createElement("AIFMFivePrincipalMarket");
	// elementAIFMPrincipalMarkets
	// .appendChild(elementAIFMFivePrincipalMarket2);
	//
	// Element elementRanking2 = doc.createElement("Ranking");
	// elementRanking2.appendChild(doc.createTextNode("2"));
	// elementAIFMFivePrincipalMarket2.appendChild(elementRanking2);
	//
	// Element elementMarketIdentification2 = doc
	// .createElement("MarketIdentification");
	// elementAIFMFivePrincipalMarket2
	// .appendChild(elementMarketIdentification2);
	//
	// Element elementMarketCodeType2 = doc
	// .createElement("MarketCodeType");
	// elementMarketCodeType2.appendChild(doc.createTextNode(reportMap
	// .get("MarketCodeType2")));
	// elementMarketIdentification2.appendChild(elementMarketCodeType2);
	//
	// if (reportMap.get("MarketCode2") != null) {
	// Element elementMarketCode2 = doc.createElement("MarketCode");
	// elementMarketCode2.appendChild(doc.createTextNode(reportMap
	// .get("MarketCode2")));
	// elementMarketIdentification2.appendChild(elementMarketCode2);
	// }
	//
	// Element elementAggregatedValueAmount2 = doc
	// .createElement("AggregatedValueAmount");
	// elementAggregatedValueAmount2.appendChild(doc
	// .createTextNode(reportMap
	// .get("MarketAggregatedValueAmount2")));
	// elementAIFMFivePrincipalMarket2
	// .appendChild(elementAggregatedValueAmount2);
	//
	// // write the content into xml file
	// TransformerFactory transformerFactory = TransformerFactory
	// .newInstance();
	// Transformer transformer = transformerFactory.newTransformer();
	// DOMSource source = new DOMSource(doc);
	//
	// String path = "D:\\file.xml";
	// // StreamResult result = new StreamResult(new File(path));
	//
	// // Output to console for testing
	// StreamResult result = new StreamResult(System.out);
	//
	// transformer.transform(source, result);
	//
	// // System.out.println("DEBUG_" + "GeneratorXML: File saved " +
	// // path);
	//
	// } catch (ParserConfigurationException pce) {
	// pce.printStackTrace();
	// ReportingErrorManager.createReportError(applicationContext,
	// "CREATION", reportExecution, "FAIL",
	// "Fail when parsing XML");
	// } catch (TransformerException tfe) {
	// tfe.printStackTrace();
	// ReportingErrorManager.createReportError(applicationContext,
	// "CREATION", reportExecution, "FAIL",
	// "Fail when transforming XML");
	// }
	//
	// }
}
