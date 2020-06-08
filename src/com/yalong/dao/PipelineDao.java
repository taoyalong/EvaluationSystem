package com.yalong.dao;

import net.sf.json.JSONObject;

public interface PipelineDao {

	//��������
	public abstract JSONObject searchpipel(JSONObject key, int index,
			int pageSize, String sortField, String sortOrder) throws Exception;

	
	public abstract void Insertpipel(JSONObject jObject);

	//�������ݸ���
	public abstract void Updatepipel(JSONObject jObject);

	//ͨ��id��ȡѧ����Ϣ
	public abstract JSONObject getpipelById(String id);

	//ͨ��idɾ��ѧ����Ϣ
	public abstract void deletepipelById(String id);

	public abstract JSONObject addEvaluationElbow(JSONObject jrows);

	public abstract JSONObject getevaluationsearch(JSONObject data, int pageIndex1, int pageSize1, String sortField,
			String sortOrder) throws Exception;

	public abstract void deleteEvalById(String id);
}