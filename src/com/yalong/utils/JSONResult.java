package com.yalong.utils;

import java.io.PrintWriter;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.util.ValueStack;

public class JSONResult implements Result{

	private static final long serialVersionUID = 1L;
	
	public void execute(ActionInvocation invocation) {
		try {
			ServletActionContext.getResponse().setContentType("text/plain,charset=utf-8");
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			PrintWriter responseStream = ServletActionContext.getResponse().getWriter();
			ValueStack valueStack = invocation.getStack();
			Object jsonModel = valueStack.findValue("jsonModel");
			System.out.println(jsonModel);
			responseStream.print(jsonModel);
			} 
		catch (Exception e) {
				System.out.println(e);
				}
		}
}
