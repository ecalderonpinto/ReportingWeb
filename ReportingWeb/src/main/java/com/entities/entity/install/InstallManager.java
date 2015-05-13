package com.entities.entity.install;

import org.springframework.context.ApplicationContext;

import com.entities.dao.common.ErrorDAO;
import com.entities.dao.loader.FileColumDAO;
import com.entities.dao.loader.FileColumListDAO;
import com.entities.dao.loader.FileConfigDAO;
import com.entities.dao.loader.LoadErrorDAO;
import com.entities.dao.loader.LoadFileDAO;
import com.entities.dao.loader.LoadRawDAO;
import com.entities.dao.loader.LoadRawDataDAO;
import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.dao.reportingtool.DepartmentDAO;
import com.entities.dao.reportingtool.FundDAO;
import com.entities.dao.reportingtool.FundGroupDAO;
import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.dao.reportingtool.ReportDataDAO;
import com.entities.dao.reportingtool.ReportDataErrorDAO;
import com.entities.dao.reportingtool.ReportErrorDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.dao.reportingtool.ReportFieldDAO;
import com.entities.dao.reportingtool.ReportFieldListDAO;
import com.entities.dao.reportingtool.ReportSemanticDAO;
import com.entities.dao.usermanager.UserControlDAO;
import com.entities.dao.usermanager.UserDAO;
import com.entities.dao.usermanager.UserRolDAO;
import com.entities.dao.usermanager.UserRolPermissionDAO;

/**
 * Class to install configuration in database
 * 
 * @author alberto.olivan
 *
 */
public class InstallManager {

	private ApplicationContext applicationContext;

	public InstallManager(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void installAll() {

		// reportCatalog and reportFields from AIFM
		InstallAIFM installAIFM = new InstallAIFM(applicationContext);
		installAIFM.install();

		// reportCatalog and reportFields from AIFM
		InstallAIF installAIF = new InstallAIF(applicationContext);
		installAIF.install();

		// reportFieldList
		InstallReportList installReportList = new InstallReportList(
				applicationContext);
		installReportList.install();

		// error
		InstallError installError = new InstallError(applicationContext);
		installError.install();

		// user
		InstallUser installUser = new InstallUser(applicationContext);
		installUser.install();
	}

	/**
	 * Process to delete all data from database
	 * 
	 * @param applicationContext
	 */
	public void deleteAll() {

		UserControlDAO userControlDAO = (UserControlDAO) applicationContext
				.getBean("userControlDAO");
		userControlDAO.deleteAll();

		UserDAO userDAO = (UserDAO) applicationContext.getBean("userDAO");
		userDAO.deleteAll();

		UserRolPermissionDAO userRolPermissionDAO = (UserRolPermissionDAO) applicationContext
				.getBean("userRolPermissionDAO");
		userRolPermissionDAO.deleteAll();

		UserRolDAO userRolDAO = (UserRolDAO) applicationContext
				.getBean("userRolDAO");
		userRolDAO.deleteAll();

		ReportSemanticDAO reportSemanticDAO = (ReportSemanticDAO) applicationContext
				.getBean("reportSemanticDAO");
		reportSemanticDAO.deleteAll();

		FileColumListDAO fileColumListDAO = (FileColumListDAO) applicationContext
				.getBean("fileColumListDAO");
		fileColumListDAO.deleteAll();

		ReportErrorDAO reportErrorDAO = (ReportErrorDAO) applicationContext
				.getBean("reportErrorDAO");
		reportErrorDAO.deleteAll();

		ReportDataErrorDAO reportDataErrorDAO = (ReportDataErrorDAO) applicationContext
				.getBean("reportDataErrorDAO");
		reportDataErrorDAO.deleteAll();

		LoadErrorDAO loadErrorDAO = (LoadErrorDAO) applicationContext
				.getBean("loadErrorDAO");
		loadErrorDAO.deleteAll();

		ErrorDAO errorDAO = (ErrorDAO) applicationContext.getBean("errorDAO");
		errorDAO.deleteAll();

		LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO) applicationContext
				.getBean("loadRawDataDAO");
		loadRawDataDAO.deleteAll();

		LoadRawDAO loadRawDAO = (LoadRawDAO) applicationContext
				.getBean("loadRawDAO");
		loadRawDAO.deleteAll();

		ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
				.getBean("reportDataDAO");
		reportDataDAO.deleteAll();

		FileColumDAO fileColumDAO = (FileColumDAO) applicationContext
				.getBean("fileColumDAO");
		fileColumDAO.deleteAll();

		ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) applicationContext
				.getBean("reportFieldListDAO");
		reportFieldListDAO.deleteAll();

		ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
				.getBean("reportFieldDAO");
		reportFieldDAO.deleteAll();

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");
		reportExecutionDAO.deleteAll();

		LoadFileDAO loadFileDAO = (LoadFileDAO) applicationContext
				.getBean("loadFileDAO");
		loadFileDAO.deleteAll();

		FileConfigDAO fileConfigDAO = (FileConfigDAO) applicationContext
				.getBean("fileConfigDAO");
		fileConfigDAO.deleteAll();

		ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
				.getBean("reportCatalogDAO");
		reportCatalogDAO.deleteAll();

		FundGroupDAO fundGroupDAO = (FundGroupDAO) applicationContext
				.getBean("fundGroupDAO");
		fundGroupDAO.deleteAll();

		FundDAO fundDAO = (FundDAO) applicationContext.getBean("fundDAO");
		fundDAO.deleteAll();

		DepartmentDAO departmentDAO = (DepartmentDAO) applicationContext
				.getBean("departmentDAO");
		departmentDAO.deleteAll();

		CompanyDAO companyDAO = (CompanyDAO) applicationContext
				.getBean("companyDAO");
		companyDAO.deleteAll();

	}
}
