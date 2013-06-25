
package org.synyx.beanfiller.criteria;

/**
 * Criteria for Floats.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class FloatCriteria extends DecimalsNumberCriteria<Float> {

    /**
     * Create a new FloatCriteria with the default values.
     */
    public FloatCriteria() {

        this(0f, 1f, 2);
    }


    /**
     * Create a new FloatCriteria with the given values.
     *
     * @param  min  of the created number
     * @param  max  of the created number
     * @param  numberOfDecimals  of the created number
     */
    public FloatCriteria(Float min, Float max, int numberOfDecimals) {

        super(min, max, numberOfDecimals);
    }
}
