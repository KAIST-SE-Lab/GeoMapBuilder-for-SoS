package kr.ac.kaist.se.scenario_example;

import kr.ac.kaist.se.model.abst.var.EnumDomainType;
import kr.ac.kaist.se.model.sos.SoSMap;
import kr.ac.kaist.se.model.sos.domain.DataVarDomain;
import kr.ac.kaist.se.model.sos.domain.DimVarDomain;
import kr.ac.kaist.se.model.sos.var.DataVar;
import kr.ac.kaist.se.model.sos.var.DimVar;

import java.util.ArrayList;
import java.util.Arrays;

public class SmartTransportMap extends SoSMap {
    public SmartTransportMap(String mapId, String mapName, String mapInitFileName) {
        super(mapId, mapName, mapInitFileName);
    }

    public SmartTransportMap(String mapId, String mapName, ArrayList<DimVar> mapDimVars, ArrayList<DataVar> mapDataVars, String mapInitFileName) {
        super(mapId, mapName, mapDimVars, mapDataVars, mapInitFileName);
    }

    @Override
    protected void initMapDimensions() {
        /*
        Dimensions of this map
            - CyclicRailRoad: int [0,99]
            - RailRoadLane: enum ("RAIL", "FACTORY")
         */
        DimVarDomain cyclicRailRoadDomain = new DimVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 99);
        DimVarDomain railRoadLaneDomain = new DimVarDomain(EnumDomainType.ENUMERATION, new ArrayList<>(Arrays.asList("RAIL", "FACTORY")));

        MapCoordinateCycleDimType cyclicRailRoadDimVar = new MapCoordinateCycleDimType(
                "cyclicRailRoadVar",
                "cyclicRailRoadVar",
                "Int",
                "0",
                "0",
                cyclicRailRoadDomain);

        MapBuildingIdDimType railRoadLaneDimVar = new MapBuildingIdDimType(
                "railRoadLaneVar",
                "railRoadLaneVar",
                "Enum",
                "RAIL",
                "RAIL",
                railRoadLaneDomain);

        addDimVar(cyclicRailRoadDimVar);
        addDimVar(railRoadLaneDimVar);
    }

    @Override
    protected void initMapInformation() {
        /*
        This SmartTransport has three dataVars:
            - isStationVar: boolean [0,1]
            - isStationFrontVar: boolean [0,1]
            - isStorageVar: boolean [0, 1]
         */

        DataVarDomain isStationDomain = new DataVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 1);
        DataVarDomain isStationFrontDomain = new DataVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 1);
        DataVarDomain isStorageDomain = new DataVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 1);

        DataVar isStationVar = new DataVar(
                "isStationVar",
                "isStationVar",
                "Int",
                "0",
                "0",
                isStationDomain);

        DataVar isStationFrontVar = new DataVar(
                "isStationFrontVar",
                "isStationFrontVar",
                "Int",
                "0",
                "0",
                isStationFrontDomain);

        DataVar isStorageVar = new DataVar(
                "isStorageVar",
                "isStorageVar",
                "Int",
                "0",
                "0",
                isStorageDomain);

        addDataVar(isStationVar);
        addDataVar(isStationFrontVar);
        addDataVar(isStorageVar);
    }
}
