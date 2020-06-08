package com.yalong.dao;

import net.sf.json.JSONObject;

public interface PipelineDao {

	//查找数据
	public abstract JSONObject searchpipel(JSONObject key, int index,
			int pageSize, String sortField, String sortOrder) throws Exception;

	
	public abstract void Insertpipel(JSONObject jObject);

	//进行数据更新
	public abstract void Updatepipel(JSONObject jObject);

	//通过id获取学生信息
	public abstract JSONObject getpipelById(String id);

	//通过id删除学生信息
	public abstract void deletepipelById(String id);

	public abstract JSONObject addEvaluationElbow(JSONObject jrows);

	public abstract JSONObject getevaluationsearch(JSONObject data, int pageIndex1, int pageSize1, String sortField,
			String sortOrder) throws Exception;

	public abstract void deleteEvalById(String id);
}