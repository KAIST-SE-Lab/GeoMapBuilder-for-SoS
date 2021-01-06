package kr.ac.kaist.se.model.sos.var;

import kr.ac.kaist.se.model.abst.var._SimDataVariable_;
import kr.ac.kaist.se.model.sos.domain.DataVarDomain;

/**
 * Concrete class to define information of a geolocation
 *
 * @author ymbaek
 */
public class DataVar extends _SimDataVariable_ {

    //Domain of a dimension variable
    protected DataVarDomain varDomain;

    public DataVar(String varId,
                   String varName,
                   String varType,
                   String dataDefaultValue,
                   String dataCurValue,
                   DataVarDomain varDomain) {
        super(varId, varName, varType, dataDefaultValue, dataCurValue);
        this.varDomain = varDomain;
    }


    /* Getters & Setters */

    public DataVarDomain getVarDomain() {
        return varDomain;
    }

    public void setVarDomain(DataVarDomain varDomain) {
        this.varDomain = varDomain;
    }
}
