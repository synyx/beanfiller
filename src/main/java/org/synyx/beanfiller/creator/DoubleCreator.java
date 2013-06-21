
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.DoubleCriteria;
import org.synyx.beanfiller.services.RandomGenerator;

import java.math.BigDecimal;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class DoubleCreator implements SimpleCreator<Double> {

    private DoubleCriteria criteria;

    public DoubleCreator() {

        this(new DoubleCriteria());
    }


    public DoubleCreator(DoubleCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Double create() {

        double d = RandomGenerator.getRandomDouble() * (criteria.getMax() - criteria.getMin()) + criteria.getMin();

        d = BigDecimal.valueOf(d).setScale(criteria.getDecimals(), BigDecimal.ROUND_HALF_UP).doubleValue();

        return d;
    }
}
