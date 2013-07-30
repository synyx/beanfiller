package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.BigIntegerCriteria;
import org.synyx.beanfiller.criteria.NumberCriteria;
import org.synyx.beanfiller.util.RandomGenerator;

import java.math.BigInteger;


/**
 * Creator for BigIntegers.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BigIntegerCreator implements SimpleCreator<BigInteger> {

    private final NumberCriteria<BigInteger> criteria;

    /**
     * Create a new BigIntegerCreator with the default BigIntegerCriteria.
     */
    public BigIntegerCreator() {

        this(new BigIntegerCriteria());
    }


    /**
     * Create a new BigIntegerCreator with the given criteria.
     *
     * @param  criteria  the criteria to use.
     */
    public BigIntegerCreator(NumberCriteria<BigInteger> criteria) {

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
