
package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.criteria.BigIntegerCriteria;
import org.synyx.beanfiller.services.RandomGenerator;

import java.math.BigInteger;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class BigIntegerBuilder implements Builder<BigInteger> {

    private final BigIntegerCriteria criteria;

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
            b = RandomGenerator.getRandomBigInteger(criteria.getMax().bitLength());
        } while (b.compareTo(criteria.getMax()) >= 0);

        return b;
    }
}
