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

import com.yalong.entity.EvaluateElbow;
import com.yalong.entity.EvaluatePipeline;
import com.yalong.entity.MetadataElbow;
import com.yalong.entity.MetadataPipeline;
import com.yalong.utils.JBToHashMap;
import com.yalong.utils.JSON;

@Component("PipelineDao")
public class PipelineDaoImpl implements PipelineDao {
	private SessionFactory sessionFactory;
	Transaction tx;
	HttpServletRequest request;
	HttpServletResponse response;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	Session session;

	// ��������
	public JSONObject searchpipel(JSONObject data, int index, int pageSize, String sortField, String sortOrder)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		HashMap result = new HashMap();
		String sql = "select s from MetadataPipeline as s";
		String where = "";
		Boolean b = false;
		if(data.isEmpty()) {}
		else{if (data.getString("isturnPId").equals("true")) {
			/* if (data.getString("EType").equals("")) { */
			where = where + " PId='" + data.getString("PId") + "'";
			System.out.println(where);
			b = true;
			/* } */
		}
		if (data.getString("isturnPType").equals("true")) {
			/* if (data.getString("EType") != null) { */
			if (b)
				where = where + " and PType='" + data.getString("PType") + "'";
			else {
				b = true;
				where = where + " PType='" + data.getString("PType") + "'";
			}
			System.out.println(where);
			/* } */
		}
		if (data.getString("isturnPPressure").equals("true")) {
			/* if (data.getString("EFlow") != null) { */
			if (b)
				where = where + "and PPressure='" + data.getString("PPressure") + "'";
			else {
				b = true;
				where = where + " PPressure='" + data.getString("PPressure") + "'";
			}
			System.out.println(where);
			/* } */
		}
		if (data.getString("isturnPFlow").equals("true")) {
			/* if (data.getString("ETemperature") != null) { */
			if (b)
				where = where + "and PFlow='" + data.getString("PFlow") + "'";
			else {
				where = where + " PFlow='" + data.getString("PFlow") + "'";
				b = true;
			}
			/* } */

		}
		if (data.getString("isturnPTemperature").equals("true")) {
			/* if (data.getString("ECaliber") != null) { */
			if (b)
				where = where + "and PTemperature='" + data.getString("PTemperature") + "'";
			else {
				b = true;
				where = where + " PTemperature='" + data.getString("PTemperature") + "'";
			}
			/* } */
		}
		if (data.getString("isturnPTemperature").equals("true")) {
			/* if (data.getString("EThickness") != null) { */
			if (b)
				where = where + "and PTemperature='" + data.getString("PTemperature") + "'";
			else {
				b = true;
				where = where + " PTemperature='" + data.getString("PTemperature") + "'";
			}
			/* } */
		}
		if (data.getString("isturnPLength").equals("true")) {
			/* if (data.getString("EThickness") != null) { */
			if (b)
				where = where + "and PLength='" + data.getString("PLength") + "'";
			else {
				b = true;
				where = where + " PLength='" + data.getString("PLength") + "'";
			}
			/* } */
		}
		if (data.getString("isturnPThickness").equals("true")) {
			/* if (data.getString("EThickness") != null) { */
			if (b)
				where = where + "and PThickness='" + data.getString("PThickness") + "'";
			else {
				b = true;
				where = where + " PThickness='" + data.getString("PThickness") + "'";
			}
			/* } */
		}
		if (b) {
			sql = sql + " where" + where;
		}
		}
		System.out.println(sql);
		System.out.println(" ������dao�еĲ�ѯ�Ŀ�ʼ");
		List<MetadataElbow> dataAll = DBSelect(sql);
		System.out.println(" ������dao�еĲ�ѯ�Ľ���");

		/* System.out.println(" dataall������ֵ��" + dataAll.toString()); */
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

	public List<MetadataElbow> DBSelect(String sql) {
		/* System.out.println(sql); */
		Query<MetadataElbow> query = session.createQuery(sql);
		// �����е����ݲ�ѯ�������ŵ�List������
		List<MetadataElbow> list = query.getResultList();
		return list;
	}

	private static String clob2String(Clob clob) throws Exception {
		return (clob != null ? clob.getSubString(1, (int) clob.length()) : null);
	}

	public void Insertpipel(JSONObject jObject) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		MetadataPipeline s = new MetadataPipeline();
		System.out.println(jObject);
		s.setPId(jObject.getString("PId"));
		s.setPType(jObject.getString("PType"));
		s.setPPressure(jObject.getString("PPressure"));
		s.setPFlow(jObject.getString("PFlow"));
		s.setPTemperature(jObject.getString("PTemperature"));
		s.setPCaliber(jObject.getString("PCaliber"));
		s.setPLength(jObject.getString("PLength"));
		s.setPThickness(jObject.getString("PThickness"));
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

	// �������ݸ���
	public void Updatepipel(JSONObject jObject) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		MetadataPipeline s = new MetadataPipeline();
		System.out.println(jObject);
		s.setPId(jObject.getString("PId"));
		s.setPType(jObject.getString("PType"));
		s.setPPressure(jObject.getString("PPressure"));
		s.setPFlow(jObject.getString("PFlow"));
		s.setPTemperature(jObject.getString("PTemperature"));
		s.setPCaliber(jObject.getString("PCaliber"));
		s.setPLength(jObject.getString("PLength"));
		s.setPThickness(jObject.getString("PThickness"));
		session.update(s);
		tx.commit();
		session.close();
	}

	// ͨ��id��ȡ�ܵ���Ϣ
	public JSONObject getpipelById(String id) {
		JSONObject json = null;
		String sql = "select * from MetadataPipeline where id = '" + id + "'";
		List<MetadataElbow> data = DBSelect(sql);
		if (data.size() > 0) {
			json = JSONObject.fromObject(data.get(0));
		}
		return json;
	}

	// ͨ��idɾ����Ϣ
	public void deletepipelById(String id) {
		System.out.println("daobegin");
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		MetadataPipeline s = new MetadataPipeline();
		s.setPId(id);
		session.delete(s);
		tx.commit();
		System.out.println("enddao");
		session.close();
	}
	
	@Override
	public JSONObject addEvaluationElbow(JSONObject jrows) {
		float speed, caliber, length, AmbientTemperature, Temperature;
		session = sessionFactory.openSession();
		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		System.out.println(jrows);
		tx = session.beginTransaction();
		speed = Float.parseFloat(jrows.getString("PSpeed"));
		caliber = Float.parseFloat(jrows.getString("PCaliber"));
		length = Float.parseFloat(jrows.getString("PLength"));
		AmbientTemperature = Float.parseFloat(jrows.getString("PExternalTemperature"));
		Temperature = Float.parseFloat(jrows.getString("PAmbientTemperature"));
		EvaluatePipeline s = new EvaluatePipeline();
		if(jrows.getString("id").length()!=0)
			s.setId(jrows.getString("id"));
		s.setPSpeed(jrows.getString("PSpeed"));
		s.setPId(jrows.getString("PId"));
		s.setPCaliber(jrows.getString("PCaliber"));
		s.setPLength(jrows.getString("PLength"));
		s.setPName(request.getSession().getAttribute("username").toString());
		s.setPAmbientTemperature(jrows.getString("PAmbientTemperature"));
		s.setPExternalTemperature(jrows.getString("PExternalTemperature"));
		Date date = new Date();
		s.setPTime(date);
		double result = evalution(speed, caliber, length, AmbientTemperature, Temperature);
		if (result <107) {
			s.setPResult("�ϸ�");
		} else
			s.setPResult("���ϸ�");
		s.setPHeatLoss(String.format("%.3f", result));
		JSONObject data =JSONObject.fromObject(s);
		session.saveOrUpdate(s);
		tx.commit();
		session.close();
		return data;
	}

	public double evalution(float u, float d, float l, float Tw, float T) {
		System.out.println("���������۷���");
		double Re = (u * d) / (0.00001506);//����ǿ�ƶ������ȹ�ʽ������ŵ��Re��
		double Nu = 0.0266 * (Math.pow(Re, 0.805)) * Math.pow(0.703, 1 / 3d);//�õ���ŵ��Re���ټ���Ŭ������Nu��
		double Hc = Nu * 0.0267 / d;//�õ�Ŭ������֮�󣬲�����ʽ�����������ϵ��hc
		//��	Ȼ���ټ���������뻷��֮����任���ۺϵĶ�������ϵ��hr
		double Hr = 0.6 * (0.0000000567 ) * ((Tw+273) * (Tw+273) + (T+273) *( T+273)) * (Tw+273 + T+273);
		//�ܶ�������ϵ��H
		double h = Hc + Hr;
		//����ɢ����ʧ 
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
		String sql = "select s from EvaluatePipeline as s";
		String where = "";
		Boolean b = false;
		if(data.containsKey("isturnEId")){
		if (data.getString("isturnEId").equals("true")) {
			/* if (data.getString("EType").equals("")) { */
			where = where + " pid='" + data.getString("PId") + "'";
			System.out.println(where);
			b = true;
			/* } */
		}
		if (data.getString("isturnSTime").equals("true")) {
			String time=data.getString("STime");
			time=time.substring(0, 10);
			if (b)
				where = where + " and  ptime  >='" +time+ " 00:00:00:00'";
			else {
				b = true;
				where = where + " ptime  >='" +time+ " 00:00:00:00'";
			}
		}
		if (data.getString("isturnETime").equals("true")) {
			String etime=data.getString("ETime");
			etime=etime.substring(0, 10);
			if (b)
				where = where + " and  ptime  <='" +etime+ " 23:59:59:59'";
			else {
				b = true;
				where = where + " ptime  <='" +etime+ " 23:59:59:59'";
			}
		}
		if (data.getString("isturnEName").equals("true")) {
			/* if (data.getString("EFlow") != null) { */
			if (b)
				where = where + "and pname='" + data.getString("PName") + "'";
			else {
				b = true;
				where = where + " pname='" + data.getString("PName") + "'";
			}
			System.out.println(where);
			/* } */
		}
		if (data.getString("isturnEResult").equals("true")) {
			/* if (data.getString("ETemperature") != null) { */
			if (b)
				where = where + "and presult='" + data.getString("PResult") + "'";
			else {
				where = where + " presult='" + data.getString("PResult") + "'";
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
			record.put("Ptime", format.format(record.get("PTime")));
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
		EvaluatePipeline s = new EvaluatePipeline();
		s.setId(id);
		session.delete(s);
		tx.commit();
		session.close();
	}


}