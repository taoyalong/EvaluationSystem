package com.yalong.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.yalong.entity.MetadataElbow;
import com.yalong.entity.User;
import com.yalong.utils.JBToHashMap;
import com.yalong.utils.JSON;

import net.sf.json.JSONObject;

public class UsrDaoImpl implements UserDao {

	// ��SSH��������Ҫʹ��ĳ��ʵ������ô�Ͷ�������һ������Ȼ��
	// ��������set����������springע�������
	// ʵ�ֲ�Ҫ��ע���ʵ������������Լ���ô��������������˭
	private SessionFactory sessionFactory;/* ����sessionfactory���� */
	Transaction tx;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {/* Ϊ�丳ֵ */
		this.sessionFactory = sessionFactory;
	}

	Session session;

	@Override
	public List<User> getUser(User u) {

		// sessionFactory���ʵ�������Լ��������hibernate��ͳд������
		// Ҳ���Խ���springȥ�й�

		// ��ȡsession
		session = sessionFactory.openSession();/* ����һ��session */

		String sql = "from User as u where u.name='" + u.getName() + "'" + " and u.password='" + u.getPassword()+"'";

		List<User> list = DBSelect(sql);
		// �ر�sessionFactory
		sessionFactory.close();
		System.out.println(list);
		return list;
	}

	public List<User> DBSelect(String sql) {
		/* System.out.println(sql); */
		Query<User> query = session.createQuery(sql);
		// �����е����ݲ�ѯ�������ŵ�List������
		List<User> list = query.getResultList();
		System.out.println(list);
		session.close();
		return list;
	}

	public void InsertElbow(JSONObject jObject) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		User s = new User();
		s.setAge(jObject.getString("age"));
		s.setName(jObject.getString("name"));
		s.setPassword(jObject.getString("password")); 
		s.setRole(jObject.getString("role"));
		s.setGender(jObject.getString("gender"));
		s.setPosition(jObject.getString("position"));
		s.setPhone(jObject.getString("phone"));
		session.save(s);
		tx.commit();
		session.close();
	}

	// �������ݸ���
	public void UpdateElbow(JSONObject jObject) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		System.out.println("����daoupdate");
		User s = new User();
		s.setId(jObject.getString("id"));
		s.setAge(jObject.getString("age"));
		s.setRole(jObject.getString("role"));
		s.setName(jObject.getString("name"));
		s.setPassword(jObject.getString("password"));
		s.setGender(jObject.getString("gender"));
		s.setPosition(jObject.getString("position"));
		s.setPhone(jObject.getString("phone"));
		session.update(s);

		/*
		 * System.out.println(jObject); String hql =
		 * "update User ch set ch.position=:position,ch.name=:name,ch.phone=:phone,ch.age=:age,ch.role=:role,ch.gender=:gender,ch. where ch.id= :id"
		 * ; Query<User> query = session.createQuery(hql);
		 * query.setParameter("position", jObject.getString("position"));
		 * query.setParameter("name", jObject.getString("name"));
		 * query.setParameter("phone", jObject.getString("phone"));
		 * query.setParameter("age", jObject.getString("age"));
		 * query.setParameter("role", jObject.getString("role"));
		 * query.setParameter("gender", jObject.getString("gender"));
		 * query.setParameter("id", jObject.getString("id")); query.executeUpdate();
		 */
		tx.commit();
		session.close();

	}

	// ͨ��idɾ����Ϣ
	public void deleteElbowById(String id) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		User s = new User();
		s.setId(id);
		session.delete(s);
		tx.commit();
		session.close();
	}

	@Override
	public JSONObject getElbowmultisearch(JSONObject data, int index, int pageSize, String sortField, String sortOrder)
			throws Exception {
		System.out.println(data);
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		HashMap result = new HashMap();
		String sql = "select s from User as s";
		String where = "";
		Boolean b = false;
		System.out.println(data.getString("Id"));
		if (data.getString("isturnVId").equals("true")) {
			/* if (data.getString("EType").equals("")) { */
			where = where + " id='" + data.getString("Id") + "'";
			System.out.println(where);
			b = true;
			/* } */
		}
		if (data.getString("isturnVType").equals("true")) {
			/* if (data.getString("EType") != null) { */
			if (b)
				where = where + " and name='" + data.getString("name") + "'";
			else {
				b = true;
				where = where + " name='" + data.getString("name") + "'";
			}
			System.out.println(where);
			/* } */
		}
		if (data.getString("isturnVPressure").equals("true")) {
			/* if (data.getString("EFlow") != null) { */
			if (b)
				where = where + "and role='" + data.getString("role") + "'";
			else {
				b = true;
				where = where + " role='" + data.getString("role") + "'";
			}
			System.out.println(where);
			/* } */
		}
		/*
		 * if (data.getString("isturnETemperature").equals("true")) { if
		 * (data.getString("ETemperature") != null) { if (b) where = where +
		 * "and ETemperature='" + data.getString("ETemperature") + "'"; else { where =
		 * where + " ETemperature='" + data.getString("ETemperature") + "'"; b = true; }
		 * }
		 * 
		 * }
		 */
		/*
		 * if (data.getString("isturnECaliber").equals("true")) { if (b) where = where +
		 * "and ECaliber='" + data.getString("ECaliber") + "'"; else { b = true; where =
		 * where + " ECaliber='" + data.getString("ECaliber") + "'"; } }
		 */
		/*
		 * if (data.getString("isturnEEThickness").equals("true")) { if (b) where =
		 * where + "and EThickness='" + data.getString("EThickness") + "'"; else { b =
		 * true; where = where + " EThickness='" + data.getString("EThickness") + "'"; }
		 * }
		 */
		if (b) {
			sql = sql + " where" + where;
		}
		System.out.println(sql);
		System.out.println(" ������dao�еĲ�ѯ�Ŀ�ʼ");
		List<User> dataAll = DBSelect(sql);
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
		return json;
	}

	@Override
	public JSONObject getuserbyname(String name) throws Exception {
		session = sessionFactory.openSession();/* ����һ��session */
		String sql = "from User as u where u.name='" + name+"'";
		// �ر�sessionFactory
		Query<User> query = session.createQuery(sql);
		// �����е����ݲ�ѯ�������ŵ�List������
		User user = query.getResultList().get(0);
		session.close();
		System.out.println(" ������dao�еĲ�ѯ�Ľ���");
		String result2 = JSON.Encode(user);
		JSONObject json = JSONObject.fromObject(result2);
		return json;
	}

	@Override
	public JSONObject editpass(User user) {
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update User ch set ch.password = :password where ch.name= :name";
		Query<User> query = session.createQuery(hql);
		query.setParameter("password", user.getPassword());
		query.setParameter("name", user.getName());
		query.executeUpdate();
		tx.commit();
		session.close();
		return null;
	}

}