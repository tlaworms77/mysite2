package com.douzone.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;
import com.douzone.mysite.action.board.BoardActionFactory;
import com.douzone.mysite.action.guestbook.GuestbookActionFactory;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 이 처리를 filter를 통해서 뺴버렸다. 반복처리부분 제거
		//request.setCharacterEncoding("utf-8");
		
		AbstractActionFactory af = new BoardActionFactory();
		
		String actionName = request.getParameter("a");
		if(actionName == null)
			System.out.println(this.getClass().getSimpleName() + " : /board");
		else {
			System.out.println(this.getClass().getSimpleName() + " : " + actionName);
		}
		Action action = af.getAction(actionName);
		action.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
