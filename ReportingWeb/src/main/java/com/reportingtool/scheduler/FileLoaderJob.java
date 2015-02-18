package com.reportingtool.scheduler;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.entities.dao.loader.FileConfigDAO;
import com.entities.entity.loader.FileConfig;

@Component("loaderJob")
public class FileLoaderJob {

	@Autowired
	private ApplicationContext applicationContext;
	private String inputDirectory = "C:/Users/Esteban Calderon/GIT/ReportingWeb/ReportingWeb/src/main/resources/input";
	private String outputDirectory = "C:/Users/Esteban Calderon/GIT/ReportingWeb/ReportingWeb/src/main/resources/done";

	public void run() {

		File input = new File(inputDirectory);
		File output = new File(outputDirectory);

		// Miramos el directorio, y
		FileConfigDAO fileConfigDAO = (FileConfigDAO) this.applicationContext
				.getBean("fileConfigDAO");
		List<FileConfig> fileConfigs = fileConfigDAO.findAll();

		File[] files = input.listFiles();
		for (File f : files) {
			FileLoader fl = new FileLoader(fileConfigs.get(0), f);
			if(fl.run()){
				System.out.println(f.getName() + " loaded");
			} else {
				System.out.println(f.getName() + " failed");
			}
		}

	}
}
