package controllers;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.TodoDAO;
import entities.Todo;

@RestController
public class TodoControllerImpl implements TodoControllerI {

	@Autowired
	private TodoDAO todoDAO;

	// GET ping
	@RequestMapping(path = "ping", method = RequestMethod.GET)
	public String ping() {
		return "pong";
	}

	// GET /user/{uid}/todo
	@Override
	@RequestMapping(path = "user/{uid}/todo", method = RequestMethod.GET)
	public Collection<Todo> index(HttpServletRequest request, HttpServletResponse response, @PathVariable int uid) {
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
		return todoDAO.index(uid);
	}

	// GET /user/{uid}/todo/{tid}
	@Override
	@RequestMapping(path = "user/{uid}/todo/{tid}", method = RequestMethod.GET)
	public Todo show(HttpServletRequest request, HttpServletResponse response, @PathVariable int uid, @PathVariable int tid) {
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
		Todo result = todoDAO.show(uid, tid);
		if (result == null){
			response.setStatus(404);
		};
		return result;
	}

	// POST /user/{uid}/todo
	@Override
	@RequestMapping(path = "user/{uid}/todo", method = RequestMethod.POST)
	public Todo create(HttpServletRequest request, HttpServletResponse response, @PathVariable int uid,
			@RequestBody String todoJson) {
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
		return todoDAO.create(uid, todoJson);
	}

	// PUT /user/{uid}/todo/{tid}
	@Override
	@RequestMapping(path = "user/{uid}/todo/{tid}", method = RequestMethod.PUT)
	public Todo update(HttpServletRequest request, HttpServletResponse response, @PathVariable int uid, @PathVariable int tid, @RequestBody String todoJson) {
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
		return todoDAO.update(uid, tid, todoJson);

	}

//  DELETE /user/{uid}/todo/{tid}
	@Override
	@RequestMapping(path = "user/{uid}/todo/{tid}", method = RequestMethod.DELETE)
	public Boolean destroy(HttpServletRequest request, HttpServletResponse response,@PathVariable int uid,@PathVariable int tid) {
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
		return todoDAO.destroy(uid, tid);
	}

}
