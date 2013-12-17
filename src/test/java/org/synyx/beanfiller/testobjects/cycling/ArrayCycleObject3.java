package org.synyx.beanfiller.testobjects.cycling;

/**
 * Test Object for testing cyclic dependencies with only Arrays.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ArrayCycleObject3 {

    private ArrayCycleObject3[] arrayCycleObject3Array;

    public ArrayCycleObject3[] getArrayCycleObject3Array() {

        return arrayCycleObject3Array;
    }


    public void setArrayCycleObject3Array(ArrayCycleObject3[] arrayCycleObject3Array) {

        this.arrayCycleObject3Array = arrayCycleObject3Array;
    }
}
