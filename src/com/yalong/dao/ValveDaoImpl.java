package com.yalong.dao;

import java.sql.Clob;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

import com.yalong.dao.ValveDao;
import com.yalong.entity.EvaluatePipeline;
import com.yalong.entity.EvaluateValve;
import com.yalong.entity.MetadataElbow;
import com.yalong.entity.MetadataValve;
import com.yalong.utils.JBToHashMap;
import com.yalong.utils.JSON;

@Component("studentDao")
public class ValveDaoImpl implements ValveDao {
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

	// 查找数据
	/*
	 * public JSONObject searchValve(String key, int index, int pageSize, String
	 * sortField, String sortOrder) throws Exception {
	 * session=sessionFactory.openSession();
	 * 
	 * HashMap result = new HashMap(); if (key == null) key = "";
	 * 
	 * String sql = "select s from MetadataValve as s";
	 * 
	 * if (StringUtil.isNullOrEmpty(sortField) == false) { if
	 * ("desc".equals(sortOrder) == false) sortOrder = "asc"; sql += " order by " +
	 * sortField + " " + sortOrder; } else { sql += " order by createtime desc"; }
	 * 
	 * System.out.println(" 这里是dao中的查询的开始"); List<MetadataValve> dataAll =
	 * DBSelect(sql); System.out.println(" 这里是dao中的查询的结束"); session.close();
	 * System.out.println(" dataall的数据值：" + dataAll.toString()); ArrayList data =
	 * new ArrayList(); int start = index * pageSize, end = start + pageSize; for
	 * (int i = 0, l = dataAll.size(); i < l; i++) { // HashMap record =
	 * (HashMap)dataAll.get(i); HashMap record =
	 * JBToHashMap.objToHash(dataAll.get(i)); if (record == null) continue; if
	 * (start <= i && i < end) { data.add(record); }
	 * 
	 * record.put("createtime", new Timestamp(100, 10, 10, 1, 1, 1, 1)); }
	 * 
	 * result.put("data", data); result.put("total", dataAll.size()); String result2
	 * = JSON.Encode(result); JSONObject json = JSONObject.fromObject(result2);
	 * return json; }
	 */

	public List<MetadataValve> DBSelect(String sql) {
		/* System.out.println(sql); */
		Query<MetadataValve> query = session.createQuery(sql);
		// 将所有的数据查询出来并放到List集合里
		List<MetadataValve> list = query.getResultList();
		System.out.println(list);
		session.close();
		return list;
	}

	private static String clob2String(Clob clob) throws Exception {
		return (clob != null ? clob.getSubString(1, (int) clob.length()) : null);
	}

	public void InsertValve(JSONObject jObject) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		MetadataValve s = new MetadataValve();
		s.setVId(jObject.getString("VId"));
		s.setVType(jObject.getString("VType"));
		s.setVPressure(jObject.getString("VPressure"));
		s.setVFlow(jObject.getString("VFlow"));
		s.setVTemperature(jObject.getString("VTemperature"));
		s.setVCaliber(jObject.getString("VCaliber"));
		s.setVLength(jObject.getString("VLength"));
		s.setVThickness(jObject.getString("VThickness"));
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
	public void UpdateValve(JSONObject jObject) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		MetadataValve s = new MetadataValve();
		s.setVId(jObject.getString("VId"));
		System.out.println(s.getVId());
		s.setVType(jObject.getString("VType"));
		s.setVPressure(jObject.getString("VPressure"));
		s.setVFlow(jObject.getString("VFlow"));
		s.setVTemperature(jObject.getString("VTemperature"));
		s.setVCaliber(jObject.getString("VCaliber"));
		s.setVLength(jObject.getString("VLength"));
		s.setVThickness(jObject.getString("VThickness"));
		session.update(s);
		tx.commit();
		session.close();
	}

	// 通过id获取学生信息
	public JSONObject getValveById(String id) {
		JSONObject json = null;
		String sql = "select * from MetadataValve where id = '" + id + "'";
		List<MetadataValve> data = DBSelect(sql);
		if (data.size() > 0) {
			json = JSONObject.fromObject(data.get(0));
		}
		return json;
	}

	// 通过id删除学生信息
	public void deleteValveById(String id) {
		/* 用的是这个吗 */
		System.out.println("用的是这个吗 ");
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		MetadataValve s = new MetadataValve();
		s.setVId(id);
		session.delete(s);
		tx.commit();
		session.close();
	}

	@Override
	public JSONObject getValvemultisearch(JSONObject data, int index, int pageSize, String sortField, String sortOrder)
			throws Exception {
		System.out.println(data);
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		HashMap result = new HashMap();
		String sql = "select s from MetadataValve as s";
		String where = "";
		Boolean b = false;
		if (data.isEmpty()) {
		} else {
			if (data.getString("isturnVId").equals("true")) {
				/* if (data.getString("EType").equals("")) { */
				where = where + " VId='" + data.getString("VId") + "'";
				System.out.println(where);
				b = true;
				/* } */
			}
			if (data.getString("isturnVType").equals("true")) {
				if (b)
					where = where + " and VType='" + data.getString("VType") + "'";
				else {
					b = true;
					where = where + " VType='" + data.getString("VType") + "'";
				}
			}
			if (data.getString("isturnVPressure").equals("true")) {
				/* if (data.getString("EType") != null) { */
				if (b)
					where = where + " and VPressure='" + data.getString("VPressure") + "'";
				else {
					b = true;
					where = where + " VPressure='" + data.getString("VPressure") + "'";
				}
				System.out.println(where);
				/* } */
			}
			if (data.getString("isturnVFlow").equals("true")) {
				/* if (data.getString("EFlow") != null) { */
				if (b)
					where = where + "and VFlow='" + data.getString("VFlow") + "'";
				else {
					b = true;
					where = where + " VFlow='" + data.getString("VFlow") + "'";
				}
				System.out.println(where);
				/* } */
			}
			if (data.getString("isturnVTemperature").equals("true")) {
				/* if (data.getString("ETemperature") != null) { */
				if (b)
					where = where + "and VTemperature='" + data.getString("VTemperature") + "'";
				else {
					where = where + " VTemperature='" + data.getString("VTemperature") + "'";
					b = true;
				}
				/* } */

			}
			if (data.getString("isturnVCaliber").equals("true")) {
				/* if (data.getString("ECaliber") != null) { */
				if (b)
					where = where + "and VCaliber='" + data.getString("VCaliber") + "'";
				else {
					b = true;
					where = where + " VCaliber='" + data.getString("VCaliber") + "'";
				}
				/* } */
			}
			if (data.getString("isturnVThickness").equals("true")) {
				/* if (data.getString("EThickness") != null) { */
				if (b)
					where = where + "and VThickness='" + data.getString("VThickness") + "'";
				else {
					b = true;
					where = where + " VThickness='" + data.getString("VThickness") + "'";
				}
				/* } */
			}
			if (b) {
				sql = sql + " where" + where;
			}
		}
		System.out.println(sql);
		System.out.println(" 这里是dao中的查询的开始");
		List<MetadataValve> dataAll = DBSelect(sql);
		System.out.println(" 这里是dao中的查询的结束");

		/* System.out.println(" dataall的数据值：" + dataAll.toString()); */
		ArrayList list = new ArrayList();
		int start = index * pageSize, end = start + pageSize;

		for (int i = 0, l = dataAll.size(); i < l; i++) {
			// HashMap record = (HashMap)dataAll.get(i);
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
		System.out.println(json);
		return json;
	}


	@Override
	public JSONObject addEvaluationElbow(JSONObject jrows) {
		float speed, caliber, length, AmbientTemperature, Temperature;
		session = sessionFactory.openSession();
		request=ServletActionContext.getRequest();
		System.out.println(jrows);
		tx = session.beginTransaction();
		speed = Float.parseFloat(jrows.getString("VSpeed"));
		caliber = Float.parseFloat(jrows.getString("VCaliber"));
		length = Float.parseFloat(jrows.getString("VLength"));
		AmbientTemperature = Float.parseFloat(jrows.getString("VExternalTemperature"));
		Temperature = Float.parseFloat(jrows.getString("VAmbientTemperature"));
		EvaluateValve s = new EvaluateValve();
		if(jrows.getString("id").length()!=0)
			s.setId(jrows.getString("id"));
		s.setVSpeed(jrows.getString("VSpeed"));
		s.setVId(jrows.getString("VId"));
		s.setVName(request.getSession().getAttribute("username").toString());
		s.setVCaliber(jrows.getString("VCaliber"));
		s.setVLength(jrows.getString("VLength"));
		s.setVAmbientTemperature(jrows.getString("VAmbientTemperature"));
		s.setVExternalTemperature(jrows.getString("VExternalTemperature"));
		Date date = new Date();
		s.setVTime(date);
		double result = evalution(speed, caliber, length, AmbientTemperature, Temperature);
		if (result < 107) {
			s.setVResult("合格");
		} else
			s.setVResult("不合格");
		s.setVHeatLoss(String.format("%.3f", result));
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
		String sql = "select s from EvaluateValve as s";
		String where = "";
		Boolean b = false;
		if(data.containsKey("isturnVId")){
		if (data.getString("isturnVId").equals("true")) {
			/* if (data.getString("EType").equals("")) { */
			where = where + " vid='" + data.getString("VId") + "'";
			System.out.println(where);
			b = true;
			/* } */
		}
		if (data.getString("isturnSTime").equals("true")) {
			String time=data.getString("STime");
			time=time.substring(0, 10);
			if (b)
				where = where + " and  vtime  >='" +time+ " 00:00:00:00'";
			else {
				b = true;
				where = where + " vtime  >='" +time+ " 00:00:00:00'";
			}
		}
		if (data.getString("isturnETime").equals("true")) {
			String etime=data.getString("ETime");
			etime=etime.substring(0, 10);
			if (b)
				where = where + " and  vtime  <='" +etime+ " 23:59:59:59'";
			else {
				b = true;
				where = where + " vtime  <='" +etime+ " 23:59:59:59'";
			}
		}
		if (data.getString("isturnEName").equals("true")) {
			/* if (data.getString("EFlow") != null) { */
			if (b)
				where = where + "and vname='" + data.getString("VName") + "'";
			else {
				b = true;
				where = where + " vname='" + data.getString("VName") + "'";
			}
			System.out.println(where);
			/* } */
		}
		if (data.getString("isturnEResult").equals("true")) {
			/* if (data.getString("ETemperature") != null) { */
			if (b)
				where = where + "and vresult='" + data.getString("VResult") + "'";
			else {
				where = where + " vresult='" + data.getString("VResult") + "'";
				b = true;
			}
			/* } */

		}
		if (b) {
			sql = sql + " where" + where;
		}
		}
		List<MetadataValve> dataAll = DBSelect(sql);
 		ArrayList list = new ArrayList();
		int start = index * pageSize, end = start + pageSize;

		for (int i = 0, l = dataAll.size(); i < l; i++) {
			// HashMap record = (HashMap)dataAll.get(i);
			HashMap record = JBToHashMap.objToHash(dataAll.get(i));
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			record.put("Vtime", format.format(record.get("VTime")));
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
		EvaluateValve s = new EvaluateValve();
		s.setId(id);
		session.delete(s);
		tx.commit();
		session.close();
	}

}
