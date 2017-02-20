package DAO;

import DAOImplement.Task;

import java.util.List;

/**
 * Created by info on 20/02/17.
 */
public interface TaskDao {
    boolean add(Task task);
    boolean update(Task task);
    boolean delete(int id);
    List<Task> getByMROId(int MRO_id);//Returns a list Task by MRO_id
    Task get(int id);//Return task corresponding the id of the task
    Task getByPlaneId(int Plane_id);//Return detail of task corresponding the id of the plane
}
