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

	// 在SSH的设计理念：要使用某个实例，那么就定义声明一个对象，然后
	// 给它添加set方法（用于spring注入进来）
	// 实现不要关注这个实例来自于那里，以及怎么创建，或者它是谁
	private SessionFactory sessionFactory;/* 建立sessionfactory对象 */
	Transaction tx;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {/* 为其赋值 */
		this.sessionFactory = sessionFactory;
	}

	Session session;

	@Override
	public List<User> getUser(User u) {

		// sessionFactory这个实例可以自己按常规的hibernate传统写法创建
		// 也可以交给spring去托管

		// 获取session
		session = sessionFactory.openSession();/* 开启一个session */

		String sql = "from User as u where u.name='" + u.getName() + "'" + " and u.password='" + u.getPassword()+"'";

		List<User> list = DBSelect(sql);
		// 关闭sessionFactory
		sessionFactory.close();
		System.out.println(list);
		return list;
	}

	public List<User> DBSelect(String sql) {
		/* System.out.println(sql); */
		Query<User> query = session.createQuery(sql);
		// 将所有的数据查询出来并放到List集合里
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

	// 进行数据更新
	public void UpdateElbow(JSONObject jObject) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		System.out.println("进入daoupdate");
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

	// 通过id删除信息
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
		System.out.println(" 这里是dao中的查询的开始");
		List<User> dataAll = DBSelect(sql);
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
		return json;
	}

	@Override
	public JSONObject getuserbyname(String name) throws Exception {
		session = sessionFactory.openSession();/* 开启一个session */
		String sql = "from User as u where u.name='" + name+"'";
		// 关闭sessionFactory
		Query<User> query = session.createQuery(sql);
		// 将所有的数据查询出来并放到List集合里
		User user = query.getResultList().get(0);
		session.close();
		System.out.println(" 这里是dao中的查询的结束");
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
