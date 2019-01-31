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

public class InsertReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		UserVo userVo = (UserVo) session.getAttribute("authuser");
		if(((UserVo) session.getAttribute("authuser")) == null) {
			System.out.println("세션이 종료되었습니다.");
			System.out.println("다시 로그인해주세요.");
			WebUtils.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return ;
		}
		
		long no = Long.parseLong(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVo vo = new BoardVo();
		
		vo.setNo(no);
		vo.setUserNo(userVo);
		vo.setTitle(title);
		vo.setContents(content);
		
		boolean result = new BoardDao().insertReply(vo);
		List<BoardVo> grouplist = new BoardDao().getReplyList(no);
		System.out.println("no : " + no);
		int minorderNo = new BoardDao().getOrderNo(no) + 1;
		System.out.println("minorderNo : " + minorderNo);
		int copyOrderNo = minorderNo;
		
		for(BoardVo replyvo : grouplist) {
			System.out.println("no:"+replyvo.getNo()+",title:"+replyvo.getTitle()+",g_no:"+replyvo.getGroupNo()+",o_no:"+replyvo.getOrderNo());
			new BoardDao().updateOrder(replyvo);
		}
		
		new BoardDao().updateOrder(grouplist.get(grouplist.size()-1), minorderNo);
	
		if(!result) {
			System.out.println("reply insert fail");
			WebUtils.redirect(request, response, request.getContextPath() + "/board");
			return;
		}
		
		System.out.println("reply insert success");
		WebUtils.redirect(request, response, request.getContextPath() + "/board");
		
	}

}
