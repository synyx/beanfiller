package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.MapCriteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class MapCreator implements GenericsCreator<Map<?, ?>> {

    private MapCriteria criteria;

    public MapCreator(MapCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Map<?, ?> createWithGenerics(List<Object> genericObjects) {

        if (genericObjects.size() >= 2) {
            Map<Object, Object> map = new HashMap<Object, Object>();

            for (int i = 0; i < genericObjects.size(); i = i + 2) {
                map.put(genericObjects.get(i), genericObjects.get(i + 1));
            }

            return map;
        } else {
            return null;
        }
    }


    @Override
    public int getSize() {

        return criteria.getSize();
    }
}