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
    protected String objLocation;

    public _SimObject_(String id, String name) {
        this.id = id;
        this.name = name;

        objLocation = null;
    }

    public _SimObject_(String id, String name, String objLocation) {
        this.id = id;
        this.name = name;
        this.objLocation = objLocation;
    }

    /**
     * Initialization of object location
     */
    protected abstract void initObjLocation();


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

    public String getObjLocation() {
        return objLocation;
    }

    public void setObjLocation(String objLocation) {
        this.objLocation = objLocation;
    }
}
