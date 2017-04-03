package com.example.glproject.ws;

import com.example.glproject.DAO.AbstractDAOFactory;
import com.example.glproject.DAO.Factory;
import com.example.glproject.DAOImpl.TaskDAOImpl;
import com.example.glproject.businessobjects.Task;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/task")
public class TaskResource {
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addTask(Task task){
        ((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO()).add(task, "tasks",String.valueOf(task.getIdPlane()));
    }


}
