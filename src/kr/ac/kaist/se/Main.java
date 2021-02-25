package kr.ac.kaist.se;

import kr.ac.kaist.se.model.sos.Entity;
import kr.ac.kaist.se.scenario_example.*;

import java.util.ArrayList;

/**
 * Main of this project (GeoMapBuilder-for-SoS) plays a role as a simulation engine.
 * In other words, Main of this project manages overall simulation time and triggers run() of SimObjects.
 * Note that this engine is independent from SIMVA-SoS Lite.
 */
public class Main {

    public static void main(String[] args) {

        /* Initialization of a map */
//        ExampleSoSMap sosMap = new ExampleSoSMap("SOSMAP01",
//                "EXAMPLE_SOS_MAP", "ExampleSoSMapInit.txt");

        //Map_Type_01_Line
        MapType_01_Line mapObject01Line = new MapType_01_Line("SOSMAP01",
                "EXAMPLE_SOS_MAP_01", "Map_Initialization_Type01_Line.txt");

        //Map_Type_02_Cycle
        MapType_02_Cycle mapObject02Cycle = new MapType_02_Cycle("SOSMAP02",
                "EXAMPLE_SOS_MAP_02", "Map_Initialization_Type02_Cycle.txt");

        //Map_Type_03_Plane
        MapType_03_Plane mapObject03Plane = new MapType_03_Plane("SOSMAP03",
                "EXAMPLE_SOS_MAP_03", "Map_Initialization_Type03_Plane.txt");

        //Map_Type_04_Cuboid
        MapType_04_Cuboid mapObject04Cuboid = new MapType_04_Cuboid("SOSMAP04",
                "EXAMPLE_SOS_MAP_04", "Map_Initialization_Type04_Cuboid.txt");

        //Map_Type_05_Cuboid_Extended (including Building numbers)
        MapType_05_Cuboid_Extended mapObject05CuboidExtended = new MapType_05_Cuboid_Extended("SOSMAP05",
                "EXAMPLE_SOS_MAP_05", "Map_Initialization_Type05_Cuboid_Extended.txt");

        SmartTransportMap smartTransportMap = new SmartTransportMap("STMAP",
                "SMART_TRANSPORT_MAP", "Smart_Transport_Map_Initialization.txt");


        /* Initialization of a sim model */
        //Declaration and instantiation of an SoS object
        ExampleSoSType sos01 = new ExampleSoSType("SOS01",
                "EXAMPLE_SOS",
                "",
                mapObject01Line,
                new ArrayList<Entity>());

        ExampleSoSType sos02 = new ExampleSoSType("SOS01",
                "EXAMPLE_SOS",
                "",
                mapObject02Cycle,
                new ArrayList<Entity>());

        ExampleSoSType sos03 = new ExampleSoSType("SOS01",
                "EXAMPLE_SOS",
                "",
                mapObject03Plane,
                new ArrayList<Entity>());

        ExampleSoSType sos04 = new ExampleSoSType("SOS01",
                "EXAMPLE_SOS",
                "",
                mapObject04Cuboid,
                new ArrayList<Entity>());

        ExampleSoSType sos05 = new ExampleSoSType("SOS01",
                "EXAMPLE_SOS",
                "",
                mapObject05CuboidExtended,
                new ArrayList<Entity>());


        ExampleSoSType stsos = new ExampleSoSType("STSOS",
                "SMART_TRANSPORT_SOS",
                "",
                smartTransportMap,
                new ArrayList<>());


//        SimEngine simEngine = new SimEngine(sos);
//        simEngine.startSimulation();
    }

}
