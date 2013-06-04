
package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.criteria.FloatCriteria;

import java.math.BigDecimal;

import java.util.Random;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class FloatBuilder implements Builder<Float> {

    private FloatCriteria criteria;

    public FloatBuilder() {

        this(new FloatCriteria());
    }


    public FloatBuilder(FloatCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Float build() {

        Random rand = new Random(System.currentTimeMillis());

        float f = rand.nextFloat() * (criteria.getMax() - criteria.getMin()) + criteria.getMin();

        f = BigDecimal.valueOf(f).setScale(criteria.getDecimals(), BigDecimal.ROUND_HALF_UP).floatValue();

        return f;
    }
}
