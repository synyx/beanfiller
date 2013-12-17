package org.synyx.beanfiller.criteria;

/**
 * Created by knell on 17.12.13.
 */
public class ShortCriteria extends NumberCriteria<Short> {

    /**
     * Create a new ShortCriteria with the default values.
     */
    public ShortCriteria() {

        super((short) 0, (short) 1000);
    }


    /**
     * @param  min  minimum value of the created number
     * @param  max  maximum value of the created number
     */
    public ShortCriteria(Short min, Short max) {

        super(min, max);
    }
}
