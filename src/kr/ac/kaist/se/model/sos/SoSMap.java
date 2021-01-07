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
    private ArrayList<DimVar> mapDimVars = new ArrayList<>();         //LocDimensions of this map
    private ArrayList<DataVar> mapDataVars = new ArrayList<>();          //LocInformations of this map

    /** Initialization of map */
    protected HashMap<String, ArrayList<DataVar>> mapLocInfo = new HashMap<>(); //HashMap to store location information

    /** MapBuilder */
    protected MapBuilder mapBuilder = new MapBuilder(); //MapBuilder to initialize and update a map


    public SoSMap(String mapId, String mapName) {
        super(mapId, mapName);
        initMap();
    }

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

        //Set dataCurValues as minimum values allowed by their domains
        for (DimVar mapDimVar : mapDimVars){
            if (mapDimVar.getVarType().equals("Int")) {
                mapDimVar.setDataCurValue((int) mapDimVar.getVarDomain().getDomainMinVal() + "");
//                System.out.println("int:" + mapDimVar.getDataCurValue());
            } else {
                mapDimVar.setDataCurValue(mapDimVar.getVarDomain().getDomainEnumVal().get(0) + "");
//                System.out.println("not int:" + mapDimVar.getDataCurValue());
            }
        }


        //Keys to be stored in hashmap (mapLocInfo)
        ArrayList<String> keyList = new ArrayList<>();

//        attachKeyString(keyList, 0);
//        System.out.println("[size:" + keyList.size() + "] " + keyList);

//        System.out.println(countAllLocPoints());

        System.out.println("[" + this.getClass().getSimpleName() + "] mapLocInfo (hashMap) initialized (size:" + getMapLocInfo().size() + ")");
    }


//    private String attachKeyString(ArrayList<String> keyList, int varIndex){
//
////        int dimIndex = 0;
////        String key = "";
//
//        while (mapDimVars.get(varIndex).checkUpdateValid(0)){
//
//            System.out.println((varIndex+1) + "|" + mapDimVars.size());
//
//            String curDimVarVal = mapDimVars.get(varIndex).getDataCurValue();
//
////            boolean isInsideOfDomain = ;
////            if (!isInsideOfDomain) {
////                System.out.println("outOfDomain");
////                return "";
////            }
//
//            //If this varIndex is the last
//            if (varIndex + 1 >= mapDimVars.size()){
//                System.out.println(curDimVarVal);
////                keyList.add(curDimVarVal);
//
//                return curDimVarVal;
//            }
//            //If a next DimVar exists
//            else{
////                keyList.add(curDimVarVal + attachKeyString(keyList, varIndex++));
//                return curDimVarVal + attachKeyString(keyList, varIndex++);
//            }
//
//            mapDimVars.get(varIndex).updateValueOfDim(1);
//
//        }
//
//    }

    /**
     * A method to add a DimVar into mapDimVars
     * @param aDimVar DimVar to be added
     */
    protected void addDimVar(DimVar aDimVar){
        boolean isDuplicate = false;
        for (DimVar dimVar : mapDimVars){
            if (aDimVar.getVarId().equals(dimVar.getVarId())){
                isDuplicate = true;
            }
        }

        if (!isDuplicate){
            mapDimVars.add(aDimVar);
        }else{
            System.out.println("[" + this.getClass().getSimpleName() + "] addDimVar failed (duplicate id: " + aDimVar.getVarId() + ")");
        }
    }

    /**
     * A method to add a DataVar into mapDataVars
     * @param aDataVar DataVar to be added
     */
    protected void addDataVar(DataVar aDataVar){
        boolean isDuplicate = false;
        for (DataVar dataVar : mapDataVars){
            if (aDataVar.getVarId().equals(dataVar.getVarId())){
                isDuplicate = true;
            }
        }

        if (!isDuplicate){
            mapDataVars.add(aDataVar);
        }else{
            System.out.println("[" + this.getClass().getSimpleName() + "] addDataVar failed (duplicate id: " + aDataVar.getVarId() + ")");
        }
    }


    /**
     * A method to count the number of all location points
     * @return number of location points in a map
     */
    protected int countAllLocPoints(){
        int count = 1;

        for (DimVar dimVar : mapDimVars) {
            int dimVarCount = 0;

            //For integer data
            if (dimVar.getVarType().equals("Int")) {
                dimVar.setDataCurValue((int)dimVar.getVarDomain().getDomainMinVal() + "");
            }
            //For enum data
            else{
                dimVar.setDataCurValue(dimVar.getVarDomain().getDomainEnumVal().get(0) + "");
            }

            boolean isInsideOfDomain = true;
            while (isInsideOfDomain) {
                //Increase value by 1
                isInsideOfDomain = dimVar.updateValueOfDim(1);
                //Count
                dimVarCount++;
            }

            count *= dimVarCount;
        }

        return count;
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
