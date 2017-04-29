package com.michael;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.model.HibernateToDoListDao;
import com.tables.Tasks;
import com.tables.Users;
/**
 * Rest jersey controller for GET,UPDATE,CREATE AND DELETE Tasks
 */
@Path("gettable")
public class RestToDo {
	private HibernateToDoListDao query;
	/**
	 * Get all tasks of user from database and sending them to client
	 */
	@GET
	@Path("/get")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Tasks> getTable(@QueryParam("userName") String userName){
		query = new HibernateToDoListDao();
		List<Tasks> allTaskOfUser = new ArrayList<>();
		//get all tasks of the stated user  
		allTaskOfUser = query.getTasks(userName);
		for(Tasks task:allTaskOfUser){
			task.getUser().setItems(null);
		}
		try {
			return allTaskOfUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("erorr");
		return null;
	}
	/**
	 * Get from user parameters and create new task
	 * @return id id of new task
	 */
	@POST
	@Path("/new")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Integer addNewTask(@FormParam("task") String task,@FormParam("description") String description,@FormParam("date") String date,@Context HttpServletRequest request){
		query = new HibernateToDoListDao();
		Users user = (Users) request.getSession().getAttribute("user");	
		if(user!=null){
			Tasks newTask = new Tasks(user, task, date,description);
			query.addItem(newTask);
			//sending to user id of task 
			return new Integer(newTask.getId());
		}
		return null;	
	}
	/**
	 * Get from user parameters order to update exists task
	 */
	@POST
	@Path("/update")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void updateTask(@FormParam("taskid")String taskId,@FormParam("task") String task,@FormParam("description") String description,@FormParam("date") String date,@Context HttpServletRequest request){
		query = new HibernateToDoListDao();
		Users user = (Users) request.getSession().getAttribute("user");
		if (user != null) {
			Tasks taskForUpdate = (Tasks) query.getTask(Integer.parseInt(taskId));
			taskForUpdate.setTask(task);
			taskForUpdate.setDescription(description);
			taskForUpdate.setDate(date);
			user.getItems().add(taskForUpdate);
			query.updateItem(taskForUpdate);
		}
	}
	/**
	 * Get from user id of task that need delete
	 */
	@POST
	@Path("/delete")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void deleteTask(@FormParam("taskid")String taskId,@Context HttpServletRequest request){
		query = new HibernateToDoListDao();
		Users user = (Users) request.getSession().getAttribute("user");	
		if(user!=null){
			int id = Integer.parseInt(taskId);
			query.deleteTask(id);
		}
		
	}
}
