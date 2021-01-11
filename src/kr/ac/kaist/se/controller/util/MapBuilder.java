package kr.ac.kaist.se.controller.util;

import kr.ac.kaist.se.model.sos.var.DataVar;
import kr.ac.kaist.se.model.sos.var.DimVar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class MapBuilder {

    public MapBuilder() {

    }

    public void updateMapData(String mapInitString,
                              HashMap<String, ArrayList<DataVar>> mapLocInfoToBeUpdated,
                              ArrayList<DimVar> mapDimVars,
                              ArrayList<DataVar> mapDataVars) {

        Set<String> wholeKeySet = mapLocInfoToBeUpdated.keySet();
        ArrayList<String> matchingKeySet;

        ArrayList<String> mapInitQueryList = new ArrayList<>(Arrays.asList(mapInitString.trim().split(";")));

        System.out.println();

        //Trim for every query
        for (String mapInitQuery : mapInitQueryList) {
            String setString = "";
            String whereString = "";

            ArrayList<String> datavarAssignmentClauses = new ArrayList<>();
            ArrayList<String> dimvarConditionClauses = new ArrayList<>();

            ArrayList<Integer> condDimVarIndices = new ArrayList<>();
            ArrayList<String> condDimVarValues = new ArrayList<>();

            ArrayList<Integer> dataVarIndices = new ArrayList<>();
            ArrayList<String> dataVarValues = new ArrayList<>();

            //List of datavars to be stored in the hashmap (mapLocInfoToBeUpdated)
            ArrayList<DataVar> locDataVars = new ArrayList<>();

            mapInitQuery = mapInitQuery.trim();

            /* Get setString (SET(setString)) */
            setString = mapInitQuery.split("WHERE")[0].split("SET")[1].trim();
            setString = setString.replace("(", "").replace(")", "").trim();

            trimStrings(new ArrayList<>(Arrays.asList(setString.split(","))), datavarAssignmentClauses);
//            System.out.println(datavarAssignmentClauses);


            //If there is WHERE clause, we have to check <dimvarConditionClauses(s)>
            if (mapInitQuery.contains("WHERE")) {


                /* Get whereString (WHERE(whereString)) */
                whereString = mapInitQuery.split("WHERE")[1].trim();
                whereString = whereString.replace("(", "").replace(")", "").trim();

                //WHERE(dimvar_cond)
                if (!whereString.equals("ALL")) {
                    trimStrings(new ArrayList<>(Arrays.asList(whereString.split("&&"))), dimvarConditionClauses);

                    parseDimVarConditionClauses(dimvarConditionClauses, mapDimVars, condDimVarIndices, condDimVarValues);

                    //Matching keys are found based on dimvarConditions
                    matchingKeySet = findMatchingKeys(wholeKeySet, condDimVarIndices, condDimVarValues);

//                    System.out.println(matchingKeySet);

                    parseDataVarAssignmentClauses(datavarAssignmentClauses, mapDataVars, dataVarIndices, dataVarValues);

                    updateLocDataVars(matchingKeySet, mapLocInfoToBeUpdated, locDataVars, dataVarIndices, dataVarValues);

//                    setLocDataVars(mapDataVars, dataVarIndices, dataVarValues, locDataVars);
//
//                    for (String matchingKey : matchingKeySet){
//                        mapLocInfoToBeUpdated.put(matchingKey, locDataVars);
//                    }


                }
                //WHERE(ALL)
                else{
                    //In this case, parsing dimVarConditions (where clause) is not needed.
                    //Since this is for all points, wholeKeySet itself will be matchingKeySet
                    matchingKeySet = new ArrayList<>(wholeKeySet);

                    parseDataVarAssignmentClauses(datavarAssignmentClauses, mapDataVars, dataVarIndices, dataVarValues);

                    updateLocDataVars(matchingKeySet, mapLocInfoToBeUpdated, locDataVars, dataVarIndices, dataVarValues);
//                    setLocDataVars(mapDataVars, dataVarIndices, dataVarValues, locDataVars);

                    for (String matchingKey : matchingKeySet){
                        mapLocInfoToBeUpdated.put(matchingKey, locDataVars);
                    }
                }

            }
            //If there is no WHERE clause, it means ALL
            else if (!mapInitQuery.contains("WHERE")) {
                //In this case, parsing dimVarConditions (where clause) is not needed.
                //Since this is for all points, wholeKeySet itself will be matchingKeySet
                matchingKeySet = new ArrayList<>(wholeKeySet);

                parseDataVarAssignmentClauses(datavarAssignmentClauses, mapDataVars, dataVarIndices, dataVarValues);

                updateLocDataVars(matchingKeySet, mapLocInfoToBeUpdated, locDataVars, dataVarIndices, dataVarValues);
//                setLocDataVars(mapDataVars, dataVarIndices, dataVarValues, locDataVars);

                for (String matchingKey : matchingKeySet){
                    mapLocInfoToBeUpdated.put(matchingKey, locDataVars);
                }
            }

//            System.out.println(mapLocInfoToBeUpdated);

            System.out.println("updateMapData Query: " + mapInitQuery);
            printMapLocHashMap(mapLocInfoToBeUpdated);
//            System.out.println(mapLocInfoToBeUpdated.size());


//            ArrayList<String> tmpList = getDimValuesFromKey("3,3,FLOOR_1");
//            System.out.println(tmpList.size() + ":" + tmpList);
//
//            Set<String> keySets = mapLocInfoToBeUpdated.keySet();
//            System.out.println(findMatchingKeys(wholeKeySet, condDimVarIndices, condDimVarValues));

//            System.out.println(keySets);
//



//            System.out.println(setString + "|" + whereString + "\n");

//            ArrayList<String> tmpString = new ArrayList<>(Arrays.asList(mapInitQuery.split("WHERE")));
//            trimStrings(tmpString);


        }
    }

    private void printMapLocHashMap(HashMap<String, ArrayList<DataVar>> mapLocHashMap){
        for(HashMap.Entry<String, ArrayList<DataVar>> pair : mapLocHashMap.entrySet()){
            System.out.print(pair.getKey() + "|");
            for (DataVar aDataVar: pair.getValue()){
                System.out.print(aDataVar.getVarId() + "(" + aDataVar.getDataCurValue() + ")");
            }
            System.out.println();
        }
    }


    private void parseDimVarConditionClauses(ArrayList<String> dimvarConditionClauses,
                                             ArrayList<DimVar> mapDimVars,
                                             ArrayList<Integer> condDimVarIndices,
                                             ArrayList<String> condDimVarValues){
        //For each dimvarConditionClauses (e.g., xPosVar==3)
        for (String cond : dimvarConditionClauses) {
//                        System.out.print(cond + ": ");

            String dimId = cond.split("==")[0].trim();
            String dimValue = cond.split("==")[1].trim();

            //For enumeration case that contains double quotation marks (e.g., "FLOOR_B1")
            //Remove the double-quotation marks
            if (dimValue.contains("\"")){
                dimValue = dimValue.replaceAll("\"", "");
            }

//                        System.out.println(dimId + "|" + dimValue);

            int index = 0;
            for (DimVar mapDimVar : mapDimVars){
                if (mapDimVar.getVarId().equals(dimId)){
                    condDimVarIndices.add(index);
                    condDimVarValues.add(dimValue);
                }
                index++;
            }
        }
    }

    private void parseDataVarAssignmentClauses(ArrayList<String> datavarAssignmentClauses,
                                               ArrayList<DataVar> mapDataVars,
                                               ArrayList<Integer> dataVarIndices,
                                               ArrayList<String> dataVarValues){

        for (String assignmentQuery : datavarAssignmentClauses){
//                        System.out.print(assignmentQuery + ": ");

            String dataVarId = assignmentQuery.split("=")[0].trim();
            String dataVarValue = assignmentQuery.split("=")[1].trim();

            //For enumeration case that contains double quotation marks (e.g., "FLOOR_B1")
            //Remove the double-quotation marks
            if (dataVarValue.contains("\"")){
                dataVarValue = dataVarValue.replaceAll("\"", "");
            }

            int index = 0;
            for (DataVar mapDataVar : mapDataVars){
                if (mapDataVar.getVarId().equals(dataVarId)){
                    dataVarIndices.add(index);
                    dataVarValues.add(dataVarValue);
                }
                index++;
            }

//                        System.out.println(dataVarId + "|" + dataVarValue);


        }
    }


    private void updateLocDataVars(ArrayList<String> matchingKeySet,
                                   HashMap<String, ArrayList<DataVar>> mapLocInfoToBeUpdated,
                                   ArrayList<DataVar> locDataVars,
                                   ArrayList<Integer> dataVarIndices,
                                   ArrayList<String> dataVarValues){
        for (String matchingKey : matchingKeySet){
            ArrayList<DataVar> dataVars = mapLocInfoToBeUpdated.get(matchingKey);

            locDataVars.clear();

            int dataVarIndex = 0;
            for (DataVar dataVar : dataVars){

                DataVar newDataVar = (DataVar)dataVar.clone();

                int innerIndex = 0;
                for (Integer index : dataVarIndices){

                    if (dataVarIndex == index){
                        newDataVar.setDataCurValue(dataVarValues.get(innerIndex));
                    }

                    innerIndex++;
                }
                dataVarIndex++;
                locDataVars.add(newDataVar);
            }

//            System.out.println(matchingKey + "|" + locDataVars);
            mapLocInfoToBeUpdated.put(matchingKey, locDataVars);
        }
    }

//    private void setLocDataVars(ArrayList<DataVar> mapDataVars,
//                                ArrayList<Integer> dataVarIndices,
//                                ArrayList<String> dataVarValues,
//                                ArrayList<DataVar> locDataVars){
//
//
//        int dataVarIndex = 0;
//        for (DataVar dataVar : mapDataVars){
//
//            DataVar newDataVar = new DataVar(
//                    dataVar.getVarId(),
//                    dataVar.getVarName(),
//                    dataVar.getVarType(),
//                    dataVar.getDataDefaultValue(),
//                    dataVar.getDataCurValue(),
//                    dataVar.getVarDomain()
//            );
//
//            int innerIndex = 0;
//            for (Integer index : dataVarIndices){
//                if (dataVarIndex == index){
////                    System.out.println(dataVarIndex); //1
//                    newDataVar.setDataCurValue(dataVarValues.get(innerIndex));
//                }
////                            mapDataVars.get(index).
//                innerIndex++;
//            }
//            dataVarIndex++;
//
//            locDataVars.add(newDataVar);
//        }
//    }

    /**
     * A method to find a list(set) of matching keys from a whole key sets,
     * based on varIndices and varValues.
     * @param wholeKeySets  A pool that has a whole set of keys
     * @param varIndices    List of indices contained in dimvar_cond (1 if yPosVar==3)
     * @param varValues     List of values contained in dimvar_cond (3 if yPosVar==3)
     * @return
     */
    private ArrayList<String> findMatchingKeys(Set<String> wholeKeySets, ArrayList<Integer> varIndices, ArrayList<String> varValues){
        ArrayList<String> matchingKeys = new ArrayList<>();
        boolean isMatching;

        for (String key : wholeKeySets){
            isMatching = true;

            int varIndicesIndex = 0;
            for (Integer index : varIndices){
                //If the key matches the given condition
                if (!(getDimValuesFromKey(key).get(index).equals(varValues.get(varIndicesIndex)))){
                    isMatching = false;
                }
                varIndicesIndex++;
            }

            if (isMatching){
                matchingKeys.add(key);
            }
        }

        return matchingKeys;
    }


    private void trimStrings(ArrayList<String> stringList, ArrayList<String> stringListTrimmed) {
        for (String aString : stringList) {
            stringListTrimmed.add(aString.trim());
        }
    }

    /**
     * A method to get dim values from string-based key (e.g., 3,2,FLOOR_3)
     * Size of returned list should be same as dimVarList.size()
     *
     * @param key key to be decomposed
     * @return A list of dim values of the given key
     */
    private ArrayList<String> getDimValuesFromKey(String key) {
        ArrayList<String> dimValListTrimmed = new ArrayList<>();
        ArrayList<String> dimValList = new ArrayList<>(Arrays.asList(key.split(",")));

        //Trim the value strings
        trimStrings(dimValList, dimValListTrimmed);

        return dimValListTrimmed;
    }

    /**
     * A method to create a key string based on a list of strings (dimValues)
     * @param dimValues a list that stores string values for every dim
     * @return key string generated
     */
    private String makeKeyFromDimValue(ArrayList<String> dimValues){
        String keyToBeReturned = "";

        int index = 0;
        for (String dimValue : dimValues){
            keyToBeReturned += dimValue;
            if (index + 1 < dimValues.size()){
                keyToBeReturned += ",";
            }
            index++;
        }

        return keyToBeReturned;
    }
}
