package com.yalong.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONObject;

import com.yalong.dao.ElbowDao;
import com.yalong.service.ElbowManager;

@Component("ElbowManager")
public class ElbowManagerImpl implements ElbowManager {

	private ElbowDao elbow;

	// ��������

	public ElbowDao getElbow() {
		return elbow;
	}

	public void setElbow(ElbowDao elbow) {
		this.elbow = elbow;
	}

	/*
	 * @Transactional(readOnly = true) public JSONObject loadElbow(String key, int
	 * index, int pageSize, String sortField, String sortOrder) throws Exception {
	 * System.out.println(" ������service�е�load�Ŀ�ʼ");
	 * 
	 * System.out.println(index); System.out.println(pageSize);
	 * 
	 * return elbow.searchElbow(key, index, pageSize, sortField, sortOrder); }
	 */

	// �Զ����ӹ���������߼�����ʼʱbegintransaction��
	// ��βTransaction().commit(),����׳�runtime�쳣�����Զ��ع�
	@Transactional
	// Ĭ�� Propagation.REQUIRED ��ǰ����������Ѵ���Transactional��ʹ��ԭ�������򴴽�һ���µ�
	public void addElbow(JSONObject jObject) {
		elbow.InsertElbow(jObject);
	}

	// �������ݸ���
	public void updateElbowInfo(JSONObject jObject) {
		elbow.UpdateElbow(jObject);
	}

	// ͨ��id��ȡѧ����Ϣ
	@Transactional(readOnly = true)
	public JSONObject getElbowById(String id) {
		return elbow.getElbowById(id);
	}

	// ͨ��idɾ��ѧ����Ϣ
	public void deleteElbowById(String id) {
		elbow.deleteElbowById(id);
	}

	@Override
	public JSONObject mulisearchElbow(JSONObject data, int pageIndex1, int pageSize1, String sortField,
			String sortOrder) throws Exception {
		return elbow.getElbowmultisearch(data, pageIndex1, pageSize1, sortField, sortOrder);
	}

	@Override
	public JSONObject evalutionElbow(JSONObject jrows) {
		System.out.println("������service");
		JSONObject data=elbow.addEvaluationElbow(jrows);
		return data;
	}

	@Override
	public JSONObject evaluationsearch(JSONObject data, int pageIndex1, int pageSize1, String sortField,
			String sortOrder) throws Exception {
		return elbow.getevaluationsearch(data, pageIndex1, pageSize1, sortField, sortOrder);
	}

	@Override
	public void deleteEvalById(String id) {
		// TODO Auto-generated method stub
		elbow.deleteEvalById(id);
	}

}