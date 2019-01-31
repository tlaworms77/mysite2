package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.UserDao;
import com.douzone.mysite.vo.UserVo;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		System.out.println(this.getClass().getSimpleName());
		System.out.println("email : " + email);
		System.out.println("password : " + password);

		UserVo authuser = new UserDao().loginCheck(email, password);

		/*if (authuser != null) {
			System.out.println("loginCheck : true");

			HttpSession session = request.getSession();
			session.setAttribute("name", authuser.getName());

			WebUtils.redirect(request, response, request.getContextPath() + "?a=");
		}*/
		if (authuser == null) {
			System.out.println("loginCheck : false");
			request.setAttribute("result", "fail");
			WebUtils.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return;
		}

		/* 인증성공 -> 인증처리 */
		//true면 없으면 만들어달라는 것
		HttpSession session = request.getSession(true);
		session.setAttribute("authuser", authuser);
		
		/* main redirect(path만적으면 된다. main이기 떄문) */ 
		WebUtils.redirect(request, response, request.getContextPath());

	}

}
