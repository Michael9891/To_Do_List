package com.michael;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.HibernateToDoListDao;
import com.tables.Users;



/**
 * Authentication servlet
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HibernateToDoListDao query;
    public Login() {
        super();
        query = new HibernateToDoListDao();
    }
/**
 * Check if username and password exists in system and sending cookies to client
 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users user = query.getUser(request.getParameter("username"), request.getParameter("password"));
		ScalaBonus scalaClassObject = new ScalaBonus();
		Integer a = 5;
		//Scala double the number a 
		Integer scala = scalaClassObject.addInt(a);
		request.getSession().setAttribute("scala", scala);
		if(user!=null&&!user.equals("")){
			//add user to session 
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("userName", user.getUserName());
			//create cookie for present in login page
			Cookie cookie = new Cookie("userCookie", user.getUserName());
			response.addCookie(cookie);
			response.sendRedirect("homep");

		}else{
		RequestDispatcher requestDis = getServletContext().getRequestDispatcher("/login.jsp");
		requestDis.forward(request, response);
		}

	}

}
