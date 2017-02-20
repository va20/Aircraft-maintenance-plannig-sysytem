package DAOImplement;

/**
 * Created by info on 20/02/17.
 */
public class Plane {
    private int id;
    private String type;
    private String MPD;

    public Plane(int id,String type,String mpd){
        this.id=id;
        this.type=type;
        this.MPD=mpd;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMPD() {
        return MPD;
    }
    public void setMPD(String mPD) {
        MPD = mPD;
    }
}
