package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVo vo = new BoardVo();
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		HttpSession session = request.getSession();
		
		if(((UserVo) session.getAttribute("authuser")) == null) {
			System.out.println("세션이 종료되었습니다.");
			System.out.println("다시 로그인해주세요.");
			WebUtils.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return ;
		}
		
		long userNo = ((UserVo) session.getAttribute("authuser")).getNo();
		
		vo.setTitle(title);
		vo.setContents(contents);
		UserVo uVo = new UserVo();
		uVo.setNo(userNo);
		vo.setUserNo(uVo);
		
		boolean result = new BoardDao().insert(vo);
		
		if(!result) {
			System.out.println("board insert : fail");
			WebUtils.redirect(request, response, request.getContextPath() + "/board");
			return;
		}
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board");
		System.out.println("board insert : success");
		
	}

}
