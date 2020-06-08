package com.yalong.service;

import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Transactional;

public interface PipelineManager {

	public abstract JSONObject loadpipel(JSONObject key, int index, int pageSize, String sortField, String sortOrder)
			throws Exception;

	public abstract void addpipel(JSONObject jObject);

	public abstract void updatepipelInfo(JSONObject jObject);

	public abstract JSONObject getpipelById(String id);

	public abstract void deletepipelById(String id);


	public abstract JSONObject evalutionElbow(JSONObject jrows);

	public abstract JSONObject evaluationsearch(JSONObject data, int pageIndex1, int pageSize1, String sortField,
			String sortOrder) throws Exception;

	public abstract void deleteEvalById(String id);

}