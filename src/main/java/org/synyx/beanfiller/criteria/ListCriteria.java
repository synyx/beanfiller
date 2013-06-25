
package org.synyx.beanfiller.criteria;

import java.util.List;


/**
 * Criteria for Lists.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ListCriteria implements Criteria<List<?>> {

    private int size;

    public ListCriteria() {

        this(3);
    }


    public ListCriteria(int size) {

        this.size = size;
    }

    public int getSize() {

        return size;
    }


    public void setSize(int size) {

        this.size = size;
    }
}
