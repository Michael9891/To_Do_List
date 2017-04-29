package com.michael;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tables.Users;
/**
 *Servlet that management requests  of user order to redirect to specific page
 */
public class ServletPagesManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletPagesManagement() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users user = (Users) request.getSession().getAttribute("user");
		//Get url request and redirect to required page
		String url = request.getRequestURL().toString().substring(request.getRequestURL().toString().lastIndexOf("/") + 1);
		//Check if session exist and redirect to required page
		if (user!= null||url.equals("loginp")||url.equals("registp")){
		RequestDispatcher requestDis;
		switch (url) {
		case "homep":
			requestDis = getServletContext().getRequestDispatcher("/home.jsp");
			requestDis.forward(request, response);
			break;
		case "loginp":
			requestDis = getServletContext().getRequestDispatcher("/login.jsp");
			requestDis.forward(request, response);
			break;
		case "about":
			requestDis = getServletContext().getRequestDispatcher("/about.jsp");
			requestDis.forward(request, response);
			break;
		case "registp":
			requestDis = getServletContext().getRequestDispatcher("/register.jsp");
			requestDis.forward(request, response);
			break;
		case "logout":
			request.getSession().invalidate();
			response.sendRedirect("loginp");
			break;
		default:
			System.out.println(url);
			requestDis = getServletContext().getRequestDispatcher("/error.jsp");
			requestDis.forward(request, response);
			break;
		}
		}else{
			response.sendRedirect("loginp");
		}

	}

}
