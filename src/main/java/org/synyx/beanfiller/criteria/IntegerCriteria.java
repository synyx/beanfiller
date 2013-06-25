package org.synyx.beanfiller.criteria;

/**
 * Criteria for Integers.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class IntegerCriteria extends NumberCriteria<Integer> {

    /**
     * Create a new IntegerCriteria with the default values.
     */
    public IntegerCriteria() {

        this(0, 1000);
    }


    /**
     * Create a new IntegerCriteria with the given values.
     *
     * @param  min  of the created number
     * @param  max  of the created number
     */
    public IntegerCriteria(Integer min, Integer max) {

        super(min, max);
    }
}
