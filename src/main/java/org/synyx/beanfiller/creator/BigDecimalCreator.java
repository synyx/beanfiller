package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.BigDecimalCriteria;
import org.synyx.beanfiller.criteria.DecimalsNumberCriteria;
import org.synyx.beanfiller.util.RandomGenerator;

import java.math.BigDecimal;


/**
 * Creator for BigDecimals.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BigDecimalCreator implements SimpleCreator<BigDecimal> {

    private final RandomGenerator randomGenerator;
    private final DecimalsNumberCriteria<BigDecimal> criteria;

    /**
     * Create a new BigDecimalCreator with the default BigDecimalCriteria.
     *
     * @param  randomGenerator
     */
    public BigDecimalCreator(RandomGenerator randomGenerator) {

        this(randomGenerator, new BigDecimalCriteria());
    }


    /**
     * Create a new BigDecimalCreator with the given criteria.
     *
     * @param  criteria  the criteria to use.
     */
    public BigDecimalCreator(RandomGenerator randomGenerator, DecimalsNumberCriteria<BigDecimal> criteria) {

        this.randomGenerator = randomGenerator;

        this.criteria = criteria;
    }

    @Override
    public BigDecimal create() {

        BigDecimal min = criteria.getMin();
        BigDecimal max = criteria.getMax();

        BigDecimal range = max.subtract(min);

        return min.add(range.multiply(BigDecimal.valueOf(randomGenerator.getRandomDouble())));
    }
}
