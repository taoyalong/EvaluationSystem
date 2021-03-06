package com.yalong.entity;
// Generated 2020-3-27 16:01:25 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EvaluateValveId generated by hbm2java
 */
@Embeddable
public class EvaluateValveId implements java.io.Serializable {

	private String id;
	private String VId;
	private String VName;
	private Date VTime;
	private String VSpeed;
	private String VCaliber;
	private String VLength;
	private String VExternalTemperature;
	private String VAmbientTemperature;
	private String VHeatLoss;
	private String VResult;

	public EvaluateValveId() {
	}

	public EvaluateValveId(String id) {
		this.id = id;
	}

	public EvaluateValveId(String id, String VId, String VName, Date VTime, String VSpeed, String VCaliber,
			String VLength, String VExternalTemperature, String VAmbientTemperature, String VHeatLoss, String VResult) {
		this.id = id;
		this.VId = VId;
		this.VName = VName;
		this.VTime = VTime;
		this.VSpeed = VSpeed;
		this.VCaliber = VCaliber;
		this.VLength = VLength;
		this.VExternalTemperature = VExternalTemperature;
		this.VAmbientTemperature = VAmbientTemperature;
		this.VHeatLoss = VHeatLoss;
		this.VResult = VResult;
	}

	@Column(name = "id", nullable = false, length = 30)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "v_id", length = 30)
	public String getVId() {
		return this.VId;
	}

	public void setVId(String VId) {
		this.VId = VId;
	}

	@Column(name = "v_name", length = 30)
	public String getVName() {
		return this.VName;
	}

	public void setVName(String VName) {
		this.VName = VName;
	}

	@Column(name = "v_time", length = 23)
	public Date getVTime() {
		return this.VTime;
	}

	public void setVTime(Date VTime) {
		this.VTime = VTime;
	}

	@Column(name = "v_speed", length = 30)
	public String getVSpeed() {
		return this.VSpeed;
	}

	public void setVSpeed(String VSpeed) {
		this.VSpeed = VSpeed;
	}

	@Column(name = "v_caliber", length = 30)
	public String getVCaliber() {
		return this.VCaliber;
	}

	public void setVCaliber(String VCaliber) {
		this.VCaliber = VCaliber;
	}

	@Column(name = "v_length", length = 30)
	public String getVLength() {
		return this.VLength;
	}

	public void setVLength(String VLength) {
		this.VLength = VLength;
	}

	@Column(name = "v_external_temperature", length = 30)
	public String getVExternalTemperature() {
		return this.VExternalTemperature;
	}

	public void setVExternalTemperature(String VExternalTemperature) {
		this.VExternalTemperature = VExternalTemperature;
	}

	@Column(name = "v_ambient_temperature", length = 30)
	public String getVAmbientTemperature() {
		return this.VAmbientTemperature;
	}

	public void setVAmbientTemperature(String VAmbientTemperature) {
		this.VAmbientTemperature = VAmbientTemperature;
	}

	@Column(name = "v_heat_loss", length = 30)
	public String getVHeatLoss() {
		return this.VHeatLoss;
	}

	public void setVHeatLoss(String VHeatLoss) {
		this.VHeatLoss = VHeatLoss;
	}

	@Column(name = "v_result", length = 30)
	public String getVResult() {
		return this.VResult;
	}

	public void setVResult(String VResult) {
		this.VResult = VResult;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EvaluateValveId))
			return false;
		EvaluateValveId castOther = (EvaluateValveId) other;

		return ((this.getId() == castOther.getId())
				|| (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())))
				&& ((this.getVId() == castOther.getVId()) || (this.getVId() != null && castOther.getVId() != null
						&& this.getVId().equals(castOther.getVId())))
				&& ((this.getVName() == castOther.getVName()) || (this.getVName() != null
						&& castOther.getVName() != null && this.getVName().equals(castOther.getVName())))
				&& ((this.getVTime() == castOther.getVTime()) || (this.getVTime() != null
						&& castOther.getVTime() != null && this.getVTime().equals(castOther.getVTime())))
				&& ((this.getVSpeed() == castOther.getVSpeed()) || (this.getVSpeed() != null
						&& castOther.getVSpeed() != null && this.getVSpeed().equals(castOther.getVSpeed())))
				&& ((this.getVCaliber() == castOther.getVCaliber()) || (this.getVCaliber() != null
						&& castOther.getVCaliber() != null && this.getVCaliber().equals(castOther.getVCaliber())))
				&& ((this.getVLength() == castOther.getVLength()) || (this.getVLength() != null
						&& castOther.getVLength() != null && this.getVLength().equals(castOther.getVLength())))
				&& ((this.getVExternalTemperature() == castOther.getVExternalTemperature())
						|| (this.getVExternalTemperature() != null && castOther.getVExternalTemperature() != null
								&& this.getVExternalTemperature().equals(castOther.getVExternalTemperature())))
				&& ((this.getVAmbientTemperature() == castOther.getVAmbientTemperature())
						|| (this.getVAmbientTemperature() != null && castOther.getVAmbientTemperature() != null
								&& this.getVAmbientTemperature().equals(castOther.getVAmbientTemperature())))
				&& ((this.getVHeatLoss() == castOther.getVHeatLoss()) || (this.getVHeatLoss() != null
						&& castOther.getVHeatLoss() != null && this.getVHeatLoss().equals(castOther.getVHeatLoss())))
				&& ((this.getVResult() == castOther.getVResult()) || (this.getVResult() != null
						&& castOther.getVResult() != null && this.getVResult().equals(castOther.getVResult())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result + (getVId() == null ? 0 : this.getVId().hashCode());
		result = 37 * result + (getVName() == null ? 0 : this.getVName().hashCode());
		result = 37 * result + (getVTime() == null ? 0 : this.getVTime().hashCode());
		result = 37 * result + (getVSpeed() == null ? 0 : this.getVSpeed().hashCode());
		result = 37 * result + (getVCaliber() == null ? 0 : this.getVCaliber().hashCode());
		result = 37 * result + (getVLength() == null ? 0 : this.getVLength().hashCode());
		result = 37 * result + (getVExternalTemperature() == null ? 0 : this.getVExternalTemperature().hashCode());
		result = 37 * result + (getVAmbientTemperature() == null ? 0 : this.getVAmbientTemperature().hashCode());
		result = 37 * result + (getVHeatLoss() == null ? 0 : this.getVHeatLoss().hashCode());
		result = 37 * result + (getVResult() == null ? 0 : this.getVResult().hashCode());
		return result;
	}

}
