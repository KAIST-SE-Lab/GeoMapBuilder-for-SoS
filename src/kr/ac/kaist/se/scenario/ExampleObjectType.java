package kr.ac.kaist.se.scenario;

import kr.ac.kaist.se.data.SimLogEvent;
import kr.ac.kaist.se.model.sos.Entity;
import kr.ac.kaist.se.model.sos.ObjectLocation;
import kr.ac.kaist.se.model.sos.SoS;

import java.util.ArrayList;

/**
 * A concrete class of an example object
 * @author ymbaek
 */
public class ExampleObjectType extends Entity {

    public ExampleObjectType(SoS mySoS,
                             String id,
                             String name,
                             ObjectLocation objLocation) {
        super(mySoS, id, name, objLocation);
    }


    @Override
    protected void initObjLocation() {

    }
}
