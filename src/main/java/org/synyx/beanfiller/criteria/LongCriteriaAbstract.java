
package org.synyx.beanfiller.criteria;

/**
 * Criteria for Longs.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class LongCriteriaAbstract extends AbstractNumberCriteria<Long> {

    private static final long MAX = 1000L;
    private static final long MIN = 0L;

    /**
     * Create a new LongCriteriaAbstract with the default values.
     */
    public LongCriteriaAbstract() {

        this(MIN, MAX);
    }


    /**
     * Create a new LongCriteriaAbstract with the given values.
     *
     * @param  min  of the created number
     * @param  max  of the created number
     */
    public LongCriteriaAbstract(Long min, Long max) {

        super(min, max);
    }
}
