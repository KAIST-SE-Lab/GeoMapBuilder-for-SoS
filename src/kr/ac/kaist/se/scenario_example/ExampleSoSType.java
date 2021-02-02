package kr.ac.kaist.se.scenario_example;

import kr.ac.kaist.se.model.sos.Entity;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.SoSMap;

import java.util.ArrayList;

/**
 * A concrete class of an example SoS that includes one or more ExampleObjects
 *
 * @author ymbaek
 */
public class ExampleSoSType extends SoS {

    public ExampleSoSType(String id,
                          String name,
                          String objLocation,
                          SoSMap sosMap,
                          ArrayList<Entity> memberEntityList) {
        super(id, name, objLocation, sosMap, memberEntityList);
    }

    @Override
    protected void initSoSModel() {
        //Declaration and instantiation of simobjects
        ExampleObjectType obj01 = new ExampleObjectType(this,
                "OBJ_01",
                "OBJ_01");


        ExampleObjectType obj02 = new ExampleObjectType(this,
                "OBJ_02",
                "OBJ_02");


        addMemberEntity(obj01);
        addMemberEntity(obj02);
    }

    @Override
    protected void initMap() {

    }

    @Override
    protected void initObjLocation() {

    }
}
