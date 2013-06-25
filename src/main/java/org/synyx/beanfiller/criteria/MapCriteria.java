
package org.synyx.beanfiller.criteria;

import java.util.Map;


/**
 * Criteria for Maps.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class MapCriteria implements Criteria<Map<?, ?>> {

    private int size;

    public MapCriteria() {

        this(3);
    }


    public MapCriteria(int size) {

        this.size = size;
    }

    public int getSize() {

        return size;
    }


    public void setSize(int size) {

        this.size = size;
    }
}
