package DAO;

import DAOImplement.GenericTask;

import java.util.List;

/**
 * Created by info on 20/02/17.
 */
public interface GenericTaskDao {
    boolean add(GenericTask g_task,String plane_type);
    boolean update(GenericTask g_task);
    boolean delete(String reference);
    List<GenericTask> getByType(int type);//Returns a list GenericTask by Plane_type
    GenericTask get(String reference);//Return generictask corresponding the reference
}
