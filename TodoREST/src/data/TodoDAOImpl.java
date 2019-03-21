package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Todo;
import entities.User;

@Transactional
public class TodoDAOImpl implements TodoDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Todo> index(int uid) {
		String query = "SELECT todo from Todo todo where todo.user.id = :id";
		return em.createQuery(query, Todo.class).setParameter("id", uid).getResultList();
	}

	@Override
	public Todo show(int uid, int tid) {
		return em.find(Todo.class, tid);

	}

	@Override
	public Todo create(int uid, String todoJson) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Todo mappedTodo = mapper.readValue(todoJson, Todo.class);
			User user = em.find(User.class, uid);
			
			mappedTodo.setUser(user);

			em.persist(mappedTodo);
			em.flush();
			return mappedTodo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Todo update(int uid, int tid, String todoJson) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Todo todo = mapper.readValue(todoJson, Todo.class);
			Todo mappedTodo = em.find(Todo.class, tid);
			mappedTodo.setTask(todo.getTask());
			mappedTodo.setDescription(todo.getDescription());
			mappedTodo.setCompleted(todo.isCompleted());
			mappedTodo.setDueDate(todo.getDueDate());
			mappedTodo.setCompletedDate(todo.getCompletedDate());
			mappedTodo.setCreatedAt(todo.getCreatedAt());
			mappedTodo.setUpdatedAt(todo.getUpdatedAt());
			mappedTodo.setUser(em.find(User.class, uid));			
			mappedTodo.setPriority(todo.getPriority());
			
			return mappedTodo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Boolean destroy(int uid, int tid) {
		boolean flag = false;
		try {			
			em.remove(em.find(Todo.class, tid));
			flag = true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return flag;
	}

}
