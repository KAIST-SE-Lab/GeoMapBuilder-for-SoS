package kr.ac.kaist.se.scenario_example;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.sos.Entity;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.cap.MoveAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * A concrete class of an example object
 * @author ymbaek
 */
public class ExampleObjectType extends Entity {

    public ExampleObjectType(SoS mySoS, String id, String name) {
        super(mySoS, id, name);
    }

    public ExampleObjectType(SoS mySoS, String id, String name, String objLocation) {
        super(mySoS, id, name, objLocation);
    }

    @Override
    protected void initObjLocation() {
        //Note that this should be implemented according to a scenario
        objLocation = "0,0,FLOOR_1";
    }

    @Override
    protected void initCapableActions() {
        MoveAction xPosMovePlusOne = new MoveAction(this.mySoS,
                this,
                "xPosMovePlusOne",
                "xPosMovePlusOne",
                new ArrayList<>(Arrays.asList("xPosVar")),
                new ArrayList<>(Arrays.asList(1)));

        MoveAction yPosMovePlusOne = new MoveAction(this.mySoS,
                this,
                "yPosMovePlusOne",
                "yPosMovePlusOne",
                new ArrayList<>(Arrays.asList("yPosVar")),
                new ArrayList<>(Arrays.asList(1)));

        MoveAction xyPosMovePlusOne = new MoveAction(this.mySoS,
                this,
                "xyPosMovePlusOne",
                "xyPosMovePlusOne",
                new ArrayList<>(Arrays.asList("xPosVar", "yPosVar")),
                new ArrayList<>(Arrays.asList(1, 1)));

        MoveAction xPosMoveMinusOne = new MoveAction(this.mySoS,
                this,
                "xPosMoveMinusOne",
                "xPosMoveMinusOne",
                new ArrayList<>(Arrays.asList("xPosVar")),
                new ArrayList<>(Arrays.asList(-1)));

        MoveAction yPosMoveMinusOne = new MoveAction(this.mySoS,
                this,
                "yPosMoveMinusOne",
                "yPosMoveMinusOne",
                new ArrayList<>(Arrays.asList("yPosVar")),
                new ArrayList<>(Arrays.asList(-1)));

        MoveAction xyPosMoveMinusOne = new MoveAction(this.mySoS,
                this,
                "xyPosMoveMinusOne",
                "xyPosMoveMinusOne",
                new ArrayList<>(Arrays.asList("xPosVar", "yPosVar")),
                new ArrayList<>(Arrays.asList(-1, -1)));

//        MoveAction yPosMovePlusOne = new MoveAction(this.mySoS,
//                this,
//                "yPosMovePlusOne",
//                "yPosMovePlusOne",
//                new ArrayList<>(Arrays.asList("yPosVar")),
//                new ArrayList<>(Arrays.asList(1)));

        capableActionList.add(xPosMovePlusOne);
        capableActionList.add(yPosMovePlusOne);
        capableActionList.add(xyPosMovePlusOne);
        capableActionList.add(xPosMoveMinusOne);
        capableActionList.add(yPosMoveMinusOne);
        capableActionList.add(xyPosMoveMinusOne);

    }



    @Override
    protected ArrayList<_SimAction_> selectMoveActions(ArrayList<_SimAction_> possibleMoveActions) {

        //A list to store selected move actions
        ArrayList<_SimAction_> selectedMoveActions = new ArrayList<>();

        /* Current strategy to select move actions: Random */

        //Depending on a strategy selected,
        //MoveActions can be differently selected according to a specific criterion.

        if (possibleMoveActions != null && possibleMoveActions.size() > 0) {
            Random rand = new Random();
            int selectedMoveActionIndex = rand.nextInt(possibleMoveActions.size());

            selectedMoveActions.add(possibleMoveActions.get(selectedMoveActionIndex));
        }

        if (selectedMoveActions.size() != 0) {
            return selectedMoveActions;
        } else {
            return null;
        }
    }
}
