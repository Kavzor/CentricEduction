package com.zocom.assignment.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpRoute {
	public static final String M_GET = "GET";
	public static final String M_POST = "POST";
	
	public interface HttpRequestHandler {
		void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
	}
	
	class RouteHandler {
		private HttpRequestHandler handler;
		private String method;
		
		public RouteHandler(HttpRequestHandler handler) {
			this(M_GET, handler);
		}
		
		public RouteHandler(String method, HttpRequestHandler handler) {
			this.handler = handler;
			this.method = method;
		}

		public HttpRequestHandler getHandler() {
			return handler;
		}

		public String getMethod() {
			return method;
		}
	}
	
	private Map<String, RouteHandler> mRequestRoutes = new HashMap<>();
	
	public void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String route = req.getPathInfo();
		
		if(route == null || !mRequestRoutes.containsKey(route.replaceFirst("/", ""))) {
			handleDefaultRoute(req, resp);
		}
		else {
			RouteHandler routeHandler = mRequestRoutes.get(route.replaceFirst("/", ""));
		
			if(req.getMethod().equals(routeHandler.getMethod())) {
				routeHandler.getHandler().process(req, resp);
			}
			else {
				resp.getWriter()
					.append("Path ")
					.append(route)
					.append(" exists but not with the http method ")
					.append(req.getMethod());
			}
		}
	}
	
	private void handleDefaultRoute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if(req.getPathInfo() != null) {
			resp.sendRedirect(req.getContextPath() + req.getServletPath());
		} 
		else {
			mRequestRoutes.get(null).getHandler().process(req, resp);
		}
	}
	
	public HttpRoute addRoute(String route, HttpRequestHandler methodHandler) {
		return addRoute(M_GET, route, methodHandler);
	}
	
	public HttpRoute addRoute(String method, String route, HttpRequestHandler methodHandler) {
		mRequestRoutes.put(route, new RouteHandler(method, methodHandler));
		return this;
	}
	
	public HttpRoute addDefaultRoute(HttpRequestHandler methodHandler) {
		return addRoute(M_GET, null, methodHandler);
	}
}
