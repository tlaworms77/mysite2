package com.douzone.mysite.controller.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.vo.UserVo;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AjaxServelt2
 */
@WebServlet("/ajax2")
public class AjaxServelt2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// java object -> json string
		UserVo vo = new UserVo();
		vo.setNo(10);
		vo.setName("둘리");
		vo.setEmail("dooly@gmail.com");
		vo.setGender("male");
		
		JSONObject jsonObject = JSONObject.fromObject(vo);
		String jsonString = jsonObject.toString(); // vo -> json로 바뀐다.
		
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.println(jsonString);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		super.doPost(req, resp);
	}
}
