package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.AbstractNumberCriteria;
import org.synyx.beanfiller.criteria.BigIntegerCriteriaAbstract;
import org.synyx.beanfiller.util.RandomGenerator;

import java.math.BigInteger;


/**
 * Creator for BigIntegers.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BigIntegerCreator implements SimpleCreator<BigInteger> {

    private final AbstractNumberCriteria<BigInteger> criteria;

    /**
     * Create a new BigIntegerCreator with the default BigIntegerCriteriaAbstract.
     */
    public BigIntegerCreator() {

        this(new BigIntegerCriteriaAbstract());
    }


    /**
     * Create a new BigIntegerCreator with the given criteria.
     *
     * @param  criteria  the criteria to use.
     */
    public BigIntegerCreator(AbstractNumberCriteria<BigInteger> criteria) {

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
