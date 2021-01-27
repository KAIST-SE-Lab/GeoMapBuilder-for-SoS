package kr.ac.kaist.se;

import kr.ac.kaist.se.controller.sim.SimEngine;
import kr.ac.kaist.se.model.sos.Entity;
import kr.ac.kaist.se.scenario_example.*;
import kr.ac.kaist.se.scenario_example.old.ExampleSoSMap;

import java.util.ArrayList;

/**
 * Main of this project (GeoMapBuilder-for-SoS) plays a role as a simulation engine.
 * In other words, Main of this project manages overall simulation time and triggers run() of SimObjects.
 * Note that this engine is independent from SIMVA-SoS Lite.
 */
public class Main {

    public static void main (String []args){

        /* Initialization of a map */
//        ExampleSoSMap sosMap = new ExampleSoSMap("SOSMAP01",
//                "EXAMPLE_SOS_MAP", "ExampleSoSMapInit.txt");

        MapType_01_Line mapObject01Line = new MapType_01_Line("SOSMAP01",
                "EXAMPLE_SOS_MAP_01", "ExampleSoSMapInit01.txt");

        MapType_02_Cycle mapObject02Cycle = new MapType_02_Cycle("SOSMAP02",
                "EXAMPLE_SOS_MAP_02", "ExampleSoSMapInit02.txt");

        MapType_03_Plane mapObject03Plane = new MapType_03_Plane("SOSMAP03",
                "EXAMPLE_SOS_MAP_03", "ExampleSoSMapInit03.txt");

        MapType_04_Cuboid mapObject04Cuboid = new MapType_04_Cuboid("SOSMAP04",
                "EXAMPLE_SOS_MAP_04", "ExampleSoSMapInit04.txt");

        MapType_05_Cuboid_Extended mapObject05CuboidExtended = new MapType_05_Cuboid_Extended("SOSMAP05",
                "EXAMPLE_SOS_MAP_05", "ExampleSoSMapInit05.txt");



        /* Initialization of a sim model */
        //Declaration and instantiation of an SoS object
        ExampleSoSType sos = new ExampleSoSType("SOS01",
                "EXAMPLE_SOS",
                "",
                mapObject05CuboidExtended,
                new ArrayList<Entity>());


        SimEngine simEngine = new SimEngine(sos);
        simEngine.startSimulation();
    }



}
