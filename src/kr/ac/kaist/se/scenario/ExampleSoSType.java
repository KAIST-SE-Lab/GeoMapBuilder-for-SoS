package kr.ac.kaist.se.scenario;

import kr.ac.kaist.se.data.SimLogEvent;
import kr.ac.kaist.se.model.sos.Entity;
import kr.ac.kaist.se.model.sos.ObjectLocation;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.SoSMap;

import java.util.ArrayList;

/**
 * A concrete class of an example SoS that includes one or more ExampleObjects
 * @author ymbaek
 */
public class ExampleSoSType extends SoS {

    public ExampleSoSType(String id,
                          String name,
                          ObjectLocation objLocation,
                          SoSMap sosMap,
                          ArrayList<Entity> memberEntityList) {
        super(id, name, objLocation, sosMap, memberEntityList);
    }

    @Override
    protected void initSoSModel() {

    }

    @Override
    protected void initMap() {

    }

}
