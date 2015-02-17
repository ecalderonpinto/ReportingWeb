package com.entities.entity.reportingtool;

// Generated 11-feb-2015 17:15:14 by Hibernate Tools 4.0.0

import com.entities.entity.common.Error;
import com.entities.utilities.hibernate.VersionAuditor;
import com.entities.utilities.hibernate.VersionableAdapter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * ReportDataError generated by hbm2java
 */
@Entity
@Table(name = "T_REPORT_DATA_ERROR")
public class ReportDataError implements VersionableAdapter {

	private long id;
	private ReportData reportData;
	private Error error;
	private String reportDataErrorType;
	private String reportDataErrorText;

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public ReportDataError() {
	}

	public ReportDataError(long reportDataErrorId, ReportData reportData,
			Error error, String reportDataErrorType,
			VersionAuditor versionAuditor) {
		this.id = reportDataErrorId;
		this.reportData = reportData;
		this.error = error;
		this.reportDataErrorType = reportDataErrorType;
		this.versionAuditor = versionAuditor;
	}

	public ReportDataError(ReportData reportData, Error error,
			String reportDataErrorType, String reportDataErrorText,
			VersionAuditor versionAuditor) {
		this.reportData = reportData;
		this.error = error;
		this.reportDataErrorType = reportDataErrorType;
		this.reportDataErrorText = reportDataErrorText;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@SequenceGenerator(name = "GEN_REPORT_DATA_ERROR", sequenceName = "SEQ_REPORT_DATA_ERROR", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_REPORT_DATA_ERROR")
	@Column(name = "REPORT_DATA_ERROR_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long reportDataErrorId) {
		this.id = reportDataErrorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPORT_DATA_ID", nullable = false, foreignKey=@ForeignKey(name="T_REPORT_DATA_ERROR_FK_DATA"))
	public ReportData getReportData() {
		return this.reportData;
	}

	public void setReportData(ReportData reportData) {
		this.reportData = reportData;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ERROR_ID", nullable = false, foreignKey=@ForeignKey(name="T_REPORT_DATA_ERROR_FK_ERROR"))
	public Error getError() {
		return this.error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	@Column(name = "REPORT_DATA_ERROR_TYPE", nullable = false, length = 40)
	public String getReportDataErrorType() {
		return this.reportDataErrorType;
	}

	public void setReportDataErrorType(String reportDataErrorType) {
		this.reportDataErrorType = reportDataErrorType;
	}

	@Column(name = "REPORT_DATA_ERROR_TEXT")
	public String getReportDataErrorText() {
		return this.reportDataErrorText;
	}

	public void setReportDataErrorText(String reportDataErrorText) {
		this.reportDataErrorText = reportDataErrorText;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public VersionAuditor getAuditor() {
		return versionAuditor;
	}

	public void setAuditor(VersionAuditor _auditor) {
		this.versionAuditor = _auditor;
	}

}
