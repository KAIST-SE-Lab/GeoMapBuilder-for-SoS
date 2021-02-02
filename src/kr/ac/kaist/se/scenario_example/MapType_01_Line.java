package kr.ac.kaist.se.scenario_example;

import kr.ac.kaist.se.model.abst.var.EnumDomainType;
import kr.ac.kaist.se.model.sos.SoSMap;
import kr.ac.kaist.se.model.sos.domain.DataVarDomain;
import kr.ac.kaist.se.model.sos.domain.DimVarDomain;
import kr.ac.kaist.se.model.sos.var.DataVar;
import kr.ac.kaist.se.model.sos.var.DimVar;

import java.util.ArrayList;

/**
 * Example map type for case study: (a) 01_Line
 *
 * @author ymbaek, ehcho
 */
public class MapType_01_Line extends SoSMap {
    public MapType_01_Line(String mapId, String mapName, String mapInitFileName) {
        super(mapId, mapName, mapInitFileName);
    }

    public MapType_01_Line(String mapId, String mapName, ArrayList<DimVar> mapDimVars, ArrayList<DataVar> mapDataVars, String mapInitFileName) {
        super(mapId, mapName, mapDimVars, mapDataVars, mapInitFileName);
    }


    @Override
    protected void initMapDimensions() {
        /*
        This method is automatically called by SoSMap.

        This ExampleSoSMap is a 1-dimensional map (logical, discrete)
        Dimensions of this map
            - xPos: int [0,10]
         */
        DimVarDomain xPosDomain = new DimVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 10);

        MapCoordinateDimType xPosDimVar = new MapCoordinateDimType(
                "xPosVar",
                "xPosVar",
                "Int",
                "0",
                "0",
                xPosDomain);

        addDimVar(xPosDimVar);

    }

    @Override
    protected void initMapInformation() {
        /*
        This method is automatically called by SoSMap.

        This ExampleSoSMap has one dataVars:
            - isAccessibleVar: boolean [0,1]
         */

        DataVarDomain isAccessibleDomain = new DataVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 1);

        DataVar isAccessibleVar = new DataVar(
                "isAccessibleVar",
                "isAccessibleVar",
                "Int",
                "0",
                "0",
                isAccessibleDomain);

        addDataVar(isAccessibleVar);
    }
}
