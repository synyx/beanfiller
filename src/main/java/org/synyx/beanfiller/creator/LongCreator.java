
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.LongCriteria;
import org.synyx.beanfiller.services.RandomGenerator;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class LongCreator implements SimpleCreator<Long> {

    private LongCriteria criteria;

    public LongCreator() {

        this(new LongCriteria());
    }


    public LongCreator(LongCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Long create() {

        return Math.round(RandomGenerator.getRandomDouble() * (criteria.getMax() - criteria.getMin())
                + criteria.getMin());
    }
}
