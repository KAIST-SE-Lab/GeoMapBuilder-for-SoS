package kr.ac.kaist.se.scenario_example;

import kr.ac.kaist.se.model.sos.domain.DimVarDomain;
import kr.ac.kaist.se.model.sos.var.DimVar;

/**
 * Dimension to represent coordinates of a map
 *
 * @author ymbaek
 */
public class MapCoordinateDimType extends DimVar {

    public MapCoordinateDimType(String varId,
                                String varName,
                                String varType,
                                DimVarDomain varDomain) {
        super(varId, varName, varType, varDomain);
    }

    public MapCoordinateDimType(String varId,
                                String varName,
                                String varType,
                                String dataDefaultValue,
                                String dataCurValue,
                                DimVarDomain varDomain) {
        super(varId, varName, varType, dataDefaultValue, dataCurValue, varDomain);
    }

    @Override
    public boolean checkUpdateValid(int diff) {
        int newValue = Integer.parseInt(this.getDataCurValue());
        newValue += diff;

        //Since MapCoordinateDimensionType has integer dataType, it returns false if it is not met.
        if (integerData != null) {
            //If newValue is out of range of varDomain
            return !(newValue < varDomain.getDomainMinVal()) && !(newValue > varDomain.getDomainMaxVal());
        } else {
            return false;
        }
    }

    @Override
    public boolean updateValueOfDim(int diff) {
        int newValue = Integer.parseInt(this.getDataCurValue());
        newValue += diff;

        //TODO: Value checking
        //System.out.println(varDomain.isValidValue(newValue));

        //Since MapCoordinateDimensionType has integer dataType, it returns false if it is not met.
        if (integerData != null) {

            //If newValue is out of range of varDomain
            if (newValue < varDomain.getDomainMinVal() || newValue > varDomain.getDomainMaxVal()) {
                return false;
            } else {
                this.setDataCurValue(newValue + "");
            }

        } else {
            return false;
        }
        return true;
    }
}
