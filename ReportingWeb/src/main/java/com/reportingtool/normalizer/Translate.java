package com.reportingtool.normalizer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.entities.dao.loader.FileColumListDAO;
import com.entities.dao.loader.LoadRawDataDAO;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.FileColumList;
import com.entities.entity.loader.LoadRawData;

public class Translate {

	private ApplicationContext applicationContext;

	public Translate(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	// main function to translate raw values
	public LoadRawData translateRaw(LoadRawData loadRawData) {

		// find config of column
		FileColum fileColum = loadRawData.getFileColum();

		List<FileColumList> fileColumLists = new ArrayList<FileColumList>(
				fileColum.getFileColumLists());

		System.out.println("DEBUG_" + "Translate: fileColum "
				+ fileColum.getColumName() + " "
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

			// save new value
			loadRawData.setLoadRawDataText(valueDest);

			LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO) applicationContext
					.getBean("loadRawDataDAO");
			loadRawDataDAO.edit(loadRawData);
		}

		return loadRawData;
	}
}
