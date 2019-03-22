
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

    private final RandomGenerator randomGenerator;
    private final NumberCriteria<Integer> criteria;

    /**
     * Create a new IntegerCreator with the default IntegerCriteria.
     *
     * @param  randomGenerator
     */
    public IntegerCreator(RandomGenerator randomGenerator) {

        this(randomGenerator, new IntegerCriteria());
    }


    /**
     * Create a new IntegerCreator with the given criteria.
     *
     * @param  randomGenerator
     * @param  criteria  the criteria to use.
     */
    public IntegerCreator(RandomGenerator randomGenerator, NumberCriteria<Integer> criteria) {

        this.randomGenerator = randomGenerator;
        this.criteria = criteria;
    }

    @Override
    public Integer create() {

        return randomGenerator.getRandomInt(criteria.getMax() - criteria.getMin() + 1) + criteria.getMin();
    }
}
