package kr.ac.kaist.se.scenario_example;

import kr.ac.kaist.se.model.abst.var.EnumDomainType;
import kr.ac.kaist.se.model.sos.SoSMap;
import kr.ac.kaist.se.model.sos.domain.DataVarDomain;
import kr.ac.kaist.se.model.sos.domain.DimVarDomain;
import kr.ac.kaist.se.model.sos.var.DataVar;
import kr.ac.kaist.se.model.sos.var.DimVar;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Example map type for case study: Extension of (d) 04_Cuboid
 *
 * @author ymbaek, ehcho
 */
public class MapType_05_Cuboid_Extended extends SoSMap {

    public MapType_05_Cuboid_Extended(String mapId, String mapName, String mapInitFileName) {
        super(mapId, mapName, mapInitFileName);
    }

    public MapType_05_Cuboid_Extended(String mapId, String mapName, ArrayList<DimVar> mapDimVars, ArrayList<DataVar> mapDataVars, String mapInitFileName) {
        super(mapId, mapName, mapDimVars, mapDataVars, mapInitFileName);
    }

    @Override
    protected void initMapDimensions() {
        /*
        This method is automatically called by SoSMap.

        This ExampleSoSMap is a 4-dimensional map (logical, discrete)
        Dimensions of this map
            - xPos: int [0,2]
            - yPos: int [0,3]
            - floor: enum ("FLOOR_1", "FLOOR_2")
            - buildingId: enum ("BLDG_A", "BLDG_B")
         */
        DimVarDomain xPosDomain = new DimVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 2);
        DimVarDomain yPosDomain = new DimVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 3);
        DimVarDomain floorDomain = new DimVarDomain(EnumDomainType.ENUMERATION, new ArrayList<String>(Arrays.asList("FLOOR_1", "FLOOR_2")));
        DimVarDomain buildingIdDomain = new DimVarDomain(EnumDomainType.ENUMERATION, new ArrayList<String>(Arrays.asList("BLDG_A", "BLDG_B")));

        MapCoordinateDimType xPosDimVar = new MapCoordinateDimType(
                "xPosVar",
                "xPosVar",
                "Int",
                "0",
                "0",
                xPosDomain);

        MapCoordinateDimType yPosDimVar = new MapCoordinateDimType(
                "yPosVar",
                "yPosVar",
                "Int",
                "0",
                "0",
                yPosDomain);

        MapFloorDimType floorDimVar = new MapFloorDimType(
                "floorVar",
                "floorVar",
                "Enum",
                "FLOOR_1",
                "FLOOR_1",
                floorDomain);

        MapBuildingIdDimType buildingIdDimVar = new MapBuildingIdDimType(
                "buildingIdVar",
                "buildingIdVar",
                "Enum",
                "BLDG_A",
                "BLDG_A",
                buildingIdDomain);


        addDimVar(xPosDimVar);
        addDimVar(yPosDimVar);
        addDimVar(floorDimVar);
        addDimVar(buildingIdDimVar);

    }

    @Override
    protected void initMapInformation() {
        /*
        This method is automatically called by SoSMap.

        This ExampleSoSMap has three dataVars:
            - isWallVar: boolean [0,1]
            - isChargingStationVar: boolean [0,1]
            - dustLevelVar: int [0,100]
         */

        DataVarDomain isWallDomain = new DataVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 1);
        DataVarDomain isChargingStationDomain = new DataVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 1);
        DataVarDomain dustLevelDomain = new DataVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 100);

        DataVar isWallVar = new DataVar(
                "isWallVar",
                "isWallVar",
                "Int",
                "0",
                "0",
                isWallDomain);

        DataVar isChargingStationVar = new DataVar(
                "isChargingStationVar",
                "isChargingStationVar",
                "Int",
                "0",
                "0",
                isChargingStationDomain);

        DataVar dustLevelVar = new DataVar(
                "dustLevelVar",
                "dustLevelVar",
                "Int",
                "0",
                "0",
                dustLevelDomain);

        addDataVar(isWallVar);
        addDataVar(isChargingStationVar);
        addDataVar(dustLevelVar);
    }
}
