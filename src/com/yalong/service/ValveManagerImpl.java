package com.yalong.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONObject;

import com.yalong.dao.ValveDao;
import com.yalong.service.ValveManager;

@Component("ValveManager")
public class ValveManagerImpl implements ValveManager {

	private ValveDao valve;

	// ��������

	

	/*
	 * @Transactional(readOnly = true) public JSONObject loadValve(String key, int
	 * index, int pageSize, String sortField, String sortOrder) throws Exception {
	 * System.out.println(" ������service�е�load�Ŀ�ʼ");
	 * 
	 * System.out.println(index); System.out.println(pageSize);
	 * 
	 * return Valve.searchValve(key, index, pageSize, sortField, sortOrder); }
	 */

	public ValveDao getValve() {
		return valve;
	}

	public void setValve(ValveDao valve) {
		this.valve = valve;
	}

	// �Զ����ӹ���������߼�����ʼʱbegintransaction��
	// ��βTransaction().commit(),����׳�runtime�쳣�����Զ��ع�
	@Transactional
	// Ĭ�� Propagation.REQUIRED ��ǰ����������Ѵ���Transactional��ʹ��ԭ�������򴴽�һ���µ�
	public void addValve(JSONObject jObject) {
		valve.InsertValve(jObject);
	}

	// �������ݸ���
	public void updateValveInfo(JSONObject jObject) {
		valve.UpdateValve(jObject);
	}

	// ͨ��id��ȡѧ����Ϣ
	@Transactional(readOnly = true)
	public JSONObject getValveById(String id) {
		return valve.getValveById(id);
	}

	// ͨ��idɾ��ѧ����Ϣ
	public void deleteValveById(String id) {
		valve.deleteValveById(id);
	}

	@Override
	public JSONObject mulisearchValve(JSONObject data, int pageIndex1, int pageSize1, String sortField,
			String sortOrder) throws Exception {
		System.out.println("into service mulisearch");
		return valve.getValvemultisearch(data, pageIndex1, pageSize1, sortField, sortOrder);
	}

	@Override
	public JSONObject evalutionElbow(JSONObject jrows) {
		System.out.println("������service");
		JSONObject data=valve.addEvaluationElbow(jrows);
		return data;
	}

	@Override
	public JSONObject evaluationsearch(JSONObject data, int pageIndex1, int pageSize1, String sortField,
			String sortOrder) throws Exception {
		return valve.getevaluationsearch(data, pageIndex1, pageSize1, sortField, sortOrder);
	}

	@Override
	public void deleteEvalById(String id) {
		// TODO Auto-generated method stub
		valve.deleteEvalById(id);
	}
}