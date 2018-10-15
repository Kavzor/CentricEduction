package com.centric.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;


@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if(username.equals("admin") && password.equals("123")) {
			String hashedPw = BCrypt.hashpw(password, BCrypt.gensalt());
			
			req.setAttribute("hashedPw", hashedPw);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
