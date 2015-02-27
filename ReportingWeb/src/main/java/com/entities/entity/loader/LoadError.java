package com.entities.entity.loader;

// Generated 11-feb-2015 16:49:54 by Hibernate Tools 4.0.0

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

import com.entities.entity.common.Error;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.utilities.hibernate.VersionAuditor;
import com.entities.utilities.hibernate.VersionableAdapter;

/**
 * LoadError generated by hbm2java
 */
@Entity
@Table(name = "T_LOAD_ERROR")
public class LoadError implements VersionableAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2267942217079301523L;
	private long id;
	private Error error;
	private LoadFile loadFile;
	private String loadErrorType;
	private String loadErrorText;

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public LoadError() {
	}

	public LoadError(long loadErrorId, Error error, LoadFile loadFile,
			String loadErrorType, VersionAuditor versionAuditor) {
		this.id = loadErrorId;
		this.error = error;
		this.loadFile = loadFile;
		this.loadErrorType = loadErrorType;
		this.versionAuditor = versionAuditor;
	}

	public LoadError(Error error, LoadFile loadFile, String loadErrorType,
			String loadErrorText, VersionAuditor versionAuditor) {
		this.error = error;
		this.loadFile = loadFile;
		this.loadErrorType = loadErrorType;
		this.loadErrorText = loadErrorText;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@SequenceGenerator(name = "GEN_LOAD_ERROR", sequenceName = "SEQ_LOAD_ERROR", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_LOAD_ERROR")
	@Column(name = "LOAD_ERROR_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long loadErrorId) {
		this.id = loadErrorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ERROR_ID", nullable = false, foreignKey = @ForeignKey(name = "T_LOAD_ERROR_FK_ERROR"))
	public Error getError() {
		return this.error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAD_FILE_ID", nullable = false, foreignKey = @ForeignKey(name = "T_LOAD_ERROR_FK_FILE"))
	public LoadFile getLoadFile() {
		return this.loadFile;
	}

	public void setLoadFile(LoadFile loadFile) {
		this.loadFile = loadFile;
	}

	@Column(name = "LOAD_ERROR_TYPE", nullable = false, length = 40)
	public String getLoadErrorType() {
		return this.loadErrorType;
	}

	public void setLoadErrorType(String loadErrorType) {
		this.loadErrorType = loadErrorType;
	}

	@Column(name = "LOAD_ERROR_TEXT")
	public String getLoadErrorText() {
		return this.loadErrorText;
	}

	public void setLoadErrorText(String loadErrorText) {
		this.loadErrorText = loadErrorText;
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

	@Override
	public boolean equals(Object object) {
		if (object instanceof LoadError) {
			return ((LoadError) object).error.equals(this.error)
					&& ((LoadError) object).loadErrorText
							.equals(this.loadErrorText)
					&& ((LoadError) object).loadErrorType
							.equals(this.loadErrorType)
					&& ((LoadError) object).loadFile.equals(this.loadFile);

		}
		return false;
	}
}
