
package org.synyx.beanfiller.testobjects.cycling;

/**
 * Test Object for testing with deeper cycles.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class CyclicObject2 {

    private CyclicObject3 cyclicObject3;

    public CyclicObject3 getCyclicObject3() {

        return cyclicObject3;
    }


    public void setCyclicObject3(CyclicObject3 cyclicObject3) {

        this.cyclicObject3 = cyclicObject3;
    }
}
