package org.synyx.beanfiller.criteria;

/**
 * Criteria for Integers.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class IntegerCriteriaAbstract extends AbstractNumberCriteria<Integer> {

    private static final int MAX = 1000;
    private static final int MIN = 0;

    /**
     * Create a new IntegerCriteriaAbstract with the default values.
     */
    public IntegerCriteriaAbstract() {

        this(MIN, MAX);
    }


    /**
     * Create a new IntegerCriteriaAbstract with the given values.
     *
     * @param  min  of the created number
     * @param  max  of the created number
     */
    public IntegerCriteriaAbstract(Integer min, Integer max) {

        super(min, max);
    }
}
