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
 * Example map type for case study: (c) 03_Plane
 * @author ymbaek, ehcho
 */
public class MapType_03_Plane extends SoSMap {
    public MapType_03_Plane(String mapId, String mapName, String mapInitFileName) {
        super(mapId, mapName, mapInitFileName);
    }

    public MapType_03_Plane(String mapId, String mapName, ArrayList<DimVar> mapDimVars, ArrayList<DataVar> mapDataVars, String mapInitFileName) {
        super(mapId, mapName, mapDimVars, mapDataVars, mapInitFileName);
    }

    @Override
    protected void initMapDimensions() {
        /*
        This method is automatically called by SoSMap.

        This ExampleSoSMap is a 2-dimensional map (logical, discrete)
        Dimensions of this map
            - xPos: int [0,2]
            - yPos: int [0,3]
         */
        DimVarDomain xPosDomain = new DimVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 2);
        DimVarDomain yPosDomain = new DimVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 3);

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


        addDimVar(xPosDimVar);
        addDimVar(yPosDimVar);

    }

    @Override
    protected void initMapInformation() {
        /*
        This method is automatically called by SoSMap.

        This ExampleSoSMap has two dataVars:
            - isWallVar: boolean [0,1]
            - isChargingStationVar: boolean [0,1]
         */

        DataVarDomain isWallDomain = new DataVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE,0,1);
        DataVarDomain isChargingStationDomain = new DataVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE,0,1);

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


        addDataVar(isWallVar);
        addDataVar(isChargingStationVar);
    }
}
