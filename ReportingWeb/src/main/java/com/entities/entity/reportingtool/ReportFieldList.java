package com.entities.entity.reportingtool;

// Generated 11-feb-2015 17:15:14 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.entities.utilities.hibernate.VersionAuditor;
import com.entities.utilities.hibernate.VersionableAdapter;

/**
 * ReportFieldList generated by hbm2java
 */
@Entity
@Table(name = "T_REPORT_FIELD_LIST", uniqueConstraints = @UniqueConstraint(columnNames = {
		"REPORT_FIELD_LIST_TYPE", "REPORT_FIELD_LIST_VALUE" }))
public class ReportFieldList implements VersionableAdapter {

	private long id;
	private String reportFieldListType;
	private String reportFieldListValue;
	private String reportFieldListDesc;

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public ReportFieldList() {
	}

	public ReportFieldList(long reportFieldListId, String reportFieldListType,
			VersionAuditor versionAuditor) {
		this.id = reportFieldListId;
		this.reportFieldListType = reportFieldListType;
		this.versionAuditor = versionAuditor;
	}

	public ReportFieldList(String reportFieldListType,
			String reportFieldListValue, String reportFieldListDesc,
			VersionAuditor versionAuditor) {
		this.reportFieldListType = reportFieldListType;
		this.reportFieldListValue = reportFieldListValue;
		this.reportFieldListDesc = reportFieldListDesc;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@SequenceGenerator(name = "GEN_REPORT_FIELD_LIST", sequenceName = "SEQ_REPORT_FIELD_LIST", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_REPORT_FIELD_LIST")
	@Column(name = "REPORT_FIELD_LIST_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long reportFieldListId) {
		this.id = reportFieldListId;
	}

	@Column(name = "REPORT_FIELD_LIST_TYPE", nullable = false)
	public String getReportFieldListType() {
		return this.reportFieldListType;
	}

	public void setReportFieldListType(String reportFieldListType) {
		this.reportFieldListType = reportFieldListType;
	}

	@Column(name = "REPORT_FIELD_LIST_VALUE")
	public String getReportFieldListValue() {
		return this.reportFieldListValue;
	}

	public void setReportFieldListValue(String reportFieldListValue) {
		this.reportFieldListValue = reportFieldListValue;
	}

	@Column(name = "REPORT_FIELD_LIST_DESC")
	public String getReportFieldListDesc() {
		return this.reportFieldListDesc;
	}

	public void setReportFieldListDesc(String reportFieldListDesc) {
		this.reportFieldListDesc = reportFieldListDesc;
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

	public VersionAuditor getVersionAuditor() {
		return versionAuditor;
	}

	public void setVersionAuditor(VersionAuditor _auditor) {
		this.versionAuditor = _auditor;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof ReportFieldList) {
			return ((ReportFieldList) object).getReportFieldListType().equals(
					this.reportFieldListType)
					&& ((ReportFieldList) object).getReportFieldListValue()
							.equals(this.reportFieldListValue);

		}
		return false;
	}
}
