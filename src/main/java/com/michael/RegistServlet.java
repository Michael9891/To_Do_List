package com.michael;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.HibernateToDoListDao;
import com.tables.Users;
/**
 * Authorization servlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HibernateToDoListDao query;
	Users user;
   
	public RegistServlet() {
        super();
        query = new HibernateToDoListDao();
    }
	@Override
	public void init() throws ServletException {
		super.init();
	}
	/**
	 * Get parameters from client, if they valid create new user else back to registration page
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("password1");
		String userName = request.getParameter("name");
		//Check if parameters not empty and username not exists
		user = query.getUser(userName);
		if((userName.equals("")||password.equals("")||confirmPassword.equals("")||!password.equals(confirmPassword))|| user!=null){
		//show to user error
			String error = "Invalid username or password";
			request.setAttribute("error", error);
			RequestDispatcher requestDis = getServletContext().getRequestDispatcher("/register.jsp");
			requestDis.forward(request, response);
		}
		else{
	    //Create new user
		user = new Users(userName, password);
		query.addItem(user);
		RequestDispatcher requestDis = getServletContext().getRequestDispatcher("/login.jsp");
		requestDis.forward(request, response);
		}
	}

}
