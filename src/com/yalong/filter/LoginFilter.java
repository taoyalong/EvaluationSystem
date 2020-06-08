package com.yalong.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter extends HttpServlet implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		Boolean redirect_flag = true;/* �ض����ʶλ */

		HttpServletRequest request = (HttpServletRequest) arg0;
		/*
		 * ActionContext actionContext = arg0.getInvocationContext(); HttpServletRequest
		 * request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		 */
		HttpServletResponse response = (HttpServletResponse) arg1;
		// ��ȡ��session���󣬵�¼״̬��Ϣ���������
		HttpSession session = request.getSession();
		// ��ȡ������·��
		String contextPath = request.getContextPath();
		// ��ȡ����·��
		String url = request.getServletPath();
		System.out.println("ServletPath=" + url);

		// ���ü��һ�з�/login.jsp,/login.action,/images/������
		if (!url.startsWith("/login.jsp") && !url.startsWith("/res") && !url.startsWith("/user/user.action")
				&& !url.startsWith("/scripts") && !url.startsWith("/images/") && !url.endsWith(".txt")
				&& !url.endsWith(".css") && !url.endsWith(".jpg") && !url.endsWith(".gif") && !url.endsWith("/res")) {
			// ��ȡsession����ĵ�¼״̬
			Cookie cookies[] = request.getCookies();/* ȡcookie */
			if (request.getSession().getAttribute("username") != null) {
				System.out.println(request.getSession().getAttribute("username"));
				System.out.println(request.getSession().getAttribute("promise"));
				redirect_flag = false;
				if (url.endsWith("index.jsp")) {
					if (request.getSession().getAttribute("promise").equals("admin")) {
					} else {
						redirect_flag = true;
						System.out.println(redirect_flag);
					}
				} else if (url.endsWith("index1.jsp")) {
					if (request.getSession().getAttribute("promise").equals("common")) {
					} else {
						redirect_flag = true;
						System.out.println(redirect_flag);
					}
				}

			}

			// �ж����δ��¼���ض��򵽵�¼ҳ��
			if (redirect_flag) {
				System.out.println("�ض��򵽣�" + contextPath + "/login.jsp");
				response.sendRedirect(contextPath + "/login.jsp");
				return;
			} else {
				System.out.println("�Ѿ���¼�˲�����");
			}
		} else {
			System.out.println("�Ҳ�����");
		}

		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}