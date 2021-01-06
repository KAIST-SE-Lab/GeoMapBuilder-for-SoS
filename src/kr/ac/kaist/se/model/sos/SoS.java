package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.data.SimLogEvent;
import kr.ac.kaist.se.model.abst.obj._SimObject_;

import java.util.ArrayList;

/**
 * Abstract class to represent a System-of-Systems (SoS)
 * (note: independent from SIMVA-SoS Lite)
 * @author ymbaek, ehcho, yjshin
 */
public abstract class SoS extends _SimObject_ {

    /** Map */
    public SoSMap sosMap;

    /** Constituent (abstract version) */
    protected ArrayList<Entity> memberEntityList;

    public SoS(String id,
               String name,
               ObjectLocation objLocation,
               SoSMap sosMap,
               ArrayList<Entity> memberEntityList) {
        super(id, name, objLocation);
        this.sosMap = sosMap;
        this.memberEntityList = memberEntityList;

        initSoSModel();
        initMap();
    }

    /**
     * Abstract method for the initialization of simulation models of an SoS.
     * This method should be implemented to build a complete SoS model.
     */
    protected abstract void initSoSModel();

    /**
     * Abstract method for the initialization of a simulation map of an SoS
     */
    protected abstract void initMap();


    @Override
    public ArrayList<SimLogEvent> run(int tick) {

        ArrayList<SimLogEvent> logEventList = new ArrayList<>();

        //Simply run member entities
        for(Entity aEntity : memberEntityList){
            logEventList.addAll(aEntity.run(tick));
        }

        return logEventList;
    }

    /* Getters & Setters */

    public SoSMap getSosMap() {
        return sosMap;
    }

    public void setSosMap(SoSMap sosMap) {
        this.sosMap = sosMap;
    }

    public ArrayList<Entity> getMemberEntityList() {
        return memberEntityList;
    }

    public void addMemberEntity(Entity aEntity){
        if (aEntity != null){
            this.memberEntityList.add(aEntity);
        }
    }

    public void setMemberEntityList(ArrayList<Entity> memberEntityList) {
        this.memberEntityList = memberEntityList;
    }
}
