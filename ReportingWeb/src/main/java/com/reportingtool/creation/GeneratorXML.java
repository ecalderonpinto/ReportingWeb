package com.reportingtool.creation;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.context.ApplicationContext;
import org.xml.sax.SAXException;

import com.entities.dictionary.ErrorTypeEnum;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.reportingtool.utilities.ReportUtilities;
import com.reportingtool.utilities.ReportingErrorManager;
import com.reportingtool.utilities.XMLGregorianCalendarConverter;
import com.reportingtool.xml.AIFMReportingInfo;
import com.reportingtool.xml.AIFMasterFeederStatusType;
import com.reportingtool.xml.AIFReportingInfo;
import com.reportingtool.xml.AIFTypeType;
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
import com.reportingtool.xml.ComplexAIFNationalIdentifierType;
import com.reportingtool.xml.ComplexAIFPrincipalInfoType;
import com.reportingtool.xml.ComplexAIFPrincipalMarketsType;
import com.reportingtool.xml.ComplexAIFRecordInfoType;
import com.reportingtool.xml.ComplexAUMGeographicalFocusType;
import com.reportingtool.xml.ComplexAllCounterpartyCollateralType;
import com.reportingtool.xml.ComplexAssetTypeExposuresType;
import com.reportingtool.xml.ComplexAssetTypeTurnoversType;
import com.reportingtool.xml.ComplexAssumptionType;
import com.reportingtool.xml.ComplexAssumptionsType;
import com.reportingtool.xml.ComplexBaseCurrencyDescriptionType;
import com.reportingtool.xml.ComplexBorrowingSourceType;
import com.reportingtool.xml.ComplexCCPExposureType;
import com.reportingtool.xml.ComplexCCPExposuresType;
import com.reportingtool.xml.ComplexCancellationAIFMRecordInfoType;
import com.reportingtool.xml.ComplexCancellationAIFRecordInfoType;
import com.reportingtool.xml.ComplexCompaniesDominantInfluenceType;
import com.reportingtool.xml.ComplexControlledStructureType;
import com.reportingtool.xml.ComplexControlledStructuresType;
import com.reportingtool.xml.ComplexCounterpartyRiskProfileType;
import com.reportingtool.xml.ComplexCurrencyExposuresType;
import com.reportingtool.xml.ComplexFinancialInstrumentBorrowingType;
import com.reportingtool.xml.ComplexFivePrincipalMarketType;
import com.reportingtool.xml.ComplexFundOfFundsInvestmentStrategiesType;
import com.reportingtool.xml.ComplexHedgeFundInvestmentStrategiesType;
import com.reportingtool.xml.ComplexHistoricalRiskProfileType;
import com.reportingtool.xml.ComplexIndividualExposureType;
import com.reportingtool.xml.ComplexInvestorConcentrationType;
import com.reportingtool.xml.ComplexLeverageAIFType;
import com.reportingtool.xml.ComplexLiquidityRiskProfileType;
import com.reportingtool.xml.ComplexMainInstrumentTradedType;
import com.reportingtool.xml.ComplexMainInstrumentsTradedType;
import com.reportingtool.xml.ComplexMarketIdentificationWithNOTType;
import com.reportingtool.xml.ComplexMarketRiskProfileType;
import com.reportingtool.xml.ComplexMasterAIFsIdentificationType;
import com.reportingtool.xml.ComplexMostImportantConcentrationType;
import com.reportingtool.xml.ComplexNAVGeographicalFocusType;
import com.reportingtool.xml.ComplexOperationalRiskType;
import com.reportingtool.xml.ComplexOtherFundInvestmentStrategiesType;
import com.reportingtool.xml.ComplexPortfolioConcentrationsType;
import com.reportingtool.xml.ComplexPrimeBrokersType;
import com.reportingtool.xml.ComplexPrincipalExposureType;
import com.reportingtool.xml.ComplexPrincipalExposuresType;
import com.reportingtool.xml.ComplexPrincipalInstrumentType;
import com.reportingtool.xml.ComplexPrivateEquityFundInvestmentStrategiesType;
import com.reportingtool.xml.ComplexRealEstateFundInvestmentStrategiesType;
import com.reportingtool.xml.ComplexRiskProfileType;
import com.reportingtool.xml.ComplexSecuritiesCashBorrowingType;
import com.reportingtool.xml.ComplexShareClassIdentificationType;
import com.reportingtool.xml.ComplexShareClassIdentifierType;
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

/**
 * Class to generate XML string from a reportExecution
 * 
 * @author alberto.olivan
 *
 */
public class GeneratorXML {

	private ApplicationContext applicationContext;

	public final String aifmXSDResource = "xml/AIFMD_DATMAN_V1.2.xsd";
	public final String aifXSDResource = "xml/AIFMD_DATAIF_V1.2.xsd";

	public final String aifmXLSHelp = "xls/AIFM_Help_v1.2.xlsx";
	public final String aifXLSHelp = "xls/AIF_Help_v1.2.xlsx";

	/**
	 * Constructor of GeneratorXML with an applicationContext
	 * 
	 * @param applicationContext
	 */
	public GeneratorXML(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Main function that generate a XML report with a reportExecution. Calls to
	 * AIF or AIFM generator.
	 * 
	 * @param reportExecution
	 * @return xml report in string
	 */
	public String generateXML(ReportExecution reportExecution) {

		String result = null;

		System.out.println("DEBUG_" + "GeneratorXML: starting XML with report "
				+ reportExecution.getReportCatalog().getReportLevel() + " "
				+ reportExecution.getReportExecutionName() + " "
				+ reportExecution.getReportPeriodType() + " "
				+ reportExecution.getReportPeriodYear() + " "
				+ reportExecution.getReportCatalog().getReportCatalogName());

		if (reportExecution.getReportCatalog().getReportLevel()
				.contains("FUND")) {
			// generate AIF report
			result = generateXMLAIF(reportExecution);
		}

		if (reportExecution.getReportCatalog().getReportLevel()
				.contains("COMPANY")) {
			// generate AIFMD report
			result = generateXMLAIFM(reportExecution);
		}

		return result;
	}

	/**
	 * Function generate a aifXML from a reportExecution with XSD classes and
	 * validate it. Create reportErrores
	 * 
	 * @param reportExecution
	 * @return aifXML string
	 */
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

		// TODO:RT user searchData() in every field

		// ///////////////////////////////////////////////////////////
		// TODO:RT ONLY STATUS = PENDING WILL CREATE XML REPORTS
		// if (aifmdReportResult.getAifmdReportResultStat().equals("PENDING")) {
		// }

		try {
			DateFormat formatDate = new SimpleDateFormat(
					ReportUtilities.datePattern);
			DateFormat formatYear = new SimpleDateFormat(
					ReportUtilities.yearPattern);
			DateFormat formatDateTime = new SimpleDateFormat(
					ReportUtilities.dateTimePattern);

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

			ComplexAssetTypeExposuresType complexAssetTypeExposuresType = objectFactoryAIF
					.createComplexAssetTypeExposuresType();

			ComplexAssetTypeTurnoversType complexAssetTypeTurnoversType = objectFactoryAIF
					.createComplexAssetTypeTurnoversType();

			ComplexCompaniesDominantInfluenceType complexCompaniesDominantInfluenceType = objectFactoryAIF
					.createComplexCompaniesDominantInfluenceType();

			ComplexCurrencyExposuresType complexCurrencyExposuresType = objectFactoryAIF
					.createComplexCurrencyExposuresType();

			ComplexRiskProfileType complexRiskProfileType = objectFactoryAIF
					.createComplexRiskProfileType();

			ComplexCounterpartyRiskProfileType complexCounterpartyRiskProfileType = objectFactoryAIF
					.createComplexCounterpartyRiskProfileType();

			ComplexLiquidityRiskProfileType complexLiquidityRiskProfileType = objectFactoryAIF
					.createComplexLiquidityRiskProfileType();

			ComplexMarketRiskProfileType complexMarketRiskProfileType = objectFactoryAIF
					.createComplexMarketRiskProfileType();

			ComplexOperationalRiskType complexOperationalRiskType = objectFactoryAIF
					.createComplexOperationalRiskType();

			ComplexStressTestsType complexStressTestsType = objectFactoryAIF
					.createComplexStressTestsType();

			ComplexAIFLeverageArticle242Type complexAIFLeverageArticle242Type = objectFactoryAIF
					.createComplexAIFLeverageArticle242Type();

			ComplexControlledStructuresType complexControlledStructuresType = objectFactoryAIF
					.createComplexControlledStructuresType();

			ComplexFinancialInstrumentBorrowingType complexFinancialInstrumentBorrowingType = objectFactoryAIF
					.createComplexFinancialInstrumentBorrowingType();

			ComplexLeverageAIFType complexLeverageAIFType = objectFactoryAIF
					.createComplexLeverageAIFType();

			ComplexSecuritiesCashBorrowingType complexSecuritiesCashBorrowingType = objectFactoryAIF
					.createComplexSecuritiesCashBorrowingType();

			ComplexAIFLeverageArticle244Type complexAIFLeverageArticle244Type = objectFactoryAIF
					.createComplexAIFLeverageArticle244Type();

			ComplexAIFDescriptionType complexAIFDescriptionType = objectFactoryAIF
					.createComplexAIFDescriptionType();

			ComplexBaseCurrencyDescriptionType complexBaseCurrencyDescriptionType = objectFactoryAIF
					.createComplexBaseCurrencyDescriptionType();

			ComplexFundOfFundsInvestmentStrategiesType complexFundOfFundsInvestmentStrategiesType = objectFactoryAIF
					.createComplexFundOfFundsInvestmentStrategiesType();

			ComplexHedgeFundInvestmentStrategiesType complexHedgeFundInvestmentStrategiesType = objectFactoryAIF
					.createComplexHedgeFundInvestmentStrategiesType();

			ComplexMasterAIFsIdentificationType complexMasterAIFsIdentificationType = objectFactoryAIF
					.createComplexMasterAIFsIdentificationType();

			ComplexOtherFundInvestmentStrategiesType complexOtherFundInvestmentStrategiesType = objectFactoryAIF
					.createComplexOtherFundInvestmentStrategiesType();

			ComplexPrimeBrokersType complexPrimeBrokersType = objectFactoryAIF
					.createComplexPrimeBrokersType();

			ComplexPrivateEquityFundInvestmentStrategiesType complexPrivateEquityFundInvestmentStrategiesType = objectFactoryAIF
					.createComplexPrivateEquityFundInvestmentStrategiesType();

			ComplexRealEstateFundInvestmentStrategiesType complexRealEstateFundInvestmentStrategiesType = objectFactoryAIF
					.createComplexRealEstateFundInvestmentStrategiesType();

			ComplexAIFIdentifierType complexAIFIdentifierType = objectFactoryAIF
					.createComplexAIFIdentifierType();

			ComplexAIFNationalIdentifierType complexAIFNationalIdentifierType = objectFactoryAIF
					.createComplexAIFNationalIdentifierType();

			ComplexAUMGeographicalFocusType complexAUMGeographicalFocusType = objectFactoryAIF
					.createComplexAUMGeographicalFocusType();

			ComplexMainInstrumentsTradedType complexMainInstrumentsTradedType = objectFactoryAIF
					.createComplexMainInstrumentsTradedType();

			ComplexMostImportantConcentrationType complexMostImportantConcentrationType = objectFactoryAIF
					.createComplexMostImportantConcentrationType();

			ComplexAIFPrincipalMarketsType complexAIFPrincipalMarketsType = objectFactoryAIF
					.createComplexAIFPrincipalMarketsType();

			ComplexInvestorConcentrationType complexInvestorConcentrationType = objectFactoryAIF
					.createComplexInvestorConcentrationType();

			ComplexPortfolioConcentrationsType complexPortfolioConcentrationsType = objectFactoryAIF
					.createComplexPortfolioConcentrationsType();

			ComplexNAVGeographicalFocusType complexNAVGeographicalFocusType = objectFactoryAIF
					.createComplexNAVGeographicalFocusType();

			ComplexPrincipalExposuresType complexPrincipalExposuresType = objectFactoryAIF
					.createComplexPrincipalExposuresType();

			ComplexShareClassIdentificationType complexShareClassIdentificationType = objectFactoryAIF
					.createComplexShareClassIdentificationType();

			ComplexAllCounterpartyCollateralType complexAllCounterpartyCollateralType = objectFactoryAIF
					.createComplexAllCounterpartyCollateralType();

			// /////////////////////////////////////////////////////////////////
			// <AifReportingInfo>

			// (1) <ReportingMemberState>
			if (ReportUtilities.searchData(reportDatas, "ReportingMemberState",
					"1", null) != null)
				aifReportingInfo.setReportingMemberState(ReportUtilities
						.searchData(reportDatas, "ReportingMemberState", "1",
								null));

			// (2) <Version>
			if (ReportUtilities.searchData(reportDatas, "Version", "2", null) != null)
				aifReportingInfo.setVersion(ReportUtilities.searchData(
						reportDatas, "Version", "2", null));

			// (3) <CreationDateAndTime>
			if (ReportUtilities.searchData(reportDatas, "CreationDateAndTime",
					"3", null) != null) {
				Date creationDateAndTime = formatDateTime.parse(ReportUtilities
						.searchData(reportDatas, "CreationDateAndTime", "3",
								null));
				XMLGregorianCalendar creationDateAndTimeXML = XMLGregorianCalendarConverter
						.asXMLGregorianCalendarDateTime(creationDateAndTime);
				aifReportingInfo.setCreationDateAndTime(creationDateAndTimeXML);
			}

			// /////////////////////////////////////////////////////////////////
			// <AIFRecordInfo>

			// (4) <FilingType>
			if (ReportUtilities
					.searchData(reportDatas, "FilingType", "4", null) != null) {
				FilingTypeType filingTypeType = FilingTypeType
						.valueOf(ReportUtilities.searchData(reportDatas,
								"FilingType", "4", null));
				complexAIFRecordInfoType.setFilingType(filingTypeType);
			}

			// (5) <AIFContentType>
			if (ReportUtilities.searchData(reportDatas, "AIFContentType", "5",
					null) != null)
				complexAIFRecordInfoType.setAIFContentType(ReportUtilities
						.searchData(reportDatas, "AIFContentType", "5", null));

			// (6) <ReportingPeriodStartDate>
			if (ReportUtilities.searchData(reportDatas,
					"ReportingPeriodStartDate", "6", null) != null) {
				Date reportingPeriodStartDate = formatYear
						.parse(ReportUtilities.searchData(reportDatas,
								"ReportingPeriodStartDate", "6", null));
				XMLGregorianCalendar reportingPeriodStartDateXML = XMLGregorianCalendarConverter
						.asXMLGregorianCalendarDate(reportingPeriodStartDate);
				complexAIFRecordInfoType
						.setReportingPeriodStartDate(reportingPeriodStartDateXML);
			}

			// (7) <ReportingPeriodEndDate>
			if (ReportUtilities.searchData(reportDatas,
					"ReportingPeriodEndDate", "7", null) != null) {
				Date reportingPeriodEndDate = formatYear.parse(ReportUtilities
						.searchData(reportDatas, "ReportingPeriodEndDate", "7",
								null));
				XMLGregorianCalendar reportingPeriodEndDateXML = XMLGregorianCalendarConverter
						.asXMLGregorianCalendarDate(reportingPeriodEndDate);
				complexAIFRecordInfoType
						.setReportingPeriodEndDate(reportingPeriodEndDateXML);
			}

			// (8) <ReportingPeriodType>
			if (ReportUtilities.searchData(reportDatas, "ReportingPeriodType",
					"8", null) != null) {
				ReportingPeriodTypeType reportingPeriodTypeType = ReportingPeriodTypeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"ReportingPeriodType", "8", null));
				complexAIFRecordInfoType
						.setReportingPeriodType(reportingPeriodTypeType);
			}

			// (9) <ReportingPeriodYear>
			if (ReportUtilities.searchData(reportDatas, "ReportingPeriodYear",
					"9", null) != null) {
				Date reportingPeriodYear = formatYear.parse(ReportUtilities
						.searchData(reportDatas, "ReportingPeriodYear", "9",
								null));
				XMLGregorianCalendar reportingPeriodYearXML = XMLGregorianCalendarConverter
						.asXMLGregorianCalendarDate(reportingPeriodYear);
				complexAIFRecordInfoType
						.setReportingPeriodYear(reportingPeriodYearXML);
			}

			// (10) <AIFReportingObligationChangeFrequencyCode>
			if (ReportUtilities.searchData(reportDatas,
					"AIFReportingObligationChangeFrequencyCode", "10", null) != null) {
				ReportingObligationChangeFrequencyCodeType reportingObligationChangeFrequencyCodeType = ReportingObligationChangeFrequencyCodeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"AIFReportingObligationChangeFrequencyCode",
								"10", null));
				complexAIFRecordInfoType
						.setAIFReportingObligationChangeFrequencyCode(reportingObligationChangeFrequencyCodeType);
			}

			// (11) <AIFReportingObligationChangeContentsCode>
			if (ReportUtilities.searchData(reportDatas,
					"AIFReportingObligationChangeQuarter", "11", null) != null)
				complexAIFRecordInfoType
						.setAIFReportingObligationChangeContentsCode(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"AIFReportingObligationChangeQuarter",
										"11", null)));

			// (12) <AIFReportingObligationChangeQuarter>
			if (ReportUtilities.searchData(reportDatas,
					"AIFReportingObligationChangeQuarter", "12", null) != null) {
				ReportingObligationChangeQuarterType reportingObligationChangeQuarterType = ReportingObligationChangeQuarterType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"AIFReportingObligationChangeQuarter", "12",
								null));
				complexAIFRecordInfoType
						.setAIFReportingObligationChangeQuarter(reportingObligationChangeQuarterType);
			}

			// (13) <LastReportingFlag>
			if (ReportUtilities.searchData(reportDatas, "LastReportingFlag",
					"13", null) != null)
				complexAIFRecordInfoType.setLastReportingFlag(Boolean
						.parseBoolean(ReportUtilities.searchData(reportDatas,
								"LastReportingFlag", "13", null)));

			// <Assumptions>
			List<ComplexAssumptionType> complexAssumptionTypeList = complexAssumptionsType
					.getAssumption();
			List<Integer> assumptionCounts = new ArrayList<Integer>();
			// first store in List all dataBlock
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AssumptionDescription"))
					assumptionCounts.add(Integer.parseInt(reportData
							.getReportDataBlock()));
			}
			for (Integer i : assumptionCounts) {
				BigInteger questionNumber = new BigInteger("0");
				String assumptionDescription = "";
				// <Assumption>
				ComplexAssumptionType complexAssumptionType = objectFactoryAIF
						.createComplexAssumptionType();

				// (14) <QuestionNumber>
				if (ReportUtilities.searchData(reportDatas, "QuestionNumber",
						"14", Integer.toString(i)) != null)
					complexAssumptionType
							.setQuestionNumber(new BigInteger(ReportUtilities
									.searchData(reportDatas, "QuestionNumber",
											"14", Integer.toString(i))));

				// (15) <AssumptionDescription>
				if (ReportUtilities.searchData(reportDatas,
						"AssumptionDescription", "15", Integer.toString(i)) != null)
					complexAssumptionType
							.setAssumptionDescription(ReportUtilities
									.searchData(reportDatas,
											"AssumptionDescription", "15",
											Integer.toString(i)));

				complexAssumptionTypeList.add(complexAssumptionType);
				System.out.println("assumption i " + i + " " + questionNumber
						+ " " + assumptionDescription);
			}
			complexAIFRecordInfoType.setAssumptions(complexAssumptionsType);

			// (16) <AIFMNationalCode>
			if (ReportUtilities.searchData(reportDatas, "AIFMNationalCode",
					"16", null) != null)
				complexAIFRecordInfoType
						.setAIFMNationalCode(ReportUtilities.searchData(
								reportDatas, "AIFMNationalCode", "16", null));

			// (17) <AIFNationalCode>
			if (ReportUtilities.searchData(reportDatas, "AIFNationalCode",
					"17", null) != null)
				complexAIFRecordInfoType
						.setAIFNationalCode(ReportUtilities.searchData(
								reportDatas, "AIFNationalCode", "17", null));

			// (18) <AIFName>
			if (ReportUtilities.searchData(reportDatas, "AIFName", "18", null) != null)
				complexAIFRecordInfoType.setAIFName(ReportUtilities.searchData(
						reportDatas, "AIFName", "18", null));

			// (19) <AIFEEAFlag>
			if (ReportUtilities.searchData(reportDatas, "AIFEEAFlag", "19",
					null) != null)
				complexAIFRecordInfoType.setAIFEEAFlag(Boolean
						.parseBoolean(ReportUtilities.searchData(reportDatas,
								"AIFEEAFlag", "19", null)));

			// (20) <AIFReportingCode>
			if (ReportUtilities.searchData(reportDatas, "AIFReportingCode",
					"20", null) != null)
				complexAIFRecordInfoType.setAIFReportingCode(new BigInteger(
						ReportUtilities.searchData(reportDatas,
								"AIFReportingCode", "20", null)));

			// (21) <AIFDomicile>
			if (ReportUtilities.searchData(reportDatas, "AIFDomicile", "21",
					null) != null)
				complexAIFRecordInfoType.setAIFDomicile(ReportUtilities
						.searchData(reportDatas, "AIFDomicile", "21", null));

			// (22) <InceptionDate>
			if (ReportUtilities.searchData(reportDatas, "InceptionDate", "22",
					null) != null) {
				Date inceptionDate = formatYear.parse(ReportUtilities
						.searchData(reportDatas, "InceptionDate", "22", null));
				XMLGregorianCalendar inceptionDateXML = XMLGregorianCalendarConverter
						.asXMLGregorianCalendarDate(inceptionDate);
				complexAIFRecordInfoType.setInceptionDate(inceptionDateXML);
			}

			// (23) <AIFNoReportingFlag>
			if (ReportUtilities.searchData(reportDatas, "AIFNoReportingFlag",
					"23", null) != null)
				complexAIFRecordInfoType.setAIFNoReportingFlag(Boolean
						.parseBoolean(ReportUtilities.searchData(reportDatas,
								"AIFNoReportingFlag", "23", null)));

			// /////////////////////////////////////////////////////////////////
			// <AIFPrincipalInfo><AIFIdentifier>

			// (24) <AIFIdentifierLEI>
			if (ReportUtilities.searchData(reportDatas, "AIFIdentifierLEI",
					"24", null) != null)
				complexAIFIdentifierType
						.setAIFIdentifierLEI(ReportUtilities.searchData(
								reportDatas, "AIFIdentifierLEI", "24", null));

			// (25) <AIFIdentifierISIN>
			if (ReportUtilities.searchData(reportDatas, "AIFIdentifierISIN",
					"25", null) != null)
				complexAIFIdentifierType.setAIFIdentifierISIN(ReportUtilities
						.searchData(reportDatas, "AIFIdentifierISIN", "25",
								null));

			// (26) <AIFIdentifierCUSIP>
			if (ReportUtilities.searchData(reportDatas, "AIFIdentifierCUSIP",
					"26", null) != null)
				complexAIFIdentifierType.setAIFIdentifierCUSIP(ReportUtilities
						.searchData(reportDatas, "AIFIdentifierCUSIP", "26",
								null));

			// (27) <AIFIdentifierSEDOL>
			if (ReportUtilities.searchData(reportDatas, "AIFIdentifierSEDOL",
					"27", null) != null)
				complexAIFIdentifierType.setAIFIdentifierSEDOL(ReportUtilities
						.searchData(reportDatas, "AIFIdentifierSEDOL", "27",
								null));

			// (28) <AIFIdentifierTicker>
			if (ReportUtilities.searchData(reportDatas, "AIFIdentifierTicker",
					"28", null) != null)
				complexAIFIdentifierType.setAIFIdentifierTicker(ReportUtilities
						.searchData(reportDatas, "AIFIdentifierTicker", "28",
								null));

			// (29) <AIFIdentifierRIC>
			if (ReportUtilities.searchData(reportDatas, "AIFIdentifierRIC",
					"29", null) != null)
				complexAIFIdentifierType
						.setAIFIdentifierRIC(ReportUtilities.searchData(
								reportDatas, "AIFIdentifierRIC", "29", null));

			// (30) <AIFIdentifierECB>
			if (ReportUtilities.searchData(reportDatas, "AIFIdentifierECB",
					"30", null) != null)
				complexAIFIdentifierType
						.setAIFIdentifierECB(ReportUtilities.searchData(
								reportDatas, "AIFIdentifierECB", "30", null));

			// (31) <Old_ReportingMemberState>
			if (ReportUtilities.searchData(reportDatas,
					"Old_ReportingMemberState", "31", null) != null)
				complexAIFNationalIdentifierType
						.setReportingMemberState(ReportUtilities.searchData(
								reportDatas, "Old_ReportingMemberState", "31",
								null));

			// (32) <Old_AIFNationalCode>
			if (ReportUtilities.searchData(reportDatas, "Old_AIFNationalCode",
					"32", null) != null)
				complexAIFNationalIdentifierType
						.setAIFNationalCode(ReportUtilities.searchData(
								reportDatas, "Old_AIFNationalCode", "32", null));

			if (ReportUtilities.searchData(reportDatas,
					"Old_ReportingMemberState", "31", null) != null
					&& ReportUtilities.searchData(reportDatas,
							"Old_AIFNationalCode", "32", null) != null)
				complexAIFIdentifierType
						.setOldAIFIdentifierNCA(complexAIFNationalIdentifierType);

			// /////////////////////////////////////////////////////////////////
			// <ShareClassIdentification>

			// TODO:RT populate this class

			List<ComplexShareClassIdentifierType> complexShareClassIdentifierTypeList = complexShareClassIdentificationType
					.getShareClassIdentifier();

			// (33) <ShareClassFlag>
			if (ReportUtilities.searchData(reportDatas, "ShareClassFlag", "33",
					null) != null)
				complexAIFPrincipalInfoType.setShareClassFlag(Boolean
						.parseBoolean(ReportUtilities.searchData(reportDatas,
								"ShareClassFlag", "33", null)));

			// TODO bucle for tantas share como haya pero cada field es 0,n
			ComplexShareClassIdentifierType complexShareClassIdentifierType = objectFactoryAIF
					.createComplexShareClassIdentifierType();

			// (34) <ShareClassNationalCode>
			complexShareClassIdentifierType.setShareClassNationalCode("");

			// (35) <ShareClassIdentifierISIN>
			complexShareClassIdentifierType.setShareClassIdentifierISIN("");

			// (36) <ShareClassIdentifierSEDOL>
			complexShareClassIdentifierType.setShareClassIdentifierSEDOL("");

			// (37) <ShareClassIdentifierCUSIP>
			complexShareClassIdentifierType.setShareClassIdentifierCUSIP("");

			// (38) <ShareClassIdentifierTicker>
			complexShareClassIdentifierType.setShareClassIdentifierTicker("");

			// (39) <ShareClassIdentifierRIC>
			complexShareClassIdentifierType.setShareClassIdentifierRIC("");

			// (40) <ShareClassName>
			complexShareClassIdentifierType.setShareClassName("");

			complexShareClassIdentifierTypeList
					.add(complexShareClassIdentifierType);

			complexAIFPrincipalInfoType
					.setShareClassIdentification(complexShareClassIdentificationType);

			// /////////////////////////////////////////////////////////////////
			// <AIFDescription>

			// (41) <AIFMasterFeederStatus>
			if (ReportUtilities.searchData(reportDatas,
					"AIFMasterFeederStatus", "41", null) != null) {
				AIFMasterFeederStatusType aifMasterFeederStatusType = AIFMasterFeederStatusType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"AIFMasterFeederStatus", "41", null));
				complexAIFDescriptionType
						.setAIFMasterFeederStatus(aifMasterFeederStatusType);
			}

			// TODO:RT populate this class

			complexAIFDescriptionType
					.setMasterAIFsIdentification(complexMasterAIFsIdentificationType);

			// /////////////////////////////////////////////////////////////////
			// <PrimeBrokers>

			// TODO:RT populate this class

			complexAIFDescriptionType.setPrimeBrokers(complexPrimeBrokersType);

			// /////////////////////////////////////////////////////////////////
			// <AIFBaseCurrencyDescription>

			// TODO:RT populate this class

			// (48) <AUMAmountInBaseCurrency>
			if (ReportUtilities.searchData(reportDatas,
					"AUMAmountInBaseCurrency", "48", null) != null)
				complexBaseCurrencyDescriptionType
						.setAUMAmountInBaseCurrency(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"AUMAmountInBaseCurrency", "48", null)));

			// (49) <BaseCurrency>
			if (ReportUtilities.searchData(reportDatas, "BaseCurrency", "49",
					null) != null)
				complexBaseCurrencyDescriptionType
						.setBaseCurrency(ReportUtilities.searchData(
								reportDatas, "BaseCurrency", "49", null));

			// (50) <FXEURRate>
			if (ReportUtilities
					.searchData(reportDatas, "FXEURRate", "50", null) != null)
				complexBaseCurrencyDescriptionType.setFXEURRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "FXEURRate",
								"50", null)));

			// (51) <FXEURReferenceRateType>
			if (ReportUtilities.searchData(reportDatas,
					"FXEURReferenceRateType", "51", null) != null) {
				FXEURReferenceRateTypeType fxEURReferenceRateTypeType = FXEURReferenceRateTypeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"FXEURReferenceRateType", "51", null));
				complexBaseCurrencyDescriptionType
						.setFXEURReferenceRateType(fxEURReferenceRateTypeType);
			}

			// (52) <FXEUROtherReferenceRateDescription>
			if (ReportUtilities.searchData(reportDatas,
					"FXEUROtherReferenceRateDescription", "52", null) != null)
				complexBaseCurrencyDescriptionType
						.setFXEUROtherReferenceRateDescription(ReportUtilities
								.searchData(reportDatas,
										"FXEUROtherReferenceRateDescription",
										"52", null));

			// (53) <AIFNetAssetValue>
			if (ReportUtilities.searchData(reportDatas, "AIFNetAssetValue",
					"53", null) != null)
				complexAIFDescriptionType.setAIFNetAssetValue(Long
						.parseLong(ReportUtilities.searchData(reportDatas,
								"AIFNetAssetValue", "53", null)));

			complexAIFDescriptionType
					.setAIFBaseCurrencyDescription(complexBaseCurrencyDescriptionType);

			// /////////////////////////////////////////////////////////////////
			// <Jurisdiction>

			// (54) <FirstFundingSourceCountry>
			if (ReportUtilities.searchData(reportDatas,
					"FirstFundingSourceCountry", "54", null) != null)
				complexAIFDescriptionType
						.setFirstFundingSourceCountry(ReportUtilities
								.searchData(reportDatas,
										"FirstFundingSourceCountry", "54", null));

			// (55) <SecondFundingSourceCountry>
			if (ReportUtilities.searchData(reportDatas,
					"SecondFundingSourceCountry", "55", null) != null)
				complexAIFDescriptionType
						.setSecondFundingSourceCountry(ReportUtilities
								.searchData(reportDatas,
										"SecondFundingSourceCountry", "55",
										null));

			// (56) <ThirdFundingSourceCountry>
			if (ReportUtilities.searchData(reportDatas,
					"ThirdFundingSourceCountry", "56", null) != null)
				complexAIFDescriptionType
						.setThirdFundingSourceCountry(ReportUtilities
								.searchData(reportDatas,
										"ThirdFundingSourceCountry", "56", null));

			// /////////////////////////////////////////////////////////////////
			// <AIF Type>

			// (57) <PredominantAIFType>
			if (ReportUtilities.searchData(reportDatas, "PredominantAIFType",
					"57", null) != null) {
				AIFTypeType predominantAIFType = AIFTypeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"PredominantAIFType", "57", null));
				complexAIFDescriptionType
						.setPredominantAIFType(predominantAIFType);
			}

			// /////////////////////////////////////////////////////////////////
			// <HedgeFundInvestmentStrategies>

			// TODO populate this class

			complexAIFDescriptionType
					.setHedgeFundInvestmentStrategies(complexHedgeFundInvestmentStrategiesType);

			// TODO:RT populate this class

			complexAIFDescriptionType
					.setFundOfFundsInvestmentStrategies(complexFundOfFundsInvestmentStrategiesType);

			// TODO:RT populate this class

			complexAIFDescriptionType
					.setOtherFundInvestmentStrategies(complexOtherFundInvestmentStrategiesType);

			// TODO:RT populate this class

			complexAIFDescriptionType
					.setPrivateEquityFundInvestmentStrategies(complexPrivateEquityFundInvestmentStrategiesType);

			// TODO:RT populate this class

			complexAIFDescriptionType
					.setRealEstateFundInvestmentStrategies(complexRealEstateFundInvestmentStrategiesType);

			// (62) <HFTTransactionNumber>
			if (ReportUtilities.searchData(reportDatas, "HFTTransactionNumber",
					"62", null) != null)
				complexAIFDescriptionType
						.setHFTTransactionNumber(new BigInteger(ReportUtilities
								.searchData(reportDatas,
										"HFTTransactionNumber", "62", null)));

			// (63) <HFTBuySellMarketValue>
			if (ReportUtilities.searchData(reportDatas,
					"HFTBuySellMarketValue", "63", null) != null)
				complexAIFDescriptionType
						.setHFTBuySellMarketValue(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"HFTBuySellMarketValue", "63", null)));

			// /////////////////////////////////////////////////////////////////
			// <MainInstrumentsTraded>

			// TODO:RT populate this class

			List<ComplexMainInstrumentTradedType> complexMainInstrumentTradedTypeList = complexMainInstrumentsTradedType
					.getMainInstrumentTraded();

			// /////////////////////////////////////////////////////////////////
			// <NAVGeographicalFocus>

			// (78) <AfricaNAVRate>
			if (ReportUtilities.searchData(reportDatas, "AfricaNAVRate", "78",
					null) != null)
				complexNAVGeographicalFocusType
						.setAfricaNAVRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "AfricaNAVRate", "78",
										null)));

			// (79) <AsiaPacificNAVRate>
			if (ReportUtilities.searchData(reportDatas, "AsiaPacificNAVRate",
					"79", null) != null)
				complexNAVGeographicalFocusType
						.setAsiaPacificNAVRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "AsiaPacificNAVRate",
										"79", null)));

			// (80) <EuropeNAVRate>
			if (ReportUtilities.searchData(reportDatas, "EuropeNAVRate", "80",
					null) != null)
				complexNAVGeographicalFocusType
						.setEuropeNAVRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "EuropeNAVRate", "80",
										null)));

			// (81) <EEANAVRate>
			if (ReportUtilities.searchData(reportDatas, "EEANAVRate", "81",
					null) != null)
				complexNAVGeographicalFocusType.setEEANAVRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "EEANAVRate",
								"81", null)));

			// (82) <MiddleEastNAVRate>
			if (ReportUtilities.searchData(reportDatas, "MiddleEastNAVRate",
					"82", null) != null)
				complexNAVGeographicalFocusType
						.setMiddleEastNAVRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "MiddleEastNAVRate",
										"82", null)));

			// (83) <NorthAmericaNAVRate>
			if (ReportUtilities.searchData(reportDatas, "NorthAmericaNAVRate",
					"83", null) != null)
				complexNAVGeographicalFocusType
						.setNorthAmericaNAVRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "NorthAmericaNAVRate",
										"83", null)));

			// (84) <SouthAmericaNAVRate>
			if (ReportUtilities.searchData(reportDatas, "SouthAmericaNAVRate",
					"84", null) != null)
				complexNAVGeographicalFocusType
						.setSouthAmericaNAVRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "SouthAmericaNAVRate",
										"84", null)));

			// (85) <SupraNationalNAVRate>
			if (ReportUtilities.searchData(reportDatas, "SupraNationalNAVRate",
					"85", null) != null)
				complexNAVGeographicalFocusType
						.setSupraNationalNAVRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas,
										"SupraNationalNAVRate", "85", null)));

			// /////////////////////////////////////////////////////////////////
			// <AUMGeographicalFocus>

			// (86) <AfricaAUMRate>
			if (ReportUtilities.searchData(reportDatas, "AfricaAUMRate", "86",
					null) != null)
				complexAUMGeographicalFocusType
						.setAfricaAUMRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "AfricaAUMRate", "86",
										null)));

			// (87) <AsiaPacificAUMRate>
			if (ReportUtilities.searchData(reportDatas, "AsiaPacificAUMRate",
					"87", null) != null)
				complexAUMGeographicalFocusType
						.setAsiaPacificAUMRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "AsiaPacificAUMRate",
										"87", null)));

			// (88) <EuropeAUMRate>
			if (ReportUtilities.searchData(reportDatas, "EuropeAUMRate", "88",
					null) != null)
				complexAUMGeographicalFocusType
						.setEuropeAUMRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "EuropeAUMRate", "88",
										null)));

			// (89) <EEAAUMRate>
			if (ReportUtilities.searchData(reportDatas, "EEAAUMRate", "89",
					null) != null)
				complexAUMGeographicalFocusType.setEEAAUMRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "EEAAUMRate",
								"89", null)));

			// (90) <MiddleEastAUMRate>
			if (ReportUtilities.searchData(reportDatas, "MiddleEastAUMRate",
					"90", null) != null)
				complexAUMGeographicalFocusType
						.setMiddleEastAUMRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "MiddleEastAUMRate",
										"90", null)));

			// (91) <NorthAmericaAUMRate>
			if (ReportUtilities.searchData(reportDatas, "NorthAmericaAUMRate",
					"91", null) != null)
				complexAUMGeographicalFocusType
						.setNorthAmericaAUMRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "NorthAmericaAUMRate",
										"91", null)));

			// (92) <SouthAmericaAUMRate>
			if (ReportUtilities.searchData(reportDatas, "SouthAmericaAUMRate",
					"92", null) != null)
				complexAUMGeographicalFocusType
						.setSouthAmericaAUMRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "SouthAmericaAUMRate",
										"92", null)));

			// (93) <SupraNationalAUMRate>
			if (ReportUtilities.searchData(reportDatas, "SupraNationalAUMRate",
					"93", null) != null)
				complexAUMGeographicalFocusType
						.setSupraNationalAUMRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas,
										"SupraNationalAUMRate", "93", null)));

			// /////////////////////////////////////////////////////////////////
			// <PrincipalExposures>

			// TODO:RT populate this class

			List<ComplexPrincipalExposureType> complexPrincipalExposureTypeList = complexPrincipalExposuresType
					.getPrincipalExposure();

			// /////////////////////////////////////////////////////////////////
			// <PortfolioConcentration>

			// TODO:RT populate this class

			complexMostImportantConcentrationType
					.setPortfolioConcentrations(complexPortfolioConcentrationsType);

			// /////////////////////////////////////////////////////////////////
			// <Typical Position Size>

			// (113) <TypicalPositionSize>
			if (ReportUtilities.searchData(reportDatas, "TypicalPositionSize",
					"113", null) != null) {
				TypicalPositionSizeType typicalPositionSizeType = TypicalPositionSizeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"TypicalPositionSize", "113", null));
				complexMostImportantConcentrationType
						.setTypicalPositionSize(typicalPositionSizeType);
			}

			// /////////////////////////////////////////////////////////////////
			// <AIFPrincipalMarkets>

			// TODO:RT populate this class

			complexMostImportantConcentrationType
					.setAIFPrincipalMarkets(complexAIFPrincipalMarketsType);

			// /////////////////////////////////////////////////////////////////
			// <InvestorConcentration>

			// (118) <MainBeneficialOwnersRate>
			if (ReportUtilities.searchData(reportDatas,
					"MainBeneficialOwnersRate", "118", null) != null)
				complexInvestorConcentrationType
						.setMainBeneficialOwnersRate(new BigDecimal(
								ReportUtilities
										.searchData(reportDatas,
												"MainBeneficialOwnersRate",
												"118", null)));

			// (119) <ProfessionalInvestorConcentrationRate>
			if (ReportUtilities.searchData(reportDatas,
					"ProfessionalInvestorConcentrationRate", "119", null) != null)
				complexInvestorConcentrationType
						.setProfessionalInvestorConcentrationRate(new BigDecimal(
								ReportUtilities
										.searchData(
												reportDatas,
												"ProfessionalInvestorConcentrationRate",
												"119", null)));

			// (120) <RetailInvestorConcentrationRate>
			if (ReportUtilities.searchData(reportDatas,
					"RetailInvestorConcentrationRate", "120", null) != null)
				complexInvestorConcentrationType
						.setRetailInvestorConcentrationRate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"RetailInvestorConcentrationRate",
										"120", null)));

			complexMostImportantConcentrationType
					.setInvestorConcentration(complexInvestorConcentrationType);

			// /////////////////////////////////////////////////////////////////
			// <IndividualExposure>

			// TODO populate this class

			complexIndividualExposureType
					.setAssetTypeExposures(complexAssetTypeExposuresType);

			// /////////////////////////////////////////////////////////////////
			// <AssetTypeTurnover>

			// TODO populate this class

			complexIndividualExposureType
					.setAssetTypeTurnovers(complexAssetTypeTurnoversType);

			// /////////////////////////////////////////////////////////////////
			// <CurrencyExposure>

			// TODO populate this class

			complexIndividualExposureType
					.setCurrencyExposures(complexCurrencyExposuresType);

			// /////////////////////////////////////////////////////////////////
			// <CompanyDominantInfluence>

			// TODO populate this class

			complexIndividualExposureType
					.setCompaniesDominantInfluence(complexCompaniesDominantInfluenceType);

			// /////////////////////////////////////////////////////////////////
			// <RiskProfile>

			// TODO:RT populate this class

			// (137) <AnnualInvestmentReturnRate>
			if (ReportUtilities.searchData(reportDatas,
					"AnnualInvestmentReturnRate", "137", null) != null)
				complexMarketRiskProfileType
						.setAnnualInvestmentReturnRate(ReportUtilities
								.searchData(reportDatas,
										"AnnualInvestmentReturnRate", "137",
										null));

			// complexMarketRiskProfileType.setMarketRiskMeasures(ComplexMarketRiskMeasuresType);

			// ComplexMarketRiskMeasuresType complexMarketRiskMeasuresType =
			// objectFactoryAIF.createComplexMarketRiskMeasuresType();
			//
			// List<ComplexMarketRiskMeasureType>
			// complexMarketRiskMeasureTypeList =
			// complexMarketRiskMeasuresType.getMarketRiskMeasure();
			//
			// ComplexMarketRiskMeasureType complexMarketRiskMeasureType =
			// objectFactoryAIF.createComplexMarketRiskMeasureType();
			// complexMarketRiskMeasureType.setBucketRiskMeasureValues(ComplexBucketRiskMeasureValuesType);
			//
			// complexMarketRiskMeasureTypeList.add(complexMarketRiskMeasureType);
			//
			// complexMarketRiskMeasureType.set(int i = 0,
			// complexMarketRiskMeasureType);
			//
			// complexMarketRiskProfileType.setMarketRiskMeasures(complexMarketRiskMeasuresType);

			complexRiskProfileType
					.setMarketRiskProfile(complexMarketRiskProfileType);

			// /////////////////////////////////////////////////////////////////
			// <Counterparty Risk Profile>

			// (148) <RegulatedMarketRate>
			// if (ReportUtilities.searchData(reportDatas,
			// "AnnualInvestmentReturnRate", "137", null) != null)

			ComplexCCPExposuresType complexCCPExposuresType = objectFactoryAIF
					.createComplexCCPExposuresType();

			List<ComplexCCPExposureType> complexCCPExposureTypeList = complexCCPExposuresType
					.getCCPExposure();

			ComplexCCPExposureType complexCCPExposureType = objectFactoryAIF
					.createComplexCCPExposureType();

			// complexCCPExposureType.setRanking(new BigInteger());
			// complexCCPExposureType.setCCPExposureValue(new BigInteger());
			// complexCCPExposureType.setCCPIdentification(ComplexEntityIdentificationType);

			// ?
			
			complexCCPExposureTypeList.add(complexCCPExposureType);

			complexCounterpartyRiskProfileType
					.setCCPExposures(complexCCPExposuresType);

			// (149) <OTCRate>

			// (150) <RegulatedMarketRate>

			// (151) <OTCRate>

			// (152) <CCPRate>

			// (153) <BilateralClearingRate>

			// (154) <CCPRate>

			// (155) <BilateralClearingRate>

			// (156) <TriPartyRepoClearingRate>

			complexRiskProfileType
					.setCounterpartyRiskProfile(complexCounterpartyRiskProfileType);

			// /////////////////////////////////////////////////////////////////
			// <AllCounterpartyCollateral>

			// (157) <AllCounterpartyCollateralCash>
			if (ReportUtilities.searchData(reportDatas,
					"AllCounterpartyCollateralCash", "157", null) != null)
				complexAllCounterpartyCollateralType
						.setAllCounterpartyCollateralCash(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"AllCounterpartyCollateralCash", "157",
										null)));

			// (158) <AllCounterpartyCollateralSecurities>
			if (ReportUtilities.searchData(reportDatas,
					"AllCounterpartyCollateralSecurities", "158", null) != null)
				complexAllCounterpartyCollateralType
						.setAllCounterpartyCollateralSecurities(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"AllCounterpartyCollateralSecurities",
										"158", null)));

			// (159) <AllCounterpartyOtherCollateralPosted>
			if (ReportUtilities.searchData(reportDatas,
					"AllCounterpartyOtherCollateralPosted", "159", null) != null)
				complexAllCounterpartyCollateralType
						.setAllCounterpartyOtherCollateralPosted(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"AllCounterpartyOtherCollateralPosted",
										"159", null)));

			complexCounterpartyRiskProfileType
					.setAllCounterpartyCollateral(complexAllCounterpartyCollateralType);

			// /////////////////////////////////////////////////////////////////
			// <LiquidityRiskProfile>

			// TODO class

			complexRiskProfileType
					.setLiquidityRiskProfile(complexLiquidityRiskProfileType);

			ComplexHistoricalRiskProfileType complexHistoricalRiskProfileType = objectFactoryAIF
					.createComplexHistoricalRiskProfileType();

			// complexHistoricalRiskProfileType
			// .setGrossInvestmentReturnsRate(ComplexSignedRateMonthPeriodType);
			// complexHistoricalRiskProfileType
			// .setNAVChangeRate(ComplexSignedRateMonthPeriodType);
			// complexHistoricalRiskProfileType
			// .setNetInvestmentReturnsRate(ComplexSignedRateMonthPeriodType);
			// complexHistoricalRiskProfileType
			// .setRedemption(ComplexQuantityMonthPeriodType);
			// complexHistoricalRiskProfileType
			// .setSubscription(ComplexQuantityMonthPeriodType);

			complexOperationalRiskType
					.setHistoricalRiskProfile(complexHistoricalRiskProfileType);

			// (218) <TotalOpenPositions>
			if (ReportUtilities.searchData(reportDatas, "TotalOpenPositions",
					"218", null) != null)
				complexOperationalRiskType
						.setTotalOpenPositions(new BigInteger(ReportUtilities
								.searchData(reportDatas, "TotalOpenPositions",
										"218", null)));

			complexRiskProfileType
					.setOperationalRisk(complexOperationalRiskType);

			// /////////////////////////////////////////////////////////////////
			// <StressTests>

			// (279) <StressTestsResultArticle15>
			if (ReportUtilities.searchData(reportDatas,
					"StressTestsResultArticle15", "279", null) != null)
				complexStressTestsType
						.setStressTestsResultArticle15(ReportUtilities
								.searchData(reportDatas,
										"StressTestsResultArticle15", "279",
										null));

			// (280) <StressTestsResultArticle16>
			if (ReportUtilities.searchData(reportDatas,
					"StressTestsResultArticle16", "280", null) != null)
				complexStressTestsType
						.setStressTestsResultArticle16(ReportUtilities
								.searchData(reportDatas,
										"StressTestsResultArticle16", "280",
										null));

			// /////////////////////////////////////////////////////////////////
			// <AIFLeverageArticle24-2>

			// (281) <AllCounterpartyCollateralRehypothecationFlag>
			if (ReportUtilities
					.searchData(reportDatas,
							"AllCounterpartyCollateralRehypothecationFlag",
							"281", null) != null)
				complexAIFLeverageArticle242Type
						.setAllCounterpartyCollateralRehypothecationFlag(Boolean.parseBoolean(ReportUtilities
								.searchData(
										reportDatas,
										"AllCounterpartyCollateralRehypothecationFlag",
										"281", null)));

			// (282) <AllCounterpartyCollateralRehypothecatedRate>
			if (ReportUtilities.searchData(reportDatas,
					"AllCounterpartyCollateralRehypothecatedRate", "282", null) != null)
				complexAIFLeverageArticle242Type
						.setAllCounterpartyCollateralRehypothecatedRate(new BigDecimal(
								ReportUtilities
										.searchData(
												reportDatas,
												"AllCounterpartyCollateralRehypothecatedRate",
												"282", null)));

			// /////////////////////////////////////////////////////////////////
			// <SecuritiesCashBorrowing>

			// (283) <UnsecuredBorrowingAmount>
			if (ReportUtilities.searchData(reportDatas,
					"UnsecuredBorrowingAmount", "283", null) != null)
				complexSecuritiesCashBorrowingType
						.setUnsecuredBorrowingAmount(new BigInteger(
								ReportUtilities
										.searchData(reportDatas,
												"UnsecuredBorrowingAmount",
												"283", null)));

			// (284) <SecuredBorrowingPrimeBrokerageAmount>
			if (ReportUtilities.searchData(reportDatas,
					"SecuredBorrowingPrimeBrokerageAmount", "284", null) != null)
				complexSecuritiesCashBorrowingType
						.setSecuredBorrowingPrimeBrokerageAmount(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"SecuredBorrowingPrimeBrokerageAmount",
										"284", null)));

			// (285) <SecuredBorrowingReverseRepoAmount>
			if (ReportUtilities.searchData(reportDatas,
					"SecuredBorrowingReverseRepoAmount", "285", null) != null)
				complexSecuritiesCashBorrowingType
						.setSecuredBorrowingReverseRepoAmount(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"SecuredBorrowingReverseRepoAmount",
										"285", null)));

			// (286) <SecuredBorrowingOtherAmount>
			if (ReportUtilities.searchData(reportDatas,
					"SecuredBorrowingOtherAmount", "286", null) != null)
				complexSecuritiesCashBorrowingType
						.setSecuredBorrowingOtherAmount(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"SecuredBorrowingOtherAmount", "286",
										null)));

			complexAIFLeverageArticle242Type
					.setSecuritiesCashBorrowing(complexSecuritiesCashBorrowingType);

			// /////////////////////////////////////////////////////////////////
			// <FinancialInstrumentBorrowing>

			// (287) <ExchangedTradedDerivativesExposureValue>
			if (ReportUtilities.searchData(reportDatas,
					"ExchangedTradedDerivativesExposureValue", "287", null) != null)
				complexFinancialInstrumentBorrowingType
						.setExchangedTradedDerivativesExposureValue(new BigInteger(
								ReportUtilities
										.searchData(
												reportDatas,
												"ExchangedTradedDerivativesExposureValue",
												"287", null)));

			// (288) <OTCDerivativesAmount>
			if (ReportUtilities.searchData(reportDatas, "OTCDerivativesAmount",
					"288", null) != null)
				complexFinancialInstrumentBorrowingType
						.setExchangedTradedDerivativesExposureValue(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"OTCDerivativesAmount", "288", null)));

			// (289) <ShortPositionBorrowedSecuritiesValue>
			if (ReportUtilities.searchData(reportDatas,
					"ShortPositionBorrowedSecuritiesValue", "289", null) != null)
				complexAIFLeverageArticle242Type
						.setShortPositionBorrowedSecuritiesValue(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"ShortPositionBorrowedSecuritiesValue",
										"289", null)));

			complexAIFLeverageArticle242Type
					.setFinancialInstrumentBorrowing(complexFinancialInstrumentBorrowingType);

			// /////////////////////////////////////////////////////////////////
			// <ControlledStructure>

			// TODO:RT populate this class

			List<ComplexControlledStructureType> complexControlledStructureTypeList = complexControlledStructuresType
					.getControlledStructure();

			complexAIFLeverageArticle242Type
					.setControlledStructures(complexControlledStructuresType);

			// /////////////////////////////////////////////////////////////////
			// <LeverageAIF>

			// (294) <GrossMethodRate>
			if (ReportUtilities.searchData(reportDatas, "GrossMethodRate",
					"294", null) != null)
				complexLeverageAIFType.setGrossMethodRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas,
								"GrossMethodRate", "294", null)));

			// (295) <CommitmentMethodRate>
			if (ReportUtilities.searchData(reportDatas, "CommitmentMethodRate",
					"295", null) != null)
				complexLeverageAIFType.setCommitmentMethodRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas,
								"CommitmentMethodRate", "295", null)));

			complexAIFLeverageArticle242Type
					.setLeverageAIF(complexLeverageAIFType);

			// /////////////////////////////////////////////////////////////////
			// <Borrowing Source>

			// TODO:RT populate this class

			List<ComplexBorrowingSourceType> complexBorrowingSourceTypeList = complexAIFLeverageArticle244Type
					.getBorrowingSource();

			// /////////////////////////////////////////////////////////////////
			// <CancellationAIFMRecordInfo>

			// (303) <CancelledAIFNationalCode>
			if (ReportUtilities.searchData(reportDatas,
					"CancelledAIFNationalCode", "303", null) != null)
				complexCancellationAIFRecordInfoType
						.setCancelledAIFNationalCode(ReportUtilities
								.searchData(reportDatas,
										"CancelledAIFNationalCode", "303", null));

			// (304) <CancelledAIFMNationalCode>
			if (ReportUtilities.searchData(reportDatas,
					"CancelledAIFMNationalCode", "304", null) != null)
				complexCancellationAIFRecordInfoType
						.setCancelledAIFMNationalCode(ReportUtilities
								.searchData(reportDatas,
										"CancelledAIFMNationalCode", "304",
										null));

			// (305) <CancelledReportingPeriodType>
			if (ReportUtilities.searchData(reportDatas,
					"CancelledReportingPeriodType", "305", null) != null) {
				ReportingPeriodTypeType cancelledReportingPeriodTypeType = ReportingPeriodTypeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"CancelledReportingPeriodType", "305", null));
				complexCancellationAIFRecordInfoType
						.setCancelledReportingPeriodType(cancelledReportingPeriodTypeType);
			}

			// (306) <CancelledReportingPeriodYear>
			if (ReportUtilities.searchData(reportDatas,
					"CancelledReportingPeriodYear", "306", null) != null) {
				Date cancelledReportingPeriodYear = formatYear
						.parse(ReportUtilities.searchData(reportDatas,
								"CancelledReportingPeriodYear", "306", null));
				XMLGregorianCalendar cancelledReportingPeriodYearXML = XMLGregorianCalendarConverter
						.asXMLGregorianCalendarDate(cancelledReportingPeriodYear);
				complexCancellationAIFRecordInfoType
						.setCancelledReportingPeriodYear(cancelledReportingPeriodYearXML);
			}

			// (307) <CancelledRecordFlag>
			if (ReportUtilities.searchData(reportDatas, "CancelledRecordFlag",
					"307", null) != null) {
				CancelledRecordFlagType cancelledRecordFlagXML = CancelledRecordFlagType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"CancelledRecordFlag", "307", null));
				complexCancellationAIFRecordInfoType
						.setCancelledRecordFlag(cancelledRecordFlagXML);
			}

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

			// /////////////////////////////////////////////////////////////////
			// <AIFRecordInfo><AIFCompleteDescription>

			complexAIFCompleteDescriptionType
					.setAIFIndividualInfo(complexAIFIndividualInfoType);
			complexAIFCompleteDescriptionType
					.setAIFLeverageInfo(complexAIFLeverageInfoType);
			complexAIFCompleteDescriptionType
					.setAIFPrincipalInfo(complexAIFPrincipalInfoType);

			complexAIFRecordInfoType
					.setAIFCompleteDescription(complexAIFCompleteDescriptionType);

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

			// validateSchemaXSD(st.toString(), reportExecution,
			// aifXSDResource);

			return st.toString();

		} catch (JAXBException e) {
			e.printStackTrace();
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL", "Error in JAXB XML" + e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL", "Error when parsing XML: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL", "General error in XML process: " + e.getMessage());
		}

		return null;
	}

	/**
	 * Function generate a aifmXML from a reportExecution with XSD classes and
	 * validate it. Create reportErrores
	 * 
	 * @param reportExecution
	 * @return aifmXML string
	 */
	public String generateXMLAIFM(ReportExecution reportExecution) {

		System.out.println("DEBUG_" + "GeneratorXML: starting XML generation ");

		// all dataFields
		List<ReportData> reportDatas = new ArrayList<ReportData>(
				reportExecution.getReportDatas());

		for (ReportData reportData : reportDatas) {
			System.out.println(reportData.getReportField().getReportFieldName()
					+ "(" + reportData.getReportField().getReportFieldNum()
					+ "): " + reportData.getReportDataText());
		}

		// ///////////////////////////////////////////////////////////
		// TODO:RT ONLY STATUS = PENDING WILL CREATE XML REPORTS
		// if (aifmdReportResult.getAifmdReportResultStat().equals("PENDING")) {
		// }

		try {

			DateFormat formatDate = new SimpleDateFormat(
					ReportUtilities.datePattern);
			DateFormat formatYear = new SimpleDateFormat(
					ReportUtilities.yearPattern);
			DateFormat formatDateTime = new SimpleDateFormat(
					ReportUtilities.dateTimePattern);

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
			// <AifmReportingInfo>

			// (1) <ReportingMemberState>
			if (ReportUtilities.searchData(reportDatas, "ReportingMemberState",
					"1", null) != null)
				aifmReportingInfo.setReportingMemberState(ReportUtilities
						.searchData(reportDatas, "ReportingMemberState", "1",
								null));

			// (2) <Version>
			if (ReportUtilities.searchData(reportDatas, "Version", "2", null) != null)
				aifmReportingInfo.setVersion(ReportUtilities.searchData(
						reportDatas, "Version", "2", null));

			// (3) <CreationDateAndTime>
			if (ReportUtilities.searchData(reportDatas, "CreationDateAndTime",
					"3", null) != null) {
				Date creationDateAndTime = formatDateTime.parse(ReportUtilities
						.searchData(reportDatas, "CreationDateAndTime", "3",
								null));
				XMLGregorianCalendar creationDateAndTimeXML = XMLGregorianCalendarConverter
						.asXMLGregorianCalendarDateTime(creationDateAndTime);
				aifmReportingInfo
						.setCreationDateAndTime(creationDateAndTimeXML);
			}

			// /////////////////////////////////////////////////////////////////
			// <AIFMRecordInfo>

			// (4) <FilingType>
			if (ReportUtilities
					.searchData(reportDatas, "FilingType", "4", null) != null) {
				FilingTypeType filingTypeType = FilingTypeType
						.valueOf(ReportUtilities.searchData(reportDatas,
								"FilingType", "4", null));
				complexAIFMRecordInfoType.setFilingType(filingTypeType);
			}

			// (5) <AIFMContentType>
			if (ReportUtilities.searchData(reportDatas, "AIFMContentType", "5",
					null) != null)
				complexAIFMRecordInfoType.setAIFMContentType(ReportUtilities
						.searchData(reportDatas, "AIFMContentType", "5", null));

			// (6) <ReportingPeriodStartDate>
			if (ReportUtilities.searchData(reportDatas,
					"ReportingPeriodStartDate", "6", null) != null) {
				Date reportingPeriodStartDate = formatDate
						.parse(ReportUtilities.searchData(reportDatas,
								"ReportingPeriodStartDate", "6", null));
				XMLGregorianCalendar reportingPeriodStartDateXML = XMLGregorianCalendarConverter
						.asXMLGregorianCalendarDate(reportingPeriodStartDate);
				complexAIFMRecordInfoType
						.setReportingPeriodStartDate(reportingPeriodStartDateXML);
			}

			// (7) <ReportingPeriodEndDate>
			if (ReportUtilities.searchData(reportDatas,
					"ReportingPeriodEndDate", "7", null) != null) {
				Date reportingPerdiodEndDate = formatDate.parse(ReportUtilities
						.searchData(reportDatas, "ReportingPeriodEndDate", "7",
								null));
				XMLGregorianCalendar reportingPerdiodEndDateXML = XMLGregorianCalendarConverter
						.asXMLGregorianCalendarDate(reportingPerdiodEndDate);
				complexAIFMRecordInfoType
						.setReportingPeriodEndDate(reportingPerdiodEndDateXML);
			}

			// (8) <ReportingPeriodType>
			if (ReportUtilities.searchData(reportDatas, "ReportingPeriodType",
					"8", null) != null) {
				ReportingPeriodTypeType reportingPeriodTypeType = ReportingPeriodTypeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"ReportingPeriodType", "8", null));
				complexAIFMRecordInfoType
						.setReportingPeriodType(reportingPeriodTypeType);
			}

			// (9) <ReportingPeriodYear>
			if (ReportUtilities.searchData(reportDatas, "ReportingPeriodYear",
					"9", null) != null) {
				Date reportingPeriodYear = formatYear.parse(ReportUtilities
						.searchData(reportDatas, "ReportingPeriodYear", "9",
								null));
				XMLGregorianCalendar reportingPeriodYearXML = XMLGregorianCalendarConverter
						.asXMLGregorianCalendarDate(reportingPeriodYear);
				complexAIFMRecordInfoType
						.setReportingPeriodYear(reportingPeriodYearXML);
			}

			// (10) <AIFMReportingObligationChangeFrequencyCode>
			if (ReportUtilities.searchData(reportDatas,
					"AIFMReportingObligationChangeFrequencyCode", "10", null) != null) {
				ReportingObligationChangeFrequencyCodeType reportingObligationChangeFrequencyCode = ReportingObligationChangeFrequencyCodeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"AIFMReportingObligationChangeFrequencyCode",
								"10", null));
				complexAIFMRecordInfoType
						.setAIFMReportingObligationChangeFrequencyCode(reportingObligationChangeFrequencyCode);
			}

			// (11) <AIFMReportingObligationChangeContentsCode>
			if (ReportUtilities.searchData(reportDatas,
					"AIFMReportingObligationChangeContentsCode", "11", null) != null)
				complexAIFMRecordInfoType
						.setAIFMReportingObligationChangeContentsCode(new BigInteger(
								ReportUtilities
										.searchData(
												reportDatas,
												"AIFMReportingObligationChangeContentsCode",
												"11", null)));

			// (12) <AIFMReportingObligationChangeQuarter>
			if (ReportUtilities.searchData(reportDatas,
					"AIFMReportingObligationChangeQuarter", "12", null) != null) {

				ReportingObligationChangeQuarterType reportingObligationChangeQuarterType = ReportingObligationChangeQuarterType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"AIFMReportingObligationChangeQuarter", "12",
								null));
				complexAIFMRecordInfoType
						.setAIFMReportingObligationChangeQuarter(reportingObligationChangeQuarterType);
			}

			// (13) <LastReportingFlag>
			if (ReportUtilities.searchData(reportDatas, "LastReportingFlag",
					"13", null) != null)
				complexAIFMRecordInfoType.setLastReportingFlag(Boolean
						.parseBoolean(ReportUtilities.searchData(reportDatas,
								"LastReportingFlag", "13", null)));

			// <Assumptions>
			List<ComplexAssumptionType> complexAssumptionTypeList = complexAssumptionsType
					.getAssumption();
			List<Integer> assumptionCounts = new ArrayList<Integer>();
			// first store in List all dataBlock
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AssumptionDescription"))
					assumptionCounts.add(Integer.parseInt(reportData
							.getReportDataBlock()));
			}
			for (Integer i : assumptionCounts) {
				BigInteger questionNumber = new BigInteger("0");
				String assumptionDescription = "";
				// <Assumption>
				ComplexAssumptionType complexAssumptionType = objectFactoryAIFM
						.createComplexAssumptionType();

				// (14) <QuestionNumber>
				if (ReportUtilities.searchData(reportDatas, "QuestionNumber",
						"14", Integer.toString(i)) != null)
					complexAssumptionType
							.setQuestionNumber(new BigInteger(ReportUtilities
									.searchData(reportDatas, "QuestionNumber",
											"14", Integer.toString(i))));

				// (15) <AssumptionDescription>
				if (ReportUtilities.searchData(reportDatas,
						"AssumptionDescription", "15", Integer.toString(i)) != null)
					complexAssumptionType
							.setAssumptionDescription(ReportUtilities
									.searchData(reportDatas,
											"AssumptionDescription", "15",
											Integer.toString(i)));

				complexAssumptionTypeList.add(complexAssumptionType);
				System.out.println("assumption i " + i + " " + questionNumber
						+ " " + assumptionDescription);
			}
			complexAIFMRecordInfoType.setAssumptions(complexAssumptionsType);

			// (16) <AIFMReportingCode>
			if (ReportUtilities.searchData(reportDatas, "AIFMReportingCode",
					"16", null) != null)
				complexAIFMRecordInfoType.setAIFMReportingCode(new BigInteger(
						ReportUtilities.searchData(reportDatas,
								"AIFMReportingCode", "16", null)));

			// (17) <AIFMJurisdiction>
			if (ReportUtilities.searchData(reportDatas, "AIFMJurisdiction",
					"17", null) != null)
				complexAIFMRecordInfoType
						.setAIFMJurisdiction(ReportUtilities.searchData(
								reportDatas, "AIFMJurisdiction", "17", null));

			// (18) <AIFMNationalCode>
			if (ReportUtilities.searchData(reportDatas, "AIFMNationalCode",
					"18", null) != null)
				complexAIFMRecordInfoType
						.setAIFMNationalCode(ReportUtilities.searchData(
								reportDatas, "AIFMNationalCode", "18", null));

			// (19) <AIFMName>
			if (ReportUtilities.searchData(reportDatas, "AIFMName", "19", null) != null)
				complexAIFMRecordInfoType.setAIFMName(ReportUtilities
						.searchData(reportDatas, "AIFMName", "19", null));

			// (20) <AIFMEEAFlag>
			if (ReportUtilities.searchData(reportDatas, "AIFMEEAFlag", "20",
					null) != null)
				complexAIFMRecordInfoType.setAIFMEEAFlag(Boolean
						.parseBoolean(ReportUtilities.searchData(reportDatas,
								"AIFMEEAFlag", "20", null)));

			// (21) <AIFMNoReportingFlag>
			if (ReportUtilities.searchData(reportDatas, "AIFMNoReportingFlag",
					"21", null) != null)
				complexAIFMRecordInfoType.setAIFMNoReportingFlag(Boolean
						.parseBoolean(ReportUtilities.searchData(reportDatas,
								"AIFMNoReportingFlag", "21", null)));

			// /////////////////////////////////////////////////////////////////
			// <AIFMPrincipalMarkets>

			List<ComplexFivePrincipalMarketType> complexFivePrincipalMarketTypeList = complexAIFMPrincipalMarketsType
					.getAIFMFivePrincipalMarket();

			ComplexMarketIdentificationWithNOTType complexMarketIdentificationWithNOTType = new ComplexMarketIdentificationWithNOTType();

			for (int i = 1; i < 6; i++) {
				if (ReportUtilities.searchData(reportDatas, "Ranking", "26",
						Integer.toString(i)) == null) {
					continue;
				} else {

					ComplexFivePrincipalMarketType complexFivePrincipalMarketType = objectFactoryAIFM
							.createComplexFivePrincipalMarketType();

					// (26) <Ranking>
					if (ReportUtilities.searchData(reportDatas, "Ranking",
							"26", Integer.toString(i)) != null)
						complexFivePrincipalMarketType
								.setRanking(new BigInteger(ReportUtilities
										.searchData(reportDatas, "Ranking",
												"26", Integer.toString(i))));

					// (27) <MarketCodeType>
					if (ReportUtilities.searchData(reportDatas,
							"MarketCodeType", "27", Integer.toString(i)) != null) {
						MarketCodeTypeWithNOTType marketCodeTypeWithNOTType = MarketCodeTypeWithNOTType
								.fromValue(ReportUtilities.searchData(
										reportDatas, "MarketCodeType", "27",
										Integer.toString(i)));
						complexMarketIdentificationWithNOTType
								.setMarketCodeType(marketCodeTypeWithNOTType);

						// (28) <MarketCode>
						if (ReportUtilities.searchData(reportDatas,
								"MarketCode", "28", Integer.toString(i)) != null) {
							complexMarketIdentificationWithNOTType
									.setMarketCode(ReportUtilities.searchData(
											reportDatas, "MarketCode", "28",
											Integer.toString(i)));
						}

						complexFivePrincipalMarketType
								.setMarketIdentification(complexMarketIdentificationWithNOTType);
					}

					// (29) <AggregatedValueAmount>
					if (ReportUtilities.searchData(reportDatas,
							"AggregatedValueAmount", "29", Integer.toString(i)) != null)
						complexFivePrincipalMarketType
								.setAggregatedValueAmount(new BigInteger(
										ReportUtilities.searchData(reportDatas,
												"AggregatedValueAmount", "29",
												Integer.toString(i))));

					complexFivePrincipalMarketTypeList
							.add(complexFivePrincipalMarketType);
				}
			}

			// /////////////////////////////////////////////////////////////////
			// <AIFMPrincipalInstruments>

			List<ComplexPrincipalInstrumentType> complexPrincipalInstrumentTypeList = complexAIFMPrincipalInstrumentsType
					.getAIFMPrincipalInstrument();

			for (int i = 1; i < 6; i++) {
				if (ReportUtilities.searchData(reportDatas, "Ranking", "30",
						Integer.toString(i)) == null) {
					continue;
				} else {
					ComplexPrincipalInstrumentType complexPrincipalInstrumentType = objectFactoryAIFM
							.createComplexPrincipalInstrumentType();

					// (30) <Ranking>
					if (ReportUtilities.searchData(reportDatas, "Ranking",
							"30", Integer.toString(i)) != null)
						complexPrincipalInstrumentType
								.setRanking(new BigInteger(ReportUtilities
										.searchData(reportDatas, "Ranking",
												"30", Integer.toString(i))));
					// (31) <SubAssetType>
					if (ReportUtilities.searchData(reportDatas, "SubAssetType",
							"31", Integer.toString(i)) != null) {
						SubAssetTypeType subAssetTypeType = SubAssetTypeType
								.fromValue(ReportUtilities.searchData(
										reportDatas, "SubAssetType", "31",
										Integer.toString(i)));
						complexPrincipalInstrumentType
								.setSubAssetType(subAssetTypeType);
					}

					// (32) <AggregatedValueAmount>
					if (ReportUtilities.searchData(reportDatas,
							"AggregatedValueAmount", "32", Integer.toString(i)) != null)
						complexPrincipalInstrumentType
								.setAggregatedValueAmount(new BigInteger(
										ReportUtilities.searchData(reportDatas,
												"AggregatedValueAmount", "32",
												Integer.toString(i))));

					complexPrincipalInstrumentTypeList
							.add(complexPrincipalInstrumentType);
				}
			}

			// /////////////////////////////////////////////////////////////////
			// <AIFMCompleteDescription>

			// (22) <AIFMIdentifierLEI>
			if (ReportUtilities.searchData(reportDatas, "AIFMIdentifierLEI",
					"22", null) != null)
				complexAIFMIdentifierType.setAIFMIdentifierLEI(ReportUtilities
						.searchData(reportDatas, "AIFMIdentifierLEI", "22",
								null));

			// (23) <AIFMIdentifierBIC>
			if (ReportUtilities.searchData(reportDatas, "AIFMIdentifierBIC",
					"23", null) != null)
				complexAIFMIdentifierType.setAIFMIdentifierBIC(ReportUtilities
						.searchData(reportDatas, "AIFMIdentifierBIC", "23",
								null));

			// (24) <Old_ReportingMemberState>
			if (ReportUtilities.searchData(reportDatas,
					"Old_ReportingMemberState", "24", null) != null)
				complexAIFMNationalIdentifierType
						.setReportingMemberState(ReportUtilities.searchData(
								reportDatas, "Old_ReportingMemberState", "24",
								null));

			// (25) <Old_AIFMNationalCode>
			if (ReportUtilities.searchData(reportDatas, "Old_AIFMNationalCode",
					"25", null) != null)
				complexAIFMNationalIdentifierType
						.setAIFMNationalCode(ReportUtilities
								.searchData(reportDatas,
										"Old_AIFMNationalCode", "25", null));

			// (33) <AUMAmountInEuro>
			if (ReportUtilities.searchData(reportDatas, "AUMAmountInEuro",
					"33", null) != null)
				complexAIFMCompleteDescriptionType
						.setAUMAmountInEuro(new BigInteger(ReportUtilities
								.searchData(reportDatas, "AUMAmountInEuro",
										"33", null)));

			// (34) <AUMAmountInBaseCurrency>
			if (ReportUtilities.searchData(reportDatas,
					"AUMAmountInBaseCurrency", "34", null) != null)
				complexBaseCurrencyDescriptionType
						.setAUMAmountInBaseCurrency(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"AUMAmountInBaseCurrency", "34", null)));

			// (35) <BaseCurrency>
			if (ReportUtilities.searchData(reportDatas, "BaseCurrency", "35",
					null) != null)
				complexBaseCurrencyDescriptionType
						.setBaseCurrency(ReportUtilities.searchData(
								reportDatas, "BaseCurrency", "35", null));

			// (36) <FXEURReferenceRateType>
			if (ReportUtilities.searchData(reportDatas,
					"FXEURReferenceRateType", "36", null) != null) {
				FXEURReferenceRateTypeType fxEURReferenceRateTypeType = FXEURReferenceRateTypeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"FXEURReferenceRateType", "36", null));
				complexBaseCurrencyDescriptionType
						.setFXEURReferenceRateType(fxEURReferenceRateTypeType);
			}

			// (37) <FXEURRate>
			if (ReportUtilities
					.searchData(reportDatas, "FXEURRate", "37", null) != null)
				complexBaseCurrencyDescriptionType.setFXEURRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "FXEURRate",
								"37", null)));

			// (38) <FXEUROtherReferenceRateDescription>
			if (ReportUtilities.searchData(reportDatas,
					"FXEUROtherReferenceRateDescription", "38", null) != null)
				complexBaseCurrencyDescriptionType
						.setFXEUROtherReferenceRateDescription(ReportUtilities
								.searchData(reportDatas,
										"FXEUROtherReferenceRateDescription",
										"38", null));

			if (ReportUtilities.searchData(reportDatas,
					"Old_ReportingMemberState", "24", null) != null
					&& ReportUtilities.searchData(reportDatas,
							"Old_AIFMNationalCode", "25", null) != null)
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

			complexAIFMRecordInfoType
					.setAIFMCompleteDescription(complexAIFMCompleteDescriptionType);

			// /////////////////////////////////////////////////////////////////
			// <CancellationAIFMRecordInfo>

			// (39) <CancelledAIFMNationalCode>
			if (ReportUtilities.searchData(reportDatas,
					"CancelledAIFMNationalCode", "39", null) != null)
				complexCancellationAIFMRecordInfoType
						.setCancelledAIFMNationalCode(ReportUtilities
								.searchData(reportDatas,
										"CancelledAIFMNationalCode", "39", null));

			// (40) <CancelledReportingPeriodType>
			if (ReportUtilities.searchData(reportDatas,
					"CancelledReportingPeriodType", "40", null) != null) {
				ReportingPeriodTypeType cancelledReportingPeriodTypeType = ReportingPeriodTypeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"CancelledReportingPeriodType", "40", null));
				complexCancellationAIFMRecordInfoType
						.setCancelledReportingPeriodType(cancelledReportingPeriodTypeType);
			}

			// (41) <CancelledReportingPeriodYear>
			if (ReportUtilities.searchData(reportDatas,
					"CancelledReportingPeriodYear", "41", null) != null) {
				Date cancelledReportingPeriodYear = formatYear
						.parse(ReportUtilities.searchData(reportDatas,
								"CancelledReportingPeriodYear", "41", null));
				XMLGregorianCalendar cancelledReportingPeriodYearXML = XMLGregorianCalendarConverter
						.asXMLGregorianCalendarDate(cancelledReportingPeriodYear);
				complexCancellationAIFMRecordInfoType
						.setCancelledReportingPeriodYear(cancelledReportingPeriodYearXML);
			}

			// (42) <CancelledRecordFlag>
			if (ReportUtilities.searchData(reportDatas, "CancelledRecordFlag",
					"42", null) != null) {
				CancelledRecordFlagType cancelledRecordFlagXML = CancelledRecordFlagType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"CancelledRecordFlag", "42", null));
				complexCancellationAIFMRecordInfoType
						.setCancelledRecordFlag(cancelledRecordFlagXML);
			}

			// /////////////////////////////////////////////////////////////////
			// create list of root elements:
			// AIFMRecordInfo and CancellationAIFMRecordInfo
			List<Object> listAIFMReporting = aifmReportingInfo
					.getCancellationAIFMRecordInfoOrAIFMRecordInfo();
			listAIFMReporting.add(complexAIFMRecordInfoType);
			listAIFMReporting.add(complexCancellationAIFMRecordInfoType);

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

			// // need a styler.xsl to transform XML to HTML
			// StringReader reader = new StringReader(st.toString());
			// StringWriter writer = new StringWriter();
			// TransformerFactory tFactory = TransformerFactory.newInstance();
			// Transformer transformer = tFactory.newTransformer(
			// new javax.xml.transform.stream.StreamSource("styler.xsl"));
			// transformer.transform(
			// new javax.xml.transform.stream.StreamSource(reader),
			// new javax.xml.transform.stream.StreamResult(writer));
			// String result = writer.toString();
			// return result;

		} catch (JAXBException e) {
			e.printStackTrace();
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL", "Error in JAXB XML" + e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL", "Error when parsing XML: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL", "General error in XML process: " + e.getMessage());
		}

		return null;

	}

	/**
	 * Validate a aifmdXML string with his XSD schema, create reportErrors in
	 * reportExecution. Return true if XSD is valid, false otherwise
	 * 
	 * @param aifmdXML
	 * @param reportExecution
	 * @param xsdResource
	 * @return boolean
	 */
	public boolean validateSchemaXSD(String aifmdXML,
			ReportExecution reportExecution, String xsdResource) {

		boolean result = false;

		// http://stackoverflow.com/questions/15732/whats-the-best-way-to-validate-an-xml-file-against-an-xsd-file

		Source xmlFile = new StreamSource(new StringReader(aifmdXML));

		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File schemaFile = new File(classLoader.getResource(xsdResource)
					.getFile());

			SchemaFactory schemaFactory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();

			validator.validate(xmlFile);

			System.out.println("DEBUG_" + "CREATION - XML is valid.");
			result = true;

			ReportingErrorManager.disableReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL");
			ReportingErrorManager.disableReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"XML Incomplete");

		} catch (SAXException e) {
			System.out.println("DEBUG_" + "CREATION - XML NOT is valid: "
					+ e.getLocalizedMessage());
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"XML Incomplete", "Validating process detect some issues: "
							+ e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL", "Error validating XML " + e.getLocalizedMessage());
		}

		return result;
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
	// * <MarketIdentification> <MarketCodeType>XX</MarketCodeType>
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
