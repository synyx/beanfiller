
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.IntegerCriteria;
import org.synyx.beanfiller.services.RandomGenerator;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class IntegerCreator implements SimpleCreator<Integer> {

    private IntegerCriteria criteria;

    public IntegerCreator() {

        this(new IntegerCriteria());
    }


    public IntegerCreator(IntegerCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Integer create() {

        return RandomGenerator.getRandomInt(criteria.getMax() - criteria.getMin() + 1) + criteria.getMin();
    }
}
