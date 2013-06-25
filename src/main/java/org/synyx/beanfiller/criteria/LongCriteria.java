
package org.synyx.beanfiller.criteria;

/**
 * Criteria for Longs.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class LongCriteria extends NumberCriteria<Long> {

    /**
     * Create a new LongCriteria with the default values.
     */
    public LongCriteria() {

        this(0L, 1000L);
    }


    /**
     * Create a new LongCriteria with the given values.
     *
     * @param  min  of the created number
     * @param  max  of the created number
     */
    public LongCriteria(Long min, Long max) {

        super(min, max);
    }
}
