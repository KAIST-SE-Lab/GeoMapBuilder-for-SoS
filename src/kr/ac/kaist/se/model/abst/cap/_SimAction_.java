package kr.ac.kaist.se.model.abst.cap;

import kr.ac.kaist.se.data.SimLogEvent;
import kr.ac.kaist.se.model.abst.obj._SimObject_;
import kr.ac.kaist.se.model.sos.SoS;

import java.util.ArrayList;

/**
 * Abstract class to represent an action object
 *
 * In SIMVA-SoS Lite, _SimAction_ can be specialized into FuncAction, MoveAction, CommAction,
 * but this project only implements MoveAction class to update ObjectLocation of a _SimObject_.
 * Note that this implementation is independent from the implementation of SIMVA-SoS Lite
 *
 * @author ymbaek
 */
public abstract class _SimAction_ {

    protected SoS accessibleSoS;    //Accessible SimModel (SoS)
    protected _SimObject_ actionSubject;  //Subject who performs this action

    protected String actionId;      //id of action
    protected String actionName;    //name of action

    // ArrayList to store SimLogEvents of executed actions for return
    protected ArrayList<SimLogEvent> actionLogEvents = new ArrayList<>();

    public _SimAction_(SoS accessibleSoS,
                       _SimObject_ actionSubject,
                       String actionId,
                       String actionName) {
        this.accessibleSoS = accessibleSoS;
        this.actionSubject = actionSubject;
        this.actionId = actionId;
        this.actionName = actionName;
    }

    /**
     * A method to check precondition of this action
     *
     * @return true if this action is executable
     */
    public abstract boolean checkPrecondition();

    /**
     * A method to actually execute this action
     * The original version of this method returns ArrayList of SimLogEvent to generate logs
     *
     * @param tick current tick of simulation
     */
    public abstract ArrayList<SimLogEvent> executeAction(int tick);

    /**
     * A method to generate event specification for SimEventLog
     *
     * @return Generated String-type log event specification
     */
    public abstract String generateLogEventSpec();



    /* Getters & Setters */

    public SoS getAccessibleSoS() {
        return accessibleSoS;
    }

    public void setAccessibleSoS(SoS accessibleSoS) {
        this.accessibleSoS = accessibleSoS;
    }

    public _SimObject_ getActionSubject() {
        return actionSubject;
    }

    public void setActionSubject(_SimObject_ actionSubject) {
        this.actionSubject = actionSubject;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
}
