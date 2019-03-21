package data;

import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Todo;
import entities.User;

@Transactional
@Repository
public class AuthDAOImpl implements AuthDAO {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(User user) {
		String passwordSha = encoder.encode(user.getPassword());
		user.setPassword(passwordSha);
		em.persist(user);
		em.flush();
		return user;
	}

	@Override
	public User login(User user) {
		User managedUser = null;
		try {
			String q = "SELECT u FROM User u JOIN FETCH u.todos WHERE u.email = :email";
			managedUser = em.createQuery(q, User.class).setParameter("email", user.getEmail()).getSingleResult();
			if (encoder.matches(user.getPassword(), managedUser.getPassword())) {
				return managedUser;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			String q = "SELECT u FROM User u WHERE u.email = :email";
			managedUser = em.createQuery(q, User.class).setParameter("email", user.getEmail()).getSingleResult();
			managedUser.setTodos(new HashSet<Todo>());
			if (encoder.matches(user.getPassword(), managedUser.getPassword())) {
				return managedUser;
			} else {
				return null;
			}
		}

	}
}
