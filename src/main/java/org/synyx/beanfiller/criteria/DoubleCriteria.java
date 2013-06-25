package org.synyx.beanfiller.criteria;

/**
 * Criteria for Doubles.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class DoubleCriteria extends DecimalsNumberCriteria<Double> {

    /**
     * Create a new DoubleCriteria with the default values.
     */
    public DoubleCriteria() {

        this(0d, 1d, 2);
    }


    /**
     * Create a new DoubleCriteria with the given values.
     *
     * @param  min  of the created number
     * @param  max  of the created number
     * @param  numberOfDecimals  of the created number
     */
    public DoubleCriteria(Double min, Double max, int numberOfDecimals) {

        super(min, max, numberOfDecimals);
    }
}
