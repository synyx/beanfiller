package org.synyx.beanfiller.criteria;

/**
 * Abstract superclass for all Number Criteria.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public abstract class NumberCriteria<T extends Number> {

    private T min;
    private T max;

    /**
     * @param  min  minimum value of the created number
     * @param  max  maximum value of the created number
     */
    protected NumberCriteria(T min, T max) {

        this.min = min;
        this.max = max;
    }

    /**
     * Get the maximum value the created number should have.
     *
     * @return  maximum value
     */
    public T getMax() {

        return max;
    }


    /**
     * Get the minimum value the created number should have.
     *
     * @return  minimum value
     */
    public T getMin() {

        return min;
    }


    /**
     * Set the maximum value the created number should have.
     *
     * @param  max  maximum value of the created number.
     */
    public void setMax(T max) {

        this.max = max;
    }


    /**
     * Set the minimum value the created number should have.
     *
     * @param  min  minimum value of the created number
     */
    public void setMin(T min) {

        this.min = min;
    }
}
