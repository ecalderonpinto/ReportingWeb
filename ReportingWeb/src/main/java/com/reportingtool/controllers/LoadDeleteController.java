package com.reportingtool.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.dao.loader.FileConfigDAO;
import com.entities.dao.loader.LoadErrorDAO;
import com.entities.dao.loader.LoadFileDAO;
import com.entities.dao.loader.LoadRawDAO;
import com.entities.dao.loader.LoadRawDataDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.entity.loader.FileConfig;
import com.entities.entity.loader.LoadError;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.loader.LoadRaw;
import com.entities.entity.loader.LoadRawData;
import com.entities.entity.reportingtool.ReportExecution;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.controllers.forms.LoadFileForm;

@Controller
@RequestMapping(value = "/loadDelete.do")
public class LoadDeleteController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(LoadDetailController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String LoadDeleteControllerPre(@RequestParam("id") String id,
			Model model) {

		System.out.println("LoadDelete Controller - id=" + id);

		LoadFileDAO loadFileDao = (LoadFileDAO) applicationContext
				.getBean("loadFileDAO");
		LoadFile loadFile = loadFileDao.findById(Long.parseLong(id));

//		LoadErrorDAO loadErrorDAO = (LoadErrorDAO) applicationContext
//				.getBean("loadErrorDAO");

		LoadRawDAO loadRawDAO = (LoadRawDAO) applicationContext
				.getBean("loadRawDAO");

//		LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO) applicationContext
//				.getBean("loadRawDataDAO");

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");

		AlertToView alert = new AlertToView();

		try {
			List<ReportExecution> reportExecutionList = loadFile
					.getReportExecutions();
			for (ReportExecution reportExecution : reportExecutionList) {
				reportExecution.getLoadFiles().remove(loadFile);
				reportExecutionDAO.edit(reportExecution);
			}

			List<LoadRaw> loadRawList = loadFile.getLoadRaws();

			// List<LoadError> loadErrorList = loadFile.getLoadErrors();
			// for (LoadError loadError : loadErrorList) {
			// loadFile.getLoadErrors().remove(loadError);
			// //loadErrorDAO.delete(loadError);
			// }

			for (LoadRaw loadRaw : loadRawList) {
				List<LoadRawData> loadRawDataList = loadRaw.getLoadRawDatas();
				for (LoadRawData loadRawData : loadRawDataList) {
					loadRawData.getLoadRaw().getLoadRawDatas()
							.remove(loadRawData);
					// loadRawDataDAO.delete(loadRawData);
				}
				loadRawDAO.edit(loadRaw);
				// loadRawDAO.delete(loadRaw);
				// loadFile.getLoadRaws().remove(loadRaw);
			}

			String fileName = loadFile.getLoadFileName();
			System.out.println("borrando " + fileName);
			loadFileDao.delete(loadFile);

			alert.setError(false);
			alert.setMessage("Delete file " + fileName + " success");

		} catch (Exception e) {
			e.printStackTrace();
			alert.setError(true);
			alert.setMessage("Error when deleting file: " + e.getMessage());
		}

		// load again info to show in loads page
		List<LoadFile> loads = loadFileDao.findAll();

		LoadFileForm form = new LoadFileForm();

		System.out.println(loads.size() + " loads");

		model.addAttribute("loads", loads);
		model.addAttribute("loadFile", form);
		model.addAttribute("alerts", true);
		model.addAttribute("alert", alert);

		return "loads";
	}

	@ModelAttribute("selectFileConfigs")
	public Map<String, String> populateFileConfigSelect() {

		System.out.println("preLoad select");

		FileConfigDAO fileConfigDao = (FileConfigDAO) applicationContext
				.getBean("fileConfigDAO");
		List<FileConfig> configs = fileConfigDao.findAll();

		Map<String, String> selectFileConfigs = new HashMap<String, String>();
		for (FileConfig config : configs) {
			selectFileConfigs.put(Long.toString(config.getId()),
					config.getFileConfigName());
		}

		return selectFileConfigs;
	}

}
