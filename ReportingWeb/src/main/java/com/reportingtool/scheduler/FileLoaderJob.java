package com.reportingtool.scheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.entities.dao.loader.FileConfigDAO;
import com.entities.dao.loader.LoadFileDAO;
import com.entities.entity.loader.FileConfig;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.loader.LoadRaw;
import com.entities.entity.loader.LoadRawData;
import com.reportingtool.normalizer.Format;
import com.reportingtool.normalizer.Translate;
import com.reportingtool.utilities.ReportingErrorManager;

@Component("loaderJob")
public class FileLoaderJob {

	@Autowired
	private ApplicationContext applicationContext;
	private String inputDirectory = "C:/Temp/input";
	private String outputDirectory = "C:/Temp/done";

	public void run() {

		LoadFile loadFile = null;

		try {
			File input = new File(inputDirectory);

			// Load FileConfigs;
			FileConfigDAO fileConfigDAO = (FileConfigDAO) this.applicationContext
					.getBean("fileConfigDAO");
			List<FileConfig> fileConfigs = fileConfigDAO.findAll();

			// Search files in inputDirectory;
			File[] files = input.listFiles();
			for (File f : files) {
				// For each file create a LoadFile running a FileLoader;
				FileLoader fl = new FileLoader(fileConfigs.get(0), f);
				loadFile = fl.run();
				if (loadFile != null) {
					System.out.println(f.getName() + " is goint to be loader");
				} else {
					ReportingErrorManager.createLoadError(applicationContext,
							"LOADER", loadFile, "PARSING",
							"Error parsing " + f.getName() + " file");
				}

				// Save LoadFile in DataBase;
				LoadFileDAO loadFileDAO = (LoadFileDAO) this.applicationContext
						.getBean("loadFileDAO");
				loadFileDAO.create(loadFile);

				// Rename and delete file;
				if (!f.renameTo(new File(outputDirectory + "/" + f.getName()
						+ ".done")))
					ReportingErrorManager.createLoadError(applicationContext,
							"LOADER", loadFile, "FILE MANAGE",
							"Manage error file " + f.getName()
									+ ", it can not be rename to DONE status");
				f.deleteOnExit();
				
				//Validate Proccess;
				for (LoadRaw loadRaw : loadFile.getLoadRaws()) {
					for (LoadRawData loadRawData : loadRaw.getLoadRawDatas()) {
						Translate translate = new Translate(applicationContext);
						translate.translateRaw(loadRawData);

						Format format = new Format(applicationContext);
						format.formatRaw(loadRawData);
					}
				}
			}

		} catch (SerialException e) {
			ReportingErrorManager.createLoadError(applicationContext, "LOADER",
					loadFile, "BLOB PROCESS", "Error processing BLOB data");
			e.printStackTrace();
		} catch (SQLException e) {
			ReportingErrorManager.createLoadError(applicationContext, "LOADER",
					loadFile, "SQL", e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			ReportingErrorManager
					.createLoadError(applicationContext, "LOADER", loadFile,
							"FILE", "File not found [" + e.getMessage() + "]");
			e.printStackTrace();
		} catch (IOException e) {
			ReportingErrorManager.createLoadError(applicationContext, "LOADER",
					loadFile, "IO Error",
					"Input/Output error: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			ReportingErrorManager.createLoadError(applicationContext, "LOADER",
					loadFile, "LOAD ERROR", e.getMessage());
			e.printStackTrace();
		}
	}

}
