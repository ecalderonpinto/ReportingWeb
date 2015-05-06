package com.reportingtool.creation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.entity.reportingtool.Fund;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;
import com.entities.utilities.hibernate.VersionAuditor;
import com.reportingtool.utilities.ReportUtilities;
import com.reportingtool.xml.AIFMReportingInfo;
import com.reportingtool.xml.ComplexAIFMRecordInfoType;
import com.reportingtool.xml.ComplexCancellationAIFMRecordInfoType;

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

		// (1) <ReportingMemberState>
		reportField = ReportUtilities.searchField(reportFields,
				"ReportingMemberState", "1");
		if (reportField != null
				&& aifmReportingInfo.getReportingMemberState() != null
				&& !aifmReportingInfo.getReportingMemberState().isEmpty()
				&& ReportUtilities.searchData(reportDatas,
						"ReportingMemberState", "1", null) == null)
			reportDatas.add(new ReportData(null, reportField, reportExecution,
					null, null, aifmReportingInfo.getReportingMemberState(),
					null, null, user));

		// TODO all fields

		// (2) <Version>

		// (3) <CreationDateAndTime>

		// (4) <FilingType>

		// (5) <AIFMContentType>
		reportField = ReportUtilities.searchField(reportFields,
				"AIFMContentType", "5");
		if (reportField != null
				&& complexAIFMRecordInfoType.getAIFMContentType() != null
				&& !complexAIFMRecordInfoType.getAIFMContentType().isEmpty()
				&& ReportUtilities.searchData(reportDatas, "AIFMContentType",
						"5", null) == null)
			reportDatas.add(new ReportData(null, reportField, reportExecution,
					null, null, complexAIFMRecordInfoType.getAIFMContentType(),
					null, null, user));

		// (6) <ReportingPeriodStartDate>

		// ...

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
