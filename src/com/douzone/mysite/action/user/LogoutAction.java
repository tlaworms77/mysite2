package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.vo.UserVo;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 가져올때는 세션 매개변수에 true 할 필요없다.
		HttpSession session = request.getSession();
		
		
		if(session != null && session.getAttribute("authuser") != null) {

			// logout 처리
			// session 을 날림
			session.removeAttribute("authuser");
			session.invalidate();
			WebUtils.redirect(request, response, request.getContextPath());
			
			// redirect는 응답이 끝난것이기 떄문에 return 필수!!
			return;
		}

		// 거의 없는 경우이지만 인증이 안된 상황에서 접근한 상황 logout을
		UserVo authUser =(UserVo)session.getAttribute("authuser");
		if(authUser == null) {
			WebUtils.redirect(request, response, request.getContextPath());
			// redirect는 응답이 끝난것이기 떄문에 return 필수!!
			return;
		}
		
		WebUtils.redirect(request, response, request.getContextPath());
	}

}
