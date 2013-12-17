
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.IntegerCriteria;
import org.synyx.beanfiller.criteria.NumberCriteria;
import org.synyx.beanfiller.util.RandomGenerator;


/**
 * Creator for Integers.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class IntegerCreator implements SimpleCreator<Integer> {

    private final NumberCriteria<Integer> criteria;

    /**
     * Create a new IntegerCreator with the default IntegerCriteria.
     */
    public IntegerCreator() {

        this(new IntegerCriteria());
    }


    /**
     * Create a new IntegerCreator with the given criteria.
     *
     * @param  criteria  the criteria to use.
     */
    public IntegerCreator(NumberCriteria<Integer> criteria) {

        this.criteria = criteria;
    }

    @Override
    public Integer create() {

        return RandomGenerator.getRandomInt(criteria.getMax() - criteria.getMin() + 1) + criteria.getMin();
    }
}
