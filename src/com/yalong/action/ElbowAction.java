package com.yalong.action;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yalong.service.ElbowManager;

@Component("ElbowManager")
@Scope("prototype")
public class ElbowAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 接收提交的数据
	private String key;
	// 分页
	private String pageIndex;
	private String pageSize;
	int pageIndex1;
	int pageSize1;
	// 字段排序
	private String sortField;
	private String sortOrder;
	private ElbowManager elbowManager;

	private Map request = null;
	private Object jsonModel;

	public ElbowManager getElbowManager() {
		return elbowManager;
	}

	public void setElbowManager(ElbowManager elbowManager) {
		this.elbowManager = elbowManager;
	}

	private String data; // 存储json数据
	private int id; // 存储id值

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ElbowAction() {
		request = (Map) ActionContext.getContext().get("request");
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Object getJsonModel() {
		return jsonModel;
	}

	public void setJsonModel(Object jsonModel) {
		this.jsonModel = jsonModel;
	}

	/*
	 * public String search() throws Exception {
	 * 
	 * 
	 * System.out.println(pageIndex); System.out.println(pageIndex1);
	 * System.out.println(" 这里是action中的search的开始"); JSONObject rInfo1 =
	 * elbowManager.loadElbow(key, pageIndex1, pageSize1, sortField, sortOrder);
	 * System.out.println(" 这里是action中的search的结束"); System.out.println(rInfo1);
	 * this.setJsonModel(rInfo1); return SUCCESS; }
	 */

	public String mulisearch() throws Exception {
		pageIndex1 = Integer.parseInt(pageIndex);
		pageSize1 = Integer.parseInt(pageSize);
		JSONObject jrows = JSONObject.fromObject(data);
		JSONObject rInfo1 = elbowManager.mulisearchElbow(jrows, pageIndex1, pageSize1, sortField, sortOrder);
		this.setJsonModel(rInfo1);
		return SUCCESS;
	}

	/* 评价记录查询action */
	public String evaluationSearch() throws Exception {
		pageIndex1 = Integer.parseInt(pageIndex);
		pageSize1 = Integer.parseInt(pageSize);
		JSONObject jrows = JSONObject.fromObject(data);
		JSONObject rInfo1 = elbowManager.evaluationsearch(jrows, pageIndex1, pageSize1, sortField, sortOrder);
		this.setJsonModel(rInfo1);
		return SUCCESS;
	}

	public String evalution() throws Exception {
		System.out.println("action");
		JSONArray jrows = JSONArray.fromObject(data);
		JSONObject jrow = jrows.getJSONObject(0);
		System.out.println("准备进入service");
		JSONObject rInfol = elbowManager.evalutionElbow(jrow);
		this.setJsonModel(rInfol);
		return SUCCESS;
	}

	public String deleteEvaluation() throws Exception {
		JSONArray jrows = JSONArray.fromObject(data);

		for (int i = 0, l = jrows.size(); i < l; i++) {
			JSONObject row = (JSONObject) jrows.get(i);
			System.out.println(row);
			String id = row.get("id") != null ? row.get("id").toString() : "";
			System.out.println(id);
			elbowManager.deleteEvalById(id);
		}
		this.setJsonModel(null);
		return null;
	}

	public String saveInfo() throws Exception {
		JSONArray jrows = JSONArray.fromObject(data);

		for (int i = 0, l = jrows.size(); i < l; i++) {
			JSONObject row = (JSONObject) jrows.get(i);
			System.out.println(row);
			String id = row.get("EId") != null ? row.get("EId").toString() : "";
			System.out.println(id);
			String state = row.get("_state") != null ? row.get("_state").toString() : "";
			if (state.equals("added") || id.equals("")) // 新增：id为空，或_state为added
			{	
				elbowManager.addElbow(row);
			} else if (state.equals("removed") || state.equals("deleted")) {
				System.out.println(id);
				elbowManager.deleteElbowById(id);
			} else if (state.equals("modified") || state.equals("")) // 更新：_state为空，或modified
			{
				elbowManager.updateElbowInfo(row);
			}

		}
		this.setJsonModel(null);
		return null;
	}
}
