package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.FloatCriteria;
import org.synyx.beanfiller.services.RandomGenerator;

import java.math.BigDecimal;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class FloatCreator implements SimpleCreator<Float> {

    private FloatCriteria criteria;

    public FloatCreator() {

        this(new FloatCriteria());
    }


    public FloatCreator(FloatCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Float create() {

        float f = RandomGenerator.getRandomFloat() * (criteria.getMax() - criteria.getMin()) + criteria.getMin();

        f = BigDecimal.valueOf(f).setScale(criteria.getDecimals(), BigDecimal.ROUND_HALF_UP).floatValue();

        return f;
    }
}
