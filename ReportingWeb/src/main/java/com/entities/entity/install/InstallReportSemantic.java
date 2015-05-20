package com.entities.entity.install;

import org.springframework.context.ApplicationContext;

import com.entities.entity.reportingtool.ReportCatalog;

public class InstallReportSemantic {

	private ApplicationContext applicationContext;

	public InstallReportSemantic(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void installAIFM(ReportCatalog reportCatalog) {

		//VersionAuditor versionAdmin = new VersionAuditor("admin");

	}
}
