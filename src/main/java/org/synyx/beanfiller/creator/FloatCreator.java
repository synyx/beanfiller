package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.AbstractDecimalsAbstractNumberCriteria;
import org.synyx.beanfiller.criteria.FloatCriteriaAbstractAbstract;
import org.synyx.beanfiller.util.RandomGenerator;

import java.math.BigDecimal;


/**
 * Creator for Floats.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class FloatCreator implements SimpleCreator<Float> {

    private final AbstractDecimalsAbstractNumberCriteria<Float> criteria;

    /**
     * Create a new FloatCreator with the default FloatCriteriaAbstractAbstract.
     *
     * @param  criteria  the criteria to use.
     */
    public FloatCreator() {

        this(new FloatCriteriaAbstractAbstract());
    }


    /**
     * Create a new FloatCreator with the given criteria.
     *
     * @param  criteria  the criteria to use.
     */
    public FloatCreator(AbstractDecimalsAbstractNumberCriteria<Float> criteria) {

        this.criteria = criteria;
    }

    @Override
    public Float create() {

        float f = RandomGenerator.getRandomFloat() * (criteria.getMax() - criteria.getMin()) + criteria.getMin();

        f = BigDecimal.valueOf(f).setScale(criteria.getNumberOfDecimals(), BigDecimal.ROUND_HALF_UP).floatValue();

        return f;
    }
}
