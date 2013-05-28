
package org.synyx.beanfiller.criteria;

import java.math.BigInteger;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class BigIntegerCriteria implements Criteria<BigInteger> {

    private BigInteger max;
    private BigInteger min;

    public BigIntegerCriteria() {

        this(BigInteger.ZERO, BigInteger.TEN);
    }


    public BigIntegerCriteria(BigInteger min, BigInteger max) {

        this.max = max;
        this.min = min;
    }

    public BigInteger getMax() {

        return max;
    }


    public void setMax(BigInteger max) {

        this.max = max;
    }


    public BigInteger getMin() {

        return min;
    }


    public void setMin(BigInteger min) {

        this.min = min;
    }
}
