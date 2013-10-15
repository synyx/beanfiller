
package org.synyx.beanfiller.criteria;

/**
 * Criteria for Floats.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class FloatCriteriaAbstractAbstract extends AbstractDecimalsAbstractNumberCriteria<Float> {

    private static final float MIN = 0f;
    private static final float MAX = 1f;
    private static final int NUMBER_OF_DECIMALS = 2;

    /**
     * Create a new FloatCriteriaAbstractAbstract with the default values.
     */
    public FloatCriteriaAbstractAbstract() {

        this(MIN, MAX, NUMBER_OF_DECIMALS);
    }


    /**
     * Create a new FloatCriteriaAbstractAbstract with the given values.
     *
     * @param  min  of the created number
     * @param  max  of the created number
     * @param  numberOfDecimals  of the created number
     */
    public FloatCriteriaAbstractAbstract(Float min, Float max, int numberOfDecimals) {

        super(min, max, numberOfDecimals);
    }
}
