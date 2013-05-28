
package org.synyx.beanfiller.criteria;

/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class DoubleCriteria implements Criteria<Float> {

    private double max;
    private double min;
    private int decimals;

    public DoubleCriteria() {

        this(0, 1, 2);
    }


    public DoubleCriteria(double min, double max, int decimals) {

        this.max = max;
        this.min = min;
        this.decimals = decimals;
    }

    public double getMax() {

        return max;
    }


    public void setMax(double max) {

        this.max = max;
    }


    public double getMin() {

        return min;
    }


    public void setMin(double min) {

        this.min = min;
    }


    public int getDecimals() {

        return decimals;
    }


    public void setDecimals(int decimals) {

        this.decimals = decimals;
    }
}
