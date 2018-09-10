package com.zocom.assignment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zocom.assignment.model.Account;
import com.zocom.assignment.model.ServiceResponse;
import com.zocom.assignment.service.HttpRoute;

@WebServlet(urlPatterns = "/home/*")
public class HomeServlet extends BaseServlet {

	@Override
	public void init() throws ServletException {
		super.init();
		setHomePath("home");
	}

	@Override
	protected void configureRoutes(HttpRoute routes) {
		routes
			.addRoute(HttpRoute.M_POST, "register", this::registerAccount)
			.addRoute(HttpRoute.M_POST, "login", this::authenticateAccount)
			.addDefaultRoute(this::sendHome);
	}
	
	protected void sendHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendHTML("index");
	}
	
	private void registerAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Account newAccount = new Account();
		newAccount.setUsername(username);
		newAccount.setEmail(email);
		newAccount.setPassword(password);
		
		getAccountDao().register(newAccount, AccountAsync()
	    .success(account -> {
	      response.getWriter()
          .append("<p>Account " + account.getUsername() + " successfully registered</p>")
          .append("<button onclick=\"window.history.back();\" >Go back</button>");
	    })
	    .failure(message -> {
	      response.getWriter()
	        .append("<p>" + message + "</p>")
	        .append("<button onclick=\"window.history.back();\" >Go back</button>");
	    })
		);
	}
	
	private void authenticateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		getAccountDao().login(username, password, AccountAsync()
		    .success(account -> {
		      signinSession();
		      setSessionValue("username", account.getUsername());
		      sendRedirect("profile");
		    })
		    .failure(message -> {
		      response.getWriter()
  	        .append("<p>" + message + "</p>")
  	        .append("<button onclick=\"window.history.back();\" >Go back</button>");
		    })
    );
	}
}
