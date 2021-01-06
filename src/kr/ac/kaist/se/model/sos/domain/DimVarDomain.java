package kr.ac.kaist.se.model.sos.domain;

import kr.ac.kaist.se.model.abst.var.EnumDomainType;
import kr.ac.kaist.se.model.abst.var._SimDataDomain_;

import java.util.ArrayList;

/**
 * SimDataDomain to constrain a DimVar.
 * @author ymbaek
 */
public class DimVarDomain extends _SimDataDomain_ {

    //TODO: Specialize DimVarDomain

    public DimVarDomain(EnumDomainType domainType, float domainMinVal, float domainMaxVal) {
        super(domainType, domainMinVal, domainMaxVal);
    }

    public DimVarDomain(EnumDomainType domainType, ArrayList<String> domainEnumVal) {
        super(domainType, domainEnumVal);
    }
}
