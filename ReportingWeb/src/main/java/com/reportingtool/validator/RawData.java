package com.reportingtool.validator;

import java.util.ArrayList;
import java.util.List;


import com.entity.loader.FileColum;
import com.entity.loader.LoadFile;
import com.entity.loader.LoadRawData;
import com.entity.loader.LoadRaw;
import com.entity.reportingtool.ReportData;
import com.entity.reportingtool.ReportExecution;
import com.entity.reportingtool.ReportField;

public class RawData {

	public void FileRawToData(LoadFile loadFile) {

		// from a file, we move raw to data

		// obtengo la lista de raw del fichero que viene
//		LoadFileDAO loadFileDAO = new LoadFileDAO();
//
//		List<LoadFile> loadFiles = new ArrayList<LoadFile>(
//				loadFileDAO.findByExample(loadFile));
		
		List<LoadRaw> loadRaws = new ArrayList<LoadRaw>(loadFile.getLoadRaws());
		
		List<LoadRawData> loadRawDatas = new ArrayList<LoadRawData>();
		for (LoadRaw loadRaw : loadRaws) {
			loadRawDatas.addAll(loadRaw.getLoadRawDatas());
		}

		// por cada raw genero un objeto data
		for (LoadRawData loadRawData : loadRawDatas) {
			System.out.println("DEBUG_" + "RawData "
					+ loadRawData.getLoadRawDataType() + " "
					+ loadRawData.getLoadRawDataText());
			this.RawToData(loadRawData);
		}

	}

	public void RawToData(LoadRawData loadRawData) {

		// create new aifmdData with war data
		ReportData reportData = new ReportData();

		// text
		reportData.setReportDataText(loadRawData.getLoadRawDataText());

		// field
		FileColum fileColum = loadRawData.getFileColum();
		ReportField reportField = fileColum.getReportField();
		reportData.setReportField(reportField);

		// report
		ReportExecution reportExecution = new ReportExecution();
		reportExecution.setReportCatalog(reportField.getReportCatalog());

		reportData.setReportExecution(reportExecution);

		// save new aifmdData
		// aifmdDataDAO.merge(aifmdData);

	}
}
