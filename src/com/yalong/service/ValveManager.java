package com.yalong.service;

import net.sf.json.JSONObject;

public interface ValveManager {

	/*
	 * public abstract JSONObject loadValve(String key, int index, int pageSize,
	 * String sortField, String sortOrder) throws Exception;
	 */

	public abstract void addValve(JSONObject jObject);

	public abstract void updateValveInfo(JSONObject jObject);

	public abstract JSONObject getValveById(String id);

	public abstract void deleteValveById(String id);

	public abstract JSONObject mulisearchValve(JSONObject jrows, int pageIndex1, int pageSize1, String sortField,String sortOrder) 
			throws Exception;

	public abstract JSONObject evalutionElbow(JSONObject jrows);

	public abstract JSONObject evaluationsearch(JSONObject data, int pageIndex1, int pageSize1, String sortField,
			String sortOrder) throws Exception;

	public abstract void deleteEvalById(String id);


}