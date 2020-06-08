package com.yalong.action;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yalong.service.ValveManager;

@Component("ValveManager")
@Scope("prototype")
public class ValveAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// �����ύ������
	private String key;
	// ��ҳ
	private String pageIndex;
	private String pageSize;
	int pageIndex1;
	int pageSize1;
	// �ֶ�����
	private String sortField;
	private String sortOrder;
	private ValveManager valveManager;

	private Map request = null;
	private Object jsonModel;
	private String data; // �洢json����
	private int id; // �洢idֵ
	
	
	public ValveManager getValveManager() {
		return valveManager;
	}

	public void setValveManager(ValveManager valveManager) {
		this.valveManager = valveManager;
	}

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

	public ValveAction() {
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
	 * System.out.println(" ������action�е�search�Ŀ�ʼ"); JSONObject rInfo1 =
	 * ValveManager.loadValve(key, pageIndex1, pageSize1, sortField, sortOrder);
	 * System.out.println(" ������action�е�search�Ľ���"); System.out.println(rInfo1);
	 * this.setJsonModel(rInfo1); return SUCCESS; }
	 */

	public String search() throws Exception {
		pageIndex1 = Integer.parseInt(pageIndex);
		pageSize1 = Integer.parseInt(pageSize);
		JSONObject jrows = JSONObject.fromObject(data);
		System.out.println(jrows);
		System.out.println(pageIndex1);
		System.out.println("guolepageindex");
		System.out.println(" ������action�е�mulisearch�Ŀ�ʼ");
		JSONObject rInfo1 = valveManager.mulisearchValve(jrows, pageIndex1, pageSize1, sortField, sortOrder);
		System.out.println(" ������action�е�mulisearch�Ľ���");
		/* System.out.println(rInfo1); */
		this.setJsonModel(rInfo1);
		return SUCCESS;
	}

	public String saveInfo() throws Exception {
		JSONArray jrows = JSONArray.fromObject(data);

		for (int i = 0, l = jrows.size(); i < l; i++) {
			JSONObject row = (JSONObject) jrows.get(i);
			System.out.println(row);
			String id = row.get("VId") != null ? row.get("VId").toString() : "";
			System.out.println(id);
			String state = row.get("_state") != null ? row.get("_state").toString() : "";
			if (state.equals("added") || id.equals("")) // ������idΪ�գ���_stateΪadded
			{
				valveManager.addValve(row);
			} else if (state.equals("removed") || state.equals("deleted")) {
				valveManager.deleteValveById(id);
			} else if (state.equals("modified") || state.equals("")) // ���£�_stateΪ�գ���modified
			{
				valveManager.updateValveInfo(row);
			}

		}
		this.setJsonModel(null);
		return null;
	}
	

	/* ���ۼ�¼��ѯaction */
	public String evaluationSearch() throws Exception {
		pageIndex1 = Integer.parseInt(pageIndex);
		pageSize1 = Integer.parseInt(pageSize);
		JSONObject jrows = JSONObject.fromObject(data);
		JSONObject rInfo1 = valveManager.evaluationsearch(jrows, pageIndex1, pageSize1, sortField, sortOrder);
		this.setJsonModel(rInfo1);
		return SUCCESS;
	}

	public String evalution() throws Exception {
		System.out.println("action");
		JSONArray jrows = JSONArray.fromObject(data);
		JSONObject jrow = jrows.getJSONObject(0);
		System.out.println("׼������service");
		JSONObject rInfol = valveManager.evalutionElbow(jrow);
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
			valveManager.deleteEvalById(id);
		}
		this.setJsonModel(null);
		return null;
	}

}