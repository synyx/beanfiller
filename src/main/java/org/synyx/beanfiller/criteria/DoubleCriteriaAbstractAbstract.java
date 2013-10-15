package org.synyx.beanfiller.criteria;

/**
 * Criteria for Doubles.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class DoubleCriteriaAbstractAbstract extends AbstractDecimalsAbstractNumberCriteria<Double> {

    private static final double MIN = 0d;
    private static final double MAX = 1d;
    private static final int NUMBER_OF_DECIMALS = 2;

    /**
     * Create a new DoubleCriteriaAbstractAbstract with the default values.
     */
    public DoubleCriteriaAbstractAbstract() {

        this(MIN, MAX, NUMBER_OF_DECIMALS);
    }


    /**
     * Create a new DoubleCriteriaAbstractAbstract with the given values.
     *
     * @param  min  of the created number
     * @param  max  of the created number
     * @param  numberOfDecimals  of the created number
     */
    public DoubleCriteriaAbstractAbstract(Double min, Double max, int numberOfDecimals) {

        super(min, max, numberOfDecimals);
    }
}
