package com.reportingtool.creation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.entity.reportingtool.ReportCatalog;
import com.entity.reportingtool.ReportData;
import com.entity.reportingtool.ReportExecution;
import com.entity.reportingtool.ReportField;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GeneratorXML {

	public void generateXML(ReportExecution reportExecution) {
		ReportCatalog reportCatalog = reportExecution.getReportCatalog();

		System.out.println("DEBUG_" + "GeneratorXML: starting XML with report "
				+ reportExecution.getReportCatalog().getReportLevel() + " "
				+ reportExecution.getReportPeriodType() + " "
				+ reportExecution.getReportPeriodYear() + " "
				+ reportExecution.getReportCatalog().getReportCatalogName()
				);

		if (reportCatalog.getReportLevel().contains("FUND")) {

			// generate AIF report with aifmdDatas
			generateXMLAIF(reportExecution);

		}

		if (reportCatalog.getReportLevel().contains("COMPANY")) {

			// generate AIFMD report with aifmdDatas
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

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			/*
			 * XML SCHEMA <AIFMReportingInfo
			 * CreationDateAndTime="2014-01-26T15:55:02" Version="1.2"
			 * ReportingMemberState="GB"
			 * xsi:noNamespaceSchemaLocation="AIFMD_DATMAN_V1.2.xsd"
			 * xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
			 * <AIFMRecordInfo> ... </AIFMRecordInfo>
			 * <CancellationAIFMRecordInfo> ... </CancellationAIFMRecordInfo>
			 * </AIFMReportingInfo>
			 */

			HashMap<String, String> reportMap = new HashMap<String, String>();

			for (ReportData reportData : reportDatas) {
				// this hashmap contain the content and <name> of every field
				reportMap.put(reportData.getReportField().getReportFieldName(),
						reportData.getReportDataText());
			}

			// http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("AIFMReportingInfo");
			doc.appendChild(rootElement);

			Element recordInfo = doc.createElement("AIFMRecordInfo");
			rootElement.appendChild(recordInfo);

			// first block of AIFMRecordInfo
			/*
			 * <FilingType>INIT</FilingType>
			 * <AIFMContentType>2</AIFMContentType>
			 * <ReportingPeriodStartDate>2014-10-01</ReportingPeriodStartDate>
			 * <ReportingPeriodEndDate>2014-12-31</ReportingPeriodEndDate>
			 * <ReportingPeriodType>Q4</ReportingPeriodType>
			 * <ReportingPeriodYear>2014</ReportingPeriodYear>
			 * <AIFMReportingObligationChangeFrequencyCode
			 * >QH</AIFMReportingObligationChangeFrequencyCode>
			 * <AIFMReportingObligationChangeQuarter
			 * >Q4</AIFMReportingObligationChangeQuarter>
			 * <LastReportingFlag>false</LastReportingFlag>
			 * <AIFMReportingCode>4</AIFMReportingCode>
			 * <AIFMJurisdiction>GB</AIFMJurisdiction>
			 * <AIFMNationalCode>474286</AIFMNationalCode> <AIFMName>AIFM
			 * 1</AIFMName> <AIFMEEAFlag>true</AIFMEEAFlag>
			 * <AIFMNoReportingFlag>false</AIFMNoReportingFlag>
			 */

			Element elementFilingType = doc.createElement("FilingType");
			elementFilingType.appendChild(doc.createTextNode(reportMap
					.get("FilingType")));
			recordInfo.appendChild(elementFilingType);

			Element elementAIFMContentType = doc
					.createElement("AIFMContentType");
			elementAIFMContentType.appendChild(doc.createTextNode(reportMap
					.get("AIFMContentType")));
			recordInfo.appendChild(elementAIFMContentType);

			Element elementReportingPeriodStartDate = doc
					.createElement("ReportingPeriodStartDate");
			elementReportingPeriodStartDate.appendChild(doc
					.createTextNode(reportMap.get("ReportingPeriodStartDate")));
			recordInfo.appendChild(elementReportingPeriodStartDate);

			Element elementReportingPeriodEndDate = doc
					.createElement("ReportingPeriodEndDate");
			elementReportingPeriodEndDate.appendChild(doc
					.createTextNode(reportMap.get("ReportingPeriodEndDate")));
			recordInfo.appendChild(elementReportingPeriodEndDate);

			Element elementReportingPeriodType = doc
					.createElement("ReportingPeriodType");
			elementReportingPeriodType.appendChild(doc.createTextNode(reportMap
					.get("ReportingPeriodType")));
			recordInfo.appendChild(elementReportingPeriodType);

			Element elementReportingPeriodYear = doc
					.createElement("ReportingPeriodYear");
			elementReportingPeriodYear.appendChild(doc.createTextNode(reportMap
					.get("ReportingPeriodYear")));
			recordInfo.appendChild(elementReportingPeriodYear);

			Element elementAIFMReportingObligationChangeFrequencyCode = doc
					.createElement("AIFMReportingObligationChangeFrequencyCode");
			elementAIFMReportingObligationChangeFrequencyCode
					.appendChild(doc.createTextNode(reportMap
							.get("AIFMReportingObligationChangeFrequencyCode")));
			recordInfo
					.appendChild(elementAIFMReportingObligationChangeFrequencyCode);

			Element elementAIFMReportingObligationChangeQuarter = doc
					.createElement("AIFMReportingObligationChangeQuarter");
			elementAIFMReportingObligationChangeQuarter.appendChild(doc
					.createTextNode(reportMap
							.get("AIFMReportingObligationChangeQuarter")));
			recordInfo.appendChild(elementAIFMReportingObligationChangeQuarter);

			Element elementLastReportingFlag = doc
					.createElement("LastReportingFlag");
			elementLastReportingFlag.appendChild(doc.createTextNode(reportMap
					.get("LastReportingFlag")));
			recordInfo.appendChild(elementLastReportingFlag);

			Element elementAIFMReportingCode = doc
					.createElement("AIFMReportingCode");
			elementAIFMReportingCode.appendChild(doc.createTextNode(reportMap
					.get("AIFMReportingCode")));
			recordInfo.appendChild(elementAIFMReportingCode);

			Element elementAIFMJurisdiction = doc
					.createElement("AIFMJurisdiction");
			elementAIFMJurisdiction.appendChild(doc.createTextNode(reportMap
					.get("AIFMJurisdiction")));
			recordInfo.appendChild(elementAIFMJurisdiction);

			Element elementAIFMNationalCode = doc
					.createElement("AIFMNationalCode");
			elementAIFMNationalCode.appendChild(doc.createTextNode(reportMap
					.get("AIFMNationalCode")));
			recordInfo.appendChild(elementAIFMNationalCode);

			Element elementAIFMName = doc.createElement("AIFMName");
			elementAIFMName.appendChild(doc.createTextNode(reportMap
					.get("AIFMName")));
			recordInfo.appendChild(elementAIFMName);

			Element elemenAIFMEEAFlag = doc.createElement("AIFMEEAFlag");
			elemenAIFMEEAFlag.appendChild(doc.createTextNode(reportMap
					.get("AIFMEEAFlag")));
			recordInfo.appendChild(elemenAIFMEEAFlag);

			Element elementAIFMNoReportingFlag = doc
					.createElement("AIFMNoReportingFlag");
			elementAIFMNoReportingFlag.appendChild(doc.createTextNode(reportMap
					.get("AIFMNoReportingFlag")));
			recordInfo.appendChild(elementAIFMNoReportingFlag);

			// second part, AIFMCompleteDescription has market and instruments
			// rankings
			/*
			 * <AIFMCompleteDescription> <AIFMIdentifier>
			 * <AIFMIdentifierLEI>969500AA77L4ZJXJ0T02</AIFMIdentifierLEI>
			 * <AIFMIdentifierBIC>TESTGB21XXX</AIFMIdentifierBIC>
			 * </AIFMIdentifier> <AIFMPrincipalMarkets>
			 * <AIFMFivePrincipalMarket> <Ranking>1</Ranking>
			 * <MarketIdentification> <MarketCodeType>XXX</MarketCodeType>
			 * </MarketIdentification>
			 * <AggregatedValueAmount>452000000</AggregatedValueAmount>
			 * </AIFMFivePrincipalMarket> .. </AIFMPrincipalMarkets>
			 * <AIFMPrincipalInstruments> <AIFMPrincipalInstrument>
			 * <Ranking>1</Ranking> <SubAssetType>SEC_LEQ_OTHR</SubAssetType>
			 * <AggregatedValueAmount>248478000</AggregatedValueAmount>
			 * </AIFMPrincipalInstrument> ... </AIFMPrincipalInstruments>
			 * </AIFMCompleteDescription>
			 */

			
			Element elementAIFMCompleteDescription = doc
					.createElement("AIFMCompleteDescription");
			recordInfo.appendChild(elementAIFMCompleteDescription);
			
			Element elementAIFMIdentifier = doc
					.createElement("AIFMIdentifier");
			elementAIFMCompleteDescription.appendChild(elementAIFMIdentifier);
			
			Element elementAIFMIdentifierLEI = doc
					.createElement("AIFMIdentifierLEI");
			elementAIFMIdentifierLEI.appendChild(doc.createTextNode(reportMap
					.get("AIFMIdentifierLEI")));
			elementAIFMIdentifier.appendChild(elementAIFMIdentifierLEI);
			
			Element elementAIFMIdentifierBIC = doc
					.createElement("AIFMIdentifierBIC");
			elementAIFMIdentifierBIC.appendChild(doc.createTextNode(reportMap
					.get("AIFMIdentifierBIC")));
			elementAIFMIdentifier.appendChild(elementAIFMIdentifierBIC);
			
			Element elementAIFMPrincipalMarkets = doc
					.createElement("AIFMPrincipalMarkets");
			elementAIFMCompleteDescription.appendChild(elementAIFMPrincipalMarkets);
			
			// market ranking 1
			Element elementAIFMFivePrincipalMarket1 = doc
					.createElement("AIFMFivePrincipalMarket");
			elementAIFMPrincipalMarkets.appendChild(elementAIFMFivePrincipalMarket1);
			
			Element elementRanking1 = doc
					.createElement("Ranking");
			elementRanking1.appendChild(doc.createTextNode("1"));
			elementAIFMFivePrincipalMarket1.appendChild(elementRanking1);
			
			Element elementMarketIdentification1 = doc
					.createElement("MarketIdentification");
			elementAIFMFivePrincipalMarket1.appendChild(elementMarketIdentification1);
			
			Element elementMarketCodeType1 = doc
					.createElement("MarketCodeType");
			elementMarketCodeType1.appendChild(doc.createTextNode(reportMap
					.get("MarketCodeType1")));
			elementMarketIdentification1.appendChild(elementMarketCodeType1);
			
			Element elementMarketCode1 = doc
					.createElement("MarketCodeType");
			elementMarketCode1.appendChild(doc.createTextNode(reportMap
					.get("MarketCodeType1")));
			elementMarketIdentification1.appendChild(elementMarketCode1);
			
			Element elementAggregatedValueAmount1 = doc
					.createElement("AggregatedValueAmount");
			elementAggregatedValueAmount1.appendChild(doc.createTextNode(reportMap
					.get("MarketCodeType1")));
			elementAIFMFivePrincipalMarket1.appendChild(elementAggregatedValueAmount1);
			
			// market ranking 2
			Element elementAIFMFivePrincipalMarket2 = doc
					.createElement("AIFMFivePrincipalMarket");
			elementAIFMPrincipalMarkets.appendChild(elementAIFMFivePrincipalMarket2);
			
			Element elementRanking2 = doc
					.createElement("Ranking");
			elementRanking2.appendChild(doc.createTextNode("2"));
			elementAIFMFivePrincipalMarket2.appendChild(elementRanking2);
			
			Element elementMarketIdentification2 = doc
					.createElement("MarketIdentification");
			elementAIFMFivePrincipalMarket2.appendChild(elementMarketIdentification2);
			
			Element elementMarketCodeType2 = doc
					.createElement("MarketCodeType");
			elementMarketCodeType2.appendChild(doc.createTextNode(reportMap
					.get("MarketCodeType2")));
			elementMarketIdentification2.appendChild(elementMarketCodeType2);
			
			Element elementMarketCode2 = doc
					.createElement("MarketCodeType");
			elementMarketCode2.appendChild(doc.createTextNode(reportMap
					.get("MarketCodeType2")));
			elementMarketIdentification2.appendChild(elementMarketCode2);
			
			Element elementAggregatedValueAmount2 = doc
					.createElement("AggregatedValueAmount");
			elementAggregatedValueAmount2.appendChild(doc.createTextNode(reportMap
					.get("MarketCodeType2")));
			elementAIFMFivePrincipalMarket2.appendChild(elementAggregatedValueAmount2);
			
			
			
			
			
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			
			String path = "D:\\file.xml";
			//StreamResult result = new StreamResult(new File(path));

			// Output to console for testing
			StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			//System.out.println("DEBUG_" + "GeneratorXML: File saved " + path);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

	}
}