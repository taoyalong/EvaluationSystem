package com.yalong.dao;

import java.util.List;

import com.yalong.entity.User;

import net.sf.json.JSONObject;

public interface UserDao {

	public List<User> getUser(User u);
	public abstract void InsertElbow(JSONObject jObject);

	public abstract void deleteElbowById(String id);

	//�������ݸ���
	public abstract void UpdateElbow(JSONObject jObject);
	//��������
	public abstract JSONObject getElbowmultisearch(JSONObject key, 
				int pageIndex1, int pageSize1, String sortField,String sortOrder)throws Exception;

	public JSONObject getuserbyname(String name) throws Exception;
	public JSONObject editpass(User user);

}