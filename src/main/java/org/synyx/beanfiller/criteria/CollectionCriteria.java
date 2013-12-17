
package org.synyx.beanfiller.criteria;

import java.util.List;


/**
 * Criteria for Lists.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class CollectionCriteria implements Criteria<List<?>> {

    /**
     * Size of the collection to create.
     */
    private int size;

    /**
     * Create a new CollectionCriteria using the default values.
     */
    public CollectionCriteria() {

        this(3);
    }


    /**
     * @param  size  Size of the collection to create.
     */
    public CollectionCriteria(int size) {

        this.size = size;
    }

    /**
     * @return  Size of the collection to create.
     */
    public int getSize() {

        return size;
    }


    /**
     * @param  size  Size of the collection to create.
     */
    public void setSize(int size) {

        this.size = size;
    }
}
