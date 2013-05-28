
package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.criteria.BigIntegerCriteria;

import java.math.BigInteger;

import java.util.Random;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class BigIntegerBuilder implements Builder<BigInteger> {

    private BigIntegerCriteria criteria;

    public BigIntegerBuilder() {

        this(new BigIntegerCriteria());
    }


    public BigIntegerBuilder(BigIntegerCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public BigInteger build() {

        BigInteger b;

        do {
            b = new BigInteger(criteria.getMax().bitLength(), new Random());
        } while (b.compareTo(criteria.getMax()) >= 0);

        return b;
    }
}
