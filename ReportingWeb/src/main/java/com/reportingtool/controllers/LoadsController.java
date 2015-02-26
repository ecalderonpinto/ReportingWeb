package com.reportingtool.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.entities.dao.loader.FileConfigDAO;
import com.entities.dao.loader.LoadFileDAO;
import com.entities.entity.loader.FileConfig;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.loader.LoadRaw;
import com.entities.entity.loader.LoadRawData;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.controllers.forms.LoadFileForm;
import com.reportingtool.normalizer.Format;
import com.reportingtool.normalizer.Translate;
import com.reportingtool.scheduler.FileLoader;
import com.reportingtool.utilities.ReportingErrorManager;

@Controller
@RequestMapping(value = "/loads.do")
@SessionAttributes("loadFile")
public class LoadsController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(LoadsController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String DataManagerControllerPre(Locale locale, Model model) {

		LoadFileDAO loadFileDao = (LoadFileDAO) applicationContext
				.getBean("loadFileDAO");
		List<LoadFile> loads = loadFileDao.findAll();

		LoadFileForm form = new LoadFileForm();

		System.out.println(loads.size() + " loads");
		model.addAttribute("loads", loads);
		model.addAttribute("loadFile", form);
		model.addAttribute("alerts", false);
		System.out.println("- Loads Controller -");
		return "loads";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("loadFile") LoadFileForm loadFileForm, Model model) {

		AlertToView alert = new AlertToView(false, "All were success");

		if (!loadFileForm.getInputFile().isEmpty()) {

			try {
				FileConfigDAO fileConfigDAO = (FileConfigDAO) applicationContext
						.getBean("fileConfigDAO");
				FileConfig fileConfig = fileConfigDAO.findById(Long
						.parseLong(loadFileForm.getSelectFileConfigs()));

				InputStream st = loadFileForm.getInputFile().getInputStream();
				FileLoader fileLoader = new FileLoader(fileConfig, loadFileForm
						.getInputFile().getInputStream(), loadFileForm
						.getInputFile().getOriginalFilename());

				LoadFile loadFile = fileLoader.run();
				if (loadFile != null) {
					System.out.println(loadFile.getLoadFileName()
							+ " is goint to be loader");
				} else {
					alert.setError(true);
					alert.setMessage("Error loading file");
					ReportingErrorManager.createLoadError(applicationContext,
							"LOADER", loadFile, "PARSING", "Error parsing "
									+ loadFile.getLoadFileName() + " file");
				}

				// Save LoadFile in DataBase;
				LoadFileDAO loadFileDAO = (LoadFileDAO) this.applicationContext
						.getBean("loadFileDAO");
				loadFileDAO.create(loadFile);

				// Validate Proccess;
				for (LoadRaw loadRaw : loadFile.getLoadRaws()) {
					for (LoadRawData loadRawData : loadRaw.getLoadRawDatas()) {
						Translate translate = new Translate(applicationContext);
						translate.translateRaw(loadRawData);

						Format format = new Format(applicationContext);
						format.formatRaw(loadRawData);
					}
				}
			} catch (IOException e) {
				alert.setError(true);
				alert.setMessage("IO Problem");
				System.out.println("IO Problem");
				e.printStackTrace();
			} catch (SerialException e) {
				alert.setError(true);
				alert.setMessage("Error parsing the file");
				System.out.println("Error parsing the file");
				e.printStackTrace();
			} catch (SQLException e) {
				alert.setError(true);
				alert.setMessage("Error inserting in database");
				System.out.println("Error inserting in database");
				e.printStackTrace();
			}
		} else {
			alert.setError(true);
			alert.setMessage("Failed to upload because the file was empty");
			System.out
					.println("Failed to upload because the file was empty.");
		}

		model.addAttribute("alerts", true);
		model.addAttribute("alert", alert);
		return "loads";
	}

	@ModelAttribute("selectFileConfigs")
	public Map<String, String> populateJavaSkillList() {

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