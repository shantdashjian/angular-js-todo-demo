package test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

//import java.util.ArrayList;
//import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.User;

public class UserTest {

	private EntityManagerFactory entityManagerFactory = null;
	private EntityManager entityManager = null;
	private User user = null;

	@Before
	public void setUp() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("Todo");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@After
	public void tearDown() throws Exception {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Test
	public void test_user_mappings() {
		user = entityManager.find(User.class, 1);    

	    assertThat(user,
	        allOf(
	        	hasProperty("id", is(1)),	           
	        	hasProperty("email", is("shaundashjian@gmail.com")),	           
	        	hasProperty("password", is("password"))          
	      )  
	     );
	}
	
	@Test
	public void test_todos_association() {
	     user = entityManager.find(User.class, 1);
//	     List<Todo> todos = new ArrayList<Todo>(user.getTodos());
	     assertEquals(3, user.getTodos().size());
//	     assertEquals("Read UNIX", todos.get(0).getTask());
	     
	  }
}
