package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.ShortCriteria;
import org.synyx.beanfiller.util.RandomGenerator;


/**
 * Creator for Short values. Created by knell on 17.12.13.
 */
public class ShortCreator implements SimpleCreator<Short> {

    private final RandomGenerator randomGenerator;
    private final ShortCriteria criteria;

    /**
     * Creates new ShortCreator using the default ShortCriteria.
     *
     * @param  randomGenerator
     */
    public ShortCreator(RandomGenerator randomGenerator) {

        this(randomGenerator, new ShortCriteria());
    }


    /**
     * Creates new ShortCreator using the given ShortCriteria.
     *
     * @param  randomGenerator
     * @param  criteria  the ShortCriteria to use.
     */
    public ShortCreator(RandomGenerator randomGenerator, ShortCriteria criteria) {

        this.randomGenerator = randomGenerator;
        this.criteria = criteria;
    }

    @Override
    public Short create() {

        return (short) Math.round(randomGenerator.getRandomDouble() * (criteria.getMax() - criteria.getMin())
                + criteria.getMin());
    }
}
