
package org.synyx.beanfiller.criteria;

import java.util.List;
import java.util.Map;


/**
 * Criteria for Maps.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class MapCriteria implements Criteria<Map<?, ?>> {

    private int size;
    private List<Object> keys;

    public MapCriteria() {

        this(3);
    }


    /**
     * Init with the number of entries, the map should have.
     *
     * @param  size  number of entries.
     */
    public MapCriteria(int size) {

        this.size = size;
    }


    /**
     * Init with a List of keys that should be used.
     *
     * @param  keys  Keys of the map.
     */
    public MapCriteria(List<Object> keys) {

        this.keys = keys;
        this.size = keys.size();
    }

    public int getSize() {

        return size;
    }


    public void setSize(int size) {

        this.size = size;
    }


    public List<Object> getKeys() {

        return keys;
    }


    public void setKeys(List<Object> keys) {

        this.keys = keys;
    }
}
