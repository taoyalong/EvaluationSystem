package com.yalong.dao;

import net.sf.json.JSONObject;

public interface ElbowDao {

	//��������
	/*
	 * public abstract JSONObject searchElbow(String key, int index, int pageSize,
	 * String sortField, String sortOrder) throws Exception;
	 */
	
	public abstract void InsertElbow(JSONObject jObject);

	//�������ݸ���
	public abstract void UpdateElbow(JSONObject jObject);

	//ͨ��id��ȡѧ����Ϣ
	public abstract JSONObject getElbowById(String id);

	//ͨ��idɾ��ѧ����Ϣ
	public abstract void deleteElbowById(String id);

	//��������
	public abstract JSONObject getElbowmultisearch(JSONObject key, 
			int pageIndex1, int pageSize1, String sortField,String sortOrder)throws Exception;

	public abstract JSONObject addEvaluationElbow(JSONObject jrows);

	public abstract JSONObject getevaluationsearch(JSONObject data, int pageIndex1, int pageSize1, String sortField,
			String sortOrder) throws Exception;

	public abstract void deleteEvalById(String id);

}