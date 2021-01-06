package kr.ac.kaist.se.model.abst.obj;

import kr.ac.kaist.se.model.intf.Simulatable;
import kr.ac.kaist.se.model.sos.ObjectLocation;

import java.sql.Timestamp;

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
}
