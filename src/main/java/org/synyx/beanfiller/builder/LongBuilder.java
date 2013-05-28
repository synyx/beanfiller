
package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.criteria.LongCriteria;

import java.util.Random;


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

        Random rand = new Random();

        return Math.round(rand.nextDouble() * (criteria.getMax() - criteria.getMin()) + criteria.getMin());
    }
}
