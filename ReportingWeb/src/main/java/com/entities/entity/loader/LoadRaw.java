package com.entities.entity.loader;

// Generated 11-feb-2015 16:49:54 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.utilities.hibernate.VersionAuditor;
import com.entities.utilities.hibernate.VersionableAdapter;

/**
 * LoadRaw generated by hbm2java
 */
@Entity
@Table(name = "T_LOAD_RAW")
public class LoadRaw implements VersionableAdapter {

	private long id;
	private LoadFile loadFile;
	private BigDecimal loadLineNumber;
	private String loadLineType;
	private byte[] loadRawBlob;
	private String loadError;
	private Set<LoadRawData> loadRawDatas = new HashSet(0);

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public LoadRaw() {
	}

	public LoadRaw(long loadRawId, LoadFile loadFile,
			VersionAuditor versionAuditor) {
		this.id = loadRawId;
		this.loadFile = loadFile;
		this.versionAuditor = versionAuditor;
	}

	public LoadRaw(LoadFile loadFile, BigDecimal loadLineNumber,
			String loadLineType, byte[] loadRawBlob, String loadError,
			Set<LoadRawData> loadRawDatas, VersionAuditor versionAuditor) {
		this.loadFile = loadFile;
		this.loadLineNumber = loadLineNumber;
		this.loadLineType = loadLineType;
		this.loadRawBlob = loadRawBlob;
		this.loadError = loadError;
		this.loadRawDatas = loadRawDatas;
		this.versionAuditor = versionAuditor;
	}

	public LoadRaw(LoadFile loadFile, BigDecimal loadLineNumber,
			String loadLineType, byte[] loadRawBlob, String loadError,
			Set<LoadRawData> loadRawDatas) {
		this.loadFile = loadFile;
		this.loadLineNumber = loadLineNumber;
		this.loadLineType = loadLineType;
		this.loadRawBlob = loadRawBlob;
		this.loadError = loadError;
		this.loadRawDatas = loadRawDatas;
	}

	@Id
	@SequenceGenerator(name = "GEN_LOAD_RAW", sequenceName = "SEQ_LOAD_RAW", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_LOAD_RAW")
	@Column(name = "LOAD_RAW_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long loadRawId) {
		this.id = loadRawId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAD_FILE_ID", nullable = false, foreignKey = @ForeignKey(name = "T_LOAD_RAW_FK_LOAD_FILE"))
	public LoadFile getLoadFile() {
		return this.loadFile;
	}

	public void setLoadFile(LoadFile loadFile) {
		this.loadFile = loadFile;
	}

	@Column(name = "LOAD_LINE_NUMBER", precision = 22, scale = 0)
	public BigDecimal getLoadLineNumber() {
		return this.loadLineNumber;
	}

	public void setLoadLineNumber(BigDecimal loadLineNumber) {
		this.loadLineNumber = loadLineNumber;
	}

	@Column(name = "LOAD_LINE_TYPE", length = 40)
	public String getLoadLineType() {
		return this.loadLineType;
	}

	public void setLoadLineType(String loadLineType) {
		this.loadLineType = loadLineType;
	}

	@Lob
	@Column(name = "LOAD_RAW_BLOB", length = 100000)
	public byte[] getLoadRawBlob() {
		return this.loadRawBlob;
	}

	public void setLoadRawBlob(byte[] loadRawBlob) {
		this.loadRawBlob = loadRawBlob;
	}

	@Column(name = "LOAD_ERROR", length = 40)
	public String getLoadError() {
		return this.loadError;
	}

	public void setLoadError(String loadError) {
		this.loadError = loadError;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loadRaw")
	@Cascade({ CascadeType.SAVE_UPDATE })
	@OrderBy("fileColum ASC")
	public Set<LoadRawData> getLoadRawDatas() {
		return this.loadRawDatas;
	}

	public void setLoadRawDatas(Set<LoadRawData> loadRawDatas) {
		this.loadRawDatas = loadRawDatas;
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
		if (object instanceof LoadRaw) {
			boolean ret = ((LoadRaw) object).getLoadFile()
					.equals(this.loadFile)
					&& ((LoadRaw) object).getLoadLineNumber().equals(
							this.loadLineNumber)
					&& Arrays.equals(((LoadRaw) object).getLoadRawBlob(),
							this.loadRawBlob);

			if (((LoadRaw) object).getLoadLineType() == null
					&& this.loadLineType == null) {
				// return ret;
			} else {
				// one or two reportExecution can be null
				if (this.loadLineType == null
						&& ((LoadRaw) object).getLoadLineType() != null) {
					ret = false;
				}
			}
			return ret;
		}
		return false;
	}
}
