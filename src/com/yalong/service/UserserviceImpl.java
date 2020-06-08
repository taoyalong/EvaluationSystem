package com.yalong.service;

import java.util.List;

import javax.annotation.Resource;

import com.yalong.dao.UserDao;
import com.yalong.entity.User;

import net.sf.json.JSONObject;



public class UserserviceImpl implements Userservice {
	 //dao实例使用注入方式
    private UserDao id;
    //用于注入使用
	@Resource
	public void setId(UserDao id) {/* Spring为其赋值IndexDaoImpl */
        this.id = id;
    }

	@Override
	public int login(User u) {
		System.out.println("进入getuser");
		List<User> tag = id.getUser(u);
		System.out.println("出了getuser");
		 if( tag != null && tag.size() > 0 ) 
			{
			 
				if(tag.get(0).getRole().equals("adm"))
				{	
					
					return 1;
				}else if(tag.get(0).getRole().equals("com"))
				{
					
					return 2;
				}
			}
			return 3;
	}

	@Override
	public JSONObject mulisearchElbow(JSONObject jrows, int pageIndex1, int pageSize1, String sortField,
			String sortOrder) throws Exception {
		return id.getElbowmultisearch(jrows, pageIndex1, pageSize1, sortField, sortOrder);
	}

	@Override
	public void addElbow(JSONObject row) {
		id.InsertElbow(row);
	}

	@Override
	public void deleteElbowById(String Id) {
		id.deleteElbowById(Id);
	}

	@Override
	public void updateElbowInfo(JSONObject row) {
		id.UpdateElbow(row);
	}

	@Override
	public JSONObject getuser( String name) throws Exception {
		return id.getuserbyname(name);
	}

	@Override
	public JSONObject editpass(User user) {
		return id.editpass(user);
	}

}
