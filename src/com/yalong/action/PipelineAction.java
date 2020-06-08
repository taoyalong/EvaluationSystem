package com.yalong.action;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yalong.service.ElbowManager;
import com.yalong.service.PipelineManager;


@Component("ElbowManager")
@Scope("prototype")
public class PipelineAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// �����ύ������
	private String key;
	// ��ҳ
	private String pageIndex;
	private String pageSize;
	// �ֶ�����
	private String sortField;
	private String sortOrder;
	private PipelineManager pipelineManager;
	private Map request = null;
	private Object jsonModel;
	private String data; // �洢json����
	private int id; // �洢idֵ

	public PipelineManager getPipelineManager() {
		return pipelineManager;
	}

	public void setPipelineManager(PipelineManager pipelineManager) {
		this.pipelineManager = pipelineManager;
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

	public PipelineAction() {
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
	
	/* ���ۼ�¼��ѯaction */
	public String evaluationSearch() throws Exception {
		int pageIndex1 = Integer.parseInt(pageIndex);
		int pageSize1 = Integer.parseInt(pageSize);
		JSONObject jrows = JSONObject.fromObject(data);
		JSONObject rInfo1 = pipelineManager.evaluationsearch(jrows, pageIndex1, pageSize1, sortField, sortOrder);
		this.setJsonModel(rInfo1);
		return SUCCESS;
	}

	public String evalution() throws Exception {
		System.out.println("action");
		JSONArray jrows = JSONArray.fromObject(data);
		JSONObject jrow = jrows.getJSONObject(0);
		System.out.println("׼������pipleservice");
		JSONObject rInfol = pipelineManager.evalutionElbow(jrow);
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
			pipelineManager.deleteEvalById(id);
		}
		this.setJsonModel(null);
		return null;
	}
	public String search() throws Exception {
		JSONObject search=JSONObject.fromObject(data);
		int pageIndex1 = Integer.parseInt(pageIndex);
		int pageSize1 = Integer.parseInt(pageSize);
		/* System.out.println(" ������action�е�search�Ŀ�ʼ"); */
		JSONObject rInfo1 = pipelineManager.loadpipel(search, pageIndex1, pageSize1, sortField, sortOrder);
		/* System.out.println(" ������action�е�search�Ľ���"); */
		/* System.out.println(rInfo1); */
		this.setJsonModel(rInfo1);
		return SUCCESS;
	}

	public String saveInfo() throws Exception {
		JSONArray jrows = JSONArray.fromObject(data);

		for (int i = 0, l = jrows.size(); i < l; i++) {
			JSONObject row = (JSONObject) jrows.get(i);
			System.out.println(row);
			 String id = row.get("PId") != null ? row.get("PId").toString() : ""; 
			System.out.println(id);
			String state = row.get("_state") != null ? row.get("_state").toString() : "";
			System.out.println(state);
			if (state.equals("added") || id.equals("")) // ������idΪ�գ���_stateΪadded
			{
				pipelineManager.addpipel(row);
			} else if (state.equals("removed") || state.equals("deleted")) {
				System.out.println("ɾ��");
				pipelineManager.deletepipelById(id);
			} else if (state.equals("modified") || state.equals("")) // ���£�_stateΪ�գ���modified
			{
				pipelineManager.updatepipelInfo(row);
			}

		}
		this.setJsonModel(null);
		return null;
	}
}