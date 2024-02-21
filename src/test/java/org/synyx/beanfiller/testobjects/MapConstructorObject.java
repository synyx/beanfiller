package org.synyx.beanfiller.testobjects;

import java.util.Map;

public class MapConstructorObject {

    private final Map<Integer, String> values;

    public MapConstructorObject(Map<Integer, String> values){

        this.values = values;
    }

    public Map<Integer, String> getValues() {
        return values;
    }
}
