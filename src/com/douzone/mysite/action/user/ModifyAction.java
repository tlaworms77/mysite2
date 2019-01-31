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

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		long no = ((UserVo)session.getAttribute("authuser")).getNo();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVo authUser = new UserVo();
		authUser.setNo(no);
		authUser.setName(name);
		authUser.setPassword(password);
		authUser.setGender(gender);
		
		boolean modifySuccess = new UserDao().modify(authUser);
		
		if(!modifySuccess) {
			System.out.println("회원 정보 수정 실패");
			WebUtils.redirect(request, response, request.getContextPath());
			return;
		}
		
		System.out.println("회원 정보 수정 성공");
		WebUtils.redirect(request, response, request.getContextPath());

	}

}
