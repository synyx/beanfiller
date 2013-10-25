package org.synyx.beanfiller.testobjects.cycling;

/**
 * Test Object for testing with deeper cycles.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class CyclicObject3 {

    private CyclicObject4 cyclicObject4;

    public CyclicObject4 getCyclicObject4() {

        return cyclicObject4;
    }


    public void setCyclicObject4(CyclicObject4 cyclicObject4) {

        this.cyclicObject4 = cyclicObject4;
    }
}
