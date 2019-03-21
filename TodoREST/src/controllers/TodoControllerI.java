package controllers;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Todo;

public interface TodoControllerI {
//    GET /user/{uid}/todo
    public Collection<Todo> index(HttpServletRequest req, HttpServletResponse res, int uid);
    
//    GET /user/{uid}/todo/{tid}
    public Todo show(HttpServletRequest req, HttpServletResponse res, int uid, int tid);
    
//    POST /user/{uid}/todo
    public Todo create(HttpServletRequest req, HttpServletResponse res, int uid, String todoJson);
    
//    PUT /user/{uid}/todo/{tid}
    public Todo update(HttpServletRequest req, HttpServletResponse res, int uid, int tid, String todoJson);
    
//    DELETE /user/{uid}/todo/{tid}
    public Boolean destroy(HttpServletRequest req, HttpServletResponse res, int uid,int tid);
}