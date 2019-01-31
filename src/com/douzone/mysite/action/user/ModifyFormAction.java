package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.UserDao;
import com.douzone.mysite.vo.UserVo;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 접근제어(보안) */
		UserVo authUser = null;
		
		HttpSession session = request.getSession();
		if(session != null) {
			authUser = (UserVo) session.getAttribute("authuser");
		}
		if(authUser == null) {
			WebUtils.redirect(request, response, request.getContextPath());
			return;
		}
		
		// 회원정보 수정은 많이 안하기 때문에 session에 담을 필요없이 쿼리로 때리는 것이 낳다.
		authUser = new UserDao().getUserInfo(authUser.getNo());
		System.out.println("ModifyFormAction contextPath : " + request.getContextPath());
		request.setAttribute("authuser", authUser);
		WebUtils.forward(request, response, "WEB-INF/views/user/modifyform.jsp");
		
	}

}
