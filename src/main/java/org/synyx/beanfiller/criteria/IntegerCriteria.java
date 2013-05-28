
package org.synyx.beanfiller.criteria;

/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class IntegerCriteria implements Criteria<Integer> {

    private int max;
    private int min;

    public IntegerCriteria() {

        this(0, 1000);
    }


    public IntegerCriteria(int min, int max) {

        this.max = max;
        this.min = min;
    }

    public int getMax() {

        return max;
    }


    public void setMax(int max) {

        this.max = max;
    }


    public int getMin() {

        return min;
    }


    public void setMin(int min) {

        this.min = min;
    }
}
