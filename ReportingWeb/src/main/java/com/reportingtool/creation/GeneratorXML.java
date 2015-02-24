package com.reportingtool.creation;

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
import com.reportingtool.xml.CancelledRecordFlagType;
import com.reportingtool.xml.ComplexAIFMCompleteDescriptionType;
import com.reportingtool.xml.ComplexAIFMIdentifierType;
import com.reportingtool.xml.ComplexAIFMPrincipalInstrumentsType;
import com.reportingtool.xml.ComplexAIFMPrincipalMarketsType;
import com.reportingtool.xml.ComplexAIFMRecordInfoType;
import com.reportingtool.xml.ComplexAssumptionsType;
import com.reportingtool.xml.ComplexBaseCurrencyDescriptionType;
import com.reportingtool.xml.ComplexCancellationAIFMRecordInfoType;
import com.reportingtool.xml.ComplexFivePrincipalMarketType;
import com.reportingtool.xml.ComplexMarketIdentificationWithNOTType;
import com.reportingtool.xml.ComplexPrincipalInstrumentType;
import com.reportingtool.xml.FilingTypeType;
import com.reportingtool.xml.MarketCodeTypeWithNOTType;
import com.reportingtool.xml.ObjectFactoryAIFM;
import com.reportingtool.xml.ReportingObligationChangeFrequencyCodeType;
import com.reportingtool.xml.ReportingObligationChangeQuarterType;
import com.reportingtool.xml.ReportingPeriodTypeType;
import com.reportingtool.xml.SubAssetTypeType;

public class GeneratorXML {

	private ApplicationContext applicationContext;

	public GeneratorXML(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void generateXML(ReportExecution reportExecution) {
		ReportCatalog reportCatalog = reportExecution.getReportCatalog();

		System.out.println("DEBUG_" + "GeneratorXML: starting XML with report "
				+ reportExecution.getReportCatalog().getReportLevel() + " "
				+ reportExecution.getReportPeriodType() + " "
				+ reportExecution.getReportPeriodYear() + " "
				+ reportExecution.getReportCatalog().getReportCatalogName());

		if (reportCatalog.getReportLevel().contains("FUND")) {

			// generate AIF report
			generateXMLAIF(reportExecution);

		}

		if (reportCatalog.getReportLevel().contains("COMPANY")) {

			// generate AIFMD report
			generateXMLAIFMD(reportExecution);

		}
	}

	public void generateXMLAIF(ReportExecution reportExecution) {

	}

	public void generateXMLAIFMD(ReportExecution reportExecution) {

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

			DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat formatYear = new SimpleDateFormat("yyyy");
			DateFormat formatDateTime = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");

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
			ComplexAIFMPrincipalInstrumentsType complexAIFMPrincipalInstrumentsType = objectFactoryAIFM
					.createComplexAIFMPrincipalInstrumentsType();
			ComplexAIFMPrincipalMarketsType complexAIFMPrincipalMarketsType = objectFactoryAIFM
					.createComplexAIFMPrincipalMarketsType();

			ComplexAssumptionsType complexAssumptionsType = objectFactoryAIFM
					.createComplexAssumptionsType();

			BigInteger AUMAmountInEuro = new BigInteger("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AUMAmountInEuro"))
					AUMAmountInEuro = new BigInteger(
							reportData.getReportDataText());
			}

			BigInteger AUMAmountInBaseCurrency = new BigInteger("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AUMAmountInBaseCurrency"))
					AUMAmountInBaseCurrency = new BigInteger(
							reportData.getReportDataText());
			}

			complexBaseCurrencyDescriptionType
					.setAUMAmountInBaseCurrency(AUMAmountInBaseCurrency);

			BigInteger aifmReportingCode = new BigInteger("0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMReportingCode"))
					aifmReportingCode = new BigInteger(
							reportData.getReportDataText());
			}

			BigInteger aifmReportingObligationChangeContentsCode = new BigInteger(
					"0");
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMReportingObligationChangeContentsCode"))
					aifmReportingObligationChangeContentsCode = new BigInteger(
							reportData.getReportDataText());
			}

			String reportingObligationChangeQuarter = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMReportingObligationChangeQuarter"))
					reportingObligationChangeQuarter = reportData
							.getReportDataText();
			}
			ReportingObligationChangeQuarterType reportingObligationChangeQuarterType = ReportingObligationChangeQuarterType
					.fromValue(reportingObligationChangeQuarter);

			String reportingObligationChangeFrequency = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMReportingObligationChangeFrequencyCode"))
					reportingObligationChangeFrequency = reportData
							.getReportDataText();
			}
			ReportingObligationChangeFrequencyCodeType reportingObligationChangeFrequencyCode = ReportingObligationChangeFrequencyCodeType
					.fromValue(reportingObligationChangeFrequency);

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

					if (reportData.getReportField().getReportFieldName()
							.equals("MarketCodeType")
							&& Integer
									.parseInt(reportData.getReportDataBlock()) == i)
						marketCodeType = reportData.getReportDataText();

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

			// <AIFMPrincipalInstruments>

			// complexAIFMPrincipalInstrumentsType
			List<ComplexPrincipalInstrumentType> complexPrincipalInstrumentTypeList = complexAIFMPrincipalInstrumentsType
					.getAIFMPrincipalInstrument();

			BigInteger aggregatedValueAmountInstrument = new BigInteger("0");
			BigInteger rankingInstrument = new BigInteger("0");
			String subAssetType = "";
			Boolean rankingInstrumentFlag = false;
			for (int i = 1; i < 6; i++) {
				for (ReportData reportData : reportDatas) {
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

			// <AIFMCompleteDescription>
			complexAIFMCompleteDescriptionType
					.setAIFMIdentifier(complexAIFMIdentifierType);
			complexAIFMCompleteDescriptionType
					.setAIFMBaseCurrencyDescription(complexBaseCurrencyDescriptionType);
			complexAIFMCompleteDescriptionType
					.setAIFMPrincipalInstruments(complexAIFMPrincipalInstrumentsType);
			complexAIFMCompleteDescriptionType
					.setAIFMPrincipalMarkets(complexAIFMPrincipalMarketsType);
			complexAIFMCompleteDescriptionType
					.setAUMAmountInEuro(AUMAmountInEuro);

			String filingType = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("FilingType"))
					filingType = reportData.getReportDataText();
			}
			FilingTypeType filingTypeType = FilingTypeType.valueOf(filingType);

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

			String reportingPeriodType = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("ReportingPeriodType"))
					reportingPeriodType = reportData.getReportDataText();
			}
			ReportingPeriodTypeType reportingPeriodTypeType = ReportingPeriodTypeType
					.fromValue(reportingPeriodType);

			// <AIFMRecordInfo>
			complexAIFMRecordInfoType
					.setAIFMCompleteDescription(complexAIFMCompleteDescriptionType);

			String aifmContentType = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMContentType"))
					aifmContentType = reportData.getReportDataText();
			}
			complexAIFMRecordInfoType.setAIFMContentType(aifmContentType);

			complexAIFMRecordInfoType.setAIFMEEAFlag(false);

			String aifmJurisdiction = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMJurisdiction"))
					aifmJurisdiction = reportData.getReportDataText();
			}
			complexAIFMRecordInfoType.setAIFMJurisdiction(aifmJurisdiction);

			String aifmName = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMName"))
					aifmName = reportData.getReportDataText();
			}
			complexAIFMRecordInfoType.setAIFMName(aifmName);

			String aifmNationalCode = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFMNationalCode"))
					aifmNationalCode = reportData.getReportDataText();
			}
			complexAIFMRecordInfoType.setAIFMNationalCode(aifmNationalCode);

			complexAIFMRecordInfoType.setAIFMNoReportingFlag(false);
			complexAIFMRecordInfoType.setAIFMReportingCode(aifmReportingCode);
			complexAIFMRecordInfoType
					.setAIFMReportingObligationChangeContentsCode(aifmReportingObligationChangeContentsCode);
			complexAIFMRecordInfoType
					.setAIFMReportingObligationChangeQuarter(reportingObligationChangeQuarterType);
			complexAIFMRecordInfoType
					.setAIFMReportingObligationChangeFrequencyCode(reportingObligationChangeFrequencyCode);
			complexAIFMRecordInfoType.setAssumptions(complexAssumptionsType);
			complexAIFMRecordInfoType.setFilingType(filingTypeType);
			complexAIFMRecordInfoType.setLastReportingFlag(false);
			complexAIFMRecordInfoType
					.setReportingPeriodEndDate(reportingPerdiodEndDateXML);
			complexAIFMRecordInfoType
					.setReportingPeriodStartDate(reportingPeriodStartDateXML);
			complexAIFMRecordInfoType
					.setReportingPeriodType(reportingPeriodTypeType);
			complexAIFMRecordInfoType
					.setReportingPeriodYear(reportingPeriodYearXML);

			// <AifmReportingInfo> values
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

			String reportingMemberState = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("ReportingMemberState")
						&& reportData.getReportField().getReportFieldParent()
								.getReportFieldName().equals("AIFMReportingInfo"))
					reportingMemberState = reportData.getReportDataText();
			}
			aifmReportingInfo.setReportingMemberState(reportingMemberState);
			
			String version = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("Version"))
					version = reportData.getReportDataText();
			}
			aifmReportingInfo.setVersion(version);

			// <CancellationAIFMRecordInfo>
			String cancelledReportingPerioYearString = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("CancelledReportingPeriodYear"))
					cancelledReportingPerioYearString = reportData
							.getReportDataText();
			}
			Date cancelledReportingPerioYear = formatYear
					.parse(cancelledReportingPerioYearString);

			XMLGregorianCalendar cancelledReportingPerioYearXML = XMLGregorianCalendarConverter
					.asXMLGregorianCalendarDate(cancelledReportingPerioYear);

			String cancelledReportingPeriodType = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("CancelledReportingPeriodType"))
					cancelledReportingPeriodType = reportData
							.getReportDataText();
			}
			ReportingPeriodTypeType cancelledReportingPeriodTypeType = ReportingPeriodTypeType
					.fromValue(cancelledReportingPeriodType);

			String cancelledRecordFlag = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("CancelledRecordFlag"))
					cancelledRecordFlag = reportData.getReportDataText();
			}
			CancelledRecordFlagType cancelledRecordFlagXML = CancelledRecordFlagType
					.fromValue(cancelledRecordFlag);

			String cancelledAIFMNationalCode = "";
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("CancelledAIFMNationalCode"))
					cancelledAIFMNationalCode = reportData.getReportDataText();
			}
			complexCancellationAIFMRecordInfoType
					.setCancelledAIFMNationalCode(cancelledAIFMNationalCode);
			complexCancellationAIFMRecordInfoType
					.setCancelledRecordFlag(cancelledRecordFlagXML);
			complexCancellationAIFMRecordInfoType
					.setCancelledReportingPeriodType(cancelledReportingPeriodTypeType);
			complexCancellationAIFMRecordInfoType
					.setCancelledReportingPeriodYear(cancelledReportingPerioYearXML);

			// create list of root elements: AIFMRecordInfo and
			// CancellationAIFMRecordInfo
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

		} catch (Exception e) {
			e.printStackTrace();
			ReportingErrorManager.createReportError(applicationContext,
					"CREATION", reportExecution, "FAIL",
					"Fail when parsing XML");
		}

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
