package com.reportingtool.scheduler;

import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.FileConfig;
import com.entities.entity.loader.LoadError;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.loader.LoadRaw;
import com.entities.entity.loader.LoadRawData;
import com.entities.entity.usermanager.User;
import com.entities.utilities.hibernate.VersionAuditor;

/**
 * 
 * @author Esteban Calderon
 * 
 * Carga un fichero;
 *
 */
public class FileLoader {

	public FileConfig fileConfig;
	public File file;
	public User loadUser;
	public LoadFile loadFile;

	public FileLoader() {
	}

	public FileLoader(FileConfig fileConfig, File file) {
		this.file = file;
		this.fileConfig = fileConfig;
	}

	public FileLoader(FileConfig fileConfig, String inputFile) {
		this.setInputFile(inputFile);
		this.fileConfig = fileConfig;
	}
	
	public FileConfig getFileConfig(){
		return this.fileConfig;
	}
	
	public void setFileConfig(FileConfig fileConfig){
		this.fileConfig = fileConfig;
	}

	public File getInputFile() {
		return file;
	}

	public void setInputFile(File inputFile) {
		this.file = inputFile;
	}

	public void setInputFile(String inputFile) {
		this.file = new File(inputFile);
		if (!this.file.exists()) {
			throw new RuntimeException("The input file doesn't exist...");
		}
	}
	
	public User getLoadUser(){
		return this.loadUser;
	}
	
	public void setLoadUser(User user){
		this.loadUser = user;
	}
	
	public boolean run() {
		
		//Create LoadFile Object;
		System.out.println("Creating LoadFile Object...");
		this.loadFile = new LoadFile(this.fileConfig.getDepartment(), 
				this.fileConfig, 
				new Date(), 
				this.file.getName(), 
				new HashSet<LoadError>(), 
				new HashSet<LoadRaw>(), 
				new VersionAuditor("admin"));
		
		//Get records of the load file;
		System.out.println("Get records of the load file...");
		List<String> records = LoaderUtilities.loadFileInList(this.file);
		
		//Process records and update LoadFile Object;
		System.out.println("Process records and update LoadFile Object...");
		for(int i=0;i<records.size();i++){
			String record = records.get(i);
			//Create LoadRaw Object;
			LoadRaw loadRaw = new LoadRaw(loadFile, new BigDecimal(i), 
					null, null, null, 
					new HashSet<LoadRawData>(), 
					new VersionAuditor("admin"));
			
			try {
				loadRaw.setLoadRawBlob(new SerialBlob(record.getBytes()));
			} catch (SerialException e) {
				//this.loadFile.getLoadErrors().add()
			} catch (SQLException e) {
				//this.loadFile.getLoadErrors().add()
			}
						
			//Split line;
			System.out.println("Split line...");
			String [] columns = record.split(fileConfig.getFileSeparator());
			
			//Create LoadRawDatas Objects;
			System.out.println("Creating LoadRawDatas objects...");
			System.out.println("FileColumns size: " + fileConfig.getFileColums().size());
			for(FileColum fileColum : fileConfig.getFileColums()){
				LoadRawData loadRawData = new LoadRawData(fileColum, 
						loadRaw, 
						columns[fileColum.getColumNumber().intValue()], 
						fileColum.getColumType(), 
						new VersionAuditor("admin"));
				loadRaw.getLoadRawDatas().add(loadRawData);
			}
			
			loadFile.getLoadRaws().add(loadRaw);
		}
		
		return true;
	}
}
