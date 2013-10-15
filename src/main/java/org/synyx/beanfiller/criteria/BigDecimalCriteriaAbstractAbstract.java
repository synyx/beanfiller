package org.synyx.beanfiller.criteria;

import java.math.BigDecimal;


/**
 * Criteria used for BigDecimals.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BigDecimalCriteriaAbstractAbstract extends AbstractDecimalsAbstractNumberCriteria<BigDecimal> {

    private static final double MAXIMUM = 12.12;
    private static final int NUMBER_OF_DECIMALS = 2;

    /**
     * Create a new BigDecimalCriteriaAbstractAbstract with the default values.
     */
    public BigDecimalCriteriaAbstractAbstract() {

        this(BigDecimal.ZERO, BigDecimal.valueOf(MAXIMUM), NUMBER_OF_DECIMALS);
    }


    /**
     * Create a new BigDecimalCriteriaAbstractAbstract with the given values.
     *
     * @param  min  of the created number
     * @param  max  of the created number
     * @param  numberOfDecimals  of the created number
     */
    public BigDecimalCriteriaAbstractAbstract(BigDecimal min, BigDecimal max, int numberOfDecimals) {

        super(min, max, numberOfDecimals);
    }
}
