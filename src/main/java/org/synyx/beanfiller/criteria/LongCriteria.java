
package org.synyx.beanfiller.criteria;

/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class LongCriteria implements Criteria<Integer> {

    private long max;
    private long min;

    public LongCriteria() {

        this(0, 1000);
    }


    public LongCriteria(long min, long max) {

        this.max = max;
        this.min = min;
    }

    public long getMax() {

        return max;
    }


    public void setMax(long max) {

        this.max = max;
    }


    public long getMin() {

        return min;
    }


    public void setMin(long min) {

        this.min = min;
    }
}
