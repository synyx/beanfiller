
package org.synyx.beanfiller.testobjects;

/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class EnumsObject {

    private TestEnum testEnum;
    private EmptyEnum emptyEnum;

    public TestEnum getTestEnum() {

        return testEnum;
    }


    public void setTestEnum(TestEnum testEnum) {

        this.testEnum = testEnum;
    }


    public EmptyEnum getEmptyEnum() {

        return emptyEnum;
    }


    public void setEmptyEnum(EmptyEnum emptyEnum) {

        this.emptyEnum = emptyEnum;
    }
}
