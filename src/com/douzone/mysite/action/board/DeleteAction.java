package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long no = Long.parseLong(request.getParameter("no"));
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		UserVo userVo = (UserVo) session.getAttribute("authuser");
		long userNo = userVo.getNo();
		System.out.println(userNo);
		userVo.setPassword(password);
		
		boolean userCheck = new BoardDao().deleteCheck(no, userVo);
		
		if(!userCheck) {
			System.out.println("비밀번호가 틀리셨습니다.");
			WebUtils.redirect(request, response, request.getContextPath() + "/board");
			return;
		} else {
//			boolean result = new BoardDao().delete(no, userNo, password);
//			new BoardDao().delete(no);
//			System.out.println("[" + no + "] 게시글 삭제 성공");
			
			List<BoardVo> list = new BoardDao().getDeleteCheckList(no);
			int parentDepth = list.get(0).getDepth();
			int count = 0;
			
			for(BoardVo vo : list) {
				if(parentDepth >= vo.getDepth()) {
					count++;
					if(count == 2){
						break;
					}
				}
				new BoardDao().delete(vo.getNo());
			}
			System.out.println("[" + no + "] 게시글 삭제 성공");
			
		}

		WebUtils.redirect(request, response, request.getContextPath() + "/board");

	}

}
