package com.yalong.action;

import java.text.DecimalFormat;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yalong.entity.User;
import com.yalong.service.ElbowManager;
import com.yalong.service.Userservice;

@Component("user")
@Scope("prototype")
public class UserAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;

	// ����service�������������������ʵ�����ʵ����
	private Userservice is = null;/* ����indexservice�Ķ���is */
	private String userData;
	private String info;
	private HttpServletRequest request1;
	private HttpServletResponse response;
	private HttpSession session;

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public Map getRequest() {
		return request;
	}

	public void setRequest(Map request) {
		this.request = request;
	}

	private String key;
	// ��ҳ
	private String pageIndex;
	private String pageSize;
	int pageIndex1;
	int pageSize1;
	// �ֶ�����
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

	private String data; // �洢json����
	private int id; // �洢idֵ

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

	public UserAction() {
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;

	}

	public Userservice getIs() {
		return is;
	}

	// ����set()����
	public void setIs(Userservice is) {/* Spring�����ļ���Ϊ�丳ֵ ��ֵΪʵ���� IndexServiceImpl */
		this.is = is;
	}

	// ��дexecute()����
	public String execute() {
		JSONObject json = JSONObject.fromObject(userData);
		request1 = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		session = request1.getSession();
		Cookie cookies[] = request1.getCookies();
		User u = new User();
		u.setName(json.getString("username"));
		u.setPassword(json.getString("pwd"));

		// ����cookie�Ƿ��е�¼��Ϣ
//		if (cookies != null) {
//			for (int i = 0; i < cookies.length; i++) {
//				Cookie cookie = cookies[i];
//				if (u.getName().equals(cookie.getValue())) {
//					System.out.println(cookie.getValue());
//					this.info = "admin";
//					return SUCCESS;
//
//				}
//			}
//		}

		if (session.getAttribute("username") != null)

		{
			System.out.println("username!=null");
			if (u.getName().equals(session.getAttribute("username"))) {
				System.out.println("username�ǵ�¼�û�");
				if (session.getAttribute("promise").equals("admin")) {
					System.out.println("promise��admin");
					this.info = "admin";
					return SUCCESS;
				} else if (session.getAttribute("promise").equals("common")) {
					this.info = "common";
					return SUCCESS;
				}
			}
		}
		System.out.println("������service login");
		int tag = is.login(u);
		System.out.println("����service login");
		System.out.println(tag);
		if (tag == 1) {
			this.info = "admin";
			session.setAttribute("username", u.getName());
			session.setAttribute("promise", "admin");
			setcookie(u);
		} else if (tag == 2) {
			session.setAttribute("username", u.getName());
			session.setAttribute("promise", "common");
			this.info = "common";
			setcookie(u);
		} else
			this.info = "error";
		return SUCCESS;
	}

	/*
	 * public HttpServletRequest getRequest1() { return request1; }
	 * 
	 * public void setRequest1(HttpServletRequest request1) { this.request1 =
	 * request1; }
	 */

	/* ����cookie */
	void setcookie(User u) {
		Cookie cookie1 = new Cookie("username", u.getName());
		// ����Cookie��������Ϊһ��
		cookie1.setMaxAge(60 * 60 * 24 * 1);
		// ����cookie
		this.getResponse().addCookie(cookie1);
	}

	/* �˳���¼ */
	public String exit() throws Exception {
		request1 = ServletActionContext.getRequest();
		request1.getSession().removeAttribute("username");
		return SUCCESS;
	}

	/* ��ѯ */
	public String search() throws Exception {
		pageIndex1 = Integer.parseInt(pageIndex);
		pageSize1 = Integer.parseInt(pageSize);
		JSONObject jrows = JSONObject.fromObject(data);
		JSONObject rInfo1 = is.mulisearchElbow(jrows, pageIndex1, pageSize1, sortField, sortOrder);
		this.setJsonModel(rInfo1);
		return SUCCESS;
	}

	public String getuser() throws Exception {
		request1 = ServletActionContext.getRequest();
		String username = request1.getSession().getAttribute("username").toString();
		JSONObject rInfo1 = is.getuser(username);
		this.setJsonModel(rInfo1);
		return SUCCESS;
	}

	public String editpass() throws Exception {
		JSONObject json = JSONObject.fromObject(data);
		System.out.println(json);
		request1 = ServletActionContext.getRequest();
		String username = request1.getSession().getAttribute("username").toString();
		User user = new User();
		user.setName(username);
		user.setPassword(json.getString("password1"));
		JSONObject rInfo1 = is.editpass(user);
		this.setJsonModel(rInfo1);
		return SUCCESS;
	}

	/* ������Ϣ���� */

	public String saveuser() {
		JSONArray jrows = JSONArray.fromObject(data);
		JSONObject row = (JSONObject) jrows.get(0);
		is.updateElbowInfo(row);
		this.setJsonModel(null);
		return null;
	}

	/* ���� */
	public String saveInfo() throws Exception {
		JSONArray jrows = JSONArray.fromObject(data);

		for (int i = 0, l = jrows.size(); i < l; i++) {
			JSONObject row = (JSONObject) jrows.get(i);
			System.out.println(row);
			String id = row.get("id") != null ? row.get("id").toString() : "";
			System.out.println(id);
			String state = row.get("_state") != null ? row.get("_state").toString() : "";
			if (state.equals("added") || id.equals("")) // ������idΪ�գ���_stateΪadded
			{
				is.addElbow(row);
			} else if (state.equals("removed") || state.equals("deleted")) {
				System.out.println(id);
				is.deleteElbowById(id);
			} else if (state.equals("modified") || state.equals("")) // ���£�_stateΪ�գ���modified
			{
				System.out.println("����serviceupdate");
				is.updateElbowInfo(row);
				System.out.println("����serviceupdate");
			}

		}
		this.setJsonModel(null);
		return null;
	}

	// ����ʽת��
	public String formatDouble(double s) {
		DecimalFormat fmat = new DecimalFormat("\u00A4##.0");
		return fmat.format(s);
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request1 = request;
	}

}