package kr.ac.kaist.se.scenario_example.old;

import kr.ac.kaist.se.model.abst.var.EnumDomainType;
import kr.ac.kaist.se.model.sos.SoSMap;
import kr.ac.kaist.se.model.sos.domain.DataVarDomain;
import kr.ac.kaist.se.model.sos.domain.DimVarDomain;
import kr.ac.kaist.se.model.sos.var.DataVar;
import kr.ac.kaist.se.model.sos.var.DimVar;
import kr.ac.kaist.se.scenario_example.MapCoordinateDimType;
import kr.ac.kaist.se.scenario_example.MapFloorDimType;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A concrete class of an example SoSMap
 *
 * @author ymbaek
 */
public class ExampleSoSMap extends SoSMap {

    public ExampleSoSMap(String mapId, String mapName, String mapInitFileName) {
        super(mapId, mapName, mapInitFileName);
    }

    public ExampleSoSMap(String mapId, String mapName, ArrayList<DimVar> mapDimVars, ArrayList<DataVar> mapDataVars, String mapInitFileName) {
        super(mapId, mapName, mapDimVars, mapDataVars, mapInitFileName);
    }

    @Override
    protected void initMapDimensions() {
        /*
        This method is automatically called by SoSMap.

        This ExampleSoSMap is a 3-dimensional map (logical, discrete)
        Dimensions of this map: 5 x 3 x 3-floors
            - xPos: int (0,5)
            - yPos: int (0,5)
            - floor: enum ("FLOOR_1", "FLOOR_2", "FLOOR_3")
         */

        DimVarDomain xPosDomain = new DimVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 2);
        DimVarDomain yPosDomain = new DimVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 2);
        DimVarDomain floorDomain = new DimVarDomain(EnumDomainType.ENUMERATION, new ArrayList<String>(Arrays.asList("FLOOR_1", "FLOOR_2", "FLOOR_3")));
//        DimVarDomain floorDomain = new DimVarDomain(EnumDomainType.ENUMERATION, new ArrayList<String>(Arrays.asList("FLOOR_1")));

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

        addDimVar(xPosDimVar);
        addDimVar(yPosDimVar);
        addDimVar(floorDimVar);

        System.out.println("[" + this.getClass().getSimpleName() + "] mapDimVars initialized (size:" + getMapDimVars().size() + ")");
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

        System.out.println("[" + this.getClass().getSimpleName() + "] mapDataVar initialized (size:" + getMapDataVars().size() + ")");
    }
}
