package com.zocom.assignment;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zocom.assignment.db.AsyncDBResponse;
import com.zocom.assignment.model.Account;
import com.zocom.assignment.model.Session;
import com.zocom.assignment.service.AccountDao;
import com.zocom.assignment.service.AccountService;
import com.zocom.assignment.service.HttpRoute;
import com.zocom.assignment.service.SessionHandler;
import com.zocom.assignment.service.SessionHandler.Handler;

public abstract class BaseServlet extends GenericServlet {
	
	protected final String JSP_PATH = "/WEB-INF/jsp/";
	protected final String HTML_PATH = "/WEB-INF/";
	
	protected static int SESSION_REDIRECT_HOME = 0;
	private static String mHomePath = "index";
	
	private HttpServletRequest req;
	private HttpServletResponse res;
	private HttpRoute mHttpRoutes;
	
	private AccountDao mAccountDao;
	
	public BaseServlet() {
		super();
		mAccountDao = new AccountService();
		mHttpRoutes = new HttpRoute();
	}
	
	@Override
	public void init() throws ServletException {
		configureRoutes(mHttpRoutes);
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		this.req = (HttpServletRequest) req;
		this.res = (HttpServletResponse) res;
		
		mHttpRoutes.handle(this.req, this.res);	
	}

	protected void sendJSP(String file) throws ServletException, IOException {
		req.getRequestDispatcher(JSP_PATH + file + ".jsp").forward(req, res);
	}
	
	protected void sendHTML(String file) throws ServletException, IOException {
		req.getRequestDispatcher(HTML_PATH + file + ".html").forward(req, res);
	}
	
	protected void sendRedirect(String path) throws IOException {
		res.sendRedirect(req.getContextPath() + "/" + path);
	}
	
	protected void appendHTML(String html) throws IOException {
		res.getWriter().write(html);
	}
	
	protected AccountDao getAccountDao() {
		return mAccountDao;
	}
	
	protected abstract void configureRoutes(HttpRoute routes);
	
	protected void sendHome() throws IOException {
		res.sendRedirect(req.getContextPath() + "/" + mHomePath);
	}
	
	protected void setHomePath(String path) {
		mHomePath = path;
	}
	
	protected String getHomePage() {
		return mHomePath;
	}
	
	public void setSessionValue(String key, Object value) {
    req.getSession().setAttribute(key, value);
  }
	
	public <T> T getSessionValue(String key) {
	   return (T) req.getSession().getAttribute(key);
	}
  
  public void signinSession() {
    req.getSession().setAttribute("authenticated", true);
  }
  
	public void signoutSession() throws IOException {
	  req.getSession().invalidate();
	  sendHome();
	}
	
	public void verifySessionAuthentication(SessionHandler handler) throws IOException, ServletException {
	  handler.getHandler(isSessionAuthenticated()).handle();
	}
	
	public boolean isSessionAuthenticated() {
	  return req.getSession().getAttribute("authenticated") != null;
	}
	
	public AsyncDBResponse<Account> AccountAsync() {
	  return new AsyncDBResponse<Account>();
	}
}
