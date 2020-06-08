package com.yalong.dao;

import net.sf.json.JSONObject;

public interface ValveDao {

	//查找数据
	/*
	 * public abstract JSONObject searchValve(String key, int index, int pageSize,
	 * String sortField, String sortOrder) throws Exception;
	 */
	
	public abstract void InsertValve(JSONObject jObject);

	//进行数据更新
	public abstract void UpdateValve(JSONObject jObject);

	//通过id获取学生信息
	public abstract JSONObject getValveById(String id);

	//通过id删除学生信息
	public abstract void deleteValveById(String id);

	//查找数据
	public abstract JSONObject getValvemultisearch(JSONObject key, 
			int pageIndex1, int pageSize1, String sortField,String sortOrder)throws Exception;

	public abstract JSONObject addEvaluationElbow(JSONObject jrows);

	public abstract JSONObject getevaluationsearch(JSONObject data, int pageIndex1, int pageSize1, String sortField,
			String sortOrder) throws Exception;

	public abstract void deleteEvalById(String id);
}