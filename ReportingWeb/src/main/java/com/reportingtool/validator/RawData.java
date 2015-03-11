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

/**
 * Class to copy raw info to a reportExecution
 * 
 * @author alberto.olivan
 *
 */
public class RawData {

	private ApplicationContext applicationContext;

	/**
	 * Constructor of RawData with an applicationContext
	 * 
	 * @param applicationContext
	 */
	public RawData(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Function to assign all loadRaws from loadFiles in a reportExecution
	 * 
	 * @param reportExecution
	 */
	public void fileRawToData(ReportExecution reportExecution) {

		for (LoadFile loadFile : reportExecution.getLoadFiles()) {

			List<LoadRawData> loadRawDatas = new ArrayList<LoadRawData>();
			for (LoadRaw loadRaw : loadFile.getLoadRaws()) {
				loadRawDatas.addAll(loadRaw.getLoadRawDatas());
			}

			// for each raw generate an object data
			for (LoadRawData loadRawData : loadRawDatas) {
				System.out.println("DEBUG_" + "RawData "
						+ loadRawData.getLoadRawDataType() + " "
						+ loadRawData.getLoadRawDataText());
				this.rawToData(loadRawData, reportExecution);
			}
		}

	}

	/**
	 * Function to create reportData and assign to reportExecution from
	 * LoadRawData
	 * 
	 * @param loadRawData
	 * @param reportExecution
	 */
	public void rawToData(LoadRawData loadRawData,
			ReportExecution reportExecution) {

		FileColum fileColum = loadRawData.getFileColum();
		ReportField reportField = fileColum.getReportField();

		if (reportField != null) {
			// create new reportData with raw data
			ReportData reportData = new ReportData();
			// text
			reportData.setReportDataText(loadRawData.getLoadRawDataText());
			// block
			reportData.setReportDataBlock(loadRawData.getLoadRawDataBlock());
			// field
			reportData.setReportField(reportField);
			// report (necesario para comparar)
			reportData.setReportExecution(reportExecution);

			boolean exist = false;
			for (ReportData reportDataAux : reportExecution.getReportDatas()) {

				// if (aux.getReportField().getId() ==
				// reportData.getReportField()
				// .getId()
				// && ((aux.getReportDataBlock() == null && reportData
				// .getReportDataBlock() == null) || (aux
				// .getReportDataBlock() != null
				// && reportData.getReportDataBlock() != null && aux
				// .getReportDataBlock().equals(
				// reportData.getReportDataBlock())))) {
				// System.out.println("Filtra Duplicado");
				// exist = true;
				// break;
				// }

				if (reportData.equals(reportDataAux)) {
					System.out.println("DEBUG_" + "RawData "
							+ reportData.getReportDataText()
							+ reportData.getReportField().getReportFieldName()
							+ " repetido.");
					exist = true;
					break;
				}
			}

			if (!exist) {
				// user
				reportData.setAuditor(new VersionAuditor("admin"));
				// save new reportData
				ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
						.getBean("reportDataDAO");

				reportDataDAO.create(reportData);

				// add reportData in reportExecution object
				reportExecution.getReportDatas().add(reportData);
			}
		}
	}
}
