package com.entities.entity.loader;

// Generated 11-feb-2015 16:49:54 by Hibernate Tools 4.0.0

import com.entities.entity.reportingtool.Department;
import com.entities.utilities.hibernate.VersionAuditor;
import com.entities.utilities.hibernate.VersionableAdapter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * LoadFile generated by hbm2java
 */
@Entity
@Table(name = "T_LOAD_FILE")
public class LoadFile implements VersionableAdapter {

	private long id;
	private Department department;
	private FileConfig fileConfig;
	private Date loadFileDate;
	private String loadFileName;
	private Set<LoadError> loadErrors = new HashSet(0);
	private Set<LoadRaw> loadRaws = new HashSet(0);

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name="VERSION", nullable=false)
	int version;
	
	public LoadFile() {
	}

	public LoadFile(long loadFileId, Department department,
			FileConfig fileConfig, VersionAuditor versionAuditor) {
		this.id = loadFileId;
		this.department = department;
		this.fileConfig = fileConfig;
		this.versionAuditor = versionAuditor;
	}

	public LoadFile(Department department,
			FileConfig fileConfig, Date loadFileDate, String loadFileName,
			Set<LoadError> loadErrors, Set<LoadRaw> loadRaws, VersionAuditor versionAuditor) {
		this.department = department;
		this.fileConfig = fileConfig;
		this.loadFileDate = loadFileDate;
		this.loadFileName = loadFileName;
		this.loadErrors = loadErrors;
		this.loadRaws = loadRaws;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@SequenceGenerator(name="GEN_LOAD_FILE", sequenceName="SEQ_LOAD_FILE", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GEN_LOAD_FILE")
	@Column(name = "LOAD_FILE_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long loadFileId) {
		this.id = loadFileId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT_ID", nullable = false)
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FILE_CONFIG_ID", nullable = false)
	public FileConfig getFileConfig() {
		return this.fileConfig;
	}

	public void setFileConfig(FileConfig fileConfig) {
		this.fileConfig = fileConfig;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LOAD_FILE_DATE", length = 7)
	public Date getLoadFileDate() {
		return this.loadFileDate;
	}

	public void setLoadFileDate(Date loadFileDate) {
		this.loadFileDate = loadFileDate;
	}

	@Column(name = "LOAD_FILE_NAME")
	public String getLoadFileName() {
		return this.loadFileName;
	}

	public void setLoadFileName(String loadFileName) {
		this.loadFileName = loadFileName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loadFile")
	public Set<LoadError> getLoadErrors() {
		return this.loadErrors;
	}

	public void setLoadErrors(Set<LoadError> loadErrors) {
		this.loadErrors = loadErrors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loadFile")
	public Set<LoadRaw> getLoadRaws() {
		return this.loadRaws;
	}

	public void setLoadRaws(Set<LoadRaw> loadRaws) {
		this.loadRaws = loadRaws;
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
