package com.entities.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;

public class SortReportField {

	// TODO:RT this class is not used
	
	public static void sortReportExecution (ReportExecution reportExecution) {
		
		List<ReportField>reportFields = new ArrayList<ReportField>(reportExecution.getReportCatalog().getReportFields());
		
		Comparator<ReportField> comparator = new Comparator<ReportField>() {
		    public int compare(ReportField c1, ReportField c2) {
		    	return c1.getReportFieldNum().compareTo(c2.getReportFieldNum());
		    }
		};

		Collections.sort(reportFields, comparator);
		
//		ReportCatalog reportCatalog = new ReportCatalog();
//		reportCatalog = reportExecution.getReportCatalog();
//		reportCatalog.setReportFields((Set<ReportField>) reportFields);
//		
//		reportExecution.setReportCatalog(reportCatalog);
//		
//		System.out.println("Collection sorted: " + reportFields);
		
	}
}
