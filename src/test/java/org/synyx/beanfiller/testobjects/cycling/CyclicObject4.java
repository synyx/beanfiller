package org.synyx.beanfiller.testobjects.cycling;

/**
 * Test Object for testing with deeper cycles.
 *
 * @author  Tobias Knell - knell@synyx.des
 */
public class CyclicObject4 {

    private CyclicObject2 cyclicObject2;

    public CyclicObject2 getCyclicObject2() {

        return cyclicObject2;
    }


    public void setCyclicObject2(CyclicObject2 cyclicObject2) {

        this.cyclicObject2 = cyclicObject2;
    }
}
