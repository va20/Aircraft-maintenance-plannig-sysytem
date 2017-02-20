package DAOImplement;

/**
 * Created by info on 20/02/17.
 */
public class GenericTask{
    private String reference;
    private String access;
    private String type;
    private String equipment;

    public GenericTask(String reference,String type,String access,String equipment){
        this.access=access;
        this.equipment=equipment;
        this.reference=reference;
        this.type=type;
    }

    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public String getAccess() {
        return access;
    }
    public void setAccess(String access) {
        this.access = access;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getEquipment() {
        return equipment;
    }
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
