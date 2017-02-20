package DAO;


import DAOImplement.Plane;


import java.util.List;

/**
 * Created by info on 20/02/17.
 */
public interface PlaneDao {
    boolean add(Plane plane);
    boolean update(int id);
    boolean delete(int id);
    List<Plane> getAllPlanes();
    //List<Plane> ByDay(Date date);
}
