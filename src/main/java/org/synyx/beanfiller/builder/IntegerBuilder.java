
package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.criteria.IntegerCriteria;
import org.synyx.beanfiller.services.RandomGenerator;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class IntegerBuilder implements Builder<Integer> {

    private IntegerCriteria criteria;

    public IntegerBuilder() {

        this(new IntegerCriteria());
    }


    public IntegerBuilder(IntegerCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Integer build() {

        return RandomGenerator.getRandomInt(criteria.getMax() - criteria.getMin() + 1) + criteria.getMin();
    }
}
