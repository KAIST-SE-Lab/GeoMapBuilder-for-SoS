package kr.ac.kaist.se.scenario_example;

import kr.ac.kaist.se.model.sos.domain.DimVarDomain;
import kr.ac.kaist.se.model.sos.var.DimVar;

/**
 * Dimension to represent coordinates (cycle) of a map
 *
 * @author ymbaek
 */
public class MapCoordinateCycleDimType extends DimVar {

    public MapCoordinateCycleDimType(String varId,
                                     String varName,
                                     String varType,
                                     DimVarDomain varDomain) {
        super(varId, varName, varType, varDomain);
    }

    public MapCoordinateCycleDimType(String varId,
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
            if (newValue < varDomain.getDomainMinVal()) {
                //Cycle: min -> max
                newValue = (int) varDomain.getDomainMaxVal();
                this.setDataCurValue(newValue + "");
            } else if (newValue > varDomain.getDomainMaxVal()) {
                //Cycle: max -> min
                newValue = (int) varDomain.getDomainMinVal();
                this.setDataCurValue(newValue + "");
            }
            //If new value is inside of its domain
            else {
                this.setDataCurValue(newValue + "");
            }

        } else {
            return false;
        }
        return true;
    }
}
