package kr.ac.kaist.se.scenario_example;

import kr.ac.kaist.se.model.abst.var.EnumDomainType;
import kr.ac.kaist.se.model.sos.SoSMap;
import kr.ac.kaist.se.model.sos.domain.DataVarDomain;
import kr.ac.kaist.se.model.sos.domain.DimVarDomain;
import kr.ac.kaist.se.model.sos.var.DataVar;
import kr.ac.kaist.se.model.sos.var.DimVar;

import java.util.ArrayList;

/**
 * Example map type for case study: (b) 02_cycle
 * @author ymbaek, ehcho
 */
public class MapType_02_Cycle extends SoSMap {

    public MapType_02_Cycle(String mapId, String mapName, String mapInitFileName) {
        super(mapId, mapName, mapInitFileName);
    }

    public MapType_02_Cycle(String mapId, String mapName, ArrayList<DimVar> mapDimVars, ArrayList<DataVar> mapDataVars, String mapInitFileName) {
        super(mapId, mapName, mapDimVars, mapDataVars, mapInitFileName);
    }

    @Override
    protected void initMapDimensions() {
        /*
        This method is automatically called by SoSMap.

        This ExampleSoSMap is a 1-dimensional cycle map (logical, discrete)
        Dimensions of this map
            - loopPos: int [0,5]
         */
        DimVarDomain loopPosDomain = new DimVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 5);

        MapCoordinateDimType loopPosDimVar = new MapCoordinateDimType(
                "loopPosVar",
                "loopPosVar",
                "Int",
                "0",
                "0",
                loopPosDomain);

        addDimVar(loopPosDimVar);

    }

    @Override
    protected void initMapInformation() {
        /*
        This method is automatically called by SoSMap.

        This ExampleSoSMap has one dataVars:
            - isStartPositionVar: boolean [0,1]
         */

        DataVarDomain isStartPositionDomain = new DataVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE,0,1);

        DataVar isStartPositionVar = new DataVar(
                "isStartPositionVar",
                "isStartPositionVar",
                "Int",
                "0",
                "0",
                isStartPositionDomain);

        addDataVar(isStartPositionVar);
    }
}
