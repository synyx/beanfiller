
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.BigIntegerCriteria;
import org.synyx.beanfiller.services.RandomGenerator;

import java.math.BigInteger;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class BigIntegerCreator implements SimpleCreator<BigInteger> {

    private final BigIntegerCriteria criteria;

    public BigIntegerCreator() {

        this(new BigIntegerCriteria());
    }


    public BigIntegerCreator(BigIntegerCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public BigInteger create() {

        BigInteger b;

        do {
            b = RandomGenerator.getRandomBigInteger(criteria.getMax().bitLength());
        } while (b.compareTo(criteria.getMax()) >= 0);

        return b;
    }
}
