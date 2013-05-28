
package org.synyx.beanfiller.testobjects;

import java.util.List;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class ArraysObject {

    private String[] stringArray;
    private int[] intArray;
    private BaseObject[] objectArray;
    private TestEnum[] enumArray;
    private List<String>[] listArray;

    public String[] getStringArray() {

        return stringArray;
    }


    public void setStringArray(String[] stringArray) {

        this.stringArray = stringArray;
    }


    public int[] getIntArray() {

        return intArray;
    }


    public void setIntArray(int[] intArray) {

        this.intArray = intArray;
    }


    public BaseObject[] getObjectArray() {

        return objectArray;
    }


    public void setObjectArray(BaseObject[] objectArray) {

        this.objectArray = objectArray;
    }


    public TestEnum[] getEnumArray() {

        return enumArray;
    }


    public void setEnumArray(TestEnum[] enumArray) {

        this.enumArray = enumArray;
    }


    public List<String>[] getListArray() {

        return listArray;
    }


    public void setListArray(List<String>[] listArray) {

        this.listArray = listArray;
    }
}
