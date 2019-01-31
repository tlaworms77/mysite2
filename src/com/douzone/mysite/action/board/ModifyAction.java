package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long no = Long.parseLong(request.getParameter("no"));
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContents(contents);
		
		boolean modifySuccess = new BoardDao().modify(vo);
		
		if(!modifySuccess) {
			System.out.println("회원 정보 수정 실패");
			WebUtils.redirect(request, response, request.getContextPath() + "/board?a=" + no);
			return;
		}
		
		System.out.println("회원 정보 수정 성공");
		WebUtils.redirect(request, response, request.getContextPath() + "/board?a=" + no);
		
	}

}
