package com.zocom.assignment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zocom.assignment.model.Account;
import com.zocom.assignment.model.ServiceResponse;
import com.zocom.assignment.model.Session;
import com.zocom.assignment.service.HttpRoute;
import com.zocom.assignment.service.SessionHandler;

@WebServlet(urlPatterns = "/profile/*")
public class ProfileServlet extends BaseServlet {
	
	@Override
	protected void configureRoutes(HttpRoute routes) {
		routes
			.addDefaultRoute(this::sendProfile)
			.addRoute(HttpRoute.M_POST, "logout", this::logoutUser)
			.addRoute(HttpRoute.M_POST, "delete", this::deleteUser);
	}
	
	private void sendProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		verifySessionAuthentication(SessionHandler.set()
		    .failure(() -> signoutSession())
		    .success(() -> {
		      attachAccount(request);
		      sendJSP("profile");
		    })
		);
	}
	
	private void attachAccount(HttpServletRequest request) throws ServletException, IOException {
    String username = getSessionValue("username");
    getAccountDao().fetch(username, AccountAsync()
      .success(account -> {
        request.setAttribute("account", account);
      })
      .failure(message -> signoutSession())
    );
	}
	
	private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
	  signoutSession();
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		getAccountDao().remove(getSessionValue("username"), AccountAsync()
		    .success(account -> logoutUser(request, response))
		    .failure(message -> appendHTML(message))
    );
	}
}
	
