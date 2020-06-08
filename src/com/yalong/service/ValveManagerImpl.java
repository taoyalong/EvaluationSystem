package com.yalong.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONObject;

import com.yalong.dao.ValveDao;
import com.yalong.service.ValveManager;

@Component("ValveManager")
public class ValveManagerImpl implements ValveManager {

	private ValveDao valve;

	// 查找数据

	

	/*
	 * @Transactional(readOnly = true) public JSONObject loadValve(String key, int
	 * index, int pageSize, String sortField, String sortOrder) throws Exception {
	 * System.out.println(" 这里是service中的load的开始");
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

	// 自动添加关于事务的逻辑，开始时begintransaction，
	// 结尾Transaction().commit(),如果抛出runtime异常将会自动回滚
	@Transactional
	// 默认 Propagation.REQUIRED 当前环境中如果已存在Transactional，使用原来，否则创建一个新的
	public void addValve(JSONObject jObject) {
		valve.InsertValve(jObject);
	}

	// 进行数据更新
	public void updateValveInfo(JSONObject jObject) {
		valve.UpdateValve(jObject);
	}

	// 通过id获取学生信息
	@Transactional(readOnly = true)
	public JSONObject getValveById(String id) {
		return valve.getValveById(id);
	}

	// 通过id删除学生信息
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
		System.out.println("进入了service");
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
