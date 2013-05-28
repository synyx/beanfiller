
package org.synyx.beanfiller.criteria;

/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class FloatCriteria implements Criteria<Float> {

    private float max;
    private float min;
    private int decimals;

    public FloatCriteria() {

        this(0f, 1, 2);
    }


    public FloatCriteria(float min, float max, int decimals) {

        this.max = max;
        this.min = min;
        this.decimals = decimals;
    }

    public float getMax() {

        return max;
    }


    public void setMax(float max) {

        this.max = max;
    }


    public float getMin() {

        return min;
    }


    public void setMin(float min) {

        this.min = min;
    }


    public int getDecimals() {

        return decimals;
    }


    public void setDecimals(int decimals) {

        this.decimals = decimals;
    }
}
