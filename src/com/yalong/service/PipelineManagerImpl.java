package com.yalong.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.yalong.dao.PipelineDao;
import net.sf.json.JSONObject;



@Component("pipelManager")
public class PipelineManagerImpl implements PipelineManager {

	private PipelineDao pipel;



	// ��������

	public PipelineDao getPipel() {
		return pipel;
	}

	public void setPipel(PipelineDao pipel) {
		this.pipel = pipel;
	}

	@Transactional(readOnly = true)
	public JSONObject loadpipel(JSONObject key, int index, int pageSize, String sortField, String sortOrder)
			throws Exception {
		/* System.out.println(" ������service�е�load�Ŀ�ʼ"); */
		return pipel.searchpipel(key, index, pageSize, sortField, sortOrder);
	}

	// �Զ����ӹ���������߼�����ʼʱbegintransaction��
	// ��βTransaction().commit(),����׳�runtime�쳣�����Զ��ع�
	@Transactional
	// Ĭ�� Propagation.REQUIRED ��ǰ����������Ѵ���Transactional��ʹ��ԭ�������򴴽�һ���µ�
	public void addpipel(JSONObject jObject) {
		pipel.Insertpipel(jObject);
	}

	// �������ݸ���
	public void updatepipelInfo(JSONObject jObject) {
		pipel.Updatepipel(jObject);
	}

	// ͨ��id��ȡѧ����Ϣ
	@Transactional(readOnly = true)
	public JSONObject getpipelById(String id) {
		return pipel.getpipelById(id);
	}

	// ͨ��idɾ��ѧ����Ϣ
	public void deletepipelById(String id) {
		System.out.println("servicebegin");
		pipel.deletepipelById(id);
		System.out.println("serviceend");
	}

	@Override
	public JSONObject evalutionElbow(JSONObject jrows) {
		System.out.println("������service");
		JSONObject data=pipel.addEvaluationElbow(jrows);
		return data;
	}

	@Override
	public JSONObject evaluationsearch(JSONObject data, int pageIndex1, int pageSize1, String sortField,
			String sortOrder) throws Exception {
		return pipel.getevaluationsearch(data, pageIndex1, pageSize1, sortField, sortOrder);
	}

	@Override
	public void deleteEvalById(String id) {
		// TODO Auto-generated method stub
		pipel.deleteEvalById(id);
	}

}