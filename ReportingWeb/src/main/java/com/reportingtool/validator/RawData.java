package com.reportingtool.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportDataDAO;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.loader.LoadRaw;
import com.entities.entity.loader.LoadRawData;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;
import com.entities.utilities.hibernate.VersionAuditor;

public class RawData {

	private ApplicationContext applicationContext;

	public RawData(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void fileRawToData(ReportExecution reportExecution) {

		for (LoadFile loadFile : reportExecution.getLoadFiles()) {

			List<LoadRawData> loadRawDatas = new ArrayList<LoadRawData>();
			for (LoadRaw loadRaw : loadFile.getLoadRaws()) {
				loadRawDatas.addAll(loadRaw.getLoadRawDatas());
			}

			// por cada raw genero un objeto data
			for (LoadRawData loadRawData : loadRawDatas) {
				System.out.println("DEBUG_" + "RawData "
						+ loadRawData.getLoadRawDataType() + " "
						+ loadRawData.getLoadRawDataText());
				this.rawToData(loadRawData, reportExecution);
			}
		}

	}

	public void rawToData(LoadRawData loadRawData,
			ReportExecution reportExecution) {

		FileColum fileColum = loadRawData.getFileColum();
		ReportField reportField = fileColum.getReportField();

		if (reportField != null) {
			// create new reportData with raw data
			ReportData reportData = new ReportData();

			// text
			reportData.setReportDataText(loadRawData.getLoadRawDataText());
			
			//block
			reportData.setReportDataBlock(loadRawData.getLoadRawDataBlock());

			// field
			reportData.setReportField(reportField);

			// report
			reportData.setReportExecution(reportExecution);

			// user
			reportData.setAuditor(new VersionAuditor("admin"));

			// save new reportData
			ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
					.getBean("reportDataDAO");

			reportDataDAO.create(reportData);
		}
	}
}
