package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.DecimalsNumberCriteria;
import org.synyx.beanfiller.criteria.FloatCriteria;
import org.synyx.beanfiller.services.RandomGenerator;

import java.math.BigDecimal;


/**
 * Creator for Floats.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class FloatCreator implements SimpleCreator<Float> {

    private DecimalsNumberCriteria<Float> criteria;

    /**
     * Create a new FloatCreator with the default FloatCriteria.
     *
     * @param  criteria  the criteria to use.
     */
    public FloatCreator() {

        this(new FloatCriteria());
    }


    /**
     * Create a new FloatCreator with the given criteria.
     *
     * @param  criteria  the criteria to use.
     */
    public FloatCreator(DecimalsNumberCriteria<Float> criteria) {

        this.criteria = criteria;
    }

    @Override
    public Float create() {

        float f = RandomGenerator.getRandomFloat() * (criteria.getMax() - criteria.getMin()) + criteria.getMin();

        f = BigDecimal.valueOf(f).setScale(criteria.getNumberOfDecimals(), BigDecimal.ROUND_HALF_UP).floatValue();

        return f;
    }
}
