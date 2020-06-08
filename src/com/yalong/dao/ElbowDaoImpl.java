package com.yalong.dao;

import java.sql.Clob;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

import com.yalong.dao.ElbowDao;
import com.yalong.entity.EvaluateElbow;
import com.yalong.entity.MetadataElbow;
import com.yalong.utils.JBToHashMap;
import com.yalong.utils.JSON;

@Component("studentDao")
public class ElbowDaoImpl implements ElbowDao {
	private SessionFactory sessionFactory;
	Transaction tx;
	HttpServletRequest request;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	Session session;

	public List<MetadataElbow> DBSelect(String sql) {
		/* System.out.println(sql); */
		Query<MetadataElbow> query = session.createQuery(sql);
		// 将所有的数据查询出来并放到List集合里
		List<MetadataElbow> list = query.getResultList();
		System.out.println(list);
		session.close();
		return list;
	}

	private static String clob2String(Clob clob) throws Exception {
		return (clob != null ? clob.getSubString(1, (int) clob.length()) : null);
	}

	public void InsertElbow(JSONObject jObject) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		MetadataElbow s = new MetadataElbow();
		s.setEId(jObject.getString("EId"));
		s.setEType(jObject.getString("EType"));
		s.setEPressure(jObject.getString("EPressure"));
		s.setEFlow(jObject.getString("EFlow"));
		s.setETemperature(jObject.getString("ETemperature"));
		s.setECaliber(jObject.getString("ECaliber"));
		s.setEThickness(jObject.getString("EThickness"));
		session.save(s);
		tx.commit();
		session.close();

	}

	private Timestamp changeFrom(String s) {
		if (s != null) {
			String s2 = s.replace('T', ' ');
			return Timestamp.valueOf(s2);
		}
		return null;
	}

	// 进行数据更新
	public void UpdateElbow(JSONObject jObject) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		MetadataElbow s = new MetadataElbow();
		s.setEId(jObject.getString("EId"));
		s.setEType(jObject.getString("EType"));
		s.setEPressure(jObject.getString("EPressure"));
		s.setEFlow(jObject.getString("EFlow"));
		s.setETemperature(jObject.getString("ETemperature"));
		s.setECaliber(jObject.getString("ECaliber"));
		s.setEThickness(jObject.getString("EThickness"));
		session.update(s);
		tx.commit();
		session.close();
	}

	// 通过id获取学生信息
	public JSONObject getElbowById(String id) {
		JSONObject json = null;
		String sql = "select * from MetadataElbow where id = '" + id + "'";
		List<MetadataElbow> data = DBSelect(sql);
		if (data.size() > 0) {
			json = JSONObject.fromObject(data.get(0));
		}
		return json;
	}

	// 通过id删除信息
	public void deleteElbowById(String id) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		MetadataElbow s = new MetadataElbow();
		s.setEId(id);
		session.delete(s);
		tx.commit();
		session.close();
	}

	@Override
	public JSONObject getElbowmultisearch(JSONObject data, int index, int pageSize, String sortField, String sortOrder)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		HashMap result = new HashMap();
		String sql = "select s from MetadataElbow as s";
		String where = "";
		Boolean b = false;
		System.out.println(data.getString("EType"));
		if (data.getString("isturnEType").equals("true")) {
			where = where + " EType='" + data.getString("EType") + "'";
			System.out.println(where);
			b = true;
			}
		if (data.getString("isturnEPressure").equals("true")) {
			if (b)
				where = where + " and EPressure='" + data.getString("EPressure") + "'";
			else {
				b = true;
				where = where + " EPressure='" + data.getString("EPressure") + "'";
			}
			System.out.println(where);
			}
		if (data.getString("isturnEFlow").equals("true")) {
			if (b)
				where = where + "and EFlow='" + data.getString("EFlow") + "'";
			else {
				b = true;
				where = where + " EFlow='" + data.getString("EFlow") + "'";
			}
			System.out.println(where);
			
		}
		if (data.getString("isturnETemperature").equals("true")) {
			if (b)
				where = where + "and ETemperature='" + data.getString("ETemperature") + "'";
			else {
				where = where + " ETemperature='" + data.getString("ETemperature") + "'";
				b = true;
			}
			/* } */

		}
		if (data.getString("isturnECaliber").equals("true")) {
			if (b)
				where = where + "and ECaliber='" + data.getString("ECaliber") + "'";
			else {
				b = true;
				where = where + " ECaliber='" + data.getString("ECaliber") + "'";
			}
			/* } */
		}
		if (data.getString("isturnEEThickness").equals("true")) {
			/* if (data.getString("EThickness") != null) { */
			if (b)
				where = where + "and EThickness='" + data.getString("EThickness") + "'";
			else {
				b = true;
				where = where + " EThickness='" + data.getString("EThickness") + "'";
			}
			/* } */
		}
		if (b) {
			sql = sql + " where" + where;
		}

		List<MetadataElbow> dataAll = DBSelect(sql);
		ArrayList list = new ArrayList();
		int start = index * pageSize, end = start + pageSize;

		for (int i = 0, l = dataAll.size(); i < l; i++) {
			HashMap record = JBToHashMap.objToHash(dataAll.get(i));
			if (record == null)
				continue;
			if (start <= i && i < end) {
				list.add(record);
			}

			record.put("createtime", new Timestamp(100, 10, 10, 1, 1, 1, 1));
		}
		result.put("data", list);
		result.put("total", dataAll.size());
		String result2 = JSON.Encode(result);
		JSONObject json = JSONObject.fromObject(result2);
		return json;
	}

	@Override
	public JSONObject addEvaluationElbow(JSONObject jrows) {
		float speed, caliber, length, AmbientTemperature, Temperature;
		session = sessionFactory.openSession();
		request=ServletActionContext.getRequest();
		tx = session.beginTransaction();
		speed = Float.parseFloat(jrows.getString("ESpeed"));
		caliber = Float.parseFloat(jrows.getString("ECaliber"));
		length = Float.parseFloat(jrows.getString("ELength"));
		Temperature = Float.parseFloat(jrows.getString("EAmbientTemperature"));
		AmbientTemperature = Float.parseFloat(jrows.getString("EExternalTemperature"));
		EvaluateElbow s = new EvaluateElbow();
		if(jrows.getString("id").length()!=0)
			s.setId(jrows.getString("id"));
		s.setESpeed(jrows.getString("ESpeed"));
		s.setEId(jrows.getString("EId"));
		s.setECaliber(jrows.getString("ECaliber"));
		s.setELength(jrows.getString("ELength"));
		s.setEName(request.getSession().getAttribute("username").toString());
		s.setEAmbientTemperature(jrows.getString("EAmbientTemperature"));
		s.setEExternalTemperature(jrows.getString("EExternalTemperature"));
		Date date = new Date();
		s.setETime(date);
		double result = evalution(speed, caliber, length, AmbientTemperature, Temperature);
		if (result < 107) {
			s.setEResult("合格");
		} else
			s.setEResult("不合格");
		s.setEHeatLoss(String.format("%.3f", result));
		JSONObject data =JSONObject.fromObject(s);
		session.saveOrUpdate(s);
		tx.commit();
		session.close();
		return data;
	}

	public double evalution(float u, float d, float l, float Tw, float T) {
		System.out.println("进入了评价方法");
		double Re = (u * d) / (0.00001506);
		double Nu = 0.0266 * (Math.pow(Re, 0.805)) * Math.pow(0.703, 1 / 3d);
		double Hc = Nu * 0.0267 / d;
		double Hr = 0.6 * (0.0000000567 ) * ((Tw+273) * (Tw+273) + (T+273) *( T+273)) * (Tw+273 + T+273);
		double h = Hc + Hr;
		double result = h * (Tw - T);
		return result;
	}

	@Override
	public JSONObject getevaluationsearch(JSONObject data, int index, int pageSize, String sortField, String sortOrder)
			throws Exception {
		System.out.println(data);
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		HashMap result = new HashMap();
		String sql = "select s from EvaluateElbow as s";
		String where = "";
		Boolean b = false;
		if(data.containsKey("isturnEId")){
		if (data.getString("isturnEId").equals("true")) {
			/* if (data.getString("EType").equals("")) { */
			where = where + " e_id='" + data.getString("EId") + "'";
			System.out.println(where);
			b = true;
			/* } */
		}
		if (data.getString("isturnSTime").equals("true")) {
			String time=data.getString("STime");
			String etime=data.getString("ETime");
			time=time.substring(0, 10);
			etime=etime.substring(0, 10);
			if (b)
				where = where + " and  e_time  between'" +time+ " 00:00:00:00' and '"+etime+" 23:59:59:59'";
			else {
				b = true;
				where = where + " e_time between  '" +time+ " 00:00:00:00' and '"+etime+" 23:59:59:59'";
			}
		}
			/*
			 * if (data.getString("isturnETime").equals("true")) { String
			 * etime=data.getString("ETime"); etime=etime.substring(0, 10); if (b) where =
			 * where + " and  e_time  <='" +etime+ " 23:59:59:59'"; else { b = true; where =
			 * where + " e_time <='" +etime+ " 23:59:59:59'"; } }
			 */
		if (data.getString("isturnEName").equals("true")) {
			/* if (data.getString("EFlow") != null) { */
			if (b)
				where = where + "and e_name='" + data.getString("EName") + "'";
			else {
				b = true;
				where = where + " e_name='" + data.getString("EName") + "'";
			}
			System.out.println(where);
			/* } */
		}
		if (data.getString("isturnEResult").equals("true")) {
			/* if (data.getString("ETemperature") != null) { */
			if (b)
				where = where + "and e_result='" + data.getString("EResult") + "'";
			else {
				where = where + " e_result='" + data.getString("EResult") + "'";
				b = true;
			}
			/* } */

		}
		if (b) {
			sql = sql + " where" + where;
		}
		}
		List<MetadataElbow> dataAll = DBSelect(sql);
 		ArrayList list = new ArrayList();
		int start = index * pageSize, end = start + pageSize;

		for (int i = 0, l = dataAll.size(); i < l; i++) {
			// HashMap record = (HashMap)dataAll.get(i);
			HashMap record = JBToHashMap.objToHash(dataAll.get(i));
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			record.put("Etime", format.format(record.get("ETime")));
			if (record == null)
				continue;
			if (start <= i && i < end) {
				list.add(record);
			}

		}
		result.put("data", list);
		result.put("total", dataAll.size());
		String result2 = JSON.Encode(result);
		JSONObject json = JSONObject.fromObject(result2);
		return json;
	}

	@Override
	public void deleteEvalById(String id) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		EvaluateElbow s = new EvaluateElbow();
		s.setId(id);
		session.delete(s);
		tx.commit();
		session.close();
	}

}
