
package org.synyx.beanfiller.criteria;

import java.util.List;


/**
 * Criteria for Lists.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class CollectionCriteria implements Criteria<List<?>> {

    private static final int SIZE = 3;

    private int size;

    public CollectionCriteria() {

        this(SIZE);
    }


    public CollectionCriteria(int size) {

        this.size = size;
    }

    public int getSize() {

        return size;
    }


    public void setSize(int size) {

        this.size = size;
    }
}
