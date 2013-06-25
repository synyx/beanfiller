
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.LongCriteria;
import org.synyx.beanfiller.criteria.NumberCriteria;
import org.synyx.beanfiller.services.RandomGenerator;


/**
 * Creator for Longs.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class LongCreator implements SimpleCreator<Long> {

    private NumberCriteria<Long> criteria;

    /**
     * Create a new LongCreator with the default LongCriteria.
     */
    public LongCreator() {

        this(new LongCriteria());
    }


    /**
     * Create a new LongCreator with the given criteria.
     *
     * @param  criteria  the criteria to use.
     */
    public LongCreator(NumberCriteria<Long> criteria) {

        this.criteria = criteria;
    }

    @Override
    public Long create() {

        return Math.round(RandomGenerator.getRandomDouble() * (criteria.getMax() - criteria.getMin())
                + criteria.getMin());
    }
}
