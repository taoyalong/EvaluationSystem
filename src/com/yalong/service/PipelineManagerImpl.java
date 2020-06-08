package com.yalong.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.yalong.dao.PipelineDao;
import net.sf.json.JSONObject;



@Component("pipelManager")
public class PipelineManagerImpl implements PipelineManager {

	private PipelineDao pipel;



	// 查找数据

	public PipelineDao getPipel() {
		return pipel;
	}

	public void setPipel(PipelineDao pipel) {
		this.pipel = pipel;
	}

	@Transactional(readOnly = true)
	public JSONObject loadpipel(JSONObject key, int index, int pageSize, String sortField, String sortOrder)
			throws Exception {
		/* System.out.println(" 这里是service中的load的开始"); */
		return pipel.searchpipel(key, index, pageSize, sortField, sortOrder);
	}

	// 自动添加关于事务的逻辑，开始时begintransaction，
	// 结尾Transaction().commit(),如果抛出runtime异常将会自动回滚
	@Transactional
	// 默认 Propagation.REQUIRED 当前环境中如果已存在Transactional，使用原来，否则创建一个新的
	public void addpipel(JSONObject jObject) {
		pipel.Insertpipel(jObject);
	}

	// 进行数据更新
	public void updatepipelInfo(JSONObject jObject) {
		pipel.Updatepipel(jObject);
	}

	// 通过id获取学生信息
	@Transactional(readOnly = true)
	public JSONObject getpipelById(String id) {
		return pipel.getpipelById(id);
	}

	// 通过id删除学生信息
	public void deletepipelById(String id) {
		System.out.println("servicebegin");
		pipel.deletepipelById(id);
		System.out.println("serviceend");
	}

	@Override
	public JSONObject evalutionElbow(JSONObject jrows) {
		System.out.println("进入了service");
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
