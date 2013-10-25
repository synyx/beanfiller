package org.synyx.beanfiller.testobjects.cycling;

/**
 * Test Object for testing cyclic dependencies with Arrays.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ArrayCycleObject {

    private CyclicObject2[] cyclicObject2array;

    public CyclicObject2[] getCyclicObject2array() {

        return cyclicObject2array;
    }


    public void setCyclicObject2array(CyclicObject2[] cyclicObject2array) {

        this.cyclicObject2array = cyclicObject2array;
    }
}
