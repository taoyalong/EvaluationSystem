package com.yalong.service;

import net.sf.json.JSONObject;

public interface ElbowManager {

	/*
	 * public abstract JSONObject loadElbow(String key, int index, int pageSize,
	 * String sortField, String sortOrder) throws Exception;
	 */

	public abstract void addElbow(JSONObject jObject);

	public abstract void updateElbowInfo(JSONObject jObject);

	public abstract JSONObject getElbowById(String id);

	public abstract void deleteElbowById(String id);

	public abstract JSONObject mulisearchElbow(JSONObject jrows, int pageIndex1, int pageSize1, String sortField,String sortOrder) 
			throws Exception;

	public abstract JSONObject evalutionElbow(JSONObject jrows);

	public abstract JSONObject evaluationsearch(JSONObject data, int pageIndex1, int pageSize1, String sortField,
			String sortOrder) throws Exception;

	public abstract void deleteEvalById(String id);


}