
package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.criteria.IntegerCriteria;

import java.util.Random;


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

        Random rand = new Random();

        return rand.nextInt(criteria.getMax() - criteria.getMin() + 1) + criteria.getMin();
    }
}
