package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.vo.UserVo;

public class ReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if(((UserVo) session.getAttribute("authuser")) == null) {
			System.out.println("세션이 종료되었습니다.");
			System.out.println("다시 로그인해주세요.");
			WebUtils.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return ;
		}
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/replyform.jsp");

	}

}
