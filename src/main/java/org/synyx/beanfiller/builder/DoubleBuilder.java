
package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.criteria.DoubleCriteria;
import org.synyx.beanfiller.services.RandomGenerator;

import java.math.BigDecimal;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class DoubleBuilder implements Builder<Double> {

    private DoubleCriteria criteria;

    public DoubleBuilder() {

        this(new DoubleCriteria());
    }


    public DoubleBuilder(DoubleCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Double build() {

        double d = RandomGenerator.getRandomDouble() * (criteria.getMax() - criteria.getMin()) + criteria.getMin();

        d = BigDecimal.valueOf(d).setScale(criteria.getDecimals(), BigDecimal.ROUND_HALF_UP).doubleValue();

        return d;
    }
}
