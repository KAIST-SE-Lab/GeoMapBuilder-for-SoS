package kr.ac.kaist.se.model.sos.var;

import kr.ac.kaist.se.model.abst.var._SimDataVariable_;
import kr.ac.kaist.se.model.sos.domain.DimVarDomain;

/**
 * Abstract class to define a dimension of a geolocation.
 *
 * To enable moving of entities,
 * increaseValueOfDim(..) and decreaseValueOfDim(..) should be implemented
 * to specify how an object can move according to the definition
 *
 * Note that this DimVar class is abstract,
 * so as to allow the definition of abstract classes according to a concrete dimension.
 *
 * @author ymbaek
 */
public abstract class DimVar extends _SimDataVariable_ {

    //Domain of a dimension variable
    protected DimVarDomain varDomain;

    public DimVar(String varId,
                  String varName,
                  String varType,
                  String dataDefaultValue,
                  String dataCurValue,
                  DimVarDomain varDomain) {
        super(varId, varName, varType, dataDefaultValue, dataCurValue);
        this.varDomain = varDomain;
    }


    /* Abstract methods */

    /**
     * A method to check if a new value is valid
     * @param diff difference from current value
     * @return true if a new value is valid
     */
    public abstract boolean checkUpdateValid(int diff);

    /**
     * A method to update value according to a given diff (0, +1, -1, ...)
     * @param diff difference from current value
     * @return true if the update is successfully performed
     */
    public abstract boolean updateValueOfDim(int diff);


    /* Getters & Setters */

    public DimVarDomain getVarDomain() {
        return varDomain;
    }

    public void setVarDomain(DimVarDomain varDomain) {
        this.varDomain = varDomain;
    }

    /**
     * A method to count the number of possible values of this dimVar
     * @return  the number counted
     */
    public int countPossibleValues(){
        //case of integer type
        if (varType == "Int"){
            return (int)varDomain.getDomainMaxVal() - (int)varDomain.getDomainMinVal() + 1;
        }
        //case of enum type
        else{
            return varDomain.getDomainEnumVal().size();
        }
    }

    public String getValueWithIndex(int index){
        //case of integer type
        if (varType == "Int"){
            return (int)varDomain.getDomainMinVal() + index + "";
        }
        //case of enum type
        else{
            return varDomain.getDomainEnumVal().get(index);
        }
    }
}
