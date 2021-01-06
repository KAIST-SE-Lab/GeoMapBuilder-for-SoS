package kr.ac.kaist.se.model.abst.obj;

import kr.ac.kaist.se.model.intf.Simulatable;
import kr.ac.kaist.se.model.sos.ObjectLocation;

import java.sql.Timestamp;

/**
 * Abstract class for general simulation objects
 * (note: independent from SIMVA-SoS Lite)
 * @author ymabek
 */
public abstract class _SimObject_ implements Simulatable {

    protected Timestamp timestamp;    //Timestamp for stdout

    /** Basic information of a SimObject */
    //Universally unique object id (Every SimObject should have its own unique id)
    protected String id;
    //Name of an object
    protected String name;

    /** Geo-location information */
    //Current location of this object
    protected ObjectLocation objLocation;

    public _SimObject_(String id, String name, ObjectLocation objLocation) {
        this.id = id;
        this.name = name;
        this.objLocation = objLocation;
    }


    /* Getters & Setters */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectLocation getObjLocation() {
        return objLocation;
    }

    public void setObjLocation(ObjectLocation objLocation) {
        this.objLocation = objLocation;
    }
}
