package com.entities.entity.reportingtool;

// Generated 11-feb-2015 17:15:14 by Hibernate Tools 4.0.0

import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.entities.utilities.hibernate.VersionAuditor;
import com.entities.utilities.hibernate.VersionableAdapter;

/**
 * ReportDataLong generated by hbm2java
 */
@Entity
@Table(name = "T_REPORT_DATA_LONG")
public class ReportDataLong implements VersionableAdapter {

	private long id;
	private Blob reportDataBlob;
	private Set<ReportData> reportDatas = new HashSet(0);

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public ReportDataLong() {
	}

	public ReportDataLong(long reportDataLongId, Blob reportDataBlob,
			VersionAuditor versionAuditor) {
		this.id = reportDataLongId;
		this.reportDataBlob = reportDataBlob;
		this.versionAuditor = versionAuditor;
	}

	public ReportDataLong(Blob reportDataBlob, Set<ReportData> reportDatas,
			VersionAuditor versionAuditor) {
		this.reportDataBlob = reportDataBlob;
		this.reportDatas = reportDatas;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@SequenceGenerator(name = "GEN_REPORT_DATA_LONG", sequenceName = "SEQ_REPORT_DATA_LONG", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_REPORT_DATA_LONG")
	@Column(name = "REPORT_DATA_LONG_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long reportDataLongId) {
		this.id = reportDataLongId;
	}

	@Column(name = "REPORT_DATA_BLOB", nullable = false)
	public Blob getReportDataBlob() {
		return this.reportDataBlob;
	}

	public void setReportDataBlob(Blob reportDataBlob) {
		this.reportDataBlob = reportDataBlob;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reportDataLong")
	public Set<ReportData> getReportDatas() {
		return this.reportDatas;
	}

	public void setReportDatas(Set<ReportData> reportDatas) {
		this.reportDatas = reportDatas;
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
