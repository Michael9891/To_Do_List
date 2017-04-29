package com.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.exception.ToDoDaoException;
import com.tables.Tasks;
import com.tables.Users;
/**
 * Hibernate data base queries.
 * @param factory -  client requests obtain Sessions from the factory.
 */
public class HibernateToDoListDao implements ITodoListDao {

	private SessionFactory factory;
	private Session session;

	public HibernateToDoListDao() {
		factory = Factory.getFactory();
	}
	/**
	 * Create new Item in data base (Users or Tasks)
	 */
	@Override
	public void addItem(Object item) {
		try {
			session = factory.openSession();
			session.beginTransaction();
			session.save(item);
			session.getTransaction().commit();
		} catch (ToDoDaoException e) {
			Transaction tx = session.getTransaction();
			if (tx.isActive())
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}

	/**
	 * Delete Item in data base (Users or Tasks)
	 */
	@Override
	public void deleteItem(Object item) {
		try {
			session = factory.openSession();
			session.beginTransaction();
			session.delete(item);
			session.getTransaction().commit();
		} catch (ToDoDaoException e) {
			Transaction tx = session.getTransaction();
			if (tx.isActive())
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}

	/**
	 * Update Item in data base (Users or Tasks)
	 */
	@Override
	public void updateItem(Object item) {
		try {
			session = factory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(item);
			session.getTransaction().commit();
		} catch (ToDoDaoException e) {
			Transaction tx = session.getTransaction();
			if (tx.isActive())
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}

	/**
	 * @return User from data base by name and password
	 */
	@Override
	public Users getUser(String name, String password) {
		try {
			session = factory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Users where userName='" + name + "' and " + "password='" + password + "'");
			Users user = (Users) query.uniqueResult();
			session.getTransaction().commit();
			return user;
		} catch (ToDoDaoException e) {
			Transaction tx = session.getTransaction();
			if (tx.isActive())
				tx.rollback();
			return null;
		} finally {
			if (session != null)
				session.close();
		}
	}

	/**
	 * @return All tasks of specific user
	 */
	@Override
	public List<Tasks> getTasks(String user) {
		try {
			session = factory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Tasks where user = ?");
			query.setString(0, user);
			@SuppressWarnings("unchecked")
			List<Tasks> task = query.list();
			session.getTransaction().commit();
			return (List<Tasks>) task;
		} catch (ToDoDaoException e) {
			Transaction tx = session.getTransaction();
			if (tx.isActive())
				tx.rollback();
			return null;
		} finally {
			if (session != null)
				session.close();
		}
	}

	/**
	 * @return Task by id of Task
	 */
	@Override
	public Object getTask(Object id) {
		try {
			session = factory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Tasks where id = ?");
			query.setInteger(0, (int) id);
			Tasks task = (Tasks) query.uniqueResult();
			session.getTransaction().commit();
			return task;
		} catch (ToDoDaoException e) {
			Transaction tx = session.getTransaction();
			if (tx.isActive())
				tx.rollback();
			return null;
		} finally {
			if (session != null)
				session.close();
		}
	}

	/**
	 * Delete Task by id
	 */
	@Override
	public void deleteTask(int idOfTask) {
		try {
			session = factory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("DELETE from Tasks where id = ?");
			query.setInteger(0, new Integer(idOfTask));
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (ToDoDaoException e) {
			Transaction tx = session.getTransaction();
			if (tx.isActive())
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public Users getUser(String userName) {
		try {
			session = factory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Users where userName = ?");
			query.setString(0, userName);
			Users user = (Users) query.uniqueResult();
			session.getTransaction().commit();
			return user;
		} catch (ToDoDaoException e) {
			Transaction tx = session.getTransaction();
			if (tx.isActive())
				tx.rollback();
			return null;
		} finally {
			if (session != null)
				session.close();
		}
		
	}

}
