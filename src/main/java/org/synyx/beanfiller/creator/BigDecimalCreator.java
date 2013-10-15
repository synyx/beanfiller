package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.AbstractDecimalsAbstractNumberCriteria;
import org.synyx.beanfiller.criteria.BigDecimalCriteriaAbstractAbstract;
import org.synyx.beanfiller.util.RandomGenerator;

import java.math.BigDecimal;


/**
 * Creator for BigDecimals.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BigDecimalCreator implements SimpleCreator<BigDecimal> {

    private final AbstractDecimalsAbstractNumberCriteria<BigDecimal> criteria;

    /**
     * Create a new BigDecimalCreator with the default BigDecimalCriteriaAbstractAbstract.
     */
    public BigDecimalCreator() {

        this(new BigDecimalCriteriaAbstractAbstract());
    }


    /**
     * Create a new BigDecimalCreator with the given criteria.
     *
     * @param  criteria  the criteria to use.
     */
    public BigDecimalCreator(AbstractDecimalsAbstractNumberCriteria<BigDecimal> criteria) {

        this.criteria = criteria;
    }

    @Override
    public BigDecimal create() {

        BigDecimal min = criteria.getMin();
        BigDecimal max = criteria.getMax();

        BigDecimal range = max.subtract(min);

        return min.add(range.multiply(BigDecimal.valueOf(RandomGenerator.getRandomDouble())));
    }
}
