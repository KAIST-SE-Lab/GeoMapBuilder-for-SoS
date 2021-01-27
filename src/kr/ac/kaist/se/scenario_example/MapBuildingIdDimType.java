package kr.ac.kaist.se.scenario_example;

import kr.ac.kaist.se.model.sos.domain.DimVarDomain;
import kr.ac.kaist.se.model.sos.var.DimVar;

/**
 * Dimension to represent building ids of a map
 * @author ymbaek
 */
public class MapBuildingIdDimType extends DimVar {


    public MapBuildingIdDimType(String varId,
                           String varName,
                           String varType,
                           String dataDefaultValue,
                           String dataCurValue,
                           DimVarDomain varDomain) {
        super(varId, varName, varType, dataDefaultValue, dataCurValue, varDomain);
    }

    @Override
    public boolean checkUpdateValid(int diff) {
        if (stringData != null) {
            int enumIndex = -1;
            int index = 0;

            for (String enumItem : varDomain.getDomainEnumVal()) {
                if (stringData.equals(enumItem)) {
                    enumIndex = index;
                }
                index++;
            }
            //If current value is not found in the enumValue of its domain
            if (enumIndex == -1) {
                return false;
            }

            //If enumIndex is properly found
            else {
                //If it is out of domain
                if (enumIndex + diff >= varDomain.getDomainEnumVal().size() || enumIndex + diff < 0) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    @Override
    public boolean updateValueOfDim(int diff) {
        int enumIndex = -1;
        int index = 0;

        for (String enumItem : varDomain.getDomainEnumVal()) {
            if (stringData.equals(enumItem)) {
                enumIndex = index;
            }
            index++;
        }

        int newEnumIndex;
        newEnumIndex = enumIndex + diff;

        if (checkUpdateValid(diff)) {
            //Update the value
            this.setDataCurValue(varDomain.getDomainEnumVal().get(newEnumIndex));
            return true;
        }else{
            return false;
        }

//        return true;
    }
}
