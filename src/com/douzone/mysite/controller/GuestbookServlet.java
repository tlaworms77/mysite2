package com.douzone.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;
import com.douzone.mysite.action.guestbook.GuestbookActionFactory;
import com.douzone.mysite.action.user.UserActionFactory;

/**
 * Servlet implementation class GuestbookServlet
 */
@WebServlet("/guestbook")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");

		AbstractActionFactory af = new GuestbookActionFactory();

		String actionName = request.getParameter("a");
		if(actionName == null)
			System.out.println(this.getClass().getSimpleName() + " : /guestbook");
		else {
			System.out.println(this.getClass().getSimpleName() + " : " + actionName);
		}
		Action action = af.getAction(actionName);
		action.execute(request, response);
	}

}
