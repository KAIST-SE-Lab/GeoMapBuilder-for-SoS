package kr.ac.kaist.se.model.sos.domain;

import kr.ac.kaist.se.model.abst.var.EnumDomainType;
import kr.ac.kaist.se.model.abst.var._SimDataDomain_;

import java.util.ArrayList;

/**
 * SimDataDomain to constrain a DataVar.
 * @author ymbaek
 */
public class DataVarDomain extends _SimDataDomain_ {

    //TODO: Specialize DataVarDomain

    public DataVarDomain(EnumDomainType domainType, float domainMinVal, float domainMaxVal) {
        super(domainType, domainMinVal, domainMaxVal);
    }

    public DataVarDomain(EnumDomainType domainType, ArrayList<String> domainEnumVal) {
        super(domainType, domainEnumVal);
    }
}
