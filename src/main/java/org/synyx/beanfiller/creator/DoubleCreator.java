
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.DecimalsNumberCriteria;
import org.synyx.beanfiller.criteria.DoubleCriteria;
import org.synyx.beanfiller.services.RandomGenerator;

import java.math.BigDecimal;


/**
 * Creator for Doubles.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class DoubleCreator implements SimpleCreator<Double> {

    private DecimalsNumberCriteria<Double> criteria;

    /**
     * Create a new DoubleCreator with the default DoubleCriteria.
     */
    public DoubleCreator() {

        this(new DoubleCriteria());
    }


    /**
     * Create a new DoubleCreator with the given criteria.
     *
     * @param  criteria  the criteria to use.
     */
    public DoubleCreator(DecimalsNumberCriteria<Double> criteria) {

        this.criteria = criteria;
    }

    @Override
    public Double create() {

        double d = RandomGenerator.getRandomDouble() * (criteria.getMax() - criteria.getMin()) + criteria.getMin();

        d = BigDecimal.valueOf(d).setScale(criteria.getNumberOfDecimals(), BigDecimal.ROUND_HALF_UP).doubleValue();

        return d;
    }
}
