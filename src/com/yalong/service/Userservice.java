package com.yalong.service;

import com.yalong.entity.User;

import net.sf.json.JSONObject;

public interface Userservice {
	public int login(User u);

	public JSONObject mulisearchElbow(JSONObject jrows, int pageIndex1, int pageSize1, String sortField,
			String sortOrder)throws Exception;

	public void addElbow(JSONObject row);

	public void deleteElbowById(String Id);

	public void updateElbowInfo(JSONObject row);

	public JSONObject getuser(String username) throws Exception;

	public JSONObject editpass(User user);
}
