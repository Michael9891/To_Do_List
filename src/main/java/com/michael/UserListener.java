package com.michael;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 *Listener to management session of users
 */
public class UserListener implements HttpSessionListener,HttpSessionAttributeListener {
	private ServletContext context = null;
	static int totalUserCount = 0,currentUserCount = 0,totalAttributeAddedCount = 0,totalAttributeRemovedCount = 0,totalAttributeReplacedCount = 0;
/**
 * Count number of users
 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		totalUserCount++;
		currentUserCount++;
		context = event.getSession().getServletContext();
		context.setAttribute("totalUserCount", totalUserCount);
		context.setAttribute("currentUserCount", currentUserCount);
	}
	/**
	 * Count number of users that visit now on site
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		currentUserCount--;
		context = event.getSession().getServletContext();
		context.setAttribute("currentUserCount", currentUserCount);
		
	}
	/**
	 * Count session attributes added
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		totalAttributeAddedCount++;
		context = event.getSession().getServletContext();
		context.setAttribute("totalAttributeAddedCount", totalAttributeAddedCount);
	}
	/**
	 * Count session attributes removed
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		totalAttributeRemovedCount++;
		context = event.getSession().getServletContext();
		context.setAttribute("totalAttributeRemovedCount", totalAttributeRemovedCount);
	}
	/**
	 * Count session attributes replaced
	 */
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		totalAttributeReplacedCount++;
		context = event.getSession().getServletContext();
		context.setAttribute("totalAttributeReplacedCount", totalAttributeReplacedCount);
	}

}
