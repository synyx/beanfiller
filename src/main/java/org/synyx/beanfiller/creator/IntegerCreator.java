
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.AbstractNumberCriteria;
import org.synyx.beanfiller.criteria.IntegerCriteriaAbstract;
import org.synyx.beanfiller.util.RandomGenerator;


/**
 * Creator for Integers.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class IntegerCreator implements SimpleCreator<Integer> {

    private final AbstractNumberCriteria<Integer> criteria;

    /**
     * Create a new IntegerCreator with the default IntegerCriteriaAbstract.
     */
    public IntegerCreator() {

        this(new IntegerCriteriaAbstract());
    }


    /**
     * Create a new IntegerCreator with the given criteria.
     *
     * @param  criteria  the criteria to use.
     */
    public IntegerCreator(AbstractNumberCriteria<Integer> criteria) {

        this.criteria = criteria;
    }

    @Override
    public Integer create() {

        return RandomGenerator.getRandomInt(criteria.getMax() - criteria.getMin() + 1) + criteria.getMin();
    }
}
