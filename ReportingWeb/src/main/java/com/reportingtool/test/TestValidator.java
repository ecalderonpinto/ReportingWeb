package com.reportingtool.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.reportingtool.creation.GeneratorXML;
import com.reportingtool.normalizer.Format;
import com.reportingtool.normalizer.Translate;
import com.reportingtool.validator.RawData;
import com.reportingtool.validator.Status;
import com.reportingtool.validator.Syntactic;
import com.entities.dao.loader.FileColumListDAO;
import com.entities.dao.loader.LoadFileDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.loader.LoadRaw;
import com.entities.entity.loader.LoadRawData;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;

public class TestValidator {

	public void process(ApplicationContext aplicationContext) {

		try {

			LoadFile loadFileExample = new LoadFile();
			loadFileExample.setLoadFileName("Fichero1.txt");

			LoadFileDAO loadFileDAO = (LoadFileDAO) aplicationContext
					.getBean("loadFileDAO");
			List<LoadFile> loadFiles = loadFileDAO
					.findByExample(loadFileExample);

			// proceso el primer elemento, puede no haber
			LoadFile loadFile = loadFiles.get(0);

			List<LoadRaw> loadRaws = new ArrayList<LoadRaw>(
					loadFile.getLoadRaws());

			// process every raw line loaded from file
			for (LoadRaw loadRaw : loadRaws) {
				List<LoadRawData> aifmdRawDatas = new ArrayList<LoadRawData>(
						loadRaw.getLoadRawDatas());
				for (LoadRawData aifmdRawData : aifmdRawDatas) {
					Translate translate = new Translate(aplicationContext);
					translate.translateRaw(aifmdRawData);

					Format format = new Format(aplicationContext);
					format.formatRaw(aifmdRawData);
				}
			}

			ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) aplicationContext
					.getBean("reportExecutionDAO");
			ReportExecution reportExecutionExample = new ReportExecution();
			reportExecutionExample.setReportPeriodType("Q1");
			reportExecutionExample.setReportPeriodYear("2014");
			List<ReportExecution> reportExecutions = new ArrayList<ReportExecution>(
					reportExecutionDAO.findByExample(reportExecutionExample));
			ReportExecution reportExecution = reportExecutions.get(0);

			RawData rawData = new RawData(aplicationContext);
			rawData.FileRawToData(loadFile, reportExecution);

		} catch (Exception e) {
			System.out.println("ERROR_" + "TestValidator 1");
			e.printStackTrace();
		}

		try {

			ReportExecution reportExecutionExample = new ReportExecution();
			reportExecutionExample.setReportPeriodYear("2014");
			reportExecutionExample.setReportPeriodType("Q1");

			ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) aplicationContext
					.getBean("reportExecutionDAO");
			List<ReportExecution> reportExecutions = reportExecutionDAO
					.findByExample(reportExecutionExample);

			// proceso el primer elemento, puede no haber
			ReportExecution reportExecution = reportExecutions.get(0);

			System.out.println("DEBUG_" + "TestValidator: ReportExecution: "
					+ reportExecution.getReportPeriodType() + " "
					+ reportExecution.getReportPeriodYear());

			Syntactic syntactic = new Syntactic(aplicationContext);

			List<ReportData> reportDatas = new ArrayList<ReportData>(
					reportExecution.getReportDatas());

			System.out.println("DEBUG_" + "TestValidator: starting for list: "
					+ reportDatas);
			for (ReportData reportData : reportDatas) {
				System.out.println("DEBUG_" + "TestValidator: "
						+ reportData.getReportDataDate() + " "
						+ reportData.getReportDataText());
				syntactic.validInValueList(reportData);
				syntactic.validRegex(reportData);
			}

			System.out
					.println("DEBUG_" + "TestValidator checking AIFMD Status");
			Status status = new Status(aplicationContext);
			status.checkAIFMDStatus(reportExecution);

			System.out.println("DEBUG_" + "TestValidator generating XML");
			GeneratorXML generatorXML = new GeneratorXML(aplicationContext);
			generatorXML.generateXML(reportExecution);

		} catch (Exception e) {
			System.out.println("ERROR_" + "TestValidator 2");
			e.printStackTrace();
		}

	}

}
