package com.yalong.dao;

import java.util.List;

import com.yalong.entity.User;

import net.sf.json.JSONObject;

public interface UserDao {

	public List<User> getUser(User u);
	public abstract void InsertElbow(JSONObject jObject);

	public abstract void deleteElbowById(String id);

	//进行数据更新
	public abstract void UpdateElbow(JSONObject jObject);
	//查找数据
	public abstract JSONObject getElbowmultisearch(JSONObject key, 
				int pageIndex1, int pageSize1, String sortField,String sortOrder)throws Exception;

	public JSONObject getuserbyname(String name) throws Exception;
	public JSONObject editpass(User user);

}
