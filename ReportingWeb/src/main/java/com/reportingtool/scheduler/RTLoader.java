package com.reportingtool.scheduler;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.entities.entity.loader.FileConfig;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.usermanager.User;

/**
 * 
 * @author Esteban Calderon
 * 
 * Carga un fichero;
 *
 */
public class RTLoader {

	public FileConfig fileConfig;
	public File file;
	public User loadUser;
	public LoadFile loadFile;

	public RTLoader() {
	}

	public RTLoader(FileConfig fileConfig, File file) {
		this.file = file;
		this.fileConfig = fileConfig;
	}

	public RTLoader(FileConfig fileConfig, String inputFile) {
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
		/*
		loadFile = new LoadFile();
		loadFile.setfileConfig(this.fileConfig);
		loadFile.setAssetManagement(this.fileConfig.getAssetManagement());
		loadFile.setLoadFileDate(new Date());
		loadFile.setLoadFileName(this.file.getName());
		loadFile.setMetadata(RTUtilities.createMetadata(loadUser.getUserId().toString()));
		
		//Get records of the load file;
		List<String> records = LoaderUtilities.loadFileInList(this.file);
		
		//Process records and update LoadFile Object;
		for(int i=0;i<records.size();i++){
			String record = records.get(i);
			//Create LoadRaw Object;
			LoadRaw loadRaw = new LoadRaw();
			loadRaw.setLoadFile(loadFile);
			loadRaw.setLoadLineNumber(new BigDecimal(i+1));
			try {
				loadRaw.setLoadRawBlob(new SerialBlob(record.getBytes()));
			} catch (SerialException e) {
				setAlert();
			} catch (SQLException e) {
				setAlert();
			}
			loadRaw.setMetadata(RTUtilities.createMetadata(loadUser.getUserId().toString()));
			
			//Split line;
			String [] columns = record.split(fileConfig.getFileSeparator());
			
			//Create aifmdRawDatas Objects;
			for(FileColum fileColumn : (Set<FileColum>)fileConfig.getFileColums()){
				AifmdRawData rawData = new AifmdRawData();
				rawData.setAifmdRawDataText(columns[fileColumn.getColumNumber().intValue()]);
				rawData.setAifmdRawDataType(fileColumn.getColumType());
				rawData.setFileColum(fileColumn);
				rawData.setLoadRaw(loadRaw);
				
				loadRaw.getAifmdRawDatas().add(rawData);
			}
			
			loadFile.getLoadRaws().add(loadRaw);
		}
		*/
		return true;
	}
	
	private void setAlert(){
		
	}
}
