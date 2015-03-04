package com.reportingtool.scheduler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.context.ApplicationContext;

import com.entities.dao.loader.LoadRawDAO;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.FileConfig;
import com.entities.entity.loader.LoadError;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.loader.LoadRaw;
import com.entities.entity.loader.LoadRawData;
import com.entities.entity.usermanager.User;
import com.entities.utilities.hibernate.VersionAuditor;
import com.reportingtool.utilities.ReportingErrorManager;

/**
 * 
 * @author Esteban Calderon
 * 
 *         Carga un fichero;
 *
 */
public class FileLoader {

	public FileConfig fileConfig;
	public InputStream file;
	public String fileName;
	public User loadUser;

	public FileLoader() {
	}

	public FileLoader(FileConfig fileConfig, File file)
			throws FileNotFoundException {
		this.file = new FileInputStream(file);
		this.fileConfig = fileConfig;
		this.fileName = file.getName();
	}

	public FileLoader(FileConfig fileConfig, String path)
			throws FileNotFoundException {
		this.setInputFile(path);
		this.fileConfig = fileConfig;
	}

	public FileLoader(FileConfig fileConfig, InputStream file, String fileName)
			throws FileNotFoundException {
		this.fileConfig = fileConfig;
		this.file = file;
		this.fileName = fileName;
	}

	public FileConfig getFileConfig() {
		return this.fileConfig;
	}

	public void setFileConfig(FileConfig fileConfig) {
		this.fileConfig = fileConfig;
	}

	public InputStream getInputFile() {
		return file;
	}

	public void setInputFile(FileInputStream inputFile) {
		this.file = inputFile;
	}

	public void setInputFile(String inputFile) throws FileNotFoundException {
		File file = new File(inputFile);
		this.file = new FileInputStream(file);
		this.fileName = file.getName();
	}

	public User getLoadUser() {
		return this.loadUser;
	}

	public void setLoadUser(User user) {
		this.loadUser = user;
	}

	public LoadFile run(ApplicationContext applicationContext)
			throws FileNotFoundException, IOException, SerialException,
			SQLException {

		// Create LoadFile Object;
		LoadFile loadFile = new LoadFile(this.fileConfig.getDepartment(),
				this.fileConfig, new Date(), this.fileName,
				new HashSet<LoadError>(), new HashSet<LoadRaw>(),
				new VersionAuditor("admin"));

		// Get records of the load file;
		List<String> records = LoaderUtilities.loadFileInList(this.file);

		// Process records and update LoadFile Object;
		for (int i = 0; i < records.size(); i++) {
			String record = records.get(i);
			// Create LoadRaw Object;
			LoadRaw loadRaw = new LoadRaw(loadFile, new BigDecimal(i), null,
					null, null, new HashSet<LoadRawData>());

			loadRaw.setLoadRawBlob(record.getBytes());

			if (!existLoadRaw(loadRaw, applicationContext)) {
				System.out.println("DEBUG: añade nuevo");
				// Split line;
				String separator = fileConfig.getFileSeparator();
				String[] columns = record.split(separator, -1);

				// Create LoadRawDatas Objects;
				for (FileColum fileColum : fileConfig.getFileColums()) {
					LoadRawData loadRawData = new LoadRawData(fileColum,
							loadRaw, columns[fileColum.getColumNumber()
									.intValue()], fileColum.getColumType(),
							new VersionAuditor("admin"));
					loadRawData.setLoadRawDataBlock(fileColum.getColumBlock());
					loadRaw.getLoadRawDatas().add(loadRawData);
				}

				// Set LoadRaw in LoadFile
				loadFile.getLoadRaws().add(loadRaw);
			} else {
				System.out.println("DEBUG: repetido - no se añade");
			}
		}

		if (loadFile.getLoadRaws().isEmpty()) {
			ReportingErrorManager.createLoadError(applicationContext, "LOADER",
					loadFile, "FILE EMPTY",
					"No rows found in " + loadFile.getLoadFileName());
		}

		return loadFile;
	}

	private boolean existLoadRaw(LoadRaw loadRaw,
			ApplicationContext applicationContext) {

		boolean result = false;

		LoadRawDAO loadRawDAO = (LoadRawDAO) applicationContext
				.getBean("loadRawDAO");

		List<LoadRaw> loadRaws = loadRawDAO.findAll();
		for (LoadRaw lr : loadRaws) {
			if (lr.equals(loadRaw)) {
				result = true;
				break;
			}
		}

		return result;
	}
}
