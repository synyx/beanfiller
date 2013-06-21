package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.criteria.FloatCriteria;
import org.synyx.beanfiller.services.RandomGenerator;

import java.math.BigDecimal;


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

        float f = RandomGenerator.getRandomFloat() * (criteria.getMax() - criteria.getMin()) + criteria.getMin();

        f = BigDecimal.valueOf(f).setScale(criteria.getDecimals(), BigDecimal.ROUND_HALF_UP).floatValue();

        return f;
    }
}
