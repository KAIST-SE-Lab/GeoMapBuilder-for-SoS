package kr.ac.kaist.se.scenario;

import kr.ac.kaist.se.model.sos.SoSMap;
import kr.ac.kaist.se.model.sos.var.DataVar;
import kr.ac.kaist.se.model.sos.var.DimVar;

import java.util.ArrayList;

/**
 * A concrete class of an example SoSMap
 * @author ymbaek
 */
public class ExampleSoSMap extends SoSMap {

    public ExampleSoSMap(String mapId,
                         String mapName,
                         ArrayList<DimVar> mapDimVars,
                         ArrayList<DataVar> mapDataVars) {
        super(mapId, mapName, mapDimVars, mapDataVars);
    }

    @Override
    protected void initMapDimensions() {

    }

    @Override
    protected void initMapInformation() {

    }
}
