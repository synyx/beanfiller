
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.AbstractDecimalsAbstractNumberCriteria;
import org.synyx.beanfiller.criteria.DoubleCriteriaAbstractAbstract;
import org.synyx.beanfiller.util.RandomGenerator;

import java.math.BigDecimal;


/**
 * Creator for Doubles.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class DoubleCreator implements SimpleCreator<Double> {

    private final AbstractDecimalsAbstractNumberCriteria<Double> criteria;

    /**
     * Create a new DoubleCreator with the default DoubleCriteriaAbstractAbstract.
     */
    public DoubleCreator() {

        this(new DoubleCriteriaAbstractAbstract());
    }


    /**
     * Create a new DoubleCreator with the given criteria.
     *
     * @param  criteria  the criteria to use.
     */
    public DoubleCreator(AbstractDecimalsAbstractNumberCriteria<Double> criteria) {

        this.criteria = criteria;
    }

    @Override
    public Double create() {

        double d = RandomGenerator.getRandomDouble() * (criteria.getMax() - criteria.getMin()) + criteria.getMin();

        d = BigDecimal.valueOf(d).setScale(criteria.getNumberOfDecimals(), BigDecimal.ROUND_HALF_UP).doubleValue();

        return d;
    }
}
