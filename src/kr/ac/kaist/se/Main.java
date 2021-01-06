package kr.ac.kaist.se;

import kr.ac.kaist.se.controller.sim.SimEngine;
import kr.ac.kaist.se.scenario.ExampleSoSType;

/**
 * Main of this project (GeoMapBuilder-for-SoS) plays a role as a simulation engine.
 * In other words, Main of this project manages overall simulation time and triggers run() of SimObjects.
 * Note that this engine is independent from SIMVA-SoS Lite.
 */
public class Main {

    public static void main (String []args){
        ExampleSoSType sos = new ExampleSoSType();

        SimEngine simEngine = new SimEngine(sos);
        simEngine.startSimulation();
    }

}
