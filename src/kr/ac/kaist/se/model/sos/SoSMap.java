package kr.ac.kaist.se.model.sos;

import kr.ac.kaist.se.controller.util.MapBuilder;
import kr.ac.kaist.se.model.abst.geo._SimMap_;
import kr.ac.kaist.se.model.sos.var.DataVar;
import kr.ac.kaist.se.model.sos.var.DimVar;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class to represent a geographical map of an SoS
 * @author ymbaek
 */
public abstract class SoSMap extends _SimMap_ {

    /** ArrayLists for MapDimensions and MapInformation */
    protected ArrayList<DimVar> mapDimVars = new ArrayList<>();   //LocDimensions of this map
    public ArrayList<DataVar> mapDataVars = new ArrayList<>();          //LocInformations of this map

    /** Initialization of map */
    protected HashMap<String, ArrayList<DataVar>> mapLocInfo = new HashMap<>(); //HashMap to store location information

    /** MapBuilder */
    protected MapBuilder mapBuilder = new MapBuilder(); //MapBuilder to initialize and update a map


    public SoSMap(String mapId,
                  String mapName,
                  ArrayList<DimVar> mapDimVars,
                  ArrayList<DataVar> mapDataVars) {
        super(mapId, mapName);
        this.mapDimVars = mapDimVars;
        this.mapDataVars = mapDataVars;
    }

    /**
     * Initialization of a map by calling other methods
     */
    protected void initMap(){
        /* Initialization of map dimensions (mapDimVars) */
        initMapDimensions();

//        timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println("[" + timestamp + "] (ToySoSMap: initDimensions) Dimensions (mapDimVars) are initialized (size:" + mapDimVars.size() + ")");


        /* Initialization of map data variables (mapDataVars) */
        initMapInformation();

//        timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println("[" + timestamp + "] (ToySoSMap: initDimensions) MapInformation (mapDataVars) is initialized (size:" + mapDataVars.size() + ")");


        /* Initialization of map location information (mapLocInfo) */
        initMapLocInfo();

//        timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println("[" + timestamp + "] (ToySoSMap: initDimensions) Location information (mapLocInfo) are initialized (size:" + mapLocInfo.size() + ")");

        //TODO: MapBuilder
//        mapBuilder.updateMapData(mapDimVars, mapDataVars, mapLocInfo, new ArrayList<String>());
    }


    /**
     * A method for initialization of map dimensions (mapDimVars)
     * This method depends on dimensionVars defined for a concrete map
     */
    protected abstract void initMapDimensions();

    /**
     * Initialization of map data variables (mapDataVars)
     * This method depends on dimensionVars defined for a concrete map
     */
    protected abstract void initMapInformation();


    /**
     * A method to initially assign data values into mapLocInfo hashmap.
     */
    protected void initMapLocInfo(){

        /*
        EX) xPosVar, yPosVar, floorNumVar:
        key :=
            (0, 0, FLOOR_1)
            (0, 0, FLOOR_B1)
            (0, 0, FLOOR_2)
            ...
         */

//        String key = "";
//        int index = 0;
//        for (DimVar dimVar : mapDimVars){
//            if (index < mapDimVars.size()-1){
//                key += dimVar.getDataDefaultValue() + ",";
//            }else{
//                key += dimVar.getDataDefaultValue();
//            }
//
//            if (dimVar.getVarType().equals("Int") || dimVar.getVarType().equals("Integer")){
//                DimVar tmpDimVar = (DimVar)dimVar.clone();
//                tmpDimVar.setDataCurValue((int)dimVar.getVarDomain().getDomainMinVal() + "");
//
//                while (Integer.valueOf(tmpDimVar.getDataCurValue()) <= (int)tmpDimVar.getVarDomain().getDomainMaxVal()){
//                    System.out.print(tmpDimVar.getDataCurValue() + " ");
//                    tmpDimVar.updateValueOfDim(1);
//                }
//
//                System.out.println(tmpDimVar.getDataCurValue());
//            }
//
//            System.out.print(dimVar.getVarName() + "[" + dimVar.getVarDomain().getDomainMinVal());
//
//            System.out.println("-" + dimVar.getVarDomain().getDomainMaxVal() + "]");
//
//            index++;
//        }
//
//        System.out.println(key);



    }



    /* Getters & Setters */

    public ArrayList<DimVar> getMapDimVars() {
        return mapDimVars;
    }

    public void setMapDimVars(ArrayList<DimVar> mapDimVars) {
        this.mapDimVars = mapDimVars;
    }

    public void addMapDimension(DimVar dimVar) {
        if (dimVar != null) {
            this.mapDimVars.add(dimVar);
        }
    }

    public ArrayList<DataVar> getMapDataVars() {
        return mapDataVars;
    }

    public void setMapDataVars(ArrayList<DataVar> mapDataVars) {
        this.mapDataVars = mapDataVars;
    }

    public void addMapInfo(DataVar infoVar) {
        if (infoVar != null) {
            this.mapDataVars.add(infoVar);
        }
    }

    public HashMap<String, ArrayList<DataVar>> getMapLocInfo() {
        return mapLocInfo;
    }

    public void setMapLocInfo(HashMap<String, ArrayList<DataVar>> mapLocInfo) {
        this.mapLocInfo = mapLocInfo;
    }
}
