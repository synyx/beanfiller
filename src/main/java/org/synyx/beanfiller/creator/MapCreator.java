package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.MapCriteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Creator for Maps.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class MapCreator implements Creator {

    private final MapCriteria criteria;

    /**
     * Create a new MapCreator with the default MapCriteria.
     */
    public MapCreator() {

        this.criteria = new MapCriteria(3);
    }


    /**
     * Create a new MapCreator with the given criteria.
     *
     * @param  criteria  the criteria to use.
     */
    public MapCreator(MapCriteria criteria) {

        this.criteria = criteria;
    }

    /**
     * Creates a HashMap and fills it with the given keys and values.
     *
     * @param  keys  keys to use.
     * @param  values  values to use.
     *
     * @return  the created Map.
     */
    public Map<?, ?> create(List<Object> keys, List<Object> values) {

        Map<Object, Object> map = new HashMap<>();

        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }

        return map;
    }


    /**
     * @return  the size of the Maps created by this Creator
     */
    public int getSize() {

        return criteria.getSize();
    }


    /**
     * @return  true if there are keys set that can be used, false otherwise.
     */
    public boolean hasKeys() {

        return criteria.getKeys() != null;
    }


    /**
     * @return  the keys to be used for created maps.
     */
    public List<Object> getKeys() {

        return criteria.getKeys();
    }
}
