package org.synyx.beanfiller.testobjects;

import java.util.List;

public class ListConstructorObject {

    private final List<String> values;

    public ListConstructorObject(List<String> values){

        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }
}
