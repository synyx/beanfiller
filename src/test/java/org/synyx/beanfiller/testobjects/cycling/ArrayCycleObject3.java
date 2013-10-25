package org.synyx.beanfiller.testobjects.cycling;

/**
 * Test Object for testing cyclic dependencies with only Arrays.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ArrayCycleObject3 {

    private ArrayCycleObject3[] arrayCycleObject3Arrray;

    public ArrayCycleObject3[] getArrayCycleObject3Arrray() {

        return arrayCycleObject3Arrray;
    }


    public void setArrayCycleObject3Arrray(ArrayCycleObject3[] arrayCycleObject3Arrray) {

        this.arrayCycleObject3Arrray = arrayCycleObject3Arrray;
    }
}
