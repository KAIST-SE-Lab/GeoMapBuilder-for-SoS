package kr.ac.kaist.se.model.sos.cap;

import kr.ac.kaist.se.controller.util.MapKeyManager;
import kr.ac.kaist.se.data.SimLogEvent;
import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.obj._SimObject_;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.var.DimVar;

import java.util.ArrayList;

/**
 * A concrete class to represent an action for geographical movement.
 *
 * @author ymbaek
 */
public class MoveAction extends _SimAction_ {

    /* Attributes to execute a MoveAction */
    private ArrayList<DimVar> allowedDims;      //Allowed dimensions

    private ArrayList<String> allowedDimVarIds; //Ids of allowed dimensions
    private ArrayList<Integer> dimVarDiffList;  //Differences for updating dimVars

//    public MoveAction(SoS accessibleSoS,
//                      _SimObject_ actionSubject,
//                      String actionId,
//                      String actionName,
//                      ArrayList<DimVar> allowedDims,
//                      ArrayList<Integer> dimVarDiffList) {
//        super(accessibleSoS, actionSubject, actionId, actionName);
//        this.allowedDims = allowedDims;
//        this.dimVarDiffList = dimVarDiffList;
//    }

    public MoveAction(SoS accessibleSoS,
                      _SimObject_ actionSubject,
                      String actionId,
                      String actionName,
                      ArrayList<String> allowedDimVarIds,
                      ArrayList<Integer> dimVarDiffList) {
        super(accessibleSoS, actionSubject, actionId, actionName);
        this.allowedDimVarIds = allowedDimVarIds;
        this.dimVarDiffList = dimVarDiffList;
    }

    @Override
    public boolean checkPrecondition() {
        return true;
    }

    @Override
    public ArrayList<SimLogEvent> executeAction(int tick) {
        //Clear of the actionLogEvents to make new logEvents
        actionLogEvents.clear();

        //First, check the precondition of this action
        if (checkPrecondition()){
            MapKeyManager keyManager = new MapKeyManager(accessibleSoS.getSosMap().getMapLocInfo(),
                    accessibleSoS.getSosMap().getMapDimVars());


            String curLoc = actionSubject.getObjLocation();
//            System.out.println(curLoc);

            int index = 0;
//            ArrayList<Integer> targetDims = new ArrayList<>();

            //EX. (1,0,FLOOR_2) -> [DimVar(xPosVar:1), DimVar(yPosVar:0), DimVar(floorVar:FLOOR_2)]

//            ArrayList<DimVar> objLocDimVars = keyManager.getDimVarsFromKey(curLoc);
//            System.out.println(objLocDimVars);
//
//
//            boolean isMovable = true;
//
//            if (isMovable){
//
//            }

//            for (DimVar objLocDimVar : getDimValuesFromKey(actionSubject.getObjLocation()))

            return actionLogEvents;
        }else{
            return null;
        }

    }

    @Override
    public String generateLogEventSpec() {
        return null;
    }

    public void printMoveActionInfo(){
        System.out.print("[" + actionId + "] ");

        int allowedDimVarIndex = 0;
        for (String dimVarId : allowedDimVarIds){
            System.out.print(dimVarId + "(" + dimVarDiffList.get(allowedDimVarIndex) + ") ");
        }
        System.out.println();
    }
}
