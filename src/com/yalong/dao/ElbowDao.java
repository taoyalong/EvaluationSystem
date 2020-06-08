package com.yalong.dao;

import net.sf.json.JSONObject;

public interface ElbowDao {

	//查找数据
	/*
	 * public abstract JSONObject searchElbow(String key, int index, int pageSize,
	 * String sortField, String sortOrder) throws Exception;
	 */
	
	public abstract void InsertElbow(JSONObject jObject);

	//进行数据更新
	public abstract void UpdateElbow(JSONObject jObject);

	//通过id获取学生信息
	public abstract JSONObject getElbowById(String id);

	//通过id删除学生信息
	public abstract void deleteElbowById(String id);

	//查找数据
	public abstract JSONObject getElbowmultisearch(JSONObject key, 
			int pageIndex1, int pageSize1, String sortField,String sortOrder)throws Exception;

	public abstract JSONObject addEvaluationElbow(JSONObject jrows);

	public abstract JSONObject getevaluationsearch(JSONObject data, int pageIndex1, int pageSize1, String sortField,
			String sortOrder) throws Exception;

	public abstract void deleteEvalById(String id);

}