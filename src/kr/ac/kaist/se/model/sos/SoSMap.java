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

    /** Input file for map initialization (.txt) */
    protected String mapInitFile;

    /** ArrayLists for MapDimensions and MapInformation */
    private ArrayList<DimVar> mapDimVars = new ArrayList<>();         //LocDimensions of this map
    private ArrayList<DataVar> mapDataVars = new ArrayList<>();          //LocInformations of this map

    /** Initialization of map */
    protected HashMap<String, ArrayList<DataVar>> mapLocInfo = new HashMap<>(); //HashMap to store location information

    /** MapBuilder */
    protected MapBuilder mapBuilder; //MapBuilder to initialize and update a map



    public SoSMap(String mapId, String mapName, String mapInitFileName) {
        super(mapId, mapName);
        this.mapInitFile = mapInitFileName;

        initMap(mapInitFileName);
    }

    public SoSMap(String mapId,
                  String mapName,
                  ArrayList<DimVar> mapDimVars,
                  ArrayList<DataVar> mapDataVars,
                  String mapInitFileName) {
        super(mapId, mapName);
        this.mapDimVars = mapDimVars;
        this.mapDataVars = mapDataVars;
        this.mapInitFile = mapInitFileName;

        initMap(mapInitFileName);
    }

    /**
     * Initialization of a map by calling other methods
     */
    protected void initMap(String mapInitFileName){
        /* Initialization of map dimensions (mapDimVars) */
        initMapDimensions();

        /* Initialization of map data variables (mapDataVars) */
        initMapInformation();

        if (!(mapInitFileName.equals("nofile") || mapInitFileName.equals(""))) {
            /* Initialization of map location information (mapLocInfo) */
            initMapLocInfo(mapInitFileName);
        }

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
     * This mathod is called only if there is a file for the initialization.
     */
    protected void initMapLocInfo(String mapInitFileName){

        /*
        EX) xPosVar, yPosVar, floorNumVar:
        key :=
            (0, 0, FLOOR_1)
            (0, 0, FLOOR_B1)
            (0, 0, FLOOR_2)
            ...
         */

        initMapLocKeys();

//        System.out.println(makeKeyStrings());

        mapBuilder = new MapBuilder(mapInitFileName, mapLocInfo);
        mapBuilder.updateMapData(mapDimVars, mapDataVars);

        System.out.println("[" + this.getClass().getSimpleName() + "] mapLocInfo (hashMap) initialized (size:" + getMapLocInfo().size() + ")");
    }


    /**
     * A method to initialize keys of mapLoc hashmap (mapLocInfo).
     * Initially, values of the hashmap are initialized as default values
     */
    private void initMapLocKeys(){
        initMapDimVarsAsMinVal();

        //Keys to be stored in hashmap (mapLocInfo) (deprecated)
//        ArrayList<String> keyList = makeKeyString(0);

        //New version
        ArrayList<String> keyList = makeKeyStrings();

        ArrayList<DataVar> defaultDataVars = new ArrayList<>();

        for (DataVar dataVar : mapDataVars){
            DataVar newDataVar = new DataVar(
                    dataVar.getVarId(),
                    dataVar.getVarName(),
                    dataVar.getVarType(),
                    dataVar.getDataDefaultValue(),
                    dataVar.getDataCurValue(),
                    dataVar.getVarDomain()
            );

            defaultDataVars.add(newDataVar);
        }

        //Instead of initializing as null, defaultDataVars are used.
        for (String key : keyList){
            mapLocInfo.put(key, defaultDataVars);
        }
    }


    /**
     * A method to initialize dimVars of a map as minimum values
     */
    private void initMapDimVarsAsMinVal(){
        //Set dataCurValues as minimum values allowed by their domains
        for (DimVar mapDimVar : mapDimVars){
            initMapDimVarAsMinVal(mapDimVar);
        }
    }

    /**
     * A method to initialize a dimvar as a minimum value
     * @param aDimVar A dimVar to be initialized
     */
    private void initMapDimVarAsMinVal(DimVar aDimVar){
        if (aDimVar.getVarType().equals("Int")) {
            aDimVar.setDataCurValue((int) aDimVar.getVarDomain().getDomainMinVal() + "");
        } else {
            aDimVar.setDataCurValue(aDimVar.getVarDomain().getDomainEnumVal().get(0) + "");
        }
    }

    private ArrayList<String> makeKeyStrings(){

        //Keys to be returned
        ArrayList<String> keyListToReturn = new ArrayList<>();

        //Arraylist for multiplication (to count how many times a value is shown)
        ArrayList<Integer> multi = new ArrayList<>();

        //EX) xPos(0~2), yPos(0~4), floor(1~3)
        //expected output of multi: [1, 3, 15]
        multi.add(1);

        for (int i = 0; i < mapDimVars.size() - 1; i++){
            int prevMulti = multi.get(multi.size() - 1);
            DimVar aDimVar = mapDimVars.get(mapDimVars.size() - 1 - i);
            multi.add(prevMulti * aDimVar.countPossibleValues());

        }

//        System.out.println(multi);


        int numTotalKeysExceptFirstDimVar = multi.get(multi.size() - 1);
        int possibleValuesOfFirstDimVar = mapDimVars.get(0).countPossibleValues();

        int numTotalKeys = numTotalKeysExceptFirstDimVar * possibleValuesOfFirstDimVar;

        //For each key
        for (int i = 0; i < numTotalKeys; i++){
            String aKey = "";

            //For each dimVar
            for (int j = 0; j < mapDimVars.size(); j++){
                int multiForDimVar = multi.get(mapDimVars.size() - 1 - j);

                if (j != 0){
                    aKey += ",";
                }

                aKey += mapDimVars.get(j).getValueWithIndex((i / multiForDimVar) % mapDimVars.get(j).countPossibleValues());

            }

            keyListToReturn.add(aKey);
        }


        return keyListToReturn;
    }

    /**
     * A method to make string-based keys of a map hashmap (mapLocInfo)
     * @param varIndex  An index to recursively generate keys
     * @return  A list of keys generated
     * @deprecated {@link #makeKeyStrings()}
     */
    private ArrayList<String> makeKeyString(int varIndex){

        ArrayList<String> thisKeyList = new ArrayList<>();

        if (varIndex + 1 >= mapDimVars.size()){

            boolean isUpdatePossible = true;
            while (isUpdatePossible){
                thisKeyList.add(mapDimVars.get(varIndex).getDataCurValue());

                if (!mapDimVars.get(varIndex).checkUpdateValid(1)){
                    isUpdatePossible = false;
                    initMapDimVarAsMinVal(mapDimVars.get(varIndex));
                }else{
                    mapDimVars.get(varIndex).updateValueOfDim(1);
                }
            }

        }else{
            int nextVarIndex = varIndex + 1;

            boolean isUpdatePossible = true;
            while (isUpdatePossible){
                ArrayList<String> subKeyList = makeKeyString(nextVarIndex);

                for (String subKeyString : subKeyList){
                    thisKeyList.add(mapDimVars.get(varIndex).getDataCurValue() + "," + subKeyString);
                }

                if (!mapDimVars.get(varIndex).checkUpdateValid(1)){
                    isUpdatePossible = false;
                    initMapDimVarAsMinVal(mapDimVars.get(varIndex));
                }else{
                    mapDimVars.get(varIndex).updateValueOfDim(1);
                }
            }
        }

        return thisKeyList;
    }





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

    /**
     * Get a list of locData with a key
     * @param key   a key to be searched
     * @return      A list of locData objects
     */
    public ArrayList<DataVar> getLocDataWithKey(String key){
        return this.mapLocInfo.get(key);
    }
}
