package kr.ac.kaist.se.model.abst.var;

import java.util.ArrayList;

/**
 * Abstract class to represent a domain of _SimDataVariable_.
 * Here, the domain generally refers to the scope of data value.
 * (Based on definitions from SIMVA-SoS Modeler and SIMVA-SoS Lite)
 * @author ymbaek
 */
public abstract class _SimDataDomain_ {

    protected EnumDomainType domainType;        //Type of domain

    protected float domainMinVal = -99999;      //Minimum value
    protected float domainMaxVal = 99999;       //Maximum value

    protected ArrayList<String> domainEnumVal = new ArrayList<>();  //Allowed enumeration values

    /* Domain for numerical DataVariable */
    public _SimDataDomain_(EnumDomainType domainType, float domainMinVal, float domainMaxVal) {
        this.domainType = domainType;
        this.domainMinVal = domainMinVal;
        this.domainMaxVal = domainMaxVal;
    }

    /* Domain for enumeration DataVariable */
    public _SimDataDomain_(EnumDomainType domainType, ArrayList<String> domainEnumVal) {
        this.domainType = domainType;
        this.domainEnumVal = domainEnumVal;
    }



    /**
     * A method to check if a given value is valid according to this domain.
     * This method is overloaded according to the data type of the value.
     *
     * @return true if a given value is valid
     */
    /* For integer variable */
    public boolean isValidValue(int value) {
        if (domainType == EnumDomainType.VALUE_RANGE_DISCRETE) {
            //If the value is lower than domainMinVal or exceeds domainMaxVal
            return value >= domainMinVal && value <= domainMaxVal;
        }
        //Wrong domainType
        else {
            return false;
        }
    }

    /* For float variable */
    public boolean isValidValue(float value) {
        if (domainType == EnumDomainType.VALUE_RANGE_CONTINUOUS) {
            //If the value is lower than domainMinVal or exceeds domainMaxVal
            return value >= domainMinVal && value <= domainMaxVal;
        }
        //Wrong domainType
        else {
            return false;
        }
    }

    /* For enumeration variable */
    public boolean isValidValue(String value) {
        boolean isAllowed = false;
        if (domainType == EnumDomainType.ENUMERATION) {
            //If the value is lower than domainMinVal or exceeds domainMaxVal
            for (String allowedString : domainEnumVal) {
                if (value.equals(allowedString)) {
                    isAllowed = true;
                }
            }
        }
        //Wrong domainType
        else {
            return false;
        }
        return isAllowed;
    }


    /* Getters & Setters */

    public EnumDomainType getDomainType() {
        return domainType;
    }

    public void setDomainType(EnumDomainType domainType) {
        this.domainType = domainType;
    }

    public float getDomainMinVal() {
        return domainMinVal;
    }

    public void setDomainMinVal(float domainMinVal) {
        this.domainMinVal = domainMinVal;
    }

    public float getDomainMaxVal() {
        return domainMaxVal;
    }

    public void setDomainMaxVal(float domainMaxVal) {
        this.domainMaxVal = domainMaxVal;
    }

    public ArrayList<String> getDomainEnumVal() {
        return domainEnumVal;
    }

    public void setDomainEnumVal(ArrayList<String> domainEnumVal) {
        this.domainEnumVal = domainEnumVal;
    }
}
