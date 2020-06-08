package com.yalong.entity;
// Generated 2020-3-27 16:01:25 by Hibernate Tools 5.2.12.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MetadataValve generated by hbm2java
 */
@Entity
@Table(name = "metadata_valve", schema = "dbo", catalog = "EvaluationSystem")
public class MetadataValve implements java.io.Serializable {

	private String VId;
	private String VType;
	private String VPressure;
	private String VFlow;
	private String VTemperature;
	private String VCaliber;
	private String VLength;
	private String VThickness;

	public MetadataValve() {
	}

	public MetadataValve(String VId) {
		this.VId = VId;
	}

	public MetadataValve(String VId, String VType, String VPressure, String VFlow, String VTemperature, String VCaliber,
			String VLength, String VThickness) {
		this.VId = VId;
		this.VType = VType;
		this.VPressure = VPressure;
		this.VFlow = VFlow;
		this.VTemperature = VTemperature;
		this.VCaliber = VCaliber;
		this.VLength = VLength;
		this.VThickness = VThickness;
	}

	@Id

	@Column(name = "v_id", unique = true, nullable = false, length = 30)
	public String getVId() {
		return this.VId;
	}

	public void setVId(String VId) {
		this.VId = VId;
	}

	@Column(name = "v_type", length = 30)
	public String getVType() {
		return this.VType;
	}

	public void setVType(String VType) {
		this.VType = VType;
	}

	@Column(name = "v_pressure", length = 30)
	public String getVPressure() {
		return this.VPressure;
	}

	public void setVPressure(String VPressure) {
		this.VPressure = VPressure;
	}

	@Column(name = "v_flow", length = 30)
	public String getVFlow() {
		return this.VFlow;
	}

	public void setVFlow(String VFlow) {
		this.VFlow = VFlow;
	}

	@Column(name = "v_temperature", length = 30)
	public String getVTemperature() {
		return this.VTemperature;
	}

	public void setVTemperature(String VTemperature) {
		this.VTemperature = VTemperature;
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

	@Column(name = "v_thickness", length = 30)
	public String getVThickness() {
		return this.VThickness;
	}

	public void setVThickness(String VThickness) {
		this.VThickness = VThickness;
	}

}