
package org.synyx.beanfiller.testobjects;

/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class ObjectWithBeans {

    private BaseObject baseObject;
    private TestEnum testEnum;

    public BaseObject getBaseObject() {

        return baseObject;
    }


    public void setBaseObject(BaseObject baseObject) {

        this.baseObject = baseObject;
    }


    public TestEnum getTestEnum() {

        return testEnum;
    }


    public void setTestEnum(TestEnum testEnum) {

        this.testEnum = testEnum;
    }
}
