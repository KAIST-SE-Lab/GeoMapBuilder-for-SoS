package kr.ac.kaist.se.model.abst.var;

import java.sql.Timestamp;

/**
 * Abstract class to represent a data variable.
 * (Based on definitions from SIMVA-SoS Modeler and SIMVA-SoS Lite)
 * @author ymbaek
 */
public abstract class _SimDataVariable_ implements Cloneable {
    protected String varId;        //id of a data (variable)
    protected String varName;      //name of a data (variable)
    protected String varType;      //type of a data (variable)

    protected Integer integerData;
    protected Float floatData;
    protected String stringData;    //String-type or Enum data

    private String dataDefaultValue;  //default value of a data (variable)
    private String dataCurValue;      //current value of a data (variable)

    public _SimDataVariable_(String varId,
                             String varName,
                             String varType,
                             String dataDefaultValue,
                             String dataCurValue) {
        this.varId = varId;
        this.varName = varName;
        this.varType = varType;
        this.dataDefaultValue = dataDefaultValue;
        this.dataCurValue = dataCurValue;

        setActualDataTypeVar(this.varType);
    }



    /**
     * A method to cast String data to data of actual type.
     * This method is called in constructors.
     *
     * @param dataType Data type of this _SimDataVariable_
     */
    private void setActualDataTypeVar(String dataType) {

        if (dataCurValue != null) {
            //Integer data
            if (dataType.equals("INT") ||
                    dataType.equals("int") ||
                    dataType.equals("Int") ||
                    dataType.equals("INTEGER") ||
                    dataType.equals("integer")) {
                integerData = Integer.valueOf(dataCurValue);
            }
            //Float data
            else if (dataType.equals("FLOAT") ||
                    dataType.equals("float") ||
                    dataType.equals("Float") ||
                    dataType.equals("DOUBLE") ||
                    dataType.equals("double") ||
                    dataType.equals("Double")) {
                floatData = Float.valueOf(dataCurValue);
            }
            //String data
            else if (dataType.equals("STRING") ||
                    dataType.equals("string") ||
                    dataType.equals("String") ||
                    dataType.equals("STR") ||
                    dataType.equals("str")) {
                stringData = dataCurValue;
            }
            //Enumeration / Enumeration String data
            else if (dataType.equals("ENUM") ||
                    dataType.equals("enum") ||
                    dataType.equals("Enum") ||
                    dataType.equals("ENUMSTRING") ||
                    dataType.equals("enumstring") ||
                    dataType.equals("EnumString")) {
                stringData = dataCurValue;
            }
        } else {

        }
    }


    /* Getters & Setters */

    public String getVarId() {
        return varId;
    }

    public void setVarId(String varId) {
        this.varId = varId;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getVarType() {
        return varType;
    }

    public void setVarType(String varType) {
        this.varType = varType;
    }

    public String getDataDefaultValue() {
        return dataDefaultValue;
    }

    public void setDataDefaultValue(String dataDefaultValue) {
        this.dataDefaultValue = dataDefaultValue;
    }

    public String getDataCurValue() {
        return dataCurValue;
    }

    public void setDataCurValue(String dataCurValue) {
        this.dataCurValue = dataCurValue;

        //Update actual data type according to its dataType
        setActualDataTypeVar(this.varType);
    }


    /**
     * An implemented method of Cloneable interface
     * @return cloned object of this class
     */
    public _SimDataVariable_ clone() {
        _SimDataVariable_ dimVar = null;

        try {
            dimVar = (_SimDataVariable_) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return dimVar;
    }
}
