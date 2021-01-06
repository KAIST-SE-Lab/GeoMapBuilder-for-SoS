package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.data.SimLogEvent;
import kr.ac.kaist.se.model.abst.obj._SimObject_;

import java.util.ArrayList;

/**
 * Abstract class to represent an object who can move around SoSMap
 * (note: independent from SIMVA-SoS Lite)
 * @author ymbaek, ehcho, yjshin
 */
public abstract class Entity extends _SimObject_ {

    //SoS that this object belongs to
    protected SoS mySoS;

    public Entity(SoS mySoS,
                  String id,
                  String name,
                  ObjectLocation objLocation) {
        super(id, name, objLocation);
        this.mySoS = mySoS;

        //Initialization of object location
        initObjLocation();
    }

    /*
        Note that if you implement run(..) method here,
        all the instantiated Entity objects have same run(..) methods.
        (i.e., All entities have same capabilities and behaviors.)
         */
    @Override
    public ArrayList<SimLogEvent> run(int tick) {
        //TODO: implement run(..) method
        return null;
    }

    /**
     * Initialization of object location
     */
    protected abstract void initObjLocation();

}
