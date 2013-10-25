package org.synyx.beanfiller.testobjects.cycling;

/**
 * Test Object for testing cyclic dependencies with only Arrays.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ArrayCycleObject4 {

    private ArrayCycleObject2[] arrayCycleObject2Array;

    public ArrayCycleObject2[] getArrayCycleObject2Array() {

        return arrayCycleObject2Array;
    }


    public void setArrayCycleObject2Array(ArrayCycleObject2[] arrayCycleObject2Array) {

        this.arrayCycleObject2Array = arrayCycleObject2Array;
    }
}
