
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.AbstractNumberCriteria;
import org.synyx.beanfiller.criteria.LongCriteriaAbstract;
import org.synyx.beanfiller.util.RandomGenerator;


/**
 * Creator for Longs.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class LongCreator implements SimpleCreator<Long> {

    private final AbstractNumberCriteria<Long> criteria;

    /**
     * Create a new LongCreator with the default LongCriteriaAbstract.
     */
    public LongCreator() {

        this(new LongCriteriaAbstract());
    }


    /**
     * Create a new LongCreator with the given criteria.
     *
     * @param  criteria  the criteria to use.
     */
    public LongCreator(AbstractNumberCriteria<Long> criteria) {

        this.criteria = criteria;
    }

    @Override
    public Long create() {

        return Math.round(RandomGenerator.getRandomDouble() * (criteria.getMax() - criteria.getMin())
                + criteria.getMin());
    }
}
