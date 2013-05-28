
package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.criteria.DoubleCriteria;

import java.math.BigDecimal;

import java.util.Random;


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

        Random rand = new Random();

        double d = rand.nextDouble() * (criteria.getMax() - criteria.getMin()) + criteria.getMin();

        d = BigDecimal.valueOf(d).setScale(criteria.getDecimals(), BigDecimal.ROUND_HALF_UP).doubleValue();

        return d;
    }
}
