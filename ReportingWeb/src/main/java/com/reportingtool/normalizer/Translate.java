package com.reportingtool.normalizer;

import java.util.ArrayList;
import java.util.List;

import com.entities.dao.loader.FileColumListDAO;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.FileColumList;
import com.entities.entity.loader.LoadRawData;


public class Translate {

	// main function to translate raw values
	public LoadRawData translateRaw(LoadRawData loadRawData) {

		// find config of column
		FileColum fileColum = loadRawData.getFileColum();

		FileColumListDAO fileColumValueDAO = new FileColumListDAO();
		
		FileColumList fileColumValue1 = new FileColumList();
		fileColumValue1.setFileColum(fileColum);
		List<FileColumList> fileColumLists = new ArrayList<FileColumList>(
				fileColumValueDAO.findByExample(fileColumValue1));
		
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
