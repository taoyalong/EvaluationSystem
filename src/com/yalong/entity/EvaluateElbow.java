package com.yalong.entity;
// Generated 2020-3-27 16:01:25 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * EvaluateElbow generated by hbm2java
 */
@Entity
@Table(name = "evaluate_elbow", schema = "dbo", catalog = "EvaluationSystem")
public class EvaluateElbow implements java.io.Serializable {

	private String id;
	private String EId;
	private String EName;
	private Date   ETime;
	private String ESpeed;
	private String ECaliber;
	private String ELength;
	private String EExternalTemperature;
	private String EAmbientTemperature;
	private String EHeatLoss;
	private String EResult;

	public EvaluateElbow() {
	}

	public EvaluateElbow(String id) {
		this.id = id;
	}

	public EvaluateElbow(String id, String EId, String EName, Date ETime, String ESpeed, String ECaliber,
			String ELength, String EExternalTemperature, String EAmbientTemperature, String EHeatLoss, String EResult) {
		this.id = id;
		this.EId = EId;
		this.EName = EName;
		this.ETime = ETime;
		this.ESpeed = ESpeed;
		this.ECaliber = ECaliber;
		this.ELength = ELength;
		this.EExternalTemperature = EExternalTemperature;
		this.EAmbientTemperature = EAmbientTemperature;
		this.EHeatLoss = EHeatLoss;
		this.EResult = EResult;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false, length = 30)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "e_id", length = 30)
	public String getEId() {
		return this.EId;
	}

	public void setEId(String EId) {
		this.EId = EId;
	}

	@Column(name = "e_name", length = 30)
	public String getEName() {
		return this.EName;
	}

	public void setEName(String EName) {
		this.EName = EName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "e_time", length = 23)
	public Date getETime() {
		return this.ETime;
	}

	public void setETime(Date ETime) {
		this.ETime = ETime;
	}

	@Column(name = "e_speed", length = 30)
	public String getESpeed() {
		return this.ESpeed;
	}

	public void setESpeed(String ESpeed) {
		this.ESpeed = ESpeed;
	}

	@Column(name = "e_caliber", length = 30)
	public String getECaliber() {
		return this.ECaliber;
	}

	public void setECaliber(String ECaliber) {
		this.ECaliber = ECaliber;
	}

	@Column(name = "e_length", length = 30)
	public String getELength() {
		return this.ELength;
	}

	public void setELength(String ELength) {
		this.ELength = ELength;
	}

	@Column(name = "e_external_temperature", length = 30)
	public String getEExternalTemperature() {
		return this.EExternalTemperature;
	}

	public void setEExternalTemperature(String EExternalTemperature) {
		this.EExternalTemperature = EExternalTemperature;
	}

	@Column(name = "e_ambient_temperature", length = 30)
	public String getEAmbientTemperature() {
		return this.EAmbientTemperature;
	}

	public void setEAmbientTemperature(String EAmbientTemperature) {
		this.EAmbientTemperature = EAmbientTemperature;
	}

	@Column(name = "e_heat_loss", length = 30)
	public String getEHeatLoss() {
		return this.EHeatLoss;
	}

	public void setEHeatLoss(String EHeatLoss) {
		this.EHeatLoss = EHeatLoss;
	}

	@Column(name = "e_result", length = 30)
	public String getEResult() {
		return this.EResult;
	}

	public void setEResult(String EResult) {
		this.EResult = EResult;
	}

}
