package org.synyx.beanfiller.testobjects.cycling;

/**
 * Test Object with cyclic dependencies to itself.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class CyclicObject {

    private CyclicObject cycle;

    public CyclicObject getCycle() {

        return cycle;
    }


    public void setCycle(CyclicObject cycle) {

        this.cycle = cycle;
    }
}
