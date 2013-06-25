package org.synyx.beanfiller.criteria;

import java.math.BigDecimal;


/**
 * Criteria used for BigDecimals.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BigDecimalCriteria extends DecimalsNumberCriteria<BigDecimal> {

    /**
     * Create a new BigDecimalCriteria with the default values.
     */
    public BigDecimalCriteria() {

        this(BigDecimal.ZERO, BigDecimal.valueOf(12.12), 2);
    }


    /**
     * Create a new BigDecimalCriteria with the given values.
     *
     * @param  min  of the created number
     * @param  max  of the created number
     * @param  numberOfDecimals  of the created number
     */
    public BigDecimalCriteria(BigDecimal min, BigDecimal max, int numberOfDecimals) {

        super(min, max, numberOfDecimals);
    }
}
