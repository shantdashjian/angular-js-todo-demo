package test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Todo;
import entities.User;

public class TodoTest {

	private EntityManagerFactory entityManagerFactory = null;
	private EntityManager entityManager = null;
	private Todo todo = null;

	@Before
	public void setUp() throws Exception {
		// This is the name of the persistence unit (not the entity)
		entityManagerFactory = Persistence.createEntityManagerFactory("Todo");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@After
	public void tearDown() throws Exception {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test_todo_mappings() {
		todo = entityManager.find(Todo.class, 1);    

	    assertThat(todo,
	        allOf(
	        	hasProperty("id", is(1)),	           
	        	hasProperty("task", is("Read UNIX")),	           
	        	hasProperty("description", is("Read this book")),          
	        	hasProperty("completed", is(false)),          
	        	hasProperty("dueDate", is(nullValue())),          
	        	hasProperty("completedDate", is(nullValue())),
	        	hasProperty("priority", is(1))        
	      )  
	     );
	}
	
	@Test
	public void test_todo_temporal() {
		todo = entityManager.find(Todo.class, 1);    
		assertEquals("2017-04-26 00:00:00.0", todo.getCreatedAt().toString());
		assertEquals("2017-04-26 00:00:00.0", todo.getUpdatedAt().toString());
	}
	
	@Test
	public void test_user_association() {
	     todo = entityManager.find(Todo.class, 1);
	     User user = todo.getUser();
	     assertEquals(1, user.getId());
	     assertEquals("shaundashjian@gmail.com", user.getEmail());
	     assertEquals("password", user.getPassword());
	     
	  }
	
	
	
}

