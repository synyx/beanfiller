
package org.synyx.beanfiller.criteria;

import java.math.BigInteger;


/**
 * Criteria for BigIntegers.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BigIntegerCriteriaAbstract extends AbstractNumberCriteria<BigInteger> {

    /**
     * Create a new BigIntegerCriteriaAbstract with the default values.
     */
    public BigIntegerCriteriaAbstract() {

        this(BigInteger.ZERO, BigInteger.TEN);
    }


    /**
     * Create a new BigIntegerCriteriaAbstract with the given values.
     *
     * @param  min  of the created number
     * @param  max  of the created number
     */
    public BigIntegerCriteriaAbstract(BigInteger min, BigInteger max) {

        super(min, max);
    }
}
