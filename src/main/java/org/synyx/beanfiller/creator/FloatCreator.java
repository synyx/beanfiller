package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.DecimalsNumberCriteria;
import org.synyx.beanfiller.criteria.FloatCriteria;
import org.synyx.beanfiller.util.RandomGenerator;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * Creator for Floats.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class FloatCreator implements SimpleCreator<Float> {

    private final RandomGenerator randomGenerator;
    private final DecimalsNumberCriteria<Float> criteria;

    /**
     * Create a new FloatCreator with the default FloatCriteria.
     *
     * @param  randomGenerator
     */
    public FloatCreator(RandomGenerator randomGenerator) {

        this(randomGenerator, new FloatCriteria());
    }


    /**
     * Create a new FloatCreator with the given criteria.
     *
     * @param  randomGenerator
     * @param  criteria  the criteria to use.
     */
    public FloatCreator(RandomGenerator randomGenerator, DecimalsNumberCriteria<Float> criteria) {

        this.randomGenerator = randomGenerator;
        this.criteria = criteria;
    }

    @Override
    public Float create() {

        float f = randomGenerator.getRandomFloat() * (criteria.getMax() - criteria.getMin()) + criteria.getMin();

        f = BigDecimal.valueOf(f).setScale(criteria.getNumberOfDecimals(), RoundingMode.HALF_UP).floatValue();

        return f;
    }
}
