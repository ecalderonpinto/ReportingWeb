package com.reportingtool.creation;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;
import com.entities.utilities.hibernate.VersionAuditor;
import com.reportingtool.utilities.ReportUtilities;
import com.reportingtool.utilities.XMLGregorianCalendarConverter;
import com.reportingtool.xml.AIFMReportingInfo;
import com.reportingtool.xml.ComplexAIFMCompleteDescriptionType;
import com.reportingtool.xml.ComplexAIFMIdentifierType;
import com.reportingtool.xml.ComplexAIFMNationalIdentifierType;
import com.reportingtool.xml.ComplexAIFMPrincipalInstrumentsType;
import com.reportingtool.xml.ComplexAIFMPrincipalMarketsType;
import com.reportingtool.xml.ComplexAIFMRecordInfoType;
import com.reportingtool.xml.ComplexAssumptionType;
import com.reportingtool.xml.ComplexAssumptionsType;
import com.reportingtool.xml.ComplexBaseCurrencyDescriptionType;
import com.reportingtool.xml.ComplexCancellationAIFMRecordInfoType;
import com.reportingtool.xml.ComplexFivePrincipalMarketType;
import com.reportingtool.xml.ComplexMarketIdentificationWithNOTType;
import com.reportingtool.xml.ComplexPrincipalInstrumentType;

/**
 * Class to load a XML file and convert to reportExecution
 * 
 * @author alberto.olivan
 *
 */
public class LoadXML {

	private ApplicationContext applicationContext;

	public LoadXML(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	// http://java.dzone.com/articles/using-jaxb-generate-java

	/**
	 * Function receive a File and AIF reportExecution, read this File to add
	 * reportDatas from XML file
	 * 
	 * @param file
	 * @param reportExecution
	 * @return reportExecution with reportDatas from file
	 */
	public ReportExecution loadAIFFile(File file,
			ReportExecution reportExecution) {

		// TODO similar to AIFM

		return reportExecution;
	}

	/**
	 * Function receive a File and AIFM reportExecution, read this File to add
	 * reportDatas from XML file
	 * 
	 * @param file
	 * @param reportExecution
	 * @return reportExecution with reportDatas from file
	 */
	public ReportExecution loadAIFMFile(File file,
			ReportExecution reportExecution) throws Exception {

		// try {

		// 1. We need to create JAXContext instance
		JAXBContext jaxbContext = JAXBContext
				.newInstance(AIFMReportingInfo.class);

		// 2. Use JAXBContext instance to create the Unmarshaller.
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// 3. Use the Unmarshaller to unmarshal the XML document to get an
		// instance of JAXBElement.
		JAXBElement<AIFMReportingInfo> root = (JAXBElement<AIFMReportingInfo>) unmarshaller
				.unmarshal(new StreamSource(file), AIFMReportingInfo.class);

		// 4. Get the instance of the required JAXB Root Class from the
		AIFMReportingInfo aifmReportingInfo = root.getValue();

		List<Object> listAIFMReporting = aifmReportingInfo
				.getCancellationAIFMRecordInfoOrAIFMRecordInfo();

		ComplexAIFMRecordInfoType complexAIFMRecordInfoType = null;
		ComplexCancellationAIFMRecordInfoType complexCancellationAIFMRecordInfoType = null;

		for (Object object : listAIFMReporting) {
			if (object instanceof ComplexAIFMRecordInfoType) {
				complexAIFMRecordInfoType = (ComplexAIFMRecordInfoType) object;
			}
			if (object instanceof ComplexCancellationAIFMRecordInfoType) {
				complexCancellationAIFMRecordInfoType = (ComplexCancellationAIFMRecordInfoType) object;
			}

		}

		// Process inverse of GenerateXML, the class aifmReportingInfo contains
		// all objects inside and we unmarshal creating reportDatas, searching
		// of every reportFields and add to reportExecution if not exists

		VersionAuditor user = new VersionAuditor("loader");

		ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
				.getBean("reportCatalogDAO");

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");

		reportExecution = reportExecutionDAO.findById(reportExecution.getId());

		List<ReportData> reportDatas = reportExecution.getReportDatas();

		ReportField reportField = new ReportField();

		ReportCatalog reportCatalog = reportCatalogDAO.findById(reportExecution
				.getReportCatalog().getId());

		List<ReportField> reportFields = reportCatalog.getReportFields();

		String temp = null;
		String block = null;
		int count = 0;

		// (1) <ReportingMemberState>
		if (aifmReportingInfo.getReportingMemberState() != null) {
			temp = aifmReportingInfo.getReportingMemberState();
			reportField = ReportUtilities.searchField(reportFields,
					"ReportingMemberState", "1");
			if (reportField != null
					&& temp != null
					&& !temp.isEmpty()
					&& ReportUtilities.searchData(reportDatas,
							"ReportingMemberState", "1", null) == null)
				reportDatas.add(new ReportData(null, reportField,
						reportExecution, null, null, temp, null, null, user));
		}

		// (2) <Version>
		if (aifmReportingInfo.getVersion() != null) {
			temp = aifmReportingInfo.getVersion();
			reportField = ReportUtilities.searchField(reportFields, "Version",
					"2");
			if (reportField != null
					&& temp != null
					&& !temp.isEmpty()
					&& ReportUtilities.searchData(reportDatas, "Version", "2",
							null) == null)
				reportDatas.add(new ReportData(null, reportField,
						reportExecution, null, null, temp, null, null, user));
		}

		// (3) <CreationDateAndTime>
		if (aifmReportingInfo.getCreationDateAndTime() != null) {
			temp = new SimpleDateFormat(ReportUtilities.dateTimePattern)
					.format(XMLGregorianCalendarConverter
							.asDate(aifmReportingInfo.getCreationDateAndTime()));
			reportField = ReportUtilities.searchField(reportFields,
					"CreationDateAndTime", "3");
			if (reportField != null
					&& temp != null
					&& !temp.isEmpty()
					&& ReportUtilities.searchData(reportDatas,
							"CreationDateAndTime", "3", null) == null)
				reportDatas.add(new ReportData(null, reportField,
						reportExecution, null, null, temp, null, null, user));
		}

		if (complexAIFMRecordInfoType != null) {

			// (4) <FilingType>
			if (complexAIFMRecordInfoType.getFilingType() != null) {
				temp = complexAIFMRecordInfoType.getFilingType().value();
				reportField = ReportUtilities.searchField(reportFields,
						"FilingType", "4");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas,
								"FilingType", "4", null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

			// (5) <AIFMContentType>
			if (complexAIFMRecordInfoType.getAIFMContentType() != null) {
				temp = complexAIFMRecordInfoType.getAIFMContentType();
				reportField = ReportUtilities.searchField(reportFields,
						"AIFMContentType", "5");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas,
								"AIFMContentType", "5", null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

			// (6) <ReportingPeriodStartDate>
			if (complexAIFMRecordInfoType.getReportingPeriodStartDate() != null) {
				temp = new SimpleDateFormat(ReportUtilities.datePattern)
						.format(XMLGregorianCalendarConverter
								.asDate(complexAIFMRecordInfoType
										.getReportingPeriodStartDate()));
				reportField = ReportUtilities.searchField(reportFields,
						"ReportingPeriodStartDate", "6");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas,
								"ReportingPeriodStartDate", "6", null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

			// (7) <ReportingPeriodEndDate>
			if (complexAIFMRecordInfoType.getReportingPeriodEndDate() != null) {
				temp = new SimpleDateFormat(ReportUtilities.datePattern)
						.format(XMLGregorianCalendarConverter
								.asDate(complexAIFMRecordInfoType
										.getReportingPeriodEndDate()));
				reportField = ReportUtilities.searchField(reportFields,
						"ReportingPeriodEndDate", "7");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas,
								"ReportingPeriodEndDate", "7", null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

			// (8) <ReportingPeriodType>
			if (complexAIFMRecordInfoType.getReportingPeriodType() != null) {
				temp = complexAIFMRecordInfoType.getReportingPeriodType()
						.value();
				reportField = ReportUtilities.searchField(reportFields,
						"ReportingPeriodType", "8");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas,
								"ReportingPeriodType", "8", null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

			// (9) <ReportingPeriodYear>
			if (complexAIFMRecordInfoType.getReportingPeriodYear() != null) {
				temp = new SimpleDateFormat(ReportUtilities.yearPattern)
						.format(XMLGregorianCalendarConverter
								.asDate(complexAIFMRecordInfoType
										.getReportingPeriodYear()));
				reportField = ReportUtilities.searchField(reportFields,
						"ReportingPeriodYear", "9");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas,
								"ReportingPeriodYear", "9", null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

			// (10) <AIFMReportingObligationChangeFrequencyCode>
			if (complexAIFMRecordInfoType
					.getAIFMReportingObligationChangeFrequencyCode() != null) {
				temp = complexAIFMRecordInfoType
						.getAIFMReportingObligationChangeFrequencyCode()
						.value();
				reportField = ReportUtilities.searchField(reportFields,
						"AIFMReportingObligationChangeFrequencyCode", "10");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas,
								"AIFMReportingObligationChangeFrequencyCode",
								"10", null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

			// (11) <AIFMReportingObligationChangeContentsCode>
			if (complexAIFMRecordInfoType
					.getAIFMReportingObligationChangeContentsCode() != null) {
				temp = complexAIFMRecordInfoType
						.getAIFMReportingObligationChangeContentsCode()
						.toString();
				reportField = ReportUtilities.searchField(reportFields,
						"AIFMReportingObligationChangeContentsCode", "11");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas,
								"AIFMReportingObligationChangeContentsCode",
								"11", null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

			// (12) <AIFMReportingObligationChangeQuarter>
			if (complexAIFMRecordInfoType
					.getAIFMReportingObligationChangeQuarter() != null) {
				temp = complexAIFMRecordInfoType
						.getAIFMReportingObligationChangeQuarter().value();
				reportField = ReportUtilities.searchField(reportFields,
						"AIFMReportingObligationChangeQuarter", "12");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas,
								"AIFMReportingObligationChangeQuarter", "12",
								null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

			// (13) <LastReportingFlag>
			temp = Boolean.toString(complexAIFMRecordInfoType
					.isLastReportingFlag());
			reportField = ReportUtilities.searchField(reportFields,
					"LastReportingFlag", "13");
			if (reportField != null
					&& temp != null
					&& !temp.isEmpty()
					&& ReportUtilities.searchData(reportDatas,
							"LastReportingFlag", "13", null) == null)
				reportDatas.add(new ReportData(null, reportField,
						reportExecution, null, null, temp, null, null, user));

			// /////////////////////////////////////////////////////////////////
			// <Assumptions>

			ComplexAssumptionsType complexAssumptionsType = complexAIFMRecordInfoType
					.getAssumptions();

			if (complexAssumptionsType != null) {

				List<ComplexAssumptionType> complexAssumptionTypeList = complexAssumptionsType
						.getAssumption();

				count = 0;
				for (ComplexAssumptionType complexAssumptionType : complexAssumptionTypeList) {

					count++;
					block = Integer.toString(count);

					// (14) <QuestionNumber>
					if (complexAssumptionType.getQuestionNumber() != null) {
						temp = complexAssumptionType.getQuestionNumber()
								.toString();
						reportField = ReportUtilities.searchField(reportFields,
								"QuestionNumber", "14");
						if (reportField != null
								&& temp != null
								&& !temp.isEmpty()
								&& ReportUtilities.searchData(reportDatas,
										"QuestionNumber", "14", block) == null)
							reportDatas.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, block,
									null, user));
					}

					// (15) <AssumptionDescription>
					if (complexAssumptionType.getAssumptionDescription() != null) {
						temp = complexAssumptionType.getAssumptionDescription();
						reportField = ReportUtilities.searchField(reportFields,
								"AssumptionDescription", "15");
						if (reportField != null
								&& temp != null
								&& !temp.isEmpty()
								&& ReportUtilities.searchData(reportDatas,
										"AssumptionDescription", "15", block) == null)
							reportDatas.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, block,
									null, user));
					}

				}

			} // end complexAssumptionsType

			// (16) <AIFMReportingCode>
			if (complexAIFMRecordInfoType.getAIFMReportingCode() != null) {
				temp = complexAIFMRecordInfoType.getAIFMReportingCode()
						.toString();
				reportField = ReportUtilities.searchField(reportFields,
						"AIFMReportingCode", "16");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas,
								"AIFMReportingCode", "16", null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

			// (17) <AIFMJurisdiction>
			if (complexAIFMRecordInfoType.getAIFMJurisdiction() != null) {
				temp = complexAIFMRecordInfoType.getAIFMJurisdiction();
				reportField = ReportUtilities.searchField(reportFields,
						"AIFMJurisdiction", "17");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas,
								"AIFMJurisdiction", "17", null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

			// (18) <AIFMNationalCode>
			if (complexAIFMRecordInfoType.getAIFMNationalCode() != null) {
				temp = complexAIFMRecordInfoType.getAIFMNationalCode();
				reportField = ReportUtilities.searchField(reportFields,
						"AIFMNationalCode", "18");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas,
								"AIFMNationalCode", "18", null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

			// (19) <AIFMName>
			if (complexAIFMRecordInfoType.getAIFMName() != null) {
				temp = complexAIFMRecordInfoType.getAIFMName();
				reportField = ReportUtilities.searchField(reportFields,
						"AIFMName", "19");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas, "AIFMName",
								"19", null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

			// (20) <AIFMEEAFlag>
			temp = Boolean.toString(complexAIFMRecordInfoType.isAIFMEEAFlag());
			reportField = ReportUtilities.searchField(reportFields,
					"AIFMEEAFlag", "20");
			if (reportField != null
					&& temp != null
					&& !temp.isEmpty()
					&& ReportUtilities.searchData(reportDatas, "AIFMEEAFlag",
							"20", null) == null)
				reportDatas.add(new ReportData(null, reportField,
						reportExecution, null, null, temp, null, null, user));

			// (21) <AIFMNoReportingFlag>
			temp = Boolean.toString(complexAIFMRecordInfoType
					.isAIFMNoReportingFlag());
			reportField = ReportUtilities.searchField(reportFields,
					"AIFMNoReportingFlag", "21");
			if (reportField != null
					&& temp != null
					&& !temp.isEmpty()
					&& ReportUtilities.searchData(reportDatas,
							"AIFMNoReportingFlag", "21", null) == null)
				reportDatas.add(new ReportData(null, reportField,
						reportExecution, null, null, temp, null, null, user));

			ComplexAIFMCompleteDescriptionType complexAIFMCompleteDescriptionType = complexAIFMRecordInfoType
					.getAIFMCompleteDescription();

			if (complexAIFMCompleteDescriptionType != null) {

				// /////////////////////////////////////////////////////////////////
				// <AIFMPrincipalMarkets>

				ComplexAIFMPrincipalMarketsType complexAIFMPrincipalMarketsType = complexAIFMCompleteDescriptionType
						.getAIFMPrincipalMarkets();

				if (complexAIFMPrincipalMarketsType != null) {
					List<ComplexFivePrincipalMarketType> complexFivePrincipalMarketTypeList = complexAIFMPrincipalMarketsType
							.getAIFMFivePrincipalMarket();

					count = 0;
					for (ComplexFivePrincipalMarketType complexFivePrincipalMarketType : complexFivePrincipalMarketTypeList) {

						count++;
						block = Integer.toString(count);

						// (26) <Ranking>
						if (complexFivePrincipalMarketType.getRanking() != null) {
							temp = complexFivePrincipalMarketType.getRanking()
									.toString();
							reportField = ReportUtilities.searchField(
									reportFields, "Ranking", "26");
							if (reportField != null
									&& temp != null
									&& !temp.isEmpty()
									&& ReportUtilities.searchData(reportDatas,
											"Ranking", "26", block) == null)
								reportDatas.add(new ReportData(null,
										reportField, reportExecution, null,
										null, temp, block, null, user));
						}

						ComplexMarketIdentificationWithNOTType complexMarketIdentificationWithNOTType = complexFivePrincipalMarketType
								.getMarketIdentification();

						// (27) <MarketCodeType>
						if (complexMarketIdentificationWithNOTType
								.getMarketCodeType() != null) {
							temp = complexMarketIdentificationWithNOTType
									.getMarketCodeType().value();
							reportField = ReportUtilities.searchField(
									reportFields, "MarketCodeType", "27");
							if (reportField != null
									&& temp != null
									&& !temp.isEmpty()
									&& ReportUtilities.searchData(reportDatas,
											"MarketCodeType", "27", block) == null)
								reportDatas.add(new ReportData(null,
										reportField, reportExecution, null,
										null, temp, block, null, user));
						}

						// (28) <MarketCode>
						if (complexMarketIdentificationWithNOTType
								.getMarketCode() != null) {
							temp = complexMarketIdentificationWithNOTType
									.getMarketCode();
							reportField = ReportUtilities.searchField(
									reportFields, "MarketCode", "28");
							if (reportField != null
									&& temp != null
									&& !temp.isEmpty()
									&& ReportUtilities.searchData(reportDatas,
											"MarketCode", "28", block) == null)
								reportDatas.add(new ReportData(null,
										reportField, reportExecution, null,
										null, temp, block, null, user));
						}

						// (29) <AggregatedValueAmount>
						if (complexFivePrincipalMarketType
								.getAggregatedValueAmount() != null) {
							temp = complexFivePrincipalMarketType
									.getAggregatedValueAmount().toString();
							reportField = ReportUtilities
									.searchField(reportFields,
											"AggregatedValueAmount", "29");
							if (reportField != null
									&& temp != null
									&& !temp.isEmpty()
									&& ReportUtilities.searchData(reportDatas,
											"AggregatedValueAmount", "29",
											block) == null)
								reportDatas.add(new ReportData(null,
										reportField, reportExecution, null,
										null, temp, block, null, user));
						}

					}

				} // end complexAIFMPrincipalMarketsType

				// /////////////////////////////////////////////////////////////////
				// <AIFMPrincipalInstruments>

				ComplexAIFMPrincipalInstrumentsType complexAIFMPrincipalInstrumentsType = complexAIFMCompleteDescriptionType
						.getAIFMPrincipalInstruments();

				if (complexAIFMPrincipalInstrumentsType != null) {

					List<ComplexPrincipalInstrumentType> complexPrincipalInstrumentTypeList = complexAIFMPrincipalInstrumentsType
							.getAIFMPrincipalInstrument();

					count = 0;
					for (ComplexPrincipalInstrumentType complexPrincipalInstrumentType : complexPrincipalInstrumentTypeList) {

						count++;
						block = Integer.toString(count);

						// (30) <Ranking>
						if (complexPrincipalInstrumentType.getRanking() != null) {
							temp = complexPrincipalInstrumentType.getRanking()
									.toString();
							reportField = ReportUtilities.searchField(
									reportFields, "Ranking", "30");
							if (reportField != null
									&& temp != null
									&& !temp.isEmpty()
									&& ReportUtilities.searchData(reportDatas,
											"Ranking", "30", block) == null)
								reportDatas.add(new ReportData(null,
										reportField, reportExecution, null,
										null, temp, block, null, user));
						}

						// (31) <SubAssetType>
						if (complexPrincipalInstrumentType.getSubAssetType() != null) {
							temp = complexPrincipalInstrumentType
									.getSubAssetType().value();
							reportField = ReportUtilities.searchField(
									reportFields, "SubAssetType", "31");
							if (reportField != null
									&& temp != null
									&& !temp.isEmpty()
									&& ReportUtilities.searchData(reportDatas,
											"SubAssetType", "31", block) == null)
								reportDatas.add(new ReportData(null,
										reportField, reportExecution, null,
										null, temp, block, null, user));
						}

						// (32) <AggregatedValueAmount>
						if (complexPrincipalInstrumentType
								.getAggregatedValueAmount() != null) {
							temp = complexPrincipalInstrumentType
									.getAggregatedValueAmount().toString();
							reportField = ReportUtilities
									.searchField(reportFields,
											"AggregatedValueAmount", "32");
							if (reportField != null
									&& temp != null
									&& !temp.isEmpty()
									&& ReportUtilities.searchData(reportDatas,
											"AggregatedValueAmount", "32",
											block) == null)
								reportDatas.add(new ReportData(null,
										reportField, reportExecution, null,
										null, temp, block, null, user));
						}
					}

				} // end complexAIFMPrincipalInstrumentsType

				// /////////////////////////////////////////////////////////////////
				// <AIFMCompleteDescription>

				ComplexAIFMIdentifierType complexAIFMIdentifierType = complexAIFMCompleteDescriptionType
						.getAIFMIdentifier();

				if (complexAIFMIdentifierType != null) {

					// (22) <AIFMIdentifierLEI>
					if (complexAIFMIdentifierType.getAIFMIdentifierLEI() != null) {
						temp = complexAIFMIdentifierType.getAIFMIdentifierLEI();
						reportField = ReportUtilities.searchField(reportFields,
								"AIFMIdentifierLEI", "22");
						if (reportField != null
								&& temp != null
								&& !temp.isEmpty()
								&& ReportUtilities.searchData(reportDatas,
										"AIFMIdentifierLEI", "22", null) == null)
							reportDatas.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
					}

					// (23) <AIFMIdentifierBIC>
					if (complexAIFMIdentifierType.getAIFMIdentifierBIC() != null) {
						temp = complexAIFMIdentifierType.getAIFMIdentifierBIC();
						reportField = ReportUtilities.searchField(reportFields,
								"AIFMIdentifierBIC", "23");
						if (reportField != null
								&& temp != null
								&& !temp.isEmpty()
								&& ReportUtilities.searchData(reportDatas,
										"AIFMIdentifierBIC", "23", null) == null)
							reportDatas.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
					}

					ComplexAIFMNationalIdentifierType complexAIFMNationalIdentifierType = complexAIFMIdentifierType
							.getOldAIFMIdentifierNCA();

					if (complexAIFMNationalIdentifierType != null) {

						// (24) <Old_ReportingMemberState>
						if (complexAIFMNationalIdentifierType
								.getReportingMemberState() != null) {
							temp = complexAIFMNationalIdentifierType
									.getReportingMemberState();
							reportField = ReportUtilities.searchField(
									reportFields, "Old_ReportingMemberState",
									"24");
							if (reportField != null
									&& temp != null
									&& !temp.isEmpty()
									&& ReportUtilities.searchData(reportDatas,
											"Old_ReportingMemberState", "24",
											null) == null)
								reportDatas.add(new ReportData(null,
										reportField, reportExecution, null,
										null, temp, null, null, user));
						}

						// (25) <Old_AIFMNationalCode>
						if (complexAIFMNationalIdentifierType
								.getAIFMNationalCode() != null) {
							temp = complexAIFMNationalIdentifierType
									.getAIFMNationalCode();
							reportField = ReportUtilities.searchField(
									reportFields, "Old_AIFMNationalCode", "25");
							if (reportField != null
									&& temp != null
									&& !temp.isEmpty()
									&& ReportUtilities.searchData(reportDatas,
											"Old_AIFMNationalCode", "25", null) == null)
								reportDatas.add(new ReportData(null,
										reportField, reportExecution, null,
										null, temp, null, null, user));
						}
					}

				} // end complexAIFMIdentifierType

				// (33) <AUMAmountInEuro>
				if (complexAIFMCompleteDescriptionType.getAUMAmountInEuro() != null) {
					temp = complexAIFMCompleteDescriptionType
							.getAUMAmountInEuro().toString();
					reportField = ReportUtilities.searchField(reportFields,
							"AUMAmountInEuro", "33");
					if (reportField != null
							&& temp != null
							&& !temp.isEmpty()
							&& ReportUtilities.searchData(reportDatas,
									"AUMAmountInEuro", "33", null) == null)
						reportDatas.add(new ReportData(null, reportField,
								reportExecution, null, null, temp, null, null,
								user));
				}

				ComplexBaseCurrencyDescriptionType complexBaseCurrencyDescriptionType = complexAIFMCompleteDescriptionType
						.getAIFMBaseCurrencyDescription();

				if (complexBaseCurrencyDescriptionType != null) {

					// (34) <AUMAmountInBaseCurrency>
					if (complexBaseCurrencyDescriptionType
							.getAUMAmountInBaseCurrency() != null) {
						temp = complexBaseCurrencyDescriptionType
								.getAUMAmountInBaseCurrency().toString();
						reportField = ReportUtilities.searchField(reportFields,
								"AUMAmountInBaseCurrency", "34");
						if (reportField != null
								&& temp != null
								&& !temp.isEmpty()
								&& ReportUtilities.searchData(reportDatas,
										"AUMAmountInBaseCurrency", "34", null) == null)
							reportDatas.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
					}

					// (35) <BaseCurrency>
					if (complexBaseCurrencyDescriptionType.getBaseCurrency() != null) {
						temp = complexBaseCurrencyDescriptionType
								.getBaseCurrency();
						reportField = ReportUtilities.searchField(reportFields,
								"BaseCurrency", "35");
						if (reportField != null
								&& temp != null
								&& !temp.isEmpty()
								&& ReportUtilities.searchData(reportDatas,
										"BaseCurrency", "35", null) == null)
							reportDatas.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
					}

					// (36) <FXEURReferenceRateType>
					if (complexBaseCurrencyDescriptionType
							.getFXEURReferenceRateType() != null) {
						temp = complexBaseCurrencyDescriptionType
								.getFXEURReferenceRateType().value();
						reportField = ReportUtilities.searchField(reportFields,
								"FXEURReferenceRateType", "36");
						if (reportField != null
								&& temp != null
								&& !temp.isEmpty()
								&& ReportUtilities.searchData(reportDatas,
										"FXEURReferenceRateType", "36", null) == null)
							reportDatas.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
					}

					// (37) <FXEURRate>
					if (complexBaseCurrencyDescriptionType.getFXEURRate() != null) {
						temp = complexBaseCurrencyDescriptionType
								.getFXEURRate().toString();
						reportField = ReportUtilities.searchField(reportFields,
								"FXEURRate", "37");
						if (reportField != null
								&& temp != null
								&& !temp.isEmpty()
								&& ReportUtilities.searchData(reportDatas,
										"FXEURRate", "37", null) == null)
							reportDatas.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
					}

					// (38) <FXEUROtherReferenceRateDescription>
					if (complexBaseCurrencyDescriptionType
							.getFXEUROtherReferenceRateDescription() != null) {
						temp = complexBaseCurrencyDescriptionType
								.getFXEUROtherReferenceRateDescription();
						reportField = ReportUtilities.searchField(reportFields,
								"FXEUROtherReferenceRateDescription", "38");
						if (reportField != null
								&& temp != null
								&& !temp.isEmpty()
								&& ReportUtilities.searchData(reportDatas,
										"FXEUROtherReferenceRateDescription",
										"38", null) == null)
							reportDatas.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
					}

				} // end complexBaseCurrencyDescriptionType

			} // end complexAIFMCompleteDescriptionType

		} // end complexAIFMRecordInfoType

		// /////////////////////////////////////////////////////////////////
		// <CancellationAIFMRecordInfo>

		if (complexCancellationAIFMRecordInfoType != null) {
			// (39) <CancelledAIFMNationalCode>
			if (complexCancellationAIFMRecordInfoType
					.getCancelledAIFMNationalCode() != null) {
				temp = complexCancellationAIFMRecordInfoType
						.getCancelledAIFMNationalCode();
				reportField = ReportUtilities.searchField(reportFields,
						"CancelledAIFMNationalCode", "39");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas,
								"CancelledAIFMNationalCode", "39", null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

			// (40) <CancelledReportingPeriodType>
			if (complexCancellationAIFMRecordInfoType
					.getCancelledReportingPeriodType() != null) {
				temp = complexCancellationAIFMRecordInfoType
						.getCancelledReportingPeriodType().value();
				reportField = ReportUtilities.searchField(reportFields,
						"CancelledReportingPeriodType", "40");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas,
								"CancelledReportingPeriodType", "40", null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

			// (41) <CancelledReportingPeriodYear>
			if (complexCancellationAIFMRecordInfoType
					.getCancelledReportingPeriodYear() != null) {
				temp = new SimpleDateFormat(ReportUtilities.yearPattern)
						.format(XMLGregorianCalendarConverter
								.asDate(complexCancellationAIFMRecordInfoType
										.getCancelledReportingPeriodYear()));
				reportField = ReportUtilities.searchField(reportFields,
						"CancelledReportingPeriodYear", "41");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas,
								"CancelledReportingPeriodYear", "41", null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

			// (42) <CancelledRecordFlag>
			if (complexCancellationAIFMRecordInfoType.getCancelledRecordFlag() != null) {
				temp = complexCancellationAIFMRecordInfoType
						.getCancelledRecordFlag().value();
				reportField = ReportUtilities.searchField(reportFields,
						"CancelledRecordFlag", "42");
				if (reportField != null
						&& temp != null
						&& !temp.isEmpty()
						&& ReportUtilities.searchData(reportDatas,
								"CancelledRecordFlag", "42", null) == null)
					reportDatas
							.add(new ReportData(null, reportField,
									reportExecution, null, null, temp, null,
									null, user));
			}

		} // end complexCancellationAIFMRecordInfoType

		// /////////////////////////////////////////////////////////////////

		System.out.println("Datos finales de XML: ");
		for (ReportData reportData1 : reportDatas) {
			System.out.println(reportData1.getReportField()
					.getReportFieldName()
					+ " ("
					+ reportData1.getReportField().getReportFieldNum()
					+ ") "
					+ reportData1.getReportDataText());
		}

		reportExecution.setReportDatas(reportDatas);

		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		return reportExecution;
	}
}
