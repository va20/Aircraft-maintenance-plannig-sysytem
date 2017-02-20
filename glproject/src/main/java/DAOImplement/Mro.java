package DAOImplement;

import java.util.List;

/**
 * Created by info on 20/02/17.
 */
public class Mro {
    private int id;
    private List<Task> list_task;

    public Mro(int id,List<Task> list_task){
        this.id=id;
        this.list_task=list_task;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<Task> getList_task() {
        return list_task;
    }
    public void setList_task(List<Task> list_task) {
        this.list_task = list_task;
    }
}
