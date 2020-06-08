package com.yalong.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONObject;

import com.yalong.dao.ElbowDao;
import com.yalong.service.ElbowManager;

@Component("ElbowManager")
public class ElbowManagerImpl implements ElbowManager {

	private ElbowDao elbow;

	// 查找数据

	public ElbowDao getElbow() {
		return elbow;
	}

	public void setElbow(ElbowDao elbow) {
		this.elbow = elbow;
	}

	/*
	 * @Transactional(readOnly = true) public JSONObject loadElbow(String key, int
	 * index, int pageSize, String sortField, String sortOrder) throws Exception {
	 * System.out.println(" 这里是service中的load的开始");
	 * 
	 * System.out.println(index); System.out.println(pageSize);
	 * 
	 * return elbow.searchElbow(key, index, pageSize, sortField, sortOrder); }
	 */

	// 自动添加关于事务的逻辑，开始时begintransaction，
	// 结尾Transaction().commit(),如果抛出runtime异常将会自动回滚
	@Transactional
	// 默认 Propagation.REQUIRED 当前环境中如果已存在Transactional，使用原来，否则创建一个新的
	public void addElbow(JSONObject jObject) {
		elbow.InsertElbow(jObject);
	}

	// 进行数据更新
	public void updateElbowInfo(JSONObject jObject) {
		elbow.UpdateElbow(jObject);
	}

	// 通过id获取学生信息
	@Transactional(readOnly = true)
	public JSONObject getElbowById(String id) {
		return elbow.getElbowById(id);
	}

	// 通过id删除学生信息
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
		System.out.println("进入了service");
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
