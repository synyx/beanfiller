package org.synyx.beanfiller.testobjects;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Date;


/**
 * Object for testing the basic Object types.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BaseObject {

    private String text;
    private Integer integerNumber;
    private int primitiveIntNumber;
    private Float floatNumber;
    private float primitiveFloatNumber;
    private Long longNumber;
    private long primitiveLongNumber;
    private Double doubleNumber;
    private double primitiveDoubleNumber;
    private Boolean booleanObject;
    private boolean primitiveBoolean;
    private Byte byteObject;
    private byte primitiveByte;

    private BigInteger bigIntegerNumber;
    private BigDecimal bigDecimalNumber;

    private Date date;

    public String getText() {

        return text;
    }


    public void setText(String text) {

        this.text = text;
    }


    public Integer getIntegerNumber() {

        return integerNumber;
    }


    public void setIntegerNumber(Integer integerNumber) {

        this.integerNumber = integerNumber;
    }


    public int getPrimitiveIntNumber() {

        return primitiveIntNumber;
    }


    public void setPrimitiveIntNumber(int primitiveIntNumber) {

        this.primitiveIntNumber = primitiveIntNumber;
    }


    public Float getFloatNumber() {

        return floatNumber;
    }


    public void setFloatNumber(Float floatNumber) {

        this.floatNumber = floatNumber;
    }


    public float getPrimitiveFloatNumber() {

        return primitiveFloatNumber;
    }


    public void setPrimitiveFloatNumber(float primitiveFloatNumber) {

        this.primitiveFloatNumber = primitiveFloatNumber;
    }


    public Long getLongNumber() {

        return longNumber;
    }


    public void setLongNumber(Long longNumber) {

        this.longNumber = longNumber;
    }


    public long getPrimitiveLongNumber() {

        return primitiveLongNumber;
    }


    public void setPrimitiveLongNumber(long primitiveLongNumber) {

        this.primitiveLongNumber = primitiveLongNumber;
    }


    public Double getDoubleNumber() {

        return doubleNumber;
    }


    public void setDoubleNumber(Double doubleNumber) {

        this.doubleNumber = doubleNumber;
    }


    public double getPrimitiveDouble() {

        return primitiveDoubleNumber;
    }


    public void setPrimitiveDoubleNumber(double primitiveDoubleNumber) {

        this.primitiveDoubleNumber = primitiveDoubleNumber;
    }


    public Boolean getBooleanObject() {

        return booleanObject;
    }


    public void setBooleanObject(Boolean booleanObject) {

        this.booleanObject = booleanObject;
    }


    public boolean getPrimitiveBoolean() {

        return primitiveBoolean;
    }


    public void setPrimitiveBoolean(boolean primitiveBoolean) {

        this.primitiveBoolean = primitiveBoolean;
    }


    public Byte getByteObject() {

        return byteObject;
    }


    public void setByteObject(Byte byteObject) {

        this.byteObject = byteObject;
    }


    public byte getPrimitiveByte() {

        return primitiveByte;
    }


    public void setPrimitiveByte(byte primitiveByte) {

        this.primitiveByte = primitiveByte;
    }


    public BigInteger getBigIntegerNumber() {

        return bigIntegerNumber;
    }


    public void setBigIntegerNumber(BigInteger bigIntegerNumber) {

        this.bigIntegerNumber = bigIntegerNumber;
    }


    public BigDecimal getBigDecimalNumber() {

        return bigDecimalNumber;
    }


    public void setBigDecimalNumber(BigDecimal bigDecimalNumber) {

        this.bigDecimalNumber = bigDecimalNumber;
    }


    public Date getDate() {

        return date;
    }


    public void setDate(Date date) {

        this.date = date;
    }
}
