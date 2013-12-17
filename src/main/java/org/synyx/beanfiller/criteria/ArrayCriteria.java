package org.synyx.beanfiller.criteria;

/**
 * Criteria for Arrays.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ArrayCriteria implements Criteria<Object> {

    private int size;

    /**
     * Creates new ArrayCriteria using the default values.
     */
    public ArrayCriteria() {

        this.size = 3;
    }


    /**
     * Creates new ArrayCriteria using the given values.
     *
     * @param  size  size of the created Arrays.
     */
    public ArrayCriteria(int size) {

        this.size = size;
    }

    /**
     * @return  size of the created Arrays.
     */
    public int getSize() {

        return size;
    }


    /**
     * @param  size  size of the created Arrays.
     */
    public void setSize(int size) {

        this.size = size;
    }
}
