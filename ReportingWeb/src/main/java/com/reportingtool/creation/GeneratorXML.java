package com.reportingtool.creation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;
import com.reportingtool.utilities.ReportingErrorManager;
import com.reportingtool.xml.AIFMReportingInfo;
import com.reportingtool.xml.ComplexAIFMCompleteDescriptionType;
import com.reportingtool.xml.ComplexAIFMIdentifierType;
import com.reportingtool.xml.ComplexAIFMPrincipalInstrumentsType;
import com.reportingtool.xml.ComplexAIFMPrincipalMarketsType;
import com.reportingtool.xml.ComplexAIFMRecordInfoType;
import com.reportingtool.xml.ComplexBaseCurrencyDescriptionType;
import com.reportingtool.xml.ObjectFactoryAIFM;

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

		ReportCatalog reportCatalog = reportExecution.getReportCatalog();

		List<ReportField> reportFields = new ArrayList<ReportField>(
				reportCatalog.getReportFields());

		// all dataFields
		List<ReportData> reportDatas = new ArrayList<ReportData>(
				reportExecution.getReportDatas());

		// ///////////////////////////////////////////////////////////
		// ONLY STATUS = PENDING WILL CREATE XML REPORTS
		// if (aifmdReportResult.getAifmdReportResultStat().equals("PENDING")) {
		// }

		try {

			// http://blog.sanaulla.info/2010/08/29/using-jaxb-to-generate-xml-from-the-java-xsd/
			
			ObjectFactoryAIFM objectFactoryAIFM = new ObjectFactoryAIFM();
			
			AIFMReportingInfo aifmReportingInfo = objectFactoryAIFM.createAIFMReportingInfo();
			
			ComplexAIFMRecordInfoType complexAIFMRecordInfoType = objectFactoryAIFM.createComplexAIFMRecordInfoType();
			ComplexAIFMCompleteDescriptionType complexAIFMCompleteDescriptionType = objectFactoryAIFM.createComplexAIFMCompleteDescriptionType();
			
			
			ComplexBaseCurrencyDescriptionType complexBaseCurrencyDescriptionType = objectFactoryAIFM.createComplexBaseCurrencyDescriptionType();
			ComplexAIFMIdentifierType complexAIFMIdentifierType = objectFactoryAIFM.createComplexAIFMIdentifierType();
			ComplexAIFMPrincipalInstrumentsType complexAIFMPrincipalInstrumentsType = objectFactoryAIFM.createComplexAIFMPrincipalInstrumentsType();
			ComplexAIFMPrincipalMarketsType complexAIFMPrincipalMarketsType = objectFactoryAIFM.createComplexAIFMPrincipalMarketsType();
			BigInteger AUMAmountInEuro = new BigInteger("0");
			
			
			//
			BigInteger AUMAmountInBaseCurrency = new BigInteger("0");
			
			complexBaseCurrencyDescriptionType.setAUMAmountInBaseCurrency(AUMAmountInBaseCurrency);
			
			
			
			
			// <AIFMCompleteDescription>
			complexAIFMCompleteDescriptionType.setAIFMIdentifier(complexAIFMIdentifierType);
			complexAIFMCompleteDescriptionType.setAIFMBaseCurrencyDescription(complexBaseCurrencyDescriptionType);
			complexAIFMCompleteDescriptionType.setAIFMPrincipalInstruments(complexAIFMPrincipalInstrumentsType);
			complexAIFMCompleteDescriptionType.setAIFMPrincipalMarkets(complexAIFMPrincipalMarketsType);
			complexAIFMCompleteDescriptionType.setAUMAmountInEuro(AUMAmountInEuro);
			
			// <AIFMRecordInfo>
			complexAIFMRecordInfoType.setAIFMCompleteDescription(complexAIFMCompleteDescriptionType);
			complexAIFMRecordInfoType.setAIFMContentType("");
			complexAIFMRecordInfoType.setAIFMEEAFlag(false);
			complexAIFMRecordInfoType.setAIFMJurisdiction("");
			complexAIFMRecordInfoType.setAIFMName("");
			
			
			
//			UserT user = factory.createUserT();
//			user.setUserName("Sanaulla");
//			ItemT item = factory.createItemT();
//			item.setItemName("Seagate External HDD");
//			item.setPurchasedOn("August 24, 2010");
//			item.setAmount(new BigDecimal("6776.5"));
//
//			ItemListT itemList = factory.createItemListT();
//			itemList.getItem().add(item);
//
//			ExpenseT expense = factory.createExpenseT();
//			expense.setUser(user);
//			expense.setItems(itemList);
			
//			JAXBContext context = JAXBContext.newInstance("generated");
//			JAXBElement<ExpenseT> element = factory
//					.createExpenseReport(expense);
//			Marshaller marshaller = context.createMarshaller();
//			marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
//			marshaller.marshal(element, System.out);

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
	// HashMap<String, String> reportMap = new HashMap<String, String>();
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
