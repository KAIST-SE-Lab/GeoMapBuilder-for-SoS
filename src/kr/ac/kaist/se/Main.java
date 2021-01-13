package kr.ac.kaist.se;

import kr.ac.kaist.se.controller.sim.SimEngine;
import kr.ac.kaist.se.model.sos.Entity;
import kr.ac.kaist.se.model.sos.ObjectLocation;
import kr.ac.kaist.se.model.sos.var.DataVar;
import kr.ac.kaist.se.model.sos.var.DimVar;
import kr.ac.kaist.se.scenario.ExampleObjectType;
import kr.ac.kaist.se.scenario.ExampleSoSMap;
import kr.ac.kaist.se.scenario.ExampleSoSType;

import java.util.ArrayList;

/**
 * Main of this project (GeoMapBuilder-for-SoS) plays a role as a simulation engine.
 * In other words, Main of this project manages overall simulation time and triggers run() of SimObjects.
 * Note that this engine is independent from SIMVA-SoS Lite.
 */
public class Main {

    public static void main (String []args){

        /* Initialization of a map */
        ExampleSoSMap sosMap = new ExampleSoSMap("SOSMAP01",
                "EXAMPLE_SOS_MAP", "ExampleSoSMapInit.txt");

        /* Initialization of a sim model */
        //Declaration and instantiation of an SoS object
        ExampleSoSType sos = new ExampleSoSType("SOS01",
                "EXAMPLE_SOS",
                "",
                sosMap,
                new ArrayList<Entity>());


        SimEngine simEngine = new SimEngine(sos);
        simEngine.startSimulation();
    }



}
