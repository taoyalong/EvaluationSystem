package com.yalong.dao;

import net.sf.json.JSONObject;

public interface ValveDao {

	//��������
	/*
	 * public abstract JSONObject searchValve(String key, int index, int pageSize,
	 * String sortField, String sortOrder) throws Exception;
	 */
	
	public abstract void InsertValve(JSONObject jObject);

	//�������ݸ���
	public abstract void UpdateValve(JSONObject jObject);

	//ͨ��id��ȡѧ����Ϣ
	public abstract JSONObject getValveById(String id);

	//ͨ��idɾ��ѧ����Ϣ
	public abstract void deleteValveById(String id);

	//��������
	public abstract JSONObject getValvemultisearch(JSONObject key, 
			int pageIndex1, int pageSize1, String sortField,String sortOrder)throws Exception;

	public abstract JSONObject addEvaluationElbow(JSONObject jrows);

	public abstract JSONObject getevaluationsearch(JSONObject data, int pageIndex1, int pageSize1, String sortField,
			String sortOrder) throws Exception;

	public abstract void deleteEvalById(String id);
}