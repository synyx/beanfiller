package org.synyx.beanfiller.testobjects;

public class CopyConstructorObjectWithPrivateConstructor {
    public static final String COPY_CONSTRUCTOR_USED_VALUE = "copy constructor used";

    private String value;

    private CopyConstructorObjectWithPrivateConstructor(){
        // private for filling
    }

    public CopyConstructorObjectWithPrivateConstructor(CopyConstructorObjectWithPrivateConstructor copy){
        this.value = COPY_CONSTRUCTOR_USED_VALUE;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
