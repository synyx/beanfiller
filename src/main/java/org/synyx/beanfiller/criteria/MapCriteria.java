
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

    /**
     * Creates new MapCriteria with the default values.
     */
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

    /**
     * @return  the size maps should be created with.
     */
    public int getSize() {

        return size;
    }


    /**
     * @param  size  the size maps should be created with.
     */
    public void setSize(int size) {

        this.size = size;
    }


    /**
     * @return  Keys the created Maps should have.
     */
    public List<Object> getKeys() {

        return keys;
    }


    /**
     * @param  keys  Keys the created Maps should have.
     */
    public void setKeys(List<Object> keys) {

        this.keys = keys;
    }
}
