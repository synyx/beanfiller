
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.LongCriteria;
import org.synyx.beanfiller.criteria.NumberCriteria;
import org.synyx.beanfiller.util.RandomGenerator;


/**
 * Creator for Longs.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class LongCreator implements SimpleCreator<Long> {

    private final RandomGenerator randomGenerator;
    private final NumberCriteria<Long> criteria;

    /**
     * Create a new LongCreator with the default LongCriteria.
     *
     * @param  randomGenerator
     */
    public LongCreator(RandomGenerator randomGenerator) {

        this(randomGenerator, new LongCriteria());
    }


    /**
     * Create a new LongCreator with the given criteria.
     *
     * @param  randomGenerator
     * @param  criteria  the criteria to use.
     */
    public LongCreator(RandomGenerator randomGenerator, NumberCriteria<Long> criteria) {

        this.randomGenerator = randomGenerator;
        this.criteria = criteria;
    }

    @Override
    public Long create() {

        return Math.round(randomGenerator.getRandomDouble() * (criteria.getMax() - criteria.getMin())
                + criteria.getMin());
    }
}
