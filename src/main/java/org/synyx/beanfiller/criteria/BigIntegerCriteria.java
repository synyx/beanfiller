
package org.synyx.beanfiller.criteria;

import java.math.BigInteger;


/**
 * Criteria for BigIntegers.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BigIntegerCriteria extends NumberCriteria<BigInteger> {

    /**
     * Create a new BigIntegerCriteria with the default values.
     */
    public BigIntegerCriteria() {

        this(BigInteger.ZERO, BigInteger.TEN);
    }


    /**
     * Create a new BigIntegerCriteria with the given values.
     *
     * @param  min  of the created number
     * @param  max  of the created number
     */
    public BigIntegerCriteria(BigInteger min, BigInteger max) {

        super(min, max);
    }
}
