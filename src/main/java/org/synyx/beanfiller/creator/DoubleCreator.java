
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.DecimalsNumberCriteria;
import org.synyx.beanfiller.criteria.DoubleCriteria;
import org.synyx.beanfiller.util.RandomGenerator;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * Creator for Doubles.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class DoubleCreator implements SimpleCreator<Double> {

    private final RandomGenerator randomGenerator;
    private final DecimalsNumberCriteria<Double> criteria;

    /**
     * Create a new DoubleCreator with the default DoubleCriteria.
     *
     * @param  randomGenerator
     */
    public DoubleCreator(RandomGenerator randomGenerator) {

        this(randomGenerator, new DoubleCriteria());
    }


    /**
     * Create a new DoubleCreator with the given criteria.
     *
     * @param  criteria  the criteria to use.
     */
    public DoubleCreator(RandomGenerator randomGenerator, DecimalsNumberCriteria<Double> criteria) {

        this.randomGenerator = randomGenerator;
        this.criteria = criteria;
    }

    @Override
    public Double create() {

        double d = randomGenerator.getRandomDouble() * (criteria.getMax() - criteria.getMin()) + criteria.getMin();

        d = BigDecimal.valueOf(d).setScale(criteria.getNumberOfDecimals(), RoundingMode.HALF_UP).doubleValue();

        return d;
    }
}
