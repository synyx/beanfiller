
package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.criteria.LongCriteria;
import org.synyx.beanfiller.services.RandomGenerator;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class LongBuilder implements Builder<Long> {

    private LongCriteria criteria;

    public LongBuilder() {

        this(new LongCriteria());
    }


    public LongBuilder(LongCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Long build() {

        return Math.round(RandomGenerator.getRandomDouble() * (criteria.getMax() - criteria.getMin())
                + criteria.getMin());
    }
}
