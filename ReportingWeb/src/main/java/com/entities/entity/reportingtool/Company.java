package com.entities.entity.reportingtool;

// Generated 11-feb-2015 16:49:54 by Hibernate Tools 4.0.0

import java.util.ArrayList;
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
 * Company generated by hbm2java
 */
@Entity
@Table(name = "T_COMPANY")
public class Company implements VersionableAdapter {

	private long id;
	private String companyName;
	private String companyCountry;
	private String companyCode;
	private String companyDesc;
	private List<ReportExecution> reportExecutions = new ArrayList<ReportExecution>();
	private List<Fund> funds = new ArrayList<Fund>();
	private List<Department> departments = new ArrayList<Department>();
	private List<ReportCustom> reportCustoms = new ArrayList<ReportCustom>();

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public Company() {
	}

	public Company(long companyId, String companyName,
			VersionAuditor versionAuditor) {
		this.id = companyId;
		this.companyName = companyName;
		this.versionAuditor = versionAuditor;
	}

	public Company(String companyName, String companyCountry,
			String companyCode, String companyDesc,
			VersionAuditor versionAuditor) {
		this.companyName = companyName;
		this.companyCountry = companyCountry;
		this.companyCode = companyCode;
		this.companyDesc = companyDesc;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@SequenceGenerator(name = "GEN_COMPANY", sequenceName = "SEQ_COMPANY", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_COMPANY")
	@Column(name = "COMPANY_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long companyId) {
		this.id = companyId;
	}

	@Column(name = "COMPANY_NAME", nullable = false)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "COMPANY_COUNTRY")
	public String getCompanyCountry() {
		return this.companyCountry;
	}

	public void setCompanyCountry(String companyCountry) {
		this.companyCountry = companyCountry;
	}

	@Column(name = "COMPANY_CODE", length = 40)
	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Column(name = "COMPANY_DESC")
	public String getCompanyDesc() {
		return this.companyDesc;
	}

	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public List<ReportExecution> getReportExecutions() {
		return this.reportExecutions;
	}

	public void setReportExecutions(List<ReportExecution> reportExecutions) {
		this.reportExecutions = reportExecutions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public List<Fund> getFunds() {
		return this.funds;
	}

	public void setFunds(List<Fund> funds) {
		this.funds = funds;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public List<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public List<ReportCustom> getReportCustoms() {
		return this.reportCustoms;
	}

	public void setReportCustoms(List<ReportCustom> reportCustoms) {
		this.reportCustoms = reportCustoms;
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
		if (object instanceof Company) {
			return ((Company) object).getCompanyName().equals(this.companyName)
					&& ((Company) object).getCompanyCode().equals(
							this.companyCode)
					&& ((Company) object).getCompanyCountry().equals(
							this.companyCountry);

		}
		return false;
	}
}
