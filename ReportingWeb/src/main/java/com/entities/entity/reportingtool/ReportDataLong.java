package com.entities.entity.reportingtool;

// Generated 11-feb-2015 17:15:14 by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	private byte[] reportDataBlob;
	private List<ReportData> reportDatas = new ArrayList<ReportData>();

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public ReportDataLong() {
	}

	public ReportDataLong(long reportDataLongId, byte[] reportDataBlob,
			VersionAuditor versionAuditor) {
		this.id = reportDataLongId;
		this.reportDataBlob = reportDataBlob;
		this.versionAuditor = versionAuditor;
	}

	public ReportDataLong(byte[] reportDataBlob, List<ReportData> reportDatas,
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
	public byte[] getReportDataBlob() {
		return this.reportDataBlob;
	}

	public void setReportDataBlob(byte[] reportDataBlob) {
		this.reportDataBlob = reportDataBlob;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reportDataLong")
	public List<ReportData> getReportDatas() {
		return this.reportDatas;
	}

	public void setReportDatas(List<ReportData> reportDatas) {
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

	public VersionAuditor getVersionAuditor() {
		return versionAuditor;
	}

	public void setVersionAuditor(VersionAuditor _auditor) {
		this.versionAuditor = _auditor;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof ReportDataLong) {
			return Arrays.equals(((ReportDataLong) object).getReportDataBlob(),
					this.reportDataBlob)
					&& ((ReportDataLong) object).getReportDatas().equals(
							this.reportDatas);

		}
		return false;
	}
}
