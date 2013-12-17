package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.ShortCriteria;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.util.RandomGenerator;


/**
 * Creator for Short values. Created by knell on 17.12.13.
 */
public class ShortCreator implements SimpleCreator<Short> {

    private final ShortCriteria criteria;

    /**
     * Creates new ShortCreator using the default ShortCriteria.
     */
    public ShortCreator() {

        this.criteria = new ShortCriteria();
    }


    /**
     * Creates new ShortCreator using the given ShortCriteria.
     *
     * @param  criteria  the ShortCriteria to use.
     */
    public ShortCreator(ShortCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Short create() throws FillingException {

        return (short) Math.round(RandomGenerator.getRandomDouble() * (criteria.getMax() - criteria.getMin())
                + criteria.getMin());
    }
}
