package org.synyx.beanfiller.testobjects;

/**
 * @author  Tobias Knell - knell@synyx.de
 */
public enum TestEnum {

    TEST1("1"),
    TEST2("2");

    private String value;

    TestEnum(String value) {

        this.value = value;
    }

    public String getValue() {

        return value;
    }


    public void setValue(String value) {

        this.value = value;
    }
}
