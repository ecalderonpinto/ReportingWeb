package com.reportingtool.scheduler;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.entities.dao.loader.FileConfigDAO;
import com.entities.dao.loader.LoadFileDAO;
import com.entities.entity.loader.FileConfig;
import com.entities.entity.loader.LoadFile;

@Component("loaderJob")
public class FileLoaderJob {

	@Autowired
	private ApplicationContext applicationContext;
	private String inputDirectory = "C:/Temp/input";
	private String outputDirectory = "C:/Temp/done";

	public void run() {

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
				LoadFile loadFile = fl.run();
				if (loadFile != null) {
					System.out.println(f.getName() + " is goint to be loader");
				} else {
					System.out.println(f.getName() + " failed");
				}

				// Save LoadFile in DataBase;
				LoadFileDAO loadFileDAO = (LoadFileDAO) this.applicationContext
						.getBean("loadFileDAO");
				loadFileDAO.create(loadFile);

				// Rename and delete file;
				if (f.renameTo(new File(outputDirectory + "/" + f.getName()
						+ ".done")))
					System.out.println("renombrado");
				else
					System.out.println("problemas al renombrar");
				f.deleteOnExit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Job ENDED");
	}

}
