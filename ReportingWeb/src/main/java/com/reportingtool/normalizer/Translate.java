package com.reportingtool.normalizer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.entities.dao.loader.FileColumListDAO;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.FileColumList;
import com.entities.entity.loader.LoadRawData;


public class Translate {
	
	
	private ApplicationContext aplicationContext;
	
	public Translate (ApplicationContext aplicationContext) {
		this.aplicationContext = aplicationContext;
	}

	// main function to translate raw values
	public LoadRawData translateRaw(LoadRawData loadRawData) {

		// find config of column
		FileColum fileColum = loadRawData.getFileColum();

		FileColumListDAO fileColumListDAO = (FileColumListDAO) aplicationContext
				.getBean("fileColumListDAO");
		
		FileColumList fileColumListExample = new FileColumList();
		fileColumListExample.setFileColum(fileColum);
		List<FileColumList> fileColumLists = new ArrayList<FileColumList>(
				fileColumListDAO.findByExample(fileColumListExample));
		
		System.out.println("DEBUG_" + "Translate: fileColum " +fileColum.getColumName());
		
		System.out.println("DEBUG_" + "Translate: "
				+ loadRawData.getLoadRawDataText());
		// a column may or may not have something to translate

		if (!fileColumLists.isEmpty()) {
			String valueOrig = loadRawData.getLoadRawDataText();
			String valueDest = "";

			// find in list the value to translate
			for (FileColumList fileColumList : fileColumLists) {
				System.out.println("DEBUG_" + "Translate:  Dest "
						+ fileColumList.getFileColumListDest());
				if (fileColumList.getFileColumListOrig().equals(valueOrig))
					valueDest = fileColumList.getFileColumListDest();
			}
			System.out.println("DEBUG_" + "Translate: Orig " + valueOrig
					+ " Dest " + valueDest);

			loadRawData.setLoadRawDataText(valueDest);
		}

		return loadRawData;
	}
}
