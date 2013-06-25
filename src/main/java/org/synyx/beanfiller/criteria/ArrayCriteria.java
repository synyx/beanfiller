package org.synyx.beanfiller.criteria;

/**
 * Criteria for Arrays.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ArrayCriteria implements Criteria<Object> {

    private int size;

    public ArrayCriteria() {

        this.size = 3;
    }


    public ArrayCriteria(int size) {

        this.size = size;
    }

    public int getSize() {

        return size;
    }


    public void setSize(int size) {

        this.size = size;
    }
}
