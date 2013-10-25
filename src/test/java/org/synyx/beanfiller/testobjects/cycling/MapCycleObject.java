
package org.synyx.beanfiller.testobjects.cycling;

import java.util.Map;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class MapCycleObject {

    private Map<String, MapCycleObject> map;

    public Map<String, MapCycleObject> getMap() {

        return map;
    }


    public void setMap(Map<String, MapCycleObject> map) {

        this.map = map;
    }
}
